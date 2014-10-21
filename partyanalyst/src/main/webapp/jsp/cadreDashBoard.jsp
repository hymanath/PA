<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>CADRE DASHBOARD</title>
	<!-- Custom Styles-->
	<link rel="stylesheet" type="text/css" href="css/style.css"> 
	<!-- CSS animation -->
    <link rel="stylesheet" type="text/css" href="css/cadreRegistrationCSS/animate.css" >

    <link rel="stylesheet" type="text/css" href="js/scrollator/fm.scrollator.jquery.css">	
	<script type="text/javascript" src="js/scrollator/fm.scrollator.jquery.js"></script>
	<script type="text/javascript" src="js/icheck/icheck.min.js"></script>
	<script type="text/javascript" src="js/jquery.dataTables.js"></script>
	<link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"/> 
<style>
	.show-grid:hover .block-hover-addBtn{display:table-cell; margin-right:-22px; top:-10px;}/*visibility: visible;*/
	.block-hover-addBtn{display:none; position: relative;}/*visibility: hidden;*/
	.border-none{border:none;}
	.text-lowercase{text-transform:lowercase;}
	.text-uppercase{text-transform:uppercase;}
	.text-capitalize{text-transform:capitalize;}
	.text-center{text-align: center;}
	.text-red{color:#dc504a;}
	.text-green{color:#4dbd74;}
	.text-orange{color:#f9a834;}
	.text-skyblue{color:#46acca;}
	.mb-0{margin-bottom:0px}
	.mb-10{margin-bottom:10px}
	.Previousmembercount td{width:20%;}
	.membercount td{width:25%;}
	.membercount td h2, .Previousmembercount td h2{margin:0px;}
	.progress{height:10px;}
	.height-300{height: 300px; overflow: auto;}
	.height-320{height: 300px; overflow: auto;width: 440px;}
	.f-16{font-size: 16px;}
	body {
    color: #333333;
    font-size: 14px;
    line-height: 20px;
    margin: 0;
    }
	p {
    color: #333;
    font-size: 14px;
   }
   .background {
    background: none repeat scroll 0 0 #e5e5e5;
   }
   .text-right {
    text-align: right;
   }
   .imgStyle{
      margin-left: 75px;
      margin-top: 30px;
	}
	
  .ajaxImgStyle {
    margin-bottom: 30px;
    margin-left: 94px;
    margin-top: 30px;
  }
  .dataTables_length, .dataTables_filter , .dataTables_info {
		color : #666666 !important;
	}
	
table.dataTable tr.odd td.sorting_1 {
    background-color: #d3d3d3;
}
table.dataTable tr.even td.sorting_1 {
    background-color: #fafafa;
}
table.dataTable tr.odd {
    background-color: #f3f3f3;
}
	</style>
</head>
<body>
<div class="container m_top10">
		<!-- Title Row -->
		<div class="row-fluid" id="fadeInDown">
			<div class="span12 well well-small  border-radius-0 mb-10 " style="background:#ffffff;">
				<h3 class="text-center text-uppercase">2014 Cadre Admin Dashboard</h3>
			</div>
		</div><!-- Title Row End-->
		
		<!-- Members Registered Previous Row -->
		<div class="row-fluid " id="PreviousmembersCount">
			<div class="span12 show-grid well well-small border-radius-0 mb-10">
				    <table class="table table-bordered border-radius-0 mb-0 Previousmembercount table-hover" >
						<tbody>
						    <tr>
								<td>
									<h2>TS</h2>
									<img class="pull-right" src="images/TS.png" style="margin-top: -40px;">
								</td>
								<td><div id="tsConstiCountId"><img class="imgStyle" src="images/icons/search.gif"/></div></td>
								<td><div id="ts2012CountId"><img class="imgStyle" src="images/icons/search.gif"/></div></td>
								<td><div id="ts2014CountId"><img class="imgStyle" src="images/icons/search.gif"/></div></td>
								<td><div id="tsPercCountId"><img class="imgStyle" src="images/icons/search.gif"/></div></td>
							</tr>
							<tr>
								<td>
									<h2>AP</h2>
									<img class="pull-right" src="images/AP.png" style="margin-top: -40px;">
								</td>
								<td><div id="apConstiCountId"><img class="imgStyle" src="images/icons/search.gif"/></div></td>
								<td><div id="ap2012CountId"><img class="imgStyle" src="images/icons/search.gif"/></div></td>
								<td><div id="ap2014CountId"><img class="imgStyle" src="images/icons/search.gif"/></div></td>
								<td><div id="apPercCountId"><img class="imgStyle" src="images/icons/search.gif"/></div></td>	
							</tr>
							
						</tbody>
					</table>
			</div>
		</div><!-- Members Registered Previous Row -->
		
		<!-- Members Count Row -->
		<div class="row-fluid fadeInUp">
			<div class="span12 show-grid well well-small border-radius-0 mb-10">
				    <table class="table table-bordered border-radius-0 mb-0 membercount" style="background:#ffffff;">
						<tbody>
							<tr>
								<td><div id="todayRegisCount"><img class="ajaxImgStyle" src="images/icons/search.gif"/></div></td>
								<td><div id="thisWeekRegisCount"><img class="ajaxImgStyle" src="images/icons/search.gif"/></div></td>
								<td><div id="monthRegisCount"><img class="ajaxImgStyle" src="images/icons/search.gif"/></div></td>
								<td><div id="totalRegisCount"><img class="ajaxImgStyle" src="images/icons/search.gif"/></div></td>					
							</tr>
							<tr>
								<td>
									<div id="todayApTgRegisCount" class="row-fluid">
										<img class="ajaxImgStyle" src="images/icons/search.gif"/>
									</div>
								</td>
								<td>
									<div id="thisWeekApTgRegisCount" class="row-fluid">
										<img class="ajaxImgStyle" src="images/icons/search.gif"/>
									</div>
								</td>
								<td>
									<div id="monthApTgRegisCount" class="row-fluid">
										<img class="ajaxImgStyle" src="images/icons/search.gif"/>
									</div>
								</td>
								<td>
									<div id="totalApTgRegisCount" class="row-fluid">
										<img class="ajaxImgStyle" src="images/icons/search.gif"/>
									</div>
								</td>
							</tr>
						</tbody>
					</table>
			</div>
		</div><!-- Members Count Row End -->
		
		
		<div class="row-fluid">
			<!-- Constituency wise Registration Processing Areas Row -->
			<div class="span6 show-grid well well-small border-radius-0 mb-10 fadeInUp " style="margin-left:0px;" >
				<div class="btn-group pull-right">
					<a class="btn btn-mini btn-success" href="javascript:{}" id="apConstiDetailsId" onclick="getAssemblyWiseCompletedPercentage(0,1);">AP</a>
					<a class="btn btn-mini" href="javascript:{}" id="tgConstiDetailsId" onclick="getAssemblyWiseCompletedPercentage(0,36);">TS</a>
				</div>
				<h4 class="f-16" style="padding-bottom: 5px;">Constituency wise Registration Processing Areas</h4>
				<div style="margin-bottom:10px;"><b>Select Constituency :&nbsp;</b><select id="constituencyWiseSelDivId" onchange="getConstituencyWisePerc(this.value);"><option value="0">ALL</option></select></div>
				<div id="constituencyWiseSelDivRes" class="height-300 scrollable_div">
					<img style="margin-left: 180px;margin-top: 101px;" src="images/icons/loading.gif"/>
				</div>
			</div><!-- Constituency wise Registration Processing Areas Row END-->
			
			<!-- District wise Registration Processing Areas ROW -->
			<div class="span6  show-grid well well-small border-radius-0 mb-10 fadeInUp " style="margin-left:20px;">
				<div class="btn-group pull-right">
					<a class="btn btn-mini btn-success" href="javascript:{}" id="apDistDetailsId" onclick="getDistrictWiseCompletedPercentage(0,1);">AP</a>
					<a class="btn btn-mini" href="javascript:{}" id="tgDistDetailsId" onclick="getDistrictWiseCompletedPercentage(0,36);">TS</a>
				</div>
				<h4 class="f-16" style="padding-bottom: 25px;">District wise Registration Processing Areas</h4>
				<div style="margin-bottom:10px;"><b>Select District :&nbsp;</b><select id="districtWiseSelDivId" onchange="getDistrictWisePerc(this.value);"><option value="0">ALL</option></select></div>
				<div id="districtWiseSelDivRes" class="height-320 scrollable_div">
					<img style="margin-left: 180px;margin-top: 101px;" src="images/icons/loading.gif"/>
				</div>
				
			</div>	<!-- District wise Registration Processing Areas ROW -->		
		</div>
		
		<div class="row-fluid fadeInUp">
			<div class="span8 show-grid well well-small border-radius-0 mb-10">
				<iframe src="https://www.google.com/maps/embed?pb=!1m10!1m8!1m3!1d3929013.1516925395!2d79.7399875!3d15.912899799999996!3m2!1i1024!2i768!4f13.1!5e0!3m2!1sen!2s!4v1412166071097" width="580" height="300" frameborder="0" style="border:0"></iframe>
				<table class="table table-bordered border-radius-0" style="margin-top: 5px;">
					<tbody >
						<tr>
							<td><div style="text-align:center;" id="totalMembersWorkingTodayId"><img style=" margin-top: 36px;padding-left: 110px;" src="images/icons/search.gif"/></div></td>
							<td style="width:50%;text-align:center;"><div><a href="javascript:{}" onclick="openDialogToTrack();">Click Here To View</br> Users Working Status </br> & </br> Location Wise Cadre Registration Info</a></div></b></td>
						</tr>
					</tbody>
				</table>
			</div>
			
			<!-- ReCently Registered Block -->
			<div class="span4 show-grid well well-small border-radius-0 pad-0" style=" width: 31.9149%;margin-left:20px;min-height:454px;">
				<h4 style="padding-bottom:5px;"><i class="icon-user" style="margin-top: 4px;"></i> &nbsp;Recently Registered </h4>
				<div id="recentRegisterCadresDiv"><img style="margin-top:180px;margin-left: 124px;" src="images/icons/loading.gif"/></div>
					
			</div><!-- ReCently Registered Block END -->
		</div>
	</div>
	<div id="dialogueDiv" style="display: none;">
		<center><div id="agewiseDivForDistrict"></div>
		<div id="genderWiseDivForDistrict"></div>
		<div id="casteGroupDivForDistrict"></div>
		<div id="casteWiseDivForDistrict"></div></center>
	</div>

	<div id="dialogueConstituencyDiv" style="display: none;">
		<center><div id="agewiseDivForConstituency"></div>
		<div id="genderWiseDivForConstituency"></div>
		<div id="casteGroupDivForConstituency"></div>
		<div id="casteWiseDivForConstituency"></div></center>
	</div>
<script type="text/javascript">
function openDialogToTrack(){
    window.open('cadreRegistrationReportAction.action','_blank');
}

$(document).ready(function(){
	  $('input').iCheck({
		checkboxClass: 'icheckbox_square-blue',
		radioClass: 'iradio_square-blue',
		increaseArea: '20%' // optional
	  });
	});
	//scrollator
	$('.scrollable_div').scrollator();
	
$('#fadeInDown').addClass('animated fadeInDown');
$('#fadeInLeft').addClass('animated fadeInLeft');
$('#fadeInRight').addClass('animated fadeInRight');
$('.fadeInUp').addClass('animated fadeInUp');
$('#fadeInUp1').addClass('animated fadeInUp');
$('#PreviousmembersCount').addClass('animated fadeInUp');
$('#membersCount').addClass('animated fadeInX');
   function getDashBoardBasicInfo(){
            $("#todayRegisCount").html('<img class="ajaxImgStyle" src="images/icons/search.gif"/>');
			$("#thisWeekRegisCount").html('<img class="ajaxImgStyle" src="images/icons/search.gif"/>');
			$("#monthRegisCount").html('<img class="ajaxImgStyle" src="images/icons/search.gif"/>');
			$("#totalRegisCount").html('<img class="ajaxImgStyle" src="images/icons/search.gif"/>');	
			
			$("#todayApTgRegisCount").html('<img class="ajaxImgStyle" src="images/icons/search.gif"/>');				
			$("#thisWeekApTgRegisCount").html('<img class="ajaxImgStyle" src="images/icons/search.gif"/>');					
			$("#monthApTgRegisCount").html('<img class="ajaxImgStyle" src="images/icons/search.gif"/>');
			$("#totalApTgRegisCount").html('<img class="ajaxImgStyle" src="images/icons/search.gif"/>');
				
       $.ajax({
          type:'GET',
          url: 'getCadreDashBoardBasicInfo.action',
          data: {task:"basicInfo"}
       }).done(function(result){
    	   if(result == "noAccess" || result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
    		   location.reload(); 
    	   }
			$("#todayRegisCount").html('<h2>'+result[0].totalCount+'</h2><p>Members <br/> Registered <span  style="font-weight:bold;"  class="text-red">Today</span></p></div></td>');
			$("#thisWeekRegisCount").html('<h2>'+result[1].totalCount+'</h2><p>Members <br/>Registered <span style="font-weight:bold;"  class="text-orange">This week</span></p></div></td>');
			$("#monthRegisCount").html('<h2>'+result[2].totalCount+'</h2><p>Members <br/>Registered <span style="font-weight:bold;" class="text-skyblue">This Month</span></p></div></td>');
			$("#totalRegisCount").html('<h2>'+result[3].totalCount+'</h2><p>Members <br/>In <span style="font-weight:bold;"  class="text-green">Total</span></p></div></td>');	
			
			$("#todayApTgRegisCount").html('<div class="span6"><strong>AP </strong> <br/>'+result[0].apCount+'</div><div class="span6 text-right"><strong>TS </strong><br/> '+result[0].tgCount+'</div>');				
			$("#thisWeekApTgRegisCount").html('<div class="span6"><strong>AP </strong> <br/>'+result[1].apCount+'</div><div class="span6 text-right"><strong>TS </strong><br/> '+result[1].tgCount+'</div>');					
			$("#monthApTgRegisCount").html('<div class="span6"><strong>AP </strong> <br/>'+result[2].apCount+'</div><div class="span6 text-right"><strong>TS </strong><br/> '+result[2].tgCount+'</div>');
			$("#totalApTgRegisCount").html('<div><strong>AP - </strong>'+result[3].apCount+'<span class="text-skyblue"> (NEW - '+result[4].apCount+')</span></div><div><strong>TS - </strong> '+result[3].tgCount+'<span class="text-skyblue"> (NEW - '+result[4].tgCount+')</span></div>');
								
	   });
   }
   
   function getRecentlyRegisteredCadresInfo(){
       $("#recentRegisterCadresDiv").html('<img style="margin-top:180px;margin-left: 124px;" src="images/icons/loading.gif"/></div>');
       $.ajax({
          type:'GET',
          url: 'getCadreDashBoardBasicInfo.action',
          data: {task:"recentlyRegistered"}
       }).done(function(result){
    	   if(result == "noAccess" || result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
    		   location.reload(); 
    	   }
			if(result != null && result.length > 0){
			 var str ='<table class="table table-bordered border-radius-0"><tbody>';
			   for(var i in result){
			      str+='<tr><td><div class="media"><a href="javascript:{}" class="pull-left"><img style="width:64px;height:64px;" id="cadreRegId'+i+'" onerror="setDefaultImage(this);" src="'+result[i].date+'"  /></a>'
			      str+='<div class="media-body">';
				  str+='<h4 class="media-heading">'+result[i].name+'</h4>';
				  str+='<i class="icon-map-marker"></i>'+result[i].location;
				  str+='</div></div></td></tr>';
			   }
			     str+='</tbody></table>';
			   $("#recentRegisterCadresDiv").html(str);
			}else{
               $("#recentRegisterCadresDiv").html('<div style="margin-left: 65px; margin-top: 75px;"><b>No Data Available</b></div>');
            }			
	   });
   }
   
   function getAssemblyWiseCompletedPercentage(assId,statId){
     if(assId == 0){
	   $("#constituencyWiseSelDivId option").remove();
	   $("#constituencyWiseSelDivId").append('<option value=0>ALL</option>');
	   if(statId == 1){
	     $("#apConstiDetailsId").removeClass("btn-success");
	     $("#apConstiDetailsId").addClass("btn-success");
		 $("#tgConstiDetailsId").removeClass("btn-success");
	   }else{
	     $("#tgConstiDetailsId").removeClass("btn-success");
	     $("#tgConstiDetailsId").addClass("btn-success");
		 $("#apConstiDetailsId").removeClass("btn-success");
	   }
	 }
	 $("#constituencyWiseSelDivRes").html('<img style="margin-left: 180px;margin-top: 101px;" src="images/icons/loading.gif"/>');
       $.ajax({
          type:'GET',
          url: 'getCadreDashBoardBasicInfo.action',
          data: {task:"assemblyWise",assemblyId:assId,stateId:statId}
       }).done(function(result){
    	   if(result == "noAccess" || result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
    		   location.reload(); 
    	   }
	      if(result.length > 0){
		    var str='';
		    for(var i in result){
				if(assId == 0){
				  $("#constituencyWiseSelDivId").append('<option value='+result[i].tgCount+'>'+result[i].location+'</option>');
				}
				str += '<p><a href="javascript:{}" onclick="getConstituencyWiseAgeGenderCasteCount('+ result[i].tgCount+ ')">'+ result[i].location+ ' ('+ result[i].apCount+ '%  - '+ result[i].totalCount+ ' Members)</a></p>';
				if(result[i].apCount <= 20){
				   str+='<div class="progress progress-danger">';
				}else if(result[i].apCount > 20 && result[i].apCount <= 40){
				   str+='<div class="progress progress-warning">';
				}else if(result[i].apCount > 40 && result[i].apCount <= 60){
				   str+='<div class="progress progress-info">';
				}else if(result[i].apCount > 60 && result[i].apCount <= 80){
				   str+='<div class="progress">';
				}else{
				   str+='<div class="progress progress-success">';
				}
				str+='  <div style="width:'+result[i].apCount+'%" class="bar"></div>';
				str+='</div>';
			}
			$("#constituencyWiseSelDivRes").html(str);
		  }else{
		    $("#constituencyWiseSelDivRes").html("<div style='margin-left: 150px;margin-top: 100px;'><b>No Data Available</b></div>");
		  }
	   });
   }
   
   function getDistrictWiseCompletedPercentage(distId,statId){
     if(distId == 0){
	   $("#districtWiseSelDivId option").remove();
	   $("#districtWiseSelDivId").append('<option value=0>ALL</option>');
	    if(statId == 1){
	     $("#apDistDetailsId").removeClass("btn-success");
	     $("#apDistDetailsId").addClass("btn-success");
		 $("#tgDistDetailsId").removeClass("btn-success");
	   }else{
	     $("#tgDistDetailsId").removeClass("btn-success");
	     $("#tgDistDetailsId").addClass("btn-success");
		 $("#apDistDetailsId").removeClass("btn-success");
	   }
	 }
	  $("#districtWiseSelDivRes").html('<img style="margin-left: 180px;margin-top: 101px;" src="images/icons/loading.gif"/>');
       $.ajax({
          type:'GET',
          url: 'getCadreDashBoardBasicInfo.action',
          data: {task:"districtWise",districtId:distId,stateId:statId}
       }).done(function(result){
    	   if(result == "noAccess" || result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
    		   location.reload(); 
    	   }
	       if(result.length > 0){
		     var str='';
		    for(var i in result){
			    if(distId == 0){
				  $("#districtWiseSelDivId").append('<option value='+result[i].tgCount+'>'+result[i].location+'</option>');
				}
				str += '<p><a href="javascript:{}" onclick="getDistrictWiseAgeGenderCasteCount('+ result[i].tgCount+ ')">'+ result[i].location+ ' ('+ result[i].apCount+ '%  - '+ result[i].totalCount+ ' Members)</a></p>';
				if(result[i].apCount <= 20){
				   str+='<div class="progress progress-danger">';
				}else if(result[i].apCount > 20 && result[i].apCount <= 40){
				   str+='<div class="progress progress-warning">';
				}else if(result[i].apCount > 40 && result[i].apCount <= 60){
				   str+='<div class="progress progress-info">';
				}else if(result[i].apCount > 60 && result[i].apCount <= 80){
				   str+='<div class="progress">';
				}else{
				   str+='<div class="progress progress-success">';
				}
				str+='  <div style="width:'+result[i].apCount+'%" class="bar"></div>';
				str+='</div>';
			}
			 $("#districtWiseSelDivRes").html(str);
		  }else{
		    $("#districtWiseSelDivRes").html("<div style='margin-left: 150px;margin-top: 100px;'><b>No Data Available</b></div>");
		  }
	   });
   }
   
   function getWorkStartedConstituencyCount(){
            $("#tsConstiCountId").html('<img class="imgStyle" src="images/icons/search.gif"/>');
			$("#ts2012CountId").html('<img class="imgStyle" src="images/icons/search.gif"/>');
			$("#ts2014CountId").html('<img class="imgStyle" src="images/icons/search.gif"/>');
			$("#tsPercCountId").html('<img class="imgStyle" src="images/icons/search.gif"/>');
							
			$("#apConstiCountId").html('<img class="imgStyle" src="images/icons/search.gif"/>');
			$("#ap2012CountId").html('<img class="imgStyle" src="images/icons/search.gif"/>');
			$("#ap2014CountId").html('<img class="imgStyle" src="images/icons/search.gif"/>');
			$("#apPercCountId").html('<img class="imgStyle" src="images/icons/search.gif"/>');	
       $.ajax({
          type:'GET',
          url: 'getCadreDashBoardBasicInfo.action',
          data: {task:"workStartedConstituency"}
       }).done(function(result){
    	   if(result == "noAccess" || result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
    		   location.reload(); 
    	   }
			$("#tsConstiCountId").html('<h2>'+result[1].totalCount+'</h2><p>Registration Started Constituencies</p></div></td>');
			$("#ts2012CountId").html('<h2>'+result[1].apCount+'</h2><p>Members <br/>Registered in&nbsp;<span class="text-red">2012</span></p></div></td>');
			$("#ts2014CountId").html('<h2>'+result[1].tgCount+'</h2><p>Members <br/>Registered in&nbsp;<span class="text-green">2014</span></p></div></td>');
			$("#tsPercCountId").html('<h2>'+result[1].percentage+'%</h2><p>Members <br/>In <span class="text-orange">Total </span></p></div></td>');
							
			$("#apConstiCountId").html('<h2>'+result[0].totalCount+'</h2><p>Registration Started Constituencies</p></div></td>');
			$("#ap2012CountId").html('<h2>'+result[0].apCount+'</h2><p>Members <br/>Registered in&nbsp;<span class="text-red">2012</span></p></div></td>');
			$("#ap2014CountId").html('<h2>'+result[0].tgCount+'</h2><p>Members <br/>Registered in&nbsp;<span class="text-green">2014</span></p></div></td>');
			$("#apPercCountId").html('<h2>'+result[0].percentage+'%</h2><p>Members <br/>In <span class="text-orange">Total </span></p></div></td>');	
								
	   });
   }
   function getConstituencyWisePerc(id){
      var stateId = 1;
      if($( "#tgConstiDetailsId").hasClass( "btn-success" )){
	    stateId = 36;
	  }
      getAssemblyWiseCompletedPercentage(id,stateId);
   }
   function getDistrictWisePerc(id){
     var stateId = 1;
      if($( "#tgDistDetailsId").hasClass( "btn-success" )){
	    stateId = 36;
	  }
     getDistrictWiseCompletedPercentage(id,stateId);
   }
   function getWorkingMembersInfo(){
        $("#totalMembersWorkingTodayId").html('<img style=" margin-top: 36px;padding-left: 110px;" src="images/icons/search.gif"/>');
         $.ajax({
          type:'GET',
          url: 'getWorkingMembersInfo.action',
          data: {task:"workingCount"}
       }).done(function(result){
    	   if(result == "noAccess" || result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
    		   location.reload(); 
    	   }
	      $("#totalMembersWorkingTodayId").html('<h2>'+result.totalCount+'</h2><p>Members <br/>In Field Today</p>');
	   });
   }
   function setDefaultImage(img)
	{
		img.src = "images/User.png";
	}
	function getConstituencyWiseAgeGenderCasteCount(constId) {
	        $('#agewiseDivForConstituency').html('<img src="images/Loading-data.gif" style="margin-top: 78px;width:70px;height:60px;">');
			$('#genderWiseDivForConstituency').html("");
			$('#casteWiseDivForConstituency').html("");
			$('#casteGroupDivForConstituency').html("");
	        $('#dialogueConstituencyDiv')
						.dialog(
								{
									width : 850,
									height:550,
									title : "Constituency Wise Cadre Age, Gender and Caste Information "
								});

			$.ajax({
				type : 'GET',
				url : 'getRepInfo.action',
				data : {
					task :'castGroupConsti',
					id:constId
				}
			}).done(function(result) {
				if(result == "noAccess" || result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
		    		   location.reload(); 
		    	   }
				if (result != null && result.length > 0) {
					var str3 = '';
					str3 += '<h5> CASTE GROUP WISE DETAILS </h5><br/>';
					
					str3 += '<table class="table table-bordered m_top20 "  style="width:600px;">';
					
					str3 += '<thead>';
					str3 += '<tr>';
					str3 += '<th ROWSPAN=2> Caste  </th>';
					str3 += '<th  COLSPAN=2> 2014  </th>';
					str3 += '<th  COLSPAN=2> 2012  </th>';
					str3 += '</tr>';
					str3 += '<tr>';
					str3 += '<th> Total  </th>';
					str3 += '<th> %  </th>';
					str3 += '<th> Total  </th>';
					str3 += '<th> %  </th>';
					str3 += '</tr>';
					str3 += '</thead>';
					str3 += '<tbody>';

					var reqRes =result;
							for ( var i in reqRes) {
									str3 += '<tr>';
									str3 += '  <td>' +reqRes[i].name+ '</td>';
									str3 += '  <td>'+reqRes[i].apCount+ '</td>';
									str3 += '  <td>'+parseFloat(reqRes[i].percentStr)+ '</td>';
									str3 += '  <td>'+ reqRes[i].tgCount+ '</td>';
									str3 += '  <td>'+parseFloat(reqRes[i].area)+ '</td>';
									str3 += '</tr>';
							}

					str3 += '</tbody>';
					str3 += '</table>';
					$('#casteGroupDivForConstituency').html(str3);
				}
			});
			
			$.ajax({
				type : 'GET',
				url : 'getRepInfo.action',
				data : {
					task :"ageConsti",
					id:constId
				}
			}).done(function(result) {
				if(result == "noAccess" || result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
		    		   location.reload(); 
		    	   }
				if (result != null && result.length > 0) {
					var str = '';
					str += '<h5> AGE WISE DETAILS </h5>';
					str += '<table class="table table-bordered m_top20 " id="ageWiseTab" style="width:600px;">';
					str += '<thead>';
					str += '<tr>';
					str += '<th ROWSPAN=2> Age  </th>';
					str += '<th  COLSPAN=2> 2014  </th>';
					
					str += '</tr>';
					str += '<tr>';
					str += '<th> Total  </th>';
					str += '<th> %  </th>';
					str += '</tr>';
					str += '</thead>';

					str += '<tbody>';
		             var reqRes =result;
							for ( var i in reqRes) {
									str += '<tr>';
									str += '  <td>' +reqRes[i].name+ '</td>';
									str += '  <td>'+ reqRes[i].apCount+ '</td>';
									str += '  <td>'+ reqRes[i].percentStr+ '</td>';
									str += '</tr>';
							}
					str += '</tbody>';
					str += '</table>';
					   $('#agewiseDivForConstituency').html(str);
				}
			});
			
			$.ajax({
				type : 'GET',
				url : 'getRepInfo.action',
				data : {
					task :"genderConsti",
					id:constId
				}
			}).done(function(result) {
				if(result == "noAccess" || result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
		    		   location.reload(); 
		    	   }
				if (result != null && result.length > 0) {
					var str2 = '';
					str2 += '<h5> GENDER WISE DETAILS </h5>';
					str2 += '<table class="table table-bordered m_top20 " id="ageWiseTab" style="width:600px;">';
					str2 += '<thead>';
					str2 += '<tr>';
					str2 += '<th ROWSPAN=2> Gender </th>';
					str2 += '<th  COLSPAN=2> 2014  </th>';
					str2 += '<th  COLSPAN=2> 2012  </th>';
					str2 += '</tr>';
					str2 += '<tr>';
					str2 += '<th> Total  </th>';
					str2 += '<th> %  </th>';
					str2 += '<th> Total  </th>';
					str2 += '<th> %  </th>';
					str2 += '</tr>';
					str2 += '</thead>';
					str2 += '<tbody>';

					 var reqRes =result;
							for ( var i in reqRes) {
									str2 += '<tr>';
									str2 += '  <td>' +reqRes[i].name+ '</td>';
									str2 += '  <td>'+ reqRes[i].apCount+ '</td>';
									str2 += '  <td>'+ reqRes[i].percentStr+ '</td>';
									str2 += '  <td>'+ reqRes[i].tgCount+ '</td>';
									str2 += '  <td>'+ reqRes[i].area+ '</td>';
									str2 += '</tr>';
							}
					str2 += '</tbody>';
					str2 += '</table>';
					   $('#genderWiseDivForConstituency').html(str2);
				}
			});
			
			$.ajax({
				type : 'GET',
				url : 'getRepInfo.action',
				data : {
					task :"casteConsti",
					id:constId
				}
			}).done(function(result) {
				if(result == "noAccess" || result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
		    		   location.reload(); 
		    	   }
				if (result != null && result.length > 0) {
					var str3 = '';
					str3 += '<h5> CASTE WISE DETAILS </h5><br/>';
					str3 += '<table class="table table-bordered m_top20 " id="casteWiseConTab" style="width:600px;">';
					str3 += '<thead>';
					str3 += '<tr>';
					str3 += '<th ROWSPAN=2> Caste  </th>';
					str3 += '<th ROWSPAN=2> Caste Group </th>';
					str3 += '<th  COLSPAN=2> 2014  </th>';
					str3 += '<th  COLSPAN=2> 2012  </th>';
					str3 += '</tr>';
					str3 += '<tr>';
					str3 += '<th> Total  </th>';
					str3 += '<th> %  </th>';
					str3 += '<th> Total  </th>';
					str3 += '<th> %  </th>';
					str3 += '</tr>';
					str3 += '</thead>';
					str3 += '<tbody>';

					var reqRes =result;
							for ( var i in reqRes) {
									str3 += '<tr>';
									str3 += '  <td>' +reqRes[i].name+ '</td>';
									str3 += '  <td>' +reqRes[i].date+ '</td>';
									str3 += '  <td>'+reqRes[i].apCount+ '</td>';
									str3 += '  <td>'+parseFloat(reqRes[i].percentStr)+ '</td>';
									str3 += '  <td>'+ reqRes[i].tgCount+ '</td>';
									str3 += '  <td>'+parseFloat(reqRes[i].area)+ '</td>';
									str3 += '</tr>';
							}

					str3 += '</tbody>';
					str3 += '</table>';

					   $('#casteWiseDivForConstituency').html(str3);
					    $('#casteWiseConTab').dataTable({
					         "iDisplayLength": 20,
					          "aLengthMenu": [[20, 50, 100, -1], [20, 50, 100, "All"]]
					     });
					
				}
			});
		}
	/*function buildDetailsForConstituency(result,type) {

			var str = '';
			str += '<h5> AGE WISE DETAILS </h5>';
			str += '<table class="table table-bordered m_top20 " id="ageWiseTab" style="width:600px;">';
			str += '<thead>';
			str += '<tr>';
			str += '<th ROWSPAN=2> Age  </th>';
			str += '<th  COLSPAN=2> 2014  </th>';
			
			str += '</tr>';
			str += '<tr>';
			str += '<th> Total  </th>';
			str += '<th> %  </th>';
			str += '</tr>';
			str += '</thead>';

			str += '<tbody>';
             var reqRes =result[0].allDetailsList;
					for ( var i in reqRes) {
							str += '<tr>';
							str += '  <td>' +reqRes[i].name+ '</td>';
							str += '  <td>'+ reqRes[i].apCount+ '</td>';
							str += '  <td>'+ reqRes[i].percentStr+ '</td>';
							str += '</tr>';
					}
			str += '</tbody>';
			str += '</table>';
			if(type == "constituency"){
			   $('#agewiseDivForConstituency').html(str);
			}else{
			   $('#agewiseDivForDistrict').html(str);
			}

			var str2 = '';
			str2 += '<h5> GENDER WISE DETAILS </h5>';
			str2 += '<table class="table table-bordered m_top20 " id="ageWiseTab" style="width:600px;">';
			str2 += '<thead>';
			str2 += '<tr>';
			str2 += '<th ROWSPAN=2> Gender </th>';
			str2 += '<th  COLSPAN=2> 2014  </th>';
			str2 += '<th  COLSPAN=2> 2012  </th>';
			str2 += '</tr>';
			str2 += '<tr>';
			str2 += '<th> Total  </th>';
			str2 += '<th> %  </th>';
			str2 += '<th> Total  </th>';
			str2 += '<th> %  </th>';
			str2 += '</tr>';
			str2 += '</thead>';
			str2 += '<tbody>';

			 var reqRes =result[1].infoList;
					for ( var i in reqRes) {
							str2 += '<tr>';
							str2 += '  <td>' +reqRes[i].name+ '</td>';
							str2 += '  <td>'+ reqRes[i].apCount+ '</td>';
							str2 += '  <td>'+ reqRes[i].percentStr+ '</td>';
							str2 += '  <td>'+ reqRes[i].tgCount+ '</td>';
							str2 += '  <td>'+ reqRes[i].area+ '</td>';
							str2 += '</tr>';
					}
			str2 += '</tbody>';
			str2 += '</table>';
			if(type == "constituency"){
			   $('#genderWiseDivForConstituency').html(str2);
            }else{
			   $('#genderWiseDivForDistrict').html(str2);
			}
			var str3 = '';
			str3 += '<h5> CASTE WISE DETAILS </h5><br/>';
			if(type == "constituency"){
			  str3 += '<table class="table table-bordered m_top20 " id="casteWiseConTab" style="width:600px;">';
			}else{
			  str3 += '<table class="table table-bordered m_top20 " id="casteWiseDistTab" style="width:600px;">';
			}
			str3 += '<thead>';
			str3 += '<tr>';
			str3 += '<th ROWSPAN=2> Caste  </th>';
			str3 += '<th  COLSPAN=2> 2014  </th>';
			str3 += '<th  COLSPAN=2> 2012  </th>';
			str3 += '</tr>';
			str3 += '<tr>';
			str3 += '<th> Total  </th>';
			str3 += '<th> %  </th>';
			str3 += '<th> Total  </th>';
			str3 += '<th> %  </th>';
			str3 += '</tr>';
			str3 += '</thead>';
			str3 += '<tbody>';

			var reqRes =result[2].cadreRegisterInfoList;
					for ( var i in reqRes) {
							str3 += '<tr>';
							str3 += '  <td>' +reqRes[i].name+ '</td>';
							str3 += '  <td>'+reqRes[i].apCount+ '</td>';
							str3 += '  <td>'+parseFloat(reqRes[i].percentStr)+ '</td>';
							str3 += '  <td>'+ reqRes[i].tgCount+ '</td>';
							str3 += '  <td>'+parseFloat(reqRes[i].area)+ '</td>';
							str3 += '</tr>';
					}

			str3 += '</tbody>';
			str3 += '</table>';
			if(type == "constituency"){
			   $('#casteWiseDivForConstituency').html(str3);
			    $('#casteWiseConTab').dataTable({
			         "iDisplayLength": 20,
			          "aLengthMenu": [[20, 50, 100, -1], [20, 50, 100, "All"]]
			     });
			}else{
			   $('#casteWiseDivForDistrict').html(str3);
			   $('#casteWiseDistTab').dataTable({
			         "iDisplayLength": 20,
			          "aLengthMenu": [[20, 50, 100, -1], [20, 50, 100, "All"]]
			     });
			}
		}
		function getDistrictWiseAgeGenderCasteCount(distId) {
			$('#agewiseDivForDistrict').html('<img src="images/Loading-data.gif" style="margin-top: 78px;width:70px;height:60px;">');
			$('#genderWiseDivForDistrict').html("");
			$('#casteWiseDivForDistrict').html("");
			$('#dialogueDiv')
					.dialog(
							{
								width : 850,
								height:550,
								title : "District Wise Cadre Age, Gender and Caste Information "
							});
			var jsObj = {
				districtId : distId,
				task : "districtWiseAgeGenderCasteCount"
			}

			$.ajax({
				type : 'GET',
				url : 'getDistrictWiseAgeGenderCasteCount.action',
				data : {
					task : JSON.stringify(jsObj)
				}
			}).done(function(result) {
				if (result != null && result.length > 0) {
					buildDetailsForConstituency(result,"district")
				}
			});
		}*/
		function getDistrictWiseAgeGenderCasteCount(distId) {
	        $('#agewiseDivForDistrict').html('<img src="images/Loading-data.gif" style="margin-top: 78px;width:70px;height:60px;">');
			$('#genderWiseDivForDistrict').html("");
			$('#casteWiseDivForDistrict').html("");
			$('#casteGroupDivForDistrict').html("");
			$('#dialogueDiv')
			.dialog(
					{
						width : 850,
						height:550,
						title : "District Wise Cadre Age, Gender and Caste Information "
					});

			$.ajax({
				type : 'GET',
				url : 'getRepInfo.action',
				data : {
					task :'castGroupDist',
					id: distId
				}
			}).done(function(result) {
				if(result == "noAccess" || result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
		    		   location.reload(); 
		    	   }
				if (result != null && result.length > 0) {
					var str3 = '';
					str3 += '<h5> CASTE GROUP WISE DETAILS </h5><br/>';
					
					str3 += '<table class="table table-bordered m_top20 "  style="width:600px;">';
					
					str3 += '<thead>';
					str3 += '<tr>';
					str3 += '<th ROWSPAN=2> Caste  </th>';
					str3 += '<th  COLSPAN=2> 2014  </th>';
					str3 += '<th  COLSPAN=2> 2012  </th>';
					str3 += '</tr>';
					str3 += '<tr>';
					str3 += '<th> Total  </th>';
					str3 += '<th> %  </th>';
					str3 += '<th> Total  </th>';
					str3 += '<th> %  </th>';
					str3 += '</tr>';
					str3 += '</thead>';
					str3 += '<tbody>';

					var reqRes =result;
							for ( var i in reqRes) {
									str3 += '<tr>';
									str3 += '  <td>' +reqRes[i].name+ '</td>';
									str3 += '  <td>'+reqRes[i].apCount+ '</td>';
									str3 += '  <td>'+parseFloat(reqRes[i].percentStr)+ '</td>';
									str3 += '  <td>'+ reqRes[i].tgCount+ '</td>';
									str3 += '  <td>'+parseFloat(reqRes[i].area)+ '</td>';
									str3 += '</tr>';
							}

					str3 += '</tbody>';
					str3 += '</table>';
					$('#casteGroupDivForDistrict').html(str3);
				}
			});
			
			$.ajax({
				type : 'GET',
				url : 'getRepInfo.action',
				data : {
					task :"ageDistrict",
					id:distId
				}
			}).done(function(result) {
				if(result == "noAccess" || result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
		    		   location.reload(); 
		    	   }
				if (result != null && result.length > 0) {
					var str = '';
					str += '<h5> AGE WISE DETAILS </h5>';
					str += '<table class="table table-bordered m_top20 " id="ageWiseTab" style="width:600px;">';
					str += '<thead>';
					str += '<tr>';
					str += '<th ROWSPAN=2> Age  </th>';
					str += '<th  COLSPAN=2> 2014  </th>';
					
					str += '</tr>';
					str += '<tr>';
					str += '<th> Total  </th>';
					str += '<th> %  </th>';
					str += '</tr>';
					str += '</thead>';

					str += '<tbody>';
		             var reqRes =result;
							for ( var i in reqRes) {
									str += '<tr>';
									str += '  <td>' +reqRes[i].name+ '</td>';
									str += '  <td>'+ reqRes[i].apCount+ '</td>';
									str += '  <td>'+ reqRes[i].percentStr+ '</td>';
									str += '</tr>';
							}
					str += '</tbody>';
					str += '</table>';
					   $('#agewiseDivForDistrict').html(str);
				}
			});
			
			$.ajax({
				type : 'GET',
				url : 'getRepInfo.action',
				data : {
					task :"gendDistrict",
					id:distId
				}
			}).done(function(result) {
				if(result == "noAccess" || result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
		    		   location.reload(); 
		    	   }
				if (result != null && result.length > 0) {
					var str2 = '';
					str2 += '<h5> GENDER WISE DETAILS </h5>';
					str2 += '<table class="table table-bordered m_top20 " id="ageWiseTab" style="width:600px;">';
					str2 += '<thead>';
					str2 += '<tr>';
					str2 += '<th ROWSPAN=2> Gender </th>';
					str2 += '<th  COLSPAN=2> 2014  </th>';
					str2 += '<th  COLSPAN=2> 2012  </th>';
					str2 += '</tr>';
					str2 += '<tr>';
					str2 += '<th> Total  </th>';
					str2 += '<th> %  </th>';
					str2 += '<th> Total  </th>';
					str2 += '<th> %  </th>';
					str2 += '</tr>';
					str2 += '</thead>';
					str2 += '<tbody>';

					 var reqRes =result;
							for ( var i in reqRes) {
									str2 += '<tr>';
									str2 += '  <td>' +reqRes[i].name+ '</td>';
									str2 += '  <td>'+ reqRes[i].apCount+ '</td>';
									str2 += '  <td>'+ reqRes[i].percentStr+ '</td>';
									str2 += '  <td>'+ reqRes[i].tgCount+ '</td>';
									str2 += '  <td>'+ reqRes[i].area+ '</td>';
									str2 += '</tr>';
							}
					str2 += '</tbody>';
					str2 += '</table>';
					   $('#genderWiseDivForDistrict').html(str2);
				}
			});
			
			$.ajax({
				type : 'GET',
				url : 'getRepInfo.action',
				data : {
					task :"castDistrict",
					id:distId
				}
			}).done(function(result) {
				if(result == "noAccess" || result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
		    		   location.reload(); 
		    	   }
				if (result != null && result.length > 0) {
					var str3 = '';
					str3 += '<h5> CASTE WISE DETAILS </h5><br/>';
					str3 += '<table class="table table-bordered m_top20 " id="casteWiseConTab" style="width:600px;">';
					str3 += '<thead>';
					str3 += '<tr>';
					str3 += '<th ROWSPAN=2> Caste  </th>';
					str3 += '<th ROWSPAN=2> Caste Group </th>';
					str3 += '<th  COLSPAN=2> 2014  </th>';
					str3 += '<th  COLSPAN=2> 2012  </th>';
					str3 += '</tr>';
					str3 += '<tr>';
					str3 += '<th> Total  </th>';
					str3 += '<th> %  </th>';
					str3 += '<th> Total  </th>';
					str3 += '<th> %  </th>';
					str3 += '</tr>';
					str3 += '</thead>';
					str3 += '<tbody>';

					var reqRes =result;
							for ( var i in reqRes) {
									str3 += '<tr>';
									str3 += '  <td>' +reqRes[i].name+ '</td>';
									str3 += '  <td>' +reqRes[i].date+ '</td>';
									str3 += '  <td>'+reqRes[i].apCount+ '</td>';
									str3 += '  <td>'+parseFloat(reqRes[i].percentStr)+ '</td>';
									str3 += '  <td>'+ reqRes[i].tgCount+ '</td>';
									str3 += '  <td>'+parseFloat(reqRes[i].area)+ '</td>';
									str3 += '</tr>';
							}

					str3 += '</tbody>';
					str3 += '</table>';

					   $('#casteWiseDivForDistrict').html(str3);
					    $('#casteWiseDistTab').dataTable({
					         "iDisplayLength": 20,
					          "aLengthMenu": [[20, 50, 100, -1], [20, 50, 100, "All"]]
					     });
					
				}
			});
		}
       getWorkStartedConstituencyCount();
	   getDistrictWiseCompletedPercentage(0,1);
	   getAssemblyWiseCompletedPercentage(0,1);
	   getRecentlyRegisteredCadresInfo();
	   getDashBoardBasicInfo();
	   getWorkingMembersInfo();
	   setInterval(function(){getWorkStartedConstituencyCount()},600000);
	   setInterval(function(){getRecentlyRegisteredCadresInfo()},600000);
	   setInterval(function(){getDashBoardBasicInfo()},600000);
	   setInterval(function(){getWorkingMembersInfo()},600000);
</script>
</body>
</html>