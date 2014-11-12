<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	<!-- YUI Dependency files (Start) -->
	<script type="text/javascript" src="js/yahoo/yahoo-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yahoo-dom-event.js"></script> 
	<script type="text/javascript" src="js/yahoo/animation-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/dragdrop-min.js"></script>
	<script type="text/javascript" src="js/yahoo/element-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/button-min.js"></script> 	
	<script src="js/yahoo/resize-min.js"></script> 
	<script src="js/yahoo/layout-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/container-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/dom-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-min.js"></script>
	<script type="text/javascript" src="js/json/json-min.js"></script>
	<script type="text/javascript" src="js/yahoo/connection-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/tabview-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/datasource-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/get-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/dragdrop-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/datatable-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/paginator-min.js"></script>
	<!-- Skin CSS files resize.css must load before layout.css --> 
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/resize.css"> 
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/layout.css">
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/container.css"> 
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/button.css"> 
 	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/tabview.css">
	<link type="text/css" rel="stylesheet" href="styles/yuiStyles/datatable.css">
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/paginator.css">
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/calendar.css">      

	<!-- YUI Dependency files (End) -->
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
<script>
 var strIndex = 0;
</script>
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
						    <tr class="tsele">
								<td>
									<h2>TS</h2>
									<img class="pull-right" src="images/TS.png" style="margin-top: -40px;">
								</td>
								<td><div id="tsConstiCountId"><img class="imgStyle" src="images/icons/search.gif"/></div></td>
								<td><div id="ts2012CountId"><img class="imgStyle" src="images/icons/search.gif"/></div></td>
								<td><div id="ts2014CountId"><img class="imgStyle" src="images/icons/search.gif"/></div></td>
								<td><div id="tsPercCountId"><img class="imgStyle" src="images/icons/search.gif"/></div></td>
							</tr>
							<tr class="apele">
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
								<td class="indiEle">
									<div id="todayApTgRegisCount" class="row-fluid">
										<img class="ajaxImgStyle" src="images/icons/search.gif"/>
									</div>
								</td>
								<td class="indiEle">
									<div id="thisWeekApTgRegisCount" class="row-fluid">
										<img class="ajaxImgStyle" src="images/icons/search.gif"/>
									</div>
								</td>
								<td class="indiEle">
									<div id="monthApTgRegisCount" class="row-fluid">
										<img class="ajaxImgStyle" src="images/icons/search.gif"/>
									</div>
								</td>
								<td class="indiEle">
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
					<a class="btn btn-mini btn-success apele" href="javascript:{}" id="apConstiDetailsId" onclick="getAssemblyWiseCompletedPercentage(0,1);">AP</a>
					<a class="btn btn-mini tsele" href="javascript:{}" id="tgConstiDetailsId" onclick="getAssemblyWiseCompletedPercentage(0,36);">TS</a>
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
					<a class="btn btn-mini btn-success apele" href="javascript:{}" id="apDistDetailsId" onclick="getDistrictWiseCompletedPercentage(0,1);">AP</a>
					<a class="btn btn-mini tsele" href="javascript:{}" id="tgDistDetailsId" onclick="getDistrictWiseCompletedPercentage(0,36);">TS</a>
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
							<td>
								Select Hours :
								<select id="hoursId" onChange="getWorkingMembersInfo();">
									<option value="0" selected="selected">All</option>
									<option value="1"> 1 Hour  </option>
									<option value="2"> 2 Hours  </option>
									<option value="3"> 3 Hours  </option>
									<option value="4"> 4 Hours  </option>
									<option value="5"> 5 Hours  </option>
								</select>
								<div style="text-align:center;" id="totalMembersWorkingTodayId">
									<img style=" margin-top: 36px;padding-left: 110px;" src="images/icons/search.gif"/>
								</div>
							</td>
							<c:if test="${sessionScope.USER.isAdmin == 'true' || sessionScope.USER.accessType != 'DISTRICT'}">
							<td style="width:50%;text-align:center;"><div><a href="javascript:{}" onclick="openDialogToTrack();">Click Here To View</br> Users Working Status </br> & </br> Location Wise Cadre Registration Info</a></div></b></td>
						  </c:if>
						 <c:if test="${sessionScope.USER.accessType == 'DISTRICT'}">
							<td style="width:50%;text-align:center;"><div></div></b></td>
						  </c:if>
						</tr>
					</tbody>
				</table>
			</div>
			
			<!-- ReCently Registered Block -->
			<div class="span4 show-grid well well-small border-radius-0 pad-0" style=" width: 31.9149%;margin-left:20px;height:485px;">
				<h4 style="padding-bottom:5px;"><i class="icon-user" style="margin-top: 4px;"></i> &nbsp;Recently Registered <i class="icon-refresh" style="margin-top: 4px;margin-left:10px;cursor:pointer;" onclick="getRecentlyRegisteredCadresInfo(0,true);"></i> </h4>
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
	
	<div id="dialogueConstituencyDistCadreDiv" style="display: none;">
		<center><div id="constituencyDistCadreDiv" class="yui-skin-sam"></div></center>
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
    	   var TodayTotalWeb = result[0].apWebCount + result[0].tgWebCount;
		    var TodayTotalWeb1 = result[0].apPartyWebCount + result[0].tgPartyWebCount;
    		var TodayTotalTab = result[0].apTabCount + result[0].tgTabCount;
    		var TodayTotalOnline =  result[0].apOnlineCount + result[0].tgOnlineCount;
    		var WeekTotalWeb = result[1].apWebCount + result[1].tgWebCount;
			var WeekTotalWeb1 = result[1].apPartyWebCount + result[1].tgPartyWebCount;
    		var WeekTotalTab = result[1].apTabCount + result[1].tgTabCount;
    		var WeekTotalOnline = result[1].apOnlineCount + result[1].tgOnlineCount;
    		var MonthTotalWeb = result[2].apWebCount + result[2].tgWebCount;
			var MonthTotalWeb1 = result[2].apPartyWebCount + result[2].tgPartyWebCount;
    		var MonthTotalTab = result[2].apTabCount + result[2].tgTabCount;
    		var MonthTotalOnline = result[2].apOnlineCount + result[2].tgOnlineCount;
    		var TotalWeb = result[3].apWebCount + result[3].tgWebCount;
			var TotalWeb1 = result[3].apPartyWebCount + result[3].tgPartyWebCount;
    		var TotalTab = result[3].apTabCount + result[3].tgTabCount;
    		var TotalOnline = result[3].apOnlineCount + result[3].tgOnlineCount;
    		$("#todayRegisCount").html('<h2>'+result[0].totalCount+'</h2><p>Members <br/> Registered <span  style="font-weight:bold;">Today</span></p> <ul class="unstyled">  <li> <p class="label" style="width: 60%;">Web -'+TodayTotalWeb+'</p></li> <li> <p class="label" style="width: 60%;">PartyOffice -'+TodayTotalWeb1+'</p></li> <li>  <p class="label" style="width: 60%;">Tab - '+TodayTotalTab+'</p>  </li>   <li> <p class="label" style="width: 60%;">Online - '+TodayTotalOnline+'</p>  </li> </ul></div></td>');
    		$("#thisWeekRegisCount").html('<h2>'+result[1].totalCount+'</h2><p>Members <br/>Registered <span style="font-weight:bold;"  class="text-orange">This week</span></p><ul class="unstyled"> <li><p class="label" style="width: 60%;">Web -'+WeekTotalWeb+'</p></li><li><p class="label" style="width: 60%;">PartyOffice -'+WeekTotalWeb1+'</p></li><li><p class="label" style="width: 60%;">Tab - '+WeekTotalTab+'</p></li><li><p class="label" style="width: 60%;">Online - '+WeekTotalOnline+'</p></li></ul></div></td>');
    		$("#monthRegisCount").html('<h2>'+result[2].totalCount+'</h2><p>Members <br/>Registered <span style="font-weight:bold;" class="text-skyblue">This Month</span></p><ul class="unstyled"> <li><p class="label" style="width: 60%;">Web -'+MonthTotalWeb+'</p></li><li><p class="label" style="width: 60%;">PartyOffice -'+MonthTotalWeb1+'</p></li><li><p class="label" style="width: 60%;">Tab - '+MonthTotalTab+'</p></li><li><p class="label" style="width: 60%;">Online - '+MonthTotalOnline+'</p></li></ul></div></td>');
    		$("#totalRegisCount").html('<h2>'+result[3].totalCount+'</h2><p>Members <br/>In <span style="font-weight:bold;"  class="text-green">Total</span></p><ul class="unstyled"> <li><p class="label" style="width: 60%;">Web -'+TotalWeb+'</p></li><li><p class="label" style="width: 60%;">PartyOffice -'+TotalWeb1+'</p></li><li><p class="label" style="width: 60%;">Tab - '+TotalTab+'</p></li><li><p class="label" style="width: 60%;">Online - '+TotalOnline+'</p></li></ul></div></td>');	
    		
    		$("#todayApTgRegisCount").html('<div style="cursor:pointer;background-color:#EAEAEA" class="span6 mytooltip"  data-placement="top" data-toggle="tooltip" data-original-title="[Web -'+result[0].apWebCount+'] [PartyOffice -'+result[0].apPartyWebCount+'][Tab - '+result[0].apTabCount+'][Online - '+result[0].apOnlineCount+']"><strong>AP </strong> <br/><span>'+result[0].apCount+'</span></div><div style="cursor:pointer;background-color:#EAEAEA" class="span6 text-right mytooltip" data-placement="top" data-toggle="tooltip" data-original-title="[Web -'+result[0].tgWebCount+'][PartyOffice -'+result[0].tgPartyWebCount+'] [Tab - '+result[0].tgTabCount+'] [Online - '+result[0].tgOnlineCount+']"><strong>TS </strong><br/><span> '+result[0].tgCount+'</div>');				
    		$("#thisWeekApTgRegisCount").html('<div style="cursor:pointer;background-color:#fdedd6" class="span6 mytooltip" data-placement="top" data-toggle="tooltip" data-original-title="[Web -'+result[1].apWebCount+'][PartyOffice -'+result[1].apPartyWebCount+'] [Tab - '+result[1].apTabCount+'][Online - '+result[1].apOnlineCount+']"><strong>AP </strong> <br/><span >'+result[1].apCount+'</span></div><div style="cursor:pointer;background-color:#fdedd6" class="span6 text-right mytooltip" data-placement="top" data-toggle="tooltip" data-original-title="[Web -'+result[1].tgWebCount+'] [PartyOffice -'+result[1].tgPartyWebCount+'][Tab - '+result[1].tgTabCount+'][Online - '+result[1].tgOnlineCount+']"><strong>TS </strong><br/> <span >'+result[1].tgCount+'</span></div>');					
    		$("#monthApTgRegisCount").html('<div style="cursor:pointer;background-color:#DBEEF4" class="span6 mytooltip" data-placement="top" data-toggle="tooltip" data-original-title="[Web -'+result[2].apWebCount+'] [PartyOffice -'+result[2].apPartyWebCount+'][Tab - '+result[2].apTabCount+'][Online - '+result[2].apOnlineCount+']"><strong>AP </strong> <br/><span>'+result[2].apCount+'</span></div><div style="cursor:pointer;background-color:#DBEEF4" class="span6 text-right mytooltip" data-placement="top" data-toggle="tooltip" data-original-title="[Web -'+result[2].tgWebCount+'] [PartyOffice -'+result[2].tgPartyWebCount+'][Tab - '+result[2].tgTabCount+'][Online - '+result[2].tgOnlineCount+']"><strong>TS </strong><br/> <span >'+result[2].tgCount+'</span></div>');
    		$("#totalApTgRegisCount").html('<div style="cursor:pointer;background-color:#DBF1E4" class="mytooltip"  data-placement="top" data-toggle="tooltip" data-original-title="[Web -'+result[3].apWebCount+'][PartyOffice -'+result[3].apPartyWebCount+'] [Tab - '+result[3].apTabCount+'][Online - '+result[3].apOnlineCount+']"><strong>AP - </strong><span >'+result[3].apCount+'</span><span class="text-skyblue"> (NEW - '+result[4].apCount+')</span></div><div style="cursor:pointer;background-color:#DBF1E4" class="mytooltip"  data-placement="top" data-toggle="tooltip" data-original-title="[Web -'+result[3].tgWebCount+'] [PartyOffice -'+result[3].tgPartyWebCount+'][Tab - '+result[3].tgTabCount+'][Online - '+result[3].tgOnlineCount+']"><strong>TS - </strong> <span >'+result[3].tgCount+'</span><span class="text-skyblue"> (NEW - '+result[4].tgCount+')</span></div>');
    		$('.mytooltip').tooltip();					
	   });
   }
  
  
   function getRecentlyRegisteredCadresInfo(startIndex,refreshFlag){
	   if(refreshFlag == true)
		strIndex = 0;
	 
       $("#recentRegisterCadresDiv").html('<img style="margin-top:180px;margin-left: 124px;" src="images/icons/loading.gif"/></div>');
       $.ajax({
          type:'GET',
          url: 'getCadreDashBoardBasicInfo.action',
          data: {task:"recentlyRegistered",startIndex:startIndex,maxIndex: 5}
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
				  str+='<a style="float:right;cursor:pointer;" id="nextId">Next<i class="icon-forward " /></a>';
				 str+=' <a style="float:left;cursor:pointer;" id="previousId"><i class="icon-backward " /> Previous</a>';
				
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
		  try{
			result.sort(SortByName); 
			}catch(e){
			}
		    var str='';
		    for(var i in result){
				if(assId == 0){
				  $("#constituencyWiseSelDivId").append('<option value='+result[i].tgCount+'>'+result[i].location+'</option>');
				}
				str += '<p><a href="javascript:{}" onclick="getConstituencyWiseAgeGenderCasteCount('+ result[i].tgCount+ ',\''+ result[i].location+ '\')">'+ result[i].location+ ' ('+ result[i].apCount+ '%  - '+ result[i].totalCount+ ' Members)</a>&nbsp;&nbsp;<i style="cursor:pointer;margin-top: 5px;" onclick="getCadreDetails('+ result[i].tgCount+ ',\'constituency\')"  title="Click here to view details" class=" icon-eye-open"></i></p>';
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
			try{
			result.sort(SortByName); 
			}catch(e){
			}
			    if(distId == 0){
				  $("#districtWiseSelDivId").append('<option value='+result[i].tgCount+'>'+result[i].location+'</option>');
				}
				str += '<p><a href="javascript:{}" onclick="getDistrictWiseAgeGenderCasteCount('+ result[i].tgCount+ ',\''+ result[i].location+ '\')">'+ result[i].location+ ' ('+ result[i].apCount+ '%  - '+ result[i].totalCount+ ' Members)</a>&nbsp;&nbsp;<i style="cursor:pointer;margin-top: 5px;" onclick="getCadreDetails('+ result[i].tgCount+ ',\'district\')" title="Click here to view details" class=" icon-eye-open"></i></p>';
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
		var hoursCount = $('#hoursId').val();
         $.ajax({
          type:'GET',
          url: 'getWorkingMembersInfo.action',
          data: {task:"workingCount",hours:hoursCount}
       }).done(function(result){
		
		if(hoursCount != 0)
		{	     
		  $("#totalMembersWorkingTodayId").html('<h2>'+result.totalCount+'</h2><p>Members In Field, <br/> in last '+hoursCount+' Hour(s) </p>');
		}
		else
		{
			$("#totalMembersWorkingTodayId").html('<h2>'+result.totalCount+'</h2><p> Members <br/> In Field Today </p>');
		}
		
		/*
			var str = '';
			str+=' <span class="span4 btn "  style="height: 110px;cursor:auto;">';
			str+=' <h4> '+result.apCount+' </h4> Members In Field, in last 2 Hours';		
			str+=' </span>';
			
			str+=' <span class="span4 btn "  style="height: 110px;cursor:auto;">';
			str+=' <h4> '+result.tgCount+' </h4> Members In Field, in last 1 Hour';		
			str+=' </span>';
			
			str+=' <span class="span4 btn "  style="height: 110px;cursor:auto;">';
			str+=' <h4> '+result.totalCount+'  </h4> Members In Field Today';		
			str+=' </span>';	
			$("#totalMembersWorkingTodayId").html(str);
		 */
		 
	   });
   }
   function setDefaultImage(img)
	{
		img.src = "images/User.png";
	}
	function getConstituencyWiseAgeGenderCasteCount(constId,locationName) {
	        $('#agewiseDivForConstituency').html('<img src="images/Loading-data.gif" style="margin-top: 78px;width:70px;height:60px;">');
			$('#genderWiseDivForConstituency').html("");
			$('#casteWiseDivForConstituency').html("");
			$('#casteGroupDivForConstituency').html("");
	        $('#dialogueConstituencyDiv')
						.dialog(
								{
									width : 850,
									height:550,
									title : " "+locationName+" Constituency Cadre Age, Gender and Caste Information "
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
		function getDistrictWiseAgeGenderCasteCount(distId,locationName) {
	        $('#agewiseDivForDistrict').html('<img src="images/Loading-data.gif" style="margin-top: 78px;width:70px;height:60px;">');
			$('#genderWiseDivForDistrict').html("");
			$('#casteWiseDivForDistrict').html("");
			$('#casteGroupDivForDistrict').html("");
			$('#dialogueDiv')
			.dialog(
					{
						width : 850,
						height:550,
						title : " "+locationName+" District Cadre Age, Gender and Caste Information "
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
		
		$("#nextId").live("click",function(){
			
		strIndex = strIndex + 5;
		
		getRecentlyRegisteredCadresInfo(strIndex,false);
		
		});
		$("#previousId").live("click",function(){
			if(strIndex > 0)
			strIndex = strIndex - 5;
			
			getRecentlyRegisteredCadresInfo(strIndex,false);
			
		});		
		function getCadreDetails(locationId,type){
		  $('#dialogueConstituencyDistCadreDiv').dialog(
			{
				width : 880,
				height:550,
				title : " Cadre Info"
			});
          YAHOO.widget.DataTable.image = function(elLiner, oRecord, oColumn, oData){
			var str='';
			var name = oData;
			var image = oRecord.getData("memberShipNo");
			str +='<img style="width:80px;height:80px;cursor:pointer;" src="'+image+'" onerror="setDefaultImage(this);" />';
			elLiner.innerHTML=str;
		};
	     var newsColumns = [
		           {key:"PHOTO",label:"PHOTO",formatter:YAHOO.widget.DataTable.image},
				   {key:"name" ,label:"NAME"},
				   {key:"area", label:"RELATIVE"},
				   {key:"location", label:"CONSTITUENCY"},
				   {key:"number", label:"AGE"},
				   {key:"date", label:"SOURCE"}
		   ];
		var newsDataSource = new YAHOO.util.DataSource("getRegisteredInfoAction.action?&locationId="+locationId+"&locationType="+type+"&maxIndex=20&");
		newsDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
		newsDataSource.responseSchema = {
		resultsList: "infoList",
		fields: ["number","name","area","location","date","memberShipNo"],
		metaFields: {
		totalRecords: "totalCount"// Access to value in the server response
		 },
	  };

	  var myConfigs = {
		initialRequest: "&sort=name&dir=asc&startIndex=0&results=20", // Initial request for first page of data
		dynamicData: true, // Enables dynamic server-driven data
		sortedBy : {key:"name", dir:YAHOO.widget.DataTable.CLASS_ASC}, // Sets UI initial sort arrow
	    paginator : new YAHOO.widget.Paginator({ 
					rowsPerPage    : 20 
					})  // Enables pagination
	};

	var newsDataTable = new YAHOO.widget.DataTable("constituencyDistCadreDiv",
	newsColumns, newsDataSource, myConfigs);

	newsDataTable.handleDataReturnPayload = function(oRequest, oResponse, oPayload) {
		oPayload.totalRecords = oResponse.meta.totalRecords;
		return oPayload;
	}
		
		
		}
       getWorkStartedConstituencyCount();
	   getDistrictWiseCompletedPercentage(0,1);
	   getAssemblyWiseCompletedPercentage(0,1);
	   getRecentlyRegisteredCadresInfo(strIndex,false);
	   getDashBoardBasicInfo();
	   getWorkingMembersInfo();
	   setInterval(function(){getWorkStartedConstituencyCount()},600000);
	   setInterval(function(){getRecentlyRegisteredCadresInfo(strIndex,false)},600000);
	   setInterval(function(){getDashBoardBasicInfo()},600000);
	   setInterval(function(){getWorkingMembersInfo()},600000);	 
	   
	   
	   function buildSurveyMemberDetails(result)
	   { 
	   	document.getElementById('weathermap').innerHTML = "<div class='span12 m_top20 widgetservey' id='map' style='height:500px'></div>";
	   	$('#detaildDiv123').show();
	   	$.each(result,function(index,value){
	   	if(value.latitude != null && value.longititude != null)
	   	{
	   		var voterDetails = 
	   		{ "type": "Feature",
	   		"properties": {"name":value.name,"location":value.location,"partno":value.partno,"url":value.url,"value":value.value,"villageCovered":value.villageCovered},
	   			"geometry": { "type": "Polygon", "coordinates": [[[value.latitude,value.longititude]]] }
	   		};
	   		apaccampus.features.push(voterDetails);
	   	}
	   	});
	   	map = new L.Map('map').setView(new L.LatLng(result[0].longititude,result[0].latitude), 10);
	   	var osm = new L.TileLayer('http://{s}.tile.osmosnimki.ru/kosmo/{z}/{x}/{y}.png');
	   	var mpn = new L.TileLayer('http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png');
	   	var qst = new L.TileLayer('http://otile1.mqcdn.com/tiles/1.0.0/osm/{z}/{x}/{y}.png', {attribution:'Tiles Courtesy of <a href="http://www.mapquest.com/" target="_blank">MapQuest</a> <img src="http://developer.mapquest.com/content/osm/mq_logo.png">'}).addTo(map);
	   	var hyb = new L.TileLayer('http://{s}.tile.osmosnimki.ru/hyb/{z}/{x}/{y}.png');
	   	var irs = new L.TileLayer('http://tile.osmosnimki.ru/basesat/{z}/{x}/{y}.jpg');
	   	var wms = new L.TileLayer.WMS('http://wms.latlon.org/', {layers:'irs', crs: L.CRS.EPSG4326});
	   	var kadastr = new L.TileLayer.WMS('http://maps.rosreestr.ru/arcgis/services/Cadastre/CadastreWMS/MapServer/WMSServer', {format:'image/png', transparent:'true', layers:'16,15,14,13,11,10,9,22,21,20,19,18,7,6', tileSize:512});

	   	map.addControl(new L.Control.Scale({width: 100, position: 'bottomleft'}));
	   	map.addControl(new L.Control.Permalink());

	   	map.addControl(new L.Control.Layers({ 'Mapnik':mpn, 'MapQuest':qst,  'Google':new L.Google()},{}
	   	 ));
	   	L.geoJson(apaccampus, {
	   		
	   		style: function (feature) {
	   			return feature.properties && feature.properties.style;
	   		},
	   		
	   		onEachFeature: onEachFeature,
	   		
	   		
	   	   pointToLayer: function (feature, latlng) {
	   			return L.circleMarker(latlng, {
	   				
	   			});
	   		}
	   	}).addTo(map);
	   	for(var i in result)
	   	{
	   		if(result[i].latitude != null && result[i].longititude != null)
	   		{
	   			var iconImg = '';
	   			if(result[i].type == "Data Collectors")
	   			{
	   				iconImg = 'images/DC.png';
	   			}
	   			else
	   			{
	   				iconImg = 'images/DV.png';
	   			}
	   			var icon = L.icon({
	   			iconUrl: iconImg,

	   			iconSize:     [30, 30], // size of the icon
	   			shadowSize:   [10, 10], // size of the shadow
	   			iconAnchor:   [10, 10], // point of the icon which will correspond to marker's location
	   			shadowAnchor: [4, 62],  // the same for the shadow
	   			popupAnchor:  [-3, -76] // point from which the popup should open relative to the iconAnchor
	   		   });
	   			
	   	
	   			var markers = new L.Marker([result[i].longititude,result[i].latitude],{icon: icon});
	   			var popuoContent = "<table class='table table-bordered m_top20 table-hover table-striped username'><tr><td>Name : </td><td>"+result[i].name+"</td></tr>";
	   			popuoContent += "<tr><td>Mobile : </td><td>"+result[i].mandalName+"</td></tr>";
	   			popuoContent += "</table>";
	   			markers.bindPopup(popuoContent);
	   			map.addLayer(markers);	
	   		}
	   		
	   	}	   	
	   	
	   } 
function SortByName(a, b){
  var aName = a.location.toLowerCase();
  var bName = b.location.toLowerCase(); 
  return ((aName < bName) ? -1 : ((aName > bName) ? 1 : 0));
}

	checkState();
	function checkState(){
		var state = "${getState}";
		if(state=="both"){
			$(".tsele").show();
			$(".apele").show();
			$(".indiEle").show();
		}
		if(state =="AP"){
			$(".tsele").hide();
			$(".apele").show();
			$(".indiEle").hide();
		}
		if(state =="TS"){
			$(".tsele").show();
			$(".apele").hide();
			$(".indiEle").hide();
		}
	}

</script>

</body>
</html>