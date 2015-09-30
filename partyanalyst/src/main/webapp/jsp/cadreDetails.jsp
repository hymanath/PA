<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title> Cadre Details</title>
	<!-- custom CSS-->
	<link href="css/cadreCommitee/cadreDetails_custom.css" rel="stylesheet" type="text/css">
	
	<!-- Bootstrap -->
    <link href="js/cadreCommittee/dist/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
	
    	<!--Bootstrap DatePicker-->
    <link href="js/cadreCommittee/bootstrapDaterangepicker/daterangepicker-bs3.css" rel="stylesheet" type="text/css" />
		<!--Hover Menu-->
    <link href="css/cadreCommitee/css/jquery.smartmenus.bootstrap.css" rel="stylesheet" type="text/css" />
    	<!--Circle-->
    <link href="js/cadreCommittee/dist/css/jquery.circliful.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"/> 
	<link href="http://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
	
	<link href="js/scrollator/fm.scrollator.jquery.css" rel="stylesheet" type="text/css">
	<link href="dist/Icomoon/style.css" rel="stylesheet" type="text/css">
	<link href="dist/scroll/jquery.mCustomScrollbar.css" rel="stylesheet" type="text/css">
	<script src="js/grievance/statusColor.js" type="text/javascript"></script>
<style>
.inbox-messages
{
	color:#fff !important
}
.benefits-block
{
	padding:0px;
}
.benefits-block li
{
	border-bottom:1px solid #ccc;
	list-style:none;
	padding:8px;
}
.benefits-block li:last-child
{
	border-bottom:0px;
}
.panel-default .panel-heading
{
	border-radius:0px;
}
.countStyleSpan
{
	background-color:#ccc;
	border-radius:50%;
	font-size:14px;
	position:relative;
	top:-25px;
	height:25px;
	width:25px;
	line-height:25px;
	display:block;
	text-align:center;
}
.count-style
{
	background-color:#f4f4f4;
	border-radius:50%;
	font-size:12px;
	height:20px;
	line-height:20px;
	width:20px;
	display:block;
	text-align:center;
	margin-right:5px;
}
.pad_10
{
	padding:10px;
}
.hoverclassli
{
	padding:0px;
	cursor:pointer;
}
.hoverclassli li{list-style:none}
.hoverclassli:hover .hoverclassul
{
	display:block
}
.hoverclassul{
	cursor:pointer;
	display:none;
	font-size:14px;
	box-shadow:0px 0px 5px rgba(0,0,0,0.3);
	position:absolute;
	right:-122px;
	width:250px;
	z-index:999;
	background:#fff;
	height:200px;
	overflow-y:scroll;
	padding:0px;
}
.hoverclassul li
{
	padding:15px;
	border-bottom:1px solid #666
}
.hoverclassul li:last-child
{
	border-bottom:0px
}
.text-primary{
	color:#337ab7 !important;
}

.accordion-toggle , .accordion-toggle:active ,.accordion-toggle:hover
{
	color:#666 !important
}
.accordion-toggle:before {
    /* symbol for "opening" panels */
    font-family:'Glyphicons Halflings';
    content:"\e113";
    float: right;
    color: inherit;
	font-size:13px;
	margin-top:3px;
	margin-right:3px;
	font-weight:400 !important
}
.accordion-toggle.collapsed:before {
    /* symbol for "collapsed" panels */
    content:"\e114";
}

.Student-List
{
	padding:0px;
}
.Student-List li{list-style:none;}
table
{
    border-collapse: separate !important;
}
#mCSB_1_container{margin-right:10px !important}
.family-identity:after
{
	position:absolute;
	left:45px;
	background-image:url(dist/img/Card_member.png);
	content:' ';
	width:20px;
	height:20px;

}
.ul-li-scroll{height:230px;}
.right_arrow {
	position: relative;
	background: #fff;
	border: 1px solid #fff;
}
.right_arrow:after, .right_arrow:before {
	left: 100%;
	bottom: 25px;
	border: solid transparent;
	content: " ";
	height: 0;
	width: 0;
	position: absolute;
	pointer-events: none;
}

.right_arrow:after {
	border-color: rgba(255, 255, 255, 0);
	border-left-color: #fff;
	border-width: 5px;
	margin-top: -5px;
}
.right_arrow:before {
	border-color: rgba(255, 255, 255, 0);
	border-left-color: #fff;
	border-width: 6px;
	margin-top: -6px;
}
.show-dropdown:hover .count-hover
{
	display:block;
}

.show-dropdown
{
	cursor:pointer	
}
.count-hover
{
	position:absolute;
	padding:0px;
	width:450px;
	border-radius:3px;
    box-shadow: 0px 2px 10px 0px rgba(0, 0, 0, 0.35);
	background:#fff !important;
	display:none;
	z-index:999;
	margin-right:41%;
	bottom:-20px;
}
.count-hover li{list-style:none; width:430px !important}

#scrollator_holder{margin-left:25px}
.table-scroll{max-height:400px;overflow-y:auto;z-index:999999}
.table-scroll-1{max-height:400px;overflow-y:auto;z-index:999999}
.font-10{font-size:10px}
h1,h2,h3,h4,h5{color:#666 !important}
.bg_f9{background:#f9f9f9}
.pad_10{padding:10px;}
.panel-default .panel-heading{background:#CCC;}
.circle-text, .circle-info,  .circle-info-half {
    display: inline-block;
    font-size: 12px;
    position: absolute;
    text-align: center;
    top: 50%;
    width: 100%;
}
canvas {
    padding-left: 0;
    padding-right: 0;
    margin-left: auto;
    margin-right: auto;
    display: block;
}
.circle-text-half{font-size: 11px !important; margin-top: 26px;}
.pad_0
{
	padding:0px;
}
.m_0{margin:0px;}
.textTransFormCls{text-transform:capitalize}

.notverified{background-color:rgb(214,77,84);}
.verified{background-color:rgb(72,21,87);}
.notEligible{background-color:rgb(102,51,0);}
.inProgress{background-color:rgb(102,205,204);}
.notpossible{background-color:rgb(255,153,51);}
.approved{background-color:rgb(105,167,143);}
.completed{background-color:rgb(0,117,125);}

.notverified-text{color:rgb(214,77,84);}
.verified-text{color:rgb(72,21,87);}
.notEligible-text{color:rgb(102,51,0);}
.inProgress-text{color:rgb(102,205,204);}
.notpossible-text{color:rgb(255,153,51);}
.approved-text{color:rgb(105,167,143);}
.completed-text{color:rgb(0,117,125);}

.enrolled-mem
{
	padding:0px;
}
.enrolled-mem li
{
	border:1px solid #666;
	border-radius:3px;
	padding:3px;
	display:inline-block
}
.enrolled-mem li.yes
{
	background-color:#D7F0DB;
}
.enrolled-mem li.yes span:after
{
	content:'\e013';
	position: relative;
	top: 1px;
	display: inline-block;
	font-family: 'Glyphicons Halflings';
	font-style: normal;
	font-weight: 400;
	line-height: 1;
	color:#666;
	-webkit-font-smoothing: antialiased;
	-moz-osx-font-smoothing: grayscale
}
.enrolled-mem li.no
{
	background-color:#E3C5C7;
}
.enrolled-mem li.no span:after
{
	content:'\e014';
	position: relative;
	top: 1px;
	display: inline-block;
	font-family: 'Glyphicons Halflings';
	font-style: normal;
	font-weight: 400;
	line-height: 1;
	color:#666;
	-webkit-font-smoothing: antialiased;
	-moz-osx-font-smoothing: grayscale
}
table.gridtable {
	font-family: arial,sans-serif;
	font-weight:bold;
	font-size:13px;
	color:#333333;
	border-width: 1px;
	border-color: #666666;
	border-collapse: collapse;
	text-align:center;
}
table.gridtable th {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #666666;
	background-color: #dedede;
}
table.gridtable td {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #666666;
	background-color: #ffffff;
}
</style>
	
	<!--<style type="text/css">
			.block{background-color:#fff !important;margin-top:30px;}
			body{background:#d9dbd4 none repeat scroll 0 0}
			.pad_10{padding:10px;}
		</style>  -->
		
<script>
var globalCadreId = '${cadreId}';

</script>

</head>
<body>
<section>
	<div class="container">
    	<div class="row">
        	<div class="col-md-4 bg_white" style="padding-top:10px;">
			<input type="hidden" value="" id="cadreBoothId" />
			<input type="hidden" value="" id="cadrePartNo" />
			<input type="hidden" value="" id="cadrePanchaytId" />
			<input type="hidden" value="" id="cadremandalId" />
			<input type="hidden" value="" id="cadreRuralORUrbanId" />
			<input type="hidden" value="" id="cadreConstituencyId" />
			<input type="hidden" value="" id="cadrePConstituencyId" />
			<input type="hidden" value="" id="cadreDistrictId" />
			<input type="hidden" value="" id="cadreVoterCardNo" />
			<input type="hidden" value="" id="cadreMemberShipId" />
			
            	<table class="table table-bordered">
                	<tr>
                    	<td class="text-bold"><i class="glyphicon glyphicon-user"></i> PERSONAL DETAILS</td>
                    </tr>
                    <tr>
                    	<td>
                        	<div class="media">
                            	<div class="media-left">
                                	<div id="imagePathId">
										<img id="dataLoadingsImgForImagePath" src="images/icons/loading.gif" style="width:25px;height:20px;display:none;"/>
									</div>
									<div class="font-10" style="padding-left:6px;padding-top:6px">
                                        <i class="glyphicon glyphicon-star"></i>
                                        <i class="glyphicon glyphicon-star"></i>
                                        <i class="glyphicon glyphicon-star"></i>
                                        <i class="glyphicon glyphicon-star-empty"></i>
                                        <i class="glyphicon glyphicon-star-empty"></i>
                                    </div>
									<!--<img src="dist/img/profile.png" class="img-responsive img-rounded" alt="Profile Image">-->
                                </div>
                                <div class="media-body">
                                	<p class="m_0"><strong>NAME</strong> : <span id="nameId"></span></p>
                                    <p class="m_0"><strong>AGE</strong> : <span id="ageId"></span></p>
                                    <p class="m_0"><strong>DOB</strong> : <span id="dobId"></span></p>
                                    <p class="m_0"><strong>QUALIFICATION</strong> : <span id="qualificationId"></span></p>
                                    <p class="m_0"><strong>OCCUPATION</strong> : <span id="occupationId"></span></p>
                                    <p class="m_0"><strong>CASTE</strong> : <span id="casteFormalId"></span></p>
                                    <p class="m_0"><strong>REGISTERED ON</strong>: <span id="registeredOnId"></span></p>
                                    <p class="m_0"><strong>REG. THROUGH</strong>: <span id="registeredAtId"></span></p>
                                </div>
                            </div>
                        </td>
                    </tr>
                    <tr>
                    	<td>
                        	<i class="glyphicon glyphicon-phone"></i> <span id="mobileNoId"></span> 
                        	<span class="pull-right" id="emailMainSpanId">
	                            <i class="glyphicon glyphicon-envelope"></i> <span id="emailSpanId"></span> 
                            </span>
                        </td>
                    </tr>
                   <!-- <tr style="display:none;" class="enrollmentCls">
						<td class="text-bold"><i class="glyphicon glyphicon-bookmark"></i> PREVIOUS ENROLEMENTS</td>
					</tr>
					<tr style="display:none;" class="enrollmentCls">
					  <td>
						<ul class="list-inline" id="cadreYearsUlId">
						  <li class="badge">2010</li> 
						  <li class="badge">2012</li>  
						 </ul>
					  </td>
					</tr>-->
					<tr class="enrollmentCls">
						<td class="text-bold"><i class="glyphicon glyphicon-bookmark"></i> PREVIOUS ENROLEMENTS</td>
					</tr>
					<tr class="enrollmentCls">
					  <td>
						<ul class="enrolled-mem" id="enrollementDiv">
						  
						 </ul>
					  </td>
					</tr>
					<tr>
                    	<td class="text-bold"><i class="icon-articles"></i> IDENTITY</td>
                    </tr>
                    <tr>
                    	<td>
                        	<p class="m_0">MEMBERSHIP ID : <span id="memberShipNoId"></span></p>
                            <p class="m_0">VOTER CARD NO : <span id="voterIdSpan"></span>&nbsp;&nbsp;(<span class="text-success" id="isFamilyId"></span>)</p>
                            <p class="m_0">PARTY POSITION : <span id="positionId"></span></p>
                            <p class="m_0">PUBLIC REPRESENTATIVE : <span id="representativeId"></span></p>
							<!-- <div id="participatedDivId" style="display:none"><p class="m_0"> PARTICIPATED CONSTITUENCY : <span id="participatedConstId"></span></p></div> -->
                        </td>
                    </tr>
					<tr>
                    	<td class="text-bold"><i class="glyphicon glyphicon-map-marker"></i> ADDRESS</td>
                    </tr>
                    <tr>
                    	<td>
                        	<p class="m_0">H NO :<span id="houseNoId"></span></p>
                            <p class="m_0">PANCHAYAT : <span id="panchayatId"></span></p>
                            <p class="m_0">MANDAL : <span id="mandalId"></span></p>
                            <p class="m_0">CONSTITUENCY : <span id="constituencyId"></span></p>
                            <p class="m_0">DISTRICT : <span id="districtNoId"></span></p>
                            <p class="m_0">STATE : <span id="stateNoId"></span></p>
                        </td>
                    </tr>
                </table>
				<div class="panel panel-default" id="participatedDivId" style="display:none">
                	<div class="panel-heading">
                    	<h4 class="panel-title text-bold"><i class="glyphicon glyphicon-file"></i> PARTICIPATED CONSTITUENCY</h4>
                    </div>
					<div class="panel-body">
                    	<h5 class="m_0">Location : <span id="participatedConstId" class="text-bold"></span></h5>
                     </div> 
                </div>
            	<div class="panel panel-default">
                	<div class="panel-heading" role="tab" id="headingTwo12">
								<a class="accordion-toggle" role="button" data-toggle="collapse" data-parent="#accordion12" href="#collapseTwo12" aria-expanded="false" aria-controls="collapseTwo12">	
								<h4 class="panel-title" style="font-weight:400">
									<i class="glyphicon glyphicon-user"></i>
										CANDIDATE GRIEVANCE DETAILS
									<span class="pull-right"><span class="count-style" id="candidateTotalComplaintsDiv">01</span></span>
								</h4>
								</a>
							</div>
					<!-- <center><strong>Grievance Details Not Available.</strong></center> -->
                    <div class="panel-body">
					  <div id="complaintCountDiv">
						</div>
                    	<!--<h5 class="m_0">TOTAL COMPLAINTS <span id="totalComplaintsId" class="text-bold"></span></h5>-->
                        <div class="display-style" id="complaintsDiv">
                          <!--  <div id="donutchart" class="display-style" style="height: 120px;float:left;width:150px;"></div>
                            <ul class="display-style pull-right graph-list" style="padding-right:20px;padding-left:0px;" id="complaintStatusUL">
                            </ul>-->
                        </div>
						<div id="financialDiv" class="m_top20">
					   
						</div>
                    </div> 
                </div>
            	<div class="panel panel-default">
                	<div class="panel-heading">
					  
                    	<h4 class="panel-title text-bold"><img src="images/family_icon.png"> FAMILY GRIEVANCE DETAILS
						 <span class="pull-right"><span class="count-style" id="totalFamilyComplaints">0</span></span>
						</h4>
						 
                    </div>
					 <div class="panel-body pad_0">
					 <img id="familyMemberImg" src="images/icons/loading.gif" style="width:45px;height:45px;margin-left:45%">
						<div  id="familyMemberDiv"></div>
                   		<!--<h5>TOTAL FINANCE REQUESTS <span id="headingId" class="text-bold"></span></h5>
                    	<div id="donutchart2" class="display-style" style="height: 120px;float:left;width:90px;"></div>
                    <ul class="display-style pull-right piechart-list pad_0" id="financeSupportUL">
                        
                    </ul>-->
					
                    </div>
                </div>
				<div id="ntrTrustDivId">
					
				</div>
				<div>
				  <div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<img src="images/User_icon_BLACK-01.png"> 
							CADRE BENEFITS
						</h4>
					</div>
						<div class="panel-body pad_0">
						<ul class="benefits-block">
						<center><img class="dataLoadingsImgForTabSection" src="images/icons/loading.gif" style="width: 50px; height: 50px;display:none;"></center>
						<li>APPROVED FINANCIAL SUPPORT <span class="pull-right" id="candidateApprovedAmount">0</span></li>
						<li>DEATH INSURANCE REQUESTS <span class="pull-right" >
							<ul  class="hoverclassli">
								<li id="candidateDeathInsurance">0
									
								</li>
							</ul>
							
							</span></li>
						<li>HOSPITALIZATION INSURANCE REQUESTS <span class="pull-right" >
							<ul  class="hoverclassli">
								<li id="candidateHospitalizationInsurance">0
									
								</li>
							</ul>
							
							</span>
							</li>
						<li>
							<span id="cadreIdSpanForEducationBenefit"></span>	
						</li>
						</ul>
					</div>
				  </div>
				  <div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
						<img src="images/family_icon.png">
							FAMILY BENEFITS
						</h4>
					</div>
					<div class="panel-body pad_0">
						<ul class="benefits-block">
						<center><img class="dataLoadingsImgForTabSection" src="images/icons/loading.gif" style="width: 50px; height: 50px;display:none"></center>
						<li>APPROVED FINANCIAL SUPPORT <span class="pull-right" id="familyApprovedAmount">0</span></li>
						<li>DEATH INSURANCE REQUESTS <span class="pull-right" >
							<ul  class="hoverclassli">
								<li id="familyDeathInsurance">0
								</li>
							</ul>
							</span></li>
						<li>HOSPITALIZATION INSURANCE REQUESTS<span class="pull-right" >
							<ul  class="hoverclassli">
								<li id="familyHospitalizationInsurance">0
									
								</li>
							</ul>
							
							</span></li>
						<li>
							<span id="familyIdSpanForEducationBenefit"></span>	
							
						</li>
						</ul>
					</div>
				  </div>
				</div>
                <div class="panel panel-default">
                	<div class="panel-heading">
                    	<h4 class="panel-title text-bold"><i class="glyphicon glyphicon-flash"></i> DEATHS AND HOSPITALIZATION</h4>
                    </div>
					<!--<center>Deaths And Hospitalization Details Not Available.</center> -->
					<center><img id="dataLoadingsImgForDeathCount" src="images/icons/loading.gif" style="width: 50px; height: 50px;"></center>
					<div id="deathHospitalDivId">
					</div>
					
                </div>
				<!--<div class="row">
					<div class="col-md-12 col-xs-12">
						<div class="panel panel-default">
							<div class="panel-heading ivrDivId" onclick="getIVRDetails();">
								<div title="Click here for Ivr Details" id="ivrId"> 
								<h4 class="panel-title text-bold" type="button" data-toggle="modal" data-target=".modalForIVR" style="cursor:pointer">
									<i class="glyphicon glyphicon-stats"></i>&nbsp;&nbsp;&nbsp;IVR DETAILS
								</h4>
								</div>
								</div>
						</div>
					</div>
				</div>-->
                <div class="panel panel-default">
                	<div class="panel-heading">
                    	<h4 class="panel-title text-bold">
                        	<img src="dist/img/family-icon.png">&nbsp;&nbsp;&nbsp;FAMILY MEMBERS
                        </h4>
                    </div>
                    <div class="panel-body">
					
					<center><img style="width: 25px; height: 25px;display:none;" src="images/icons/loading.gif" id="dataLoadingsImgForFamilyMembers"/></center>
                    	<div class="family-members" id="familyMembersDiv">
                          
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-md-8">
            	<div class="panel panel-default">
                	<div class="panel-heading">
                    	<h4 class="panel-title text-bold"><i class="glyphicon glyphicon-sort-by-attributes" style="transform: rotate(270deg);"></i>&nbsp;&nbsp;&nbsp;CADRE MEMBER BOOTH PERFORMANCE</h4>
                    </div>
                    <div class="panel-body">
                    	<div class="panel-group electionPerformanceDiv" id="accordion" role="tablist" aria-multiselectable="true">
                          
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                	<div class="panel-heading">
                    	<h4 class="panel-title text-bold"><i class="glyphicon glyphicon-stats"></i>&nbsp;&nbsp;&nbsp;2014 CADRE ENROLMENT STATS</h4>
                    </div>
                    <div class="panel-body" style="padding:0px 15px;">
                    	<div class="row table-responsive" id="memberShipCountDiv"><!--id="memberShipCountDiv"-->
						   
                        </div>
                    </div>
				
                </div>
                <div class="panel panel-default">
                	<div class="panel-heading">
                    	<h4 class="panel-title text-bold"><i class="glyphicon glyphicon-record"></i>&nbsp;&nbsp;&nbsp;&nbsp;CADRE PARTICIPATION ACTIVITIES</h4>
                    </div>
                    <div class="panel-body">
                    	<div class="row">
                        	<div class="col-md-4 col-xs-12" id="eventInvitatinDiv">
                            	
                            </div>
                            <div class="col-md-4 col-xs-12">
                            	<table class="table m_0 table-bordered">
                                	<thead>
                                    	<th class="text-center" colspan="3">TRAINING'S</th>
                                    </thead>
                                    <tr class="text-center">
                                    	<td id="totalInvitedCountId">0<br/>Invited</td>
                                        <td id="totalAttendedCountId">0<br/>Attended</td>
                                        <td id="totalAbsentedCountId">0<br/>Absent</td>
                                    </tr>
                                </table>
                            </div>
							 <div class="col-md-4 col-xs-12" id="partyMeetingDescDiv">
                            	
                            </div>
							<div class="col-md-12 col-xs-12 col-sm-12" style="margin-top:10px;">
								<div class="panel-group m_top10" id="accordion2323" role="tablist" aria-multiselectable="true">
								  <div class="panel panel-default" id="eventParticipationCollapseDivId" style="display:none;">
									<div class="panel-heading" role="tab" id="headingOne">
									  <a role="button" class="collapsed accordion-toggle" data-toggle="collapse" data-parent="#accordion2323" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
										<h4 class="panel-title">
										  <span>EVENT PARTICIPATION DETAILS</span>
										</h4>
									   </a>
									</div>
									<div id="collapseOne" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">
									  <div class="panel-body">
										<div id="participationTableDivId" class="table-responsive"></div>
									  </div>
									</div>
								  </div>
								  <div class="panel panel-default" id="trainingDetailsMainDivId" style="display:none">
									<div class="panel-heading" role="tab" id="headingTwo">
										<a class="collapsed accordion-toggle" role="button" data-toggle="collapse" data-parent="#accordion2323" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
										<h4 class="panel-title">
										 TRAINING CAMP PARTICPATION DETAILS
										 </h4>
										</a>
									</div>
									<div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
									  <div class="panel-body">
										<table class="table table-bordered">
											<thead>
												<th>Program Name</th>
												<th>INVITED</th>
												<th>ATTENDED</th>
												<th>ABSENT</th>
												<th></th>
											</thead>
											<tbody id="trainingDetailsBodyId">
												
											</tbody>
										</table>
									  </div>
									</div>
								  </div>
								  <div class="panel panel-default" id="partyMeetingDetailsShowHideDiv" style="display:none">
									<div class="panel-heading" role="tab" id="headingThree">
										<a class="collapsed accordion-toggle" role="button" data-toggle="collapse" data-parent="#accordion2323" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
										<h4 class="panel-title">
										 <span id="partymeetingTitledivId"> PARTY MEETING PARTICIPATION DETAILS</span>
										 </h4>
										</a>
									</div>
									<div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
									  <div class="panel-body">
										<div id="partymettingParlDivId" style="display:none;" >
									
											<div id="partyMetindetlsDivId"></div>
										</div>
									  </div>
									</div>
								  </div>
								</div>
							</div>
                         <!--   <div class="col-md-12 col-xs-12" id="participationTableMainDivId" style="display:none;">
								<h4 style="border-bottom:1px solid #999">Events Participation Details</h4>
								<div id="participationTableDivId" class="table-responsive">
								</div>
                            </div>-->
						
							<!--
							 <div class="col-md-12 col-xs-12" id="partymettingParlDivId" style="display:none;" >
								<!--<h4 style="border-bottom:1px solid #999">Party Meetings Participation Details</h4> -->
								<!--<div id="partyMetindetlsDivId"></div>
								<!--<table class="table m_0 table-bordered">
                                	<thead>
                                    	<th class="text-center">MEETING TYPE </th>
                                    	<th class="text-center"> INVITED </th>
                                    	<th class="text-center"> ATTENDED </th>
                                    	<th class="text-center"> ABSENT </th>
                                    </thead>
                                    <tr class="text-center">
                                    	<td> STATE-PUBLIC REPRESENTATIVES MEETING </td>
                                        <td> <ul class="list-inline"><li class="show-dropdown">2
											<ul class="count-hover right_arrow">
												<li>
													<table class="table table-bordered table-hover">
														<tr>
															<td>sdf</td>
															<td>dfg</td>
														</tr>
													</table>
												</li>
											</ul> </td>
                                        <td> 1 </td>
                                        <td> 0 </td>
                                    </tr>
                                </table>
								
								</div> -->
                            </div>
							
                        </div>
                    </div>
                
                <div class="panel panel-default" id="electionProfileMainDivId">
                	<div class="panel-heading">
                    	<h4 class="panel-title text-bold"><i class="glyphicon glyphicon-folder-open"></i>&nbsp;&nbsp;&nbsp;CADRE ELECTION PROFILE</h4>
                    </div>
                    <div class="panel-body">
                    	<div class="cadre-election" >
						<div id="electionProfileDivId"> </div>
                        	
                        </div>
                    </div>
                </div>
				
                <div class="panel panel-default">
                	<div class="panel-heading">
					<a id="" class="showbtnCls" title="Click here to Show Committee Details" href="javascript:{newsHideAndShow('newsMainDivId');}">
                    	<h4 class="panel-title text-bold" style=""><i class="glyphicon glyphicon-list-alt"></i>&nbsp;&nbsp;&nbsp;NEWS
						 <i class="pull-right glyphicon glyphicon-triangle-top "></i>							
						</h4>
						</a>
						<a id="" class="newsMainDivId" style="display:none;" title="Click here to Hide Committee Details" href="javascript:{newsHideAndShow('newsMainDivId');}">
						<h4 class="panel-title text-bold" style=""><i class="glyphicon glyphicon-list-alt"></i>&nbsp;&nbsp;&nbsp;NEWS
							 <i class="pull-right glyphicon glyphicon-triangle-bottom "></i>
						</h4>
						</a>
						<div class="pull-right dateCls" style="margin-top: -15px;margin-right: 25px;display:none;">
							<div class="calendar-style"  id="reportrange">
								<div class="caret"></div>
								<i class="glyphicon glyphicon-calendar"></i>
							</div>
						</div>
					</div>
					
					<div class="panel-body" id="newsMainDivId" style="display:none;">
                    	
						
					<center><img style="width: 100px; height: 100px;margin-top:50px" src="images/icons/loading.gif" id="dataLoadingsImgForNewsId"/></center>
					<div id="hideShowNewsDiv" style="display:none;">
                        <div class="panel panel-default">
                        	<div class="panel-heading bg_f9">
                            	<h4 class="panel-title text-bold text-center">
                                	CANDIDATE NEWS
                                </h4>
                            </div>
							<div id="candidateCategoryWiseNewsId"></div>
                         </div>
						
						
						
                        <div class="panel panel-default">
                        	<div class="panel-heading bg_f9">
                            	<h4 class="panel-title text-bold text-center">CANDIDATE LOCATION SPECIFIC NEWS</h4>
                            </div>
                            <div class="panel-body pad_0">
                            	<div class="row pad_10"  >
                                	<div class="col-md-12" style="margin-bottom:10px;" >
										<!-- -->
											<label class="radio-inline">
												<input type="radio" name="radio" id="districtRadioNewsId" class="newsRadioCls">District
											</label>
											<label class="radio-inline">
												<input type="radio" name="radio" id="pConstiRadioNewsId" class="newsRadioCls">Parliament Constituency
											</label>
											<label class="radio-inline hidingAssemlyCls">
												<input type="radio" name="radio" id="aConstiRadioNewsId" class="newsRadioCls">Assembly Constituency
											</label>
											<label class="radio-inline hidingMandalCls" style="display:none;">
												<input type="radio" name="radio" id="mandalRadioNewsId" class="newsRadioCls">Mandal
											</label>
											<label class="radio-inline hidingMandalCls" style="display:none;">
												<input type="radio" name="radio" id="panchayatRadioNewsId" class="newsRadioCls">Panchayat
											</label>
										<!-- -->
									</div>
                                	<div class="col-md-12">
										<div class="row">
											<div class="col-md-12" id="propertiesId"></div>
										</div>
									</div>
									
                                	
                                    
                                </div>
								
                            </div>
                        </div>
						<div id="issuesMainDiv" class="row">
							<div class="col-md-12 col-xs-12">
								<div class="panel panel-default">
									<div class="panel-heading bg_f9">
										<h4 class="panel-title text-bold ">DEPARTMENT WISE ISSUES SUMMARY<span id="issuesCount" class="pull-right">TOTAL COUNT - 0</span></h4>
									</div>
									<div class="panel-body pad_0">
										<div class="table-scroll">
											<table id="issuesSummary" class="table m_0 m_0">
												
											</table>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
                    </div>
                </div>
            </div>
        </div>
		<div class="row m_top20">
        	<div class="col-md-12 col-xs-12">
            	<div class="panel panel-default">
                	<div class="panel-heading">
					<a href="javascript:{hideAndShowCommittee(1);}" title="Click here to Show Committee Details" id="showId">
                    	<h4 class="panel-title">
                        	<i class="glyphicon glyphicon-briefcase"></i>&nbsp;&nbsp;&nbsp;COMMITTEES 
							 <i class="pull-right glyphicon glyphicon-triangle-top "></i>							
                        </h4>
						</a>
						<a href="javascript:{hideAndShowCommittee(2);}" title="Click here to Hide Committee Details" style="display:none;" id="hideId">
							<h4 class="panel-title">
                        	<i class="glyphicon glyphicon-briefcase"></i>&nbsp;&nbsp;&nbsp;COMMITTEES 
							 
							 <i class="pull-right glyphicon glyphicon-triangle-bottom "></i>
                        </h4>
						</a>
                    </div>
					<!--<center><h3>Data Not Available.</h3></center>-->
                    <div class="panel-body" id="committeesDivId" style="display:none;"> 
                    	<div>
                        	<label class="radio-inline urbanClass">
                            	<input type="radio" name="committeeLocation" class="committeeLocCls" value="panchayat">Village/Ward
                            </label>
                            <label class="radio-inline urbanClass">
                            	<input type="radio" name="committeeLocation" class="committeeLocCls" value="mandal">Mandal/Town/Division
                            </label>
                            <label class="radio-inline">
                            	<input type="radio" name="committeeLocation" class="committeeLocCls" value="assemblyConstituency">Assembly Constituency
                            </label>
                            <label class="radio-inline">
                            	<input type="radio" name="committeeLocation" class="committeeLocCls" value="parliamentConstituency">Parliament Constituency
                            </label>
                            <label class="radio-inline">
                            	<input type="radio" name="committeeLocation" class="committeeLocCls" value="district" checked>District
                            </label>
                        </div>
                        <div class="table m_0-responsive m_top10 table-responsive" id="committeesCountDiv">
                            <table class="table m_0 table-bordered">
                                <thead>
                                    <tr>
                                        <th></th>
                                        <th class="text-center" colspan="4">Main Committees</th>
                                        <th class="text-center" colspan="4">Affliated Committees</th>
                                    </tr>
                                    <tr>
                                    	<th></th>
                                    	<th>Total</th>
                                        <th>Started</th>
                                        <th>Completed</th>
                                        <th>Members</th>
                                        <th>Total</th>
                                        <th>Started</th>
                                        <th>Completed</th>
                                        <th>Members</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>Village Committees</td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td>Mandal Committees</td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td>District Committees</td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
		<!--<div class="col-md-12 m_top10 pad_10 block" id="surveyDetailsMainDivId">
				<h4 style="border-bottom:1px solid #999">Survey Details</h4>
					
		</div>-->
		 <div class="row">
        	<div class="col-md-12 col-xs-12">
            	<div class="panel panel-default">
                	<div class="panel-heading">
					<a href="javascript:{hideAndShowSurvey(1);}" title="Click here to Show Committee Details" id="surveyshowId"> 
                    	<h4 class="panel-title text-bold"><i class="glyphicon glyphicon-stats"></i>&nbsp;&nbsp;&nbsp;SURVEY DETAILS
							<i class="pull-right glyphicon glyphicon-triangle-top "></i>
						</h4>
						</a>
							<a href="javascript:{hideAndShowSurvey(2);}" title="Click here to Hide Committee Details" style="display:none;" id="surveyhideId">
						<h4 class="panel-title text-bold"><i class="glyphicon glyphicon-stats"></i>&nbsp;&nbsp;&nbsp;SURVEY DETAILS
							 <i class="pull-right glyphicon glyphicon-triangle-bottom "></i>
						</h4>
						</a>
                    </div>
                    <div class="panel-body" id="surveyDetailsId" style="display:none;">
						<h4 style="font-weight:bold;margin:5px;">TAB SURVEY'S</h4>
						<div class="surveyDetailssCls">	</div>
						<div class="surveyDetailsCls">	</div>
						
						<center><img id="surveyDataLoadoing" src="images/icons/survey-details.gif" style="width:250px;height:200px;display:none;"/></center>
                    	<div id="surveyDetailsMainDivId" class=""></div>
						
						<h4 style="font-weight:bold;margin-top:20px;">IVR SURVEY'S</h4>
						<div class="ivrDetailsCls" id="ivrsurveyDetailsId"></div>
						<center><img id="ivrsurveyDataLoadoing" src="images/icons/survey-details.gif" style="width:250px;height:200px;display:none;"/></center>
						
                    </div>
                </div>
            </div>
        </div>
		
		<!-- <div class="row">
        	<div class="col-md-12 col-xs-12">
            	<div class="panel panel-default">
                	<div class="panel-heading">
					<a href="javascript:{hideAndShowIVRSurvey(1);}" title="Click here to Show IVR Details" id="ivrsurveyshowId"> 
                    	<h4 class="panel-title text-bold"><i class="glyphicon glyphicon-stats"></i>&nbsp;&nbsp;&nbsp;IVR DETAILS
							<i class="pull-right glyphicon glyphicon-triangle-top "></i>
						</h4>
						</a>
							<a href="javascript:{hideAndShowIVRSurvey(2);}" title="Click here to Hide IVR Details" style="display:none;" id="ivrsurveyhideId">
						<h4 class="panel-title text-bold"><i class="glyphicon glyphicon-stats"></i>&nbsp;&nbsp;&nbsp;IVR DETAILS
							 <i class="pull-right glyphicon glyphicon-triangle-bottom "></i>
						</h4>
						</a>
                    </div>
                    <div class="panel-body ivrDetailsCls2" id="ivrsurveyDetailsId" style="display:none;">
						<center><img id="ivrsurveyDataLoadoing" src="images/icons/survey-details.gif" style="width:250px;height:200px;display:none;"/></center>
                    	<div id="ivrDetailsMainDivId" class=""></div>
                    </div>
                </div>
            </div>
        </div>-->
		
		<!--<div class="panel-group" id="accordion111" role="tablist" aria-multiselectable="true">
		<div class="panel panel-default">
				<div class="panel-heading" role="tab" id="headingOne111" onclick="getCandidateAndConstituencySurveyResult();">
					<a role="button" class="accordion-toggle" data-toggle="collapse" data-parent="#accordion111" href="#collapseOne111" aria-expanded="true" aria-controls="collapseOne111">
					<h4 class="panel-title text-bold" type="button"style="cursor:pointer"><i class="glyphicon glyphicon-stats"></i>
						&nbsp;&nbsp;&nbsp;CONSTITUENCY CANDIDATE SURVEY DETAILS
					</h4>
					</a>
				</div>
			<div id="collapseOne111" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne111">
				<div class="panel-body candiConstSurveyDetailsCls">
					
				  </div>
				</div>
			</div>
		</div>-->
		
		
      
    </div>
    <!-- model -->
<div class="modal fade myModalForDeath">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="deathTitleId"></h4>
      </div>
      <div class="modal-body">
        <div class="row">
			<div class="col-md-12">
				<div style="background: rgba(0, 0, 0, 0.1) none repeat scroll 0% 0%; border: medium none transparent; margin-bottom: 2px;" class="well well-sm">
				<center><img id="dataLoadingsImgForDeathPopUp" src="images/icons/loading.gif" style="width:50px;height:50px;display:none;margin-top:50px;"/></center>
				<div id="popupForDeathDetails" class="table-scroll-1"></div>	
				</div>
			</div>
		</div>
      </div>
      <div class="modal-footer">
       <button type="button" class="btn btn-default btn-success btn-sm" data-dismiss="modal">Close</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
	<div class="modal fade modalForSurvey">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header" style="background-color:#CCCCCC">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title" style="text-align:center;"><b>Survey Participation Details</b></h4>
				</div>
				<center><img id="familySurveyDataLoadoing" src="images/icons/survey-details.gif" style="width:250px;height:200px;display:none;"/></center>
				<div class="modal-body familySurveyDetailsCls">
				
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default btn-success btn-sm" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
	    <!-- model For Ntr Trust -->
<div class="modal fade modalForNtrTrust">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="ntrTrustTitleId"></h4>
      </div>
      <div class="modal-body">
        <div class="row">
			<div class="col-md-12">
				<div>
					<div>

					  <!-- Nav tabs -->
					  <ul class="nav nav-tabs" role="tablist">
						<li role="presentation" class="active 11"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">CADRE</a></li>
						<li role="presentation" class="22"><a href="#profile" aria-controls="profile" role="tab" data-toggle="tab">FAMILY</a></li>
					  </ul>

					  <!-- Tab panes -->
					  <div class="tab-content">
						<div role="tabpanel" class="tab-pane active" id="home">
							<center><img id="dataLoadingsImgForNtrTrust" src="images/icons/loading.gif" style="width:50px;height:50px;display:none;margin-top:50px;"/></center>
							<div id="ntrTrustDetails"></div>	
						</div>
						<div role="tabpanel" class="tab-pane" id="profile">
							<center><img id="dataLoadingsImgForNtrTrust" src="images/icons/loading.gif" style="width:50px;height:50px;display:none;margin-top:50px;"/></center>
							<div id="ntrTrustCadreFamilyDetails"></div>	
						</div>
					  </div>

					</div>
				</div>
			</div>
		</div>
      </div>
      <div class="modal-footer">
       <button type="button" class="btn btn-default btn-success btn-sm" data-dismiss="modal">Close</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
	
	<!--<div class="modal fade modalForIVR">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header" style="background-color:#CCCCCC">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title" style="text-align:center;"><b>IVR Details</b></h4>
				</div>
				<center><img id="ivrDataLoadoing" src="images/icons/loading.gif" style="width:100px;height:100px;display:none;"/></center>
				<div class="modal-body ivrDetailsCls1">
				
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default btn-success btn-sm" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>-->
	
	<!-- model For Training -->
<div class="modal fade modelForTrainingDetails">
  <div class="modal-dialog  modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" style="text-align:center;">TRAINING DETAILS</h4>
      </div>
      <div class="modal-body">
        <div class="row">
			<div class="col-md-12">
			<div id="trainingDetailsTableId"></div>
			<center><img id="dataLoadingsImgForTrainingDetails" src="images/icons/loading.gif" style="width:50px;height:50px;display:none;margin-top:50px;"/></center>
			</div>
			<div class="col-md-12">
			<div id="remarkDetailsId"></div>
			</div>
		</div>
      </div>
      <div class="modal-footer">
       <button type="button" class="btn btn-default btn-success btn-sm" data-dismiss="modal">Close</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
	
</section>
			<!-- model For benefits -->
		<div class="modal fade" id="modelForBenefitsId">
		  <div class="modal-dialog">
			<div class="modal-content">
			  <div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title" style="text-align:center;"><span class="modelTitleCls"><span></h4>
			  </div>
			  <div class="modal-body">
				<div class="row">
					<div class="col-md-12">
						<div id="benefitsEducationCountsId"></div>
					</div>
				</div>
				</div>
			  <div class="modal-footer">
			   <button type="button" class="btn btn-default btn-success btn-sm" data-dismiss="modal">Close</button>
			  </div>
			</div><!-- /.modal-content -->
		  </div><!-- /.modal-dialog -->
		</div><!-- /.modal -->
		
		<!-- model For benefits Of Family -->
		<div class="modal fade" id="modelForBenefitsFamilyId">
		  <div class="modal-dialog">
			<div class="modal-content">
			  <div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title" style="text-align:center;"><span class="modelTitleClsFamily"><span></h4>
			  </div>
			  <div class="modal-body">
				<div class="row">
					<div class="col-md-12">
						<div id="benefitsEducationCountsFamilyId"></div>
					</div>
				</div>
				</div>
			  <div class="modal-footer">
			   <button type="button" class="btn btn-default btn-success btn-sm" data-dismiss="modal">Close</button>
			  </div>
			</div><!-- /.modal-content -->
		  </div><!-- /.modal-dialog -->
		</div><!-- /.modal -->
		

	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script src=" https://code.jquery.com/ui/1.11.1/jqueryui/1.11.1/jquery-ui.js "></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/cadreCommittee/dist/js/bootstrap.min.js"></script>
	<!--Circle js file-->
	
    <script src="js/cadreCommittee/dist/js/jquery.circliful.min.js"></script>
    <!--Bootstrap Date Picker-->
   <script src="js/cadreCommittee/bootstrapDaterangepicker/moment.min.js" type="text/javascript"></script> 
	<script src="js/cadreCommittee/bootstrapDaterangepicker/daterangepicker.js" type="text/javascript"></script>   
    <!--Hover Menu-->
    <script src="js/cadreCommittee/js/jquery.smartmenus.min.js" type="text/javascript"></script>
    <script src="js/cadreCommittee/js/jquery.smartmenus.bootstrap.min.js" type="text/javascript"></script>
	 <script src="js/jquery.classyloader.min.js"></script>
    <script type="text/javascript" src="js/jquery.dataTables.js"></script>	
	<script type="text/javascript" src="js/highcharts/js/highcharts_cadre.js"></script>
	<!-- scrollator -->
	<script type="text/javascript" src="js/scrollator/fm.scrollator.jquery.js"></script>
	<script type="text/javascript" src="dist/scroll/jquery.mCustomScrollbar.js"></script>
	<script type="text/javascript" src="dist/scroll/jquery.mousewheel.js"></script>
	
	<script>
	
	
	var globalCadreId = '${cadreId}';
	
	
	var participatedConstituencyId=0;
	var participatedConstituencyType="";
	var participatedParliamentId = 0;
	var participatedDistrictId = 0;
	
	var participatedConstName = "";
	var participatedParlName = "";
	var participatedDistName = "";
	//getParticipatedConstituencyId(globalCadreId);
	
	//var globalCadreId;
	/*  $(document).ready(function() {
			globalCadreId='${param.cadreId}';
			//globalMembershipId='${param.memberShipId}';
			cadreFormalDetailedInformation(globalCadreId);
			//complaintDetailsOfCadre(globalCadreId,globalMembershipId);
			getEventDetailsOfCadre(globalCadreId);
			getTdpCadreSurveyDetails(globalCadreId,0,null);
			
			
	 }); */
	 
	 
	function getParticipatedConstituencyId(cadreId){
		var jsObj={
			tdpCadreId:cadreId
		}	
		$.ajax({
				type:'POST',
				 url: 'getParticipatedConstituencyAction.action',
				 data : {task:JSON.stringify(jsObj)} ,
				}).done(function(result){
					if(result !=null){
						//alert(1);
						participatedConstituencyId =result.id;
						participatedConstituencyType = result.electionType;
						participatedParliamentId = result.parlimentId;
						participatedDistrictId=result.districtId;
						
						participatedConstName = result.name;
						participatedParlName = result.parliament;
						participatedDistName = result.district;
						//alert(participatedConstituencyId);
						if(participatedConstituencyId != null && participatedConstituencyId > 0){
						//alert(1);
							$("#participatedDivId").show();
							$("#participatedConstId").html(""+result.name+"&nbsp;&nbsp;"+participatedConstituencyType+"");
						}else if(participatedParliamentId != null && participatedParliamentId > 0){
							$("#participatedDivId").show();
							participatedConstituencyType=" Parliament";
							$("#participatedConstId").html(""+result.name+"&nbsp;&nbsp;"+participatedConstituencyType+"");
						}
						
						getCategoryWiseStatusCount();
						getTotalMemberShipRegistrationsInCadreLocation();		
						//getCadreFamilyDetailsByCadreId();
						getElectionPerformanceInCadreLocation();
						getApprovedFinancialSupprotForCadre();
						cadreFormalDetailedInformation(globalCadreId);
						getEventDetailsOfCadre(globalCadreId);
						//getTdpCadreSurveyDetails(globalCadreId,0,null,"All",0);
						getLocationwiseCommitteesCount();
						getPartyMeetingsOverViewForCadre();
						getEventsOverviewFortdpCadre();
						//getCandidateAndConstituencySurveyResult();
						
					}
						
				});
	}
	 
	 //global Ids of CADRE
	 
	 var globalPanchayatId=0;
	 var globalTehsilId=0;
	 var globalConstituencyId=0;
	 var globalParliamentId=0;
	 var globalDistrictId=0;
	 
	 var globalPancName = "";
	 var globalTehsName = "";
	 var globalConstName = "";
	 var globalParlName = "";
	 var globalDistName = "";
	 
	 var globalCandidateId; 
	 var ownBoothDetailsVo;
	 var globalAreaType="";
		function cadreFormalDetailedInformation(globalCadreId){
			var localCadreId=globalCadreId;
			//loading images showing
			$("#dataLoadingsImgForownBoothDetailsId").show();
			$("#dataLoadingsImgForImagePath").show();
			
			$('#enrollementDiv').html('');
			
			//hiding divs of Election && and Grievance details
			$("#electionProfileMainDivId").hide();
			$("#grievanceDetailsMainDivId").hide();
			var jsobj={
				cadreId:localCadreId
			}
			$.ajax({
				type:'GET',
				 url: 'cadreFormalDetailedInformationAction.action',
				 data : {task:JSON.stringify(jsobj)} ,
			}).done(function(result){
				//loading images hiding
				 $("#dataLoadingsImgForownBoothDetailsId").hide();
				 $("#dataLoadingsImgForImagePath").hide();
				 
				var str='';
				var strEnrollment='';
				if(result !=null){
						$("#dataLoadingsImgForownBoothDetailsId").show();
						$("#dataLoadingsImgForImagePath").show();
						
				//nameId dobId ageId qualificationId occupationId voterIdSpan panchayatId mandalId constituencyId positionId representativeId
					/* start Survey Fields */	
									
					$('#cadreBoothId').val(result.boothId);				
					$('#cadrePartNo').val(result.partNo);				
					$('#cadrePanchaytId').val(result.panchayatId);
					$('#cadremandalId').val(result.tehsilId);	
					$('#cadreRuralORUrbanId').val(result.localElectionBody);						
					$('#cadreConstituencyId').val(result.constituencyId);	
					$('#cadrePConstituencyId').val(result.pconstituencyId);						
					$('#cadreDistrictId').val(result.districtId);
					$('#cadreVoterCardNo').val(result.voterIdCardNo);
					$('#cadreMemberShipId').val(result.membershipNo);
					/* end Survey Fields */
					
					 $("#nameId").html(result.name);
					 $("#dobId").html(result.dateOfBirth); 
					 $("#ageId").html(result.age);
					 $("#qualificationId").html(result.qualification);
					 $("#occupationId").html(result.occupation);
					 $("#voterIdSpan").html(result.voterIdCardNo);
					 
					 if(result.isFamilyVoterId =="false"){
						 $("#isFamilyId").html('<b>Own VoterCard</b>');
					 }else if(result.isFamilyVoterId == "true"){
						 $("#isFamilyId").html('<b>Family VoterCard</b>');
					 }
					 
					 $("#panchayatId").html(result.panchayatName);
					 $("#mandalId").html(result.tehsilName);
					 $("#constituencyId").html(result.constituencyName);
					 $("#positionId").html(result.partyPosition);
					 $("#representativeId").html(result.representativeType);
					 $("#mobileNoId").html(result.mobileNo);
					 $("#memberShipNoId").html(result.membershipNo);
					 $("#casteFormalId").html(result.casteName);
					 $("#registeredOnId").html(result.registeredTime);
					 $("#registeredAtId").html(result.registeredOn);
					 
					 
					 //previous enrollmetYears building
					/* var preEnrollemets=[];
					 var preEnrolleArray=new Array();
					 if(result.enrollmentYears !=null && result.enrollmentYears !=""){
						preEnrollemets = result.enrollmentYears.split(",");
					 }
					 
					 if(preEnrollemets !=null && preEnrollemets.length>0){
						 $(".enrollmentCls").show();
						 for(var e in preEnrollemets){
							  strEnrollment+='<li class="badge">'+preEnrollemets[e]+'</li> ';
						 }
						 $("#cadreYearsUlId").html(strEnrollment);
					 }
					 */
					 var str2 ='';
					if(result.enrollmentYears != null && result.enrollmentYears.trim().length > 0)
					{
					var years = result.enrollmentYears.split(",");
					if(years.indexOf("2012") > -1)
					str2+='<li class="yes">2012<span></span></li>&nbsp;';
					else
					str2+='<li class="no">2012<span></span></li>&nbsp;';
					if(years.indexOf("2010") > -1)
					str2+='<li class="yes">2010<span></span></li>&nbsp;';
					else
					str2+='<li class="no">2010<span></span></li>&nbsp;';
					}
					else
					{
					str2+='<li class="no">2012<span></span></li>&nbsp;';
					str2+='<li class="no">2010<span></span></li>&nbsp;';
					}

					$("#enrollementDiv").html(str2);
					 
					 if(result.candidate !=null && result.candidate !=0){
						 globalCandidateId=result.candidate;
					 }
					 
					 if(result.emailId ==null || result.emailId =="" || result.emailId ==undefined){
						 $("#emailMainSpanId").hide();
						  //$("#emailSpanId").html(result.emailId);
					 }
					else{
						 $("#emailMainSpanId").show();
						$("#emailSpanId").html(result.emailId);
					}
					
					 $("#districtNoId").html(result.districtName);
					 var cadreDistrictId  = parseInt(result.districtId);
					if(cadreDistrictId >= 1 && cadreDistrictId <=10 )
						$("#stateNoId").html("Telangana");
					else if(cadreDistrictId >= 11 && cadreDistrictId <=23 )
						$("#stateNoId").html("Andhra Pradesh");
					else
						$("#stateNoId").html(result.stateName);
					
					 $("#houseNoId").html(result.houseNo);
					 
					 $("#globalAreaType").html(result.areaType);
					 
					 if(result.imagePath !=null && result.imagePath !=""){
						 $("#imagePathId").html('<img src="'+result.imagePath+'" class="media-object img-circle" style="border:1px solid #ccc;margin-top:10px;" width="80px" height="80px;">'); 
					 }else{
						 $("#imagePathId").html('<img src="images/search_details_member_imahe.png" class="media-object img-circle" style="border:1px solid #ccc;margin-top:10px;" width="80px" height="80px;">');
					 }
					 
					 //assigning radio ButtonIds for News 
					 
					 $("#panchayatRadioNewsId").val(result.panchayatId);
					 $("#mandalRadioNewsId").val(result.tehsilId);
					 
					 
					 if(participatedConstituencyType == null || participatedConstituencyType =="" || participatedConstituencyType == undefined){
						 
						 //Hiding Panchayat && Mandal Div
						 if(result.localElectionBody ==0 || result.localElectionBody ==null){
							$(".hidingMandalCls").show();
							//$('#panchayatRadioNewsId').prop('checked', true);
						 }else{
							//$('#aConstiRadioNewsId').prop('checked', true);
							 $(".hidingMandalCls").hide();					 
						 }
						 
						  $("#aConstiRadioNewsId").val(result.constituencyId);
						  $("#pConstiRadioNewsId").val(result.pConstituencyId);
						  $("#districtRadioNewsId").val(result.districtId);
					 }
					 else{
						 
						  $("#aConstiRadioNewsId").val(participatedConstituencyId);
						  $("#pConstiRadioNewsId").val(participatedParliamentId);
						  $("#districtRadioNewsId").val(participatedDistrictId);
						  if(participatedConstituencyType == "Parliament"){
							  $(".hidingAssemlyCls").hide();
						  }
						  else{
							  $(".hidingAssemlyCls").show();
						  }
						  
						  $(".hidingMandalCls").hide();	
					 }
					 $('#districtRadioNewsId').prop('checked', true);
					 
					  
					  //Calling WebService 
						   getCandidateAndLocationSummaryNews();

					
					 //
					  $("#dataLoadingsImgForownBoothDetailsId").hide();
					  $("#dataLoadingsImgForImagePath").hide();
					 
					 if(result.ccmVO !=null && result.ccmVO!=""){
						 ownBoothDetailsVo=result.ccmVO;
						 buildingOwnBoothDetails(ownBoothDetailsVo);
					 }
					 
					 //assigning values to global variables
					 if(result.localElectionBody ==0 || result.localElectionBody ==null){
						globalPanchayatId=result.panchayatId;
						globalTehsilId=result.tehsilId;
					 }
					 globalConstituencyId=result.constituencyId;
					 globalParliamentId=result.pConstituencyId;
					 globalDistrictId=result.districtId;
					 globalElectionBodyId=result.localElectionBody;	

					 globalPancName = result.panchayatName;
					 globalTehsName = result.tehsilName;
					 globalConstName = result.constituencyName;
					 globalParlName = result.pConstituencyName;
					 globalDistName = result.districtName;
					 
					 getCandidateAndConstituencySurveyResult();
					 complaintDetailsOfCadre(localCadreId,result.membershipNo);
					 getCandidateElectDetatails(localCadreId);
					 getDeathsAndHospitalizationDetails();
					getTdpCadreSurveyDetails(globalCadreId,0,null,"NotAll",0,'true');
					getCadreFamilyDetailsByCadreId();
					getTotalComplaintsForCandidate();
					
				}
			});
		}
		function buildingOwnBoothDetails(boothDetailsVo){
			
			var ownBoothDetailsVo=boothDetailsVo;
			var str='';
			
			if(ownBoothDetailsVo !=null && ownBoothDetailsVo !=""){
				if(ownBoothDetailsVo.ownBoothPerc !=null){//own booth
					str+='<td>'+ownBoothDetailsVo.ownBoothPerc+'</td>';
				}else{
					str+='<td>-</td>';
				}
				if(ownBoothDetailsVo.ownPanchPerc !=null){//own panchayat
					str+='<td>'+ownBoothDetailsVo.ownPanchPerc+'</td>';
				}else{
					str+='<td>-</td>';
				}
				if(ownBoothDetailsVo.ownMandalPerc !=null){//own Mandal
					str+='<td>'+ownBoothDetailsVo.ownMandalPerc+'</td>';
				}else{
					str+='<td>-</td>';
				}
				if(ownBoothDetailsVo.ownConstiPerc !=null){//own Constituency
					str+='<td>'+ownBoothDetailsVo.ownConstiPerc+'</td>';
				}else{
					str+='<td>-</td>';
				}
				if(ownBoothDetailsVo.ownWardPerc !=null){//own WardPerc
					str+='<td>'+ownBoothDetailsVo.ownWardPerc+'</td>';
				}else{
					str+='<td>-</td>';
				}
				
				$("#ownBoothDetailsId").html(str);
			}
			
		}
		
		function complaintDetailsOfCadre(cadreId,membershipId){
			var localCadreId=cadreId;
			var localMemberShipId=membershipId;
			
			//$("#dataLoadingsImgForComplaint").show();
			//33160
			var jsobj={
				cadreId:localCadreId,
				membershipId:localMemberShipId
			}
			$.ajax({
				type:'GET',
				 url: 'complaintDetailsOfCadreAction.action',
				 data : {task:JSON.stringify(jsobj)} ,
			}).done(function(result){
				var str='';
				/* if(result !=null){
					if(result.knownList !=null && result.knownList.length>0){
						$("#grievanceDetailsMainDivId").show();
					
						$("#totalComplaintsId").html(result.knownList.length);
					
					for(var i in result.knownList){
						str+='<tr>';
							str+='<td>'+result.knownList[i].name+'</td>';
							str+='<td>'+result.knownList[i].occupation+'</td>';
							str+='<td>'+result.knownList[i].dateOfBirth+'</td>';
							str+='<td>'+result.knownList[i].status+'</td>';
							str+='<td>'+result.knownList[i].representativeType+'</td>';
							str+='<td>'+result.knownList[i].type+'</td>';
						str+='</tr>';
					}
					$("#grievanceDetailsId").html(str);
					}
					else{
						$("#grievanceDetailsId").html("Data Not Available.");
					}
				} */
				
				
			});
		}
		function getEventDetailsOfCadre(globalCadreId){
			var localCadreId=globalCadreId;
			//8341157
			var jsobj={
				cadreId:localCadreId
			}
			$.ajax({
				type:'GET',
				 url: 'getEventDetailsOfCadreAction.action',
				 data : {task:JSON.stringify(jsobj)} ,
			}).done(function(myresult){
			if(myresult !=null && myresult.length>0 && myresult !=""){
				//$("#participationTableMainDivId").show();
				$("#eventParticipationCollapseDivId").show();
				
				var str='';
				for(var k in myresult)
				{
					str+='<h4 class="m_top10">'+myresult[k].eventTypeStr.toUpperCase()+'</h4>';
					str+='<table class="table m_0 table-bordered table-responsive" style="margin-top: 10px">';
					str+='<thead>';
					str+='<tr>';
					/* str+='<th colspan="5" style="background-color:#CCCCCC;text-align:center;"> '+myresult[k].eventTypeStr.toUpperCase()+' PARTICIPATION DETAILS </th>';		 */	

					//$("#eventHeadingId").html('<span> EVENT PARTICIPATION DETAILS </span>');
					str+='</tr>';
						str+='<tr>';
						
						str+='<th>MAIN EVENT</th>';
						str+='<th>SUB EVENT</th>';
						str+='<th>INIVITED</th>';
						str+='<th>ATTENDED</th>';
						if(myresult[k].eventTypeStr == 'Visitor Event')
							str+='';
						else
							str+='<th>ABSENT</th>';
						
						str+='</tr>';
					str+='</thead>';
					str+='<tbody>';
					var results = myresult[k].knownList;
					if(results !=null && results.length>0){
					for(var i in results){
						var subLength;
						if(results[i].knownList !=null){
							subLength=results[i].knownList.length;
						}					
								if(results[i].knownList !=null){
									for(var j in results[i].knownList){
										str+='<tr>';
										if(j==0){											
												str+='<td style="border-bottom:#fff;">'+results[i].name+'</td>';
										}else{
											str+='<td style="border:#fff;"></td>';
										}
										str+='<td>'+results[i].knownList[j].name+'</td>';
										if(results[i].knownList[j].eventTypeId == 2)
											str+='<td>0</td>';
										else
											str+='<td>'+results[i].knownList[j].invitationCount+'</td>';
										str+='<td>'+results[i].knownList[j].total+'</td>';
										if(results[i].knownList[j].eventTypeId != 2)
											str+='<td>'+results[i].knownList[j].absentCount+'</td>';
										 str+='</tr>';
									}
								}
								else{
									str+='</tbody>';
									str+='</table>';
									$("#participationTableDivId").html("Data Not Available.");
									return;
								}	
						}
						
					str+='</tbody>';
				   str+='</table>';	
				   //$("#participationTableDivId").html(str);
				}else{
					str+='</tbody>';
				    str+='</table>';	
					//$("#participationTableDivId").html("Data Not Available.");
					}					
				}
				 $("#participationTableDivId").html(str);	
				   
				}else{
					$("#eventParticipationCollapseDivId").hide();
					//$("#participationTableMainDivId").hide();
				}
				
			});
		}

		function familyMembersSurveyDetails(votercardNo)
		{	
			$("#familySurveyDataLoadoing").show();
			$(".arrow_box3").hide();
			if(participatedConstituencyType == '')
			participatedConstituencyType = null;
			$('.familySurveyDetailsCls').html("");
			if(participatedConstituencyId == null )
				participatedConstituencyId=0;
			var jsObj={
				cadreId:0,
				surveyId:0,
				searchTypeStr: "All",
				boothId: 0,
				isPriority: "false",
				voterCardNo:votercardNo,
				constituencyId: participatedConstituencyId,
				constiTypeStr: participatedConstituencyType
			}
			
			$.ajax({
				type:'GET',
				 url: 'getTdpCadreSurveyDetailsAction.action',
				 data : {task:JSON.stringify(jsObj)} ,
			}).done(function(result){
				//console.log(result);
				$("#familySurveyDataLoadoing").hide();
				if(result != null){
					
					if(result.verifierVOList !=null){
						
						var str='';
											
						str='';
						str+='<div class="tab-content m_top20">';
						str+='<div role="tabpanel" class="tab-pane active" id="area">';
						str+='<div class="panel-group m_0" id="accordion1" role="tablist" aria-multiselectable="true">';
							for(var i in result.verifierVOList){
								str+='<div class="panel panel-default m_0">';
								str+='<div class="panel-heading bg_f9 innerDivFamilyCls" attr_survy_divId="familySurveyTable'+i+'" role="tab" id="heading'+i+'">';
								str+='<a role="button" data-toggle="collapse" data-parent="#accordion1" onclick="getTdpCadreFamilySurveyDetails('+globalCadreId+','+result.verifierVOList[i].id+',\'null\',\'NotAll\',\'familySurveyTable'+i+'\',\'true\',\''+votercardNo+'\');" aria-expanded="true" aria-controls="" style="cursor:pointer;"> ';
								str+='<h4 class="panel-title text-bold">';
								str+=''+result.verifierVOList[i].name+'';
								str+='<span class="pull-right"><i class="glyphicon glyphicon-triangle-top topsurveyTable" id="topsurveyTable'+i+'" style=""></i><i class="glyphicon glyphicon-triangle-bottom bottomsurveyTable" id="bottomsurveyTable'+i+'" style="display:none;"></i></span>';
								str+='</h4> </a><div style="offset4"><center><img id="familyAjax'+result.verifierVOList[i].id+'" src="images/icons/survey-details.gif" style="display:none;width:250px;height:200px;"/></center></div>';
								
								str+='</div>';
								
								str+='<div id="familySurveyTable'+i+'" class="panel-collapse collapse in allSurveyDtlsCls hideSurveyCls " role="" aria-labelledby="" style="display:none;">';
									str+='<div class="panel-body table-responsive">';										
									str+='</div>';
									str+='</div>';
								str+='</div>';
						str+='</div>';

							}

					
					str+='</div>';
					str+='</div> ';                     
					
					$('.familySurveyDetailsCls').html(str);
				
				 }
				}
			});
		}
		
	function getTdpCadreFamilySurveyDetails(globalCadreId,surveyId,indexId,searchTypeStr,divId,isPriority,voterCardNo){
		$("#"+divId+"").html("");

		if($("#"+divId).hasClass("showSurveyCls")){
			return;
		}
		
		if(participatedConstituencyType == '')
		participatedConstituencyType = null; 
		var temp="familyAjax"+surveyId+"";
			$("#"+temp).show();
		var localCadreId=globalCadreId;
		var surveyId=surveyId;
		var indexId=indexId;
		if(participatedConstituencyId == null )
				participatedConstituencyId=0;
		var jsObj={
			cadreId:localCadreId,
			surveyId:surveyId,
			searchTypeStr: searchTypeStr,
			boothId: $('#cadreBoothId').val(),
			isPriority: "false",
			voterCardNo:voterCardNo,
			constituencyId: participatedConstituencyId,
			constiTypeStr: participatedConstituencyType
		}
		
		$.ajax({
			type:'GET',
			 url: 'getTdpCadreSurveyDetailsAction.action',
			 data : {task:JSON.stringify(jsObj)} ,
		}).done(function(result){
			$("#"+temp).hide();
			if(result != null){
			var str='';
				if(result.verifierVOList !=null){	
					if(result.verifierVOList != null && result.verifierVOList.length>0)
					{			
						str+='<div class="panel-body">';
						/*if(isPriority == 'true')
							{
								str+='<div class="pull-right tooltipClass" style="margin-bottom: 5px;"> <a href="javascript:{getTdpCadreFamilySurveyDetails('+globalCadreId+','+surveyId+',\'null\',\'All\',\''+divId+'\',\'false\');;}" class="btn btn-default btn-xs " style="padding:3px 5px 5px;background-color:#CCC;border-radius:0px;" data-toggle="tooltip" data-placement="bottom" title="View All Questions Response"><i class="glyphicon glyphicon-list"></i></a> </div>';
							}*/
						str+='<table class="table m_0 table-bordered">';
						str+='<thead>';
							str+='<th style="text-align:center;">Question</th>';
							str+='<th style="text-align:center;">Answer</th>';
						str+='</thead>';
						str+='<tbody>';
						
						for(var i in result.verifierVOList[0].verifierVOList){
						str+='<tr>';
							str+='<td>'+result.verifierVOList[0].verifierVOList[i].name+'</td>';
							str+='<td>'+result.verifierVOList[0].verifierVOList[i].option+'</td>'; 
						str+='</tr>';
						}
					
					}				
					$("#"+divId+"").show();		
					$("#"+divId+"").html(str);	
				}
			}
		});
		
	}
	
	$(document).on('click', '.innerDiv', function(){
		/*var surveyDivId = $(this).attr("attr_survy_divId");
		//$(".allSurveyDtlsCls").hide();
		var temp = $("#"+surveyDivId).css("display");
		alert(temp);
		if(temp != "none"){alert(1);
			$("#"+surveyDivId).css("display")="none";
		}else{alert(2);
			$("#"+surveyDivId).show();
		}*/
		var surveyDivId = $(this).attr("attr_survy_divId");
		
		//alert($("#"+surveyDivId).hasClass("hideSurvey"));
		
		if($("#"+surveyDivId).hasClass("hideSurvey")){
			$("#"+surveyDivId).removeClass("hideSurvey");
			$("#"+surveyDivId).addClass("showSurvey");
			$("#"+surveyDivId).css({"display":"block"});
		}else if($("#"+surveyDivId).hasClass("showSurvey")){
			$("#"+surveyDivId).removeClass("showSurvey");
			$("#"+surveyDivId).addClass("hideSurvey");
			$("#"+surveyDivId).css({"display":"none"});
			
		}
		
	});
	$(document).on('click', '.innerDivFamilyCls', function(){
	
		var surveyDivId = $(this).attr("attr_survy_divId");
		
		//alert(surveyDivId);
		
		if($("#"+surveyDivId).hasClass("hideSurveyCls")){
			$("#"+surveyDivId).removeClass("hideSurveyCls");
			$("#"+surveyDivId).addClass("showSurveyCls");
			$("#"+surveyDivId).css({"display":"block"});
		}else if($("#"+surveyDivId).hasClass("showSurveyCls")){
			$("#"+surveyDivId).removeClass("showSurveyCls");
			$("#"+surveyDivId).addClass("hideSurveyCls");
			$("#"+surveyDivId).css({"display":"none"});
			
		}
	
	});
	
	var participatedSurveysArr = new Array();
	var isparticipatedSelected = '';
	function getTdpCadreSurveyDetails(globalCadreId,surveyId,indexId,searchTypeStr,divId,isPriority){

		if($("#"+divId).hasClass("showSurvey")){
			if(isPriority=="true"){
			return;
			}
		}
		if(participatedConstituencyType == '')
		participatedConstituencyType = null;
	var temp="ajax"+surveyId+"";
			$("#"+temp).show();
			var localCadreId=globalCadreId;
			var surveyId=surveyId;
			var indexId=indexId;
			if(surveyId != null && surveyId>0)
			{
				$('.topsurveyTable').show();
				$('.bottomsurveyTable').hide();
				
				
				$('#top'+divId+'').hide();								
				$('#bottom'+divId+'').show();				
			}
			
			if(isPriority =='false')
				$('#'+divId+'').html('<div style="offset4"><img id="ajaxsurveyTable" src="images/icons/survey-details.gif" style="width:250px;height:200px;margin-left:300px;"/></div>');
			
			$('.allSurveyDtlsCls').hide();
			$("#img"+divId+"").show();
			if(surveyId !=0 && localCadreId !=0){
				$("#dataLoadingsImg").show();
			}
			if(surveyId>0){
				searchTypeStr = "NotAll";
			}
			else{
				$('#surveyDataLoadoing').show();
			}
				
			$('#list1').click(function(){
				$('#list2').removeClass('li_arr');
				$('#list2').removeClass('active');
				$('#list1').addClass('active');
				$('#list1').addClass('li_arr');
				$('#list3').removeClass('active');
				$('#list3').removeClass('li_arr');
				isparticipatedSelected = '';
			});
			$('#list2').click(function(){
				$('#list1').removeClass('li_arr');
				$('#list1').removeClass('active');
				$('#list2').addClass('active');
				$('#list2').addClass('li_arr');
				$('#list3').removeClass('active');
				$('#list3').removeClass('li_arr');
				isparticipatedSelected = 'false';
			});
			$('#list3').click(function(){
				$('#list2').removeClass('li_arr');
				$('#list2').removeClass('active');
				$('#list1').removeClass('active');
				$('#list1').removeClass('li_arr');
				$('#list3').addClass('active');
				$('#list3').addClass('li_arr');
				isparticipatedSelected = '';
			});
			var voterCardNo = 'false';
			console.log("isparticipatedSelected "+isparticipatedSelected)
			if(participatedSurveysArr != null && participatedSurveysArr.length>0)
			{
				if(isparticipatedSelected == 'false')
				{
					voterCardNo = "notParticipated";
				}
				else
				{					
					for(var i in participatedSurveysArr)
					{
						var id = participatedSurveysArr[i].id;
						if(surveyId == id )
						{
							voterCardNo = "participated";
						}
					}
				}
				
			}
			if(participatedConstituencyId == null )
				participatedConstituencyId=0;
			var jsObj={
				cadreId:localCadreId,
				surveyId:surveyId,
				searchTypeStr: searchTypeStr,
				boothId: $('#cadreBoothId').val(),
				isPriority: isPriority,
				voterCardNo:voterCardNo,
				constituencyId: participatedConstituencyId,
				constiTypeStr: participatedConstituencyType
			}
			$('#'+divId+'').show();
			$.ajax({
				type:'GET',
				 url: 'getTdpCadreSurveyDetailsAction.action',
				 data : {task:JSON.stringify(jsObj)} ,
			}).done(function(result){
			$('#surveyDataLoadoing').hide();
			if(result !=null){
				$("#"+temp).hide();
				$("#surveyDetailsMainDivId").show();
				$("#img"+divId+"").hide();
				if(result.verifierVOList !=null){
				if(surveyId ==0 && localCadreId !=0){
					var str='';
					if(searchTypeStr == 'NotAll')
					{
						participatedSurveysArr =[];
						
						str+='<ul class="nav nav-tabs tab-list display-style" role="tablist">';
						if(result.count != null && result.count >0)
						{
							str+='<li class="active li_arr" style="margin-top: 0px;padding:0px;" id="list1"><a href="#participated" onclick="getTdpCadreSurveyDetails('+globalCadreId+','+surveyId+',\'null\',\'NotAll\',\'\',\'true\');" class="text-bold" data-toggle="tab" style="cursor:pointer;" >CANDIDATE PARTICIPATED SURVEYS&nbsp;&nbsp;&nbsp;&nbsp;'+result.count+'</a></li>';
							
							str+='<li  style="margin-top: 0px;padding:0px; left: 10px;" id="list2" ><a href="#area" onclick="getTdpCadreSurveyDetails('+globalCadreId+','+surveyId+',\'null\',\'All\',\'\',\'true\');" class="text-bold" data-toggle="tab"  style="cursor:pointer;">SURVEYS IN CANDIDATE AREA&nbsp;&nbsp;&nbsp;&nbsp;'+result.totalCount+'</a></li>';
							
							str+='<li  style="margin-top: 0px;padding:0px; left: 10px;" id="list3" ><a href="#participated" onclick="getCandidateAndConstituencySurveyResult();">SURVEYS ON CANDIDATE &nbsp;&nbsp;&nbsp;&nbsp;'+candiConstiSurveyCount+'</a></li>';
							
						}
						else{
							str+='<li style="padding:10px 15px;" >CANDIDATE PARTICIPATED SURVEYS&nbsp;&nbsp;&nbsp;&nbsp;'+result.count+'</li>';
							
							str+='<li  class="active li_arr"  style="margin-top: 0px;padding:0px; left: 10px;" id="list2" ><a href="#area" onclick="getTdpCadreSurveyDetails('+globalCadreId+','+surveyId+',\'null\',\'All\',\'\',\'true\');" class="text-bold" data-toggle="tab"  style="cursor:pointer;">SURVEYS IN CANDIDATE AREA&nbsp;&nbsp;&nbsp;&nbsp;'+result.totalCount+'</a></li>';
							
							str+='<li style="margin-top: 0px;padding:0px; left: 20px;" id="list3"><a href="#participated" onclick="getCandidateAndConstituencySurveyResult();">SURVEYS ON CANDIDATE &nbsp;&nbsp;&nbsp;&nbsp;'+candiConstiSurveyCount+'</a></li>';;
						}
						str+='</ul>';
						$('.surveyDetailssCls').html(str);
					}
					
					
					str='';
					str+='<div class="tab-content m_top20">';
					str+='<div role="tabpanel" class="tab-pane active" id="area">';
					str+='<div class="panel-group m_0" id="accordion1" role="tablist" aria-multiselectable="true">';
					

							for(var i in result.verifierVOList){
								if(result.isVerified != null && result.isVerified =='true')
								{
									var obj = {
										id:result.verifierVOList[i].id
									};
									participatedSurveysArr.push(obj);
								}
								str+='<div class="panel panel-default m_0">';
								
								str+='<div class="panel-heading bg_f9 innerDiv" role="tab" id="heading'+i+'" attr_survy_divId="surveyTable'+i+'">';
								str+='<a role="button" data-toggle="collapse" data-parent="#accordion1" onclick="tableshidesandShow(\'surveyTable'+i+'\','+i+');" aria-expanded="true" aria-controls="" style="cursor:pointer;"> ';
								str+='<h4 class="panel-title text-bold">';
								str+=''+result.verifierVOList[i].name+'';
								str+='<span class="pull-right"><i class="glyphicon glyphicon-triangle-top topsurveyTable" id="topsurveyTable'+i+'" style=""></i><i class="glyphicon glyphicon-triangle-bottom bottomsurveyTable topsurveyTable'+i+'" id="topsurveyTable'+i+'" style="display:none;"></i></span>';
								str+='</h4> </a><div style="offset4"><img id="ajaxsurveyTable'+i+'" src="images/icons/survey-details.gif" style="display:none;width:250px;height:200px;margin-left:300px;"/></div>';
								
								str+='</div>';
																
									str+='<div id="surveyTable'+i+'" class="panel-collapse collapse in allSurveyDtlsCls table-responsive" role="" aria-labelledby="" style="display:none;">';
									str+='<img id="ajaxsurveyTable'+i+'" src="images/icons/survey-details.gif" style="width:250px;height:200px;margin-left:300px;"/>';
									str+='<div class="panel-body">';										
									str+='</div>';
									str+='</div>';
									
									getTdpCadreSurveyDetails(globalCadreId,result.verifierVOList[i].id,indexId,searchTypeStr,'surveyTable'+i+'',isPriority);
									
								str+='</div>';
						str+='</div>';

							}

					
					str+='</div>';
					str+='</div> ';                     
					str+='</div>';
					
					$('.surveyDetailsCls').html(str);
					console.log(participatedSurveysArr);
				}
				else if(surveyId !=0 && surveyId !=0 ){
					buildingSurveyQuestionsDetails(result,surveyId,indexId,divId,isPriority);
				}
			 }		
			}else{
				$("#"+temp).hide();
				if(surveyId ==0 && localCadreId !=0){
					$('.surveyDetailsCls').html('<div>Data Not Available.</div>');
				}
				else if(surveyId !=0 && surveyId !=0 ){
					$("#collapse"+surveyId+''+indexId).html('<div>Data Not Available.</div>');	
				}
				$("#surveyDetailsMainDivId").hide();
				
				 $("#"+divId+"").show();		
				$("#"+divId+"").html("<div style='text-align:center;'>Data Not Available.</div>");	
			}
		});
			
	}
		
		$('#accordionSurvey').on('shown.bs.collapse', function() {
			var surveyId = $(this).find("div.in").attr("attr_survey_id");
			var cadreId= $(this).find("div.in").attr("attr_cadre_id");
			var indexId=$(this).find("div.in").attr("attr_index_id");
			getTdpCadreSurveyDetails(cadreId,surveyId,indexId,"NotAll",0,'true');
		});

		
		function buildingSurveyQuestionsDetails(results,surveyId,indexId,divId,isPriority){
			$("#dataLoadingsImg").hide();
			var str='';
				if(results.verifierVOList !=null){					
					str+='<div class="panel-body">';
					if(isPriority == 'true')
					{
						str+='<div class="pull-right tooltipClass" style="margin-bottom: 5px;"> <a href="javascript:{getTdpCadreSurveyDetails('+globalCadreId+','+surveyId+',\'null\',\'All\',\''+divId+'\',\'false\');;}" class="btn btn-default btn-xs " style="padding:3px 5px 5px;background-color:#CCC;border-radius:0px;" data-toggle="tooltip" data-placement="bottom" title="View All Questions Response"><i class="glyphicon glyphicon-list"></i></a> </div>';
					}
					
									str+='<table class="table m_0 table-bordered">';
										/*str+='<thead>';
											str+='<th style="text-align:center;">Question</th>';
											str+='<th style="text-align:center;">Answer</th>';
										str+='</thead>';*/
										str+='<tbody>';
										
										for(var i in results.verifierVOList){
											str+='<tr>';
												str+='<td>'+results.verifierVOList[i].name+'</td>';
												str+='<td>'+results.verifierVOList[i].option+'</td>'; 
											str+='</tr>';
											if(results.verifierVOList[i].verifierVOList != null && results.verifierVOList[i].verifierVOList.length>0)
											{
												
											
								if(results.verifierVOList[i].verifierVOList[0].verifierVOList != null && results.verifierVOList[i].verifierVOList[0].verifierVOList.length>0)
											{
												str+='<tr>';
													str+='<td colspan="2">';
													str+='<div class="table-responsive" style="width:1059px;overflow-y:auto">';
													str+='<table class="table table-bordered">';
														str+='<thead >';
															str+='<th style="text-align:center;background-color:lightgrey;width:60px;">';
																str+=' Location ';
															str+='</th>';
															for(var k in results.verifierVOList[i].verifierVOList[0].verifierVOList)
															{	
																str+='<th style="background-color:lightgrey;">';
																str+=''+results.verifierVOList[i].verifierVOList[0].verifierVOList[k].option+'';
																str+='</th>';
															}
														str+='</thead>';
														str+='<tbody>';
															for(var k in results.verifierVOList[i].verifierVOList)
															{	
																str+='<tr>';
																str+='<th style="background-color:lightgrey;">';
																str+=''+results.verifierVOList[i].verifierVOList[k].name+'';
																str+='</th>';
																for(var l in results.verifierVOList[i].verifierVOList[k].verifierVOList)
																{
																	var perc = results.verifierVOList[i].verifierVOList[k].verifierVOList[l].percentage;
																	perc = parseFloat(perc).toFixed(2);
																	str+='<td>'+perc+'</td>';
																}
																str+='</tr>';
															}
														str+='</tbody>';
														str+='</table>';
														str+='</div>'; 
													str+='</td>'; 
												str+='</tr>';
											}
											}
										}
											
										str+='</tbody>';
									str+='</table>';
					str+='</div>';
					
				}else{
					str+='<div>"Data Not Available."</div>';
				}
				//$("#"+divId+"").show();		
				$("#"+divId+"").html(str);		
		}
		function getCandidateElectDetatails(cadreId){
			var localCadreId=cadreId;
			var jsObj={
				cadreId:localCadreId,
			}
			$.ajax({
				type:'GET',
				 url: 'getCandidateElectDetatailsAction.action',
				 data : {task:JSON.stringify(jsObj)} ,
			}).done(function(result){
				var str='';
				if(result !=null && result !=''){
					$("#electionProfileMainDivId").show();
					str+='<div>';
					
					for(var i in result){
						if(result[i].status==true)
						{
							str+='<ul class="wl-sub-details" style="margin-bottom:0px;">';
							str+='<li onmouseover="this.style.color=\'#06ABEA\';" onmouseout="this.style.color=\'#333333\';" style="cursor:pointer;text-align:left;" class="eachParticipationListCls" attr_constId='+result[i].constituencyId+' attr_election_type='+result[i].electionType+' attr_election_year='+result[i].electionYear+'>';
							str+='<strong><span style="color:green">Won</span> in '+result[i].electionYear+' </strong> '+result[i].electionType+' Election with <strong>'+result[i].votesPercentage+'</strong> of votes gain for <strong>'+result[i].partyName+'</strong> party in <strong>'+result[i].constituencyName+'</strong> constituency</li>';
							str+='</ul>';
						}
					}
					for(var i in result){
						if(result[i].status ==false){
							str+='<ul class="wl-sub-details">';
							str+='<li onmouseover="this.style.color=\'#F13144\';" onmouseout="this.style.color=\'#333333\';" style="cursor:pointer;text-align:left;" class="eachParticipationListCls" attr_constId='+result[i].constituencyId+' attr_election_type='+result[i].electionType+' attr_election_year='+result[i].electionYear+'>';
							str+='<strong><span style="color:red">Lost</span> in '+result[i].electionYear+' </strong> '+result[i].electionType+' Election with <strong>'+result[i].votesPercentage+'</strong> of votes gain for <strong>'+result[i].partyName+'</strong> party in <strong>'+result[i].constituencyName+'</strong> constituency</li>';
							str+='</ul>';
						}
					}
						str+='</div>';
						
					$("#electionProfileDivId").html(str);
				}
				 else{
					$("#electionProfileMainDivId").hide();
				}
			});
			
		}
		
		$(document).on("click",".eachParticipationListCls",function(){
			
			var constId=$(this).attr("attr_constId");
			var elecType=$(this).attr("attr_election_type");
			var elecYear=$(this).attr("attr_election_year");
			
			var win =window.open("constituencyElectionResultsAction.action?constituencyId="+constId+"&electionType="+elecType+"&electionYear="+elecYear,"constituencyElectionResults","scrollbars=yes,height=600,width=750,left=200,top=200");
			win.focus();
		});
		
		$(function () {
		  $('[data-toggle="tooltip"]').tooltip();
		});
	
</script>		
<script type="text/javascript">
$('.table-scroll').scrollator({
	custom_class: 'table-scroll',
});


/*$('#myStathalf').circliful();
$('#myStathalf1').circliful();
$('#myStathalf2').circliful();
$('#myStathalf3').circliful();
$('#myStathalf4').circliful();
$('#myStathalf5').circliful();
$('#myStathalf6').circliful();
$('#myStathalf7').circliful();
$('#myStathalf8').circliful();
$('#myStathalf9').circliful();
$('#myStathalf10').circliful();
$('#myStathalf11').circliful();
$('#myStathalf12').circliful();
$('#myStathalf13').circliful();
$('#myStathalf14').circliful();
$('#myStathalf15').circliful();
$('#myStathalf16').circliful();
$('#myStathalf17').circliful();
$('#myStathalf18').circliful();*/

</script>
<script type="text/javascript">
	
$(document).ready(function() {
  var cb = function(start, end, label) {
	console.log(start.toISOString(), end.toISOString(), label);
  }
setcolorsForStatus();
  var optionSet1 = {
	startDate: moment().subtract(29, 'days'),
	endDate: moment(),
	minDate: '01/01/2012',
	maxDate: '12/31/2015',
	//dateLimit: { days: 60 },
	showDropdowns: true,
	showWeekNumbers: true,
	timePicker: false,
	timePickerIncrement: 1,
	timePicker12Hour: true,
	ranges: {
	   'Today': [moment(), moment()],
	   'Yesterday': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
	   'Last 7 Days': [moment().subtract(6, 'days'), moment()],
	   'Last 30 Days': [moment().subtract(29, 'days'), moment()],
	   'This Month': [moment().startOf('month'), moment().endOf('month')],
	   'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
	},
	opens: 'left',
	buttonClasses: ['btn btn-default'],
	applyClass: 'btn-small btn-primary newsSubmitBtn',
	cancelClass: 'btn-small',
	format: 'MM/DD/YYYY',
	separator: ' to ',
	locale: {
		applyLabel: 'Submit',
		cancelLabel: 'Clear',
		fromLabel: 'From',
		toLabel: 'To',
		customRangeLabel: 'Custom',
		daysOfWeek: ['Su', 'Mo', 'Tu', 'We', 'Th', 'Fr','Sa'],
		monthNames: ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'],
		firstDay: 1
	}
  };

  var optionSet2 = {
	startDate: moment().subtract(7, 'days'),
	endDate: moment(),
	opens: 'left',
	ranges: {
	   'Today': [moment(), moment()],
	   'Yesterday': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
	   'Last 7 Days': [moment().subtract(6, 'days'), moment()],
	   'Last 30 Days': [moment().subtract(29, 'days'), moment()],
	   'This Month': [moment().startOf('month'), moment().endOf('month')],
	   'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
	}
  };

  $('#reportrange span').html(moment().subtract(29, 'days').format('MMMM D, YYYY') + ' - ' + moment().format('MMMM D, YYYY'));

  $('#reportrange').daterangepicker(optionSet1, cb);

  $('#reportrange').on('show.daterangepicker', function() { console.log("show event fired"); });
  $('#reportrange').on('hide.daterangepicker', function() { console.log("hide event fired"); });
  $('#reportrange').on('apply.daterangepicker', function(ev, picker) { 
	console.log("apply event fired, start/end dates are " 
	  + picker.startDate.format('MMMM D, YYYY') 
	  + " to " 
	  + picker.endDate.format('MMMM D, YYYY')
	); 
  });
  $('#reportrange').on('cancel.daterangepicker', function(ev, picker) { console.log("cancel event fired"); });

  $('#options1').click(function() {
	$('#reportrange').data('daterangepicker').setOptions(optionSet1, cb);
  });

  $('#options2').click(function() {
	$('#reportrange').data('daterangepicker').setOptions(optionSet2, cb);
  });

  $('#destroy').click(function() {
	$('#reportrange').data('daterangepicker').remove();
  });

});
$(document).on("click",".newsSubmitBtn",function(){
	getCandidateAndLocationSummaryNews();
});

</script>
<script type="text/javascript">



 function getTotalMemberShipRegistrationsInCadreLocation()
 {
	$("#memberShipCountDiv").html('<img alt="Processing Image" src="./images/icons/search.gif">');
	var pcId=0;
	//pcId:participatedConstituencyId,pcType:participatedConstituencyType
	//alert(participatedParliamentId);
	if(participatedConstituencyId != null && participatedConstituencyId > 0){
		pcId = participatedConstituencyId;
	}else{
		pcId = participatedParliamentId;
	}

	var pcType = participatedConstituencyType;
	//pcType="Assembly";
	  $.ajax({
			type : "POST",
			url  : "getTotalMemberShipRegsInCadreLocationAction.action",
			data : {tdpCadreId:globalCadreId,pcId:pcId,pcType:pcType}
		  }).done(function(result){
			if(result != null){
				  buildTotalMemberShipRegInCadreLocation(result,pcType);
			}
			  else{
				$("#memberShipCountDiv").html('No Data Available.');  
			  }
		});
			
}

/*function buildTotalMemberShipRegInCadreLocation(result)
{
	 var locationNamesArr = [];
	 locationNamesArr.push("Own Booth");
	 if(result.cadreLocation =="Mandal")
		locationNamesArr.push("Own Panchayat");
	
	 locationNamesArr.push("Own Mandal/Muncipality","Own AC","Own PC","Own District");
	// console.log(locationNamesArr);
	 
	 var dataArr = [];
	 dataArr.push(parseFloat(result.boothPerc));
	  if(result.cadreLocation =="Mandal")
	   dataArr.push(parseFloat(result.panchPerc));
   dataArr.push(parseFloat(result.mandalPerc),parseFloat(result.constiPerc),parseFloat(result.parConsPerc),parseFloat(result.districtPerc));
  // console.log(dataArr);
   Highcharts.setOptions({
        colors: ["#66cdcc"]
    });
	
	 $('#memberShipCountDiv').highcharts({
        chart: {
            type: 'column'
        },
        xAxis: {
            categories: locationNamesArr,
            crosshair: true
        },
        yAxis: {
            min: 0,
            title: {
                text: ' '
            }
        },
        plotOptions: {
            column: {
                pointPadding: 0.2,
                borderWidth: 0
            }
        },
        series: [{
            name: 'Percentage',
            data: dataArr

        }]
    });
	 
	 
}*/

function buildTotalMemberShipRegInCadreLocation(result,pcType)
{
	var str = '';
		str += '<table class="table m_0">';
		str += '<tr>';
	//	alert(pcType);
	if(pcType !=null && pcType !="" && pcType !=undefined){
		
		if(pcType == "Assembly"){
			str += '<td>';
			str += '<div class="fulCircleCls" data-dimension="100%" data-text="'+result.constiPerc+'%" data-percent="'+result.constiPerc+'" data-fgcolor="#330000" data-bgcolor="#cccccc" data-type="half" data-info="Own AC ('+participatedConstName.toUpperCase()+')"></div>';
			str += '</td>';
		}
		str += '<td>';
		str += '<div class="fulCircleCls" data-dimension="100%" data-text="'+result.parConsPerc+'%" data-percent="'+result.parConsPerc+'" data-fgcolor="#330000" data-bgcolor="#cccccc" data-type="half" data-info="Own PC ('+participatedParlName.toUpperCase()+')"></div>';
		str += '</td>';
		
		str += '<td>';
		str += '<div class="fulCircleCls" data-dimension="100%" data-text="'+result.districtPerc+'%" data-percent="'+result.districtPerc+'" data-fgcolor="#330000" data-bgcolor="#cccccc" data-type="half" data-info="Own District ('+participatedDistName.toUpperCase()+')"></div>';
		str += '</td>';
		
	}
	else{
		
		str += '<td>';
		str += '<div class="fulCircleCls" data-dimension="100%" data-text="'+result.boothPerc+'%" data-percent="'+result.boothPerc+'" data-fgcolor="#330000" data-bgcolor="#cccccc" data-type="half" data-info="Own Booth"></div>';
		str += '</td>';
		if(result.cadreLocation =="Mandal")
		{
			str += '<td>';
			str += '<div class="fulCircleCls" data-dimension="100%" data-text="'+result.panchPerc+'%" data-percent="'+result.panchPerc+'" data-fgcolor="#330000" data-bgcolor="#cccccc" data-type="half" data-info="Own Panchayat ('+globalPancName.toUpperCase()+')"></div>';
			str += '</td>';
		 }
		
		str += '<td>';
		str += '<div class="fulCircleCls" data-dimension="100%" data-text="'+result.mandalPerc+'%" data-percent="'+result.mandalPerc+'" data-fgcolor="#330000" data-bgcolor="#cccccc" data-type="half" data-info="Own Man/Mun ('+globalTehsName.toUpperCase()+')"></div>';
		str += '</td>';
		
		str += '<td>';
		str += '<div class="fulCircleCls" data-dimension="100%" data-text="'+result.constiPerc+'%" data-percent="'+result.constiPerc+'" data-fgcolor="#330000" data-bgcolor="#cccccc" data-type="half" data-info="Own AC ('+globalConstName.toUpperCase()+')"></div>';
		str += '</td>';
		
		str += '<td>';
		str += '<div class="fulCircleCls" data-dimension="100%" data-text="'+result.parConsPerc+'%" data-percent="'+result.parConsPerc+'" data-fgcolor="#330000" data-bgcolor="#cccccc" data-type="half" data-info="Own PC ('+globalParlName.toUpperCase()+')"></div>';
		str += '</td>';
		
		str += '<td>';
		str += '<div class="fulCircleCls" data-dimension="100%" data-text="'+result.districtPerc+'%" data-percent="'+result.districtPerc+'" data-fgcolor="#330000" data-bgcolor="#cccccc" data-type="half" data-info="Own District ('+globalDistName.toUpperCase()+')"></div>';
		str += '</td>';
		
	}
		str += '</tr>';
		str += '</table>';
	
	
	
	
	
	 $("#memberShipCountDiv").html(str);
	 $('.fulCircleCls').circliful();
}


function getCadreFamilyDetailsByCadreId()
{
	//$("#familyMembersDiv").html('<img alt="Processing Image" src="./images/icons/search.gif">');
	$("#dataLoadingsImgForFamilyMembers").show();
		  $.ajax({
		  type : "POST",
		  url  : "getCadreFamilyDetailsAction.action",
		  data : {tdpCadreId:globalCadreId}
	  }).done(function(result){
	  $("#dataLoadingsImgForFamilyMembers").hide();
		if(result != null && result.length > 0){
		  buildCadreFamilyDetails(result); 
		}else{
			$("#familyMembersDiv").html("No Data Available");
			}
			
			getNtrTrustStudentDetailsInstitutionWise("cadre");
	  });
 } 
 
 var familycadreIdsArrayGlobal=[];
 function buildCadreFamilyDetails(result)
 {
 console.log(result.familyMembersList)
	  familyInfoArr=[];
	 var constId = $('#cadreConstituencyId').val();
	 var partNo = $('#cadrePartNo').val();
	 var str = '';
	 
	 str += '<ul>';
	 for(var i in result)
	 {
		 if(result[i].tdpCadreId !=null){
			 familycadreIdsArrayGlobal.push(result[i].tdpCadreId);
		 }
		var imgPath;
			
			 if(result[i].imagePath != null && result[i].imagePath.trim().length > 0)
			 { imgPath = result[i].imagePath;
			 }			
			  else
			  {
			 imgPath = "http://www.mytdp.com/voter_images/"+constId+"/Part"+partNo+"/"+result[i].votercardNo+".jpg"	;
			  }
	 var familyObj = {
	 voterId:result[i].votercardNo,
	 membershipId:result[i].membershipNo,
	 name:result[i].name,
	 relation:result[i].relation,
	 relativeName:result[i].relativeName,
	 imagePath:imgPath
	 };
	 
	 familyInfoArr.push(familyObj);
	 
		 str += '<li>';
         str += '<div class="media">';
		 var imgPath='';
		 if(result[i].imagePath != null && result[i].imagePath.trim().length > 0)
		 { 
		 imgPath = result[i].imagePath;
		 } 
		 else
		 {
		 imgPath="http://www.mytdp.com/voter_images/"+constId+"/Part"+partNo+"/"+result[i].votercardNo+".jpg" ;
		 }
		  str += '<div class="media-left ">';
		  str += '<img src="'+imgPath+'" class="img-responsive media-object img-circle"  style="height: 50px;width:35px;" >';
		  str += '</div>';
		 /*if(result[i].tdpCadreId != null ){
			 str += '<div class="media-left ">';
			 //str += '<img src="dist/img/family-member.png" class="img-responsive media-object img-circle" alt="profile">';
			// str += '<img src="http://www.mytdp.com/voter_images/'+constId+'/Part'+partNo+'/'+result[i].votercardNo+'.jpg" class="img-responsive media-object img-circle"  style="height: 50px;width:35px;" >';
			str += '<img src="'+imgPath+'" class="img-responsive media-object img-circle"  style="height: 50px;width:35px;" >';
			 str += '</div>';
		 }
		 else
		 {
			 str += '<div class="media-left">';
			// str += '<img src="dist/img/family-member.png" class="img-responsive media-object img-circle" alt="profile">';
			// str += '<img src="http://www.mytdp.com/voter_images/'+constId+'/Part'+partNo+'/'+result[i].votercardNo+'.jpg" class="img-responsive media-object img-circle"  style="height: 50px;width:35px;" >';
			str += '<img src="'+imgPath+'" class="img-responsive media-object img-circle"  style="height: 50px;width:35px;" >';
			 str += '</div>';
		 }*/
         str += '<div class="media-body">';
         str += '<div class="m_0">'+result[i].name+'';
		if(result[i].tdpCadreId != null )
		str+=' [ <b><a href="cadreDetailsAction.action?cadreId='+result[i].tdpCadreId+'" data-toggle="tooltip" data-placement="right" title="Membership No" class="membershipno-cls">'+result[i].membershipNo+'</a></b> ] ';
		/*if(result[i].publicRepresentativeStr != null){
			str+='[<b>'+result[i].publicRepresentativeStr+'</b>]';
		}*/
		 str += '<span class="pull-right">';
		 if(result[i].count != null && result[i].count> 0)
           str += '<img class="img-responsive survey-drop" src="img/survey.png" style="cursor:pointer;" id="survey-dropdown" >';
		   else
		  //str += '<img class="img-responsive" src="img/survey.png" id="survey-dropdown">';
		 str += '</span>';
		 str += '<ul class="survey-hover survey-hover'+i+' arrow_box3" style="display:none">';
        
		 if(result[i].education != null && result[i].education.trim().length > 0)
			 str += '<li>Education : <span class="pull-right">'+result[i].education+'</span></li>';
		
         if(result[i].occupation != null && result[i].occupation.trim().length > 0)
			 str += '<li>Occupation : <span class="pull-right">'+result[i].occupation+'</span></li>';
		 
         if(result[i].count != null && result[i].count> 0)
			 str += '<li>Participated in Survey : <span class="pull-right" style="cursor:pointer; color:#485EDB;" data-toggle="modal" data-target=".modalForSurvey" onclick = "familyMembersSurveyDetails(\''+result[i].votercardNo+'\')">'+result[i].count+'</span></li>';
		 
		 str += '</ul>';
         str += '</div>';
         str += '<p class="m_0">Relation : <span class="textTransFormCls">'+result[i].relation+'</span>';
		 if(result[i].relativeName != null && result[i].relativeName.trim().length > 0)
		  str += ' - <span class="textTransFormCls">'+result[i].relativeName.toLowerCase()+'</span></p>';
         str += '<p class="m_0">Age : ';
		 if(result[i].age != null)
		   str +=''+result[i].age+'';
	   else
		 str += '';
		if(result[i].publicRepresentativeStr != null)
		{
			str += '<p class="m_0" style="font-weight:bold">Public Repr. : <span class="textTransFormCls" style="color:#2772C0">'+result[i].publicRepresentativeStr+'</span>';
		}
		if(result[i].partyPositionStr != null)
		{
			str += '<p class="m_0" style="font-weight:bold">Party Position: <span class="textTransFormCls" style="color:#2772C0">'+result[i].partyPositionStr+'</span>';
		}
         str += '</p></div>';
         str += '</div>';
         str += '</li>';
	 }
	 str += '</ul>';
	$("#familyMembersDiv").html(str);
	getMemberComplaints();
	$(function () {
	  $('.membershipno-cls').tooltip()
	})
	
	getNtrTrustStudentDetailsInstitutionWise("family");
 }
 
 <!--Grievance  CANDIDATE-->
 
function getTotalComplaintsForCandidate()
{//9999
    $("#complaintCountDiv").html('');
	$("#financialDiv").html('');
	$("#complaintsDiv").html('');
	$("#candidateApprovedAmount").html('<img src="images/icons/loading.gif" style="width:20px;height:20px;"></img>');
	$("#candidateDeathInsurance").html('<img src="images/icons/loading.gif" style="width:20px;height:20px;"></img>');
	$("#candidateHospitalizationInsurance").html('<img src="images/icons/loading.gif" style="width:20px;height:20px;"></img>');
var votercardNo = $('#cadreVoterCardNo').val();
var membershipId = $('#cadreMemberShipId').val();
var arr =[];
var obj = {
	"voterId":votercardNo,
	"membershipId" :membershipId,
	"name":"",
	"relation":""
}

arr.push(obj);
console.log(arr)
	$.ajax({
			type : "POST",
			url: "http://mytdp.com/Grievance/WebService/Auth/getCategoryWiseStatusCountForCandidate",
			//url: "http://localhost:8080/Grievance/WebService/Auth/getCategoryWiseStatusCountForCandidate",
			  data: JSON.stringify(arr),
			 contentType: "application/json; charset=utf-8",
			 dataType: "json",
			 username: "grievance",
             password: "grievance@!tG"	
			 }).done(function(myresult){
				 if(myresult != null){
				buildTotalComplaints(myresult,0);
				buildInsuranceTotalComplaints(myresult,0);
		}
		else{
			$("#complaintCountDiv").html('No Data Available.');
			$("#complaintsDiv").html('No Data Available.');
				$("#candidateApprovedAmount").html('0');
				$("#candidateDeathInsurance").html('0');
				$("#candidateHospitalizationInsurance").html('0');
		}
			});
}


function buildTotalComplaints(result,complaintId)
{

	var str = '';
	str += '<ul class="list-inline">';
	
	$("#candidateTotalComplaintsDiv").html(''+result[0].count+'');
	str += '<li style="margin-top:5px">';
	str += '<ul class="display-style pull-right graph-list count-list">';
	for(var i in result[0].subList){
		
	if(result[0].subList[i].count  > 0)
	{
	var color=getColorCodeByStatus(result[0].subList[i].status);
	str += '<li><span style="background-color:'+color+';height:11px;width:11px;display:inline-block;margin-right:4px"></span><span class="approved-text">'+result[0].subList[i].status.toUpperCase()+'<span class="pull-right">'+result[0].subList[i].count+'</span></span></li>';
	}
	}
	str += '</ul>';
	str += '</li>';
	str += '</ul>';
	$("#complaintCountDiv").html(str);
	
	var comp1='';
	if(result[0].amountVO != null){
	if(result[0].amountVO.cmRefiedFund == null)
	result[0].amountVO.cmRefiedFund =0;
	if(result[0].amountVO.partyFund == null)
	result[0].amountVO.partyFund =0;
	$("#candidateApprovedAmount").html(''+result[0].amountVO.approved+'/-');
	}
	else
	{
	 $("#candidateApprovedAmount").html('0/-');
	}
	
	var comp = '';
	comp += '<ul class="inbox-messages custom-scroll-ins" style="margin-bottom:0px;">';
	for(var j in result[0].voList){
		var color = getColorCodeByStatus(result[0].voList[j].status);
		if(result[0].voList[j].complaintId == complaintId){
		comp += '<li style="cursor:pointer;background:'+color+';border-left:4px solid '+color+'"';
		comp += '<p class="m_0">C ID - '+result[0].voList[j].complaintId+'</p>';
			comp += '<p class="m_0">'+result[0].voList[j].subject+'</p>';
			comp += '<p class="m_0">Status - <span class="textTransFormCls">'+result[0].voList[j].status+'</span></p>';
			if(result[0].voList[j].raisedDate != null)
			 comp += '<p class="m_0">'+result[0].voList[j].raisedDate+'</p>';
			 comp += '</li>';
		}
		 
   }
   
   for(var j in result[0].voList){
		if(result[0].voList[j].complaintId != complaintId){
		
			comp += '<li style="background:'+color+';border-left:4px solid '+color+'" ';
			
			comp += '<p class="m_0">C ID - '+result[0].voList[j].complaintId+'</p>';
			comp += '<p class="m_0">'+result[0].voList[j].subject+'</p>';
			
			comp += '<p class="m_0">Status - <span class="textTransFormCls">'+result[0].voList[j].status+'</span></p>';
			if(result[0].voList[j].raisedDate != null)
			 comp += '<p class="m_0">'+result[0].voList[j].raisedDate+'</p>';
			comp += '</li>';
		}
   }
   
	comp += '</ul>';
	 
    $("#complaintsDiv").html(comp);
	$(".custom-scroll-ins").mCustomScrollbar();
}

function buildInsuranceTotalComplaints(result,complaintId)
{

	 var hosReq = 0;
	 var deathReq=0;
	 for(var j in result[1].voList){
				if(result[1].voList[j].issueType == 'Hospitalization')
				{
				hosReq = 1 +hosReq;
				}
				if(result[1].voList[j].issueType == 'Death')
				{
					deathReq = 1 +deathReq;
				}
			}
	
	var str ='';
	str+=''+deathReq+'';
	if(deathReq > 0)
	{
	str+='<ul class="hoverclassul">';
	 for(var j in result[1].voList){
	 if(result[1].voList[j].issueType == 'Death'){
	str+='<li>';
	str+='<p class="m_0" >C ID -'+result[1].voList[j].complaintId+'</p>';
	str+='<p class="m_0">'+result[1].voList[j].subject+'</p>';
	str+='<p class="m_0">Status - '+result[1].voList[j].status +'</p>';
	str+='<p class="m_0"> '+result[1].voList[j].raisedDate+'</p>';
	str+='</li>';
	}
	}
	str+='</ul>';
	}
	
	$("#candidateDeathInsurance").html(str);
	
	var str1 ='';
	str1+=''+hosReq+'';
	if(hosReq > 0)
	{
	str1+='<ul class="hoverclassul">';
	 for(var j in result[1].voList){
	 if(result[1].voList[j].issueType == 'Hospitalization'){
	str1+='<li>';
	str1+='<p class="m_0" >C ID -'+result[1].voList[j].complaintId+'</p>';
	str1+='<p class="m_0">'+result[1].voList[j].subject+'</p>';
	str1+='<p class="m_0">Status - '+result[1].voList[j].status +'</p>';
	str1+='<p class="m_0"> '+result[1].voList[j].raisedDate+'</p>';
	str1+='</li>';
	}
	}
	str1+='</ul>';
	}
	
	$("#candidateHospitalizationInsurance").html(str1);
}

<!--end-->
 //Grievance Family 
 var familyInfoArr =new Array();
function getMemberComplaints()
{
  $("#familyDeathInsurance").html('<img src="images/icons/loading.gif" style="width:20px;height:20px;"></img>');
  $("#familyApprovedAmount").html('<img src="images/icons/loading.gif" style="width:20px;height:20px;"></img>');
  $("#familyHospitalizationInsurance").html('<img src="images/icons/loading.gif" style="width:20px;height:20px;"></img>');
  $("#familyMemberDiv").html('');
	$.ajax({
			type : "POST",
			url: "http://mytdp.com/Grievance/WebService/Auth/getTotalComplaintsForCandidate",
			//url: "http://localhost:8080/Grievance/WebService/Auth/getTotalComplaintsForCandidate",
			  data: JSON.stringify(familyInfoArr),
			 contentType: "application/json; charset=utf-8",
			 dataType: "json",
			 username: "grievance",
             password: "grievance@!tG"
			 }).done(function(myresult){
				$("#familyMemberImg").hide();
				if(myresult != null && myresult.length > 0)
				{
					buildFamilyMemberComplaint(myresult);
					buildInsuranceFamilyMemberComplaint(myresult);
				}
					else{
			   $("#familyMemberDiv").html("<div style='text-align:center;padding:10px'>NO DATA AVAILABLE </div>");
			   $("#familyDeathInsurance").html('0');
			   $("#familyApprovedAmount").html('0');
			   $("#familyHospitalizationInsurance").html('0');
					}
			});

}

function buildFamilyMemberComplaint(result,jobj)
{

	 var flag = false;
 try{
	 var cnt = 0;

	if(result!=null && result.length>0){
	for(var j in result[0].subList){
	  if(result[0].subList[j].subList != null && result[0].subList[j].subList.length > 0)
	  {
	  for(var k in result[0].subList[j].subList)
				{
				cnt  = cnt + 1;
		  }
	 }
	}		  
	var comp = '';
	if(result[0].amountVO != null){
	if(result[0].amountVO.cmRefiedFund == null)
	result[0].amountVO.cmRefiedFund =0;
	if(result[0].amountVO.partyFund == null)
	result[0].amountVO.partyFund =0;
	//comp+='<div class="panel panel-default">';
	//comp+='<div class="panel-heading">'; 
	//comp+='<h4 class="panel-title">TOTAL COMPLAINTS-'+cnt+'</h4>';
	//comp+='</div>';
	//comp+='<div class="panel-body">';
	//comp+='<h5 class="m_0">TOTAL FINANCIAL REQUESTED :'+result[0].amountVO.requested+'/-</h5>';
	//comp+='<h5 class="m_0">TOTAL APPROVED :'+result[0].amountVO.approved+'/-</h5>';
	//comp+='<p class="m_0">Party Support '+result[0].amountVO.partyMembsCount+' ['+result[0].amountVO.partyFund+'/-]</p>';
  //comp+='<p class="m_0">Govt Support '+result[0].amountVO.cmReliefMembsCount+' ['+result[0].amountVO.cmRefiedFund+'/-]</p>';
	
	$("#familyApprovedAmount").html(''+result[0].amountVO.approved+'/-');
	}
	$("#totalFamilyComplaints").html(''+cnt+'');
	//comp+='</div>';
	//comp+='</div>';
	//comp += '<div class="panel-group m_0" id="accordion155" role="tablist" aria-multiselectable="true">';
	
	 for(var j in result[0].subList){
	  if(result[0].subList[j].subList != null && result[0].subList[j].subList.length > 0)
			{ 
			flag = true;
			//comp += '<div class="panel panel-default">';
			//comp += '<div class="panel-heading" style="background-color:#FFF" role="tab" id="headingOne155'+j+'">';
			//comp += '<div role="button" style="color:#666" data-toggle="collapse" data-parent="#accordion155" href="#collapseOne155'+j+'" aria-expanded="true" aria-controls="collapseOne155'+j+'">';
			comp += '<div class="media" style="padding:10px">';
			comp += '<div class="media-left">';
			comp += '<img src="'+result[0].subList[j].image+'" style="height:35px;width:35px;"  class="media-object img-border img-circle">';
			comp += '</div>';
			comp += '<div class="media-body">';
			comp += '<ul class="list-inline">';
			comp += '<li style="width:85%">';
			comp += '<p class="m_0">Name- '+result[0].subList[j].name+'</p>';
			comp += '<p class="m_0">Relation- '+result[0].subList[j].relation+' - '+result[0].subList[j].status+' </p>';
			if(result[0].subList[j].membershipId == null)
			comp += '<p class="m_0">MemberShipID- N/A</p>';
			else
			comp += '<p class="m_0">MemberShipID- <a target="_blank" title="Click here to View '+result[0].subList[j].name+' Cadre Details " href="http://mytdp.com/cadreDetailsAction.action?memberShipId='+result[0].subList[j].membershipId+'">'+result[0].subList[j].membershipId+'</p></a>';
			
			comp += '</li>';
			comp += '<li>';
			comp += '<span class="countStyleSpan">'+result[0].subList[j].subList.length+'</span>';
			comp += '</li>';
			comp += '</ul>';
			comp += '</div>';
			comp += '</div>';
			//comp += '</div>';
			//comp += '</div>';
			//comp += '<div id="collapseOne155'+j+'" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne155'+j+'">';
				comp += '<div class="panel-body pad_0">';
				
				comp+='<ul style="margin-bottom:0px;box-shadow:none" class="inbox-messages custom-scroll-ins">';
				for(var k in result[0].subList[j].subList)
				{
		     	var color = getColorCodeByStatus(result[0].subList[j].subList[k].status);
				comp += '<li style="background:'+color+'"';
				
				comp+=' <p class="m_0">C ID - '+result[0].subList[j].subList[k].complaintId+'</p><p class="m_0">'+result[0].subList[j].subList[k].subject+'</p><p class="m_0">Status - <span class="textTransFormCls">'+result[0].subList[j].subList[k].status+'</span></p><p class="m_0">'+result[0].subList[j].subList[k].raisedDate+'</p></li>';
				}
				comp+='</ul>';
			 } 
				comp += '</div>';
			//comp += '</div>';
			comp += '</div>';
		}
	  //comp += '</div>';
	 
      $("#familyMemberDiv").html(comp);
	  if(result[0].count>=7){
	    $("#familyMemberDiv").css("height","760px");
	  }else{
		$("#familyMemberDiv").css("height","auto"); 
	  }
	}
	if(!flag)
	{
	 
      $("#familyMemberDiv").html("<div style='text-align:center;padding:10px'>NO DATA AVAILABLE </div>");
	  $("#familyApprovedAmount").html('0/-');
	}
	
 }catch(e){}
 $(".custom-scroll-ins").mCustomScrollbar();
}


function buildInsuranceFamilyMemberComplaint(result)
{
	  var hosReq = 0;
	 var deathReq=0;
	for(var j in result[1].subList){
	  if(result[1].subList[j].subList != null && result[1].subList[j].subList.length > 0)
			{ 
			for(var k in result[1].subList[j].subList)
				{
				if(result[1].subList[j].subList[k].issueType == 'Hospitalization')
				{
				hosReq = 1 +hosReq;
				}
			if(result[1].subList[j].subList[k].issueType == 'Death')
				{
				deathReq = 1 +deathReq;
				}
		   }  
		}
	} 
	var str = '';
	str+=''+deathReq+'';
	if(deathReq > 0)
	{
	str+='<ul class="hoverclassul">';
	for(var j in result[1].subList){
	  if(result[1].subList[j].subList != null && result[1].subList[j].subList.length > 0)
			{ 
			for(var k in result[1].subList[j].subList)
				{
				if(result[1].subList[j].subList[k].issueType == 'Death')
				{
					str+='<li>';
					str+='<p class="m_0">C ID -'+result[1].subList[j].subList[k].complaintId+'</p>';
					
					str+='<p class="m_0">'+result[1].subList[j].subList[k].subject+'</p>';
					str+='<p class="m_0">Status - '+result[1].subList[j].subList[k].status +'</p>';
					str+='<p class="m_0"> '+result[1].subList[j].subList[k].raisedDate+'</p>';
					str+='</li>';
			 }
		}
	}
	}
	str+='</ul>';
	}
	
	$("#familyDeathInsurance").html(str);
	
	var str1 = '';
	str1+=''+hosReq+'';
	if(hosReq > 0)
	{
	str1+='<ul class="hoverclassul">';
	for(var j in result[1].subList){
	  if(result[1].subList[j].subList != null && result[1].subList[j].subList.length > 0)
			{ 
			for(var k in result[1].subList[j].subList)
				{
				if(result[1].subList[j].subList[k].issueType == 'Hospitalization')
				{
					str1+='<li>';
					str1+='<p class="m_0">C ID -'+result[1].subList[j].subList[k].complaintId+'</p>';
					str1+='<p class="m_0">'+result[1].subList[j].subList[k].subject+'</p>';
					str1+='<p class="m_0">Status - '+result[1].subList[j].subList[k].status +'</p>';
					str1+='<p class="m_0"> '+result[1].subList[j].subList[k].raisedDate+'</p>';
					str1+='</li>';
			 }
		}
	}
	}
	str1+='</ul>';
	}
	
	$("#familyDeathInsurance").html(str);
	$("#familyHospitalizationInsurance").html(str1);
	
}
 // end
 
 function getElectionPerformanceInCadreLocation()
 {
			
	  $(".electionPerformanceDiv").html('<img alt="Processing Image" src="./images/icons/search.gif">');
			$.ajax({
				type : "POST",
				url  : "getElectionPerformanceInCadreLocationAction.action",
				data : {tdpCadreId:globalCadreId}
			}).done(function(result){
				$(".electionPerformanceDiv").html("");
				if(result != null && result.length > 0)
				  buildElectionPerformanceInCadreLocation(result);
			  else
				$(".electionPerformanceDiv").html("No Data Available");
			});
			
}

function buildElectionPerformanceInCadreLocation(result)
{
	$(".electionPerformanceDiv").html('');
	
	var str = '';
	for(var i in result)
	{
		
		str += '<div class="panel panel-default">';
		str += '<div class="panel-heading  bg_white" style="" role="tab" id="headingOne'+result[i].year+'">';
		str += '<h4 class="panel-title">';
		str += '<a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne'+result[i].year+'" ';
		if(result[i].year == "2014")
		  str += ' aria-expanded="true" ';
	    else
		 str += ' aria-expanded="false" '; 
	 
		str +=' aria-controls="collapseOne'+result[i].year+'"> '+result[i].year+' PERFORMANCE';
		//if(result[i].year == "2014")
		str += '<span class="pull-right"><i class="glyphicon glyphicon-chevron-up"></i></span></a>';
		//else
		//str += '<span class="pull-right"><i class="glyphicon glyphicon-chevron-down"></i></span></a>';
		
        str += '</h4>';
	    str += '</div>';
		
		str += '<div id="collapseOne'+result[i].year+'" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne'+result[i].year+'">';
		str += '<div class="panel-body" style="padding: 0px;" >';
		
		str += '<div class="row ">';
		str += '<div class="col-md-12 table-responsive">';
		str += '<table class="table m_0">';
		str += '<tr>';
	 if(result[i].year != "2009")
	 {
	   str += '<td>';
       str += '<div class="fulCircleCls1" data-dimension="100%" data-info="Own Booth" data-text="'+result[i].boothPerc+'%" data-percent="'+result[i].boothPerc+'" data-fgcolor="#330000" data-bgcolor="#cccccc" data-type="half" ></div>';
       str += '</td>';
	 }
	   
	if(result[i].cadreLocation =="Mandal")
	{
		str += '<td>';
        str += '<div class="fulCircleCls1" data-dimension="100%" data-text="'+result[i].panchPerc+'%" data-percent="'+result[i].panchPerc+'" data-fgcolor="#330000" data-bgcolor="#cccccc" data-type="half" data-info="Own Panchayat ('+globalPancName.toUpperCase()+')"></div>';
        str += '</td>';
	 }
	
	str += '<td>';
    str += '<div class="fulCircleCls1" data-dimension="100%" data-text="'+result[i].mandalPerc+'%" data-percent="'+result[i].mandalPerc+'" data-fgcolor="#330000" data-bgcolor="#cccccc" data-type="half" data-info="Own Man/Mun ('+globalTehsName.toUpperCase()+')"></div>';
    str += '</td>';
	
	str += '<td>';
    str += '<div class="fulCircleCls1" data-dimension="100%" data-text="'+result[i].constiPerc+'%" data-percent="'+result[i].constiPerc+'" data-fgcolor="#330000" data-bgcolor="#cccccc" data-type="half" data-info="Own AC ('+globalConstName.toUpperCase()+')"></div>';
    str += '</td>';
	
	str += '<td>';
    str += '<div class="fulCircleCls1" data-dimension="100%" data-text="'+result[i].parConsPerc+'%" data-percent="'+result[i].parConsPerc+'" data-fgcolor="#330000" data-bgcolor="#cccccc" data-type="half" data-info="Own PC ('+globalParlName.toUpperCase()+')"></div>';
	str += '</td>';
	
	str += '<td>';
    str += '<div class="fulCircleCls1" data-dimension="100%" data-text="'+result[i].districtPerc+'%" data-percent="'+result[i].districtPerc+'" data-fgcolor="#330000" data-bgcolor="#cccccc" data-type="half" data-info="Own District ('+globalDistName.toUpperCase()+')"></div>';
    str += '</td>';
	
		str += '</tr>';
		str += '</table>';
		str += '</div>';
		str += '</div>';
		str += '</div>';
		str += '</div>';
	}
	$(".electionPerformanceDiv").html(str);
	$('.fulCircleCls1').circliful();
}

// Financial
var financialColorArr = [];
var typearr = ['EDP','CM Relief Fund','Educational','Financial Support','Personal'];
function setcolorsForFinancialchart()
	{
		
		financialColorArr = new Array();
		var colorStatic = new Array('#D64D54','#66CDCC','#00B17D','#663300','#4169E1','#1771D7');
		
		var colorCount = 0;
		
		for(var i in typearr)
		{
		 
				var obj = {
				 type : typearr[i],
				 color : colorStatic[colorCount]
				}
				financialColorArr.push(obj)
			  
				if(colorCount == (colorStatic.length)-1)
				colorCount = 0;
				 colorCount++;
		}
		
		return financialColorArr;
	}

	function getColorCodeByType(type)
	{

		if(financialColorArr != null && financialColorArr.length > 0)
		{
			for(var i in financialColorArr)
			{
				if(financialColorArr[i].type == type)
					return financialColorArr[i].color;
			}
		}
	}

function getApprovedFinancialSupprotForCadre()
{
	setcolorsForFinancialchart();
	$.ajax({
		type : "POST",
		url  : "getApprovedFinancialSupprotForCadreAction.action",
		data : {tdpCadreId:globalCadreId}
		
	}).done(function(result){
		$("#headingId").html("");
		$("#donutchart2").html("");
		$("#financeSupportUL").html("");
		
		if(result != null && result.length > 0)
		  buildApprovedFinancialSupprotForCadre(result);
	  else
		$("#donutchart2").html("No Data Available."); 
		
	});
}

function buildApprovedFinancialSupprotForCadre(result)
{
	var dataArr = [];
	var colorsArr = [];
	
   if(result != null && result.length > 0)
   {
	 for(var i in result)
	 {
	  if(result[i].name !='')
	  {
		  var data= new Array();
		  data.push(result[i].name,result[i].donationAmount);
		  dataArr.push(data);
		  colorsArr.push(getColorCodeByType(result[i].name));
	  }
	
	  }
	}
	 
	Highcharts.setOptions({
        colors: colorsArr
    });
	$('#donutchart2').highcharts({
        chart: {
            type: 'pie',
			backgroundColor: 'transparent',
			
            options3d: {
                enabled: false,
                alpha: 50
            }
        },
		legend: {
                enabled: true,
                align: 'right',
                verticalAlign: 'right',
                floating: false,
                backgroundColor: (Highcharts.theme && Highcharts.theme.background2) || 'white',
                borderColor: '#CCC',
                borderWidth: 1,
                shadow: false
            },
        plotOptions: {
            pie: {
                innerSize: 40,
                depth: 10,
				dataLabels: {
                    enabled: false,
				}
            }, 
        },
		
		series: [{
			name : 'Count',
            data: dataArr
        }]
    });
    $("#headingId").html(""+result[0].totalComplaints+" ["+result[0].totalRequests+"]/-");

	var str = '';
	for(var i in result)
	{
		if(result[i].name !='')
		{
		  var color = getColorCodeByType(result[i].name);
		  str += '<li style="color:'+color+';text-transform:uppercase">';
		  str += '<span style="margin-right:4px;padding:0px 8px;background-color:'+color+';"></span>';
		  str += ''+result[i].name+' '+result[0].count+'';  
		   if(result[i].donationAmount != null)
			 str += '['+result[i].donationAmount+'/-]</li>';
		  else
		   str += '[0/-]</li>';  
		}
	}
	$("#financeSupportUL").html(str);
	
	
}
	$(document).click(function() {
		$('.survey-hover').hide();
	});
	$(document).on("click",".survey-drop",function(){
		$('.survey-hover').hide();
		$(this).parent().find('.survey-hover').show();
		e.stopPropagation(); 
	});
	

/* getTotalMemberShipRegistrationsInCadreLocation();		
getCadreFamilyDetailsByCadreId();
getElectionPerformanceInCadreLocation();
getApprovedFinancialSupprotForCadre(); */

//News
//getCandidateAndLocationSummaryNews();
$(document).on("click",".newsRadioCls",function(){
	getCandidateAndLocationSummaryNews();
});

function getCandidateAndLocationSummaryNews(){
	
	//rebuilding the blocks
	$("#candidateCategoryWiseNewsId").html("");
	$("#propertiesId").html("");
	$("#issuesSummary").html("");
	$("#issuesCount").html("");
	
	//data loading Image.
	$("#dataLoadingsImgForNewsId").show();
	$("#hideShowNewsDiv").hide();
	
		var locatioinType;
		var locationId;
		if($(".newsRadioCls").is(':checked')) {
			if($("#panchayatRadioNewsId").is(':checked')){
					locatioinType="panchayat";
					locationId=$("#panchayatRadioNewsId").val();
			}
			else if($("#mandalRadioNewsId").is(':checked')){
				locatioinType="mandal";
				locationId=$("#mandalRadioNewsId").val();
			}
			else if($("#aConstiRadioNewsId").is(':checked')){
				locatioinType="constituency";
				locationId=$("#aConstiRadioNewsId").val();
			}
			else if($("#pConstiRadioNewsId").is(':checked')){
				locatioinType="parliament";
				locationId=$("#pConstiRadioNewsId").val();
			}
			else if($("#districtRadioNewsId").is(':checked')){
				locatioinType="district";
				locationId=$("#districtRadioNewsId").val();
			}
		}
		
		var fromDate=$(".dp_startDate").val();//mm/dd/yyyy
		var toDate=$(".dp_endDate").val();
		
		var startDate;
		var endDate;
		if((fromDate !=null && fromDate !="") && (toDate !=null && toDate !="")){
			var startArray=fromDate.split("/");
			startDate=startArray[1]+"-"+startArray[0]+"-"+startArray[2];
			var endArray=toDate.split("/");
			endDate=endArray[1]+"-"+endArray[0]+"-"+endArray[2];
		}

		//3424,47,"09-06-2015";,"15-07-2015";
		if(globalCandidateId ==undefined || globalCandidateId ==null){
			globalCandidateId=0;
		}
		candidateId=globalCandidateId;
		locationType=locatioinType;
		locationId=locationId;
		//locationType="district";
		//locationId=13;
		
		startDate=startDate;
		endDate=endDate;
		
		var url = window.location.href;
		var wurl = url.substr(0,(url.indexOf(".com")+4));

	 $.ajax({
		url: "http://mytdp.com/CommunityNewsPortal/webservice/getCandidateAndLocationSummary/"+startDate+"/"+endDate+"/"+locationType+"/"+locationId+"/"+candidateId+""
	}).then(function(result) {
		$("#dataLoadingsImgForNewsId").hide();
		$("#hideShowNewsDiv").show();
		if(result !=null && result !=""){
			var str="";
				str+='<div class="panel-body pad_0">';
					if(result.candidateSummary !=null){
                            	str+='<table class="table m_0 table-bordered m_0">';
                                	str+='<tr>';
                                    	str+='<td>TOTAL ARTICLES <a class="candidateRedirectedCls" attr_candidateId="'+candidateId+'" attr_categoryId="0" attr_benefitId="0" attr_fromDate="'+startDate+'" attr_toDate="'+endDate+'" attr_count="'+result.candidateSummary.totalCount+'" style="cursor:pointer;"> <span class="pull-right text-bold">'+result.candidateSummary.totalCount+'</span></a></td>';
                                        str+='<td>POSITIVE ARTICLES <a class="candidateRedirectedCls" attr_candidateId="'+candidateId+'" attr_categoryId="0" attr_benefitId="1" attr_fromDate="'+startDate+'" attr_toDate="'+endDate+'" attr_count="'+result.candidateSummary.positiveCount+'" style="cursor:pointer;"> <span class="pull-right text-bold">'+result.candidateSummary.positiveCount+'</span></a></td>';
                                        str+='<td>NEGATIVE ARTICLES <a class="candidateRedirectedCls" attr_candidateId="'+candidateId+'" attr_categoryId="0" attr_benefitId="2" attr_fromDate="'+startDate+'" attr_toDate="'+endDate+'" attr_count="'+result.candidateSummary.negativeCount+'" style="cursor:pointer;"> <span class="pull-right text-bold">'+result.candidateSummary.negativeCount+'</span></a></td>';
                                        str+='<td>NEUTRAL ARTICLES <a class="candidateRedirectedCls" attr_candidateId="'+candidateId+'" attr_categoryId="0" attr_benefitId="3" attr_fromDate="'+startDate+'" attr_toDate="'+endDate+'" attr_count="'+result.candidateSummary.neutralCount+'" style="cursor:pointer;"> <span class="pull-right text-bold">'+result.candidateSummary.neutralCount+'</span></a></td>';
                                    str+='</tr>';
                                str+='</table>';
								if(result.candidateSummary.categoryList !=null && result.candidateSummary.categoryList.length>0){
									str+='<table class="table m_0 m_0">';
										str+='<thead>';
											str+='<th width="50%">CATEGORY NAME</th>';
											str+='<th>POSITIVE</th>';
											str+='<th>NEGATIVE</th>';
											str+='<th>NEUTRAL</th>';
										str+='</thead>';
										str+='<tbody>';
										
											for(var i in result.candidateSummary.categoryList){
												str+='<tr>';
													str+='<td id="'+result.candidateSummary.categoryList[i].categoryId+'">'+result.candidateSummary.categoryList[i].categoryName+'</td>';
													if(result.candidateSummary.categoryList[i].positiveCount !=0){
														str+='<td><a class="candidateRedirectedCls" attr_candidateId="'+candidateId+'" attr_categoryId="'+result.candidateSummary.categoryList[i].categoryId+'" attr_benefitId=1 attr_fromDate="'+startDate+'" attr_toDate="'+endDate+'" attr_count="'+result.candidateSummary.categoryList[i].positiveCount+'" style="cursor:pointer;"><span class="text-success">'+result.candidateSummary.categoryList[i].positiveCount+'</span></a></td>';
													}else{
														str+='<td><span class="text-success">'+result.candidateSummary.categoryList[i].positiveCount+'</span></td>';
													}
													if(result.candidateSummary.categoryList[i].negativeCount !=0){
														str+='<td><a class="candidateRedirectedCls" attr_candidateId="'+candidateId+'" attr_categoryId="'+result.candidateSummary.categoryList[i].categoryId+'" attr_benefitId=2 attr_fromDate="'+startDate+'" attr_toDate="'+endDate+'" attr_count="'+result.candidateSummary.categoryList[i].negativeCount+'"   style="cursor:pointer;"><span class="text-danger">'+result.candidateSummary.categoryList[i].negativeCount+'</span></a></td>';
													}else{
														str+='<td><span class="text-danger">'+result.candidateSummary.categoryList[i].negativeCount+'</span></td>';
													}
													if(result.candidateSummary.categoryList[i].neutralCount !=0){
														str+='<td><a class="candidateRedirectedCls" attr_candidateId="'+candidateId+'" attr_categoryId="'+result.candidateSummary.categoryList[i].categoryId+'" attr_benefitId=3 attr_fromDate="'+startDate+'" attr_toDate="'+endDate+'" attr_count="'+result.candidateSummary.categoryList[i].neutralCount+'" style="cursor:pointer;"><span class="text-warning">'+result.candidateSummary.categoryList[i].neutralCount+'</span></a></td>';
													}else{
														str+='<td><span class="text-warning">'+result.candidateSummary.categoryList[i].neutralCount+'</span></td>';
													}
													
												str+='</tr>';
											}
									
										str+='</tbody>';
									str+='</table>';
								}
								else{
										str+='<div class="text-center">&nbsp No Data Available.</div>';
									}
                    str+='</div> ';
				
					$("#candidateCategoryWiseNewsId").html(str);
				}else{
					$("#candidateCategoryWiseNewsId").html("Candidate Wise Data Not Available.");
				}
				if(result.departmentSummary !=null && result.departmentSummary.length>0){
					buildingIssuesTable(result.departmentSummary,startDate,endDate,locationId,locationType);
				}else{
					$("#issuesSummary").html("<center><h5>&nbsp No Data Available.</h5></center>");
				}

				if(result.locationSummary !=null){
					$("#propertiesId").html("");
					buildingPropertiesResult(result.locationSummary,startDate,endDate,locationId,locationType);
				}else{
					$("#propertiesId").html("Location Wise Data Not Available.");
				}
		}else{
			$("#newsMainDivId").html("Problem Ocuured While Getting Data..Please Contact Admin..");
		}
	}); 
}
	
	function buildingIssuesTable(result,startDate,endDate,locationId,locationType){
		$("#issuesSummary").html("");
		$("#issuesCount").html("");
		var str = "";
		var ttlCount = result[0].totalCount;
		$("#issuesCount").html(' TOTAL COUNT - <a class="departmentNewsCls" attr_fromDate="'+startDate+'" attr_toDate="'+endDate+'" attr_departmentId="0" attr_locationId="'+locationId+'" attr_locationType="'+locationType+'" attr_count ="'+ttlCount+'" style="cursor:pointer;" >'+ttlCount+'</a>');
		for(var i in result){
			str +="<tr>";
			str +="<td width='80%'>"+result[i].partyName+"</td>";
			str +='<td><a class="departmentNewsCls" attr_fromDate="'+startDate+'" attr_toDate="'+endDate+'" attr_departmentId="'+result[i].partyId+'" attr_locationId="'+locationId+'" attr_locationType="'+locationType+'" attr_count ="'+result[i].count+'" style="cursor:pointer;" >'+result[i].count+'</a></td>';
			str +="</tr>";
		}
		$("#issuesSummary").html(str);
	}
	
	/* $(document).on("click",".depCount",function(){
		buildArticlesPage();
	}); */
	
	/* function buildArticlesPage(){
		var win = window.open('articleDetailsAction.action', '_blank');
	} */
	
	function buildingPropertiesResult(result,startDate,endDate,locationId,locationType){
		$("#propertiesId").html("");
		var props = result.typeList;
		var str = '';
		if(props!=null && props.length>0){
			for(var i in props){
				if(props[i].propertyType=="detail"){
					str+='<div class="col-md-6">';
                        str+='<div class="panel panel-default">';
                        str+='<div class="panel-heading bg_f9">';
                        str+='<h4 class="panel-title text-center text-capitalize">'+props[i].aliasName+'</h4>';
                        str+='</div>';
                        str+='<div class="panel-body pad_0 table-scroll">';
						var totalCheck=false;
                        str+='<table class="table m_0 m_0" id="analysisStoriesId">';
                        str+='<thead id="divId">';
                        str+='<tr>';
                        str+='<th>Party Name</th>';
                        str+='<th>Positive</th>';
                        str+='<th>Negative</th>';
                        str+='<th>Neutral</th>';
                        str+='</tr>';
                        str+='</thead>';
						$("#divId").show();//thead Showing for Analysis
						for(var j in props[i].partiesList){
							var condCheck=true;
							var cnt = 0;
							for(var m in props[i].partiesList[j].oppenentsList){
								if(props[i].partiesList[j].oppenentsList[m].count==0){
									cnt = cnt + 1;
								}
							}
							if(cnt == 3){condCheck = false;}
							if(condCheck){
								totalCheck=true;
									str+='<tr>';
										str+='<td>'+props[i].partiesList[j].partyName+'</td>';
										for(var k in props[i].partiesList[j].oppenentsList){
											str+='<td>';
											if(props[i].partiesList[j].oppenentsList[k].aliasName=='Pos'){
												if(props[i].partiesList[j].oppenentsList[k].count==0){
													str+='<span>'+props[i].partiesList[j].oppenentsList[k].count+'</span>';
												}else{
													str+='<a class="analysisNewsCls" attr_fromDate="'+startDate+'" attr_toDate="'+endDate+'" attr_benefitId=1 attr_partyId="'+props[i].partiesList[j].partyId+'" attr_locationType="'+locationType+'" attr_propertyId="'+props[i].id+'" attr_locationId="'+locationId+'" attr_propertyType="'+props[i].propertyType+'" attr_count="'+props[i].partiesList[j].oppenentsList[k].count+'" style="cursor:pointer;"><span class="text-success">'+props[i].partiesList[j].oppenentsList[k].count+'</span></a>';
												}
											}else if(props[i].partiesList[j].oppenentsList[k].aliasName=='neg'){
												if(props[i].partiesList[j].oppenentsList[k].count==0){
													str+='<span>'+props[i].partiesList[j].oppenentsList[k].count+'</span>';
												}else{
													str+='<a class="analysisNewsCls" attr_fromDate="'+startDate+'" attr_toDate="'+endDate+'" attr_benefitId=2 attr_partyId="'+props[i].partiesList[j].partyId+'" attr_propertyId="'+props[i].id+'" attr_locationType="'+locationType+'" attr_locationId="'+locationId+'" attr_propertyType="'+props[i].propertyType+'" attr_count="'+props[i].partiesList[j].oppenentsList[k].count+'" style="cursor:pointer;"><span class="text-danger">'+props[i].partiesList[j].oppenentsList[k].count+'</span></a>';
												}
											}else if(props[i].partiesList[j].oppenentsList[k].aliasName=='Neutral'){
												if(props[i].partiesList[j].oppenentsList[k].count==0){
													str+='<span>'+props[i].partiesList[j].oppenentsList[k].count+'</span>';
												}else{
													str+='<a class="analysisNewsCls" attr_fromDate="'+startDate+'" attr_toDate="'+endDate+'" attr_benefitId=3 attr_partyId="'+props[i].partiesList[j].partyId+'" attr_locationType="'+locationType+'" attr_propertyId="'+props[i].id+'" attr_locationId="'+locationId+'" attr_propertyType="'+props[i].propertyType+'" attr_count="'+props[i].partiesList[j].oppenentsList[k].count+'" style="cursor:pointer;"><span class="text-warning">'+props[i].partiesList[j].oppenentsList[k].count+'</span></a>';
												}
											}
											str+='</td>';
									
								}	
								str+='</tr>';
							}
							
						}
                        
                        str+='</table>';
						if(totalCheck){
							$("#divId").show();
						}else{
							$("#divId").hide();//thead Hiding for Analysis
							str+='<tr><center><h5>No Data Available</h5></center></tr>';
						}
                        str+='</div>';
                        str+='</div>';
					
				}else if(props[i].propertyType=="versus"){
					
                        str+='<div class="panel panel-default">';
                        str+='<div class="panel-heading bg_f9" style="padding-left:5px;padding-right:5px;">';
                        str+='<h4 class="panel-title text-center text-capitalize">'+props[i].aliasName+' </h4>';
                        str+='</div>';
                        str+='<div class="panel-body pad_0 table-scroll">';
                        str+='<table class="table m_0 table-bordered m_0">';
						
						var isExist=false;var z=1;
						if(props[i].partiesList !=null && props[i].partiesList.length>0){
							for(var j in props[i].partiesList){
								if(props[i].partiesList[j].oppenentsList !=null && props[i].partiesList[j].oppenentsList.length >0){
									for(var k in props[i].partiesList[j].oppenentsList){
										if(props[i].partiesList[j].oppenentsList[k].count !=0){
											if(z==1){
												str+='<h5 style="font-weight:bold;"><center> BY '+props[i].partiesList[j].partyName+' PARTY </center></h5>';
											}
											isExist=true;
											str+='<tr>';
											str+='<td>'+props[i].partiesList[j].oppenentsList[k].partyName+'</td>';
											str+='<td><a class="actionReactionNewsCls" attr_fromDate="'+startDate+'" attr_toDate="'+endDate+'" attr_partyId=872 attr_propertyId="'+props[i].id+'" attr_locationId="'+locationId+'" attr_secondaryPartyId="'+props[i].partiesList[j].oppenentsList[k].partyId+'" attr_locationType="'+locationType+'" attr_propertyType="'+props[i].propertyType+'" attr_count="'+props[i].partiesList[j].oppenentsList[k].count+'" style="cursor:pointer;">'+props[i].partiesList[j].oppenentsList[k].count+'</a></td>';
											str+='</tr>';
											z=2;
										}
									}
								}
							}
							if(!isExist){
									str+='<tr><center><h5>No Data Available</h5></center></tr>';
								}
						}
						else{
							str+='<tr><center><h5>No Data Available</h5></center></tr>';
						}
						str+='<tr>';
						str+='</tr>';
						str+='</table>';
                        str+='</div>';
                        str+='</div>';
                   
				}else if(props[i].propertyType=="summary" && props[i].aliasName =="SPOT NEWS"){
					
                        str+='<div class="panel panel-default">';
                        str+='<div class="panel-heading bg_f9" style="padding-left:5px;padding-right:5px;">';
                        str+='<h4 class="panel-title text-center text-capitalize">'+props[i].aliasName+'</h4>';
                        str+='</div>';
                        str+='<div class="panel-body pad_0 table-scroll">';
                        str+='<table class="table m_0 table-bordered m_0">';
						var valExist=false;
						for(var j in props[i].partiesList){
							if( props[i].partiesList[j].count !=0){
								valExist=true;
								str+='<tr>';
									str+='<td>'+props[i].partiesList[j].partyName+'</td>';
									str+='<td><a class="summaryNewsCls" attr_fromDate="'+startDate+'" attr_toDate="'+endDate+'" attr_propertyId="'+props[i].id+'" attr_locationId="'+locationId+'" attr_locationType="'+locationType+'" attr_partyId="'+props[i].partiesList[j].partyId+'" attr_propertyType="'+props[i].propertyType+'" style="cursor:pointer;" attr_count="'+props[i].partiesList[j].count+'">'+props[i].partiesList[j].count+'</a></td>';
								str+='</tr>';
							}
						}
						if(valExist==false){
								str+='<tr><center><h5>No Data Available</h5></center></tr>';
							}
						str+='</table>';
                        str+='</div>';
                        str+='</div>';
                    str+='</div>';
				}
				else if(props[i].propertyType=="summary" && props[i].aliasName !="SPOT NEWS"){
					str+='<div class="col-md-6">';
                        str+='<div class="panel panel-default">';
                        str+='<div class="panel-heading bg_f9" style="padding-left:5px;padding-right:5px;">';
                        str+='<h4 class="panel-title text-center text-capitalize">'+props[i].aliasName+'</h4>';
                        str+='</div>';
                        str+='<div class="panel-body pad_0 table-scroll">';
                        str+='<table class="table m_0 table-bordered m_0">';
						var valExist=false;
						for(var j in props[i].partiesList){
							if( props[i].partiesList[j].count !=0){
								valExist=true;
								str+='<tr>';
									str+='<td>'+props[i].partiesList[j].partyName+'</td>';
									str+='<td><a class="summaryNewsCls" attr_fromDate="'+startDate+'" attr_toDate="'+endDate+'" attr_propertyId="'+props[i].id+'" attr_locationId="'+locationId+'" attr_locationType="'+locationType+'" attr_count="'+props[i].partiesList[j].count+'" attr_partyId="'+props[i].partiesList[j].partyId+'" style="cursor:pointer;">'+props[i].partiesList[j].count+'</a></td>';
								str+='</tr>';
							}
						}
						if(valExist==false){
								str+='<tr><center><h5>No Data Available</h5></center></tr>';
							}
						str+='</table>';
                        str+='</div>';
                        str+='</div>';
                    str+='</div>';
				}
			}
		}
		$("#propertiesId").html(str);
		$('.table-scroll').scrollator({
			custom_class: 'table-scroll',
		});
	}
	
	$(document).on("click",".ranges li",function(){
		getCandidateAndLocationSummaryNews();
	});
	
	//Dont Delete the Below Content Please..
	
	 /* var jsObj={
		candidateId:347,
		locationType:"district",
		locationId:13,
		startDate:"09-06-2015",
		endDate:"15-07-2015"	
	}
		$.ajax({
				type : "POST",
				url  : "getCandidateAndLocationSummaryNewsAction.action",
				data : {task:JSON.stringify(jsObj)}
			}).done(function(result){
				console.log(result);
			}); */
	
function getCadreIdByMemberShipId()
{
	$.ajax({
		type : "POST",
		url  : "getCadreIdByMembershipIdAction.action",
		data : {membershipId:"38324292"}
		
	}).done(function(result){
		console.log(result);
	});
	
}

function getCategoryWiseStatusCount()
{
	$.ajax({
		type : "POST",
		url  : "getCategoryWiseStatusCountAction.action",
		data : {tdpCadreId:globalCadreId}
		
	}).done(function(result){
		$("#donutchart").html("");
		$("#totalComplaintsId").html("");
		$("#complaintStatusUL").html("");
		if(result != null && result.subList != null && result.subList.length > 0)
		{
		  categroyWiseChart(result.subList);
		  buildCategroyInfo(result);
		}
	  else
	   $("#totalComplaintsId").html("0");
		  
	  
	});
}

function categroyWiseChart(result)
{
	
	var dataArr = [];
	var colorsarr = new Array();
	for(var i in result)
	{
	  var data= new Array();
	  data.push(result[i].status,result[i].count);
	  dataArr.push(data);
	  colorsarr.push(result[i].color);
		  
	}

	Highcharts.setOptions({
        colors: colorsarr
    });
    $('#donutchart').highcharts({
        chart: {
            type: 'pie',
			backgroundColor: 'transparent',
            options3d: {
                enabled: false,
                alpha: 50
            }
        },
		legend: {
                enabled: true,
                align: 'right',
                verticalAlign: 'right',
                floating: false,
                backgroundColor: (Highcharts.theme && Highcharts.theme.background2) || 'white',
                borderColor: '#CCC',
                borderWidth: 1,
                shadow: false
            },
        plotOptions: {
            pie: {
                innerSize: 40,
                depth: 10,
				dataLabels: {
                    enabled: false,
				}
            }, 
        },
		
		series: [{
			name : 'Count',
            data: dataArr
        }]
    });

}

function buildCategroyInfo(result)
{
	$("#totalComplaintsId").html(""+result.count+"");
	var str = '';
	for(var i in result.subList)
	{
		str += '<li class="show-dropdown">';
		if(result.subList[i].status.toLowerCase() == ("In Progress").toLowerCase())
		{
		 str += '<span class="InProgress"></span>'+result.subList[i].status+'<span><span class="pull-right">'+result.subList[i].count+'</span></span>';
		}
		if(result.subList[i].status.toLowerCase() == ("Completed").toLowerCase())
		{
		 str += '<span class="Completed">'+result.subList[i].status+'<span class="pull-right">'+result.subList[i].count+'</span></span>';
		}
		if(result.subList[i].status.toLowerCase() == ("Not Verified").toLowerCase())
		{
		 str += '<span class="NotVerified">'+result.subList[i].status+'<span class="pull-right">'+result.subList[i].count+'</span></span>';
		}
		if(result.subList[i].status.toLowerCase() == ("Not Eligible").toLowerCase())
		{
		 str += '<span class="NotEligible"></span>'+result.subList[i].status+'<span><span class="pull-right">'+result.subList[i].count+'</span></span>';
		}
		if(result.subList[i].status.toLowerCase() == ("Not possible").toLowerCase())
		{
		 str += '<span class="NotPossible">'+result.subList[i].status+'<span class="pull-right">'+result.subList[i].count+'</span></span>';
		}
		if(result.subList[i].status.toLowerCase() == ("Approved").toLowerCase())
		{
		 str += '<span class="Approved">'+result.subList[i].status+'<span class="pull-right">'+result.subList[i].count+'</span></span>';
		}
		if(result.subList[i].status.toLowerCase() == ("Verified").toLowerCase())
		{
		 str += '<span class="Verified">'+result.subList[i].status+'<span class="pull-right">'+result.subList[i].count+'</span></span>';
		}
		str += '<ul class="count-hover arrow_box3">';
		for(var j in result.subList[i].subList)
		str += '<li>'+result.subList[i].subList[j].status+'<span class="pull-right">'+result.subList[i].subList[j].count+'</span></li>';
		str += '</ul>';
		str += '</li>';
	}
	$("#complaintStatusUL").html(str);
	
}


var membershipId = '${memberShipId}';
var constituencyId = '${constituencyId}';
if((globalCadreId == null || globalCadreId.trim().length == 0) && (membershipId != null && membershipId > 0))
{
		getCadreIdByMemberShipId();
}
else 
{
	getParticipatedConstituencyId(globalCadreId);
	/* getCategoryWiseStatusCount();
	getTotalMemberShipRegistrationsInCadreLocation();		
	
	getElectionPerformanceInCadreLocation();
	getApprovedFinancialSupprotForCadre();
	cadreFormalDetailedInformation(globalCadreId);
	getEventDetailsOfCadre(globalCadreId);
	//getTdpCadreSurveyDetails(globalCadreId,0,null,"All",0);
	getLocationwiseCommitteesCount();
	getPartyMeetingsOverViewForCadre();
	getEventsOverviewFortdpCadre(); */
		
}
function getCadreIdByMemberShipId()
{
	$.ajax({
		type : "POST",
		url  : "getCadreIdByMembershipIdAction.action",
		data : {membershipId:membershipId,constituencyId:constituencyId}
		
	}).done(function(result){
		console.log(result);
		if(result != null)
		{
			globalCadreId = result;
			if((participatedConstituencyId == null || participatedConstituencyId.length == 0) && (globalCadreId != null && globalCadreId > 0))
			{
				getParticipatedConstituencyId(globalCadreId);
				/* getCategoryWiseStatusCount();
				getTotalMemberShipRegistrationsInCadreLocation();		
				//getCadreFamilyDetailsByCadreId();
				getElectionPerformanceInCadreLocation();
				getApprovedFinancialSupprotForCadre();
				cadreFormalDetailedInformation(globalCadreId);
				getEventDetailsOfCadre(globalCadreId);
				//getTdpCadreSurveyDetails(globalCadreId,0,null,"All",0);
				getLocationwiseCommitteesCount();
				getPartyMeetingsOverViewForCadre();
				getEventsOverviewFortdpCadre(); */
			}
			else{
				getCategoryWiseStatusCount();
				getTotalMemberShipRegistrationsInCadreLocation();		
				//getCadreFamilyDetailsByCadreId();
				getElectionPerformanceInCadreLocation();
				getApprovedFinancialSupprotForCadre();
				cadreFormalDetailedInformation(globalCadreId);
				getEventDetailsOfCadre(globalCadreId);
				//getTdpCadreSurveyDetails(globalCadreId,0,null,"All",0);
				getLocationwiseCommitteesCount();
				getPartyMeetingsOverViewForCadre();
				getEventsOverviewFortdpCadre();
			}
		}
		
		
	});
	
}

function getLocationwiseCommitteesCount()
{
	var locationId=0;
	var electionType="";
	if(participatedConstituencyType !=null && participatedConstituencyType !="" && participatedConstituencyType !=undefined){
		electionType = participatedConstituencyType;
		if(participatedConstituencyId !=0){
			locationId=participatedConstituencyId;
		}else if(participatedParliamentId !=0){
			locationId = participatedParliamentId;
		}
	}
	
	var locationType = $("input[name='committeeLocation']:checked").val();
		
	$("#committeesCountDiv").html("");
	$("#committeesCountDiv").html('<img alt="Processing Image" src="./images/icons/search.gif">');
	$.ajax({
		type : "POST",
		url  : "getLocationwiseCommitteesCountAction.action",
		data : {locationType : locationType,tdpCadreId:globalCadreId,locationId:locationId,electionType:electionType}
	}).done(function(result){
		if(result != null && result.length > 0)
		 buildLocationwiseCommitteesCount(result);
	 else
	  $("#committeesCountDiv").html("No Data Available.");
		console.log(result);
	});
}

function buildLocationwiseCommitteesCount(result)
{
	var str = '';
	str += '<table class="table m_0 table-bordered">';
	str += '<tr>';
	str += '<th></th>';
    str += '<th class="text-center" colspan="5">Main Committees</th>';
    str += '<th class="text-center" colspan="5">Affliated Committees</th>';
    str += '</tr>';
	str += '<tr>';
    str += '<th></th>';
    str += '<th>Total</th>';
    str += '<th>Started</th>';
	str += '<th>Not Yet Started</th>';
    str += '<th>Completed</th>';
	
    str += '<th>Members</th>';
    str += '<th>Total</th>';
    str += '<th>Started</th>';
	str += '<th>Not Yet Started</th>';
    str += '<th>Completed</th>';
	
    str += '<th>Members</th>';
    str += '</tr>';
	
	if(result[0].areaType !=null && result[0].areaType !=""){
		globalAreaType=result[0].areaType;
	}
	
	for(var i in result)
	{
	   str += '<tr>';
	  if(result[i].locationType == "Village" || result[i].locationType =="mandalLevelVillage" || result[i].locationType =="Ward" || result[i].locationType=="municipalWiseWards")
	   str += '<td>Village/Ward Committees</td>';
   
     if(result[i].locationType == "Mandal" || result[i].locationType=="localElection")
	   str += '<td>Mandal/Town/Division Committees</td>';
   
    if(result[i].locationType == "District")
	   str += '<td>District Committees</td>';
   
   var affiNotyetStarted=0;
   var mainNotyetStarted=0;
	if(result[i].mainCommTotalCount !=null && result[i].mainCommTotalCount !=0){
		mainNotyetStarted=result[i].mainCommTotalCount - (result[i].mainCommStartedCount + result[i].mainCommCompletedCount);
	}
	if(result[i].affiCommTotalCount !=null && result[i].affiCommTotalCount !=0){
		affiNotyetStarted=result[i].affiCommTotalCount -(result[i].affiCommStartedCount + result[i].affiCommCompletedCount);
	}
   
     str += '<td>'+result[i].mainCommTotalCount+'</td>';
	 str += '<td>'+result[i].mainCommStartedCount+'</td>';
	  str += '<td>'+mainNotyetStarted+'</td>';
	 str += '<td>'+result[i].mainCommCompletedCount+'</td>';
	 str += '<td>'+result[i].mainCommTotalMembers+'</td>';
	 str += '<td>'+result[i].affiCommTotalCount+'</td>';
	 str += '<td>'+result[i].affiCommStartedCount+'</td>';
	  str += '<td>'+affiNotyetStarted+'</td>';
	 str += '<td>'+result[i].affiCommCompletedCount+'</td>';
	 
	 str += '<td>'+result[i].affiCommTotalMembers+'</td>';
	 
      str += '</tr>';
	}
	
	str += '</table>';
	$("#committeesCountDiv").html(str);
}


$('.committeeLocCls').click(function(){
    getLocationwiseCommitteesCount();
  });

//getCadreIdByMemberShipId();
//getCategoryWiseStatusCount();

$(document).on("click",".candidateRedirectedCls",function(){
	var candidateId=$(this).attr("attr_candidateId");;
	var categoryId=$(this).attr("attr_categoryId");
	var benefitId=$(this).attr("attr_benefitId");
	var fromDate=$(this).attr("attr_fromDate");
	var toDate=$(this).attr("attr_toDate");
	var articlesCount=$(this).attr("attr_count");
	var type="candidate";
	
	var win = window.open('articleDetailsAction.action?cid='+candidateId+'&caid='+categoryId+'&bfid='+benefitId+'&fdt='+fromDate+'&tdt='+toDate+'&typ='+type+'&atCnt='+articlesCount+'', '_blank');
});

$(document).on("click",".departmentNewsCls",function(){
	var fromDate=$(this).attr("attr_fromDate");
	var toDate=$(this).attr("attr_toDate");
	var departmentId=$(this).attr("attr_departmentId");
	var locationId=$(this).attr("attr_locationId");
	var locationType=$(this).attr("attr_locationType");
	var articlesCount=$(this).attr("attr_count");
	var type="department";
	
	var win = window.open('articleDetailsAction.action?did='+departmentId+'&fdt='+fromDate+'&tdt='+toDate+'&typ='+type+'&lid='+locationId+'&ltp='+locationType+'&atCnt='+articlesCount+'', '_blank');
	
});

$(document).on("click",".analysisNewsCls",function(){
	var fromDate=$(this).attr("attr_fromDate");
	var toDate=$(this).attr("attr_toDate");
	var benefitId=$(this).attr("attr_benefitId");
	var locationId=$(this).attr("attr_locationId");
	var partyId=$(this).attr("attr_partyId");
	var propertyId=$(this).attr("attr_propertyId");
	var propertyType=$(this).attr("attr_propertyType");
	var articlesCount=$(this).attr("attr_count");
	var locationType=$(this).attr("attr_locationType");
	var type="location"; 

	var win = window.open('articleDetailsAction.action?fdt='+fromDate+'&tdt='+toDate+'&typ='+type+'&bfid='+benefitId+'&lid='+locationId+'&ltp='+locationType+'&ptid='+partyId+'&prid='+propertyId+'&pttp='+propertyType+'&atCnt='+articlesCount+'','_blank');
});

$(document).on("click",".actionReactionNewsCls",function(){
	var fromDate=$(this).attr("attr_fromDate");
	var toDate=$(this).attr("attr_toDate");
	var locationId=$(this).attr("attr_locationId");
	var partyId=$(this).attr("attr_partyId");
	var propertyId=$(this).attr("attr_propertyId");
	var secondaryPartyId=$(this).attr("attr_secondaryPartyId");
	var propertyType=$(this).attr("attr_propertyType");
	var articlesCount=$(this).attr("attr_count");
	var locationType=$(this).attr("attr_locationType");
	var type="location";
	
	var win = window.open('articleDetailsAction.action?fdt='+fromDate+'&tdt='+toDate+'&typ='+type+'&scpid='+secondaryPartyId+'&ltp='+locationType+'&lid='+locationId+'&ptid='+partyId+'&prid='+propertyId+'&pttp='+propertyType+'&atCnt='+articlesCount+'','_blank');
});

$(document).on("click",".summaryNewsCls",function(){
	var fromDate=$(this).attr("attr_fromDate");
	var toDate=$(this).attr("attr_toDate");
	var locationId=$(this).attr("attr_locationId");
	var partyId=$(this).attr("attr_partyId");
	var propertyId=$(this).attr("attr_propertyId");
	var propertyType=$(this).attr("attr_propertyType");
	var articlesCount=$(this).attr("attr_count");
	var locationType=$(this).attr("attr_locationType");
	var type="location"; 

	var win = window.open('articleDetailsAction.action?fdt='+fromDate+'&tdt='+toDate+'&typ='+type+'&lid='+locationId+'&ltp='+locationType+'&ptid='+partyId+'&prid='+propertyId+'&pttp='+propertyType+'&atCnt='+articlesCount+'','_blank');
});


function getDeathsAndHospitalizationDetails(){
	
	//data Loading image
	$("#dataLoadingsImgForDeathCount").show();
	$("#deathHospitalDivId").html("");
	
	var panchayatId=globalPanchayatId;
	if(panchayatId ==undefined || panchayatId =="" || panchayatId ==null){
		panchayatId=0;
	}
	var mandalId=globalTehsilId;
	if(mandalId ==undefined || mandalId =="" || mandalId ==null){
		mandalId=0;
	}
	
	var constituencyId=globalConstituencyId;
	if(constituencyId ==undefined || constituencyId =="" || constituencyId ==null){
		constituencyId=0;
	}
	
	var parliamentId=globalParliamentId;
	if(parliamentId ==undefined || parliamentId =="" || parliamentId ==null){
		parliamentId=0;
	}
	
	var districtId=globalDistrictId;
	if(districtId ==undefined || districtId =="" || districtId ==null){
		districtId=0;
	}

	var jsObj={
		panchayatId:panchayatId,
		mandalId:mandalId,
		constituencyId:constituencyId,
		parliamentId:parliamentId, 
		districtId:districtId
	}
	$.ajax({
			type:'POST',
			 url: 'getDeathsAndHospitalizationDetailsAction.action',
			 data : {task:JSON.stringify(jsObj)} ,
			}).done(function(result){
				$("#dataLoadingsImgForDeathCount").hide();
				
				var str='';
				if(result !=null){
					if(result.verifierVOList !=null && result.verifierVOList.length>0){
						 str+'<div class="panel-body pad_0">';
							str+='<table class="table m_0 table-bordered m_0">'
								
								str+='<thead>';
									str+='<th style="background-color:#f5f5f5">Location</th>';
									str+='<th style="background-color:#f5f5f5">Death</th>';
									str+='<th style="background-color:#f5f5f5">Hospitalization</th>';
								str+='</thead>'
								for(var i in result.verifierVOList){
									str+='<tr>';
										str+='<td id="'+result.verifierVOList[i].id+'">'+result.verifierVOList[i].name+'</td>';
										if(result.verifierVOList[i].verifierVOList !=null && result.verifierVOList[i].verifierVOList.length>0){
											for(var j in result.verifierVOList[i].verifierVOList){
												if(result.verifierVOList[i].verifierVOList[j].count !=0){
													str+='<td><a class="deathDetailsCls" attr_locationId='+result.verifierVOList[i].id+' attr_locationType='+result.verifierVOList[i].name+' attr_insuranceTypeId='+result.verifierVOList[i].verifierVOList[j].id+' attr_insuranceType='+result.verifierVOList[i].verifierVOList[j].name+' style="cursor:pointer;" data-toggle="modal" data-target=".myModalForDeath">'+result.verifierVOList[i].verifierVOList[j].count+'</a></td>';
												}
												else{
													str+='<td>'+result.verifierVOList[i].verifierVOList[j].count+'</td>';
												}
												
											}
										}
										else{
											str+='<td>0</td>';
											str+='<td>0</td>';
										}
									str+='</tr>';
								}	
								
							str+'</table>';
						str+='</div>';
					}
					else{
						 str+='<div>Death And Hospital Data is Not Available.</div>';
					}
			 }
			 else{
				 str+='<div>Some Problem Occured Please Contact Admin.</div>';
			 }
				
			$("#deathHospitalDivId").html(str);
		});
}


$(document).on("click",".deathDetailsCls",function(){
	var locationId=$(this).attr("attr_locationId");
	var locationType=$(this).attr("attr_locationType");
	var insuranceTypeId=$(this).attr("attr_insuranceTypeId");
	var insuranceType=$(this).attr("attr_insuranceType");
	
	getCadresDetailsOfDeathsAndHospitalization(locationId,locationType,insuranceTypeId,insuranceType);
});

function getCadresDetailsOfDeathsAndHospitalization(locationId,locationType,insuranceTypeId,insuranceType){
	
	$("#deathTitleId").html('');
	$("#popupForDeathDetails").html('');
	
	$("#dataLoadingsImgForDeathPopUp").show();
	
	var jsObj={
		locationId:locationId,
		locationType:locationType,
		insuranceTypeId:insuranceTypeId
		
	}
	
	$.ajax({
			type:'POST',
			 url: 'getCadresDetailsOfDeathsAndHospitalizationAction.action',
			 data : {task:JSON.stringify(jsObj)} ,
			}).done(function(result){
				
				$("#dataLoadingsImgForDeathPopUp").hide();
				
				$("#deathTitleId").html("<div style='color:green;'>"+insuranceType+" Cadres Details</div>");
				if(result !=null){
						if(result.knownList !=null && result.knownList.length>0){
							buildingAllCadresDetails(result.knownList);
						}
						else{
							$("#popupForDeathDetails").html("No Data Available.");
						}
					}
				else{
					$("#popupForDeathDetails").html("Problem occured.Please contact admin.");
				}
			});
}
function buildingAllCadresDetails(result){
	var str='';
	for(var i in result){
			
			str+='<div class="media " style="border-bottom: 1px solid rgb(51, 51, 51);" attr_cadre_id='+result[i].cadreId+'>';
				str+='<span href="#" class="media-left">';
				str+='<img style="width: 64px; height: 64px;" src="'+result[i].imagePath+'" />';
				str+='</span>';
				str+='<div class="media-body">';
				str+='<h5 class="media-heading"> <span style="font-weight:bold;"> Name:</span> '+result[i].name+' ; ';				
				str+=' <span style="font-weight:bold;"> Relative Name: </span>'+result[i].relativeName+' </h5>';
				str+='<ul class="list-inline">';
				str+='<li>Age:'+result[i].age+';</li>';
				str+='<li>Mobile No: '+result[i].mobileNo+'</li>';
				str+='<li>Caste: '+result[i].casteName+'</li>';
				str+='<li>Voter ID: '+result[i].voterIdCardNo+'</li>';
				str+='<li>MemberShip No: '+result[i].membershipNo+'</li>';
				str+='<li>Registered On: '+result[i].registeredOn+'</li>';
				str+='<li>Occupation: '+result[i].occupation+'</li>';
				str+='</ul>';
				 
				str+='</div>';
				<c:if test="${fn:contains(sessionScope.USER.entitlements, 'TDP_CADRE_DETAILS' )}">
				str+='<div id="cadreDetailsDivId" class="cadreDetailsCls" attr_cadre_id='+result[i].cadreId+' attr_membership_id='+result[i].membershipNo+' style="cursor:pointer;"><input type="button" value="More Cadre Details" class="btn btn-sm btn-primary pull-right"/></div>';
				</c:if>
			str+='</div>';
			
		}
		$("#popupForDeathDetails").html(str);
		$('.table-scroll-1').scrollator({
			custom_class: 'table-scroll-1',
		});
	
}
$(document).on("click",".cadreDetailsCls",function(){
		var cadreId=$(this).attr("attr_cadre_id");
		var redirectWindow=window.open('cadreDetailsAction.action?cadreId='+cadreId+'','_blank');
	});

function hideAndShowCommittee(typeId)
{
	if(typeId == 1)
	{
		if(participatedConstituencyType !=null && participatedConstituencyType !="" && participatedConstituencyType !=undefined){
			$(".urbanClass").hide();
		}else{
			if(globalAreaType == "URBAN"){
				$(".urbanClass").hide();
			}
			else{
				$(".urbanClass").show();
			}
		}
			$('#committeesDivId').show();
			$('#showId').hide();
			$('#hideId').show();
		
	}
	else if(typeId == 2)
	{
		$('#committeesDivId').hide();
		$('#showId').show();
		$('#hideId').hide();
	}
}
function hideAndShowSurvey(typeId)
{	
	if(typeId == 1)
	{
		getIVRDetails();
		$('#surveyDetailsId').show();
		$('#surveyshowId').hide();
		$('#surveyhideId').show();
	}
	else if(typeId == 2)
	{
		$('#surveyDetailsId').hide();
		$('#surveyshowId').show();
		$('#surveyhideId').hide();
	}
}

function hideAndShowIVRSurvey(typeId)
{	
	if(typeId == 1)
	{
		getIVRDetails();
		$('#ivrsurveyDetailsId').show();
		$('#ivrsurveyshowId').hide();
		$('#ivrsurveyhideId').show();
	}
	else if(typeId == 2)
	{
		$('#ivrsurveyDetailsId').hide();
		$('#ivrsurveyshowId').show();
		$('#ivrsurveyhideId').hide();
	}
}

function tableshidesandShow(divId,index)
{

	$('#topsurveyTable'+index+'').hide();
	$('.topsurveyTable'+index+'').hide();
	var styleStr = $('#'+divId+'').is(":visible")
	if(styleStr)
	{
		$('#'+divId+'').hide();
		$('.'+divId+'').hide();
		$('.topsurveyTable'+index+'').show();
	}
	else
	{
		$('#'+divId+'').show();
		$('.'+divId+'').show();
		$('#topsurveyTable'+index+'').show();
	}	
}

function newsHideAndShow(divId)
{
	$('.showbtnCls').hide();
	$('.dateCls').hide();
		var styleStr = $('#'+divId+'').is(":visible")
		console.log(styleStr);
		if(styleStr)
		{
			$('#'+divId+'').hide();
			$('.'+divId+'').hide();
			$('.showbtnCls').show();
			
		}
		else
		{
			$('#'+divId+'').show();
			$('.'+divId+'').show();
			$('.dateCls').show();
		}	
}

function getPartyMeetingDetails()
{
	$('#partyMeetingDescDiv').html('');
	var jsObj={
		tdpCadreId:globalCadreId		
	}	
	$.ajax({
			type:'POST',
			 url: 'getPartyMeetingDetailsForCadre.action',
			 data : {task:JSON.stringify(jsObj)} ,
			}).done(function(result){
				if(result != null && result.partyMeetingVOList != null && result.partyMeetingVOList.length >0)
				{
					var str='';
					str+='<table class="table m_0 table-bordered">';
					str+='<thead>';
					str+='<th class="text-center" colspan="3">PARTY MEETINGS</th>';
					str+='</thead>';
					str+='<tr class="text-center">';
					var myResult = result.partyMeetingVOList[0];

						str+='<td>'+myResult.invitedCount+'<br/>Invited</td>';						
						if(parseInt(myResult.invitedCount) > parseInt(myResult.attendedCount))
						{
							var absentCount = parseInt(myResult.invitedCount) - parseInt(myResult.attendedCount);
							str+='<td>'+myResult.attendedCount+'<br/>Attended</td>';
							str+='<td>'+absentCount+'<br/>Absent</td>';
						}
						else if(parseInt(myResult.invitedCount) < parseInt(myResult.attendedCount))
						{
							var absentCount = 0;
							str+='<td>'+parseInt(myResult.attendedCount)+'<br/>Attended</td>';
							str+='<td>'+absentCount+'<br/>Absent</td>';
						}
						else
						{
							var absentCount = parseInt(myResult.invitedCount) - parseInt(myResult.attendedCount);
							str+='<td>'+myResult.attendedCount+'<br/>Attended</td>';
							str+='<td>'+absentCount+'<br/>Absent</td>';
						}
						str+='</tr>';
					str+='</table>';

					$('#partyMeetingDescDiv').html(str);
				}
			});
}


function getPartyMeetingDetaildReprt()
{
	$('#partyMetindetlsDivId').html('');
	var jsObj={
		tdpCadreId:globalCadreId
	}	
	$.ajax({
			type:'POST',
			 url: 'getPartyMeetingDetailsForCadre.action',
			 data : {task:JSON.stringify(jsObj)} ,
			}).done(function(result){
				if(result != null && result.partyMeetingVOList != null && result.partyMeetingVOList.length >0)
				{
					$('#partymettingParlDivId').show();
					$('#partyMeetingDetailsShowHideDiv').show();
					var str='';
					str+='<table class="table m_0 table-bordered" style="margin-top:10px;">';
					str+='<thead>';
					//str+='<tr>';
					//str+='<th colspan="4" style="background-color:#CCCCCC;text-align:center;"> PARTY MEETINGS PARTICIPATION DETAILS </th>';						
					//str+='</tr>'; 
					str+='<tr>';						
					str+='<th class="text-center"> MEETING NAME </th>';
					str+='<th class="text-center"> INVITED </th>';
					str+='<th class="text-center"> ATTENDED </th>';
					str+='<th class="text-center"> ABSENT </th>';								
					str+='</tr>';
					str+='</thead>';
					
					for(var i in result.partyMeetingVOList)
					{
						str+='<tr class="text-center">';
						str+='<td>'+result.partyMeetingVOList[i].location+' - '+result.partyMeetingVOList[i].name+' </td>';
						str+='<td> <ul class="list-inline"><li class="show-dropdown invitedDetlsDiv" name="invitedDetlsDiv'+i+'" key="'+result.partyMeetingVOList[i].id+'"><u style="color:#23527C;"> '+result.partyMeetingVOList[i].invitedCount+'</u> ';
						str+='<ul class="count-hover left_arrow" >';
						str+='<li>';
							str+='<div id="invitedDetlsDiv'+i+'" class="invitationCls"></div>';
						str+='</li>';
						str+='</ul>';
						
						str+='</td>';
						
						if(parseInt(result.partyMeetingVOList[i].invitedCount) > parseInt(result.partyMeetingVOList[i].attendedCount))
						{
							var absentCount = parseInt(result.partyMeetingVOList[i].invitedCount) - parseInt(result.partyMeetingVOList[i].attendedCount);
							str+='<td> '+result.partyMeetingVOList[i].attendedCount+' </td>';
							str+='<td> '+absentCount+' </td>';
							
						/*str+='<td> <ul class="list-inline"><li class="show-dropdown"><u style="color:#23527C;">'+absentCount+'</u>';
						str+='<ul class="count-hover right_arrow" >';
						str+='<li>';
						str+='<table class="table table-bordered" style="margin: 10px">';
						str+='<thead>';
						
						str+='<tr>';
						//str+='<th style=""> MEETING NAME </th>';
						str+='<th style=""> LOCATION </th>';
						str+='<th style=""> TIME </th>';
						str+='<th style=""> STATUS </th>';
						str+='</tr>';
						str+='<thead>';
						str+='<tbody>';
						if(absetPArtyMeetingsArr != null && absetPArtyMeetingsArr.length>0)
						{
							for(var k in absetPArtyMeetingsArr)
							{
								str+='<tr>';
									//str+='<td> '+absetPArtyMeetingsArr[k].meetingName+'</td>';
									str+='<td> '+absetPArtyMeetingsArr[k].location+' </td>';
									str+='<td> '+absetPArtyMeetingsArr[k].time+' </td>';
									str+='<td> '+absetPArtyMeetingsArr[k].status+' </td>';
								str+='</tr>';
							}
						}
						str+='</tbody>';
						str+='</table>';
						str+='</li>';
						str+='</ul> </td>';*/
						}
						else if(parseInt(result.partyMeetingVOList[i].invitedCount) < parseInt(result.partyMeetingVOList[i].attendedCount))
						{
							var absentCount = 0;
							str+='<td> '+result.partyMeetingVOList[i].attendedCount+' </td>';
							str+='<td> '+absentCount+' </td>';
						}
						else
						{
							var absentCount = 0;
							str+='<td> '+result.partyMeetingVOList[i].attendedCount+' </td>';
							str+='<td> '+absentCount+' </td>';							

						}
						str+='</tr>';
					}
					
					str+='</table>';


					$('#partyMetindetlsDivId').html(str);
					str='';
					$(".invitedDetlsDiv").hover(function(){
						var divId = $(this).attr('name');
						var meetingTypeId = $(this).attr('key');
						var jsObj={
							tdpCadreId:globalCadreId,
							meetingTypeId:meetingTypeId
						}	
						$.ajax({
								type:'POST',
								 url: 'getPartyMeetingTypeWiseDetails.action',
								 data : {task:JSON.stringify(jsObj)} ,
								}).done(function(result){
									var str='';
									if(result != null && result.partyMeetingVOList != null && result.partyMeetingVOList.length >0)
									{//srish
										str+='<table class="table table-bordered" style="margin: 10px">';
										str+='<thead>';
										str+='<tr>';
										str+='<th style=""> LOCATION </th>';
										str+='<th style=""> TIME </th>';
										str+='<th style=""> START DATE</th>';
										str+='<th style=""> END DATE</th>';
										str+='</tr>';
										str+='<thead>';
										str+='<tbody>';
										if(result.partyMeetingVOList != null && result.partyMeetingVOList.length>0)
										{
											for(var k in result.partyMeetingVOList)
											{
												str+='<tr>';
												str+='<td>'+result.partyMeetingVOList[k].location+'</td>';
												str+='<td>'+result.partyMeetingVOList[k].name+'</td>';										
												str+='<td>'+result.partyMeetingVOList[k].startDateStr+'</td>';
												str+='<td>'+result.partyMeetingVOList[k].endDateStr+'</td>';
												str+='</tr>';
											}
										}
										str+='</tbody>';
										str+='</table>';
										
										$('#'+divId+'').html(str);
									}
								});
					});
				}
			});
}

var absetPArtyMeetingsArr = new Array();
function getPartyMeetingsOverViewForCadre()
{
	absetPArtyMeetingsArr=[];
	$('#partyMetindetlsDivId').html('');
	var jsObj={
		tdpCadreId:globalCadreId
	}	
	$.ajax({
			type:'POST',
			 url: 'getPartyMeetingsOverViewForCadre.action',
			 data : {task:JSON.stringify(jsObj)} ,
			}).done(function(myResult){
				if(myResult != null)
				{
					var str='';
					str+='<table class="table m_0 table-bordered">';
					str+='<thead>';
					str+='<th class="text-center" colspan="3">PARTY MEETINGS</th>';
					str+='</thead>';
					str+='<tr class="text-center">';
						str+='<td>'+myResult.invitedCount+'<br/>Invited</td>';						
						if(parseInt(myResult.invitedCount) > parseInt(myResult.attendedCount))
						{
							var absentCount = parseInt(myResult.invitedCount) - parseInt(myResult.attendedCount);
							str+='<td>'+myResult.attendedCount+'<br/>Attended</td>';
							str+='<td>'+absentCount+'<br/>Absent</td>';
						}
						else if(parseInt(myResult.invitedCount) < parseInt(myResult.attendedCount))
						{
							var absentCount = 0;
							str+='<td>'+myResult.attendedCount+'<br/>Attended</td>';
							str+='<td>'+absentCount+'<br/>Absent</td>';
						}
						else
						{
							var absentCount = parseInt(myResult.invitedCount) - parseInt(myResult.attendedCount);
							str+='<td>'+myResult.attendedCount+'<br/>Attended</td>';
							str+='<td>'+absentCount+'<br/>Absent</td>';
						}
						str+='</tr>';
					str+='</table>';

					$('#partyMeetingDescDiv').html(str);
					
					var resultList = myResult.partyMeetingVOList;
					if(resultList != null && resultList.length>0)
					{
						for(var k in resultList)
						{
							var absentObj = {
								id:resultList[k].id,
								meetingName:resultList[k].meetingType,
								location:resultList[k].location,
								time:resultList[k].name,
								status : resultList[k].memberStatus
							};							
							absetPArtyMeetingsArr.push(absentObj);
						}
					}
					
					getPartyMeetingDetaildReprt();
				}
			});
}


function getEventsOverviewFortdpCadre()
{
	$('#eventInvitatinDiv').html('');
	var jsObj={
		tdpCadreId:globalCadreId
	}	
	$.ajax({
			type:'POST',
			 url: 'getEventsOverviewFortdpCadre.action',
			 data : {task:JSON.stringify(jsObj)} ,
			}).done(function(myResult){
				if(myResult != null)
				{
					var str='';
					str+='<table class="table m_0 table-bordered">';
					str+='<thead>';
					str+='<th class="text-center" colspan="3"> EVENTS INVITATION </th>';
					str+='</thead>';
					str+='<tr class="text-center">';
						str+='<td>'+myResult.invitedCount+'<br/>Invited</td>';						
						if(parseInt(myResult.invitedCount) > parseInt(myResult.attendedCount))
						{
							var absentCount = parseInt(myResult.invitedCount) - parseInt(myResult.attendedCount);
							str+='<td>'+myResult.attendedCount+'<br/>Attended</td>';
							str+='<td>'+absentCount+'<br/>Absent</td>';
						}
						else if(parseInt(myResult.invitedCount) < parseInt(myResult.attendedCount))
						{
							var absentCount = 0;
							str+='<td>'+myResult.attendedCount+'<br/>Attended</td>';
							str+='<td>'+absentCount+'<br/>Absent</td>';
						}
						else
						{
							var absentCount = parseInt(myResult.invitedCount) - parseInt(myResult.attendedCount);
							str+='<td>'+myResult.attendedCount+'<br/>Attended</td>';
							str+='<td>'+absentCount+'<br/>Absent</td>';
						}
						str+='</tr>';
					str+='</table>';

					$('#eventInvitatinDiv').html(str);
				}
			});
}

getPartyMeetingsOverViewForCadre();
getEventsOverviewFortdpCadre();
</script>		
<script type="text/javascript">

	function getNtrTrustStudentDetailsInstitutionWise(type){
		
		$(".dataLoadingsImgForTabSection").show();
		$("#cadreIdSpanForEducationBenefit").html("");
		$("#familyIdSpanForEducationBenefit").html("");
		
		cadreId = globalCadreId;
		
		var cadreIdsArr=[];
		if(type == "cadre"){
			cadreIdsArr.push(cadreId);
			var jsObj={	
				cadreIdsArr:cadreIdsArr
			}	
		}
		else{
			var jsObj={	
				cadreIdsArr:familycadreIdsArrayGlobal
			}
		}
		
		$("#benefitsEducationCountsId").html("");
		
		
		/* var jsObj={
			tdpCadreId:cadreId,
			familyCadreIds:familycadreIdsArrayGlobal
		}	 */
		$.ajax({
				type:'POST',
				 url: 'getNtrTrustStudentDetailsInstitutionWiseAction.action',
				 data : {task:JSON.stringify(jsObj)} ,
				}).done(function(result){
					
					$(".dataLoadingsImgForTabSection").hide();
					
					var str='';
					if(result !=null){
						if(type == "cadre"){
							if(result.count !=null && result.count>0){
								//var rsltLst =  result.ntrTrustStudentVoList;
									buildingStudentDetailsInstitutionWiseOfCadre(result,type);
							}
							else{
								if(result.count ==null){
									result.count =0;
								}
								$("#cadreIdSpanForEducationBenefit").html('NTR TRUST EDUCATION BENEFITS<ul class="pull-right"><li>'+result.count+'</li></ul>');
							}
						}
						if(type == "family"){
							if(result.count !=null && result.count>0){
									buildingStudentDetailsInstitutionWiseOfFamily(result,type);
							}else{
								if(result.count ==null){
									result.count =0;
								}
								$("#familyIdSpanForEducationBenefit").html('NTR TRUST EDUCATION BENEFITS<ul class="list-inline pull-right"><li>'+result.count+'</li></ul>');
							}
						}
					}
					
			});
	} 
	function buildingStudentDetailsInstitutionWiseOfCadre(result,type){
		str='';
		if(result !=null){
			if(type =="cadre"){
			$("#cadreIdSpanForEducationBenefit").html('NTR TRUST EDUCATION BENEFITS<ul class="pull-right list-inline showStudentBenefitModalcls" attr_title="NTR TRUST EDUCATION BENEFITS" attr_type="'+type+'"><li><a style="cursor:pointer;">'+result.count+'</a></li></ul>');
			}
			
			if(result.ntrTrustStudentVoList !=null && result.ntrTrustStudentVoList.length>0){
				str+='<div class="panel-group" id="accordionNtrTrust1" role="tablist" aria-multiselectable="true">';
				
						for(var i in result.ntrTrustStudentVoList){
							str+='<div class="panel panel-default">';
							str+='<div class="panel-heading">';
							
							str+='<a role="button" data-toggle="collapse" data-parent="#accordionNtrTrust1" href="#collapseNtrOne'+i+'" aria-expanded="true" aria-controls="collapseNtrOne'+i+'" class="ntrBenefitCountCls" attr_id="'+result.ntrTrustStudentVoList[i].id+'" attr_type="'+type+'" style="cursor:pointer" >';
							str+='<h4 class="panel-title" id="headingNtrOne'+i+'">'+result.ntrTrustStudentVoList[i].name+'';
							str+='<span class=" pull-right">'+result.ntrTrustStudentVoList[i].count+'</span>';
							str+='</h4>';
							str+='</a>';
							str+='</div>';
							str+='<div id="collapseNtrOne'+i+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingNtrOne'+i+'">';
							str+='<div class="panel-body">';
							str+='<center><img id="dataLoadingImageForModelSchoolCadre" src="images/icons/loading.gif" style="width:50px;height:50px;margin-top:50px;display:none"/></center>';
							str+='<div class="NtrTrustStudentDivClass">';
								
							str+='</div>';
							str+='</div>';
							str+='</div>';
							str+='</div>';
						}
				str+='</div>';
				$("#benefitsEducationCountsId").html(str);
			}		
			
		}
	}
	
	function buildingStudentDetailsInstitutionWiseOfFamily(result,type){
		str='';
		if(result !=null){
			$("#familyIdSpanForEducationBenefit").html('NTR TRUST EDUCATION BENEFITS<span class="pull-right showStudentBenefitModalclsFamily" attr_title="NTR TRUST EDUCATION BENEFITS" attr_type="'+type+'"><a style="cursor:pointer;">'+result.count+'</a></span>');

			
			if(result.ntrTrustStudentVoList !=null && result.ntrTrustStudentVoList.length>0){
				str+='<div class="panel-group" id="accordionNtrTrustFamily" role="tablist" aria-multiselectable="true">';
				
						for(var i in result.ntrTrustStudentVoList){
							str+='<div class="panel panel-default">';
							str+='<div class="panel-heading">';
							
							str+='<a role="button" data-toggle="collapse" data-parent="#accordionNtrTrustFamily" href="#collapseNtrOneFamily'+i+'" aria-expanded="true" aria-controls="collapseNtrOneFamily'+i+'" class="ntrBenefitCountClsFamily" attr_id="'+result.ntrTrustStudentVoList[i].id+'" attr_type="'+type+'" style="cursor:pointer" >';
							str+='<h4 class="panel-title" id="headingNtrOneFamily'+i+'">'+result.ntrTrustStudentVoList[i].name+'';
							str+='<span class=" pull-right">'+result.ntrTrustStudentVoList[i].count+'</span>';
							str+='</h4>';
							str+='</a>';
							str+='</div>';
							str+='<div id="collapseNtrOneFamily'+i+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingNtrOneFamily'+i+'">';
							str+='<div class="panel-body">';
							str+='<center><img id="dataLoadingImageForModelSchoolFamily" src="images/icons/loading.gif" style="width:50px;height:50px;margin-top:50px;display:none"/></center>';
							str+='<div class="NtrTrustStudentDivClassFamily">';
								
							str+='</div>';
							str+='</div>';
							str+='</div>';
							str+='</div>';
						}
				str+='</div>';
				$("#benefitsEducationCountsFamilyId").html(str);
			}		
			
		}
	}
	
	$(document).on("click",".showStudentBenefitModalcls",function(){
		var title=$(this).attr("attr_title");
		var type = $(this).attr("attr_type");
			$(".modelTitleCls").html(title);
			$("#modelForBenefitsId").modal("show");
	});
	
	$(document).on("click",".showStudentBenefitModalclsFamily",function(){
		var title=$(this).attr("attr_title");
		var type = $(this).attr("attr_type");
		$(".modelTitleClsFamily").html(title);
		$("#modelForBenefitsFamilyId").modal("show");
	});
	
	$(document).on("click",".ntrBenefitCountCls",function(){
		var instituId=$(this).attr("attr_id");
		var type=$(this).attr("attr_type");
		getStudentFormalDetailsByCadre(instituId,type);
	});
	
	$(document).on("click",".ntrBenefitCountClsFamily",function(){
		var instituId=$(this).attr("attr_id");
		var type=$(this).attr("attr_type");
		getStudentFormalDetailsByCadre(instituId,type);
	});
	function getStudentFormalDetailsByCadre(institutionId,type){
		
		
		if(type =="cadre"){
			$(".NtrTrustStudentDivClass").html("");
			$("#dataLoadingImageForModelSchoolCadre").show();
		}else{
			$(".NtrTrustStudentDivClassFamily").html("");
			$("#dataLoadingImageForModelSchoolFamily").show();
		}
		
		cadreId = globalCadreId;
		
		var cadreIds=[];
		if(type == "cadre"){
			cadreIds.push(cadreId);
			var jsObj={	
				cadreIds:cadreIds,
				institutionId:institutionId
			}	
		}
		else{
			var jsObj={	
				cadreIds:familycadreIdsArrayGlobal,
				institutionId:institutionId
			}
		}
		$.ajax({
				type:'POST',
				 url: 'getStudentFormalDetailsByCadreAction.action',
				 data : {task:JSON.stringify(jsObj)} ,
				}).done(function(result){
					
					//$("#dataLoadingsImgForNtrTrust").hide();
					if(type =="cadre"){
						$("#dataLoadingImageForModelSchoolCadre").hide();
					}else{
						$("#dataLoadingImageForModelSchoolFamily").hide();
					}
					var str='';
					if(result !=null && result.length>0){
						if(type =="cadre"){
							str+='<div class="panel-group" style="margin:0" id="accordion323" role="tablist" aria-multiselectable="true">';
						}
						else{
							str+='<div class="panel-group" style="margin:0" id="accordion3255" role="tablist" aria-multiselectable="true">';
						}
						
						for(var i in result){
						  str+='<div class="panel panel-default" style="margin-top:0px">';
						
							if(type == "cadre"){
								str+='<div class="panel-heading" style="background-color:#f4f4f4" role="tab" id="headingOne'+i+'">';
								str+='<a role="button" data-toggle="collapse" data-parent="#accordion323" href="#collapseOne'+i+'" aria-expanded="true" aria-controls="collapseOne'+i+'">';
									str+='<h4 class="panel-title" id="'+result[i].id+'">'+result[i].name+'</h4>';
								str+='</a>';
							}
							else{
								str+='<div class="panel-heading" style="background-color:#f4f4f4" role="tab" id="headingTwo'+i+'">';
								str+='<a role="button" data-toggle="collapse" data-parent="#accordion3255" href="#collapsetwo'+i+'" aria-expanded="true" aria-controls="collapsetwo'+i+'">';
									str+='<h4 class="panel-title" id="'+result[i].id+'">'+result[i].name+'</h4>';
								str+='</a>';
							}
								
							str+='</div>';
							if(type == "cadre"){
								str+='<div id="collapseOne'+i+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne'+i+'">';
							}
							else{
								str+='<div id="collapsetwo'+i+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo'+i+'">';
							}
							  str+='<div class="panel-body">';
								str+='<ul class="list-inline">';
									str+='<li><b>Father Name </b> : '+result[i].fatherName+'</li>';
									str+='<li><b>Mother Name </b> :'+result[i].motherName+'</li>';
									if(result[i].guardian !=null && result[i].guardian !=""){
										str+='<li><b>Guardian </b> :'+result[i].guardian+'</li>';
									}
									str+='<li><b>Caste </b>:'+result[i].casteStr+'</li>';
									str+='<li><b>Date Of Birth </b> :'+result[i].dateStr+'</li>';
									if(result[i].yearOfJoining !=null && result[i].yearOfJoining !=""){
										str+='<li><b>Year Of Joining </b> :'+result[i].yearOfJoining+'</li>';
									}
									if(result[i].tdpCadreId !=null && result[i].tdpCadreId >0){
										str+='<li><b>Cadre Id </b> :'+result[i].tdpCadreId+'</li>';
									}
									if(result[i].membershipNo !=null && result[i].membershipNo>0){
										str+='<li><b>MemberShip No </b> :'+result[i].membershipNo+'</li>';
									}
									str+='<li><b>Relation With Cadre</b> :'+result[i].relation+'</li>';
								str+='</ul>'
								
								str+='<h4 class="m_0" style="color:#a94442 !important"><i class="glyphicon glyphicon-map-marker text-danger" style="font-size:14px"></i>Communication Address</h4><hr class="m_0"/>'
								
								for(var j in result[i].addressDetailsList){
									str+='<ul class="Student-List">';
									if(result[i].addressDetailsList[j].houseNoStr !=null && result[i].addressDetailsList[j].houseNoStr !=""){
										str+='<li><b>H NO</b> : '+result[i].addressDetailsList[j].houseNoStr+'</li>';
									}
									if(result[i].addressDetailsList[j].stateStr !=null && result[i].addressDetailsList[j].stateStr !=""){
										str+='<li><b>State</b> : '+result[i].addressDetailsList[j].stateStr+'</li>';
									}
									if(result[i].addressDetailsList[j].districtStr !=null && result[i].addressDetailsList[j].districtStr !=""){
										str+='<li><b>District</b> : '+result[i].addressDetailsList[j].districtStr+'</li>';
									}
									if(result[i].addressDetailsList[j].constituencyStr !=null && result[i].addressDetailsList[j].constituencyStr !=""){
										str+='<li><b>Constituency</b> : '+result[i].addressDetailsList[j].constituencyStr+' </li>';
									}
									if(result[i].addressDetailsList[j].tehsilStr !=null && result[i].addressDetailsList[j].tehsilStr !=""){
										str+='<li><b>Mandal</b> : '+result[i].addressDetailsList[j].tehsilStr+'</li>';
									}
									if(result[i].addressDetailsList[j].localElectionBodyStr !=null && result[i].addressDetailsList[j].localElectionBodyStr !=""){
										str+='<li><b>Muncipality</b> : '+result[i].addressDetailsList[j].localElectionBodyStr+' </li>';
									}
									if(result[i].addressDetailsList[j].panchayatStr !=null && result[i].addressDetailsList[j].panchayatStr !=""){
										str+='<li><b>Panchayat</b> : '+result[i].addressDetailsList[j].panchayatStr+'  </li>';
									}
									if(result[i].addressDetailsList[j].wardStr !=null && result[i].addressDetailsList[j].wardStr !=""){
										str+='<li><b>Ward</b> : '+result[i].addressDetailsList[j].wardStr+' Ward</li>';
									}
									if(result[i].addressDetailsList[j].locationStr !=null && result[i].addressDetailsList[j].locationStr !=""){
										str+='<li><b>Location</b> : '+result[i].addressDetailsList[j].locationStr+' </li>';
									}
									if(result[i].addressDetailsList[j].streetStr !=null && result[i].addressDetailsList[j].streetStr !=""){
										str+='<li><b>Street</b> : '+result[i].addressDetailsList[j].streetStr+' </li>';
									}
									if(result[i].addressDetailsList[j].pincodeLng !=null && result[i].addressDetailsList[j].pincodeLng>0){
										str+='<li><b>Pincode</b> : '+result[i].addressDetailsList[j].pincodeLng+'</li>';
									}
									
									for(var l in result[i].ntrTrustStudentVoList){
											str+='<li><b>Contact:</b>'+result[i].ntrTrustStudentVoList[l].phoneNo+'('+result[i].ntrTrustStudentVoList[l].phoneType+')';
											str+='</li>';
										}
									str+='</ul>';
								}
								str+='<h4 class="m_0" style="color:#a94442 !important"><i class="glyphicon glyphicon-book text-danger" style="font-size:14px"></i> Acedamic Details</h4><hr class="m_0"/>';
								str+='<table class="table table-bordered">';
									str+='<thead>';
										str+='<th style="background-color:#f5f5f5">Course</th>';
										str+='<th style="background-color:#f5f5f5">Start Date</th>';
										str+='<th style="background-color:#f5f5f5">End Date</th>';
									str+='</thead>';
									for(var k in result[i].academicDetailsList){
										str+='<tr>';
											str+='<td>'+result[i].course+'</td>';
											str+='<td>'+result[i].academicDetailsList[k].startMonth+' '+result[i].academicDetailsList[k].startYear+'</td>';
											str+='<td>'+result[i].academicDetailsList[k].endMonth+' '+result[i].academicDetailsList[k].endYear+'</td>';
										str+='</tr>';
									}
																		
								str+='</table>';
								
								str+='<h4 class="m_0" style="color:#a94442 !important"><i class="glyphicon glyphicon-share text-danger" style="font-size:14px"></i> Recommendation Details</h4><hr class="m_0"/>';
								str+='<table class="table table-bordered">';
								  str+='<thead>';
									str+='<th style="background-color:#f5f5f5">Person Name</th>';
									str+='<th style="background-color:#f5f5f5">Designation</th>';
									str+='<th style="background-color:#f5f5f5">Contact</th>';
									str+='<th style="background-color:#f5f5f5">MemberShip No</th>';
								  str+='</thead>';
								  for(var b in result[i].recomendationDetailsList){
									str+='<tr>';
										str+='<td>'+result[i].recomendationDetailsList[b].name+'</td>';
										str+='<td>'+result[i].recomendationDetailsList[b].designation+'</td>';
										str+='<td>'+result[i].recomendationDetailsList[b].phoneNo+'</td>';
										if(result[i].recomendationDetailsList[b].membershipNo !=null && result[i].recomendationDetailsList[b].membershipNo >0){
											str+='<td>'+result[i].recomendationDetailsList[b].membershipNo+'</td>';
										}else{
											str+='<td>-</td>';
										}
										
									str+='</tr>';
								  }
								 
								str+='</table>';
							  str+='</div>';
							str+='</div>';
						  str+='</div>';
						}
						str+='</div>';
					}
					if(type=="cadre"){
						$(".NtrTrustStudentDivClass").html(str);
					}else{
						$(".NtrTrustStudentDivClassFamily").html(str);
					}
				});
	}
	
	/* function buildNtrStudentDetails(result){
		var str='';
			if(result !=null){
				
				if(type=="family"){
					 //str+='<h4 class="m_0 text-primary">Family</h4>';
					 str+='<div class="panel-group" style="margin-top:10px" id="accordion324" role="tablist" aria-multiselectable="true">';
				}
				if(type=="cadre"){
					// str+='<h4 class="m_0 text-primary">Cadre</h4>';
					str+='<div class="panel-group" style="margin-top:10px" id="accordion323" role="tablist" aria-multiselectable="true">';
				}
				
						for(var i in result){
						  str+='<div class="panel panel-default" style="margin:0px">';
						  
						  if(type == "family"){
							  str+='<div class="panel-heading" role="tab" id="headingTwo'+i+'">';
								str+='<a role="button" data-toggle="collapse" data-parent="#accordion324" href="#collapseTwo'+i+'" aria-expanded="true" aria-controls="collapseTwo'+i+'">';
									str+='<h4 class="panel-title" id="'+result[i].id+'">'+result[i].name+'</h4>';
								str+='</a>';
							str+='</div>';
							str+='<div id="collapseTwo'+i+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo'+i+'">';
						  }
						  if(type=="cadre"){
							str+='<div class="panel-heading" role="tab" id="headingOne'+i+'">';
								str+='<a role="button" data-toggle="collapse" data-parent="#accordion323" href="#collapseOne'+i+'" aria-expanded="true" aria-controls="collapseOne'+i+'">';
									str+='<h4 class="panel-title" id="'+result[i].id+'">'+result[i].name+'</h4>';
								str+='</a>';
							str+='</div>';
							str+='<div id="collapseOne'+i+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne'+i+'">';
						  }
							  str+='<div class="panel-body">';
								str+='<ul class="list-inline">';
									str+='<li><b>Father Name </b> : '+result[i].fatherName+'</li>';
									str+='<li><b>Mother Name </b> :'+result[i].motherName+'</li>';
									if(result[i].guardian !=null && result[i].guardian !=""){
										str+='<li><b>Guardian </b> :'+result[i].guardian+'</li>';
									}
									str+='<li><b>Caste </b>:'+result[i].casteStr+'</li>';
									str+='<li><b>Date Of Birth </b> :'+result[i].dateStr+'</li>';
									if(result[i].yearOfJoining !=null && result[i].yearOfJoining !=""){
										str+='<li><b>Year Of Joining </b> :'+result[i].yearOfJoining+'</li>';
									}
									if(result[i].tdpCadreId !=null && result[i].tdpCadreId >0){
										str+='<li><b>Cadre Id </b> :'+result[i].tdpCadreId+'</li>';
									}
									if(result[i].membershipNo !=null && result[i].membershipNo>0){
										str+='<li><b>MemberShip No </b> :'+result[i].membershipNo+'</li>';
									}
									str+='<li><b>Relation With Cadre</b> :'+result[i].relation+'</li>';
								str+='</ul>'
								
								str+='<h4 class="m_0" style="color:#a94442 !important "><i class="glyphicon glyphicon-map-marker text-danger" style="font-size:14px"></i>Communication Address</h4><hr class="m_0"/>'
								
								for(var j in result[i].addressDetailsList){
									str+='<ul class="Student-List">';
									if(result[i].addressDetailsList[j].houseNoStr !=null && result[i].addressDetailsList[j].houseNoStr !=""){
										str+='<li><b>H NO</b> : '+result[i].addressDetailsList[j].houseNoStr+'</li>';
									}
									if(result[i].addressDetailsList[j].stateStr !=null && result[i].addressDetailsList[j].stateStr !=""){
										str+='<li><b>State</b> : '+result[i].addressDetailsList[j].stateStr+'</li>';
									}
									if(result[i].addressDetailsList[j].districtStr !=null && result[i].addressDetailsList[j].districtStr !=""){
										str+='<li><b>District</b> : '+result[i].addressDetailsList[j].districtStr+'</li>';
									}
									if(result[i].addressDetailsList[j].constituencyStr !=null && result[i].addressDetailsList[j].constituencyStr !=""){
										str+='<li><b>Constituency</b> : '+result[i].addressDetailsList[j].constituencyStr+' </li>';
									}
									if(result[i].addressDetailsList[j].tehsilStr !=null && result[i].addressDetailsList[j].tehsilStr !=""){
										str+='<li><b>Mandal</b> : '+result[i].addressDetailsList[j].tehsilStr+'</li>';
									}
									if(result[i].addressDetailsList[j].localElectionBodyStr !=null && result[i].addressDetailsList[j].localElectionBodyStr !=""){
										str+='<li><b>Muncipality</b> : '+result[i].addressDetailsList[j].localElectionBodyStr+' </li>';
									}
									if(result[i].addressDetailsList[j].panchayatStr !=null && result[i].addressDetailsList[j].panchayatStr !=""){
										str+='<li><b>Panchayat</b> : '+result[i].addressDetailsList[j].panchayatStr+'  </li>';
									}
									if(result[i].addressDetailsList[j].wardStr !=null && result[i].addressDetailsList[j].wardStr !=""){
										str+='<li><b>Ward</b> : '+result[i].addressDetailsList[j].wardStr+' Ward</li>';
									}
									if(result[i].addressDetailsList[j].locationStr !=null && result[i].addressDetailsList[j].locationStr !=""){
										str+='<li><b>Location</b> : '+result[i].addressDetailsList[j].locationStr+' </li>';
									}
									if(result[i].addressDetailsList[j].streetStr !=null && result[i].addressDetailsList[j].streetStr !=""){
										str+='<li><b>Street</b> : '+result[i].addressDetailsList[j].streetStr+' </li>';
									}
									if(result[i].addressDetailsList[j].pincodeLng !=null && result[i].addressDetailsList[j].pincodeLng>0){
										str+='<li><b>Pincode</b> : '+result[i].addressDetailsList[j].pincodeLng+'</li>';
									}
									
									for(var l in result[i].ntrTrustStudentVoList){
											str+='<li><b>Contact:</b>'+result[i].ntrTrustStudentVoList[l].phoneNo+'('+result[i].ntrTrustStudentVoList[l].phoneType+')';
											str+='</li>';
										}
									str+='</ul>';
								}
								str+='<h4 class="m_0" style="color:#a94442 !important"><i class="glyphicon glyphicon-book text-danger" style="font-size:14px"></i> Acadamic Details</h4><hr class="m_0"/>';
								str+='<table class="table table-bordered">';
									str+='<thead>';
										str+='<th style="background-color:#f5f5f5">Course</th>';
										str+='<th style="background-color:#f5f5f5">Start Date</th>';
										str+='<th style="background-color:#f5f5f5">End Date</th>';
									str+='</thead>';
									for(var k in result[i].academicDetailsList){
										str+='<tr>';
											str+='<td>'+result[i].course+'</td>';
											str+='<td>'+result[i].academicDetailsList[k].startMonth+' '+result[i].academicDetailsList[k].startYear+'</td>';
											str+='<td>'+result[i].academicDetailsList[k].endMonth+' '+result[i].academicDetailsList[k].endYear+'</td>';
										str+='</tr>';
									}
																		
								str+='</table>';
								
								str+='<h4 class="m_0" style="color:#a94442 !important"><i class="glyphicon glyphicon-share text-danger" style="font-size:14px"></i> Recommendation Details</h4><hr class="m_0"/>';
								str+='<table class="table table-bordered">';
								  str+='<thead>';
									str+='<th style="background-color:#f5f5f5">Person Name</th>';
									str+='<th style="background-color:#f5f5f5">Designation</th>';
									str+='<th style="background-color:#f5f5f5">Contact</th>';
									str+='<th style="background-color:#f5f5f5">MemberShip No</th>';
								  str+='</thead>';
								  for(var b in result[i].recomendationDetailsList){
									str+='<tr>';
										str+='<td>'+result[i].recomendationDetailsList[b].name+'</td>';
										str+='<td>'+result[i].recomendationDetailsList[b].designation+'</td>';
										str+='<td>'+result[i].recomendationDetailsList[b].phoneNo+'</td>';
										if(result[i].recomendationDetailsList[b].membershipNo !=null && result[i].recomendationDetailsList[b].membershipNo >0){
											str+='<td>'+result[i].recomendationDetailsList[b].membershipNo+'</td>';
										}else{
											str+='<td>-</td>';
										}
										
									str+='</tr>';
								  }
								 
								str+='</table>';
							  str+='</div>';
							str+='</div>';
						  str+='</div>';
						}
						str+='</div>';
					}
			if(type =="cadre"){
				$("#ntrTrustDetails").html(str);
			}else if(type == "family"){
				$("#ntrTrustCadreFamilyDetails").html(str);
			} 
		
	}*/
	
	
//getCandidateAndConstituencySurveyResult();
var candiConstiSurveyCount = 0;
function getCandidateAndConstituencySurveyResult()
{
	$('.surveyDetailsCls').html("");
	$('#surveyDataLoadoing').show();
	
	var candidateId = globalCandidateId;
	var constituencyId = 0;
	
	if(participatedConstituencyId != null && participatedConstituencyId > 0){
		constituencyId = participatedConstituencyId;
	}else if(globalConstituencyId > 0){
		constituencyId = globalConstituencyId;
	}
	var surveyId = 0;
	
	var jsObj={
			candidateId:candidateId,
			constituencyId:constituencyId,
			surveyId:surveyId
	}
	$.ajax({
		type:'GET',
		url :'getCandidateAndConstituencySurveyResultAction.action',
		data : {task:JSON.stringify(jsObj)} ,
	}).done(function(result){
		if(result != null){
			if(result.length != null && result.length > 0){
				candiConstiSurveyCount = result.length;
			}
			buildCandidateAndConstituencySurveyResult(result,surveyId,null);
			
		}
	});
}

function getCandidateAndConstituencySurveyResultBySurvey(surveyId,divId){
	var candidateId = globalCandidateId;
	var constituencyId = 0;
	
	if(participatedConstituencyId != null && participatedConstituencyId > 0){
		constituencyId = participatedConstituencyId;
	}else if(globalConstituencyId > 0){
		constituencyId = globalConstituencyId;
	}
	var surveyId = surveyId;
	//$("#"+divId+"").html("");
	
	var jsObj={
			candidateId:candidateId,
			constituencyId:constituencyId,
			surveyId:surveyId
	}
	$.ajax({
		type:'GET',
		url :'getCandidateAndConstituencySurveyResultAction.action',
		data : {task:JSON.stringify(jsObj)} ,
	}).done(function(result){
		if(result != null){
			buildCandidateAndConstituencySurveyResult(result,surveyId,divId);
			
		}
	});
}

function buildCandidateAndConstituencySurveyResult(result,surveyId,divId){
	if(result != null && result.length > 0){
		if(surveyId == 0){
			var str='';
			str+='<div class="panel-group" style="margin-top:20px" id="accordion121" role="tablist" aria-multiselectable="true">';
			for(var i in result){
			 str+='<div class="panel panel-default">';
					str+='<div class="panel-heading" role="tab" id="headingOne'+i+'" attr_survy_divId="candiConstSurveyId'+i+'" onclick="getCandidateAndConstituencySurveyResultBySurvey('+result[i].surveyId+',\'candiConstSurveyId'+i+'\')">';
						str+='<a role="button" class="accordion-toggle" data-toggle="collapse" data-parent="#accordion121" href="#collapseOne'+i+'" aria-expanded="true" aria-controls="collapseOne'+i+'">';
							str+='<h4 class="panel-title">';
								str+=''+result[i].surveyName+'';
							str+='</h4>';
						str+='</a>';
					str+='</div>';
					
					str+='<div id="collapseOne'+i+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne'+i+'">';
						str+='<center><img id="candiConstSurveyAjaxId'+result[i].surveyId+'" src="images/icons/survey-details.gif" style="width:250px;height:200px;"/></center>';
						str+='<div class="panel-body">';
						str+='<div  id="candiConstSurveyId'+i+'"></div>';
					 str+='</div>';
					str+='</div>';
				  str+='</div>';
			}
			str+='</div>';
			
			$('.surveyDetailsCls').html(str);
			$('#surveyDataLoadoing').hide();
		}else if(surveyId > 0){
		var temp="candiConstSurveyAjaxId"+surveyId+"";
		var str='';
		for(var i in result){
				  if(result[i].questions != null){
					for(var j in result[i].questions)
					{
						str+='<table class="table table-bordered">';
						str+='<tr><td colspan="3" style="text-weight:bold">'+result[i].questions[j].question+'</td></tr>';
						str+='<b><tr><td>Option</td><td>Count</td><td>Percentage</td></tr></b>';
						for(var k in result[i].questions[j].options){
							str+='<tr>';
								str+='<td>'+result[i].questions[j].options[k].optionVal+'</td>';
								str+='<td>'+result[i].questions[j].options[k].count+'</td>';
								str+='<td>'+result[i].questions[j].options[k].percentage+'</td>';
							str+='</tr>';
						}
						str+='</table>';
					}
					}
					}
				  $("#"+temp+"").hide();
				  $("#"+divId+"").html(str);
				 
		}
		
	}else{
		$('#surveyDataLoadoing').hide();
		$('.surveyDetailsCls').html("NO DATA AVAILABLE");
	}
}

function getIVRDetails()
{
	$("#ivrsurveyDataLoadoing").show();
	var candidateId = globalCandidateId;//290951
	$.ajax({
		url: "http://mytdp.com/Survey/WebService/getCandidateIVRResult/"+candidateId+""
	}).then(function(result) {
		if(result != null && result.length > 0){
			buildPublicScoreTable(result);
		}else{
			$("#ivrsurveyDataLoadoing").hide();
			$(".ivrDetailsCls").html("NO DATA AVAILABLE");
		}
	});
}

function buildPublicScoreTable(myResult)
{
	$(".ivrDetailsCls").html("");
	var str ="";
	if(myResult!=null){
		if(myResult.length>0){
			for(var i in myResult){
					result = myResult[i];
					
						str+="<h5 style='font-weight:bold'> ROUND - "+result.round+"</h5>";
						str+='<table style="margin:5px;" class="gridtable"  border="1"  width="95%">';
						str+='<thead>';
						str+='<tr>';
								str+='<th>Total Dialed</th>';
								str+='<th>Answered</th>';
								str+='<th>Feedback</th>';
								if(result.option1!=null){
									if(result.topPrior == "Option1"){
										if(result.option1Name!=null){
											str+='<th style="background:green">'+result.option1Name+'</th>';
										}else{
											str+='<th style="background:green">Option1</th>';
										}
									}else{
										if(result.option1Name!=null){
											str+='<th>'+result.option1Name+'</th>';
										}else{
											str+='<th>Option1</th>';
										}
									}
								}
								if(result.option2!=null){
									if(result.topPrior == "Option2"){
										if(result.option2Name!=null){
											str+='<th style="background:green">'+result.option2Name+'</th>';
										}else{
											str+='<th style="background:green">Option2</th>';
										}
									}else{
										if(result.option2Name!=null){
											str+='<th>'+result.option2Name+'</th>';
										}else{
											str+='<th>Option2</th>';
										}
									}
								}
								if(result.option3!=null){
									if(result.topPrior == "Option3"){
										if(result.option3Name!=null){
											str+='<th style="background:green">'+result.option3Name+'</th>';
										}else{
											str+='<th style="background:green">Option3</th>';
										}
									}else{
										if(result.option3Name!=null){
											str+='<th>'+result.option3Name+'</th>';
										}else{
											str+='<th>Option3</th>';
										}
									}
								}
								if(result.option4!=null){
									if(result.topPrior == "Option4"){
										if(result.option4Name!=null){
											str+='<th style="background:green">'+result.option4Name+'</th>';
										}else{
											str+='<th style="background:green">Option4</th>';
										}
									}else{
										if(result.option4Name!=null){
											str+='<th>'+result.option4Name+'</th>';
										}else{
											str+='<th>Option4</th>';
										}
									}
								}
								
								if(result.option5!=null){
									if(result.topPrior == "Option5"){
										if(result.option5Name!=null){
											str+='<th style="background:green">'+result.option5Name+'</th>';
										}else{
											str+='<th style="background:green">Option5</th>';
										}
									}else{
										if(result.option5Name!=null){
											str+='<th>'+result.option5Name+'</th>';
										}else{
											str+='<th>Option5</th>';
										}
									}
								}
								if(result.option6!=null){
									if(result.topPrior == "Option6"){
										if(result.option6Name!=null){
											str+='<th style="background:green">'+result.option6Name+'</th>';
										}else{
											str+='<th style="background:green">Option6</th>';
										}
									}else{
										if(result.option6Name!=null){
											str+='<th>'+result.option6Name+'</th>';
										}else{
											str+='<th>Option6</th>';
										}
									}
								}
								
								if(result.others!=null){
									if(result.topPrior == "Others"){
										str+='<th style="background:green"> Others </th>';
									}else{
										str+='<th> Others </th>';
									}
								}
								str+='<th> TYPE </th>';
						str+='</tr>';
						str+='</thead>';
						str+='<tbody>';
					
						if(result!=null){
							str+="<tr>";
									str+="<td>"+result.totalDialed+"</td>";
									str+="<td>"+result.answered+"( "+result.answeredPer+" %)</td>";
									str+="<td>"+result.feedback+" ( "+result.feedbackPer+" %)</td>";
									if(result.option1!=null){
										if(result.topPrior == "Option1"){
											str+='<td style="background:lightGreen">'+result.option1+'('+result.option1Per+' %)</td>';
										}else{
											str+='<td>'+result.option1+'('+result.option1Per+' %)</td>';
										}
									}
									if(result.option2!=null){
										if(result.topPrior == "Option2"){
											str+='<td style="background:lightGreen">'+result.option2+'('+result.option2Per+' %)</td>';
										}else{
											str+='<td>'+result.option2+'('+result.option2Per+' %)</td>';
										}
									}
									if(result.option3!=null){
										if(result.topPrior == "Option3"){
											str+='<td style="background:lightGreen">'+result.option3+'('+result.option3Per+' %)</td>';
										}else{
											str+='<td>'+result.option3+'('+result.option3Per+' %)</td>';
										}
											
									}
									if(result.option4!=null){
										if(result.topPrior == "Option4"){
											str+='<td style="background:lightGreen">'+result.option4+'('+result.option4Per+' %)</td>';
										}else{
											str+='<td>'+result.option4+'('+result.option4Per+' %)</td>';
										}
									}
									if(result.option5!=null){
										if(result.topPrior == "Option5"){
											str+='<td style="background:lightGreen">'+result.option5+'('+result.option5Per+' %)</td>';
										}else{
											str+='<td>'+result.option5+'('+result.option5Per+' %)</td>';
										}
									}
									if(result.option6!=null){
										if(result.topPrior == "Option6"){
											str+='<td style="background:lightGreen">'+result.option6+'('+result.option6Per+' %)</td>';
										}else{
											str+='<td>'+result.option6+'('+result.option6Per+' %)</td>';
										}
									}
									
									if(result.others!=null){
										if(result.topPrior == "Others"){
											str+="<td style='background:lightGreen'>"+result.others+" ( "+result.othersPer+" %)</td>";
										}else{
											str+="<td>"+result.others+" ( "+result.othersPer+" %)</td>";
										}
									}
									str+="<td>"+result.type+"</td>";
								str+="</tr>";
							}
				str+"</tbody>";
				str+="</table>";
			}
		}
	}
	$("#ivrsurveyDataLoadoing").hide();
	$(".ivrDetailsCls").html(str);
}

getStatusCountOfCadreForInvitationAndAttendance();
function getStatusCountOfCadreForInvitationAndAttendance(){
	
	$("#trainingDetailsBodyId").html('');
	$("#modelForTrainingDetails").hide();
	var jsObj ={
		tdpCadreId:globalCadreId
	}
	$.ajax({
		type:'GET',
		url :'getStatusCountOfCadreForInvitationAndAttendanceAction.action',
		data : {task:JSON.stringify(jsObj)} ,
	}).done(function(result){
		if(result != null && result.length>0 && result !=""){
			buiildingTrainigStatusDetailsOfCadre(result);
		}
		else{
			$("#trainingDetailsMainDivId").hide();
			$("#trainingDetailsBodyId").html("No Data Available");
		}
	});
	
}
function buiildingTrainigStatusDetailsOfCadre(result){
	
	$("#trainingDetailsMainDivId").show();
	var str='';
	
	$("#totalInvitedCountId").html(result[0].totalInviteeCount +'</br>Invited');
	$("#totalAttendedCountId").html(result[0].totalAttendedCount+'</br>Attended');
	$("#totalAbsentedCountId").html(result[0].totalAbsentCount+'</br>Absent');
	
	for(var i in result){
		str+='<tr>';
			 str+='<td id="'+result[i].id+'">'+result[i].progName+'</td>';
			 str+='<td>'+result[i].count+'</td>';
			 str+='<td>'+result[i].total+'</td>';
			 if(result[i].dateString =="Absent"){
				 str+='<td>1</td>';
			 }
			 else{
				  str+='<td>0</td>';
			 }
			 str+='<td><input class="btn btn-sm btn-primary detailsCls" attr_programId="'+result[i].id+'" type="button" value="Details" type="button" data-toggle="modal" data-target=".modelForTrainingDetails"/></td>';
		str+='</tr>';
	}
	$("#trainingDetailsBodyId").html(str);
}

$(document).on("click",".detailsCls",function(){
	
	var programId = $(this).attr("attr_programId");
	var cadreId = globalCadreId;
	
	$("#modelForTrainingDetails").show();
	$("#dataLoadingsImgForTrainingDetails").show();
	getAttendedTrainingCampBatchDetailsOfCadre(programId,cadreId);
	getRemarkSOfCadreByCallPurpose(programId,cadreId);
	
});

function getAttendedTrainingCampBatchDetailsOfCadre(programId,cadreId){
	
	var jsObj ={
		programId:programId,
		tdpCadreId:cadreId
	}
	$.ajax({
		type:'GET',
		url :'getAttendedTrainingCampBatchDetailsOfCadreAction.action',
		data : {task:JSON.stringify(jsObj)} ,
	}).done(function(result){
		if(result != null)
		{
			var str='';
			str+='<table class="table table-bordered">';
				str+='<thead>';
					str+='<th>Program Name</th>';
					str+='<th>Center Name</th>';
					str+='<th>Batch Name</th>';
					for(var i in result.simpleVOList1){
						var day = parseInt(i) + parseInt(1);
						str+='<th>Day '+day+' ('+result.simpleVOList1[i].dateString+')</th>';
					}
				str+='</thead>';
				str+='<tbody>';
					str+='<tr>';
						str+='<td>'+result.progName+'</td>';
						str+='<td>'+result.campName+'</td>';
						str+='<td>'+result.batchName+'</td>';
						for(var i in result.simpleVOList1){
							str+='<td>'+result.simpleVOList1[i].isAttended+'</td>';
						}
					str+='</tr>';
				str+='</tbody>';
			str+='</table>';
			
			$("#trainingDetailsTableId").html(str);
		}else{
			//$("#trainingDetailsTableId").html("NO ATTENDNACE DATA AVAILABLE...");
			$("#trainingDetailsTableId").html("");
		}
						
	});
	
}
function getRemarkSOfCadreByCallPurpose(programId,cadreId){
	var jsObj ={
		programId:programId,
		tdpCadreId:cadreId
	}
	$.ajax({
		type:'GET',
		url :'getRemarkSOfCadreByCallPurposeAction.action',
		data : {task:JSON.stringify(jsObj)} ,
	}).done(function(result){
		if(result != null && result.length > 0){
			var str='';
			str+='<h4 class="m_0">REMARKS</h4>';
			str+='<table class="table table-bordered m_top10" >';
				str+='<thead>';
					str+='<th>Purpose</th>';
					str+='<th>Remarks</th>';
					str+='<th>Date & Time</th>';
				str+='</thead>';
				str+='<tbody>';
				for(var i in result){
					if(result[i].remarks != null && result[i].remarks.length > 0){
						str+='<tr>';
							str+='<td>'+result[i].name+'</td>';
							str+='<td>'+result[i].remarks+'</td>';
							str+='<td>'+result[i].dateString+'</td>';
						str+='</tr>';
					}
				}
				str+='</tbody>';
			str+='</table>';
			
			$("#dataLoadingsImgForTrainingDetails").hide();
			$("#remarkDetailsId").html(str);
		}
		else{
			$("#dataLoadingsImgForTrainingDetails").hide();
		}
	});
}


</script>

</body>
</html>