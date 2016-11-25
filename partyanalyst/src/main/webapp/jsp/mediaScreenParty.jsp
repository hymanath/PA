<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>TDP 2016-2018 MEMBERSHIP DRIVE RANKINGS</title>
<link href="newCoreDashBoard/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="http://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
<link href="newCoreDashBoard/Plugins/Date/daterangepicker.css" rel="stylesheet" type="text/css"/>
<link href="dist/DatatableBootstrap/DatatableB.css" rel="stylesheet" type="text/css"/>
<link href="dist/Plugins/Chosen/chosen.css" rel="stylesheet" type="text/css"/>
<link href="dist/2016DashBoard/Plugins/Scroller/jquery.mCustomScrollbar.css" rel="stylesheet" type="text/css"/>
<link href="dist/mediaScreenParty/custom.css" rel="stylesheet" type="text/css"/>
<link href="dist/mediaScreenParty/NewsWidget/newsWidget.css" rel="stylesheet" type="text/css"/>
<link href="dist/mediaScreenParty/NewsWidget/newsWidget_mini.css" rel="stylesheet" type="text/css"/>
<link href="newCoreDashBoard/Plugins/Slick/slick.css" type="text/css" rel="stylesheet"/>
<link href="newCoreDashBoard/Plugins/Slick/slick-theme.css" type="text/css" rel="stylesheet"/>
</head>
<body>
<div  class="container" >
	<div class="row">
		<div class="col-md-12 col-xs-12 col-sm-12">
			<h3 class="text-center mainHeading">TDP 2016-2018 MEMBERSHIP DRIVE RANKINGS</h3>
		</div>
		<div class="col-md-12 col-xs-12 col-sm-12 m_top10">
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
						<img src="dist/mediaScreenParty/AP_Districts.png" class="img-responsive" style="margin:auto"/>
					</div>
					<div class="col-md-6 col-xs-12 col-sm-6">
						<h4 style="color:#ED1B24"><b>TODAY TOP<i class="glyphicon glyphicon-arrow-up"></i></b></h4>
						<div class="emptyDiv m_top10"></div>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12 col-xs-12 col-sm-6">
				<h4 style="color:#ED1B24"><b>TILL NOW TOP<i class="glyphicon glyphicon-arrow-up"></i></b></h4>
				<div class="emptyDiv1 m_top10"></div>
			</div>
		</div>
	</div>	

	<div   id="constituencyDiv" style="display:none;">
		<div class="row">
		<div class="col-md-12 col-xs-12 col-sm-6">
			<div class="col-md-6 col-xs-12 col-sm-6">
				<img src="dist/mediaScreenParty/AP_Constituencies.png" class="img-responsive" style="margin:auto"/>
			</div>
			<div class="col-md-6 col-xs-12 col-sm-6">
				<h4 style="color:#ED1B24"><b>TODAY TOP<i class="glyphicon glyphicon-arrow-up"></i></b></h4>
				<div class="emptyDiv2"></div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12 col-xs-12 col-sm-6">
				<h4 style="color:#ED1B24"><b>TILL NOW TOP<i class="glyphicon glyphicon-arrow-up"></i></b></h4>
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

//getConstituencyWiseDisrictList1("Today",1,"count");
//getConstituencyWiseDisrictList3("Total",1,"count");
getTodayDistrictList("Today",1,"count");
getOverAllDistrictList("Total",1,"count");

$(document).on("click",".arrowDotsCls",function(){
	clearInterval(triggerCLick1);
	clearInterval(triggerCLick);
	clearInterval(triggerCLickS);
	clearInterval(triggerCLickS1);
	var value = $(".arrowDots li.active").attr('attr_val');
	var valPerc = $(".btnActive").attr("attr_val");
	
	if(value == 1){
		$("#constituencyDiv").show();
		$("#districtDiv").hide();
		$(".arrowDots li:nth-child(1)").toggleClass("active")
		$(".arrowDots li:nth-child(2)").toggleClass("active")
		if(valPerc == 1){
			getConstituencyWiseDisrictList1("Today",1,"count");
			getConstituencyWiseDisrictList3("Total",1,"count");
		}
		else if(valPerc == 2){
			getConstituencyWiseDisrictList1("Today",1,"percentage");
			getConstituencyWiseDisrictList3("Total",1,"percentage");
		}
	}
	else if(value == 2){
		if(valPerc == 1){
			getTodayDistrictList("Today",1,"count");
			getOverAllDistrictList("Total",1,"count");
		}
		else if(valPerc == 2){
			getTodayDistrictList("Today",1,"percentage");
			getOverAllDistrictList("Total",1,"percentage");
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
		if($(".newsWidgetCls1").find(".slick-track li:nth-child(14)").hasClass("slick-current") == true)
		{
			$(".newsWidgetCls1").find(".slick-track li:nth-child(14)").removeClass("slick-current");
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
				getConstituencyWiseDisrictList1("Today",1,"count");
				getConstituencyWiseDisrictList3("Total",1,"count");
			}
			else if(value == 2){
				getConstituencyWiseDisrictList1("Today",1,"percentage");
				getConstituencyWiseDisrictList3("Total",1,"percentage");
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
/* setInterval(function(){ 
	$("#constituencyDiv").show();
	$("#districtDiv").hide();
	$(".arrowDots li:nth-child(1)").toggleClass("active")
	$(".arrowDots li:nth-child(2)").toggleClass("active")
	var value = $(".btnActive").attr("attr_val");
	if(value == 1){
		getConstituencyWiseDisrictList1("Today",1,"count");
		getConstituencyWiseDisrictList3("Total",1,"count");
	}
	else if(value == 2){
		getConstituencyWiseDisrictList1("Today",1,"percentage");
		getConstituencyWiseDisrictList3("Total",1,"percentage");
	}
}, 100 * 1000); */

function getConstituencyWiseDisrictList1(type,stateId,sortType){
	$("#todayapImgId").show();
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
			buildingConstituencyList2(result,"constituencyListPanel1",type,sortType);
		}
		$("#todayapImgId").hide();
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
	if($(window).width() < 500)
	{
		$("#mainNewsWidget2").newsWidget({
			currentNewsWidth: 270,
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
	}else{
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
	}
	
	if(value == 2){
		$(".textChanged").html("Target Achieved");
	}
}

function getConstituencyWiseDisrictList3(type,stateId,sortType){
	$("#totalapimgId").show();
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
			buildingConstituencyList3(result,"constituencyListPanel3",type,sortType);
		}
		$("#totalapimgId").hide();
	});
}

var triggerCLick1 = '';
var triggerCLickS1 = '';
function trigger1()
{
  clearInterval(triggerCLick)
  clearInterval(triggerCLickS)
  triggerCLick1 = setInterval(function(){  $(".emptyDiv2").find(".newsWidgetCnt .up").trigger("click"); },2300);
  
  triggerCLickS1 = setInterval(function(){  $(".emptyDiv3").find(".slick-next").trigger("click"); },2300);
 
}


function buildingConstituencyList3(result,divId,type,sortType){
	var value = $(".btnActive").attr("attr_val");
	clearInterval(triggerCLick1);
	clearInterval(triggerCLickS1);
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
	   autoplaySpeed: 2200,
	  slidesToShow: 1,
	  centerMode: true,
	  variableWidth: true
	});
	trigger1();
}

function buildingConstituencyList(result,divId,type,sortType){
	
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
	if($(window).width() < 500)
	{
		$("#mainNewsWidget").newsWidget({
			currentNewsWidth: 280,
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
	}else{
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
	}
	if(value == 2){
		$(".textChanged").html("Target Achieved");
	}
}	

var triggerCLick = '';
var triggerCLickS = '';
function trigger()
{
  clearInterval(triggerCLick1)
  clearInterval(triggerCLickS1)
  triggerCLick = setInterval(function(){  $(".emptyDiv").find(".newsWidgetCnt .up").trigger("click");  },2300);
 
  triggerCLickS = setInterval(function(){  $(".emptyDiv1").find(".slick-next").trigger("click"); },2300);

}

function buildingDistrictListOverAll(result,divId,type,sortType){
	var value = $(".btnActive").attr("attr_val");
	clearInterval(triggerCLick);
	clearInterval(triggerCLickS);
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
					str1+='<span class="pull-right registrationsCount" >Registrations <br/><b>'+result[i].attenteeCount+'</b></span></span>';
				else if(value == 2)
					str1+='<span class="pull-right registrationsCount" >Target Achieved <br/><b>'+result[i].attenteeCount+'</b></span></span>';
				str1+='<span class="distName">'+result[i].name+'</span>';
			str1+='</li>';
		}
	str1+='</ul>';
	$(".emptyDiv1").html(str1);
	$('.newsWidgetCls1').slick({
	  infinite: false,
	   autoplay: false,
	   autoplaySpeed: 2200,
	  slidesToShow: 1,
	  centerMode: true,
	  variableWidth: true
	});
	trigger();
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
			buildingDistrictListOverAll(result,"overAllDistrictList",type,sortType);
		}
		$("#todaytsImgId").hide();
	});
}

function refreshFunctions(){
	//alert(123);
	var value = $('input[name=radio]:checked').val();
	
	if(value == 1){
		getConstituencyWiseDisrictList1("Today",1,"count");
		getConstituencyWiseDisrictList3("Total",1,"count");
		getTodayDistrictList("Today",1,"count");
		getOverAllDistrictList("Total",1,"count");
		
		$(".percentage").hide();
		$(".counts").show();
	}
	else if(value == 2){
		getConstituencyWiseDisrictList1("Today",1,"percentage");
		getConstituencyWiseDisrictList3("Total",1,"percentage");
		getTodayDistrictList("Today",1,"percentage");
		getOverAllDistrictList("Total",1,"percentage");
		
		$(".percentage").show();
		$(".counts").hide();
	}	
}

$(document).on("click",".radioCls",function(){
	clearInterval(triggerCLick1);
	clearInterval(triggerCLick);
	clearInterval(triggerCLickS);
	clearInterval(triggerCLickS1);
	var value = $(".btnActive").attr("attr_val");
	$(".arrowDots li:nth-child(1)").addClass("active");
	$(".arrowDots li:nth-child(2)").removeClass("active");
	$("#constituencyDiv").hide();
	$("#districtDiv").show();
	if(value == 1){
		getTodayDistrictList("Today",1,"count");
		getOverAllDistrictList("Total",1,"count");
	}
	else if(value == 2){
		getTodayDistrictList("Today",1,"percentage");
		getOverAllDistrictList("Total",1,"percentage");
	}
});

</script>		
</body>
</html>