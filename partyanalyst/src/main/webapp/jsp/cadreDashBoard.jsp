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
	<link rel="stylesheet" type="text/css" href="css/style.css"> 
	<!-- CSS animation -->
    <link rel="stylesheet" type="text/css" href="css/cadreRegistrationCSS/animate.css" >

    <link rel="stylesheet" type="text/css" href="js/scrollator/fm.scrollator.jquery.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
	<script type="text/javascript" src="js/jQuery/js/jquery-ui-1.8.5.custom.min.js"></script>
	<script type="text/javascript" src="js/scrollator/fm.scrollator.jquery.js"></script>
	<script type="text/javascript" src="js/bootstrap3/bootstrap.js"></script>
	<script type="text/javascript" src="js/icheck/icheck.min.js"></script>
	<script type="text/javascript" src="js/jquery.dataTables.js"></script>
	<link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"/> 
	<link rel="stylesheet" type="text/css" href="styles/bootstrapInHome/bootstrap.css"/> 
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
	body{background:#e5e5e5 }
	p{margin:0px}
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
	.height-500{height: 500px; overflow: auto;}
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
 #inActiveUsersId  thead th{
				background-color: #dff0d8  !important;
				color : #468847 !important;
				line-height: 20px !important;
			}
			
			#leaderDataDiv1,#leaderDataDiv2 {font-size:10px !important;font-family:verdana;font-weight:bold;}
	
	
	.summary td {
		//color: #666699;
		padding: 7px 17px;
		cursor:pointer;
		border-bottom:1px solid lightblue;
		border-left:1px solid lightblue;
		border-right:1px solid lightblue;
	}
	.summary th {
		border:1px solid lightblue;
		color: #003399;
		font-size: 14px;
		font-weight: normal;
		padding: 12px;
	}
	
	.summary{
		margin-left:25px;margin-bottom:10px;
	}
	
	.info td {
		color: #666699;
		padding: 7px 17px;
		cursor:pointer;
		border-left:    1px solid #6699CC;
		border-right:  1px solid #6699CC; 
		border-bottom: 1px solid #6699CC;
	}
	.info th {
		border-left:    1px solid #6699CC;
		border-right:  1px solid #6699CC; 
		border-top: 1px solid #6699CC;
		color: #003399;
		font-size: 14px;
		font-weight: normal;
		padding: 12px;
	}
	
	.typeRd{margin:5px;}
	.cCodeDiv{height:8px;width:8px;margin:6px;float:left;}
	
	
.progress {
    height: 3px !important;
}		
	</style>
</head>
<body>
<script>
 var strIndex = 0;
</script>
<div class="container m_top10">
		
		<div class="row-fluid" >
		
		<button type="button" class="btn btn-primary pull-right show2016DashBoard">click here to view 2016 Cadre DashBoard </button>
		
		</div>
		<!-- Title Row -->
		<div class="row-fluid m_top10" id="fadeInDown">
			<div class="span12 well well-small  border-radius-0 mb-10 " style="background:#ffffff;">
				<h3 class="text-center text-uppercase">2014 Cadre Admin Dashboard</h3>
			</div>
		</div><!-- Title Row End-->
		
		<!-- Members Registered Previous Row -->
		<div class="row-fluid " id="PreviousmembersCount">
		  <c:if test="${fn:contains(sessionScope.USER.entitlements, 'Leader_Cadre_DashBoard' ) }"> 
			<div class="span12 show-grid well well-small border-radius-0 mb-10">
				    <table class="table table-bordered border-radius-0 mb-0 Previousmembercount table-hover" >
						<tbody>
						    <tr class="tsele">
								<td>
									<h2>TS</h2>
									<img class="pull-right" src="images/TS.png" style="margin-top: -40px;">
								</td>
								<td><div id="tsConstiCountId"><h2>119</h2><p>Registration Started Constituencies</p></div></td>
								<td><div id="ts2012CountId"><h2>482566</h2><p>Members Registered in<br><span class="text-red">2012</span></p></div></td>
								<td><div id="ts2014CountId"><img class="imgStyle" src="images/icons/search.gif"/></div></td>
								<td><div id="tsPercCountId"><img class="imgStyle" src="images/icons/search.gif"/></div></td>
							</tr>
							<tr class="apele">
								<td>
									<h2>AP</h2>
									<img class="pull-right" src="images/AP.png" style="margin-top: -40px;">
								</td>
								<td><div id="apConstiCountId"><h2>175</h2><p>Registration Started Constituencies</p></div></td>
								<td><div id="ap2012CountId"><h2>914359</h2><p>Members Registered in<br><span class="text-red">2012</span></p></div></td>
								<td><div id="ap2014CountId"><img class="imgStyle" src="images/icons/search.gif"/></div></td>
								<td><div id="apPercCountId"><img class="imgStyle" src="images/icons/search.gif"/></div></td>	
							</tr>
							
						</tbody>
					</table>
			</div>
		  </c:if>
		</div><!-- Members Registered Previous Row -->
		
		<!-- Members Count Row -->
		<div class="row-fluid fadeInUp">
			<div class="span12 show-grid well well-small border-radius-0 mb-10">
				    <table class="table table-bordered border-radius-0 mb-0 membercount" style="background:#ffffff;">
						<tbody>
							<tr>
								<td style="background-color:#EAEAEA"><div id="todayRegisCount" class="row-fluid offset1"><img class="ajaxImgStyle" src="images/icons/search.gif"/></div></td>
								<!--<td><div id="thisWeekRegisCount"><img class="ajaxImgStyle" src="images/icons/search.gif"/></div></td>
								<td><div id="monthRegisCount"><img class="ajaxImgStyle" src="images/icons/search.gif"/></div></td>-->
								<td style="background-color:#DBF1E4"><div id="totalRegisCount" class="row-fluid offset1"><img class="ajaxImgStyle" src="images/icons/search.gif"/></div></td>					
							</tr>
							<tr>
								<td class="indiEle">
									<div id="todayApTgRegisCount" class="row-fluid ">
										<img class="ajaxImgStyle" style="margin-left:130px;" src="images/icons/search.gif"/>
									</div>
								</td>
								<!--<td class="indiEle">
									<div id="thisWeekApTgRegisCount" class="row-fluid">
										<img class="ajaxImgStyle" src="images/icons/search.gif"/>
									</div>
								</td>
								<td class="indiEle">
									<div id="monthApTgRegisCount" class="row-fluid">
										<img class="ajaxImgStyle" src="images/icons/search.gif"/>
									</div>
								</td>-->
								<td class="indiEle">
									<div id="totalApTgRegisCount" class="row-fluid">
										<img class="ajaxImgStyle" style="margin-left:134px;" src="images/icons/search.gif"/>
									</div>
								</td>
							</tr>
						</tbody>
					</table>
			</div>
		</div><!-- Members Count Row End -->
		
		 <c:if test="${not fn:contains(sessionScope.USER.entitlements, 'Leader_Cadre_DashBoard' ) && not fn:contains(sessionScope.USER.entitlements, 'CADRE_2014_MP' )}"> 
		<div class="row-fluid">
			<div class="span6 show-grid well well-small border-radius-0 mb-10 fadeInUp " style="margin-left:0px;" >
				<div class="btn-group pull-right">
					<a class="btn btn-mini btn-success apele" href="javascript:{}" id="apConstiDetailsId" onclick="getAssemblyWiseCompletedPercentage(0,1);">AP</a>
					<a class="btn btn-mini tsele" href="javascript:{}" id="tgConstiDetailsId" onclick="getAssemblyWiseCompletedPercentage(0,36);">TS</a>
				</div>
				<h4 class="f-16" style="padding-bottom: 5px;">Constituency wise Registration Processing Areas</h4>
				<div style="margin-bottom:10px;"><b>Select Constituency :&nbsp;</b><select id="constituencyWiseSelDivId" onchange="getConstituencyWisePerc(this.value);"><option value="0">ALL</option></select></div>
				
				<div style="padding:2px 10px;" class="well">
					<input type="radio" id="targetCId" name="percCalcC" value="target" checked="true" style="margin-top:0px;"/><span> Target &nbsp;&nbsp;</span>
					<input type="radio" id="prevCId" name="percCalcC" value="prev" style="margin-top:0px;"/><span> Prev - Enrollment </span>
				</div>  
				
				<div id="constituencyWiseSelDivRes" class="height-300 scrollable_div" style="min-height:320px;"> 
					<img style="margin-left: 180px;margin-top: 101px;" src="images/icons/loading.gif"/>
				</div>
			</div>
			
			<div class="span6  show-grid well well-small border-radius-0 mb-10 fadeInUp " style="margin-left:20px;min-height:452px;">
				<div class="btn-group pull-right">
					<a class="btn btn-mini btn-success apele" href="javascript:{}" id="apDistDetailsId" onclick="getDistrictWiseCompletedPercentage(0,1);">AP</a>
					<a class="btn btn-mini tsele" href="javascript:{}" id="tgDistDetailsId" onclick="getDistrictWiseCompletedPercentage(0,36);">TS</a>
				</div>
				<h4 class="f-16" style="padding-bottom: 5px;">District wise Registration Processing Areas</h4>
				<div style="margin-bottom:10px;"><b>Select District :&nbsp;</b><select id="districtWiseSelDivId" onchange="getDistrictWisePerc(this.value);"><option value="0">ALL</option></select></div>
				
				<div class="well" style="padding:2px 10px;">
					<input type="radio" id="targetDId" name="percCalcD" value="target" checked="true" style="margin-top:0px;"/><span> Target &nbsp;&nbsp;</span>
					<input type="radio" id="prevDId" name="percCalcD" value="prev" style="margin-top:0px;"/><span> Prev - Enrollment</span>
				</div>
				
				<div id="districtWiseSelDivRes" class="height-320 scrollable_div">
					<img style="margin-left: 180px;margin-top: 101px;" src="images/icons/loading.gif"/>
				</div>
				
			</div>			
		</div>
		</c:if>
		 <c:if test="${fn:contains(sessionScope.USER.entitlements, 'Leader_Cadre_DashBoard' ) || fn:contains(sessionScope.USER.entitlements, 'CADRE_2014_MP' ) }"> 
		<div class="row-fluid">
			<div class="span6 show-grid well well-small border-radius-0 mb-10 fadeInUp " style="margin-left:0px;" >
				<h4> Constituency Target VS Registered Cadre  <i class="icon-refresh refreshCon" style="margin-top: 4px;margin-left:10px;cursor:pointer;"></i>
				<!--
				<i class="icon-info-sign" onClick="popUpForInformation()" title="Click To View the Color Code Information" style="margin-top: 4px;margin-left:10px;cursor:pointer;"></i>
				-->
				</h4>
			
				<div style="padding:5px;">
					<input type="radio" class="typeRd" id="todayCId" name="compareC" value="today" checked="true" style="margin-top:0px;"/><span style="margin-right:10px;"> TODAY</span>
					<input type="radio" class="typeRd" id="asOfNowCId" name="compareC" value="asoftoday" style="margin-top:0px;"/><span style="margin-right:10px;"> AS OF TODAY </span>
					<input type="radio" class="typeRd" id="overAllCId" name="compareC" value="overall" style="margin-top:0px;"/><span style="margin-right:10px;"> OVER ALL </span>
					
					<div class="btn-group pull-right">
					<input type="button" class="btn btn-mini btn-success apele" value="AP" name="constTargetBtn" id="apConstTargetComp" checked="checked">AP</input>
					<input type="button" class="btn btn-mini tsele" value="TS" name="constTargetBtn" id="tgConstTargetComp" >TS</input>
				</div>
				</div>
				<div id="leaderDataDiv2smry" style="margin-left:-30px;"  class="m_top10"></div>
				<div id="leaderDataDiv2" class="" style="margin-top: -1px">
					<img style="margin-left: 180px;margin-top: 101px;" id="ajaxImgStyle" src="images/icons/loading.gif"/>
				</div>
			</div>
			
			<div class="span6 show-grid well well-small border-radius-0 mb-10 fadeInUp " style="margin-left:20px;" >
				<h4> District  Target Vs Registered Cadre  <i class="icon-refresh refreshDist" style="margin-top: 4px;margin-left:10px;cursor:pointer;"></i>
				<!--
				<i class="icon-info-sign" onClick="popUpForInformation()" title="Click To View the Color Code Information" style="margin-top: 4px;margin-left:10px;cursor:pointer;"></i>
				
				-->
				</h4>
				
				<div style="padding:5px;">
					<input type="radio" class="typeRd" id="todayDId" name="compareD" value="today" checked="true" style="margin-top:0px;"/><span style="margin-right:10px;"> TODAY</span>
					<input type="radio" class="typeRd" id="asOfNowDId" name="compareD" value="asoftoday" style="margin-top:0px;"/><span style="margin-right:10px;"> AS OF TODAY</span>
					<input type="radio" class="typeRd" id="overAllDId" name="compareD" value="overall" style="margin-top:0px;"/><span style="margin-right:10px;"> OVER ALL</span>
					
					<div class="btn-group pull-right">
					<input type="button" value="AP" class="btn btn-mini btn-success apele" name="distTargetBtn" id="apDistTargetComp" checked="checked">AP</input>
					<input type="button" value="TS" class="btn btn-mini tsele" name="distTargetBtn" id="tgDistTargetComp">TS</input>
				</div>
				</div>
				<div id="leaderDataDiv1smry" style="margin-left:-30px;"  class="m_top10"></div>
				<div id="leaderDataDiv1" class="scrollable_div" style="margin-top: -1px">
					<img style="margin-left: 180px;margin-top: 101px;" id="ajaxImgStyle" src="images/icons/loading.gif"/>
				</div>
			</div>
		</div>
		</c:if>
		<div class="row-fluid fadeInUp">
			<div class="span12 show-grid well well-small border-radius-0 mb-10" >
				<!--<iframe src="https://www.google.com/maps/embed?pb=!1m10!1m8!1m3!1d3929013.1516925395!2d79.7399875!3d15.912899799999996!3m2!1i1024!2i768!4f13.1!5e0!3m2!1sen!2s!4v1412166071097" width="580" height="300" frameborder="0" style="border:0"></iframe>-->
				<table class="table table-bordered border-radius-0" style="margin-top: 5px; margin-bottom: 0px;">
					<tbody >
						<tr>
							<td>
							<div style="text-align:center;">
								Select Hours :
								<select id="hoursId" onChange="getWorkingMembersInfo();">
									<option value="0" selected="selected">All</option>
									<option value="1"> 1 Hour  </option>
									<option value="2"> 2 Hours  </option>
									<option value="3"> 3 Hours  </option>
									<option value="4"> 4 Hours  </option>
									<option value="5"> 5 Hours  </option>
								</select>
								</div>
								<div style="text-align:center;padding-top: 15px;" id="totalMembersWorkingTodayId">
									<img style=" margin-top: 36px;padding-left: 110px;" src="images/icons/search.gif"/>
								</div>
							</td>
							<c:if test="${sessionScope.USER.isAdmin == 'true' || sessionScope.USER.accessType != 'DISTRICT'}">
							  <td style="width:50%;text-align:center;"><div><a href="javascript:{}" onclick="openDialogToTrack();">Click Here To View</br> Users Working Status </br> & </br> Location Wise Cadre Registration Info</a></br>
							   <c:if test="${fn:contains(sessionScope.USER.entitlements, 'Leader_Cadre_DashBoard' ) }">
					             </br><a title="Click Here For Leader Cadre DashBoard" href="leaderCadreDashBoardAction.action"><span>Leader Cadre DashBoard</span></a>
								 </br></br>
								 <a title="Click Here For Cadre Reports - 2014 " href="cadreReportsAction.action"><span>Cadre Reports - 2014 </span></a>
					           </c:if></div></b></td>
						    </c:if>
						 <c:if test="${sessionScope.USER.accessType == 'DISTRICT'}">
							<td style="width:50%;text-align:center;"><div></div></b></td>
						  </c:if>
							</tr>
							
						
					</tbody>
				</table>
			</div>
			
			<!-- ReCently Registered Block 
			<div class="span7 show-grid well well-small border-radius-0 pad-0" style="margin-left:20px;min-height:370px;">
				<h4 style="padding :10px"><i class="icon-user" style="margin-top: 4px;"></i> &nbsp;Recently Registered <i class="icon-refresh" style="margin-top: 4px;margin-left:10px;cursor:pointer;" onclick="getRecentlyRegisteredCadresInfo(0,true);"></i> </h4>
				<div id="recentRegisterCadresDiv" style="margin-top:15px;"><img style="margin-top:180px;margin-left: 124px;" src="images/icons/loading.gif"/></div>
					
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
	
	
	
	
	<div id="inActiveUsersForDialog">
	  <div id="inActiveUsers" > </div>
	</div>
	
	<div id="informationWindowDIV">
		<div id="informationWindowInner" style="margin-left:10px;">
			
		</div>
	</div>
	<div id="boothWiseInfoWindowDIV">
		<div id="boothWiseInfoWindowInner" style="margin-left:10px;">
			
		</div>
	</div>
<script type="text/javascript">
function openDialogToTrack(){
    window.open('cadreRegistrationReportAction.action','_blank');
}



/* $(document).ready(function(){
	  $('input').iCheck({
		checkboxClass: 'icheckbox_square-blue',
		radioClass: 'iradio_square-blue',
		increaseArea: '20%' // optional
	  });
	}); */
	//scrollator
	//$('.scrollable_div').scrollator();
	
$('#fadeInDown').addClass('animated fadeInDown');
$('#fadeInLeft').addClass('animated fadeInLeft');
$('#fadeInRight').addClass('animated fadeInRight');
$('.fadeInUp').addClass('animated fadeInUp');
$('#fadeInUp1').addClass('animated fadeInUp');
$('#PreviousmembersCount').addClass('animated fadeInUp');
$('#membersCount').addClass('animated fadeInX');
   function getDashBoardBasicInfo(){
            $("#todayRegisCount").html('<img class="ajaxImgStyle" style="margin-left:145px;" src="images/icons/search.gif"/>');
			//$("#thisWeekRegisCount").html('<img class="ajaxImgStyle" src="images/icons/search.gif"/>');
			//$("#monthRegisCount").html('<img class="ajaxImgStyle" src="images/icons/search.gif"/>');
			$("#totalRegisCount").html('<img class="ajaxImgStyle" style="margin-left:145px;" src="images/icons/search.gif"/>');	
			
			$("#todayApTgRegisCount").html('<img class="ajaxImgStyle" style="margin-left:180px;" src="images/icons/search.gif"/>');				
			//$("#thisWeekApTgRegisCount").html('<img class="ajaxImgStyle" src="images/icons/search.gif"/>');					
			//$("#monthApTgRegisCount").html('<img class="ajaxImgStyle" src="images/icons/search.gif"/>');
			$("#totalApTgRegisCount").html('<img class="ajaxImgStyle" style="margin-left:180px;"  src="images/icons/search.gif"/>');
			
			$("#ts2014CountId").html('<img class="imgStyle" src="images/icons/search.gif"/>');
			$("#tsPercCountId").html('<img class="imgStyle" src="images/icons/search.gif"/>');
						
			$("#ap2014CountId").html('<img class="imgStyle" src="images/icons/search.gif"/>');
			$("#apPercCountId").html('<img class="imgStyle" src="images/icons/search.gif"/>');	
				
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
			var TodayTotalVzwParty = result[0].vzwPartyCount;
    		/*var WeekTotalWeb = result[1].apWebCount + result[1].tgWebCount;
			var WeekTotalWeb1 = result[1].apPartyWebCount + result[1].tgPartyWebCount;
    		var WeekTotalTab = result[1].apTabCount + result[1].tgTabCount;
    		var WeekTotalOnline = result[1].apOnlineCount + result[1].tgOnlineCount;
    		var MonthTotalWeb = result[2].apWebCount + result[2].tgWebCount;
			var MonthTotalWeb1 = result[2].apPartyWebCount + result[2].tgPartyWebCount;
    		var MonthTotalTab = result[2].apTabCount + result[2].tgTabCount;
    		var MonthTotalOnline = result[2].apOnlineCount + result[2].tgOnlineCount;*/
    		var TotalWeb = result[3].apWebCount + result[3].tgWebCount;
			var TotalWeb1 = result[3].apPartyWebCount + result[3].tgPartyWebCount;
    		var TotalTab = result[3].apTabCount + result[3].tgTabCount;
    		var TotalOnline = result[3].apOnlineCount + result[3].tgOnlineCount;
			var TotalVzwParty = result[3].vzwPartyCount;
    		
			$("#todayRegisCount").html('<div class="span6"><ul class="unstyled"> <li>  <p class="label" style="width: 100%;">Tab - '+TodayTotalTab+'</p>  </li> <li> <p class="label" style="width: 100%;">Web -'+TodayTotalWeb+'</p></li> <li> <p class="label" style="width: 100%;">Party Office(Hyd) -'+TodayTotalWeb1+'</p></li> <li> <p class="label" style="width: 100%;">Party Office(Vij) -'+TodayTotalVzwParty+'</p></li>   <li> <p class="label" style="width: 100%;">Online - '+TodayTotalOnline+'</p>  </li> </ul></div><div class="span1"><img src="images/icons/brace.png" style="margin-top: 10px; margin-left: 5px;"></div><div class="span4"><h2>'+result[0].totalCount+'</h2><p>Members Registered <br><span style="font-weight:bold;">Today</span></p> </div></div></td>');
    		
			/*$("#thisWeekRegisCount").html('<h2>'+result[1].totalCount+'</h2><p>Members Registered In <br/><span style="font-weight:bold;"  class="text-orange">Last 7 Days</span></p><ul class="unstyled"> <li><p class="label" style="width: 60%;">Tab - '+WeekTotalTab+'</p></li> <li><p class="label" style="width: 60%;">Web -'+WeekTotalWeb+'</p></li><li><p class="label" style="width: 60%;">Party Office -'+WeekTotalWeb1+'</p></li><li><p class="label" style="width: 60%;">Online - '+WeekTotalOnline+'</p></li></ul></div></td>');
    		$("#monthRegisCount").html('<h2>'+result[2].totalCount+'</h2><p>Members Registered In <br/><span style="font-weight:bold;" class="text-skyblue">Last One Month</span></p><ul class="unstyled"> <li><p class="label" style="width: 60%;">Tab - '+MonthTotalTab+'</p></li> <li><p class="label" style="width: 60%;">Web -'+MonthTotalWeb+'</p></li><li><p class="label" style="width: 60%;">Party Office -'+MonthTotalWeb1+'</p></li><li><p class="label" style="width: 60%;">Online - '+MonthTotalOnline+'</p></li></ul></div></td>');*/
    		
			$("#totalRegisCount").html('<div class="span6"><ul class="unstyled"> <li><p class="label" style="width: 100%;">Tab - '+TotalTab+'</p></li> <li><p class="label" style="width:100%;">Web -'+TotalWeb+'</p></li><li><p class="label" style="width: 100%;">Party Office(Hyd) -'+TotalWeb1+'</p></li><li><p class="label" style="width: 100%;">Party Office(Vij) -'+TotalVzwParty+'</p></li><li><p class="label" style="width: 100%;">Online - '+TotalOnline+'</p></li></ul></div><div class="span1"><img src="images/icons/brace.png" style="margin-top: 10px; margin-left: 5px;"></div><div><h2>'+result[3].totalCount+'</h2><p>Members In<br/><span style="font-weight:bold;"  class="text-green">Total</span></p></div></div></td>');
			
			<c:if test="${sessionScope.USER.isAdmin == 'true' || sessionScope.USER.accessType != 'DISTRICT'}">
			$("#todayApTgRegisCount").html('<div style="cursor:pointer;background-color:#EAEAEA;" class="span4 mytooltip text-center" data-html="true" data-placement="top" data-toggle="tooltip" data-original-title="[Tab - '+result[0].apTabCount+'] [Web -'+result[0].apWebCount+'] <br> [PartyOffice -'+result[0].apPartyWebCount+'] [Online - '+result[0].apOnlineCount+']"><strong><span style="margin-left:10px;">  AP </span></strong><br><span style="margin-left:10px;"> '+result[0].apCount+'</span></div><div style="cursor:pointer;background-color:#EAEAEA;" class="span4 text-center mytooltip" data-html="true" data-placement="top" data-toggle="tooltip" data-original-title="[Tab - '+result[0].tgTabCount+'] [Web -'+result[0].tgWebCount+'] <br>[PartyOffice -'+result[0].tgPartyWebCount+'] [Online - '+result[0].tgOnlineCount+']"><strong><span style="margin-right:15px;"> TELANGANA </span></strong><br/><span style="margin-right:15px;"> '+result[0].tgCount+'</span></div><div id="ghmcTodayDiv" class="span4"><a id="todayId" class="btn btn-block border-radius-0" onclick="getGHMCDetails(\'today\');" style="font-size: 13px;margin-top:5px;">Get GHMC Drive Details</a></div>');
			</c:if>
			<c:if test="${sessionScope.USER.accessType == 'DISTRICT'}">		
			$("#todayApTgRegisCount").html('<div style="cursor:pointer;background-color:#EAEAEA;" class="span6 mytooltip text-center" data-html="true" data-placement="top" data-toggle="tooltip" data-original-title="[Tab - '+result[0].apTabCount+'] [Web -'+result[0].apWebCount+'] <br> [PartyOffice -'+result[0].apPartyWebCount+'] [Online - '+result[0].apOnlineCount+']"><strong><span style="margin-left:10px;">  AP </span></strong><br><span style="margin-left:10px;"> '+result[0].apCount+'</span></div><div style="cursor:pointer;background-color:#EAEAEA;" class="span6 text-center mytooltip" data-html="true" data-placement="top" data-toggle="tooltip" data-original-title="[Tab - '+result[0].tgTabCount+'] [Web -'+result[0].tgWebCount+'] <br>[PartyOffice -'+result[0].tgPartyWebCount+'] [Online - '+result[0].tgOnlineCount+']"><strong><span style="margin-right:15px;"> TELANGANA </span></strong><br/><span style="margin-right:15px;"> '+result[0].tgCount+'</span></div>');
    		</c:if>	
    		<!--$("#thisWeekApTgRegisCount").html('<div style="cursor:pointer;background-color:#fdedd6" class="span6 mytooltip" data-html="true" data-placement="top" data-toggle="tooltip" data-original-title="[Tab - '+result[1].apTabCount+'] [Web -'+result[1].apWebCount+']<br>[PartyOffice -'+result[1].apPartyWebCount+'] [Online - '+result[1].apOnlineCount+']"><strong><span style="margin-left:10px;"> AP</span></strong> <br/><span style="margin-left:10px;">'+result[1].apCount+'</span></div><div style="cursor:pointer;background-color:#fdedd6" class="span6 text-right mytooltip" data-html="true" data-placement="top" data-toggle="tooltip" data-original-title="[Tab - '+result[1].tgTabCount+'] [Web -'+result[1].tgWebCount+'] <br> [PartyOffice -'+result[1].tgPartyWebCount+'] [Online - '+result[1].tgOnlineCount+']"><strong><span style="margin-right:15px;"> TS </span></strong><br/> <span style="margin-right:15px;">'+result[1].tgCount+'</span></div>');					
    		<!--$("#monthApTgRegisCount").html('<div style="cursor:pointer;background-color:#DBEEF4" class="span6 mytooltip" data-html="true" data-placement="top" data-toggle="tooltip" data-original-title="[Tab - '+result[2].apTabCount+'] [Web -'+result[2].apWebCount+'] <br> [PartyOffice -'+result[2].apPartyWebCount+'] [Online - '+result[2].apOnlineCount+']"><strong><span style="margin-left:10px;"> AP</span></strong> <br/><span style="margin-left:10px;">'+result[2].apCount+'</span></div><div style="cursor:pointer;background-color:#DBEEF4" class="span6 text-right mytooltip" data-html="true" data-placement="top" data-toggle="tooltip" data-original-title="[Tab - '+result[2].tgTabCount+'] [Web -'+result[2].tgWebCount+'] <br> [PartyOffice -'+result[2].tgPartyWebCount+'] [Online - '+result[2].tgOnlineCount+']"><strong><span style="margin-right:15px;"> TS</span></strong><br/><span style="margin-right:15px;">'+result[2].tgCount+'</span></div>');-->
    		<c:if test="${sessionScope.USER.isAdmin == 'true' || sessionScope.USER.accessType != 'DISTRICT'}">				
			$("#totalApTgRegisCount").html('<div style="cursor:pointer;background-color:#DBF1E4" class="span4 mytooltip text-center" data-html="true" data-placement="top" data-toggle="tooltip" data-original-title="[Tab - '+result[3].apTabCount+'] [Web -'+result[3].apWebCount+'] <br> [PartyOffice -'+result[3].apPartyWebCount+'] [Online - '+result[3].apOnlineCount+']"><strong><span style="margin-left:10px;">AP  </span></strong><br><span >'+result[3].apCount+'</span><span class="text-skyblue"></span></div><div style="cursor:pointer;background-color:#DBF1E4;" class="span4 mytooltip text-center" data-html="true" data-placement="top" data-toggle="tooltip" data-original-title="[Tab - '+result[3].tgTabCount+'] [Web -'+result[3].tgWebCount+'] <br> [PartyOffice -'+result[3].tgPartyWebCount+'][Online - '+result[3].tgOnlineCount+']"><strong><span style="margin-left:10px;">TELANGANA </span></strong> <br><span >'+result[3].tgCount+'</span><span class="text-skyblue"> </span></div><div id="ghmcTotalDiv" class="span4"  style="margin-top: 5px;background-color:#DBF1E4"><a style="background-color:#DBF1E4;background-image:none;font-size: 13px;" class="btn btn-block border-radius-0" id="totalId" onclick="getGHMCDetails(\'total\');">Get GHMC Drive Details</a></div>');
			</c:if>
			<c:if test="${sessionScope.USER.accessType == 'DISTRICT'}">		
			$("#totalApTgRegisCount").html('<div style="cursor:pointer;background-color:#DBF1E4" class="span6 mytooltip text-center" data-html="true" data-placement="top" data-toggle="tooltip" data-original-title="[Tab - '+result[3].apTabCount+'] [Web -'+result[3].apWebCount+'] <br> [PartyOffice -'+result[3].apPartyWebCount+'] [Online - '+result[3].apOnlineCount+']"><strong><span style="margin-left:10px;">AP  </span></strong><br><span >'+result[3].apCount+'</span><span class="text-skyblue"></span></div><div style="cursor:pointer;background-color:#DBF1E4;" class="span6 mytooltip text-center" data-html="true" data-placement="top" data-toggle="tooltip" data-original-title="[Tab - '+result[3].tgTabCount+'] [Web -'+result[3].tgWebCount+'] <br> [PartyOffice -'+result[3].tgPartyWebCount+'][Online - '+result[3].tgOnlineCount+']"><strong><span style="margin-left:10px;">TELANGANA </span></strong> <br><span >'+result[3].tgCount+'</span><span class="text-skyblue"> </span></div>');
			</c:if>
	
			$('.mytooltip').tooltip();
			$("#ts2014CountId").html('<h2>'+result[3].tgCount+'</h2><p>Members Registered in<br/><span class="text-green">2014</span></p></div></td>');
            $("#ap2014CountId").html('<h2>'+result[3].apCount+'</h2><p>Members Registered in<br/><span class="text-green">2014</span></p></div></td>');
            $("#tsPercCountId").html('<h2>'+parseInt((result[3].tgCount)*100/482566)+'%</h2><p>Members In <br/><span class="text-orange">Total </span></p></div></td>');
            $("#apPercCountId").html('<h2>'+parseInt((result[3].apCount)*100/914359)+'%</h2><p>Members In <br/><span class="text-orange">Total </span></p></div></td>');			
	   });
   }
  
  
   function getRecentlyRegisteredCadresInfo(startIndex,refreshFlag){
	   if(refreshFlag == true)
		strIndex = 0;
	 
       $("#recentRegisterCadresDiv").html('<img style="margin-top:180px;margin-left: 124px;" src="images/icons/loading.gif"/></div>');
       $.ajax({
          type:'GET',
          url: 'getCadreDashBoardBasicInfo.action',
          data: {task:"recentlyRegistered",startIndex:startIndex,maxIndex: 6}
       }).done(function(result){
    	   if(result == "noAccess" || result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
    		   location.reload(); 
    	   }
			if(result != null && result.length > 0){
			 var str ='<table class="table table-bordered border-radius-0"><tbody>';
			
			   for(var i=0;i<result.length;i=i+2){
				  str+='<tr>';
			      str+='<td style="width:50%;"><div class="media"><a href="javascript:{}" class="pull-left"><img style="width:64px;height:64px;" id="cadreRegId'+i+'" onerror="setDefaultImage(this);" src="'+result[i].date+'"  /></a>'
			      str+='<div class="media-body">';
				  str+='<h4 class="media-heading">'+result[i].name+'</h4>';
				  str+='<i class="icon-map-marker"></i>'+result[i].location;
				  str+='</div></div></td>';
				  
				  str+='<td style="width:50%;"><div class="media"><a href="javascript:{}" class="pull-left"><img style="width:64px;height:64px;" id="cadreRegId'+(i+1)+'" onerror="setDefaultImage(this);" src="'+result[i+1].date+'"  /></a>'
			      str+='<div class="media-body">';
				  str+='<h4 class="media-heading">'+result[i+1].name+'</h4>';
				  str+='<i class="icon-map-marker"></i>'+result[i+1].location;
				  str+='</div></div></td>';
				  str+='</tr>';
			   }
			     str+='</tbody></table>';
				  str+='<a style="float:right;cursor:pointer;" id="nextId">Next &rarr; &nbsp;</a>';
				 str+=' <a style="float:left;cursor:pointer;" id="previousId"> &nbsp;&larr; Previous</a>';
				
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
	 
	 var val = $('input:radio[name=percCalcC]:checked').val();
	 
	 
	 $("#constituencyWiseSelDivRes").html('<img style="margin-left: 180px;margin-top: 101px;" src="images/icons/loading.gif"/>');
       $.ajax({
          type:'GET',
          url: 'getCadreDashBoardBasicInfo.action',
          data: {task:"assemblyWise",assemblyId:assId,stateId:statId,percType:val}
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
				str += '<p style="padding-bottom:2px;"><a href="javascript:{}" onclick="getConstituencyWiseAgeGenderCasteCount('+ result[i].tgCount+ ',\''+ result[i].location+ '\')">'+ result[i].location+ ' [&nbsp;'+ result[i].totalCount+ ' Members&nbsp;]';
					if(result[i].apCount <= 20)
					str+='<span class="pull-right label label-important"> '+ result[i].apCount+ '% </span>';
					else if(result[i].apCount > 20 && result[i].apCount <= 40)
										str+='<span class="pull-right label label-warning"> '+ result[i].apCount+ '% </span>';
					else if(result[i].apCount > 40 && result[i].apCount <= 60)
										str+='<span class="pull-right label label-info"> '+ result[i].apCount+ '% </span>';
					else if(result[i].apCount > 60 && result[i].apCount <= 80)
										str+='<span class="pull-right label label-info"> '+ result[i].apCount+ '% </span>';
					else
					str+='<span class="pull-right label label-success"> '+ result[i].apCount+ '% </span>';
					str+='</a>&nbsp;&nbsp;<i style="cursor:pointer;" onclick="getCadreDetails('+ result[i].tgCount+ ',\'constituency\')"  title="Click here to view details" class=" icon-eye-open"></i></p>';
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
	  var val = $('input:radio[name=percCalcD]:checked').val();
	 
	  $("#districtWiseSelDivRes").html('<img style="margin-left: 180px;margin-top: 101px;" src="images/icons/loading.gif"/>');
       $.ajax({
          type:'GET',
          url: 'getCadreDashBoardBasicInfo.action',
          data: {task:"districtWise",districtId:distId,stateId:statId,percType:val}
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
				str += '<p  style="padding-bottom:2px;"><a href="javascript:{}" onclick="getDistrictWiseAgeGenderCasteCount('+ result[i].tgCount+ ',\''+ result[i].location+ '\')">'+ result[i].location+ ' [&nbsp;'+ result[i].totalCount+ ' Members&nbsp;]';
				if(result[i].apCount <= 20)
					str+='<span class="pull-right label label-important"> '+ result[i].apCount+ '% </span>';
					else if(result[i].apCount > 20 && result[i].apCount <= 40)
										str+='<span class="pull-right label label-warning"> '+ result[i].apCount+ '% </span>';
					else if(result[i].apCount > 40 && result[i].apCount <= 60)
										str+='<span class="pull-right label label-info"> '+ result[i].apCount+ '% </span>';
					else if(result[i].apCount > 60 && result[i].apCount <= 80)
										str+='<span class="pull-right label label-info"> '+ result[i].apCount+ '% </span>';
					else
					str+='<span class="pull-right label label-success"> '+ result[i].apCount+ '% </span>';
					str+='</a>&nbsp;&nbsp;<i style="cursor:pointer;" onclick="getCadreDetails('+ result[i].tgCount+ ',\'district\')" title="Click here to view details" class=" icon-eye-open"></i></p>';
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
			$("#ts2012CountId").html('<h2>'+result[1].apCount+'</h2><p>Members Registered in<br/><span class="text-red">2012</span></p></div></td>');
			$("#ts2014CountId").html('<h2>'+result[1].tgCount+'</h2><p>Members Registered in<br/><span class="text-green">2014</span></p></div></td>');
			$("#tsPercCountId").html('<h2>'+result[1].percentage+'%</h2><p>Members In <br/><span class="text-orange">Total </span></p></div></td>');
							
			$("#apConstiCountId").html('<h2>'+result[0].totalCount+'</h2><p>Registration Started Constituencies</p></div></td>');
			$("#ap2012CountId").html('<h2>'+result[0].apCount+'</h2><p>Members Registered in<br/><span class="text-red">2012</span></p></div></td>');
			$("#ap2014CountId").html('<h2>'+result[0].tgCount+'</h2><p>Members Registered in<br/><span class="text-green">2014</span></p></div></td>');
			$("#apPercCountId").html('<h2>'+result[0].percentage+'%</h2><p>Members In <br/><span class="text-orange">Total </span></p></div></td>');	
								
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
        $("#totalMembersWorkingTodayId").html('<img style=" margin-top: 36px;" src="images/icons/search.gif"/>');
		var hoursCount = $('#hoursId').val();
         $.ajax({
          type:'GET',
          url: 'getWorkingMembersInfo.action',
          data: {task:"workingCount",hours:hoursCount}
       }).done(function(result){
		
		if(hoursCount != 0)
		{	     
		  //$("#totalMembersWorkingTodayId").html('<b style="font-size: 22px;">'+result.totalCount+'</b><p>TAB Members In Field, since last '+hoursCount+' //Hour(s) </p><b style="font-size: 22px;">'+result.apCount+'</b><p>WEB Members working, since last '+hoursCount+' Hour(s) </p>');
		  var str='';
		  str+='<b style="font-size: 22px;">'+result.totalCount+'</b><p>TAB Members In Field, since last '+hoursCount+' Hour(s) </p>';
		  str+='<b style="font-size: 22px;">'+result.apCount+'</b><p>WEB Members working, since last '+hoursCount+' Hour(s) </p>';
		   if(result.votersCount==null || result.votersCount == 0)
		   {
		    str+='<span style="margin-left: 166px;"><span style="font-size:18px;margin-left:-40px">0 In Active Users</span></span>';
		   }
		  else
		  {
		    str+='<span style="margin-left: 166px;"><a href="javascript:{gettingInActiveUsersDetails('+hoursCount+')}"><span style="font-size:18px;margin-left:-40px">'+result.votersCount+'</a> In Active Users</span></span>';
		  }
		  
          $("#totalMembersWorkingTodayId").html(str);
		}
		else
		{
			$("#totalMembersWorkingTodayId").html('<b style="font-size: 22px;">'+result.totalCount+'</b><p>TAB Members In Field Today </p><b style="font-size: 22px;">'+result.apCount+'</b><p>WEB Members  working Today </p>');
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
			<c:choose>
				<c:when test="${sessionScope.USER.accessType == 'MP' || sessionScope.USER.accessType == 'MLA'}">
					var taskVal="castGroupDistByAccess";
				</c:when>  
				<c:otherwise>
				  var taskVal="CadrecastGroupDist";
				</c:otherwise>
			</c:choose>			
			$.ajax({
				type : 'GET',
				url : 'getRepInfoForcastGroupDistAction.action',
				data : {
					task :taskVal,
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
			<c:choose>
				<c:when test="${sessionScope.USER.accessType == 'MP' || sessionScope.USER.accessType == 'MLA'}">
				var taskValue="ageDistrictByAccess";
				</c:when>  
				<c:otherwise>
				var taskValue="ageDistrict";
				</c:otherwise>
			</c:choose>
			
			$.ajax({
				type : 'GET',
				url : 'getRepInfo.action',
				data : {
					task :taskValue,
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
			
			<c:choose>
				<c:when test="${sessionScope.USER.accessType == 'MP' || sessionScope.USER.accessType == 'MLA'}">
				var taskVal1="gendDistrictByAccess";
				</c:when>  
				<c:otherwise>
				var taskVal1="gendDistrict";
				</c:otherwise>
			</c:choose>
			
			$.ajax({
				type : 'GET',
				url : 'getRepInfo.action',
				data : {
					task :taskVal1,
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
			<c:choose>
				<c:when test="${sessionScope.USER.accessType == 'MP' || sessionScope.USER.accessType == 'MLA'}">
				var taskValue1="castDistrictByAccess";
				</c:when>  
				<c:otherwise>
				var taskValue1="CadrecastDistrict";
				</c:otherwise>
			</c:choose>
			
			$.ajax({
				type : 'GET',
				url : 'getRepInfoForCastDistrictAction.action',
				data : {
					task :taskValue1,
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
			
		strIndex = strIndex + 6;
		
		//getRecentlyRegisteredCadresInfo(strIndex,false);
		
		});
		$("#previousId").live("click",function(){
			if(strIndex > 0)
			strIndex = strIndex - 6;
			
			//getRecentlyRegisteredCadresInfo(strIndex,false);
			
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
       //getWorkStartedConstituencyCount();
	 //  getRecentlyRegisteredCadresInfo(strIndex,false);
	   getDashBoardBasicInfo();
	   getWorkingMembersInfo();
	   //setInterval(function(){getWorkStartedConstituencyCount()},1800000);
	   //setInterval(function(){getRecentlyRegisteredCadresInfo(strIndex,false)},1800000);
	   //setInterval(function(){getDashBoardBasicInfo()},1800000);
	   //setInterval(function(){getWorkingMembersInfo()},1800000);	 
	   
	   $("input:radio[name=percCalcC]").click(function() {
			//var value = $(this).val();
			//getAssemblyWiseCompletedPercentage(0,1);
		});
		
		$("input:radio[name=percCalcD]").click(function() {
			//var value = $(this).val();
			//getDistrictWiseCompletedPercentage(0,1);
		});
	   
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
			
			updateSelectBtnToTS();
			
		}
	}
	
	function updateSelectBtnToTS(){
			$("#apDistTargetComp").removeAttr('checked');
			$("#tgDistTargetComp").attr('checked', 'checked');
			$("#tgDistTargetComp").addClass("btn-success");
			$("#apDistTargetComp").removeClass("btn-success");
			
			
			$("#apConstTargetComp").removeAttr('checked');
			$("#tgConstTargetComp").attr('checked', 'checked');
			$("#tgConstTargetComp").addClass("btn-success");
			$("#apConstTargetComp").removeClass("btn-success");
	}
	
 function gettingInActiveUsersDetails(hoursCount)
 {
 $('#inActiveUsers').html('<img src="images/Loading-data.gif" style="margin-top: 78px;width:70px;height:60px; margin-left:314px;">');
 $('#inActiveUsersForDialog').dialog({	
	     width : 850,
	     height:550,
	     title : "In Active Users Details For Last  " +hoursCount+" Hour(s) "
    });
   
   $.ajax({
				type : 'GET',
				url : 'gettingInActiveUsersDetailsAction.action',
				data :{hoursCount:hoursCount
				      }
			}).done(function(result) {
			    
				
				if (result != null) {
					var str3 = '';
					str3 += '<table class="table table-bordered m_top20 " id="inActiveUsersId">';
					str3 += '<thead>';
					str3 += '<tr>';
					str3 += '<th> District </th>';
					str3 += '<th> Parliament </th>';
					str3 += '<th> Constituency </th>';
					str3 += '<th> User Name </th>';
					str3 += '<th>Name</th>';
					str3 += '<th>Mobile No</th>';
					//str3 += '<th>TabNo</th>';
					str3 += '</tr>';
					str3 += '</thead>';
					str3 += '<tbody>';
                    var reqRes =result;
					for ( var i in reqRes) { 
					str3 += '<tr>';
					str3 += '  <td>' +reqRes[i].startTime+ '</td>';
					str3 += '  <td>' +reqRes[i].endTime+ '</td>';
					str3 += '  <td>' +reqRes[i].workedTime+ '</td>';
					str3 += '  <td>' +reqRes[i].desc+ '</td>';
					str3 += '  <td>' +reqRes[i].name+ '</td>';
					str3 += '  <td>'+reqRes[i].mobileNo+ '</td>';
					//str3 += '  <td>'+reqRes[i].caste+ '</td>';
					str3 += '</tr>';
							}
                    str3 += '</tbody>';
					str3 += '</table>';

					   $('#inActiveUsers').html(str3);
					   $('#inActiveUsersId').dataTable({
					         "iDisplayLength": 20,
					          "aLengthMenu": [[20, 50, 100, -1], [20, 50, 100, "All"]]
					     });
					  
				}
			});
 
 }
 
  function getLocationswiseleaderCadreInfo1(scpId,scp,stId,targetDiv){
		var scopeId = scpId; // 2 -- District 3 -- Constituency
		var scope = scp;
		var stateId = stId; // 1-- AP 2-- TS
		$(targetDiv).html('<img style="margin-left: 180px;margin-top: 101px;" id="ajaxImgStyle" src="images/icons/loading.gif"/>');
		$(targetDiv+"smry").html("");
		if(scopeId == 0){
			$("#errStatusDiv").html("Select Scope").css("color","red");
			return;
		}
		//$("#ajaxImgStyle").show();
		var jObj = {
			type : scope,
			stateId:stateId,
			task:"mainLevel"
		}
		
		var constant = "1"
		if(targetDiv == "#leaderDataDiv2"){
			constant = "2"
		}
		$.ajax({
          type:'GET',
          url: 'getLocationWiseAsOfNowDetailsAction.action',
         data : {task:JSON.stringify(jObj)} ,
        }).done(function(result){
				//$("#ajaxImgStyle").hide();
				var str='';
				var str1='';
				str1+='<div>';
						str1+='<table class="summary">';
							str1+="<tr>";
								str1+="<th class='best'> VERY GOOD </th>";
								str1+="<th class='good'> GOOD </th>";
								str1+="<th class='ok'> OK </th>";
								str1+="<th class='poor'> POOR </th>";
								str1+="<th class='worst'> VERY POOR </th>";
							str1+="</tr>";
							str1+="<tr>";
								if(scopeId==2){
									str1+="<td attr='district' attrst='Best' class='statusBsd best' title='Click to View Districts With Status Very Good'> <div class='cCodeDiv' style='background-color:green;'/>"+result[0].bestCount+"</td>";
									str1+="<td attr='district' attrst='Good' class='statusBsd good' title='Click to View Districts With Status  Good'><div class='cCodeDiv' style='background-color:lightgreen;'/>"+result[0].goodCount+"</td>";
									str1+="<td attr='district' attrst='Ok' class='statusBsd ok' title='Click to View Districts With Status Ok'><div class='cCodeDiv' style='background-color:yellow;'/>"+result[0].okCount+"</td>";
									str1+="<td attr='district' attrst='Poor' class='statusBsd poor' title='Click to View Districts With Status  Poor'><div class='cCodeDiv' style='background-color:orange;'/>"+result[0].poorCount+"</td>";
									str1+="<td attr='district' attrst='Worst' class='statusBsd worst' title='Click to View Districts With Status Very Poor'><div class='cCodeDiv' style='background-color:#C43C35;'/>"+result[0].worstCount+"</td>";
								}else{
									str1+="<td attr='constituency' attrst='Best' class='statusBsc best' title='Click to View Constituencies With Status Very Good'> <div class='cCodeDiv' style='background-color:green;'/>"+result[0].bestCount+"</td>";
									str1+="<td attr='constituency' attrst='Good' class='statusBsc good' title='Click to View Constituencies With Status Good'><div class='cCodeDiv' style='background-color:lightgreen;'/>"+result[0].goodCount+"</td>";
									str1+="<td attr='constituency' attrst='Ok' class='statusBsc ok' title='Click to View Constituencies With Status Ok'><div class='cCodeDiv' style='background-color:yellow;'/>"+result[0].okCount+"</td>";
									str1+="<td attr='constituency' attrst='Poor' class='statusBsc poor' title='Click to View Constituencies With Status Poor'><div class='cCodeDiv' style='background-color:orange;'/>"+result[0].poorCount+"</td>";
									str1+="<td attr='constituency' attrst='Worst' class='statusBsc worst' title='Click to View Constituencies With Status Very Poor'><div class='cCodeDiv' style='background-color:#C43C35;'/>"+result[0].worstCount+"</td>";
								}
							str1+="</tr>";
						str1+='</table>';
						str1+='<div class="span12"> <i class="icon-info-sign pull-right" onClick="popUpForInformation()" title="Click To View the Color Code Information" style="margin-top: -75px;margin-right:20px ; cursor:pointer;cursor:pointer;"></i>';
					
					str1+='</div>';
					
					str+='<table class="table table-bordered" id="'+constant+'tabledata1">';
					str+='<thead><tr>';
					str+='<th>'+scope+'</th>';
					str+='<th>Target Cadres</th>';
					str+='<th>Registered Cadres</th>';
					//str+='<th>Difference</th>';
					str+='<th>% of Register cadres</th>';
					str+='<th>Cadre Details</th>';
					str+='</tr></thead>';
					str+='<tbody>';
					for(var i in result)
					{
						str+='<tr>';
						if(scopeId == 2)
						{
							str+='<td><a href="javascript:{}" onclick="getDistrictWiseAgeGenderCasteCount('+ result[i].id+ ',\''+ result[i].name+ '\')"> '+result[i].name+' </a></td>';
						}
						else
						{
							str+='<td><a href="javascript:{}" onclick="getConstituencyWiseAgeGenderCasteCount('+ result[i].id+ ',\''+ result[i].name+ '\')"> '+result[i].name+' </a></td>';
						}
						str+='<td>'+result[i].targetCadres+'</td>';
						if(result[i].totalRecords==null){
							str+='<td>-</td>';
						}else{
						str+='<td>'+result[i].totalRecords+'</td>';
						}
						//str+='<td>'+result[i].difference+'</td>';
						if(result[i].percentage==null){
							str+='<td style="background-color: #e77c79;>-</td>';
						}else{
							var colorStatus = result[i].colorStatus;
							if(colorStatus=="Best"){
								str+='<td style="background-color: green;">'+result[i].percentage+'</td>';
							}
							if(colorStatus=="Good"){
								str+='<td style="background-color:lightgreen">'+result[i].percentage+'</td>';
							}
							if(colorStatus=="Ok"){
								str+='<td style="background-color:yellow">'+result[i].percentage+'</td>';
							}
							if(colorStatus=="Poor"){
								str+='<td style="background-color:orange">'+result[i].percentage+'</td>';
							}
							if(colorStatus=="Worst"){
								str+='<td style="background-color:#C43C35">'+result[i].percentage+'</td>';
							}
							if(scopeId == 2)
							{
								str+='<td> <i style="cursor:pointer;;" onclick="getCadreDetails('+ result[i].id+ ',\'District\')"  title="Click here to view details" class=" icon-eye-open"></i></td> ';
							}
							else
							{
								str+='<td> <i style="cursor:pointer;" onclick="getCadreDetails('+ result[i].id+ ',\'constituency\')"  title="Click here to view details" class=" icon-eye-open"></i></td> ';
							}
						}
						str+='</tr>';
					}
					str+='</tbody>';
					str+='</table>';
					$(targetDiv).html(str);
					$(targetDiv+"smry").html(str1);
					$("#"+constant+"tabledata1").dataTable({
						"iDisplayLength": -1,
						"sScrollY" : "200",
						"aLengthMenu": [[20, 50, 100, -1], [20, 50, 100, "All"]]
					});
				 
				 if(targetDiv == "#leaderDataDiv1"){
					$("#leaderDataDiv1 .dataTables_length").hide();
				 }else{
					$("#leaderDataDiv2 .dataTables_length").hide();
				 }
				 
				 
			});
		}
   
		function getLocationswiseleaderCadreInfo2(scpId,scp,stId,targetDiv,fromTask){
			//var date = $.datepicker.formatDate('yy-mm-dd', new Date());
			$(targetDiv+"smry").html("");
			var scopeId = scpId;
			var scope = scp;
			var stateId = stId;
			$(targetDiv).html('<img style="margin-left: 180px;margin-top: 101px;" id="ajaxImgStyle" src="images/icons/loading.gif"/>');
			if(scopeId == 0){
				$("#errStatusDiv").html("Select Scope").css("color","red");
				return;
			}
			
			var constant = "1"
			if(targetDiv == "#leaderDataDiv2"){
				constant = "2"
			}
			
			$("#ajaxImgStyle").show();
			var jObj = {
				type : scope,
				stateId:stateId,
				//date:date,
				fromTask:fromTask,
				task:"mainLevel"
			}
			$.ajax({
			  type:'GET',
			  url: 'getLocationWiseToDayDetailsAction.action',
			  data : {task:JSON.stringify(jObj)} ,
            }).done(function(result){
				//$("#ajaxImgStyle").hide();
				var str='';
				var str1 = '';
					str1+='<div>';
						str1+='<table class="summary">';
							str1+="<tr>";
								str1+="<th class='best'> VERY GOOD </th>";
								str1+="<th class='good'> GOOD </th>";
								str1+="<th class='ok'> OK </th>";
								str1+="<th class='poor'> POOR </th>";
								str1+="<th class='worst'> VERY POOR </th>";
							str1+="</tr>";
							str1+="<tr>";
								if(scopeId==2){
									str1+="<td attr='district' attrst='Best' class='statusBsd best' title='Click to View Districts with Status Very Good'><div class='cCodeDiv' style='background-color:green;'/>"+result[0].bestCount+"</td>";
									str1+="<td attr='district' attrst='Good' class='statusBsd good' title='Click to View Districts with Status Good'><div class='cCodeDiv' style='background-color:lightgreen;'/>"+result[0].goodCount+"</td>";
									str1+="<td attr='district' attrst='Ok' class='statusBsd ok' title='Click to View Districts with Status Ok'><div class='cCodeDiv' style='background-color:yellow;'/>"+result[0].okCount+"</td>";
									str1+="<td attr='district' attrst='Poor' class='statusBsd poor' title='Click to View Districts with Status Poor'><div class='cCodeDiv' style='background-color:orange;'/>"+result[0].poorCount+"</td>";
									str1+="<td attr='district' attrst='Worst' class='statusBsd worst' title='Click to View Districts with Status Very Poor'><div class='cCodeDiv' style='background-color:#C43C35;'/>"+result[0].worstCount+"</td>";
								}else{
									str1+="<td attr='constituency' attrst='Best' class='statusBsc best'title='Click to View Constituencies with Status Very Good'><div class='cCodeDiv' style='background-color:green;'/>"+result[0].bestCount+"</td>";
									str1+="<td attr='constituency' attrst='Good' class='statusBsc good' title='Click to View Constituencies with Status Good'><div class='cCodeDiv' style='background-color:lightgreen;'/>"+result[0].goodCount+"</td>";
									str1+="<td attr='constituency' attrst='Ok' class='statusBsc ok' title='Click to View Constituencies with Status Ok'><div class='cCodeDiv' style='background-color:yellow;'/>"+result[0].okCount+"</td>";
									str1+="<td attr='constituency' attrst='Poor' class='statusBsc poor' title='Click to View Constituencies with Status Poor'><div class='cCodeDiv' style='background-color:orange;'/>"+result[0].poorCount+"</td>";
									str1+="<td attr='constituency' attrst='Worst' class='statusBsc worst' title='Click to View Constituencies with Status Very Poor'><div class='cCodeDiv' style='background-color:#C43C35;'/>"+result[0].worstCount+"</td>";
								}
							str1+="</tr>";
						str1+='</table>';
					str1+='<div class="span12"> <i class="icon-info-sign pull-right" onClick="popUpForInformation()" title="Click To View the Color Code Information" style="margin-top: -75px;margin-right:20px ; cursor:pointer;"></i>';
					str1+='</div>';
					str+='<table class="table table-bordered" id="'+constant+'tabledata2">';
					str+='<thead><tr>';
					str+='<th>'+scope+'</th>';
					str+='<th>Target Cadres</th>';
					str+='<th>Registered Cadres</th>';
					//str+='<th>Difference</th>';
					str+='<th>% of Register cadres</th>';
					str+='<th>Cadre Details</th>';
					str+='</tr></thead>';
					str+='<tbody>';
					for(var i in result){
						
						str+='<tr>';
						if(scopeId == 2)
						{
							str+='<td><a href="javascript:{}" onclick="getDistrictWiseAgeGenderCasteCount('+ result[i].id+ ',\''+ result[i].name+ '\')"> '+result[i].name+' </a></td>';
						}
						else
						{
							str+='<td><a href="javascript:{}" onclick="getConstituencyWiseAgeGenderCasteCount('+ result[i].id+ ',\''+ result[i].name+ '\')"> '+result[i].name+' </a></td>';
						}
						
						str+='<td>'+result[i].targetCadres+'</td>';
						if(result[i].totalRecords==null){
							str+='<td>-</td>';
						}else{
						str+='<td>'+result[i].totalRecords+'</td>';
						}
						//str+='<td>'+result[i].difference+'</td>';
						 if(result[i].percentage==null){
							str+='<td style="background-color: #e77c79;>-</td>';
						}else{
							var colorStatus = result[i].colorStatus;
							if(colorStatus=="Best"){
								str+='<td style="background-color: green;">'+result[i].percentage+'</td>';
							}
							if(colorStatus=="Good"){
								str+='<td style="background-color:lightgreen">'+result[i].percentage+'</td>';
							}
							if(colorStatus=="Ok"){
								str+='<td style="background-color:yellow">'+result[i].percentage+'</td>';
							}
							if(colorStatus=="Poor"){
								str+='<td style="background-color:#F89406">'+result[i].percentage+'</td>';
							}
							if(colorStatus=="Worst"){
								str+='<td style="background-color:#C43C35">'+result[i].percentage+'</td>';
							}
							if(scopeId == 2)
							{
								str+='<td> <i style="cursor:pointer;" onclick="getCadreDetails('+ result[i].id+ ',\'District\')"  title="Click here to view details" class=" icon-eye-open"></i></td> ';
							}
							else
							{
								str+='<td> <i style="cursor:pointer;" onclick="getCadreDetails('+ result[i].id+ ',\'constituency\')"  title="Click here to view details" class=" icon-eye-open"></i>'; 
								//str+='<i style="cursor:pointer;" onclick="getBoothWiseTargetInfo('+ result[i].id+ ')"  title="Click here to view booth wise target info " class=" icon-eye-open"></i>';
								str+='</td> ';
							}
						} 
						str+='</tr>';
					}
					str+='</tbody>';
					str+='</table>';
					$(targetDiv).html(str);
					$(targetDiv+"smry").html(str1);
					$("#"+constant+"tabledata2").dataTable({
						"iDisplayLength": -1,
						"sScrollY" : "200",
						"aLengthMenu": [[20, 50, 100, -1], [20, 50, 100, "All"]]
			     });
				 
				 if(targetDiv == "#leaderDataDiv1"){
					$("#leaderDataDiv1 .dataTables_length").hide();
				 }else{
					$("#leaderDataDiv2 .dataTables_length").hide();
				 }
			});
		}
   
   
	   $("#apConstTargetComp").click(function(){
			$(this).attr('checked', 'checked');
			$("#tgConstTargetComp").removeAttr('checked');
			
			$(this).addClass("btn-success");
			$("#tgConstTargetComp").removeClass("btn-success");
	   
			var val = $('input:radio[name=compareC]:checked').val();
			if(val=='today'){
				getLocationswiseleaderCadreInfo2(3,"Constituency",1,"#leaderDataDiv2","today");
			}else if(val=='asoftoday'){
				getLocationswiseleaderCadreInfo2(3,"Constituency",1,"#leaderDataDiv2","asOfToday");
			}else{
				getLocationswiseleaderCadreInfo1(3,"Constituency",1,"#leaderDataDiv2");
			}
	   });
	   
	   $("#apDistTargetComp").click(function(){
			$(this).attr('checked', 'checked');
			$("#tgDistTargetComp").removeAttr('checked');
			
			$(this).addClass("btn-success");
			$("#tgDistTargetComp").removeClass("btn-success");
	   
			var val = $('input:radio[name=compareD]:checked').val();
			if(val=='today'){
				getLocationswiseleaderCadreInfo2(2,"District",1,"#leaderDataDiv1","today");
			}if(val=='asoftoday'){
				getLocationswiseleaderCadreInfo2(2,"District",1,"#leaderDataDiv1","asOfToday");
			}else{
				getLocationswiseleaderCadreInfo1(2,"District",1,"#leaderDataDiv1");
			}
			
	   });
	   
	   $("#tgConstTargetComp").click(function(){
			$(this).attr('checked', 'checked');
			$("#apConstTargetComp").removeAttr('checked');
			
			$(this).addClass("btn-success");
			$("#apConstTargetComp").removeClass("btn-success");
	   
			var val = $('input:radio[name=compareC]:checked').val();
			if(val=='today'){
				getLocationswiseleaderCadreInfo2(3,"Constituency",2,"#leaderDataDiv2","today");
			}else if(val=='asoftoday'){
				getLocationswiseleaderCadreInfo2(3,"Constituency",2,"#leaderDataDiv2","asOfToday");
			}else{
				getLocationswiseleaderCadreInfo1(3,"Constituency",2,"#leaderDataDiv2");
			}
	   });
	   $("#tgDistTargetComp").click(function(){
			$(this).attr('checked', 'checked');
			$("#apDistTargetComp").removeAttr('checked');
			
			$(this).addClass("btn-success");
			$("#apDistTargetComp").removeClass("btn-success");
			
			var val = $('input:radio[name=compareD]:checked').val();
			if(val=='today'){
				getLocationswiseleaderCadreInfo2(2,"District",2,"#leaderDataDiv1","today");
			}else if(val=='asoftoday'){
				getLocationswiseleaderCadreInfo2(2,"District",2,"#leaderDataDiv1","asOfToday");
			}else{
				getLocationswiseleaderCadreInfo1(2,"District",2,"#leaderDataDiv1");
			}
	   });
	   
	   
	    $('input:radio[name=compareC]').click(function() {
			var state = "${getState}";
			if(state == "TS"){
				$("#tgConstTargetComp").click();
			}else{
				$("#apConstTargetComp").click();
			}
			
			var val = $('input:radio[name=compareC]:checked').val();
		});
	   $('input:radio[name=compareD]').click(function() {
			var state = "${getState}";
			if(state == "TS"){
				$("#tgDistTargetComp").click();
			}else{
				$("#apDistTargetComp").click();
			}
			var val = $('input:radio[name=compareD]:checked').val();
		});
		
	function getLocationswiseleaderCadreInfo1status(scpId,scp,stId,targetDiv,status){
		var scopeId = scpId; // 2 -- District 3 -- Constituency
		var scope = scp;
		var stateId = stId; // 1-- AP 2-- TS
		$(targetDiv).html('<img style="margin-left: 180px;margin-top: 101px;" id="ajaxImgStyle" src="images/icons/loading.gif"/>');
		$(targetDiv+"smry").html("");
		if(scopeId == 0){
			$("#errStatusDiv").html("Select Scope").css("color","red");
			return;
		}
		//$("#ajaxImgStyle").show();
		var jObj = {
			type : scope,
			stateId:stateId,
			task:"mainLevel"
		}
		
		var constant = "1"
		if(targetDiv == "#leaderDataDiv2"){
			constant = "2"
		}
		$.ajax({
          type:'GET',
          url: 'getLocationWiseAsOfNowDetailsAction.action',
         data : {task:JSON.stringify(jObj)} ,
        }).done(function(result){
				//$("#ajaxImgStyle").hide();
				var str='';
				var str1='';
				str1+='<div>';
						str1+='<table class="summary">';
							str1+="<tr>";
								str1+="<th class='best'> VERY GOOD </th>";
								str1+="<th class='good'> GOOD </th>";
								str1+="<th class='ok'> OK </th>";
								str1+="<th class='poor'> POOR </th>";
								str1+="<th class='worst'> VERY POOR </th>";
							str1+="</tr>";
							str1+="<tr>";
								if(scopeId==2){
									str1+="<td attr='district' attrst='Best' class='statusBsd best' title='Click to View Districts with Status Very Good'> <div class='cCodeDiv' style='background-color:green;'/>"+result[0].bestCount+"</td>";
									str1+="<td attr='district' attrst='Good' class='statusBsd good' title='Click to View Districts with Status Good'> <div class='cCodeDiv' style='background-color:lightgreen;'/>"+result[0].goodCount+"</td>";
									str1+="<td attr='district' attrst='Ok' class='statusBsd ok' title='Click to View Districts with Status Ok'> <div class='cCodeDiv' style='background-color:yellow;'/>"+result[0].okCount+"</td>";
									str1+="<td attr='district' attrst='Poor' class='statusBsd poor' title='Click to View Districts with Status Poor'><div class='cCodeDiv' style='background-color:orange;'/>"+result[0].poorCount+"</td>";
									str1+="<td attr='district' attrst='Worst' class='statusBsd worst' title='Click to View Districts with Status Very Poor'><div class='cCodeDiv' style='background-color:#C43C35;'/>"+result[0].worstCount+"</td>";
								}else{
									str1+="<td attr='constituency' attrst='Best' class='statusBsc best' title='Click to View Constituencies with Status Very Good'> <div class='cCodeDiv' style='background-color:green;'/>"+result[0].bestCount+"</td>";
									str1+="<td attr='constituency' attrst='Good' class='statusBsc good' title='Click to View Constituencies with Status Good'> <div class='cCodeDiv' style='background-color:lightgreen;'/>"+result[0].goodCount+"</td>";
									str1+="<td attr='constituency' attrst='Ok' class='statusBsc ok' title='Click to View Constituencies with Status Ok'> <div class='cCodeDiv' style='background-color:yellow'/>"+result[0].okCount+"</td>";
									str1+="<td attr='constituency' attrst='Poor' class='statusBsc poor' title='Click to View Constituencies with Status Poor'> <div class='cCodeDiv' style='background-color:orange;'/>"+result[0].poorCount+"</td>";
									str1+="<td attr='constituency' attrst='Worst' class='statusBsc worst' title='Click to View Constituencies with Status Very Poor'> <div class='cCodeDiv' style='background-color:#C43C35;'/>"+result[0].worstCount+"</td>";
								}
							str1+="</tr>";
						str1+='</table>';
					str1+='</div>';
					str+='<table class="table table-bordered" id="'+constant+'tabledata1">';
					str+='<thead><tr>';
					str+='<th>'+scope+'</th>';
					str+='<th>Target Cadres</th>';
					str+='<th>Registered Cadres</th>';
					//str+='<th>Difference</th>';
					str+='<th>% of Register cadres</th>';
					str+='<th>Cadre Details</th>';
					str+='</tr></thead>';
					str+='<tbody>';
					for(var i in result){
						if(status == result[i].colorStatus){
							str+='<tr>';
							if(scopeId == 2)
						{
							str+='<td><a href="javascript:{}" onclick="getDistrictWiseAgeGenderCasteCount('+ result[i].id+ ',\''+ result[i].name+ '\')"> '+result[i].name+' </a></td>';
						}
						else
						{
							str+='<td><a href="javascript:{}" onclick="getConstituencyWiseAgeGenderCasteCount('+ result[i].id+ ',\''+ result[i].name+ '\')"> '+result[i].name+' </a></td>';
						}
							str+='<td>'+result[i].targetCadres+'</td>';
							if(result[i].totalRecords==null){
								str+='<td>-</td>';
							}else{
							str+='<td>'+result[i].totalRecords+'</td>';
							}
							//str+='<td>'+result[i].difference+'</td>';
							if(result[i].percentage==null){
								str+='<td style="background-color: #e77c79;>-</td>';
							}else{
								var colorStatus = result[i].colorStatus;
								if(colorStatus=="Best"){
									str+='<td style="background-color: green;">'+result[i].percentage+'</td>';
								}
								if(colorStatus=="Good"){
									str+='<td style="background-color:lightgreen">'+result[i].percentage+'</td>';
								}
								if(colorStatus=="Ok"){
									str+='<td style="background-color:yellow">'+result[i].percentage+'</td>';
								}
								if(colorStatus=="Poor"){
									str+='<td style="background-color:orange">'+result[i].percentage+'</td>';
								}
								if(colorStatus=="Worst"){
									str+='<td style="background-color:#C43C35">'+result[i].percentage+'</td>';
								}
								if(scopeId == 2)
								{
									str+='<td> <i style="cursor:pointer;" onclick="getCadreDetails('+ result[i].id+ ',\'District\')"  title="Click here to view details" class=" icon-eye-open"></i></td> ';
								}
								else
								{
									str+='<td> <i style="cursor:pointer;" onclick="getCadreDetails('+ result[i].id+ ',\'constituency\')"  title="Click here to view details" class=" icon-eye-open"></i></td> ';
								}
							}
							str+='</tr>';
						}
					}
					str+='</tbody>';
					str+='</table>';
					$(targetDiv).html(str);
					$(targetDiv+"smry").html(str1);
					$("#"+constant+"tabledata1").dataTable({
						"iDisplayLength": -1,
						"sScrollY" : "200",
						"aLengthMenu": [[20, 50, 100, -1], [20, 50, 100, "All"]]
					});
				 
				 if(targetDiv == "#leaderDataDiv1"){
					$("#leaderDataDiv1 .dataTables_length").hide();
				 }else{
					$("#leaderDataDiv2 .dataTables_length").hide();
				 }
				 
				 
			});
		}
   
		function getLocationswiseleaderCadreInfo2status(scpId,scp,stId,targetDiv,fromTask,status){
			//var date = $.datepicker.formatDate('yy-mm-dd', new Date());
			
			
			
			$(targetDiv+"smry").html("");
			var scopeId = scpId;
			var scope = scp;
			var stateId = stId;
			$(targetDiv).html('<img style="margin-left: 180px;margin-top: 101px;" id="ajaxImgStyle" src="images/icons/loading.gif"/>');
			if(scopeId == 0){
				$("#errStatusDiv").html("Select Scope").css("color","red");
				return;
			}
			
			var constant = "1"
			if(targetDiv == "#leaderDataDiv2"){
				constant = "2"
			}
			
			$("#ajaxImgStyle").show();
			var jObj = {
				type : scope,
				stateId:stateId,
				//date:date,
				fromTask:fromTask,
				task:"mainLevel"
			}
			$.ajax({
			  type:'GET',
			  url: 'getLocationWiseToDayDetailsAction.action',
			  data : {task:JSON.stringify(jObj)} ,
            }).done(function(result){
				//$("#ajaxImgStyle").hide();
				var str='';
				var str1 = '';
					str1+='<div>';
						str1+='<table class="summary">';
							str1+="<tr>";
								str1+="<th class='best'>  VERY GOOD </th>";
								str1+="<th class='good'> GOOD </th>";
								str1+="<th class='ok'> OK </th>";
								str1+="<th class='poor'> POOR </th>";
								str1+="<th class='worst'> VERY POOR </th>";
							str1+="</tr>";
							str1+="<tr>";
								if(scopeId==2){
									str1+="<td attr='district' attrst='Best' class='statusBsd best' title='Click to View Districts with Status Very Good'> <div class='cCodeDiv' style='background-color:green;'/> "+result[0].bestCount+"</td>";
									str1+="<td attr='district' attrst='Good' class='statusBsd good' title='Click to View Districts with Status Good'> <div class='cCodeDiv' style='background-color:lightgreen;'/>"+result[0].goodCount+"</td>";
									str1+="<td attr='district' attrst='Ok' class='statusBsd ok' title='Click to View Districts with Status Ok'> <div class='cCodeDiv' style='background-color:yellow;'/>"+result[0].okCount+"</td>";
									str1+="<td attr='district' attrst='Poor' class='statusBsd poor' title='Click to View Districts with Status Poor'><div class='cCodeDiv' style='background-color:orange;'/>"+result[0].poorCount+"</td>";
									str1+="<td attr='district' attrst='Worst' class='statusBsd worst' title='Click to View Districts with Status Very Poor'> <div class='cCodeDiv' style='background-color:#C43C35;'/>"+result[0].worstCount+"</td>";
								}else{
									str1+="<td attr='constituency' attrst='Best' class='statusBsc best' title='Click to View Constituencies with Status Very Good'> <div class='cCodeDiv' style='background-color:green;'/>"+result[0].bestCount+"</td>";
									str1+="<td attr='constituency' attrst='Good' class='statusBsc good' title='Click to View Constituencies with Status Good'> <div class='cCodeDiv' style='background-color:lightgreen;'/>"+result[0].goodCount+"</td>";
									str1+="<td attr='constituency' attrst='Ok' class='statusBsc ok' title='Click to View Constituencies with Status Ok'> <div class='cCodeDiv' style='background-color:yellow;'/>"+result[0].okCount+"</td>";
									str1+="<td attr='constituency' attrst='Poor' class='statusBsc poor' title='Click to View Constituencies with Status Poor'> <div class='cCodeDiv' style='background-color:orange;'/>"+result[0].poorCount+"</td>";
									str1+="<td attr='constituency' attrst='Worst' class='statusBsc worst' title='Click to View Constituencies with Status Very Poor'><div class='cCodeDiv' style='background-color:#C43C35;'/>"+result[0].worstCount+"</td>";
								}
							str1+="</tr>";
						str1+='</table>';
					str1+='</div>';
					str+='<table class="table table-bordered" id="'+constant+'tabledata2">';
					str+='<thead><tr>';
					str+='<th>'+scope+'</th>';
					str+='<th>Target Cadres</th>';
					str+='<th>Registered Cadres</th>';
					//str+='<th>Difference</th>';
					str+='<th>% of Register cadres</th>';
					str+='<th>Cadre Details</th>';
					str+='</tr></thead>';
					str+='<tbody>';
					for(var i in result){
						if(status == result[i].colorStatus){
							str+='<tr>';
							if(scopeId == 2)
						{
							str+='<td><a href="javascript:{}" onclick="getDistrictWiseAgeGenderCasteCount('+ result[i].id+ ',\''+ result[i].name+ '\')"> '+result[i].name+' </a></td>';
						}
						else
						{
							str+='<td><a href="javascript:{}" onclick="getConstituencyWiseAgeGenderCasteCount('+ result[i].id+ ',\''+ result[i].name+ '\')"> '+result[i].name+' </a></td>';
						}
							str+='<td>'+result[i].targetCadres+'</td>';
							if(result[i].totalRecords==null){
								str+='<td>-</td>';
							}else{
							str+='<td>'+result[i].totalRecords+'</td>';
							}
							//str+='<td>'+result[i].difference+'</td>';
							 if(result[i].percentage==null){
								str+='<td style="background-color: #e77c79;>-</td>';
							}else{
								var colorStatus = result[i].colorStatus;
								if(status == colorStatus){
									if(colorStatus=="Best"){
										str+='<td style="background-color: green;">'+result[i].percentage+'</td>';
									}
									if(colorStatus=="Good"){
										str+='<td style="background-color:lightgreen">'+result[i].percentage+'</td>';
									}
									if(colorStatus=="Ok"){
										str+='<td style="background-color:yellow">'+result[i].percentage+'</td>';
									}
									if(colorStatus=="Poor"){
										str+='<td style="background-color:orange">'+result[i].percentage+'</td>';
									}
									if(colorStatus=="Worst"){
										str+='<td style="background-color:#C43C35">'+result[i].percentage+'</td>';
									}
									if(scopeId == 2)
									{
										str+='<td> <i style="cursor:pointer;" onclick="getCadreDetails('+ result[i].id+ ',\'District\')"  title="Click here to view details" class=" icon-eye-open"></i></td> ';
									}
									else
									{
										str+='<td> <i style="cursor:pointer;" onclick="getCadreDetails('+ result[i].id+ ',\'constituency\')"  title="Click here to view details" class=" icon-eye-open"></i></td> ';
									}
								}
							}
								
							str+='</tr>';
						}
					}
					str+='</tbody>';
					str+='</table>';
					$(targetDiv).html(str);
					$(targetDiv+"smry").html(str1);
					$("#"+constant+"tabledata2").dataTable({
						"iDisplayLength": -1,
						"sScrollY" : "200",
						"aLengthMenu": [[20, 50, 100, -1], [20, 50, 100, "All"]]
			     });
				 
				 if(targetDiv == "#leaderDataDiv1"){
					$("#leaderDataDiv1 .dataTables_length").hide();
				 }else{
					$("#leaderDataDiv2 .dataTables_length").hide();
				 }
			});
		}
		
		$(".refreshCon").click(function(){
			$("#apConstTargetComp").addClass("btn-success");
			$("#tgConstTargetComp").removeClass("btn-success");
			
			$("#todayCId").attr("checked","checked");
			
			var state = "${getState}";
			if(state == "TS"){
				getLocationswiseleaderCadreInfo2(3,"Constituency",2,"#leaderDataDiv2","today");
			}else{
				getLocationswiseleaderCadreInfo2(3,"Constituency",1,"#leaderDataDiv2","today");
			}
		});
		$(".refreshDist").click(function(){
			$("#apDistTargetComp").addClass("btn-success");
			$("#tgDistTargetComp").removeClass("btn-success");
			$("#todayDId").attr("checked","checked");
			
			var state = "${getState}";
			if(state == "TS"){
				getLocationswiseleaderCadreInfo2(2,"District",2,"#leaderDataDiv1","today");
			}else{
				getLocationswiseleaderCadreInfo2(2,"District",1,"#leaderDataDiv1","today");
			}
			
		});
		
		$(document).on('click',".statusBsd",function(){
			var attr = $(this).attr("attr");
			var attrst = $(this).attr("attrst");
			var stateId = 2;
			var state = $('input:button[name=distTargetBtn]:checked').val();
				if(state == "AP"){
					stateId = 1;
				}
			var reportType = $('input:radio[name=compareD]:checked').val();
			
			if(reportType == "overall"){
				getLocationswiseleaderCadreInfo1status(2,"District",stateId,"#leaderDataDiv1",attrst);
			}else{
				getLocationswiseleaderCadreInfo2status(2,"District",stateId,"#leaderDataDiv1",reportType,attrst);
			}
			
			
		});
		$(document).on('click',".statusBsc",function(){
			var attr = $(this).attr("attr");
			var attrst = $(this).attr("attrst");
			var stateId = 2;
			var state = $('input:button[name=constTargetBtn]:checked').val();
			
				if(state == "AP"){
					stateId = 1;
				}
			var reportType = $('input:radio[name=compareC]:checked').val();
			
			if(reportType == "overall"){
				getLocationswiseleaderCadreInfo1status(3,"Constituency",stateId,"#leaderDataDiv2",attrst);
			}else{
				getLocationswiseleaderCadreInfo2status(3,"Constituency",stateId,"#leaderDataDiv2",reportType,attrst);
			}
			
		
		});
		
		
		function popUpForInformation(){
			var str = "";
				$("#informationWindowDIV").dialog({
					resizable:false,
					title:'Color Codes Information ',
					height: 'auto',
					width:'300',
					top:250,
					left:100,
					modal: true
				});
				str+='<table class="info">';
				str+='<tr>';
						str+='<td><div style="height:13px;width:13px;background-color:green;"/></td>';
						str+='<td> >120% </td><td> Very Good </td>';
				str+='</tr>';
				str+='<tr>';
						str+='<td><div style="height:13px;width:13px;background-color:lightgreen;"/></td>';
						str+='<td>   100-120% </td><td> Good</td>';
				str+='</tr>';
				str+='<tr>';
						str+='<td><div style="height:13px;width:13px;background-color:yellow;"/></td>';
						str+='<td>   75-100%  </td><td> Ok</td>';
				str+='</tr>';
				str+='<tr>';
						str+='<td><div style="height:13px;width:13px;background-color:orange;"/></td>';
						str+='<td>  50-75% </td><td> Poor</td>';
				str+='</tr>';
				str+='<tr>';
						str+='<td><div style="height:13px;width:13px;background-color:#C43C35;"/></td>';
						str+='<td>  <50% </td><td> Very Poor </td>';
				str+='</tr>';
				str+='</table>';	
			$("#informationWindowInner").html(str);
		}
		function getBoothWiseTargetInfo(id){
		    $("#boothWiseInfoWindowDIV").dialog({
					resizable:false,
					title:'Booth Wise Target Information',
					height: '500',
					width:'800',
					top:250,
					left:100,
					modal: true
				});
				$("#boothWiseInfoWindowInner").html('<img src="images/Loading-data.gif" style="margin-left:325px;margin-top:120px;width:70px;height:60px;">');
			var jObj = {
				constituencyId : id
			}
			$.ajax({
			  type:'GET',
			  url: 'getLocationBoothWisDetailsAction.action',
			  data : {task:JSON.stringify(jObj)} ,
            }).done(function(result){
			   
			});
		}
	  <c:if test="${fn:contains(sessionScope.USER.entitlements, 'Leader_Cadre_DashBoard' ) || fn:contains(sessionScope.USER.entitlements, 'CADRE_2014_MP' ) }"> 
		  var state = "${getState}";
		  if(state == "TS"){
			getLocationswiseleaderCadreInfo2(3,"Constituency",2,"#leaderDataDiv2","today");
			getLocationswiseleaderCadreInfo2(2,"District",2,"#leaderDataDiv1","today");
		  }else{
			getLocationswiseleaderCadreInfo2(3,"Constituency",1,"#leaderDataDiv2","today");
			getLocationswiseleaderCadreInfo2(2,"District",1,"#leaderDataDiv1","today");
		  }
		  
	  </c:if>
	    <c:if test="${not fn:contains(sessionScope.USER.entitlements, 'Leader_Cadre_DashBoard' ) && not fn:contains(sessionScope.USER.entitlements, 'CADRE_2014_MP' )}"> 
		getDistrictWiseCompletedPercentage(0,1);
		getAssemblyWiseCompletedPercentage(0,1);
	   </c:if>
	
	function getGHMCDetails(typeId){
		if(typeId == 'today'){
			$("#ghmcTodayDiv").html('<img src="images/icons/search.gif" style="margin-left:60px;margin-top:12px;" />'); 
		}
		else if(typeId == 'total'){
			$("#ghmcTotalDiv").html('<img src="images/icons/search.gif" style="margin-left:60px;margin-top:12px;" />'); 
		}		
			var jObj = {
				type:typeId
			}
			$.ajax({
			  type:'GET',
			  url: 'getGHMCRegisteredCountsDetailsAction.action',
			  data : {task:JSON.stringify(jObj)} ,
            }).done(function(result){
			
			if(typeId == 'today'){
				$("#ghmcTodayDiv").html('<ul class="unstyled" style="background-color:#EAEAEA;"> <li>  <small>GHMC Drive Tab - '+result[0].ghmcTabCount+'</small>  </li> <li><small> GHMC Drive Web - '+result[0].ghmcWebCount+'</small></li> ');
				}
				else if(typeId == 'total'){
					$("#ghmcTotalDiv").html('<ul class="unstyled" style="background-color:##DBF1E4"> <li><small>GHMC Drive Tab - '+result[0].ghmcTabCount+'</small></li><li><small>GHMC Drive Web - '+result[0].ghmcWebCount+'</small></li> ');
				}
				
			});
	}
	 $(".show2016DashBoard").click(function(){
			window.location.replace('newCadreDashBoard2016Action.action');
	   });
	
</script>

</body>
</html>
