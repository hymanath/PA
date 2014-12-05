<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>CADRE DASHBOARD</title>
	<!-- Custom Styles-->
	<!-- Bootstrap -->
    <link href="js/media/css2.3.2/bootstrap.min.css" rel="stylesheet">	
	<!-- Custom Styles-->
    <link href="js/media/css2.3.2/style.css" rel="stylesheet">
	<!-- CSS animation -->
    <link href="js/media/css2.3.2/animate.css" rel="stylesheet">	
	<!-- icheck Css-->
	<link href="js/media/icheck/skins/all.css?v=1.0.2" rel="stylesheet">	
	<!-- scrollator -->
	<link href="js/media/scrollator/fm.scrollator.jquery.css" rel="stylesheet">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<!--<script src="easy-ticker-master/test/jquery.min.js"></script>-->
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    
	<script src="js/media/icheck/jquery.js"></script>
	 <script src="js/media/icheck/icheck.js"></script>
	 <script src="js/media/scrollator/fm.scrollator.jquery.js"></script>
	
	<!--easy-ticker-master-->
	 <script src="js/media/easy-ticker-master/test/jquery.easing.min.js"></script>
	 <script src="js/media/easy-ticker-master/jquery.easy-ticker.js"></script>
	
	
<style>
	.show-grid:hover .block-hover-addBtn{display:table-cell; margin-right:-22px; top:-10px;}/*visibility: visible;*/
	.block-hover-addBtn{display:none; position: relative;}/*visibility: hidden;*/
	.border-none{border:none;}
	.text-lowercase{text-transform:lowercase;}
	.text-uppercase{text-transform:uppercase;}
	.text-capitalize{text-transform:capitalize;}
	.text-red{color:#dc504a;}
	.text-green{color:#4dbd74;}
	.text-orange{color:#f9a834;}
	.text-skyblue{color:#46acca;}
	.mb-0{margin-bottom:0px}
	.m-0{margin:0px;}
	.mb-5{margin-bottom:5px}
	.mb-10{margin-bottom:10px}
	.Previousmembercount td{width:20%;}
	.membercount td{width:25%;}
	.membercount td h2, .Previousmembercount td h2{margin:0px;}
	.progress{height:10px;}
	.height-300{height: 300px; overflow: auto;}
	.f-16{font-size: 16px;}
	.header-bg{background:#3598DB url('2014-Header-BG.png') repeat-x; height:179px;}
	.color-white{color:#f9f9f9;}
	
	
	
	.vticker1,.vticker2,.vticker3,.vticker4{border: 1px solid #ccc; width: 440px;;}
	
	.vticker1 li,.vticker2 li,.vticker3 li,.vticker4 li{border-bottom: 1px solid green;	padding: 10px; display:block; width:440px;}
	.et-run{	background: red;}
	.rank{background:#3598DB; width:20px; height:20px; padding:5px 6px; color:#fff; font-weight:bold; font-size:18px; border-radius:50%;}
	.rank-success{background:#BFD662; width:20px; height:20px; padding:5px 6px; color:#fff; font-weight:bold; font-size:18px; border-radius:50%;}
	.h-30{height:30px;}
	.f-10{font-size:10px;}
	#menu{display:none;}
	.mainwrapper {height: 90px;}
	#mainImgAlignId{margin-bottom: -10px;}
	body{background:#F8D53B url('js/media/background_fixed.jpg') !important;}
	</style>
</head>
<body>
<body>
	
		<div class="container-fluid mb-5" style="background: transparent url('js/media/red-thread.png') repeat-x;">	
		<!-- Title Row -->
		<div class="container ">	
		<div class="row-fluid" id="fadeInDown">
			<div class="span12   border-radius-0 mb-5 pad-0">
				<img src="js/media/logo3.gif" class="">	<a class="pull-right" title="LogOut" href="newlogoutAction.action"><i class="icon-off"></i></a>
                 <img class=""src="js/media/ntr_small_image.png" style="margin-left:300px; margin-top:-35px" />
				<img src="js/media/cbn-ntr-img.png" class="pull-right">
			
				<h2 class=" text-uppercase m-0" style="margin-top: -43px; margin-left: 280px; color: rgb(246, 211, 59);">2014 Cadre Enrollment</h2>
			</div>
		</div><!-- Title Row End-->
		</div>
		
	</div><!-- Header Row End-->
		
	<div class="container ">	
		
		
		<!-- Members Registered Previous Row -->
		<div class="row-fluid " id="PreviousmembersCount">
			<div class="span12 show-grid well well-small border-radius-0 mb-10 " >
				    <table class="table table-bordered border-radius-0 mb-0 Previousmembercount table-hover" >
						<tbody>
							<tr>
								<td>
									<h3 class="m-0 h-30">TS</h3>
									<img class="pull-right" src="js/media/TS.png" style="margin-top: -40px;">
								</td>
								<td><h3 class="m-0 h-30">119</h3><p class="f-10">Registration Started Constituencies</p></td>
								<td><h3 class="m-0 h-30">482566</h3><p class="f-10">Members Registered in<span class="text-red"> &nbsp;<b>2012</b></span></p></td>
								<td><div id="ts2014CountId"><img class="imgStyle" src="images/icons/search.gif"/></div></td>
								<td><div id="tsPercCountId"><img class="imgStyle" src="images/icons/search.gif"/></div></td>
								
							</tr>
							<tr>
								<td>
									<h3 class="m-0 h-30">AP</h3>
									<img class="pull-right" src="js/media/AP.png" style="margin-top: -40px;">
								</td>
								<td><h3 class="m-0 h-30">175</h3><p class="f-10">Registration Started Constituencies</p></td>
								<td><h3  class="m-0 h-30">914359</h3><p class="f-10">Members Registered in<span class="text-red">&nbsp;<b>2012</b></span></p></td>
								<td><div id="ap2014CountId"><img class="imgStyle" src="images/icons/search.gif"/></div></td>
								<td><div id="apPercCountId"><img class="imgStyle" src="images/icons/search.gif"/></div></td>

							</tr>
							<tr>
								<td colspan="2">
									<h4 style="margin-left: 130px;margin-top: 6px;" class="m-0 h-30">TOTAL IN <br>AP <small>[and]</small> TS</h4>
									
								</td>
								<!--<td><h3  class="m-0 h-30"></h3><p class="f-10"> </p></td>-->
								<td><h3 class="m-0 h-30">1396925</h3><p class="f-10">Members Registered in<span class="text-red">&nbsp;<b>2012</b></span></p></td>
								<td><div id="tot2014CountId"><img class="imgStyle" src="images/icons/search.gif"/></div></td>
								<td><div id="totPercCountId"><img class="imgStyle" src="images/icons/search.gif"/></div></td>
							</tr>
						</tbody>
					</table>
			</div>
		</div>
		
		<div class="row-fluid">
			<div id="fadeInLeft" class="span6 show-grid well well-small border-radius-0 mb-5 ">
						<h4 class="m-0 text-center"><span style="background:#ffffff; padding:0px 3px;font-size: 16px;">TODAY TOP 20 <img src="js/media/bullhorn-c.gif" /> CONSTITUENCIES IN AP</span></h4>
						<div class="span10" style="border-top: 1px solid rgb(204, 204, 204); height: 0px ! important; margin-top: -27px; margin-left: 27px;">
						</div>
				
				<div id="apTodayN">
					<img style="margin-left:-180px;"  class="imgStyle" src="images/icons/search.gif"/>
				</div>
							
			</div>
			<div id="fadeInRight"class="span6 show-grid well well-small border-radius-0 mb-10 ">
				<h4 class="m-0 text-center"><span style="background:#ffffff; padding:0px 3px;font-size: 16px;">TODAY TOP 20 <img src="js/media/bullhorn-c.gif" /> CONSTITUENCIES IN TS</span></h4>
				<div class="span10 " style="border-top: 1px solid rgb(204, 204, 204); height: 0px ! important; margin-top: -27px; margin-left: 27px;"></div>
				<div id="tsTodayN">
					<img style="margin-left:-180px;"  class="imgStyle" src="images/icons/search.gif"/>
				</div>
			</div>
		</div>
		
		<div class="row-fluid">
			<div id="fadeInLeft" class="span6 show-grid well well-small border-radius-0 mb-10 ">
						<h4 class="m-0 text-center"><span style="background:#ffffff; padding:0px 3px;font-size: 16px;">OVERALL TOP 20 <img src="js/media/bullhorn-c.gif" /> CONSTITUENCIES IN AP</span></h4>
						<div class="span10" style="border-top: 1px solid rgb(204, 204, 204); height: 0px ! important; margin-top: -27px; margin-left: 20px;">
						</div>
				
				<div  id="apAllN">
					<img style="margin-left:-180px;"  class="imgStyle" src="images/icons/search.gif"/>
				</div>
							
			</div>
			<div id="fadeInRight"class="span6 show-grid well well-small border-radius-0 mb-10 ">
				<h4 class="m-0 text-center"><span style="background:#ffffff; padding:0px 3px;font-size: 16px;">OVERALL TOP 20 <img src="js/media/bullhorn-c.gif" /> CONSTITUENCIES IN TS</span></h4>
				<div class="span10 " style="border-top: 1px solid rgb(204, 204, 204); height: 0px ! important; margin-top: -27px; margin-left: 20px;"></div>
				<div id="tsAllN">
					<img style="margin-left:-180px;"  class="imgStyle" src="images/icons/search.gif"/>
				</div>
			</div>
		</div>
	
		<script>
		apTodayC = 0;
        apTotalC=0;
        tsTodayC = 0;
        tsTotalC=0;
		$(document).ready(function(){
		  $('input').iCheck({
			checkboxClass: 'icheckbox_square-blue',
			radioClass: 'iradio_square-blue',
			increaseArea: '20%' // optional
		  });
		});
		//scrollator
		$(document.body).scrollator();
		$('.scrollable_div').scrollator();
		<!-- iCheck -->
		
		<!-- ticker -->
		$('.ticker').ticker();
	 </script>
	 <script>
		$('#fadeInDown').addClass('animated fadeInDown');
		$('#fadeInLeft').addClass('animated fadeInLeft');
		$('#fadeInRight').addClass('animated fadeInRight');
		$('.fadeInUp').addClass('animated fadeInUp');
		$('#fadeInUp1').addClass('animated fadeInUp');
		$('#PreviousmembersCount').addClass('animated fadeInUp');
		$('#membersCount').addClass('animated fadeInX');
	</script>
		
	

<script type="text/javascript">
function callScrollingMethod(rsId){
	var dd = $('#'+rsId).easyTicker({
		direction: 'up',
		easing: 'easeInOutBack',
		speed: 'slow',
		interval: 2000,
		height: 'auto',
		visible: 2,
		mousePause: 0,
		controls: {
			up: '.up',
			down: '.down',
			toggle: '.toggle',
			stopText: 'Stop !!!'
		}
	}).data('easyTicker');
	
	}

</script>	
<script type="text/javascript">



   function getDashBoardBasicInfo(){
            
			$("#ts2014CountId").html('<img class="imgStyle" src="images/icons/search.gif"/>');
			$("#tsPercCountId").html('<img class="imgStyle" src="images/icons/search.gif"/>');
						
			$("#ap2014CountId").html('<img class="imgStyle" src="images/icons/search.gif"/>');
			$("#apPercCountId").html('<img class="imgStyle" src="images/icons/search.gif"/>');	
			
			$("#tot2014CountId").html('<img class="imgStyle" src="images/icons/search.gif"/>');
			$("#totPercCountId").html('<img class="imgStyle" src="images/icons/search.gif"/>');	
				
       $.ajax({
          type:'GET',
          url: 'getTotalRegisterCadreInfo.action',
          data: {task:"basicInfo"}
       }).done(function(result){
    	   if(result == "noAccess" || result.tgCount == undefined){
    		   location.reload(); 
    	   }
		   try{
		     if(result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
			    location.reload();
			 }
		   }catch(e){
		   }
		   $("#ts2014CountId").html('<h3 class="m-0 h-30">'+result.tgCount+'</h3><p class="f-10">Members Registered in<span class="text-green"> &nbsp;<b>2014</b></span></p>');
           $("#tsPercCountId").html('<h3 class="m-0 h-30">'+parseInt((result.tgCount)*100/482566)+'%</h3><p class="f-10">Members In <span class="text-orange"><b>Total</b> </span></p>');	
		   $("#ap2014CountId").html('<h3  class="m-0 h-30">'+result.apCount+'</h3><p class="f-10">Members Registered in<span class="text-green">&nbsp;<b>2014</b></span></p>');
		   $("#apPercCountId").html('<h3  class="m-0 h-30">'+parseInt((result.apCount)*100/914359)+'%</h3><p class="f-10">Members In <span class="text-orange"><b>Total</b> </span></p>');
		   $("#tot2014CountId").html('<h3  class="m-0 h-30">'+(result.tgCount+result.apCount)+'</h3><p class="f-10">Members Registered in<span class="text-green">&nbsp;<b>2014</b></span></p>');
		   $("#totPercCountId").html('<h3  class="m-0 h-30">'+parseInt((result.apCount+result.tgCount)*100/1396925)+'%</h3><p class="f-10">Members In <span class="text-orange"><b>Total Party</b></span></p>');
	   });
   }
   
        function getApToday(){
			
			var jObj = {
				"type":"Constituency","stateId":1,"fromTask":"today","task":"mainLevel"
			}
			$.ajax({
			  type:'GET',
			  url: 'getLocationWiseToDayDetailsAction.action',
			  data : {task:JSON.stringify(jObj)} ,
            }).done(function(result){
			      if(result[0].name == undefined){
					   location.reload(); 
				   }
				   try{
					 if(result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
						location.reload();
					 }
				   }catch(e){
				   }
                  result.sort(SortByPerc);
				  var count = 1;
				  var str ="";
				  str+='<marquee scrollamount="3">';
						
				  for(var i in result){
				  if(count < 10){
				    str+='<span class="rank">0'+count+'</span>&nbsp;<b>'+ result[i].name+'</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
				   }else{
				     str+='<span class="rank">'+count+'</span>&nbsp;<b>'+ result[i].name+'</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
				   }
				    count++;
					if(count == 21){
					   break;
					}
				  }
				  str+='</marquee>';
				  $("#apTodayN").html(str);
				  //callScrollingMethod("apTodayC"+apTodayC);
				  apTodayC++;
			});
		}
	   function getApTotal(){
			
			var jObj = {
				"type":"Constituency","stateId":1,"task":"mainLevel"
			}
			$.ajax({
			  type:'GET',
			  url: 'getLocationWiseAsOfNowDetailsAction.action',
			  data : {task:JSON.stringify(jObj)} ,
            }).done(function(result){
			       if(result[0].name == undefined){
					   location.reload(); 
				   }
				   try{
					 if(result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
						location.reload();
					 }
				   }catch(e){
				   }
                  result.sort(SortByPerc);
				  var count = 1;
				  var str ="";
				 str+='<marquee scrollamount="3">';
						
				  for(var i in result){
				  if(count < 10){
				    str+='<span class="rank-success">0'+count+'</span>&nbsp;<b>'+ result[i].name+'</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
				   }else{
				     str+='<span class="rank-success">'+count+'</span>&nbsp;<b>'+ result[i].name+'</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
				   }
				    count++;
					if(count == 21){
					   break;
					}
				  }
				  str+='</marquee>';
				  $("#apAllN").html(str);
				  //callScrollingMethod("apTotalC"+apTotalC);
				  apTotalC++;
			});
			
		}
     function getTgToday(){
			
			var jObj = {
				"type":"Constituency","stateId":2,"fromTask":"today","task":"mainLevel"
			}
			$.ajax({
			  type:'GET',
			  url: 'getLocationWiseToDayDetailsAction.action',
			  data : {task:JSON.stringify(jObj)} ,
            }).done(function(result){
			        if(result[0].name == undefined){
					   location.reload(); 
				   }
				   try{
					 if(result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
						location.reload();
					 }
				   }catch(e){
				   }
                  result.sort(SortByPerc);
				  var count = 1;
				  var str ="";
				  str+='<marquee scrollamount="3">';
						
				  for(var i in result){
				  if(count < 10){
				    str+='<span class="rank">0'+count+'</span>&nbsp;<b>'+ result[i].name+'</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
				   }else{
				     str+='<span class="rank">'+count+'</span>&nbsp;<b>'+ result[i].name+'</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
				   }
				    count++;
					if(count == 21){
					   break;
					}
				  }
				 str+='</marquee>';
				  $("#tsTodayN").html(str);
				  //callScrollingMethod('tsTodayC'+tsTodayC);
				  tsTodayC++;
				 
			});
		}
	   function getTgTotal(){
			
			var jObj = {
				"type":"Constituency","stateId":2,"task":"mainLevel"
			}
			$.ajax({
			  type:'GET',
			  url: 'getLocationWiseAsOfNowDetailsAction.action',
			  data : {task:JSON.stringify(jObj)} ,
            }).done(function(result){
			       if(result[0].name == undefined){
					   location.reload(); 
				   }
				   try{
					 if(result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
						location.reload();
					 }
				   }catch(e){
				   }
                 result.sort(SortByPerc);
				  var count = 1;
				  var str ="";
				  str+='<marquee scrollamount="3">';
						
				  for(var i in result){
				  if(count < 10){
				    str+='<span class="rank-success">0'+count+'</span>&nbsp;<b>'+ result[i].name+'</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
				   }else{
				     str+='<span class="rank-success">'+count+'</span>&nbsp;<b>'+ result[i].name+'</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
				   }
				    count++;
					if(count == 21){
					   break;
					}
				  }
				  str+='</marquee>';
				  $("#tsAllN").html(str);
				  //callScrollingMethod("tsTotalC"+tsTotalC);
				tsTotalC++;
			});
		}
       getApToday();
	   getApTotal();
	   getTgToday();
	   getTgTotal()
	   getDashBoardBasicInfo();
	   setInterval(function(){getApToday()},900000);
	   setInterval(function(){getApTotal()},900000);
	   setInterval(function(){getTgToday()},900000);
	   setInterval(function(){getTgTotal()},900000);
	   setInterval(function(){getDashBoardBasicInfo()},900000);
	
	function SortByPerc(a, b){
	  var aName = parseFloat(b.percentage.toLowerCase()); 
	  var  bName = parseFloat(a.percentage.toLowerCase());
	  return ((aName < bName) ? -1 : ((aName > bName) ? 1 : 0));
	}
	
</script>	
</body>
</html>
