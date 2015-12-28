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
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css" href="css/cadreRegistrationCSS/animate.css" >
	<!--<link rel="stylesheet" type="text/css" href="dist/css/bootstrap.css">-->
	<link rel="stylesheet" type="text/css" href="styles/bootstrapInHome/bootstrap.css"/> 
    <link rel="stylesheet" type="text/css" href="js/scrollator/fm.scrollator.jquery.css">	
	<script type="text/javascript" src="js/scrollator/fm.scrollator.jquery.js"></script>
	<script type="text/javascript" src="js/icheck/icheck.min.js"></script>
	<script type="text/javascript" src="js/jquery.dataTables.js"></script>
	<link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"/>
	<script type="text/javascript" src="js/jQuery/js/jquery-ui-1.8.5.custom.min.js"></script>
    <script type="text/javascript" src="js/bootstrap3/bootstrap.js"></script>		
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
 var stateId = '${stateId}';

</script>
<div class="container m_top10">

		<!-- Title Row -->
		<div class="row-fluid" id="fadeInDown">
			<div class="span12 well well-small  border-radius-0 mb-10 " style="background:#ffffff;">
				<h3 class="text-center text-uppercase" id="heading">2014 Cadre Admin Dashboard</h3>
			</div>
		</div><!-- Title Row End-->
		
		<!-- Members Registered Previous Row -->
		<div class="row-fluid " id="PreviousmembersCount">
		  <c:if test="${fn:contains(sessionScope.USER.entitlements, 'Leader_Cadre_DashBoard' ) }"> 
			<div class="span12 show-grid well well-small border-radius-0 mb-10" id="basicDiv">
				   
			</div>
		  </c:if>
		</div><!-- Members Registered Previous Row -->
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
				<h4> Constituency Registered Cadre  
				<!--
				<i class="icon-info-sign" onClick="popUpForInformation()" title="Click To View the Color Code Information" style="margin-top: 4px;margin-left:10px;cursor:pointer;"></i>
				-->
				</h4>
			
				<!--<div style="padding:5px;">
					<input type="radio" class="typeRd" id="todayCId" name="compareC" value="today" checked="true" style="margin-top:0px;"/><span style="margin-right:10px;"> TODAY</span>
					<input type="radio" class="typeRd" id="asOfNowCId" name="compareC" value="asoftoday" style="margin-top:0px;"/><span style="margin-right:10px;"> AS OF TODAY </span>
					<input type="radio" class="typeRd" id="overAllCId" name="compareC" value="overall" style="margin-top:0px;"/><span style="margin-right:10px;"> OVER ALL </span>
					
					<div class="btn-group pull-right">
					<input type="button" class="btn btn-mini btn-success apele" value="AP" name="constTargetBtn" id="apConstTargetComp" checked="checked">AP</input>
					<input type="button" class="btn btn-mini tsele" value="TS" name="constTargetBtn" id="tgConstTargetComp" >TS</input>
				</div>
				</div>-->
				<div id="leaderDataDiv2smry" style="margin-left:-30px;"  class="m_top10"></div>
				<div id="leaderDataDiv2" class="height-300 scrollable_div" style="margin-top: -1px">
					<img style="margin-left: 180px;margin-top: 101px;" id="ajaxImgStyle" src="images/icons/loading.gif"/>
				</div>
			</div>
			
			<div class="span6 show-grid well well-small border-radius-0 mb-10 fadeInUp " style="margin-left:20px;" >
				<h4> District Registered Cadre  
				<!--
				<i class="icon-info-sign" onClick="popUpForInformation()" title="Click To View the Color Code Information" style="margin-top: 4px;margin-left:10px;cursor:pointer;"></i>
				
				-->
				</h4>
				
				<!--<div style="padding:5px;">
					<input type="radio" class="typeRd" id="todayDId" name="compareD" value="today" checked="true" style="margin-top:0px;"/><span style="margin-right:10px;"> TODAY</span>
					<input type="radio" class="typeRd" id="asOfNowDId" name="compareD" value="asoftoday" style="margin-top:0px;"/><span style="margin-right:10px;"> AS OF TODAY</span>
					<input type="radio" class="typeRd" id="overAllDId" name="compareD" value="overall" style="margin-top:0px;"/><span style="margin-right:10px;"> OVER ALL</span>
					
					<div class="btn-group pull-right">
					<input type="button" value="AP" class="btn btn-mini btn-success apele" name="distTargetBtn" id="apDistTargetComp" checked="checked">AP</input>
					<input type="button" value="TS" class="btn btn-mini tsele" name="distTargetBtn" id="tgDistTargetComp">TS</input>
				</div>
				</div>-->
				<div id="leaderDataDiv1smry" style="margin-left:-30px;"  class="m_top10"></div>
				<div id="leaderDataDiv1" class="height-300 scrollable_div" style="margin-top: -1px">
					<img style="margin-left: 180px;margin-top: 101px;" id="ajaxImgStyle" src="images/icons/loading.gif"/>
				</div>
			</div>
		</div>
		</c:if>
		<!--<div class="row-fluid fadeInUp">
			<div class="span12 show-grid well well-small border-radius-0 mb-10" >
				<!--<iframe src="https://www.google.com/maps/embed?pb=!1m10!1m8!1m3!1d3929013.1516925395!2d79.7399875!3d15.912899799999996!3m2!1i1024!2i768!4f13.1!5e0!3m2!1sen!2s!4v1412166071097" width="580" height="300" frameborder="0" style="border:0"></iframe>
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
			
			 ReCently Registered Block 
			<div class="span7 show-grid well well-small border-radius-0 pad-0" style="margin-left:20px;min-height:370px;">
				<h4 style="padding :10px"><i class="icon-user" style="margin-top: 4px;"></i> &nbsp;Recently Registered <i class="icon-refresh" style="margin-top: 4px;margin-left:10px;cursor:pointer;" onclick="getRecentlyRegisteredCadresInfo(0,true);"></i> </h4>
				<div id="recentRegisterCadresDiv" style="margin-top:15px;"><img style="margin-top:180px;margin-left: 124px;" src="images/icons/loading.gif"/></div>
					
			</div><!-- ReCently Registered Block END 
		</div>-->
		
		
		
	
		
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
		
		var jObj = {
			type : scope,
			stateId:stateId,
			task:"locationWiseInfoForState"
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
				
				var str='';
				
					str+='<table class="table table-bordered" id="'+constant+'tabledata1">';
					str+='<thead><tr>';
					str+='<th>'+scope+'</th>';
				
					str+='<th>Registered Cadres</th>';
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
							
							if(result[i].totalRecords==null){
								str+='<td>-</td>';
							}else{
							str+='<td>'+result[i].totalRecords+'</td>';
							}
							if(scopeId == 2)
								{
									str+='<td> <i style="cursor:pointer;" onclick="getCadreDetails('+ result[i].id+ ',\'District\')"  title="Click here to view details" class=" icon-eye-open"></i></td> ';
								}
								else
								{
									str+='<td> <i style="cursor:pointer;" onclick="getCadreDetails('+ result[i].id+ ',\'constituency\')"  title="Click here to view details" class=" icon-eye-open"></i></td> ';
								}
							str+='</tr>';
						
					}
					str+='</tbody>';
					str+='</table>';
					$(targetDiv).html(str);
					
					/*$("#"+constant+"tabledata1").dataTable({
						"iDisplayLength": -1,
						"aLengthMenu": [[20, 50, 100, -1], [20, 50, 100, "All"]]
					});*/
				 
				 if(targetDiv == "#leaderDataDiv1"){
					$("#leaderDataDiv1 .dataTables_length").hide();
				 }else{
					$("#leaderDataDiv2 .dataTables_length").hide();
				 }
			});
		}
   
	
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
	  
	
	function getStateName()
	{
	
		var jobj = 
		{
		stateId :stateId,
		task:"getState"
		}
			$.ajax({
			  type:'GET',
			  url: 'getStateNameAction.action',
			  data : {task:JSON.stringify(jobj)} ,
            }).done(function(result){
			   if(result != null)
			   $("#heading").html(result + " Cadre Admin Dashboard");
			});
		
	}
	function getBasicInfo()
	{
	$("#basicDiv").html('<img style="margin-left: 180px;margin-top: 101px;" id="ajaxImgStyle" src="images/icons/loading.gif"/>');
		var jobj = 
		{
		stateId :stateId,
		task:"basicRegistrationInfo"
		}
			$.ajax({
			  type:'GET',
			  url: 'getDashBoardBasicInfoAction.action',
			  data : {task:JSON.stringify(jobj)} ,
            }).done(function(result){
				var str ='';
			
			    str+='<table class="table table-bordered border-radius-0 mb-0 Previousmembercount table-hover" >';
				str+='<tbody>';
				str+='<tr >';
				str+='<td><h2>&nbsp;&nbsp;</h2><img class="pull-right" src="images/${stateId}.png" style="margin-top: -40px;"></td>';
				str+='<td><div ><h2>'+result.totalCount+'</h2><p>Total Constituencies</p></div></td>';
				str+='<td><div ><h2>'+result.votersCount+'</h2><p>Registration Started Constituencies</p></div></td>';
				 str+='<td><div ><h2>'+result.apCount+'</h2><p>Members Registered in<br><span class="text-red">2014</span></p></div></td>';
				str+='</tr>';
				 str+='</tbody>';
				 str+='<table>';
			   $("#basicDiv").html(str);
			});
		
	}
</script>
<script>
getStateName();
getBasicInfo();
getLocationswiseleaderCadreInfo1status(3,"Constituency",stateId,"#leaderDataDiv2",'');
getLocationswiseleaderCadreInfo1status(2,"District",stateId,"#leaderDataDiv1",'');

</script>
</body>
</html>
