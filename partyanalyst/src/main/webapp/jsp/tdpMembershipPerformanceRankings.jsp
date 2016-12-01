<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0"> 
<title>TDP 2016-18 MEMBERSHIP DRIVE RANKINGS</title>
<link rel="icon" type="image/png" href="https://mytdp.com/dist/img/logo.png">
<link href="newCoreDashBoard/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="http://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
<link href="newCoreDashBoard/Plugins/Date/daterangepicker.css" rel="stylesheet" type="text/css"/>
<link href="dist/DatatableBootstrap/DatatableB.css" rel="stylesheet" type="text/css"/>
<link href="dist/Plugins/Chosen/chosen.css" rel="stylesheet" type="text/css"/>
<link href="dist/2016DashBoard/Plugins/Scroller/jquery.mCustomScrollbar.css" rel="stylesheet" type="text/css"/>
<link href="newCoreDashBoard/Plugins/Slick/slick.css" type="text/css" rel="stylesheet"/>
<link href="dist/mediaScreenParty/responsive.css" type="text/css" rel="stylesheet"/>
<link href="newCoreDashBoard/Plugins/Slick/slick-theme.css" type="text/css" rel="stylesheet"/>
<style class="text/css">
	.levelHolderClass h2
	{
		margin-top:0px
	}
	header.eventsheader 
	{
		background-image:url("dist/img/header_bg_s.jpg");
		//background-color:#fed501;
		//background-size:100% auto;
		//background-position:center bottom;
		background-repeat: repeat-x;
		//background-origin: border-box;
		//background-repeat: no-repeat;
		height: 125px;
	}
	footer
	{
		position:relative;
		left:0;
		right:0;
		bottom:0
	}
	#menu_multilevelpushmenu 
	{	
     
      background-color: transparent;
      box-shadow: 0 0 10px #000;
	  
    }
	.line_heightDiv
	{
		line-height:50px;
	}
</style>
</head>
<body>
    <header class="eventsheader">
	<div class="container">
        <div class="row">
            <div class="col-md-3 col-xs-3 col-sm-3">
                <img src="dist/img/logo_s.png" class="img-responsive" alt="logo">
            </div>   
			<div class="col-md-6 col-xs-6 col-sm-6">
			<div class="row">
					<div class="col-md-12 col-xs-12 col-sm-12 span1 ntrR">
						<center><img src="dist/img/nrt_s.png" style="margin-top: 5px;" class="img-responsive" alt="ntr">  </center>
					</div>
					 <div class="col-md-12 col-xs-12 col-sm-12 span5 text-center headingR">               
						 <p class="header-text display-style" id="mainheading">2016-2018 MEMBERSHIP DRIVE</p><p style="text-align:center;color: rgb(92, 45, 37) ! important; font-size: 14px ! important;" class="header-text display-style"></p>                
					</div>
			</div>
			</div>
			 <div class="col-md-3 col-xs-3 col-sm-3">
                <img src="dist/img/cbn_s.png" class="img-responsive cbnImage" alt="cbn">
            </div>
        </div>       
    </div>
</header>
</head>
<body>
<div class="container" style="margin-bottom:20px;">
	<div class="row m_XSTop10 m_MDTop20" id="PreviousmembersCount">
		<div class="col-md-12 col-xs-12 col-sm-12 show-grid well well-small border-radius-0 mb-10" style="padding:0px 0px;">
			<div class="table-responsive">
				<table class="table table-bordered border-radius-0 mb-0 Previousmembercount table-hover" style="border-top-width: 0px; margin-bottom: 0px;">
					<thead>
						<th style="width:260px !important;"></th>
						<th style="color:#FE6663" class="text-center"> 2014-16 </th>
						<th style="color:#3AA020" class="text-center"> 2016-18 </th>
						<th style="color:#46A2D0" class="text-center"> TODAY </th>
						<th style="color:#FF9934" class="text-center">2014-16 Vs 2016-18</th>
					</thead>
					<tbody>
						<tr>
							<td style="padding-top: 2px; padding-bottom: 2px;">
								<h4 style="padding:5px 0px 0px 10px;display:inline-block;text-transform:uppercase;">Andhra Pradesh</h4>
								<img style="width:45px;display:inline-block" class="pull-right" src="images/AP.png">
							</td>
							<td class="text-center"><div id="ap2014CountId"><h4>4716975</h4></div></td>
							<td class="text-center"><div id="ap2016CountId"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></td>
							<td class="text-center"><div id="apTodayCountId"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></td>
							<td class="text-center"><div id="apPercentageId"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></td>
						</tr>
						<tr>
							<td style="padding-top: 2px; padding-bottom: 2px;">
								<h4 style="padding:5px 0px 0px 10px;display:inline-block;text-transform:uppercase;">telangana</h4>
								<img style="width:45px;display:inline-block" class="pull-right" src="images/TS.png">
							</td>
							<td class="text-center"><div id="ts2014CountId"><h4>794427</h4></div></td>
							<td class="text-center"><div id="ts2016CountId"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></td>
							<td class="text-center"><div id="tsTodayCountId"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></td>
							<td class="text-center"><div id="tsPercentageId"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></td>
						</tr>	
						<tr>
							<td>
								<h4 style="padding-left: 10px;">TOTAL  <small><b>AP & TS</b></small></h4>
							</td>
							<td class="text-center"><div id="total2014CountId"><h4>5511402</h4></div></td>
							<td class="text-center"><div id="total2016CountId"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></td>
							<td class="text-center"><div id="totalTodayCountId"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></td>
							<td class="text-center"><div id="totalPercentageId"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<div id="todayBlockDivId" style="display:none;">
		<div class="row m_XSTop10 m_MDTop20">
			<div class="col-md-3 col-xs-12 col-sm-3 show-grid well well-small border-radius-0 mb-10 b_shadow">
				<h4><i class="fa fa-bullhorn" aria-hidden="true"></i> <span class="text-uppercase">Today Top 20 </span></h4>
				<h5 style="letter-spacing:-1px;color:#3798DC;" Class="text-uppercase"><b>Constituencies in AP</b></h5>
			</div>
			<div class="col-md-9 col-xs-12 col-sm-9 show-grid well well-small border-radius-0 mb-10" style="margin-bottom: 0px; border-radius: 0px;" id="todayAPConstituencies"></div>
		</div>
		<div class="row m_XSTop10 m_MDTop5">
			<div class="col-md-3 col-xs-12 col-sm-3 show-grid well well-small border-radius-0 b_shadow">
				<h4><i class="fa fa-bullhorn" aria-hidden="true"></i> <span class="text-uppercase">Today Top 20 </span></h4>
				<h5 style="letter-spacing:-1px;color:#3798DC;" Class="text-uppercase"><b>Constituencies in TS</b></h5>
			</div>
		  <div class="col-md-9 col-xs-12 col-sm-9 show-grid well well-small border-radius-0 mb-10" style="margin-bottom: 0px; border-radius: 0px;" id="todayTSConstituencies"></div>
		</div>

		<div class="row m_XSTop10 m_MDTop20">
			<div class="col-md-3 col-xs-12 col-sm-3 show-grid well well-small border-radius-0 mb-10 b_shadow">
				<h4><i class="fa fa-bullhorn" aria-hidden="true"></i> <span class="text-uppercase">Today Top 5 </span></h4>
				<h5 style="color:#3798DC;" Class="text-uppercase"><b>Districts in AP</b></h5>
			</div>
			<div class="col-md-9 col-xs-12 col-sm-9 show-grid well well-small border-radius-0 mb-10" style="margin-bottom: 0px; border-radius: 0px;" id="todayAPDistricts"></div>
		</div>

		<div class="row m_XSTop10 m_MDTop5">
		  <div class="col-md-3 col-xs-12 col-sm-3 show-grid well well-small border-radius-0 b_shadow">
			<h4><i class="fa fa-bullhorn" aria-hidden="true"></i> <span class="text-uppercase">Today Top 5 </span></h4>
			<h5 style="color:#3798DC;" Class="text-uppercase"><b>Districts in TS</b></h5>
		  </div>
		  <div class="col-md-9 col-xs-12 col-sm-9 show-grid well well-small border-radius-0 mb-10" style="margin-bottom: 0px; border-radius: 0px;" id="todayTSDistricts"></div>
		</div>
	</div>
	<div id="totalBlockDivId">
		<div class="row m_XSTop10 m_MDTop20">
			<div class="col-md-3 col-xs-12 col-sm-3 show-grid well well-small border-radius-0 mb-10 b_shadow">
				<h4><i class="fa fa-bullhorn" aria-hidden="true"></i> <span class="text-uppercase">OverAll Top 20 </span></h4>
				<h5 style="letter-spacing:-1px;color:#980033;" Class="text-uppercase"><b>Constituencies in AP</b></h5>
			</div>
			<div class="col-md-9 col-xs-12 col-sm-9 show-grid well well-small border-radius-0 mb-10" style="margin-bottom: 0px; border-radius: 0px;" id="overAllAPConstituencies"></div>
		</div>
		<div class="row m_XSTop10 m_MDTop5">
			<div class="col-md-3 col-xs-12 col-sm-3 show-grid well well-small border-radius-0 b_shadow">
				<h4><i class="fa fa-bullhorn" aria-hidden="true"></i> <span class="text-uppercase">OverAll Top 20 </span></h4>
				<h5 style="letter-spacing:-1px;color:#980033;" Class="text-uppercase"><b>Constituencies in TS</b></h5>
			</div>
			<div class="col-md-9 col-xs-12 col-sm-9 show-grid well well-small border-radius-0 mb-10" style="margin-bottom: 0px; border-radius: 0px;" id="overAllTSConstituencies"></div>
		</div>

		<div class="row m_XSTop10 m_MDTop20">
			<div class="col-md-3 col-xs-12 col-sm-3 show-grid well well-small border-radius-0 mb-10 b_shadow">
				<h4 style=""><i class="fa fa-bullhorn" aria-hidden="true"></i> <span class="text-uppercase">OverAll Top 5 </span></h4>
				<h5 style="color:#980033;" Class="text-uppercase"><b>Districts in AP</b></h5>
			</div>
			<div class="col-md-9 col-xs-12 col-sm-9 show-grid well well-small border-radius-0 mb-10" style="margin-bottom: 0px; padding-bottom: 19px;" id="overAllAPDistricts"></div>
		</div>

		<div class="row m_XSTop10 m_MDTop5">
		  <div class="col-md-3 col-xs-12 col-sm-3 show-grid well well-small border-radius-0 mb-10 b_shadow">
			<h4><i class="fa fa-bullhorn" aria-hidden="true"></i> <span class="text-uppercase">OverAll Top 5 </span></h4>
			<h5 style="color:#980033;" Class="text-uppercase"><b>Districts in TS</b></h5>
		  </div>
		  <div class="col-md-9 col-xs-12 col-sm-9 show-grid well well-small border-radius-0 mb-10" style="margin-bottom: 0px; padding-bottom: 19px;" id="overAllTSDistricts"></div>
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
var windowWidth = $(window).width()
headingResponsive();
function headingResponsive()
{
	if(windowWidth < 500)
	{
		var margin = windowWidth * 0.25
		$("#mainheading").css("width",windowWidth);
		$("#mainheading").css("margin-left",'-'+margin+'px');
	}	
}
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
		infinite : true,
		slidesToShow: 3,
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
		infinite : true,
		slidesToShow: 3,
		slidesToScroll: 1,
		autoplay: true,
		autoplaySpeed: 2200,
		variableWidth: true
	});
	$(".slick-prev").css("display","none");
	$(".slick-next").css("display","none");
	$(".slick-list").css("padding-bottom","10px");
	$(".slick-list").css("padding-top","8px");
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
		infinite : true,
		slidesToShow: 4,
		slidesToScroll: 1,
		autoplay: true,
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
		infinite : true,
		slidesToShow: 4,
		slidesToScroll: 1,
		autoplay: true,
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
		infinite : true,
		slidesToShow: 3,
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
		infinite : true,
		slidesToShow: 3,
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
		infinite : true,
		slidesToShow: 4,
		slidesToScroll: 1,
		autoplay: true,
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
		infinite : true,
		slidesToShow: 4,
		slidesToScroll: 1,
		autoplay: true,
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