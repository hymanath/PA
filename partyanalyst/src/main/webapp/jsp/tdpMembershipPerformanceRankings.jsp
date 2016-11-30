<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>TDP 2016-18 MEMBERSHIP DRIVE RANKINGS</title>
<link href="newCoreDashBoard/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="http://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
<link href="newCoreDashBoard/Plugins/Date/daterangepicker.css" rel="stylesheet" type="text/css"/>
<link href="dist/DatatableBootstrap/DatatableB.css" rel="stylesheet" type="text/css"/>
<link href="dist/Plugins/Chosen/chosen.css" rel="stylesheet" type="text/css"/>
<link href="dist/2016DashBoard/Plugins/Scroller/jquery.mCustomScrollbar.css" rel="stylesheet" type="text/css"/>
<link href="newCoreDashBoard/Plugins/Slick/slick.css" type="text/css" rel="stylesheet"/>
<link href="newCoreDashBoard/Plugins/Slick/slick-theme.css" type="text/css" rel="stylesheet"/>




<style type="text/css">
body
{
	background-image:url("dist/mediaScreenParty/background.png");
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
}
h1,h2,h3,h4,h5,h6
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
	<div class="row " id="PreviousmembersCount">
		<div class="col-md-12 col-xs-12 col-sm-12 show-grid well well-small border-radius-0 mb-10" style="padding: 10px 0px 0px;">
			<div class="table-responsive">
				<table class="table table-bordered border-radius-0 mb-0 Previousmembercount table-hover" style="border-top-width: 0px;">
					<thead>
						<th></th>
						<th style="color:#FE6663"> 2014-16 </th>
						<th style="color:#3AA020"> 2016-18 </th>
						<th style="color:#46A2D0"> Today </th>
						<th style="color:#FF9934">Total Compared with 2014-16</th>
					</thead>
					<tbody>
						<tr>
							<td style="padding-top: 2px; padding-bottom: 2px;">
								<h4 style="display:inline-block;padding-left: 10px;">AP</h4>
								<img style="display:inline-block;width:45px;" class="pull-right" src="images/AP.png">
							</td>
							<td><div id="ap2014CountId"><h4>4716975</h4></div></td>
							<td><div id="ap2016CountId"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></td>
							<td><div id="apTodayCountId"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></td>
							<td><div id="apPercentageId"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></td>
						</tr>
						<tr>
							<td style="padding-top: 2px; padding-bottom: 2px;">
								<h4 style="display:inline-block;padding-left: 10px;">TS</h4>
								<img style="display:inline-block;width:45px;" class="pull-right" src="images/TS.png">
							</td>
							<td><div id="ts2014CountId"><h4>794427</h4></div></td>
							<td><div id="ts2016CountId"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></td>
							<td><div id="tsTodayCountId"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></td>
							<td><div id="tsPercentageId"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></td>
						</tr>	
						<tr>
							<td>
								<h4 style="padding-left: 10px;">TOTAL  <small><b>AP & TS</b></small></h4>
							</td>
							<td><div id="total2014CountId"><h4>5511402</h4></div></td>
							<td><div id="total2016CountId"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></td>
							<td><div id="totalTodayCountId"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></td>
							<td><div id="totalPercentageId"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<div id="todayBlockDivId" style="display:none;">
		<div class="row">
			<div class="col-md-3 col-xs-12 col-sm-3 show-grid well well-small border-radius-0 mb-10" style="margin-bottom: 2px; border-radius: 0px;">
				<h4 style="display:inline-block;"><i class="fa fa-bullhorn" aria-hidden="true"></i> <span class="text-uppercase">Today Top 20 </span></h4>
				<h5 style="display:inline-block;letter-spacing:-1px;color:#3798DC;" Class="text-uppercase"><b>Constituencies in AP</b></h5>
			</div>
			<div class="col-md-9 col-xs-12 col-sm-9 show-grid well well-small border-radius-0 mb-10" style="margin-bottom: 0px; border-radius: 0px;" id="todayAPConstituencies"></div>
		</div>
		<div class="row">
		  <div class="col-md-3 col-xs-12 col-sm-3 show-grid well well-small border-radius-0">
				<h4 style="display:inline-block;"><i class="fa fa-bullhorn" aria-hidden="true"></i> <span class="text-uppercase">Today Top 20 </span></h4>
				<h5 style="display:inline-block;letter-spacing:-1px;color:#3798DC;" Class="text-uppercase"><b>Constituencies in TS</b></h5>
		  </div>
		  <div class="col-md-9 col-xs-12 col-sm-9 show-grid well well-small border-radius-0 mb-10" style="margin-bottom: 0px; border-radius: 0px;" id="todayTSConstituencies"></div>
		</div>

		<div class="row">
			<div class="col-md-3 col-xs-12 col-sm-3 show-grid well well-small border-radius-0 mb-10" style="margin-bottom: 2px; border-radius: 0px;">
				<h4 style="display:inline-block;"><i class="fa fa-bullhorn" aria-hidden="true"></i> <span class="text-uppercase">Today Top 5 </span></h4>
				<h5 style="display:inline-block;color:#3798DC;" Class="text-uppercase"><b>Districts in AP</b></h5>
			</div>
			<div class="col-md-9 col-xs-12 col-sm-9 show-grid well well-small border-radius-0 mb-10" style="margin-bottom: 0px; border-radius: 0px;" id="todayAPDistricts"></div>
		</div>

		<div class="row">
		  <div class="col-md-3 col-xs-12 col-sm-3 show-grid well well-small border-radius-0">
			<h4 style="display:inline-block;"><i class="fa fa-bullhorn" aria-hidden="true"></i> <span class="text-uppercase">Today Top 5 </span></h4>
			<h5 style="display:inline-block;color:#3798DC;" Class="text-uppercase"><b>Districts in TS<b></h5>
		  </div>
		  <div class="col-md-9 col-xs-12 col-sm-9 show-grid well well-small border-radius-0 mb-10" style="margin-bottom: 0px; border-radius: 0px;" id="todayTSDistricts"></div>
		</div>
	</div>
	<div id="totalBlockDivId">
		<div class="row">
			<div class="col-md-3 col-xs-12 col-sm-3 show-grid well well-small border-radius-0 mb-10" style="margin-bottom: 2px; border-radius: 0px;">
				<h4 style="display:inline-block;"><i class="fa fa-bullhorn" aria-hidden="true"></i> <span class="text-uppercase">OverAll Top 20 </span></h4>
				<h5 style="display:inline-block;letter-spacing:-1px;color:#980033;" Class="text-uppercase"><b>Constituencies in AP</b></h5>
			</div>
			<div class="col-md-9 col-xs-12 col-sm-9 show-grid well well-small border-radius-0 mb-10" style="margin-bottom: 0px; border-radius: 0px;" id="overAllAPConstituencies"></div>
		</div>
		<div class="row">
		  <div class="col-md-3 col-xs-12 col-sm-3 show-grid well well-small border-radius-0">
				<h4 style="display:inline-block;"><i class="fa fa-bullhorn" aria-hidden="true"></i> <span class="text-uppercase">OverAll Top 20 </span></h4>
				<h5 style="display:inline-block;letter-spacing:-1px;color:#980033;" Class="text-uppercase"><b>Constituencies in TS</b></h5>
		  </div>
		  <div class="col-md-9 col-xs-12 col-sm-9 show-grid well well-small border-radius-0 mb-10" style="margin-bottom: 0px; border-radius: 0px;" id="overAllTSConstituencies"></div>
		</div>

		<div class="row">
			<div class="col-md-3 col-xs-12 col-sm-3 show-grid well well-small border-radius-0 mb-10" style="margin-bottom: 2px; border-radius: 0px;">
				<h4 style="display:inline-block;"><i class="fa fa-bullhorn" aria-hidden="true"></i> <span class="text-uppercase">OverAll Top 5 </span></h4>
				<h5 style="display:inline-block;color:#980033;" Class="text-uppercase"><b>Districts in AP</b></h5>
			</div>
			<div class="col-md-9 col-xs-12 col-sm-9 show-grid well well-small border-radius-0 mb-10" style="margin-bottom: 0px; border-radius: 0px;padding-bottom: 19px;" id="overAllAPDistricts"></div>
		</div>

		<div class="row">
		  <div class="col-md-3 col-xs-12 col-sm-3 show-grid well well-small border-radius-0 mb-10" style="margin-bottom: 2px; border-radius: 0px;">
			<h4 style="display:inline-block;"><i class="fa fa-bullhorn" aria-hidden="true"></i> <span class="text-uppercase">OverAll Top 5 </span></h4>
			<h5 style="display:inline-block;color:#980033;" Class="text-uppercase"><b>Districts in TS</b></h5>
		  </div>
		  <div class="col-md-9 col-xs-12 col-sm-9 show-grid well well-small border-radius-0 mb-10" style="margin-bottom: 0px; border-radius: 0px;padding-bottom: 15px;" id="overAllTSDistricts"></div>
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
<script src="newCoreDashBoard/Plugins/Slick/slick.js" type="text/javascript"></script>
<script type="text/javascript">

/*$('.autoplay').slick({
   slidesToShow: 1,
  slidesToScroll: 1,
  autoplay: true,
  autoplaySpeed: 500,
   variableWidth: true
});
$(".slick-prev").css("display","none");
$(".slick-next").css("display","none");
$(".slick-list").css("padding-bottom","6px");
$(".slick-list").css("padding-top","8px");*/

get2016LocationWiseRegisteredCounts("total");
get2016LocationWiseRegisteredCounts("today");
function get2016LocationWiseRegisteredCounts(type){
	if(type == "total"){
		$("#ap2016CountId").show();
		$("#apPercentageId").show();
		$("#ts2016CountId").show();
		$("#tsPercentageId").show();
		$("#total2016CountId").show();
		$("#totalPercentageId").show();
	}
	else{
		$("#apTodayCountId").show();
		$("#tsTodayCountId").show();
		$("#totalTodayCountId").show();
	}
	if(type == 'total'){
		$("#ap2016CountId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');	
		$("#apPercentageId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');	
		$("#ts2016CountId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');	
		$("#tsPercentageId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');	
		$("#total2016CountId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');	
		$("#totalPercentageId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');	
	}
	else{
		$("#apTodayCountId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');	
		$("#tsTodayCountId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');	
		$("#totalTodayCountId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');	
	}
	var jObj = {
		type:type,
		locationScopeId:2,
		locationType:""
	}
	$.ajax({
	  type:'GET',
	  url: 'get2016LocationWiseRegisteredCountsAction.action',
	  data : {task:JSON.stringify(jObj)} ,
	}).done(function(result){
		if(result !=null && result.length >0){
			if(type == 'total'){
				var totalRegCount=0;
				var totalPergeCount=0;
				var str='';
				var str1='';
				str+='<h4>'+result[0].count2016+'</h4>';
				str1+='<h4>'+result[1].count2016+'</h4>';
					
				$("#ap2016CountId").html(str)
				$("#apPercentageId").html('<h4>'+result[0].percentage+' %</h4>');
				$("#ts2016CountId").html(str1)
				$("#tsPercentageId").html('<h4>'+result[1].percentage+' %</h4>');
				totalRegCount=result[0].count2016+result[1].count2016;
				$("#total2016CountId").html('<h4>'+totalRegCount+'<h4>');
				$('#totalPercentageId').html('<h4>'+((totalRegCount * 100) / 5511402).toFixed(2)+' %</h4>');
			}
			else{
				var totalRegCount=0;
				var str='';
				var str1='';
				str+='<h4>'+result[0].count2016+'</h4>';
				str1+='<h4>'+result[1].count2016+'</h4>';
				
				$("#apTodayCountId").html(str)
				$("#tsTodayCountId").html(str1)
				totalRegCount=result[0].count2016+result[1].count2016;
				$("#totalTodayCountId").html('<h4>'+totalRegCount+'<h4>');
			}
		}
	});
}
	
function getTodayConstituencyWiseForAP(type,stateId){
	//$("#todayapImgId").show();
	var jObj = {
		type:type,
		stateId:stateId
	}
	$.ajax({
	  type:'GET',
	  url: 'getConstituencyWiseTodayAndOverAllCountsNewAction.action',
	  data : {task:JSON.stringify(jObj)} ,
	}).done(function(result){
		if(result != null && result.length > 0){
			buildingTodayAPConstituencies(result);
		}
	});
}

function buildingTodayAPConstituencies(result){
	var str = '';
	str+='<ul  class="list-inline autoplay" style="margin-bottom: 0px;">';
	for(var i in result){
		var temp = parseInt(i)+1;
		temp = temp <=9 ? "0"+temp:temp;
		str+='<li style="padding-right: 20px;">';
			str+='<span style="padding: 5px; border-radius: 50%; background: rgb(54, 151, 219) none repeat scroll 0% 0%; color: rgb(255, 255, 255);" ><b>'+temp+'</b></span>';
		str+='<span style="font-size: 16px; font-weight: bold; text-transform: uppercase;">&nbsp&nbsp'+result[i].name+'</span>';
		str+='</li>';
	}
	str+='</ul>';
	$("#todayAPConstituencies").html(str);
	$('.autoplay').slick({
		infinite : false,
		slidesToShow: 1,
		slidesToScroll: 1,
		autoplay: true,
		autoplaySpeed: 2200,
		variableWidth: true
	});
	$(".slick-prev").css("display","none");
	$(".slick-next").css("display","none");
	$(".slick-list").css("padding-bottom","6px");
	$(".slick-list").css("padding-top","6px");
}

function getTodayConstituencyWiseForTS(type,stateId){
	//$("#todaytsImgId").show();
	var jObj = {
		type:type,
		stateId:stateId
	}
	$.ajax({
	  type:'GET',
	  url: 'getConstituencyWiseTodayAndOverAllCountsNewAction.action',
	  data : {task:JSON.stringify(jObj)} ,
	}).done(function(result){
		if(result != null && result.length > 0){
			buildingTodayTSConstituencies(result);
		}
		$("#todaytsImgId").hide();
	});
}

function buildingTodayTSConstituencies(result){
	var str = '';
	str+='<ul  class="list-inline autoplayTS" style="margin-bottom: 0px;">';
	for(var i in result){
		var temp = parseInt(i)+1;
		temp = temp <=9 ? "0"+temp:temp;
		str+='<li style="padding-right: 20px;">';
			str+='<span style="padding: 5px; border-radius: 50%; background: rgb(54, 151, 219) none repeat scroll 0% 0%; color: rgb(255, 255, 255);" ><b>'+temp+'</b></span>';
		str+='<span style="font-size: 16px; font-weight: bold; text-transform: uppercase;">&nbsp&nbsp'+result[i].name+'</span>';
		str+='</li>';
	}
	str+='</ul>';
	$("#todayTSConstituencies").html(str);
	$('.autoplayTS').slick({
		infinite : false,
		slidesToShow: 1,
		slidesToScroll: 1,
		autoplay: true,
		autoplaySpeed: 2200,
		variableWidth: true
	});
	$(".slick-prev").css("display","none");
	$(".slick-next").css("display","none");
	$(".slick-list").css("padding-bottom","6px");
	$(".slick-list").css("padding-top","6px");
}

function getTodayDistrictForAP(type,stateId){
	//$("#todaytsImgId").show();
	var jObj = {
		type:type,
		stateId:stateId
	}
	$.ajax({
	  type:'GET',
	  url: 'getDistrictWiseTodayAndOverAllCountsNewAction.action',
	  data : {task:JSON.stringify(jObj)} ,
	}).done(function(result){
		if(result != null && result.length > 0){
			buildingTodayAPDistricts(result);
		}
		//$("#todaytsImgId").hide();
	});
}

function buildingTodayAPDistricts(result){
	var str = '';
	str+='<ul  class="list-inline todayAPDstrts" style="margin-bottom: 0px;">';
	for(var i in result){
		var temp = parseInt(i)+1;
		temp = temp <=9 ? "0"+temp:temp;
		str+='<li style="padding-right: 20px;">';
			str+='<span style="padding: 5px; border-radius: 50%; background: rgb(54, 151, 219) none repeat scroll 0% 0%; color: rgb(255, 255, 255);" ><b>'+temp+'</b></span>';
		str+='<span style="font-size: 16px; font-weight: bold; text-transform: uppercase;">&nbsp&nbsp'+result[i].name+'</span>';
		str+='</li>';
	}
	str+='</ul>';
	$("#todayAPDistricts").html(str);
	$('.todayAPDstrts').slick({
		infinite : false,
		slidesToShow: 1,
		slidesToScroll: 1,
		autoplay: false,
		autoplaySpeed: 2200,
		variableWidth: true
	});
	$(".slick-prev").css("display","none");
	$(".slick-next").css("display","none");
	$(".slick-list").css("padding-bottom","5px");
	$(".slick-list").css("padding-top","5px");
}

function getTodayDistrictForTS(type,stateId){
	//$("#todaytsImgId").show();
	var jObj = {
		type:type,
		stateId:stateId
	}
	$.ajax({
	  type:'GET',
	  url: 'getDistrictWiseTodayAndOverAllCountsNewAction.action',
	  data : {task:JSON.stringify(jObj)} ,
	}).done(function(result){
		if(result != null && result.length > 0){
			buildingTodayTSDistricts(result);
		}
		//$("#todaytsImgId").hide();
	});
}

function buildingTodayTSDistricts(result){
	var str = '';
	str+='<ul  class="list-inline tdyTSDistrcts" style="margin-bottom: 0px;">';
	for(var i in result){
		var temp = parseInt(i)+1;
		temp = temp <=9 ? "0"+temp:temp;
		str+='<li style="padding-right: 20px;">';
			str+='<span style="padding: 5px; border-radius: 50%; background: rgb(54, 151, 219) none repeat scroll 0% 0%; color: rgb(255, 255, 255);" ><b>'+temp+'</b></span>';
		str+='<span style="font-size: 16px; font-weight: bold; text-transform: uppercase;">&nbsp&nbsp'+result[i].name+'</span>';
		str+='</li>';
	}
	str+='</ul>';
	$("#todayTSDistricts").html(str);
	$('.tdyTSDistrcts').slick({
		infinite : false,
		slidesToShow: 1,
		slidesToScroll: 1,
		autoplay: false,
		autoplaySpeed: 2200,
		variableWidth: true
	});
	$(".slick-prev").css("display","none");
	$(".slick-next").css("display","none");
	$(".slick-list").css("padding-bottom","5px");
	$(".slick-list").css("padding-top","5px");
}
getOverAllConstituencyWiseForAP("Total",1);
getOverAllConstituencyWiseForTS("Total",36);
getOverAllDistrictForAP("Total",1);
getOverAllDistrictForTS("Total",36);
//OVERALL
setTimeout(function(){
	getTodayConstituencyWiseForAP("Today",1);
	getTodayConstituencyWiseForTS("Today",36);
	getTodayDistrictForAP("Today",1);
	getTodayDistrictForTS("Today",36);
	$("#todayBlockDivId").show();
	$("#totalBlockDivId").hide();
}, 50000);
setTimeout(function(){
	location.reload();
}, 100000);

function getOverAllConstituencyWiseForAP(type,stateId){
	//$("#todayapImgId").show();
	var jObj = {
		type:type,
		stateId:stateId
	}
	$.ajax({
	  type:'GET',
	  url: 'getConstituencyWiseTodayAndOverAllCountsNewAction.action',
	  data : {task:JSON.stringify(jObj)} ,
	}).done(function(result){
		if(result != null && result.length > 0){
			buildingOverAllAPConstituencies(result);
		}
	});
}

function buildingOverAllAPConstituencies(result){
	var str = '';
	str+='<ul  class="list-inline autoplayTotal" style="margin-bottom: 0px;">';
	for(var i in result){
		var temp = parseInt(i)+1;
		temp = temp <=9 ? "0"+temp:temp;
		str+='<li style="padding-right: 20px;">';
			str+='<span style="padding: 5px; border-radius: 50%; background: rgb(155, 1, 55) none repeat scroll 0% 0%; color: rgb(255, 255, 255);" ><b>'+temp+'</b></span>';
		str+='<span style="font-size: 16px; font-weight: bold; text-transform: uppercase;">&nbsp&nbsp'+result[i].name+'</span>';
		str+='</li>';
	}
	str+='</ul>';
	$("#overAllAPConstituencies").html(str);
	$('.autoplayTotal').slick({
		infinite : false,
		slidesToShow: 1,
		slidesToScroll: 1,
		autoplay: true,
		autoplaySpeed: 2200,
		variableWidth: true
	});
	$(".slick-prev").css("display","none");
	$(".slick-next").css("display","none");
	$(".slick-list").css("padding-bottom","6px");
	$(".slick-list").css("padding-top","6px");
}

function getOverAllConstituencyWiseForTS(type,stateId){
	//$("#todaytsImgId").show();
	var jObj = {
		type:type,
		stateId:stateId
	}
	$.ajax({
	  type:'GET',
	  url: 'getConstituencyWiseTodayAndOverAllCountsNewAction.action',
	  data : {task:JSON.stringify(jObj)} ,
	}).done(function(result){
		if(result != null && result.length > 0){
			buildingOverAllTSConstituencies(result);
		}
		$("#todaytsImgId").hide();
	});
}

function buildingOverAllTSConstituencies(result){
	var str = '';
	str+='<ul  class="list-inline autoplayTotalTS" style="margin-bottom: 0px;">';
	for(var i in result){
		var temp = parseInt(i)+1;
		temp = temp <=9 ? "0"+temp:temp;
		str+='<li style="padding-right: 20px;">';
			str+='<span style="padding: 5px; border-radius: 50%; background: rgb(155, 1, 55) none repeat scroll 0% 0%; color: rgb(255, 255, 255);" ><b>'+temp+'</b></span>';
		str+='<span style="font-size: 16px; font-weight: bold; text-transform: uppercase;">&nbsp&nbsp'+result[i].name+'</span>';
		str+='</li>';
	}
	str+='</ul>';
	$("#overAllTSConstituencies").html(str);
	$('.autoplayTotalTS').slick({
		infinite : false,
		slidesToShow: 1,
		slidesToScroll: 1,
		autoplay: true,
		autoplaySpeed: 2200,
		variableWidth: true
	});
	$(".slick-prev").css("display","none");
	$(".slick-next").css("display","none");
	$(".slick-list").css("padding-bottom","6px");
	$(".slick-list").css("padding-top","6px");
}

function getOverAllDistrictForAP(type,stateId){
	//$("#todaytsImgId").show();
	var jObj = {
		type:type,
		stateId:stateId
	}
	$.ajax({
	  type:'GET',
	  url: 'getDistrictWiseTodayAndOverAllCountsNewAction.action',
	  data : {task:JSON.stringify(jObj)} ,
	}).done(function(result){
		if(result != null && result.length > 0){
			buildingOverAllAPDistricts(result);
		}
		//$("#todaytsImgId").hide();
	});
}

function buildingOverAllAPDistricts(result){
	var str = '';
	str+='<ul  class="list-inline allAPDistricts" style="margin-bottom: 0px;">';
	for(var i in result){
		var temp = parseInt(i)+1;
		temp = temp <=9 ? "0"+temp:temp;
		str+='<li style="padding-right: 20px;">';
			str+='<span style="padding: 5px; border-radius: 50%; background: rgb(155, 1, 55) none repeat scroll 0% 0%; color: rgb(255, 255, 255);" ><b>'+temp+'</b></span>';
		str+='<span style="font-size: 16px; font-weight: bold; text-transform: uppercase;">&nbsp&nbsp'+result[i].name+'</span>';
		str+='</li>';
	}
	str+='</ul>';
	$("#overAllAPDistricts").html(str);
	$('.allAPDistricts').slick({
		infinite : false,
		slidesToShow: 1,
		slidesToScroll: 1,
		autoplay: false,
		autoplaySpeed: 2200,
		variableWidth: true
	});
	$(".slick-prev").css("display","none");
	$(".slick-next").css("display","none");
	$(".slick-list").css("padding-bottom","5px");
	$(".slick-list").css("padding-top","5px");
}

function getOverAllDistrictForTS(type,stateId){
	//$("#todaytsImgId").show();
	var jObj = {
		type:type,
		stateId:stateId
	}
	$.ajax({
	  type:'GET',
	  url: 'getDistrictWiseTodayAndOverAllCountsNewAction.action',
	  data : {task:JSON.stringify(jObj)} ,
	}).done(function(result){
		if(result != null && result.length > 0){
			buildingOverAllTSDistricts(result);
		}
		//$("#todaytsImgId").hide();
	});
}

function buildingOverAllTSDistricts(result){
	var str = '';
	str+='<ul  class="list-inline overTSDistricts" style="margin-bottom: 0px;">';
	for(var i in result){
		var temp = parseInt(i)+1;
		temp = temp <=9 ? "0"+temp:temp;
		str+='<li style="padding-right: 20px;">';
			str+='<span style="padding: 5px; border-radius: 50%; background: rgb(155, 1, 55) none repeat scroll 0% 0%; color: rgb(255, 255, 255);" ><b>'+temp+'</b></span>';
		str+='<span style="font-size: 16px; font-weight: bold; text-transform: uppercase;">&nbsp&nbsp'+result[i].name+'</span>';
		str+='</li>';
	}
	str+='</ul>';
	$("#overAllTSDistricts").html(str);
	$('.overTSDistricts').slick({
		infinite : false,
		slidesToShow: 1,
		slidesToScroll: 1,
		autoplay: false,
		autoplaySpeed: 2200,
		variableWidth: true
	});
	$(".slick-prev").css("display","none");
	$(".slick-next").css("display","none");
	$(".slick-list").css("padding-bottom","5px");
	$(".slick-list").css("padding-top","5px");
}

</script>		
</body>
</html>