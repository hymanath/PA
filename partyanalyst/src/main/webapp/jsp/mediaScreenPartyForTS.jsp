<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MEDIA SCREEN PARTY FOR TELENGANA</title>
<link href="newCoreDashBoard/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="http://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
<link href="newCoreDashBoard/Plugins/Date/daterangepicker.css" rel="stylesheet" type="text/css"/>
<link href="dist/DatatableBootstrap/DatatableB.css" rel="stylesheet" type="text/css"/>
<link href="dist/Plugins/Chosen/chosen.css" rel="stylesheet" type="text/css"/>
<link href="dist/2016DashBoard/Plugins/Scroller/jquery.mCustomScrollbar.css" rel="stylesheet" type="text/css"/>
<style type="text/css">
body
{
	background-image:url("dist/mediaScreenParty/background.png");
}
.mCSB_inside > .mCSB_container
{
	margin-right:10px;
}
.text-capital
{
	text-transform:uppercase;
}
.count
{
	background-color:#ddd;
	border-radius:50%;
	height:25px;
	width:25px;
	display:block;
	text-align:center;
	font-size:16px;
	color:#fff;
}
h1,h2,h3,h4,h5,h6,.table
{
	margin:0px;
}
<!--.menuR
{
	display:none;
}-->
.spinner {
  margin: 30px auto;
  width: 40px;
  height: 40px;
  position: relative;
  text-align: center;
  
  -webkit-animation: sk-rotate 2.0s infinite linear;
  animation: sk-rotate 2.0s infinite linear;
}

.dot1, .dot2 {
  width: 60%;
  height: 60%;
  display: inline-block;
  position: absolute;
  top: 0;
  background-color: #1ABC9C;
  border-radius: 100%;
  
  -webkit-animation: sk-bounce 2.0s infinite ease-in-out;
  animation: sk-bounce 2.0s infinite ease-in-out;
}

.dot2 {
  top: auto;
  bottom: 0;
  -webkit-animation-delay: -1.0s;
  animation-delay: -1.0s;
}

@-webkit-keyframes sk-rotate { 100% { -webkit-transform: rotate(360deg) }}
@keyframes sk-rotate { 100% { transform: rotate(360deg); -webkit-transform: rotate(360deg) }}

@-webkit-keyframes sk-bounce {
  0%, 100% { -webkit-transform: scale(0.0) }
  50% { -webkit-transform: scale(1.0) }
}

@keyframes sk-bounce {
  0%, 100% { 
    transform: scale(0.0);
    -webkit-transform: scale(0.0);
  } 50% { 
    transform: scale(1.0);
    -webkit-transform: scale(1.0);
  }
}
</style>
</head>
<body>
<div class="container">
	<div class="row">
		<div class="col-md-12 col-xs-12 col-sm-12">
			<h3 style="margin-top:-30px;color:#EE4A24" class="text-center"><b>2014 - 2016 MEMBERSHIP DRIVE</b></h3>
		</div>
		<div class="col-md-12 col-xs-12 col-sm-12">
			<label class="radio-inline pull-right" style="margin-left:10px;">
				<input type="radio" name="radio" class="radioCls" value="2"><b>Target Achieved[%]</b></input>
			</label>
			<label class="radio-inline pull-right">
				<input type="radio" name="radio" class="radioCls" checked value="1"><b>Registrations</b></input>
			</label>
			<label class="radio-inline pull-right">
				<b>Sort By: </b>
			</label>
		</div>
	</div>
	<div class="row">		
		<div class="row">
			<div class="col-md-6 col-xs-12 col-sm-6">
			<div class="panel panel-default">
				<div class="panel-heading" style="background:#fff;padding:5px">
					<div class="row">
						<div class="col-md-2 col-xs-4 col-sm-3">
							<img src="dist/mediaScreenParty/Notification.png" class="img-responsive" style="width:50px;"/>
						</div>
						<div class="col-md-10 col-xs-4 col-sm-9">
							<h5 class="text-capital" style="font-size:12px;"><span style="color:#3A98DE">Today TOp</span></h5>
							<h4 class="text-capital">Districts in ts</h4>
							<h5 style="margin-right: 25px;font-size:12px;display:none;" class="pull-right percentage">Percentage[%]</h5>
							<h5 style="margin-right: 25px;font-size:12px;" class="pull-right counts">Registrations</h5>
						</div>
					</div>
				</div>
				<div class="panel-body" style="padding:0px;" id="todayDistrictList">
					<div id="todaytsImgId"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>
				</div>
			</div>
		</div>
		
			<div class="col-md-6 col-xs-12 col-sm-6">
				<div class="panel panel-default">
					<div class="panel-heading" style="background:#fff;padding:5px">
						<div class="row">
							<div class="col-md-2 col-xs-4 col-sm-3">
								<img src="dist/mediaScreenParty/Notification.png" class="img-responsive" style="width:50px;"/>
							</div>
							<div class="col-md-10 col-xs-4 col-sm-9">
								<h5 class="text-capital" style="font-size:12px;"><span style="color:#3A98DE">today TOp 20</span></h5>
								<h4 class="text-capital">constituencies in ts</h4>
								<h5 style="margin-right: 25px;font-size:12px;display:none;" class="pull-right percentage">Percentage[%]</h5>
								<h5 style="margin-right: 25px;font-size:12px;" class="pull-right counts">Registrations</h5>
							</div>
						</div>
					</div>
				<div class="panel-body" style="padding:0px;" id="constituencyListPanel2">
					<div id="todaytsImgId"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>
				</div>
			</div>
		</div>
	</div>	
  </div>
  <div class="row">		
	<div class="row">
		<div class="col-md-6 col-xs-12 col-sm-6">
			<div class="panel panel-default">
				<div class="panel-heading" style="background:#fff;padding:5px;">
					<div class="row">
						<div class="col-md-2 col-xs-4 col-sm-3">
							<img src="dist/mediaScreenParty/Notification.png" class="img-responsive" style="width:50px;"/>
						</div>
						<div class="col-md-10 col-xs-4 col-sm-9">
							<h5 class="text-capital" style="font-size:12px;"><span style="color:#9D0D3E">OverAll TOp</span></h5>
							<h4 class="text-capital">District in ts</h4>
							<h5 style="margin-right: 25px;font-size:12px;display:none;" class="pull-right percentage">Percentage[%]</h5>
							<h5 style="margin-right: 25px;font-size:12px;" class="pull-right counts">Registrations</h5>
						</div>
					</div>
				</div>
				<div class="panel-body" style="padding:0px;" id="overAllDistrictList">
				<div id="totaltsimgId"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>
				</div>
			</div>
		</div>
	 
		<div class="col-md-6 col-xs-12 col-sm-6">
			<div class="panel panel-default">
				<div class="panel-heading" style="background:#fff;padding:5px;">
					<div class="row">
						<div class="col-md-2 col-xs-4 col-sm-3">
							<img src="dist/mediaScreenParty/Notification.png" class="img-responsive" style="width:50px;"/>
						</div>
						<div class="col-md-10 col-xs-4 col-sm-9">
							<h5 class="text-capital" style="font-size:12px;"><span style="color:#9D0D3E">OverAll TOp 20</span></h5>
							<h4 class="text-capital">Constituencies in ts</h4>
							<h5 style="margin-right: 25px;font-size:12px;display:none;" class="pull-right percentage">Percentage[%]</h5>
							<h5 style="margin-right: 25px;font-size:12px;" class="pull-right counts">Registrations</h5>
						</div>
					</div>
				</div>
				<div class="panel-body" style="padding:0px;" id="constituencyListPanel4">
				<div id="totaltsimgId"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>
				</div>
			</div>
		</div>
	</div>	
  </div>
  
 </div> 
 
 
<script src="newCoreDashBoard/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="newCoreDashBoard/js/bootstrap.min.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Date/moment.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Date/daterangepicker.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Highcharts/highcharts.js" type="text/javascript"></script>
<script src="dist/Plugins/Chosen/chosen.jquery.js" type="text/javascript"></script>
<!--<script src="js/FieldMonitoring/fieldMonitoringDashboard.js" type="text/javascript"></script>-->
<script src="dist/2016DashBoard/Plugins/Scroller/jquery.mCustomScrollbar.js" type="text/javascript"></script>
<script src="https://cdn.datatables.net/v/bs/dt-1.10.12/datatables.min.js" type="text/javascript"></script>	
<script type="text/javascript"> 
$("#menu").parent().remove();

getConstituencyWiseDisrictList2("Today",36,"count");
getConstituencyWiseDisrictList4("Total",36,"count");
getTodayDistrictList("Today",36,"count");
getOverAllDistrictList("Total",36,"count");
function getConstituencyWiseDisrictList2(type,stateId,sortType){
	$("#todaytsImgId").show();
	var jObj = {
		type:type,
		stateId:stateId,
		sortType : sortType
	}
	$.ajax({
	  type:'GET',
	  url: 'getConstituencyWiseTodayAndOverAllCountsAction.action',
	  data : {task:JSON.stringify(jObj)} ,
	}).done(function(result){
		if(result != null && result.length > 0){
			buildingConstituencyList(result,"constituencyListPanel2",type,sortType);
		}
		$("#todaytsImgId").hide();
	});
}

function getConstituencyWiseDisrictList4(type,stateId,sortType){
	$("#totaltsimgId").show();
	var jObj = {
		type:type,
		stateId:stateId,
		sortType :sortType
	}
	$.ajax({
	  type:'GET',
	  url: 'getConstituencyWiseTodayAndOverAllCountsAction.action',
	  data : {task:JSON.stringify(jObj)} ,
	}).done(function(result){
		if(result != null && result.length > 0){
			buildingConstituencyList(result,"constituencyListPanel4",type,sortType);
		}
		$("#totaltsimgId").hide();
	});
}
function getTodayDistrictList(type,stateId,sortType){
	$("#todaytsImgId").show();
	var jObj = {
		type:type,
		stateId:stateId,
		sortType:sortType
	}
	$.ajax({
	  type:'GET',
	  url: 'getDistrictWiseTodayAndOverAllCountsAction.action',
	  data : {task:JSON.stringify(jObj)} ,
	}).done(function(result){
		if(result != null && result.length > 0){
			buildingConstituencyList(result,"todayDistrictList",type,sortType);
		}
		$("#todaytsImgId").hide();
	});
}
function getOverAllDistrictList(type,stateId,sortType){
	$("#todaytsImgId").show();
	var jObj = {
		type:type,
		stateId:stateId,
		sortType:sortType
	}
	$.ajax({
	  type:'GET',
	  url: 'getDistrictWiseTodayAndOverAllCountsAction.action',
	  data : {task:JSON.stringify(jObj)} ,
	}).done(function(result){
		if(result != null && result.length > 0){
			buildingConstituencyList(result,"overAllDistrictList",type,sortType);
		}
		$("#todaytsImgId").hide();
	});
}


function buildingConstituencyList(result,divId,type,sortType){
	var str='';
	str+='<div class="'+divId+'" style="height:160px;overflow-y:scroll;">';
		str+='<table class="table table-condensed" style="margin-bottom:0px;">';
		for(var i in result){
			var temp = parseInt(i)+1;
			str+='<tr>';
				if(type == "Today"){
					str+='<td><span class="count" style="background-color:#3A98DE"><b>'+temp+'</b></span></td>';
				}else{
					str+='<td><span class="count" style="background-color:#9D0D3E"><b>'+temp+'</b></span></td>';
				}
				str+='<td class="text-capital">'+result[i].name+'</td>';
				if(sortType == "count")
					str+='<td style="width:80px">'+result[i].attenteeCount+'</td>';
				else if(sortType == "percentage")
					str+='<td style="width:80px">'+result[i].per2016+'</td>';
			str+='</tr>';
		}
		
		str+='</table>';
	str+='</div>';
	$("#"+divId).html(str);
	//$("."+divId).mCustomScrollbar({setHeight:'200px'})
	
	var div = $("."+divId);
	var myVar;
	myFunction()
	function myFunction() {
		myVar = setInterval(function(){	var pos = div.scrollTop();div.scrollTop(pos + 2);}, 100)
	}

	setInterval(function(){
		clearTimeout(myVar);
		setInterval(function(){
			var pos = div.scrollTop();
			div.scrollTop(pos - 2);
		}, 100)
	}, 29000)
}	

//setTimeout(function(){ location.reload(); }, 43000);
setInterval(function() {
    refreshFunctions();
  }, 60 * 1000)

function refreshFunctions(){
	//alert(123);
	var value = $('input[name=radio]:checked').val();
	
	if(value == 1){
		getConstituencyWiseDisrictList2("Today",36,"count");
		getConstituencyWiseDisrictList4("Total",36,"count");
		getTodayDistrictList("Today",36,"count");
		getOverAllDistrictList("Total",36,"count");
		
		$(".percentage").hide();
		$(".counts").show();
	}
	else if(value == 2){
		getConstituencyWiseDisrictList2("Today",36,"percentage");
		getConstituencyWiseDisrictList4("Total",36,"percentage");
		getTodayDistrictList("Today",36,"percentage");
		getOverAllDistrictList("Total",36,"percentage");
		
		$(".percentage").show();
		$(".counts").hide();
	}	
}

$(document).on("click",".radioCls",function(){
	var value = $('input[name=radio]:checked').val();
	
	if(value == 1){
		getConstituencyWiseDisrictList2("Today",36,"count");
		getConstituencyWiseDisrictList4("Total",36,"count");
		getTodayDistrictList("Today",36,"count");
		getOverAllDistrictList("Total",36,"count");
		
		$(".percentage").hide();
		$(".counts").show();
	}
	else if(value == 2){
		getConstituencyWiseDisrictList2("Today",36,"percentage");
		getConstituencyWiseDisrictList4("Total",36,"percentage");
		getTodayDistrictList("Today",36,"percentage");
		getOverAllDistrictList("Total",36,"percentage");
		
		$(".percentage").show();
		$(".counts").hide();
	}	
});

</script>
</body>
</html>