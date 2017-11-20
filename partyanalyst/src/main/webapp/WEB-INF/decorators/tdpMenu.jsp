<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ResourceBundle;" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html lang="en" class="no-js">
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
<meta name="viewport" content="width=device-width, initial-scale=1.0"> 
<title><decorator:title default="TDP Party's Election Analysis &amp; Management Platform"/></title>
<link rel="icon" type="image/png" href="https://mytdp.com/dist/img/logo.png">
<script src="dist/js/jquery-1.11.2.min.js"></script>
<!--<link href="dist/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="fullexpandcollapse/custom.css" rel="stylesheet" type="text/css">-->
<link rel="stylesheet" href="fullexpandcollapse/jquery.multilevelpushmenu_red.css">
<link rel="stylesheet" href="fullexpandcollapse/fullexpandcollapse.css">
<link rel="stylesheet" href="dist/css/font-awesome.css">
<link rel="stylesheet" href="css/MenuResponsive.css">

<!--YUI SCRIPT-->
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yahoo-dom-event/yahoo-dom-event.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/connection/connection-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/datasource/datasource-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/autocomplete/autocomplete-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/element/element-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/container/container-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/menu/menu-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/button/button-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/paginator/paginator-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/datatable/datatable-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/json/json-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/tabview/tabview-min.js"></script>

  <script>
		var Localization = { <%
		
		ResourceBundle rb = ResourceBundle.getBundle("common_Lables");
		String stateSelect = rb.getString("stateSelect");
		String distSelect = rb.getString("distSelect");
		String constSelect = rb.getString("constSelect");
		String assembly = rb.getString("assembly");
		String parliament = rb.getString("parliament");
		String localBody = rb.getString("localBodies");
		String electionTypeInHome = rb.getString("electionTypeInHome");
		String electionYearInHome = rb.getString("electionYearInHome");
		
		ResourceBundle resb = ResourceBundle.getBundle("global_ErrorMessages");
		String errorMsg = resb.getString("constTypeAlert");
%> }
	</script>
	
<style class="text/css">
	.levelHolderClass h2
	{
		margin-top:0px
	}
	header.eventsheader 
	{
		background-image:url("dist/img/header-footer.png");
		background-color:#fed501;
		background-size:100% auto;
		background-position:center bottom;
		background-repeat: no-repeat;
		background-origin: border-box;
		background-repeat: no-repeat;
		height: 71px;
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
<decorator:head/>
	</head>
    	<body>
    <header class="eventsheader">
	<div class="container">
        <div class="row">
            <div class="col-md-3 col-xs-6 col-sm-2 span2 logoR">
                <img src="dist/img/logo.png" class="img-responsive" alt="logo">
            </div>
            <div class="col-md-1 col-xs-1 col-sm-2 span1 cbnR">
                <img src="dist/img/CBN1.png" class="img-responsive" alt="cbn">
            </div>
            <div class="col-md-4 col-xs-7 col-sm-4 span5 text-center headingR" style="line-height:20px;">               
                 <p class="header-text display-style" id="mainheading" style="font-size:22px !important;color:#5c2d25 !important;margin-top:16px;margin-bottom:0px;text-align:center">TELUGU DESAM PARTY</p><p  style="text-align:center;color: rgb(92, 45, 37) ! important; font-size: 14px ! important;"  class="header-text display-style">Dare To Dream - Strive To Achieve</p>                
            </div>
            <div class="col-md-1 col-xs-1 col-sm-2 span1 ntrR"><img src="dist/img/NTR1.png" style="margin-top: 5px;" class="img-responsive" alt="ntr">  
            </div>
            <div class="col-md-3 col-xs-5 col-xs-offset-1 col-sm-2 col-sm-offset-0 span3 menuR">
			<c:if test="${sessionScope.loginStatus == 'out' && (sessionScope.hasFreeUserRole == true && sessionScope.hasPartyAnalystUserRole != true)}">
            	<p style="margin-top:1px;margin-bottom:0px;font-size:10px;color:#333;padding:0px;">Welcome, ${sessionScope.UserName} |</p> </c:if>
				<c:if test="${sessionScope.loginStatus == 'out' && sessionScope.hasPartyAnalystUserRole == true}">
				<p style="padding:0px;font-size:10px;color:#333;margin-top:1px;margin-bottom:0px;" class="adminCls">Welcome, ${sessionScope.UserName} </c:if>
				<c:if test="${sessionScope.USER.isAdmin == 'true' && fn:contains(sessionScope.USER.entitlements, 'APPLICATION_ADMIN_ENTITLEMENT' )}">
						| <a style="color:green !important;" href="<c:out value="${pageContext.request.contextPath}/adminUpload.action" />" >Admin</a>
						|
				</c:if>
				<c:if test="${sessionScope.loginStatus == null || sessionScope.loginStatus == 'in'}">
				
				<a id="loginId" href="javascript:{}" onClick="openDialogForLoginWindow()" class="btn btn-mini"><i class="icon-user"></i><b> Login</b></a>
					<!--<span>|</span>
					<a href="<c:out value="${pageContext.request.contextPath}/freeUserRegistration.action" />">Register</a>-->
					
				</c:if>
				</p>
				
				<a id="trigger" aria-expanded="false" data-toggle="dropdown" class="dropdown-toggle btn btn-default btn-xs fullcollapse" href="#" style="margin-top:10px">
					Menu <i class="glyphicon glyphicon-align-justify icon-align-justify"></i>
                </a>
				<c:if test="${fn:contains(sessionScope.USER.entitlements, 'LEADER_OCCASIONS_ENTITLEMENT' )}">
					<a href="birthDayAction.action" style="position:relative">
						<span class="birthdayCount" > <b> ${sessionScope.birthDayCount} </b></span>
						<img src="img/birthdayIcon.png" class="birthdaysIcon" title="Today BirthDay Count"/>
						<!--<i class="fa fa-birthday-cake text-danger" style="font-size: 18px; margin-top: 10px;margin-left:10px;" title="Today Birthdays"></i>-->
					</a>
				</c:if>
            </div>
        </div>       
    </div>
</header>
	<div class="container">
		<div class="" id="menu" style="z-index:9999">
			<nav class="">
                <h2><i class="fa fa-reorder line_heightDiv" style="color:#5c2d25;"></i>&nbsp;</h2>
					<ul>
						
						<c:if test="${ sessionScope.USER.isAdmin == 'true' || fn:contains(sessionScope.USER.entitlements, 'CADREDASHBOARD' )}">
							<li>
								<!--  <a href="home.action"><i class="fa fa-home"></i><span>&nbsp;&nbsp;Home</span></a> -->
								<a href="dashBoardAction.action"><i class="fa fa-home"></i><span>&nbsp;&nbsp;Home</span></a>
							 </li>
							</c:if>
							<c:if test="${sessionScope.loginStatus == 'out' && sessionScope.hasPartyAnalystUserRole == true}">   
							<c:if test="${fn:contains(sessionScope.USER.entitlements, 'PARTY_PERFORMANCE_REPORT' ) || fn:contains(sessionScope.USER.entitlements, 'ELECTION_COMPARISION_REPORT' ) ||
							fn:contains(sessionScope.USER.entitlements, 'PARTY_RESULTS_REPORT' ) || fn:contains(sessionScope.USER.entitlements, 'ELECTION_RESULTS_ANALYSIS_REPORT' )  ||
							fn:contains(sessionScope.USER.entitlements, 'CENSUS_REPORT_ENTITLEMENT' ) || fn:contains(sessionScope.USER.entitlements, 'PARTY_STRENGTH_AND_WEAKNESS' ) ||
							fn:contains(sessionScope.USER.entitlements, 'VOTER_ANALYSIS' )  || fn:contains(sessionScope.USER.entitlements, 'PARTY_BOOTHWISE_RESULTS_REPORT' ) ||
							fn:contains(sessionScope.USER.entitlements, 'CROSS_VOTING_REPORT' ) || fn:contains(sessionScope.USER.entitlements, 'PARTY_BOOTHWISE_RESULTS_REPORT' ) }">
                        <li>
							<a href="#"><i class="fa fa-pie-chart"></i><span>&nbsp;&nbsp;Analysis</span></a>
								<h2><i class="fa fa-pie-chart line_heightDiv"></i>Analysis</h2>
						<ul>
							<c:if test="${fn:contains(sessionScope.USER.entitlements, 'PARTY_PERFORMANCE_REPORT' ) || fn:contains(sessionScope.USER.entitlements, 'ELECTION_COMPARISION_REPORT' ) ||
							fn:contains(sessionScope.USER.entitlements, 'PARTY_RESULTS_REPORT' ) || fn:contains(sessionScope.USER.entitlements, 'ELECTION_RESULTS_ANALYSIS_REPORT' )  ||
							fn:contains(sessionScope.USER.entitlements, 'CENSUS_REPORT_ENTITLEMENT' ) || fn:contains(sessionScope.USER.entitlements, 'PARTY_STRENGTH_AND_WEAKNESS' ) }">
                            <li>
                                 <a href="#"><i class="fa fa-area-chart"></i><span>&nbsp;&nbsp;Election Analysis</span></a>
									<h2><i class="fa fa-area-chart line_heightDiv"></i>Election Analysis</h2>
								<ul>
								<c:if test="${fn:contains(sessionScope.USER.entitlements, 'PARTY_PERFORMANCE_REPORT' ) }">
									 <li>
										<a href="partyPerformanceMain.action"><i class="fa fa-mars-double"></i><span>&nbsp;&nbsp;Party Performance</span></a>
									</li></c:if>
									<c:if test="${fn:contains(sessionScope.USER.entitlements, 'ELECTION_COMPARISION_REPORT' ) }">
									<li>
										<a href="electionComparisonAction.action"><i class="fa fa-book"></i><span>&nbsp;&nbsp;Elections Comparison Report</span></a>
									</li></c:if>
									<c:if test="${fn:contains(sessionScope.USER.entitlements, 'PARTY_RESULTS_REPORT' ) }">
									<li>
										<a href="partyResultsCriteriaAction.action"><i class="fa fa-tachometer"></i><span>&nbsp;&nbsp;Party Results Report</span></a>
									</li></c:if>
									<c:if test="${fn:contains(sessionScope.USER.entitlements, 'ELECTION_RESULTS_ANALYSIS_REPORT' ) }">
									<li>
										<a href="electionResultsAnalysisAction.action"><i class="fa fa-map-signs"></i><span>&nbsp;Elections Results Analysis Report</span></a>
									</li></c:if>
									<c:if test="${fn:contains(sessionScope.USER.entitlements, 'CENSUS_REPORT_ENTITLEMENT' ) }">
									<li>
										<a href="censusReportAction.action"><i class="fa fa-balance-scale"></i><span>&nbsp;&nbsp;Elections vs Demographics</span></a>
									</li></c:if>
									<c:if test="${fn:contains(sessionScope.USER.entitlements, 'PARTY_STRENGTH_AND_WEAKNESS' ) }">
									<li>
										<a href="partyStrengthAction.action"><i class="fa fa-book"></i><span>&nbsp;&nbsp;Party Strengths / Weakness</span></a>
									</li></c:if>
								</ul>
							</li>
							</c:if>
						<c:if test="${fn:contains(sessionScope.USER.entitlements, 'VOTER_ANALYSIS' )  ||
						fn:contains(sessionScope.USER.entitlements, 'CROSS_VOTING_REPORT' ) || fn:contains(sessionScope.USER.entitlements, 'PARTY_BOOTHWISE_RESULTS_REPORT' ) }">
							<li>
                               <a href="#"><i class="fa fa-industry"></i><span>&nbsp;&nbsp;Politician Analysis</span></a>
								<h2><i class="fa fa-industry line_heightDiv"></i>Politician Analysis</h2>
								<ul>
								<c:if test="${fn:contains(sessionScope.USER.entitlements, 'VOTER_ANALYSIS' ) }">
								  <li>
									<a href="suggestiveModelAction.action"><i class="fa fa-eye"></i><span>&nbsp;&nbsp;Suggestive Modal</span></a>
								  </li></c:if>
								<c:if test="${fn:contains(sessionScope.USER.entitlements, 'PARTY_BOOTHWISE_RESULTS_REPORT' ) }">
								  <li>
									<a href="mandalPageSDetailAction.action"><i class="fa fa-group"></i><span>&nbsp;&nbsp;Mandal Voting Report</span></a>
								  </li></c:if>
								<c:if test="${fn:contains(sessionScope.USER.entitlements, 'CROSS_VOTING_REPORT' ) }">
								  <li>
									<a href="crossVotingReportInputAction.action"><i class="fa fa-area-chart"></i><span>&nbsp;Cross Voting Report</span></a>
								  </li></c:if>
								<c:if test="${fn:contains(sessionScope.USER.entitlements, 'PARTY_BOOTHWISE_RESULTS_REPORT' ) }">
								  <li>
									<a href="partyBoothResultAction.action"><i class="fa fa-barcode"></i><span>Constituency Booth Results Report</span></a>
								  </li>
								</c:if>
								</ul>
							</li>
                        </c:if>      
					 </ul>
                   </li>
					</c:if></c:if>
					<c:if test="${sessionScope.loginStatus == 'out' && sessionScope.hasPartyAnalystUserRole == true}">   
					<c:if test="${fn:contains(sessionScope.USER.entitlements, 'VOTER_ANALYSIS' )  || fn:contains(sessionScope.USER.entitlements, 'VOTER_SEARCH_AND_EDIT' ) || fn:contains(sessionScope.USER.entitlements, 'PROBLEM_MANAGEMENT_ENTITLEMENT' ) || fn:contains(sessionScope.USER.entitlements, 'CADRE_MANAGEMENT_ENTITLEMENT' ) || fn:contains(sessionScope.USER.entitlements, 'CALL_CENTER_ENTITLEMENT' ) || fn:contains(sessionScope.USER.entitlements, 'ADMIN_PAGE' ) || fn:contains(sessionScope.USER.entitlements, 'VOTER_DATA_TOOLS' ) || fn:contains(sessionScope.USER.entitlements, 'TRAINING_CAMP_ADMIN' ) || fn:contains(sessionScope.USER.entitlements, 'TRAINING_CAMP_SUPER_ADMIN' ) || fn:contains(sessionScope.USER.entitlements, 'TRAINING_CAMP_CALLER_ADMIN' ) || fn:contains(sessionScope.USER.entitlements, 'TRAINING_CAMP_CALLER' ) || fn:contains(sessionScope.USER.entitlements, 'TRAINING_CAMP_FEEDBACK_UPDATE_ENTITLEMENT' ) || fn:contains(sessionScope.USER.entitlements, 'CADRE_MEMBERSHIPCARD_DISPATCHER' ) || fn:contains(sessionScope.USER.entitlements, 'CADRE_MEMBERSHIPCARD_DISPATCHER_GROUP' ) ||  fn:contains(sessionScope.USER.entitlements, 'TDP_CADRE_SEARCH' ) || fn:contains(sessionScope.USER.entitlements, 'CADRE_SEARCH_ENT' ) || fn:contains(sessionScope.USER.entitlements, 'COMMITTEE_MGT' ) || fn:contains(sessionScope.USER.entitlements, 'TDP_COMMITTEE_ADMIN' ) || fn:contains(sessionScope.USER.entitlements, 'TDP_COMMITTEE_AREAWISE_ACCESS' ) || fn:contains(sessionScope.USER.entitlements, 'COMMITTEE_DETAILED_REPORT' ) || fn:contains(sessionScope.USER.entitlements, 'PARTY_ACTIVITY_UPDATE' ) || fn:contains(sessionScope.USER.entitlements, 'CADRE_COMMITTEE_MANAGEMENT' ) || fn:contains(sessionScope.USER.entitlements, 'TIRUPATHI_BYEELECTION' ) || fn:contains(sessionScope.USER.entitlements, 'NEW_LIVE_RESULTS' ) || fn:contains(sessionScope.USER.entitlements, 'CADREIVRDASHBOARD' ) || fn:contains(sessionScope.USER.entitlements, 'ACTIVITY_ENTRY_ENTITLEMENT') || fn:contains(sessionScope.USER.entitlements, 'ACTIVITIES_DASHBOARD_ENTITLEMENT')  || fn:contains(sessionScope.USER.entitlements, 'POLLING_MANAGEMENT_ENTITLEMENT') || fn:contains(sessionScope.USER.entitlements, 'POLLING_MANAGEMENT_ENTITLEMENT_ADMIN')}">
					 <li>
                        <a href="#"><i class="fa fa-wrench"></i><span>&nbsp;&nbsp;Management Tools</span></a>
                        <h2><i class="fa fa-wrench line_heightDiv"></i>Management Tools</h2>
                       <ul>
					   
					   <c:if test="${sessionScope.USER.isAdmin == 'true' || fn:contains(sessionScope.USER.entitlements, 'TRAINING_CAMP_ADMIN' ) || fn:contains(sessionScope.USER.entitlements, 'TRAINING_CAMP_SUPER_ADMIN' ) || fn:contains(sessionScope.USER.entitlements, 'TRAINING_CAMP_CALLER_ADMIN' ) || fn:contains(sessionScope.USER.entitlements, 'TRAINING_CAMP_CALLER' ) || fn:contains(sessionScope.USER.entitlements, 'TRAINING_CAMP_FEEDBACK_UPDATE_ENTITLEMENT' )}">
						 <li>
								<a href="#"><i class="fa fa-envelope ico-white"></i><span>&nbsp;&nbsp;Cadre Training</span></a>
								 <h2><i class="fa fa-envelope ico-white line_heightDiv"></i> Cadre Training </h2>
								 <ul>
								 <c:if test="${fn:contains(sessionScope.USER.entitlements, 'TRAINING_CAMP_ADMIN' ) || fn:contains(sessionScope.USER.entitlements, 'TRAINING_CAMP_SUPER_ADMIN' )}">
								 <li>
									<a href="trainingCenterDashBoardAction.action"><i class="fa fa-th-list ico-white"></i><span>&nbsp;&nbsp;Training Center Dashboard</span></a>
									</li></c:if>
									<c:if test="${fn:contains(sessionScope.USER.entitlements, 'TRAINING_CAMP_CALLER_ADMIN') ||
									fn:contains(sessionScope.USER.entitlements, 'TRAINING_CAMP_SUPER_ADMIN' )}">
									<li>
									<a href="callCenterTrainingAdmin.action"><i class="fa fa-th-large ico-white"></i><span>&nbsp;&nbsp;Caller Admin Dashboard</span></a>
									</li></c:if>
									<c:if test="${fn:contains(sessionScope.USER.entitlements, 'TRAINING_CAMP_CALLER' ) || fn:contains(sessionScope.USER.entitlements, 'TRAINING_CAMP_CALLER_ADMIN' ) || fn:contains(sessionScope.USER.entitlements, 'TRAINING_CAMP_SUPER_ADMIN' )}">
									 <li>
									<a href="campCallCenterTrainingAgentDashBoard.action"><i class="fa fa-dashboard ico-white"></i><span>&nbsp;&nbsp;Caller Dashboard</span></a>
									</li></c:if>
									<c:if test="${sessionScope.USER.isAdmin == 'true' ||  fn:contains(sessionScope.USER.entitlements, 'TRAINING_CAMP_FEEDBACK_UPDATE_ENTITLEMENT' ) || fn:contains(sessionScope.USER.entitlements, 'TRAINING_CAMP_SUPER_ADMIN' )}">
									 <li>
									<a href="trainingCampMainDashboard.action"><i class="fa fa-list-alt ico-white"></i><span>&nbsp;&nbsp;Training Feedback</span></a>
									</li></c:if>
								 </ul>
						</li></c:if>
						
					   <c:if test="${ sessionScope.USER.isAdmin == 'true' || fn:contains(sessionScope.USER.entitlements, 'CADRE_MEMBERSHIPCARD_DISPATCHER' ) || fn:contains(sessionScope.USER.entitlements, 'CADRE_MEMBERSHIPCARD_DISPATCHER_GROUP' ) ||  fn:contains(sessionScope.USER.entitlements, 'TDP_CADRE_SEARCH' ) || fn:contains(sessionScope.USER.entitlements, 'CADRE_SEARCH_ENT' ) || fn:contains(sessionScope.USER.entitlements, 'COMMITTEE_MGT' ) || fn:contains(sessionScope.USER.entitlements, 'CADRE_DELETE_ENTITLEMENT_GROUP') || fn:contains(sessionScope.USER.entitlements, 'CADRE_DELETE_ENTITLEMENT')}">
						 <li>
								<a href="#"><i class="fa fa-archive ico-white"></i><span>&nbsp;&nbsp;Cadre Search / Registration DashBoard</span></a>
								 <h2><i class="glyphicon glyphicon-tree-deciduous ico-white line_heightDiv"></i>&nbsp;&nbsp;Cadre Search</h2>
								 <ul>
								 <c:if test="${ sessionScope.USER.isAdmin == 'true' || fn:contains(sessionScope.USER.entitlements, 'TDP_CADRE_SEARCH') || fn:contains(sessionScope.USER.entitlements, 'COMMITTEE_MGT') ||  fn:contains(sessionScope.USER.entitlements, 'CADRE_SEARCH_ENT') || fn:contains(sessionScope.USER.entitlements, 'CADRE_DELETE_ENTITLEMENT_GROUP') || fn:contains(sessionScope.USER.entitlements, 'CADRE_DELETE_ENTITLEMENT')}"> 
									<li>
									<a href="cadreSearchPageAction.action"><i class="fa fa-search ico-white"></i><span>&nbsp;&nbsp;Cadre Search</span></a>
									</li></c:if>
									<c:if test="${ sessionScope.USER.isAdmin == 'true'  &&							fn:contains(sessionScope.USER.entitlements, 'VOTER_ANALYSIS' ) }"> 
									
									
										<li>
										<a href="cadreVoterSearchAction.action"><i class="fa fa-magnet ico-white"></i><span>&nbsp;&nbsp;Cadre Voter Search</span></a>
										</li>
									</c:if>
									<c:if test="${sessionScope.USER.isAdmin == 'true' && fn:contains(sessionScope.USER.entitlements, 'VOTER_ANALYSIS' )}">
								<c:if test="${ sessionScope.USER.isAdmin == 'true' || fn:contains(sessionScope.USER.entitlements, 'CADREDASHBOARD' )}">
	<li>
										<a href="#"><i class="fa fa-pencil ico-white"></i><span>&nbsp;&nbsp;Cadre Reports</span></a>
										 <h2><i class="fa fa-pencil ico-white line_heightDiv"></i>Cadre Reports</h2>
									
		<ul>
			<li>
		<c:if test="${ sessionScope.USER.isAdmin == 'true' || fn:contains(sessionScope.USER.entitlements, 'CADREDASHBOARD' )}">
						<li>		
							<a href="#"><i class="fa fa-pencil ico-white"></i><span>&nbsp;&nbsp;2014 Cadre Reports</span></a>
										 <h2><i class="fa fa-pencil ico-white line_heightDiv"></i>2014 Cadre Reports</h2>
										 
								<ul>
									<li>
										<a href="cadreRegistrationAmountReportAction.action"><i class="fa fa-briefcase ico-white"></i><span>&nbsp;&nbsp;2014 Cadre Reconcilation Report</span></a>
									</li>
									<li>
										<a href="leaderCadreDashBoardAction.action"><i class="fa fa-square-o ico-white "></i><span>&nbsp;&nbsp;Leader Cadre Report</span></a>
									</li>
									<li>
										<a href="cadreReportsAction.action"><i class="fa fa-filter ico-white"></i><span>&nbsp;&nbsp;Cadre Reports - 2014</span></a>
									</li>
									<li>
										<a href="misReportAction.action"><i class="fa fa-globe ico-white"></i><span>&nbsp;&nbsp;MIS Reports</span></a>
									</li>
								</ul>
					</li>
									
		</c:if>
			</li>
		
			<li>
		<c:if test="${ sessionScope.USER.isAdmin == 'true' || fn:contains(sessionScope.USER.entitlements, 'CADREDASHBOARD' )}">
				<li>			
						<a href="#"><i class="fa fa-pencil ico-white"></i><span>&nbsp;&nbsp;2016 Cadre Reports</span></a>
						 <h2><i class="fa fa-pencil ico-white line_heightDiv"></i>2016 Cadre Reports</h2>
						 
					<ul>
					
							<c:if test="${ sessionScope.USER.isAdmin == 'true' || fn:contains(sessionScope.USER.entitlements, 'CADREDASHBOARD' )}">	
									<li>
										<a href="#"><i class="fa fa-envelope ico-white"></i><span>&nbsp;&nbsp;Web Monitoring</span></a>
										 <h2><i class="fa fa-envelope ico-white line_heightDiv"></i>Web Monitoring</h2>
										 <ul>
											 <c:if test="${fn:contains(sessionScope.USER.entitlements, 'CADRE_FIELD_MONITORING')}">
												<li>
													<a href="fieldMonitoringNewAction.action"><i class="fa fa-qrcode  ico-white"></i><span>&nbsp;&nbsp;Field Monitoring</span></a>
												</li>
											</c:if>
											<c:if test="${fn:contains(sessionScope.USER.entitlements, 'CADRE_FIELD_MONITORING_DASHBOARD')}">
												<li>
													<a href="fieldMonitoringDashboardAction.action"><i class="fa fa-qrcode  ico-white"></i><span>&nbsp;&nbsp;Field Monitoring DashBoard</span></a>
												</li>
											</c:if>
											<c:if test="${fn:contains(sessionScope.USER.entitlements, 'CADRE_FIELD_MONITORING_DASHBOARD')}">
												<li>
													<a href="fieldMonitoringReportAction.action"><i class="fa fa-qrcode  ico-white"></i><span>&nbsp;&nbsp;Field Monitoring Report</span></a>
												</li>
											</c:if>
											<c:if test="${fn:contains(sessionScope.USER.entitlements, 'CADRE_DATA_MONITORING')}">
												<li>
													<a href="dataMonitoringAction.action"><i class="fa fa-qrcode  ico-white"></i><span>&nbsp;&nbsp;Data Monitoring</span></a>
												</li>
											</c:if>
											<c:if test="${fn:contains(sessionScope.USER.entitlements, 'CADRE_DATA_MONITORING_DASHBOARD')}">
												<li>
													<a href="dataMonitoringDashboardNewAction.action"><i class="fa fa-qrcode  ico-white"></i><span>&nbsp;&nbsp;Data Monitoring DashBoard</span></a>
												</li>
											</c:if>
											<c:if test="${fn:contains(sessionScope.USER.entitlements, 'CADRE_WEB_MONITORING_DASHBOARD')}">
												<li>
													<a href="webMonitoringDashboardAction.action"><i class="fa fa-qrcode  ico-white"></i><span>&nbsp;&nbsp;Web Monitoring DashBoard</span></a>
												</li>
											</c:if>
											<li>
												<a href="boothWiseDataMonitoringAction.action"><i class="fa fa-qrcode  ico-white"></i><span>&nbsp;&nbsp;Booth Wise Data Monitoring</span></a>
											</li>
										 </ul>
									</li>
							</c:if>
										
							<li>
								<a href="newCadreDashBoard2016Action.action"><i class="fa fa-qrcode  ico-white"></i><span>&nbsp;&nbsp;2016 Cadre Dashboard</span></a>
							</li>
							<li>
								<a href="cadreDemographicReportsAction.action"><i class="fa fa-qrcode  ico-white"></i><span>&nbsp;&nbsp;Cadre Demographic Reports</span></a>
							</li>
										
										
						
						<c:if test="${sessionScope.USER.isAdmin == 'true' &&fn:contains(sessionScope.USER.entitlements, 'VOTER_ANALYSIS' )}">
								<!--- Card Printing DashBoard 2016 start -->
							<li>
						
								<a href="#"><i class="fa fa-archive  ico-white"></i><span>&nbsp;&nbsp;Card Printing DashBoard 2016</span></a>
								<h2><i class="fa fa-archive  ico-white line_heightDiv"></i>Card Printing DashBoard 2016</h2>
									 <ul>
										<li>
											<a href="cardPrintAdminAction.action"><i class="fa fa-bookmark  ico-white"></i><span>&nbsp;&nbsp;Cadre Data Pre Verification</span></a>
										</li>
										<li>
											<a href="cardPrintPostVerificationAction.action"><i class="fa fa-bookmark  ico-white"></i><span>&nbsp;&nbsp;Cadre Data Post Verification</span></a>
										</li>
										<li>
											<a href="cardPrintingStatusAction.action"><i class="fa fa-bookmark  ico-white"></i><span>&nbsp;&nbsp;Location Wise Card Print Status</span></a>
										</li>
										<li>
											<a href="cardPrintDispatchAction.action"><i class="fa fa-indent  ico-white"></i><span>&nbsp;&nbsp;Dispatch Status</span></a>
										</li>
										<li>
											<a href="cardPrintDashboardAction.action"><i class="fa fa-dashboard  ico-white"></i><span>&nbsp;&nbsp;DashBoard</span></a>
										</li>
									 </ul>
							</li>	
						</c:if>	
							
					</ul>
						 
				</li>
									
		</c:if>
			</li>
		</ul>
	</li>

</c:if>
</c:if>
          <li>
	<a href="newCadreDashBoard2016Action.action"><i class="fa fa-book"></i><span>&nbsp;&nbsp; Cadre Dashboard (AP/TS)</span></a>
				</li>
								<c:if test="${ sessionScope.USER.isAdmin == 'true' || fn:contains(sessionScope.USER.entitlements, 'CADREDASHBOARD' )}">
								<li>
									<a href="#"><i class="fa fa-dashboard ico-white"></i><span>&nbsp;&nbsp;Cadre Dashboard (Other States)</span></a>
									 <h2><i class="fa fa-dashboard ico-white line_heightDiv"></i> Cadre Dashoard (Other States)</h2>
									 <ul>
									 <!--<li>
									<a href="cadreDashBoardAction.action"><i class="fa fa-book"></i><span>&nbsp;&nbsp;Andhra Pradesh/Telangana</span></a>
									  </li>-->
									<li>
									<a href="cadredashBoardAction.action?stateId=12"><i class="fa fa-book"></i><span>&nbsp;&nbsp;Karnataka</span></a>
									</li>
									<li>
									<a href="cadredashBoardAction.action?stateId=15"><i class="fa fa-book"></i><span>&nbsp;&nbsp;Maharashtra</span></a>
									</li>
									<li>
									<a href="cadredashBoardAction.action?stateId=29"><i class="fa fa-book"></i><span>&nbsp;&nbsp;Andaman/Nicobar</span></a>
									</li>
									 </ul>
								</li></c:if>
								<c:if test="${sessionScope.USER.isAdmin == 'true' &&							fn:contains(sessionScope.USER.entitlements, 'VOTER_ANALYSIS' )}">
								<c:if test="${ sessionScope.USER.isAdmin == 'true' || fn:contains(sessionScope.USER.entitlements, 'CADREDASHBOARD' )}">
									<li>
									<a href="tdpCadreCardsPrintingDashBoardAction.action"><i class="fa fa-bookmark ico-white"></i><span>&nbsp;&nbsp;Cadre Cards Printing Dashboard</span></a>
									</li>
									</c:if>
									<c:if test="${sessionScope.USER.isAdmin == 'true' || fn:contains(sessionScope.USER.entitlements, 'CADRE_MEMBERSHIPCARD_DISPATCHER' ) || fn:contains(sessionScope.USER.entitlements, 'CADRE_MEMBERSHIPCARD_DISPATCHER_GROUP' )}">
									 <li>
									<a href="cadreMemberShipCardDispatcherAction.action"><i class="fa fa-indent ico-white"></i><span>&nbsp;&nbsp;Cadre Membership Card Status</span></a>
									</li></c:if>
									</c:if>
									</ul>
						</li></c:if>
						<c:if test="${sessionScope.USER.isAdmin == 'true' ||							fn:contains(sessionScope.USER.entitlements, 'AFFILIATED_UNION_REGISTRATION_ENTITLEMENT' ) || 
						fn:contains(sessionScope.USER.entitlements, 'AFFILIATED_UNION_DASHBOARD_ENTITLEMENT' )}">
						<li>
							<a href="#"><img class=" ico-white pull-left" src="images/events.jpg"></img><span>&nbsp;&nbsp;Affiliated Union Enrollment </span></a>
										 <h2><img class=" ico-white line_heightDiv  pull-left" src="images/events.jpg"></img> Affiliated Cadre  </h2>
							<ul>	
							
								<c:if test="${sessionScope.USER.isAdmin == 'true' ||fn:contains(sessionScope.USER.entitlements, 'AFFILIATED_UNION_REGISTRATION_ENTITLEMENT' )}">
								 <li>
									<a href="affiliatedCadreSearchAction.action"><i class="fa fa-dashboard"></i><span>&nbsp;&nbsp;All Union Registrations</span></a>
								 </li>
								</c:if>
								
								<c:if test="${sessionScope.USER.isAdmin == 'true' ||							fn:contains(sessionScope.USER.entitlements, 'AFFILIATED_UNION_DASHBOARD_ENTITLEMENT' )}">
								 <li>
									<a href="affiliatedCadreDashBoardAction.action"><i class="fa fa-dashboard"></i><span>&nbsp;&nbsp;DashBoard</span></a>
								 </li>
								</c:if>
								<c:if test="${sessionScope.USER.isAdmin == 'true' }">
							 <li>
								<a href="unionSearchRegiststrationAction.action"><i class="fa fa-user-plus"></i><span>&nbsp;&nbsp;Union Graduates Enrollment</span></a>									
							</li>
							</c:if>
							</ul>
						</li>
						</c:if>
						
						<c:if test="${ sessionScope.USER.isAdmin == 'true' || fn:contains(sessionScope.USER.entitlements, 'TDP_COMMITTEE_ADMIN' ) || fn:contains(sessionScope.USER.entitlements, 'TDP_COMMITTEE_AREAWISE_ACCESS' ) || fn:contains(sessionScope.USER.entitlements, 'COMMITTEE_DETAILED_REPORT' ) || fn:contains(sessionScope.USER.entitlements, 'COMMITTEE_MGT' ) || fn:contains(sessionScope.USER.entitlements, 'PARTY_ACTIVITY_UPDATE' ) || fn:contains(sessionScope.USER.entitlements, 'CADRE_COMMITTEE_MANAGEMENT' ) || fn:contains(sessionScope.USER.entitlements, 'TDP_COMMITTEE_STATE_DISTRICT_ACCESS')}">
						 <li>
								<a href="#"><i class="fa fa-group ico-white"></i><span>&nbsp;&nbsp;Committees</span></a>
								 <h2><i class="fa fa-group ico-white line_heightDiv"></i> Committees</h2>
								 <ul>
								<c:if test="${ sessionScope.USER.isAdmin == 'true' || fn:contains(sessionScope.USER.entitlements, 'TDP_COMMITTEE_ADMIN' ) || fn:contains(sessionScope.USER.entitlements, 'TDP_COMMITTEE_AREAWISE_ACCESS' ) || fn:contains(sessionScope.USER.entitlements, 'COMMITTEE_DETAILED_REPORT' ) || fn:contains(sessionScope.USER.entitlements, 'COMMITTEE_MGT' )}">
									<c:if test="${fn:contains(sessionScope.USER.entitlements, 'COMMITTEE_MGT' )}">
										<li>
											<a href="committeeInfoAction.action"><i class="fa fa-calendar-plus-o"></i><span>&nbsp;&nbsp; Create Committee </span></a>
										</li>
									</c:if>
								  <li>
									<a href="#"><i class="fa fa-dashboard ico-white"></i><span>&nbsp;&nbsp;Committees Dashboard</span></a>
									 <h2><i class="fa fa-dashboard ico-white line_heightDiv"></i> Committees Dashboard</h2>
									 <ul>
									 <c:if test="${ sessionScope.USER.isAdmin == 'true' ||  fn:contains(sessionScope.USER.entitlements, 'TDP_COMMITTEE_ADMIN' ) || fn:contains(sessionScope.USER.entitlements, 'TDP_COMMITTEE_AREAWISE_ACCESS' ) || fn:contains(sessionScope.USER.entitlements, 'COMMITTEE_DETAILED_REPORT' ) || fn:contains(sessionScope.USER.entitlements, 'COMMITTEE_MGT' )}">
									<li>
									<a href="committeeDashBoardAction.action"><i class="fa fa-dashboard ico-white"></i><span>&nbsp;&nbsp;Committee Dashboard</span></a>
									</li>
									</c:if>
									<c:if test="${ sessionScope.USER.isAdmin == 'true' ||  fn:contains(sessionScope.USER.entitlements, 'TDP_COMMITTEE_ADMIN' ) || fn:contains(sessionScope.USER.entitlements, 'COMMITTEE_DETAILED_REPORT' )}">
									<li>
									<a href="cadreCommitteeRolesDashboard.action"><i class="fa fa-list ico-white"></i><span>&nbsp;&nbsp;Committee Details Report</span></a>
									</li></c:if>
									 </ul>
									</li>
								</c:if>
								<c:if test="${sessionScope.USER.isAdmin == 'true' &&fn:contains(sessionScope.USER.entitlements, 'VOTER_ANALYSIS' )}">
									<c:if test="${ sessionScope.USER.isAdmin == 'true' || fn:contains(sessionScope.USER.entitlements, 'CADREDASHBOARD' )}">
								<li>
									<a href="cadreCommitteeAction.action"><i class="fa fa-edit ico-white"></i><span>&nbsp;&nbsp;TDP Committees Management</span></a>
								</li>
									</c:if>
									</c:if>
									<!--<c:if test="${ sessionScope.USER.isAdmin == 'true' || fn:contains(sessionScope.USER.entitlements, 'CADREDASHBOARD' ) || not fn:contains(sessionScope.USER.entitlements, 'PARTY_ACTIVITY_UPDATE' || fn:contains(sessionScope.USER.entitlements, 'CADRE_DELETE_ENTITLEMENT_GROUP') || fn:contains(sessionScope.USER.entitlements, 'CADRE_DELETE_ENTITLEMENT'))}">
									<li>
									<a href="#"><i class="fa fa-user-secret ico-white"></i><span>&nbsp;&nbsp;Party Meetings</span></a>
									 <h2><i class="fa fa-user-secret ico-white line_heightDiv"></i> Party Meetings</h2>
									<ul>
									 
										<li>
										<a href="partyMeetingsDashBoard.action"><i class="fa fa-dashboard ico-white"></i><span>&nbsp;&nbsp;Meeting Dashboard</span></a>
										</li>
										<li>
										<a href="meetingList.action"><i class="fa fa-street-view ico-white"></i><span>&nbsp;&nbsp;Meeting MOM & ATR</span></a>
										</li>
									</ul>
								</li>
									</c:if> -->
									<c:if test="${sessionScope.USER.isAdmin == 'true' &&							fn:contains(sessionScope.USER.entitlements, 'VOTER_ANALYSIS' )}">
							<c:if test="${ sessionScope.USER.isAdmin == 'true' || fn:contains(sessionScope.USER.entitlements, 'CADREDASHBOARD' )}">
								<li>
									<a href="cadreCommitteeSummaryAction.action"><i class="fa fa-edit ico-white"></i><span>&nbsp;&nbsp;Summary Report</span></a>
								</li>
							</c:if>
							</c:if>
							<c:if test="${ sessionScope.USER.isAdmin == 'true' || fn:contains(sessionScope.USER.entitlements, 'TDP_COMMITTEE_ADMIN')}">
								<li>
									<a href="committeeUpdateApproveAction.action"><i class="fa fa-edit ico-white"></i><span>&nbsp;&nbsp;Approval Requests</span></a>
								</li>
									</c:if>
							<c:if test="${ sessionScope.USER.isAdmin == 'true' || fn:contains(sessionScope.USER.entitlements, 'CADRE_COMMITTEE_MANAGEMENT') || fn:contains(sessionScope.USER.entitlements, 'TDP_COMMITTEE_ADMIN')}">
								<li>
									<a href="constituencyCommitteeSummaryAction.action"><i class="fa fa-edit ico-white"></i><span>&nbsp;&nbsp;Advanced Dashboard</span></a>
								</li>
							</c:if>
								 </ul>
								
						</li>
						
						
						</c:if>
						
						<c:if test="${sessionScope.USER.isAdmin == 'true' ||							fn:contains(sessionScope.USER.entitlements, 'PARTY_MEETINGS_ENTITLEMENT' ) || 
						fn:contains(sessionScope.USER.entitlements, 'PARTY_MEETINGS_ADMIN_ENTITLEMENT' )}">
						 <li>								
							<a href="#"><i class="fa fa-user-secret ico-white"></i><span>&nbsp;&nbsp;Party Meetings</span></a>
							<h2><i class="fa fa-user-secret ico-white line_heightDiv"></i> Party Meetings</h2>
							<ul>		
							<c:if test="${sessionScope.USER.accessType != 'MP'}">
								<li>
									<a href="partyMeetingsDashBoard.action"><i class="fa fa-dashboard ico-white"></i><span>&nbsp;&nbsp;Meeting Dashboard</span></a>
									</li>
							</c:if>
								<li>
									<a href="meetingList.action"><i class="fa fa-street-view ico-white"></i><span>&nbsp;&nbsp;Meeting MOM & ATR</span></a>
								</li>
								
								<c:if test="${fn:contains(sessionScope.USER.entitlements, 'PARTY_MEETING_THIRD_PARTY_UPDATION_ENTITLEMENT') || fn:contains(sessionScope.USER.entitlements, 'PARTY_MEETING_THIRD_PARTY_UPDATION_ADMIN_ENTITLEMENT')}">
									<li>
										<a href="finalizedPartyMeetingConductedAction.action"><i class="fa fa-qrcode  ico-white"></i><span>&nbsp;&nbsp;Update Party Meetings Conflicts</span></a>
									</li>
								</c:if>
								
							</ul>
						</li>
						</c:if>
						
						<c:if test="${sessionScope.USER.isAdmin == 'true' || fn:contains(sessionScope.USER.entitlements, 'PARTY_ACTIVITY_UPDATE' ) || fn:contains(sessionScope.USER.entitlements, 'ACTIVITY_ENTRY_ENTITLEMENT') || fn:contains(sessionScope.USER.entitlements, 'ACTIVITIES_DASHBOARD_ENTITLEMENT')}">
							<li>
								<a href="#"><img class=" ico-white pull-left" src="images/events.jpg"></img><span>&nbsp;&nbsp;Events & Activities </span></a>
								 <h2><img class=" ico-white line_heightDiv  pull-left" src="images/events.jpg"></img> Events & Activities  </h2>
								 <ul>
								 <c:if test="${sessionScope.USER.isAdmin == 'true' || fn:contains(sessionScope.USER.entitlements, 'PARTY_ACTIVITY_UPDATE') || fn:contains(sessionScope.USER.entitlements, 'CADRE_DELETE_ENTITLEMENT_GROUP') || fn:contains(sessionScope.USER.entitlements, 'CADRE_DELETE_ENTITLEMENT') || fn:contains(sessionScope.USER.entitlements, 'ACTIVITY_ENTRY_ENTITLEMENT') }">
								 <li><a href="updateActivityAction.action"><i class="fa fa-mail-forward"></i><span>  Activities Entry  </span></a></li></c:if>
								 
							
							<c:if test="${sessionScope.USER.isAdmin == 'true' ||
							fn:contains(sessionScope.USER.entitlements, 'VOTER_ANALYSIS') || fn:contains(sessionScope.USER.entitlements, 'ACTIVITIES_DASHBOARD_ENTITLEMENT') }">
								 <li><a href="activitiesDashboard.action"><i class="fa fa-firefox"></i><span> Activities DashBoard  </span></a></li>
							</c:if>
							<!--<li><a href="activitiesQuestionResponcesAction.action"><i class="fa fa-eye"></i><span> Activities Response Report  </span></a></li>-->
								 	 <c:if test="${sessionScope.USER.isAdmin == 'true' ||
							fn:contains(sessionScope.USER.entitlements, 'VOTER_ANALYSIS') }">
									<li>
										<a href="partyLeadersDetailsAction.action"><i class="fa fa-envelope-square"></i><span>&nbsp;&nbsp;Leaders Details </span></a>
									</li>
									<li>
										<a href="eventInvitees.action"><i class="fa fa-envelope-square"></i><span>&nbsp;&nbsp;Events Invitees</span></a>
									</li>
									  <li>
									<a href="#"><i class="fa fa-dashboard ico-white"></i><span>&nbsp;&nbsp;Events Dashoard</span></a>
									<h2><i class="fa fa-dashboard ico-white line_heightDiv"></i> Events Dashoard</h2>
									<ul>
									<c:if test="${fn:contains(sessionScope.USER.entitlements, 'MAHANADU_MAIN_DASHBOARD_ENTITLEMENT') ||
									fn:contains(sessionScope.USER.entitlements, 'MAHANADU_MAIN_DASHBOARD_ADMIN_ENTITLEMENT') }">									  
										<li>
											<a href="mahanaduCadreVisitNewInfoAction.action"><i class="fa fa-dashboard ico-white"></i><span>&nbsp;&nbsp;Mahanadu Entry /Exit Dashboard</span></a>
										</li>
									</c:if>
									<c:if test="${fn:contains(sessionScope.USER.entitlements, 'EVENTS_DASHBOARD_ENTITLEMENT') ||
									fn:contains(sessionScope.USER.entitlements, 'EVENTS_DASHBOARD_ADMIN_ENTITLEMENT') }">									  
										<li>
											<a href="eventDashboardAction.action?eventId=1"><i class="fa fa-dashboard ico-white"></i><span>&nbsp;&nbsp;Event Dashoard</span></a>
										</li>
									</c:if>
									</ul>
									
									<c:if test="${sessionScope.USER.isAdmin == 'true'}">
					<li>
                        <a href="#"><img src='img/mahanaduicon1.png' class='pull-left changeIconCode' style='width:16px;'/><span>&nbsp;&nbsp;Mahanadu 2017</span></a>
                        <h2><i class="fa fa-wrench line_heightDiv"></i>Mahanadu 2017</h2>
                       <ul>
							<li>
								<a href="eventDashboardAction.action?eventId=51"><i class="fa fa-dashboard ico-white"></i><span>&nbsp;&nbsp;Event Dashoard</span></a>
							</li>
							<li>
								<a href="mahanaduCadreVisitNewInfoAction.action"><i class="fa fa-dashboard ico-white"></i><span>&nbsp;&nbsp;Mahanadu Entry /Exit Dashboard</span></a>
							</li>
							<li>
								<a href="eventReportDashBoardAction.action"><i class="fa fa-dashboard ico-white"></i><span>&nbsp;&nbsp; Event Demographic Analysis</span></a>
							</li>
					   </ul>
					 </li>
				</c:if>	
				
			
				<c:if test="${ fn:contains(sessionScope.USER.entitlements, 'BLOOD_BANK_REGISTRATION_ENTITLEMENT' )  ||
				fn:contains(sessionScope.USER.entitlements, 'BLOOD_BANK_DASHBOARD_ENTITLEMENT' ) ||
				fn:contains(sessionScope.USER.entitlements, 'BLOOD_BANK_BLEEDING_ENTITLEMENT' ) ||
				fn:contains(sessionScope.USER.entitlements, 'BLOOD_BANK_REGISTRATION_ADMIN_ENTITLEMENT' ) ||
				fn:contains(sessionScope.USER.entitlements, 'BLOOD_BANK_DASHBOARD_ADMIN_ENTITLEMENT' ) ||
				fn:contains(sessionScope.USER.entitlements, 'BLOOD_BANK_BLEEDING_ADMIN_ENTITLEMENT' )}">
					<li>
                        <a href="#"><img src='img/blood_donation.png' class='pull-left changeIconCode' style='width:16px;'/><span>&nbsp;&nbsp;BloodBank</span></a>
                        <h2><i class="fa fa-wrench line_heightDiv"></i>BloodBank</h2>
                       <ul>
					   <c:if test="${  fn:contains(sessionScope.USER.entitlements, 'BLOOD_BANK_REGISTRATION_ENTITLEMENT' )  ||
				fn:contains(sessionScope.USER.entitlements, 'BLOOD_BANK_REGISTRATION_ADMIN_ENTITLEMENT' ) }">
							<li>
								<a href="bloodBankRegistrationAction.action"><img src='img/blood_donation.png' class='pull-left changeIconCode' style='width:16px;'/><span>&nbsp;&nbsp;BloodBank Registration</span></a>
							</li>
						</c:if>	
				<c:if test="${  fn:contains(sessionScope.USER.entitlements, 'BLOOD_BANK_DASHBOARD_ENTITLEMENT' )  ||
				fn:contains(sessionScope.USER.entitlements, 'BLOOD_BANK_DASHBOARD_ADMIN_ENTITLEMENT' ) }">						
							<li>
								<a href="bloodBankDashBoardAction.action"><img src='img/blood_donation.png' class='pull-left changeIconCode' style='width:16px;'/><span>&nbsp;&nbsp;Blood Bank DashBoard</span></a>
							</li>
				</c:if>	
						<c:if test="${ fn:contains(sessionScope.USER.entitlements, 'BLOOD_BANK_BLEEDING_ENTITLEMENT' )  ||
						fn:contains(sessionScope.USER.entitlements, 'BLOOD_BANK_BLEEDING_ADMIN_ENTITLEMENT' ) }">	
							<li>
								<a href="bloodBankBleadingAction.action"><img src='img/blood_donation.png' class='pull-left changeIconCode' style='width:16px;'/><span>&nbsp;&nbsp;BloodBank Bleading</span></a>
							</li>
							</c:if>		
					   </ul>
					</li>
				</c:if>	
									
									</li>
									</c:if>
									</ul>
							</li></c:if>
						
						<c:if test="${sessionScope.loginStatus == 'out' && sessionScope.hasPartyAnalystUserRole == true}">
					   <c:if test="${fn:contains(sessionScope.USER.entitlements, 'VOTER_ANALYSIS' )  || fn:contains(sessionScope.USER.entitlements, 'VOTER_SEARCH_AND_EDIT' ) || fn:contains(sessionScope.USER.entitlements, 'PROBLEM_MANAGEMENT_ENTITLEMENT' ) || fn:contains(sessionScope.USER.entitlements, 'CADRE_MANAGEMENT_ENTITLEMENT' ) || fn:contains(sessionScope.USER.entitlements, 'CALL_CENTER_ENTITLEMENT' ) || fn:contains(sessionScope.USER.entitlements, 'ADMIN_PAGE' ) || fn:contains(sessionScope.USER.entitlements, 'VOTER_DATA_TOOLS' )}">
						 <li>
								<a href="#"><i class="fa fa-cogs"></i><span>&nbsp;&nbsp;Tools</span></a>
								 <h2><i class="fa fa-cogs line_heightDiv"></i> Tools</h2>
								 <ul>
								 <c:if test="${sessionScope.loginStatus == 'out' && sessionScope.hasPartyAnalystUserRole == true}">   
								<c:if test="${fn:contains(sessionScope.USER.entitlements, 'VOTER_ANALYSIS' )  || fn:contains(sessionScope.USER.entitlements, 'VOTER_SEARCH_AND_EDIT' ) || fn:contains(sessionScope.USER.entitlements, 'ADMIN_PAGE' ) ||  fn:contains(sessionScope.USER.entitlements, 'VOTER_DATA_TOOLS' )
								}">
									<li>
									<a href="#"><i class="fa fa-bar-chart"></i><span>&nbsp;&nbsp;Constituency Analysis</span></a>
									 <h2><i class="fa fa-bar-chart line_heightDiv"></i> Constituency Analysis</h2>
									 <ul>
									 <c:if test="${fn:contains(sessionScope.USER.entitlements, 'VOTER_ANALYSIS' ) }">
										<li>
										<a href="votersAnalysisNewAction.action"><i class="fa fa-pie-chart"></i><span>&nbsp;&nbsp;Voter Analysis</span></a>
										</li></c:if>
										<c:if test="${fn:contains(sessionScope.USER.entitlements, 'VOTER_SEARCH_AND_EDIT' ) || fn:contains(sessionScope.USER.entitlements, 'ADMIN_PAGE' ) || fn:contains(sessionScope.USER.entitlements, 'VOTER_DATA_TOOLS' )}">
										<li>
										<a href="votersSearchAction.action"><i class="fa fa-search"></i><span>&nbsp;&nbsp;Voter Search & Report</span></a>
										</li></c:if>
										<c:if test="${fn:contains(sessionScope.USER.entitlements, 'VOTER_ANALYSIS' ) }">
										<li>
										<a href="casteAndElectionResultsComparisonAction.action"><i class="fa fa-signal"></i><span>&nbsp;&nbsp;Caste Vs Election Result</span></a>
										</li></c:if>
									 </ul>
									 
									</li></c:if></c:if>
									<c:if test="${sessionScope.loginStatus == 'out' && sessionScope.hasPartyAnalystUserRole == true}">
									<c:if test="${fn:contains(sessionScope.USER.entitlements, 'PROBLEM_MANAGEMENT_ENTITLEMENT')}">
									<li>
									<a href="#"><i class="fa fa-puzzle-piece ico-white"></i><span>&nbsp;&nbsp;Problem Management</span></a>
									 <h2><i class="fa fa-puzzle-piece ico-white line_heightDiv"></i> Problem Management</h2>
									 <ul>
									    <li>
											<a href="javascript:{openAddNewProblemWindow();}"><i class="fa fa-plus-square"></i><span>&nbsp;&nbsp;Add New Problem</span></a>
										</li>
										<li>
											<a href="constituencyManagementAction.action?cmTask=PROBLEMS_MANAGEMENT"><i class="fa fa-search-plus"></i><span>&nbsp;&nbsp;All Problems</span></a>
										</li>
										<li>
											<a href="completeProblemDetailsSearchAction.action"><i class="fa fa-search-minus"></i><span>&nbsp;&nbsp;Problem Search & Report</span></a>
										</li>
									 </ul>
									</li></c:if></c:if>
									<c:if test="${ sessionScope.USER.isAdmin == 'true'  &&							fn:contains(sessionScope.USER.entitlements, 'VOTER_ANALYSIS' ) }">
									<li>
										<a href="initailConstituencyManagementAction.action"><i class="fa fa-university"></i><span>&nbsp;&nbsp;Constituency Management</span></a>
									</li>
									</c:if>
									<c:if test="${fn:contains(sessionScope.USER.entitlements, 'CADRE_MANAGEMENT_ENTITLEMENT' ) }">
									<li>
									<a href="cadreManagementAction.action"><i class="fa fa-street-view"></i><span>&nbsp;&nbsp;Cadre Management</span></a>
									</li></c:if>
									<c:if test="${fn:contains(sessionScope.USER.entitlements, 'CALL_CENTER_ENTITLEMENT' ) }">
									<li>
									<a href="callCenterAction.action"><i class="fa fa-headphones ico-white"></i><span>&nbsp;&nbsp;Call Center</span></a>
									</li>
									</c:if>
								 </ul>
						</li></c:if></c:if>
						
						<c:if test="${sessionScope.USER.isAdmin == 'true' &&							fn:contains(sessionScope.USER.entitlements, 'VOTER_ANALYSIS' )}">
						<c:if test="${ sessionScope.USER.isAdmin == 'true' || fn:contains(sessionScope.USER.entitlements, 'TIRUPATHI_BYEELECTION' ) || fn:contains(sessionScope.USER.entitlements, 'NEW_LIVE_RESULTS' ) }">
							<li>
								<a href="#"><img  class=" ico-white pull-left" src="images/elections.jpg"></img><span>&nbsp;&nbsp;Elections</span></a>
								
								 <h2><img class=" ico-white line_heightDiv pull-left" src="images/elections.jpg"></img> Elections</h2>
								 <ul>
								 <c:if test="${sessionScope.USER.isAdmin == 'true' || fn:contains(sessionScope.USER.entitlements, 'CADREIVRDASHBOARD' ) || fn:contains(sessionScope.USER.entitlements, 'POLLING_MANAGEMENT_ENTITLEMENT') || fn:contains(sessionScope.USER.entitlements, 'POLLING_MANAGEMENT_ENTITLEMENT_ADMIN') }">
									 <li>
										<a href="pollingManagmentAction.action"><i class="fa fa-group ico-white"></i><span>&nbsp;&nbsp; GHMC Elections -  2016 </span></a>
									</li>
									</c:if>
										<c:if test="${sessionScope.USER.isAdmin == 'true' || fn:contains(sessionScope.USER.entitlements, 'TIRUPATHI_BYEELECTION' ) }">
										<li>
											<a href="currentBoothsStatus.action"><i class="fa fa-link ico-white"></i><span>&nbsp;&nbsp;Tirupathi Bye Elections Poll Management</span></a>
									</li></c:if>
									<c:if test="${ sessionScope.USER.isAdmin == 'true' || fn:contains(sessionScope.USER.entitlements, 'NEW_LIVE_RESULTS' ) }">
									  <li>
									<a href="acPcWiseElectionResultAction.action?stateId=1"><i class="fa fa-paperclip ico-white"></i><span>&nbsp;&nbsp;Live Results</span></a>
									</li></c:if>
									</ul>
							</li></c:if>
							<c:if test="${sessionScope.USER.isAdmin == 'true' }">
							 <li>
								<a href="cadreMissedCallCampaignAction.action"><i class="fa fa-phone ico-white"></i><span>&nbsp;&nbsp;Cadre Missed Call Campaign</span></a>									
							</li>
							</c:if>
							
						<c:if test="${ sessionScope.USER.isAdmin == 'true' || fn:contains(sessionScope.USER.entitlements, 'CADREIVRDASHBOARD' ) }">
						<li>
								<a href="#"><img class=" ico-white pull-left" src="images/ivr.jpg"></img><span>&nbsp;&nbsp;IVR Campaign</span></a>
								<h2><img class=" ico-white line_heightDiv pull-left" src="images/ivr.jpg"></img> IVR Campaign</h2>
								<ul>
								  <li>
									<a href="cadreIvrReportAction.action"><i class="fa fa-bullhorn ico-white"></i><span>&nbsp;&nbsp;Cadre IVR Dashoard</span></a>
								  </li>
								  <li>
									<a href="villageIVRAction.action"><i class="fa fa-dashboard ico-white"></i><span>&nbsp;&nbsp;Committee IVR Dashoard</span></a>
								  </li>
								</ul>
						 </li></c:if>
						 </c:if>
						  
						<c:if test="${sessionScope.USER.isAdmin == 'true' &&fn:contains(sessionScope.USER.entitlements, 'VOTER_ANALYSIS' )}">
						 <li>								
							<a href="dailyVerificationReportsAction.action"><i class="fa fa-folder-open ico-white"></i><span>&nbsp;&nbsp;CTP Project</span></a>
						</li>
						</c:if>
						
						<!--<c:if test="${sessionScope.loginStatus == 'out' && sessionScope.hasPartyAnalystUserRole == true}">  
						<c:if test="fn:contains(sessionScope.USER.entitlements, 'PARTY_MEETINGS_ENTITLEMENT' ) || fn:contains(sessionScope.USER.entitlements, 'PARTY_MEETINGS_ADMIN_ENTITLEMENT'))}">
										<li>
										<a href="#"><i class="fa fa-user-secret ico-white"></i><span>&nbsp;&nbsp;Party Meetings</span></a>
										 <h2><i class="fa fa-user-secret ico-white line_heightDiv"></i> Party Meetings</h2>
										<ul>
										 
											<li>
											<a href="partyMeetingsDashBoard.action"><i class="fa fa-dashboard ico-white"></i><span>&nbsp;&nbsp;Meeting Dashboard</span></a>
											</li>
											<li>
											<a href="meetingList.action"><i class="fa fa-street-view ico-white"></i><span>&nbsp;&nbsp;Meeting MOM & ATR</span></a>
											</li>
										</ul>
									</li>
						</c:if>
						</c:if>-->
						
						<!--<c:if test="${sessionScope.USER.isAdmin == 'true' ||							fn:contains(sessionScope.USER.entitlements, 'PARTY_MEETINGS_ENTITLEMENT' ) || 
						fn:contains(sessionScope.USER.entitlements, 'PARTY_MEETINGS_ADMIN_ENTITLEMENT' )}">
						 <li>								
							<a href="#"><i class="fa fa-user-secret ico-white"></i><span>&nbsp;&nbsp;Party Meetings</span></a>
							<h2><i class="fa fa-user-secret ico-white line_heightDiv"></i> Party Meetings</h2>
							<ul>
							</ul>
						</li>
						</c:if>-->
					
					<!--<c:if test="${sessionScope.loginStatus == 'out' ||  fn:contains(sessionScope.USER.entitlements, 'AFFILIATED_UNION_REGISTRATION_ENTITLEMENT' ) || fn:contains(sessionScope.USER.entitlements, 'AFFILIATED_UNION_DASHBOARD_ENTITLEMENT' )}">
						<li>
							<a href="#"><img class=" ico-white pull-left" src="images/events.jpg"></img><span>&nbsp;&nbsp;Affiliated Cadre </span></a>
										 <h2><img class=" ico-white line_heightDiv  pull-left" src="images/events.jpg"></img> Affiliated Cadre  </h2>
							<ul>	 
								<c:if test="${sessionScope.USER.isAdmin == 'true' ||							fn:contains(sessionScope.USER.entitlements, 'AFFILIATED_UNION_REGISTRATION_ENTITLEMENT' )}">
								 <li>
									<a href="rtcUnionRegistrationAction.action"><i class="fa fa-dashboard"></i><span>&nbsp;&nbsp;Registration</span></a>
								 </li>
								</c:if>
								
								<c:if test="${sessionScope.USER.isAdmin == 'true' ||							fn:contains(sessionScope.USER.entitlements, 'AFFILIATED_UNION_DASHBOARD_ENTITLEMENT' )}">
								 <li>
									<a href="rtcUnionDashBoardAction.action"><i class="fa fa-dashboard"></i><span>&nbsp;&nbsp;DashBoard</span></a>
								 </li>
								</c:if>
								
							</ul>
						</li>
					</c:if> -->
					<!--Balu-->
						
						
						
						</ul>
                    </li>
					<c:if test="${fn:contains(sessionScope.USER.entitlements, 'COMMITTEE_MANAGEMENT_MULTIPLE_AREAS_ENTITLEMENT' )}">
					<li>
						<a href="committeeInfoAction.action"><i class="fa fa-calendar-plus-o"></i><span>&nbsp;&nbsp;Committee Management</span></a>
					</li>
						
					</c:if>
					</c:if></c:if> 
					
					
					<c:if test="${sessionScope.loginStatus == 'out' ||  fn:contains(sessionScope.USER.entitlements, 'CADRE_FAMILY_DETAILS_UPDATION' ) || fn:contains(sessionScope.USER.entitlements, 'TDP_CADRE_SEARCH' ) || fn:contains(sessionScope.USER.entitlements, 'MAHANADU' ) || fn:contains(sessionScope.USER.entitlements, 'CADREDASHBOARD' ) || fn:contains(sessionScope.USER.entitlements, 'CASTE_SURVEY_CALL_CENTER' ) || fn:contains(sessionScope.USER.entitlements, 'VIZAG_WM' ) || fn:contains(sessionScope.USER.entitlements, 'SURVEY_USER_CREATION' ) || fn:contains(sessionScope.USER.entitlements, 'PARTY_CADRE_SEARCH' ) || fn:contains(sessionScope.USER.entitlements, 'IVR_MOBILE_NUMBERS_RETRIVAL_REDIRECT' ) || fn:contains(sessionScope.USER.entitlements, 'CADRE_REGISTRATION_2014' ) || fn:contains(sessionScope.USER.entitlements, 'CADRE_MEMBERSHIPCARD_DISPATCHER' ) || fn:contains(sessionScope.USER.entitlements, 'GHMC_CADRE_MEGA_DRIVE_USER' ) || fn:contains(sessionScope.USER.entitlements, 'CADRE_2014_CARD_SAMPLE' ) || fn:contains(sessionScope.USER.entitlements, 'TABDEALLOCATIONALTER' ) || fn:contains(sessionScope.USER.entitlements, 'PARTYCADREDASHBOARD' ) || fn:contains(sessionScope.USER.entitlements, 'CADREIVRDASHBOARD' ) || fn:contains(sessionScope.USER.entitlements, 'CADRE_COMMITTEE_MANAGEMENT' ) || fn:contains(sessionScope.USER.entitlements, 'TDP_COMMITTEE_ADMIN' ) || fn:contains(sessionScope.USER.entitlements, 'TDP_COMMITTEE_AREAWISE_ACCESS' ) || fn:contains(sessionScope.USER.entitlements, 'TIRUPATHI_BYEELECTION' ) || fn:contains(sessionScope.USER.entitlements, 'TDP_COMMITTEE_STATE_DISTRICT_ACCESS' ) || fn:contains(sessionScope.USER.entitlements, 'CADRE_REGISTRATIONFOR_OTHERSTATES' ) || fn:contains(sessionScope.USER.entitlements, 'MAHANADUTABALLOCATION' ) || fn:contains(sessionScope.USER.entitlements, 'OTHER_STATE_DELEGATE_REG' ) || fn:contains(sessionScope.USER.entitlements, 'COMMITTEE_MGT' ) || fn:contains(sessionScope.USER.entitlements, 'TRAINING_CAMP_CALLER_ADMIN' ) || fn:contains(sessionScope.USER.entitlements, 'TRAINING_CAMP_CALLER' ) || fn:contains(sessionScope.USER.entitlements, 'TRAINING_CAMP_FEEDBACK_UPDATE_ENTITLEMENT' ) || fn:contains(sessionScope.USER.entitlements, 'TRAINING_CAMP_ADMIN' )}">
					 <li>
                        <a href="dashBoardAction.action"><i class="fa fa-dashboard"></i><span>&nbsp;&nbsp;Dashboard</span></a>
                    </li>
					</c:if>
					<c:if test="${sessionScope.USER.isAdmin == 'true' ||							fn:contains(sessionScope.USER.entitlements, 'APPOINTMENTS_MANAGE_ENTITLEMENT' )}">
						<li>
							<a href="appointmentsAction.action"><i class="fa fa-calendar-plus-o"></i><span>&nbsp;&nbsp;Appointments</span></a>
						</li>	
					</c:if>
					
				<c:if test="${fn:contains(sessionScope.USER.entitlements, 'EVENTS_DASHBOARD_ENTITLEMENT' )}">
					<li>
						<a href="eventDashboardAction.action?eventId=30"><i class="fa fa-dashboard ico-white"></i><span>&nbsp;&nbsp;Event Dashoard</span></a>
					</li>
					 <li>
						<a href="mahanaduCadreVisitNewInfoAction.action"><i class="fa fa-dashboard ico-white"></i><span>&nbsp;&nbsp;Mahanadu Entry /Exit Dashboard</span></a>
					</li>
				</c:if>	
				<!--
				<c:if test="${sessionScope.USER.isAdmin == 'true'}">
					<li>
                        <a href="#"><img src='img/mahanaduicon1.png' class='pull-left changeIconCode' style='width:16px;'/><span>&nbsp;&nbsp;Mahanadu 2016</span></a>
                        <h2><i class="fa fa-wrench line_heightDiv"></i>Mahanadu 2016</h2>
                       <ul>
							<li>
								<a href="eventDashboardAction.action?eventId=30"><i class="fa fa-dashboard ico-white"></i><span>&nbsp;&nbsp;Event Dashoard</span></a>
							</li>
							<li>
								<a href="mahanaduCadreVisitNewInfoAction.action"><i class="fa fa-dashboard ico-white"></i><span>&nbsp;&nbsp;Mahanadu Entry /Exit Dashboard</span></a>
							</li>
							<li>
								<a href="eventReportDashBoardAction.action"><i class="fa fa-dashboard ico-white"></i><span>&nbsp;&nbsp; Event Demographic Analysis</span></a>
							</li>
					   </ul>
					 </li>
				</c:if>	
				
			
				<c:if test="${ fn:contains(sessionScope.USER.entitlements, 'BLOOD_BANK_REGISTRATION_ENTITLEMENT' )  ||
				fn:contains(sessionScope.USER.entitlements, 'BLOOD_BANK_DASHBOARD_ENTITLEMENT' ) ||
				fn:contains(sessionScope.USER.entitlements, 'BLOOD_BANK_BLEEDING_ENTITLEMENT' ) ||
				fn:contains(sessionScope.USER.entitlements, 'BLOOD_BANK_REGISTRATION_ADMIN_ENTITLEMENT' ) ||
				fn:contains(sessionScope.USER.entitlements, 'BLOOD_BANK_DASHBOARD_ADMIN_ENTITLEMENT' ) ||
				fn:contains(sessionScope.USER.entitlements, 'BLOOD_BANK_BLEEDING_ADMIN_ENTITLEMENT' )}">
					<li>
                        <a href="#"><img src='img/blood_donation.png' class='pull-left changeIconCode' style='width:16px;'/><span>&nbsp;&nbsp;BloodBank</span></a>
                        <h2><i class="fa fa-wrench line_heightDiv"></i>BloodBank</h2>
                       <ul>
					   <c:if test="${  fn:contains(sessionScope.USER.entitlements, 'BLOOD_BANK_REGISTRATION_ENTITLEMENT' )  ||
				fn:contains(sessionScope.USER.entitlements, 'BLOOD_BANK_REGISTRATION_ADMIN_ENTITLEMENT' ) }">
							<li>
								<a href="bloodBankRegistrationAction.action"><img src='img/blood_donation.png' class='pull-left changeIconCode' style='width:16px;'/><span>&nbsp;&nbsp;BloodBank Registration</span></a>
							</li>
						</c:if>	
				<c:if test="${  fn:contains(sessionScope.USER.entitlements, 'BLOOD_BANK_DASHBOARD_ENTITLEMENT' )  ||
				fn:contains(sessionScope.USER.entitlements, 'BLOOD_BANK_DASHBOARD_ADMIN_ENTITLEMENT' ) }">						
							<li>
								<a href="bloodBankDashBoardAction.action"><img src='img/blood_donation.png' class='pull-left changeIconCode' style='width:16px;'/><span>&nbsp;&nbsp;BloodBank DashBoard</span></a>
							</li>
				</c:if>	
						<c:if test="${ fn:contains(sessionScope.USER.entitlements, 'BLOOD_BANK_BLEEDING_ENTITLEMENT' )  ||
						fn:contains(sessionScope.USER.entitlements, 'BLOOD_BANK_BLEEDING_ADMIN_ENTITLEMENT' ) }">	
							<li>
								<a href="bloodBankBleadingAction.action"><img src='img/blood_donation.png' class='pull-left changeIconCode' style='width:16px;'/><span>&nbsp;&nbsp;BloodBank Bleading</span></a>
							</li>
							</c:if>		
					   </ul>
					</li>
				</c:if>	
					-->
				
				<c:if test="${ fn:contains(sessionScope.USER.entitlements, 'DEBATE_ENTITLEMENT' )  ||
						fn:contains(sessionScope.USER.entitlements, 'DEBATE_ADMIN_ENTITLEMENT' ) }">					
					<li style="text-align: right">
                        <a href="http://mytdp.com/debateAction.action"><img style="width:18px;float:left" src="img/cadre_debate.png"><span>&nbsp;&nbsp;Debate</span></a>
                    </li>
				</c:if>	
				<c:if test="${ fn:contains(sessionScope.USER.entitlements, 'KAIZALA_MESSAGES_ENTITLEMENT' )}">	
					<li>
						<a href="http://mytdp.com/KAIZALA/kaizalaMessagesDashBoard?userId=${sessionScope.USER.registrationID}"><i class="fa fa-qrcode  ico-white"></i><span>&nbsp;KAIZALA DashBoard</span></a>
					</li>
				</c:if>		
				<c:if test="${fn:contains(sessionScope.USER.entitlements, 'CREATE_ALERT_ENTITLEMENT')
				|| fn:contains(sessionScope.USER.entitlements, 'UPDATE_ALERT_ENTITLEMENT') || 
				fn:contains(sessionScope.USER.entitlements, 'ALERT_DASHBOARD_USER_ENTITLEMENT') ||
				fn:contains(sessionScope.USER.entitlements, 'ALERT_DASHBOARD_ADMIN_ENTITLEMENT') || fn:contains(sessionScope.USER.entitlements, 'ALERT_CLARIFICATION_DASHBOARD_ENTITLEMENT') || fn:contains(sessionScope.USER.entitlements, 'ALERT_CLARIFICATION_DASHBOARD_ADMIN_ENTITLEMENT') ||
				fn:contains(sessionScope.USER.entitlements, 'TDP_CADRE_LOGIN_ENTITLEMENT')}">
					<li>
						<a href="#"><i class="fa fa-bell-o ico-white"></i><span>&nbsp;&nbsp;Alert</span></a>
							 <h2><i class="fa fa-bell-o ico-white line_heightDiv"></i> Alert</h2>
								 <ul>
								 	<c:if test="${fn:contains(sessionScope.USER.entitlements, 'CREATE_ALERT_ENTITLEMENT')}">
									  	<li>
											<a href="http://mytdp.com/createAlertAction.action"><i class="fa fa-edit ico-white"></i><span>&nbsp;&nbsp;Create Alert</span></a>
										</li>											
										<li>
											<a href="alertDashboardAction.action"><i class="fa fa-wpforms ico-white"></i><span>&nbsp;&nbsp;Alert Dashboard</span></a>
										</li>
									</c:if> 
									<c:if test="${fn:contains(sessionScope.USER.entitlements, 'TDP_CADRE_LOGIN_ENTITLEMENT')}">
										<li>
											<a href="centralPartyMembersAlertDashboardAction.action"><i class="fa fa-wpforms ico-white"></i><span>&nbsp;&nbsp;Assigned Alerts</span></a>
										</li>
									</c:if> 
									<c:if test="${fn:contains(sessionScope.USER.entitlements, 'ALERT_CLARIFICATION_DASHBOARD_ENTITLEMENT') || fn:contains(sessionScope.USER.entitlements, 'ALERT_CLARIFICATION_DASHBOARD_ADMIN_ENTITLEMENT')}">
										<li>
											<a href="alertClarificationDashboardAction.action"><i class="fa fa-wpforms ico-white"></i><span>&nbsp;&nbsp;Alert Clarification Dashboard</span></a>
										</li>
									</c:if> 										
								</ul>
					</li>
				</c:if>	
				
							<c:if test="${sessionScope.USER.isAdmin == 'true'}">
								<li>
									<a href="#"><img src='img/mahanaduicon1.png' class='pull-left changeIconCode' style='width:16px;'/><span>&nbsp;&nbsp;Mahanadu 2017</span></a>
									<h2><i class="fa fa-wrench line_heightDiv"></i>Mahanadu 2017</h2>
								   <ul>
										<li>
											<a href="eventDashboardAction.action?eventId=51"><i class="fa fa-dashboard ico-white"></i><span>&nbsp;&nbsp;Event Dashoard</span></a>
										</li>
										<li>
											<a href="mahanaduCadreVisitNewInfoAction.action"><i class="fa fa-dashboard ico-white"></i><span>&nbsp;&nbsp;Mahanadu Entry /Exit Dashboard</span></a>
										</li>
										<li>
											<a href="eventReportDashBoardAction.action"><i class="fa fa-dashboard ico-white"></i><span>&nbsp;&nbsp; Event Demographic Analysis</span></a>
										</li>
										<li>
											<a href="#"><img src='img/blood_donation.png' class='pull-left changeIconCode' style='width:16px;'/><span>&nbsp;&nbsp;BloodBank</span></a>
											<h2><i class="fa fa-wrench line_heightDiv"></i>BloodBank</h2>
										   <ul>
										   <c:if test="${  fn:contains(sessionScope.USER.entitlements, 'BLOOD_BANK_REGISTRATION_ENTITLEMENT' )  ||
											fn:contains(sessionScope.USER.entitlements, 'BLOOD_BANK_REGISTRATION_ADMIN_ENTITLEMENT' ) }">
												<li>
													<a href="bloodBankRegistrationAction.action"><img src='img/blood_donation.png' class='pull-left changeIconCode' style='width:16px;'/><span>&nbsp;&nbsp;BloodBank Registration</span></a>
												</li>
											</c:if>	
											<c:if test="${  fn:contains(sessionScope.USER.entitlements, 'BLOOD_BANK_DASHBOARD_ENTITLEMENT' )  ||
											fn:contains(sessionScope.USER.entitlements, 'BLOOD_BANK_DASHBOARD_ADMIN_ENTITLEMENT' ) }">						
														<li>
															<a href="bloodBankDashBoardAction.action"><img src='img/blood_donation.png' class='pull-left changeIconCode' style='width:16px;'/><span>&nbsp;&nbsp;Blood Bank DashBoard</span></a>
														</li>
											</c:if>	
											<c:if test="${ fn:contains(sessionScope.USER.entitlements, 'BLOOD_BANK_BLEEDING_ENTITLEMENT' )  ||
											fn:contains(sessionScope.USER.entitlements, 'BLOOD_BANK_BLEEDING_ADMIN_ENTITLEMENT' ) }">	
												<li>
													<a href="bloodBankBleadingAction.action"><img src='img/blood_donation.png' class='pull-left changeIconCode' style='width:16px;'/><span>&nbsp;&nbsp;BloodBank Bleading</span></a>
												</li>
												</c:if>		
										   </ul>
										</li>
										<li>
											<a href="mahanaduNotificationAction.action"><i class="fa fa-dashboard ico-white"></i><span>&nbsp;&nbsp; Notifications</span></a>
										</li>
								   </ul>
								 </li>
							</c:if>
							
					 <c:if test="${fn:contains(sessionScope.USER.entitlements, 'CREATE_NOMINATED_POST_ENTITLEMENT') || 
				        fn:contains(sessionScope.USER.entitlements, 'NOMINATED_POST_OVERVIEW_ENTITLEMENT') ||
						 fn:contains(sessionScope.USER.entitlements, 'CREATE_NOMINATED_POST_ENTITLEMENT_ADMIN_GROUP')}">
									<li>
										<a href="#"><i class="fa fa-envelope ico-white"></i><span>&nbsp;&nbsp;Nominated Post</span></a>
										 <h2><i class="fa fa-envelope ico-white line_heightDiv"></i>Nominated Post</h2>
										 <ul>
										 	<c:if test="${fn:contains(sessionScope.USER.entitlements, 'CREATE_NOMINATED_POST_ENTITLEMENT')}">
										  <li>
										<a href="nominatedPostProfileAction.action"><i class="fa fa-pencil-square-o ico-white"></i><span>&nbsp;&nbsp;Apply Nominated Post</span></a>
										</li>
										<li>
										<a href="applyQuickApprovalNominatedPostAction.action"><i class="fa fa-file ico-white"></i><span>&nbsp;&nbsp;Apply Quick Approval Post</span></a> 
										</li>
										</c:if>
											<c:if test="${fn:contains(sessionScope.USER.entitlements, 'NOMINATED_POST_OVERVIEW_ENTITLEMENT')}">
										  <li>
										<a href="nominatedPostApplicationReviewAction.action"><i class="fa fa-qrcode  ico-white"></i><span>&nbsp;&nbsp;Nominated Posts Overview</span></a>
										</li>
										<li>
										<a href="nominatedPostMainDashboardAction.action"><i class="fa fa-pie-chart ico-white"></i><span>&nbsp;&nbsp;Nominated Posts Dashboard</span></a>
										</li>
										<li>
										<a href="updateNominationPostCandidateDetailsAction.action"><i class="fa fa-file ico-white"></i><span>&nbsp;&nbsp;Upload Documnets</span></a>
										</li>
				                           </c:if>
										   <c:if test="${fn:contains(sessionScope.USER.entitlements, 'NOMINATED_POST_QUICK_APPROVAL_ENTITLEMENT')}">
											   <li>
												<a href="applyQuickApprovalNominatedPostAction.action"><i class="fa fa-file ico-white"></i><span>&nbsp;&nbsp;Apply Quick Approval Post</span></a>
											   </li>
										   </c:if>
										   
										</li>
										 </ul>
										 <li>
											<a href="committeeDashBoardAction.action"><i class="fa fa-dashboard ico-white"></i><span>&nbsp;&nbsp;Committee Dashboard</span></a>
										</li>
									
									</c:if>
									<c:if test="${sessionScope.USER.isAdmin != 'true'}">
								<c:if test="${fn:contains(sessionScope.USER.entitlements, 'PARTY_MEETINGS_CREATE_USER_ENTITLEMENT') || fn:contains(sessionScope.USER.entitlements, 'PARTY_MEETINGS_CREATE_USER_ADMIN_ENTITLEMENT')}">
									<li>
										<a href="cadrePartyMeetingManagementAction.action"><i class="fa fa-qrcode ico-white"></i><span>&nbsp;&nbsp; create And Update Party Meetings</span></a>
									</li>
								</c:if>
							</c:if>
												
								<!--<li style="text-align: right">
									<a href="newCadreDashBoard2016Action.action"><i class="fa fa-dashboard ico-white"></i><span>&nbsp;&nbsp;2016 Cadre Dashboard</span></a>
								</li>-->
						<!--
							<c:if test="${fn:contains(sessionScope.USER.entitlements, 'CADRE_FIELD_MONITORING') || 
				        fn:contains(sessionScope.USER.entitlements, 'CADRE_FIELD_MONITORING_DASHBOARD') ||
						 fn:contains(sessionScope.USER.entitlements, 'CADRE_DATA_MONITORING') ||
						 fn:contains(sessionScope.USER.entitlements, 'CADRE_DATA_MONITORING_DASHBOARD') ||
						 fn:contains(sessionScope.USER.entitlements, 'CADRE_WEB_MONITORING_DASHBOARD')}">
									<li><!--
										<a href="#"><i class="fa fa-envelope ico-white"></i><span>&nbsp;&nbsp;Web Monitoring</span></a>
										 <h2><i class="fa fa-envelope ico-white line_heightDiv"></i>Web Monitoring</h2>
										 <ul>
											 <c:if test="${fn:contains(sessionScope.USER.entitlements, 'CADRE_FIELD_MONITORING')}">
												<li>
													<a href="fieldMonitoringNewAction.action"><i class="fa fa-qrcode  ico-white"></i><span>&nbsp;&nbsp;Field Monitoring</span></a>
												</li>
											</c:if>
											<c:if test="${fn:contains(sessionScope.USER.entitlements, 'CADRE_FIELD_MONITORING_DASHBOARD')}">
												<li>
													<a href="fieldMonitoringDashboardAction.action"><i class="fa fa-qrcode  ico-white"></i><span>&nbsp;&nbsp;Field Monitoring DashBoard</span></a>
												</li>
											</c:if>
											<c:if test="${fn:contains(sessionScope.USER.entitlements, 'CADRE_FIELD_MONITORING_DASHBOARD')}">
												<li>
													<a href="fieldMonitoringReportAction.action"><i class="fa fa-qrcode  ico-white"></i><span>&nbsp;&nbsp;Field Monitoring Report</span></a>
												</li>
											</c:if>
											<c:if test="${fn:contains(sessionScope.USER.entitlements, 'CADRE_DATA_MONITORING')}">
												<li>
													<a href="dataMonitoringAction.action"><i class="fa fa-qrcode  ico-white"></i><span>&nbsp;&nbsp;Data Monitoring</span></a>
												</li>
											</c:if>
											<c:if test="${fn:contains(sessionScope.USER.entitlements, 'CADRE_DATA_MONITORING_DASHBOARD')}">
												<li>
													<a href="dataMonitoringDashboardNewAction.action"><i class="fa fa-qrcode  ico-white"></i><span>&nbsp;&nbsp;Data Monitoring DashBoard</span></a>
												</li>
											</c:if>
											<c:if test="${fn:contains(sessionScope.USER.entitlements, 'CADRE_WEB_MONITORING_DASHBOARD')}">
												<li>
													<a href="webMonitoringDashboardAction.action"><i class="fa fa-qrcode  ico-white"></i><span>&nbsp;&nbsp;Web Monitoring DashBoard</span></a>
												</li>
											</c:if>
											<li>
												<a href="boothWiseDataMonitoringAction.action"><i class="fa fa-qrcode  ico-white"></i><span>&nbsp;&nbsp;Booth Wise Data Monitoring</span></a>
											</li>
										 </ul>
									</li>
									</c:if> -->
									
							
						<c:if test="${fn:contains(sessionScope.USER.entitlements,'ACCESS_USERS_CADRE_REGISTRATION_2016_DASHBOARD')}">
							<li>
								<a href="cadreRegistrationDetailsAction.action"><i class="fa fa-qrcode  ico-white"></i><span>&nbsp;&nbsp;2016 Cadre Dashboard</span></a>
							</li>
						</c:if>

							<c:if test="${sessionScope.USER.isAdmin != 'true'}">
								<c:if test="${fn:contains(sessionScope.USER.entitlements, 'PARTY_MEETING_THIRD_PARTY_UPDATION_ENTITLEMENT') || fn:contains(sessionScope.USER.entitlements, 'PARTY_MEETING_THIRD_PARTY_UPDATION_ADMIN_ENTITLEMENT')}">
									<li>
										<a href="finalizedPartyMeetingConductedAction.action"><i class="fa fa-qrcode  ico-white"></i><span>&nbsp;&nbsp;Update Party Meetings Conflicts</span></a>
									</li>
								</c:if>
							</c:if>
							<c:if test="${fn:contains(sessionScope.USER.entitlements, 'CORE_DASHBOARD_USER') || fn:contains(sessionScope.USER.entitlements, 'CORE_DASHBOARD_ADMIN_USER')}">
								<c:if test="${not fn:contains(sessionScope.USER.entitlements, 'ACCESS_USERS_CADRE_REGISTRATION_2016_DASHBOARD')}">
									<li>
										<a target="_blank" href="partyLeadersDashboardAction.action"><i class="fa fa-qrcode  ico-white"></i><span>&nbsp; Core Dashboard </span></a>
									</li>
									<li>
										<a target="_blank" href="getBoothInchargeDashboardAction.action"><i class="fa fa-qrcode  ico-white"></i><span>&nbsp; Booth Committee Dashboard </span></a>
									</li>
								</c:if>
							</c:if>
							<c:if test="${fn:contains(sessionScope.USER.entitlements, 'ACCESS_USERS_CADRE_REGISTRATION_2016_DASHBOARD')}">
									<li>
										<a href="partyLeadersDashboardAction.action"><i class="fa fa-qrcode  ico-white"></i><span>&nbsp; Core Dashboard </span></a>
									</li>
							</c:if>
							
							<c:if test="${sessionScope.USER.isAdmin != 'true'}">
								<c:if test="${fn:contains(sessionScope.USER.entitlements, 'BOOTH_INCHARGE_COMMITTEE_ENTITLEMENT')}">
										<li>
											<a href="electionBoothCommitteeAction.action"><i class="fa fa-qrcode  ico-white"></i><span>&nbsp;  Booth Committee Creation </span></a>
										</li>
								</c:if>
								<c:if test="${fn:contains(sessionScope.USER.entitlements, 'BOOTH_INCHARGE_COMMITTEE_ADMIN_ENTITLEMENT')}">
									<li>
										<a href="getBoothInchargeDashboardAction.action"><i class="fa fa-qrcode  ico-white"></i><span>&nbsp; Booth Committee Dashboard </span></a>
									</li>										
								</c:if>
							</c:if>	
							
							<c:if test="${sessionScope.USER.isAdmin == 'true'}">
								<c:if test="${fn:contains(sessionScope.USER.entitlements, 'BOOTH_INCHARGE_COMMITTEE_ADMIN_ENTITLEMENT')}">
									<li>
										<a href="getBoothInchargeDashboardAction.action"><i class="fa fa-qrcode  ico-white"></i><span>&nbsp; Booth Committee Dashboard </span></a>
									</li>										
								</c:if>
							</c:if>	
								<c:if test="${fn:contains(sessionScope.USER.entitlements, 'ITDP_DOOR_TO_DOOR_DASHBOARD_USER_ENTITLEMENT')}">
									<li>
										<a href="doorToDoorCampaignDashboardAction.action"><i class="fa fa-qrcode  ico-white"></i><span>&nbsp; Door To Door Dashboard </span></a>
									</li>										
								</c:if>
							<c:if test="${sessionScope.USER.isAdmin != 'true'}">
							    <c:if test="${ fn:contains(sessionScope.USER.entitlements, 'BLOOD_BANK_REGISTRATION_ENTITLEMENT' )  ||
							fn:contains(sessionScope.USER.entitlements, 'BLOOD_BANK_DASHBOARD_ENTITLEMENT' ) ||
							fn:contains(sessionScope.USER.entitlements, 'BLOOD_BANK_BLEEDING_ENTITLEMENT' ) ||
							fn:contains(sessionScope.USER.entitlements, 'BLOOD_BANK_REGISTRATION_ADMIN_ENTITLEMENT' ) ||
							fn:contains(sessionScope.USER.entitlements, 'BLOOD_BANK_DASHBOARD_ADMIN_ENTITLEMENT' ) ||
							fn:contains(sessionScope.USER.entitlements, 'BLOOD_BANK_BLEEDING_ADMIN_ENTITLEMENT' )}">
								<li>
									<a href="#"><img src='img/blood_donation.png' class='pull-left changeIconCode' style='width:16px;'/><span>&nbsp;&nbsp;BloodBank</span></a>
									<h2><i class="fa fa-wrench line_heightDiv"></i>BloodBank</h2>
								   <ul>
								   <c:if test="${  fn:contains(sessionScope.USER.entitlements, 'BLOOD_BANK_REGISTRATION_ENTITLEMENT' )  ||
									fn:contains(sessionScope.USER.entitlements, 'BLOOD_BANK_REGISTRATION_ADMIN_ENTITLEMENT' ) }">
										<li>
											<a href="bloodBankRegistrationAction.action"><img src='img/blood_donation.png' class='pull-left changeIconCode' style='width:16px;'/><span>&nbsp;&nbsp;BloodBank Registration</span></a>
										</li>
									</c:if>	
									<c:if test="${  fn:contains(sessionScope.USER.entitlements, 'BLOOD_BANK_DASHBOARD_ENTITLEMENT' )  ||
									fn:contains(sessionScope.USER.entitlements, 'BLOOD_BANK_DASHBOARD_ADMIN_ENTITLEMENT' ) }">						
												<li>
													<a href="bloodBankDashBoardAction.action"><img src='img/blood_donation.png' class='pull-left changeIconCode' style='width:16px;'/><span>&nbsp;&nbsp;Blood Bank DashBoard</span></a>
												</li>
									</c:if>	
									<c:if test="${ fn:contains(sessionScope.USER.entitlements, 'BLOOD_BANK_BLEEDING_ENTITLEMENT' )  ||
									fn:contains(sessionScope.USER.entitlements, 'BLOOD_BANK_BLEEDING_ADMIN_ENTITLEMENT' ) }">	
										<li>
											<a href="bloodBankBleadingAction.action"><img src='img/blood_donation.png' class='pull-left changeIconCode' style='width:16px;'/><span>&nbsp;&nbsp;BloodBank Bleading</span></a>
										</li>
										</c:if>		
								   </ul>
								</li>
							   </c:if>
							</c:if>			
							<!--
							<c:if test="${sessionScope.USER.isAdmin == 'true' &&fn:contains(sessionScope.USER.entitlements, 'VOTER_ANALYSIS' )}">
							<!--- Card Printing DashBoard 2016 start 
						<li>
						<!--
							<a href="#"><i class="fa fa-archive  ico-white"></i><span>&nbsp;&nbsp;Card Printing DashBoard 2016</span></a>
							 <h2><i class="fa fa-archive  ico-white line_heightDiv"></i>Card Printing DashBoard 2016</h2>
							 <ul>
								<li>
									<a href="cardPrintAdminAction.action"><i class="fa fa-bookmark  ico-white"></i><span>&nbsp;&nbsp;Cadre Data Pre Verification</span></a>
								</li>
								<li>
									<a href="cardPrintPostVerificationAction.action"><i class="fa fa-bookmark  ico-white"></i><span>&nbsp;&nbsp;Cadre Data Post Verification</span></a>
								</li>
								<li>
									<a href="cardPrintingStatusAction.action"><i class="fa fa-bookmark  ico-white"></i><span>&nbsp;&nbsp;Location Wise Card Print Status</span></a>
								</li>
								<li>
									<a href="cardPrintDispatchAction.action"><i class="fa fa-indent  ico-white"></i><span>&nbsp;&nbsp;Dispatch Status</span></a>
								</li>
								<li>
									<a href="cardPrintDashboardAction.action"><i class="fa fa-dashboard  ico-white"></i><span>&nbsp;&nbsp;DashBoard</span></a>
								</li>
							 </ul>
						</li>	
						
						</c:if>-->
						<!--<c:if test="${fn:contains(sessionScope.USER.entitlements, 'ALERT_CLARIFICATION_DASHBOARD_ENTITLEMENT') || fn:contains(sessionScope.USER.entitlements, 'ALERT_CLARIFICATION_DASHBOARD_ADMIN_ENTITLEMENT')}">
								<li>
									<a href="alertClarificationDashboardAction.action"><i class="fa fa-bell-o ico-white floatLeft"></i><span>&nbsp; Alert Clarification Dashboard </span></a>
								</li>
							</c:if>-->
							
						<!--- Card Printing DashBoard 2016 end -->						
					<c:if test="${sessionScope.loginStatus == 'out' && (sessionScope.hasFreeUserRole == true && sessionScope.hasPartyAnalystUserRole != true)}">
					 <li>
                        <a href="newlogoutAction.action"><i class="fa fa-sign-out"></i><span>&nbsp;&nbsp;Sign-out</span></a>
                       
                    </li></c:if>
					<c:if test="${sessionScope.loginStatus == 'out' && sessionScope.hasPartyAnalystUserRole == true}">  
					 <li>
                        <a href="newlogoutAction.action"><i class="fa fa-sign-out"></i><span>&nbsp;&nbsp;Sign-out</span></a>
                       
                    </li></c:if>
                </ul>
				
            </nav>
					</div>
           
    </div>
	
	
</header>
<section>
	<div>
		<decorator:body/>
	</div>
</section>
 <!-- /pusher -->	

<footer class="footerCls">
    <div class="text-center" style="text-align:center">
		    All &copy; Telugu Desam Party
    </div>
</footer>
		
		
		<!--<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>-->
		
		<script src="fullexpandcollapse/jquery.multilevelpushmenu.js"></script>
        <script type="text/javascript" src="fullexpandcollapse/fullexpandcollapse.js"></script>
<script>

function openAddNewProblemWindow()
{	
	var browser_addNewProblem = window.open("addNewProblemAction.action","addNewProblem","scrollbars=yes","height=100","width=100","left=200","top=200");	
	browser_addNewProblem.focus();
}
</script>
</body>
</html>