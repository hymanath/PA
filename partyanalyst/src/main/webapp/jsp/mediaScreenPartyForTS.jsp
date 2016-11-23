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
<link href="dist/mediaScreenParty/NewsWidget/newsWidget.css" rel="stylesheet" type="text/css"/>
<link href="dist/mediaScreenParty/NewsWidget/newsWidget_mini.css" rel="stylesheet" type="text/css"/>
<link href="newCoreDashBoard/Plugins/Slick/slick.css" type="text/css" rel="stylesheet"/>
<link href="newCoreDashBoard/Plugins/Slick/slick-theme.css" type="text/css" rel="stylesheet"/>
<style type="text/css">
body
{
	background-image:url("dist/mediaScreenParty/background.png");
	background-repeat:no-repeat;
	background-size:cover;
}
.slick-prev::before, .slick-next::before
{
	padding:5px 5px !important;
	background-color:rgba(255,255,255,0.8);
}
blockquote h2 span
{
	font-size:38px !important;
}
blockquote h2
{
	text-transform:uppercase !important;
	font-size:18px !important;
	vertical-align:middle;
}
.newsWidgetCls1 , .newsWidgetCls2
{
	padding-left:0px
}
.newsWidgetCls
{
	padding-left:0px !important
}
.slickSlide
{
	padding:30px 20px;
	color:#fff;
	background-color:#585335;
	border:1px solid #eecf36;

}
.registrationsCount
{
	display:none;
	color:#EECF36;
	font-size:14px;
	width:80px;
	text-align:center;
}
.distPriority , .distName
{
	display:inline-block;
}
.slickCount
{
	font-size:22px;
	margin-right:5px;
}
.slickSlide.slick-active .distPriority .slickCount , .slickSlide.slick-current .distPriority .slickCount
{
	font-size:38px;
}
.slick-track
{
	padding:20px;
}
.slickSlide.slick-active .registrationsCount, .slickSlide.slick-current .registrationsCount ,.slickSlide.slick-active .distPriority, .slickSlide.slick-current .distPriority,.slickSlide.slick-active .distName, .slickSlide.slick-current .distName
{
	display:block
}
.slickSlide.slick-active , .slickSlide.slick-current 
{
	background-color:#ed1b24 ;
	padding:20px 20px;
	margin-top:-15px;
	width:250px;
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
.btnNewCustom.btnActive
{
	background-color:#fff;
}
.m_top20
{
	margin-top:20px;
}
.btnNewCustom
{
	background-color:#CCCCCC
}
.arrowDots
{
	padding-left:0px;
}
.arrowDots li
{
	width:10px;
	height:10px;
	background-color:#91833C;
	cursor:pointer;
	display:inline-block;
	list-style:none;
	margin:0px 5px;
}
.arrowDots li.active
{
	background-color:#ED1B24;
}

</style>
</head>
<body>
<div  class="container" >
	<div class="row">
		<div class="col-md-12 col-xs-12 col-sm-12">
			<ul class="arrowDots pull-left">
				<li attr_val="1" class="active arrowDotsCls"></li>
				<li attr_val="2" class="arrowDotsCls"></li>
			</ul>
			<div class="btn-group pull-right">
			  <button type="button" attr_val="1" class="btn btnClassChange btnNewCustom btnActive radioCls">REGISTRATIONS</button>
			  <button type="button" attr_val="2" class="btn btnClassChange btnNewCustom radioCls">TARGET ACHIEVED %</button>
			</div>
		</div>
	</div>
	<div id="districtDiv">
		<div class="row">
			<div class="col-md-12 col-xs-12 col-sm-12">
				
				<div class="row m_top20">
					<div class="col-md-6 col-xs-12 col-sm-6">
						<img src="dist/mediaScreenParty/TS_Districts.png" class="img-responsive" style="margin:auto"/>
					</div>
					<div class="col-md-6 col-xs-12 col-sm-6">
						<span style="color:#ED1B24"><b>TODAY TOP<i class="glyphicon glyphicon-arrow-up"></i></b></span>
						<div class="emptyDiv"></div>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12 col-xs-12 col-sm-6">
				<span style="color:#ED1B24"><b>TILL NOW TOP<i class="glyphicon glyphicon-arrow-up"></i></b></span>
				<div class="emptyDiv1"></div>
			</div>
		</div>
	</div>	

	<div   id="constituencyDiv" style="display:none;">
		<div class="row">
		<div class="col-md-12 col-xs-12 col-sm-6">
			<div class="col-md-6 col-xs-12 col-sm-6">
				<img src="dist/mediaScreenParty/TS_Constituencies.png" class="img-responsive" style="margin:auto"/>
			</div>
			<div class="col-md-6 col-xs-12 col-sm-6">
				<span style="color:#ED1B24"><b>TODAY TOP<i class="glyphicon glyphicon-arrow-up"></i></b></span>
				<div class="emptyDiv2"></div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12 col-xs-12 col-sm-6">
				<span style="color:#ED1B24"><b>TILL NOW TOP<i class="glyphicon glyphicon-arrow-up"></i></b></span>
				<div class="emptyDiv3"></div>
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
<script src="dist/mediaScreenParty/NewsWidget/TweenMax.min.js" type="text/javascript"></script>
<script src="dist/mediaScreenParty/NewsWidget/jquery.newsWidget.min.js" type="text/javascript"></script>
<script src="https://cdn.datatables.net/v/bs/dt-1.10.12/datatables.min.js" type="text/javascript"></script>	
<script src="newCoreDashBoard/Plugins/Slick/slick.js" type="text/javascript"></script>	
<script type="text/javascript"> 
$(document).on("click",".btnClassChange",function(){
	$(".btnNewCustom").removeClass("btnActive")
	$(this).addClass("btnActive")
});

$("#menu").parent().remove();

//getConstituencyWiseDisrictList2("Today",36,"count");
//getConstituencyWiseDisrictList4("Total",36,"count");
getTodayDistrictList("Today",36,"count");
getOverAllDistrictList("Total",36,"count");


$(document).on("click",".arrowDotsCls",function(){
	var value = $(".arrowDots li.active").attr('attr_val');
	var valPerc = $(".btnActive").attr("attr_val");
	if(value == 1){
		$("#constituencyDiv").show();
		$("#districtDiv").hide();
		$(".arrowDots li:nth-child(1)").toggleClass("active")
		$(".arrowDots li:nth-child(2)").toggleClass("active")
		if(valPerc == 1){
			getConstituencyWiseDisrictList2("Today",36,"count");
			getConstituencyWiseDisrictList4("Total",36,"count");
		}
		else if(valPerc == 2){
			getConstituencyWiseDisrictList2("Today",36,"percentage");
			getConstituencyWiseDisrictList4("Total",36,"percentage");
		}
	}
	else if(value == 2){
		if(valPerc == 1){
			getTodayDistrictList("Today",36,"count");
			getOverAllDistrictList("Total",36,"count");
		}
		else if(valPerc == 2){
			getTodayDistrictList("Today",36,"percentage");
			getOverAllDistrictList("Total",36,"percentage");
		}
		
		$("#constituencyDiv").hide();
		$("#districtDiv").show();
		$(".arrowDots li:nth-child(1)").toggleClass("active")
		$(".arrowDots li:nth-child(2)").toggleClass("active")
	}
});
setInterval(function(){
	consShow()
	consHide()
},100);

function consShow()
{
	var cons = setInterval(function(){
		if($(".newsWidgetCls1").find(".slick-track li:nth-child(11)").hasClass("slick-current") == true)
		{
			$(".newsWidgetCls1").find(".slick-track li:nth-child(11)").removeClass("slick-current");
			clearInterval(cons);
			clearInterval(triggerCLick);
			clearInterval(triggerCLickS);
			$(".emptyDiv2").find(".slick-prev").trigger("click");
			$("#constituencyDiv").show();
			$("#districtDiv").hide();
			$(".arrowDots li:nth-child(1)").toggleClass("active")
			$(".arrowDots li:nth-child(2)").toggleClass("active");
			var value = $(".btnActive").attr("attr_val");
			if(value == 1){
				getConstituencyWiseDisrictList2("Today",1,"count");
				getConstituencyWiseDisrictList4("Total",1,"count");
			}
			else if(value == 2){
				getConstituencyWiseDisrictList2("Today",1,"percentage");
				getConstituencyWiseDisrictList4("Total",1,"percentage");
			}
		}
		
	}, 100);

}
function consHide()
{
	var consH = setInterval(function(){ 
		if($(".newsWidgetCls2").find(".slick-track li:nth-child(20)").hasClass("slick-current") == true)
		{
			clearInterval(consH);
			$(".newsWidgetCls2").find(".slick-track li:nth-child(20)").removeClass("slick-current")
			clearInterval(triggerCLick1);
			clearInterval(triggerCLickS1);
			$(".emptyDiv3").find(".slick-prev").trigger("click");
			$("#constituencyDiv").hide();
			$("#districtDiv").show();
			$(".arrowDots li:nth-child(1)").toggleClass("active")
			$(".arrowDots li:nth-child(2)").toggleClass("active")
			var value = $(".btnActive").attr("attr_val");
			if(value == 1){
				getTodayDistrictList("Today",1,"count");
				getOverAllDistrictList("Total",1,"count");
			}
			else if(value == 2){
				getTodayDistrictList("Today",1,"percentage");
				getOverAllDistrictList("Total",1,"percentage");
			}
			
		}
		
	}, 100);

}
/* setTimeout(function(){
	$("#constituencyDiv").show();
	$("#districtDiv").hide();
	$(".arrowDots li:nth-child(1)").toggleClass("active")
	$(".arrowDots li:nth-child(2)").toggleClass("active")
	var value = $(".btnActive").attr("attr_val");
	if(value == 1){
		getConstituencyWiseDisrictList2("Today",36,"count");
		getConstituencyWiseDisrictList4("Total",36,"count");
	}
	else if(value == 1){
		getConstituencyWiseDisrictList2("Today",36,"percentage");
		getConstituencyWiseDisrictList4("Total",36,"percentage");
	}
}, 24000); */

/* setInterval(function(){ 
	$("#constituencyDiv").hide();
	$("#districtDiv").show();
	$(".arrowDots li:nth-child(1)").toggleClass("active")
	$(".arrowDots li:nth-child(2)").toggleClass("active")
	var value = $(".btnActive").attr("attr_val");
	if(value == 1){
		getTodayDistrictList("Today",36,"count");
		getOverAllDistrictList("Total",36,"count");
	}
	else if(value == 2){
		getTodayDistrictList("Today",36,"percentage");
		getOverAllDistrictList("Total",36,"percentage");
	}
}, 50 * 1000); */
/* setInterval(function(){ 
	$("#constituencyDiv").show();
	$("#districtDiv").hide();
	$(".arrowDots li:nth-child(1)").toggleClass("active")
	$(".arrowDots li:nth-child(2)").toggleClass("active")
	var value = $(".btnActive").attr("attr_val");
	if(value == 1){
		getConstituencyWiseDisrictList2("Today",36,"count");
		getConstituencyWiseDisrictList4("Total",36,"count");
	}
	else if(value == 1){
		getConstituencyWiseDisrictList2("Today",36,"percentage");
		getConstituencyWiseDisrictList4("Total",36,"percentage");
	}
}, 75 * 1000); */

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
			buildingConstituencyList2(result,"constituencyListPanel2",type,sortType);
		}
		$("#todaytsImgId").hide();
	});
}

function buildingConstituencyList2(result,divId,type,sortType){
	var value = $(".btnActive").attr("attr_val");
	var str1 = '';
	str1+='<ul id="mainNewsWidget2" class="newsWidgetCls">';
		for(var i in result){
			var temp = parseInt(i)+1;
			temp = (temp < 10 ? '0' : '') + temp
				if(value == 2){
					result[i].attenteeCount = result[i].per2016;
				}
				str1+='<li data-title="<span>'+temp+'</span> '+result[i].name+'" data-date="12-2-2013"  data-image="images/img/2.jpg"  data-link="http://google.com" data-link-type="readmore">';
				str1+='<h3>'+result[i].attenteeCount+'</h3>';
			str1+='</li>';
		}
	str1+='</ul>';
	$(".emptyDiv2").html(str1);
	
	$("#mainNewsWidget2").newsWidget({
		currentNewsWidth: 400,
		currentNewsHeight:100,
		fullArticleType : "widget" ,
		navBtns: "right", 
		closedNewsWidth:300,
		closedNewsPosition:"left", 
		closedNewsOffset:50,
		widgetOpenType: "fade",
		fullArticleOpen: "fadeUp",
		fullArticleClose: "fadeRight",
		linkText:", Read!" , 
		titleInLink : "before"
	});
	if(value == 2){
		$(".textChanged").html("Target Achieved");
	}
	
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
			buildingConstituencyList3(result,"constituencyListPanel4",type,sortType);
		}
		$("#totaltsimgId").hide();
	});
}

var triggerCLick1 = '';
var triggerCLickS1 = '';
function trigger1()
{
  triggerCLick1 = setInterval(function(){  $(".emptyDiv2").find(".newsWidgetCnt .up").trigger("click");  },2300);
  triggerCLickS1 = setInterval(function(){  $(".emptyDiv3").find(".slick-next").trigger("click"); },2300);
}


function buildingConstituencyList3(result,divId,type,sortType){
	var value = $(".btnActive").attr("attr_val");
	clearInterval(triggerCLick1);
	var str1 = '';
	str1+='<ul  class="newsWidgetCls2">';
		for(var i in result){
			var temp = parseInt(i)+1;
			temp = (temp < 10 ? '0' : '') + temp
			if(value == 2){
				result[i].attenteeCount = result[i].per2016;
			}
			str1+='<li class="slickSlide">';
				str1+='<span class="distPriority"><span class="slickCount">'+temp+'</span>';
				if(value == 1)
					str1+='<span  class="pull-right registrationsCount">Registrations <br/><b>'+result[i].attenteeCount+'</b></span></span>';
				else if(value == 2) 
					str1+='<span  class="pull-right registrationsCount">Target Achieved <br/><b>'+result[i].attenteeCount+'</b></span></span>';
				str1+='<span class="distName">'+result[i].name+'</span>';
			str1+='</li>';
		}
	str1+='</ul>';
	$(".emptyDiv3").html(str1);
	$('.newsWidgetCls2').slick({
	  infinite: false,
	   autoplay: false,
	   autoplaySpeed: 1800,
	  slidesToShow: 1,
	  centerMode: true,
	  variableWidth: true
	});
	trigger1();
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
			buildingDistrictList(result,"todayDistrictList",type,sortType);
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
			buildingDistrictListOverAll(result,"overAllDistrictList",type,sortType);
		}
		$("#todaytsImgId").hide();
	});
}

var triggerCLick = '';
var triggerCLickS = '';
function trigger()
{
  triggerCLick = setInterval(function(){  $(".emptyDiv").find(".newsWidgetCnt .up").trigger("click");  },2300);
  triggerCLickS = setInterval(function(){  $(".emptyDiv1").find(".slick-next").trigger("click"); },2300);
}

function buildingDistrictListOverAll(result,divId,type,sortType){
	var value = $(".btnActive").attr("attr_val");
	clearInterval(triggerCLick);
	var str1 = '';
	str1+='<ul  class="newsWidgetCls1">';
		for(var i in result){
			var temp = parseInt(i)+1;
			temp = (temp < 10 ? '0' : '') + temp
			if(value == 2){
				result[i].attenteeCount = result[i].per2016;
			}
			str1+='<li class="slickSlide">';
				str1+='<span class="distPriority"><span class="slickCount">'+temp+'</span>';
				if(value == 1)
					str1+='<span  class="pull-right registrationsCount">Registrations <br/><b>'+result[i].attenteeCount+'</b></span></span>';
				else if(value == 2)
					str1+='<span  class="pull-right registrationsCount">Target Achieved <br/><b>'+result[i].attenteeCount+'</b></span></span>';
				str1+='<span class="distName">'+result[i].name+'</span>';
			str1+='</li>';
		}
	str1+='</ul>';
	$(".emptyDiv1").html(str1);
	$('.newsWidgetCls1').slick({
	  infinite: false,
	   autoplay: false,
	   autoplaySpeed: 1800,
	  slidesToShow: 1,
	  centerMode: true,
	  variableWidth: true
	});
	trigger();
}

function buildingDistrictList(result,divId,type,sortType){
	var value = $(".btnActive").attr("attr_val");
	var str1 = '';
	str1+='<ul id="mainNewsWidget" class="newsWidgetCls">';
		for(var i in result){
			var temp = parseInt(i)+1;
			temp = (temp < 10 ? '0' : '') + temp
			if(value == 2){
				result[i].attenteeCount = result[i].per2016;
			}
			if(type == "Today"){
				str1+='<li data-title="<span>'+temp+'</span> '+result[i].name+'" data-date="12-2-2013"  data-image="images/img/2.jpg"  data-link="http://google.com" data-link-type="readmore">';
			}else{
				str1+='<li data-title="<span>'+temp+'</span> '+result[i].name+'" data-date="12-2-2013"  data-image="images/img/2.jpg"  data-link="http://google.com" data-link-type="readmore">';
			}
				str1+='<h3>'+result[i].attenteeCount+'</h3>';
			str1+='</li>';
		}
	str1+='</ul>';
	$(".emptyDiv").html(str1);
	
	/* var myVar;
	myFunction()
	function myFunction() {
		myVar = setInterval(function(){	var pos = div.scrollTop();div.scrollTop(pos + 2);}, 100)
	} */
	$("#mainNewsWidget").newsWidget({
		currentNewsWidth: 400,
		currentNewsHeight:100,
		fullArticleType : "widget" ,
		navBtns: "right", 
		closedNewsWidth:300,
		closedNewsPosition:"left", 
		closedNewsOffset:50,
		widgetOpenType: "fade",
		fullArticleOpen: "fadeUp",
		fullArticleClose: "fadeRight",
		linkText:", Read!" , 
		titleInLink : "before"
	});
	if(value == 2){
		$(".textChanged").html("Target Achieved");
	}
	
}	

//setTimeout(function(){ location.reload(); }, 43000);

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
	var value = $(".btnActive").attr("attr_val");
	$(".arrowDots li:nth-child(1)").addClass("active");
	$(".arrowDots li:nth-child(2)").removeClass("active");
	$("#constituencyDiv").hide();
	$("#districtDiv").show();
	if(value == 1){
		
		getTodayDistrictList("Today",36,"count");
		getOverAllDistrictList("Total",36,"count");
		//$(".textChanged").html("Registrations");
		
	}
	else if(value == 2){
		
		getTodayDistrictList("Today",36,"percentage");
		getOverAllDistrictList("Total",36,"percentage");
		
		
	}
});

</script>
</body>
</html>