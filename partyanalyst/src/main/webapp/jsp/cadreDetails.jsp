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
<title> Leader Matrix </title>
	<!-- Bootstrap -->
    <link href="js/cadreCommittee/dist/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
	<!--Bootstrap DatePicker-->
    <link href="js/cadreCommittee/bootstrapDaterangepicker/daterangepicker-bs3.css" rel="stylesheet" type="text/css" />
		<!--Hover Menu-->
    <link href="css/cadreCommitee/css/jquery.smartmenus.bootstrap.css" rel="stylesheet" type="text/css" />
    	<!--Circle-->
    <link href="js/cadreCommittee/dist/css/jquery.circliful.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"/> 
	<link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
	<link href="js/scrollator/fm.scrollator.jquery.css" rel="stylesheet" type="text/css">
	<link href="dist/Icomoon/style.css" rel="stylesheet" type="text/css">
	<link href="dist/scroll/jquery.mCustomScrollbar.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" href="dist/sliderbar/bootstrap-slider.css">
    <link href="newCoreDashBoard/Plugins/RangeSlider/iThing.css" type="text/css" rel="stylesheet"/>
    <link href="newCoreDashBoard/Plugins/RangeSlider/jquery-ui-1.8.10.custom.css" type="text/css" rel="stylesheet"/>
	<link href="newCoreDashBoard/Plugins/Slick/slick.css" type="text/css" rel="stylesheet"/>
     <link href="newCoreDashBoard/Plugins/Slick/slick-theme.css" type="text/css" rel="stylesheet"/>
	     <!--   server side pagination CSS-->
    <link rel="stylesheet" type="text/css" href="styles/simplePagination-1/simplePagination.css"/>
		<!-- custom CSS-->
	<link href="css/cadreCommitee/cadreDetails_custom1.css" rel="stylesheet" type="text/css">
	<link href="dist/JqueryTe/jquery-te-1.4.0.css" rel="stylesheet" type="text/css">
	<!--<link href="css/cadreCommitee/cadreDetails_custom.css" rel="stylesheet" type="text/css">-->
	<!-- Add fancyBox main JS and CSS files -->  
	<link rel="stylesheet" href="js/jQuery/development-bundle/themes/base/jquery.ui.all.css" type="text/css" media="all" />
	<link href="dist/Timepicker/bootstrap-datetimepicker.min.css" type="text/css" rel="stylesheet"/>
	<link rel="stylesheet" type="text/css" href="newCoreDashBoard/css/simplePagination.css"/>
	<link rel="stylesheet" type="text/css" href="pdfexpand/source/jquery.fancybox.css"/>
	
						<!-- End -->

<style type="text/css">
.sessionInfoUl
{
	padding-left:10px;
}
.closeIconBenefit
{
	position:absolute;
	right:10px;
	cursor:pointer;
	top:10px;
}
.benefitCountCls
{
	cursor:pointer;
}
.constituencyDetailsDivId
{
	position:relative;
	background-color:#F0E6DA;
	box-shadow:inset 0px 0px 5px 3px rgba(0,0,0,0.4);
	padding:10px !important; 
}

#benefitsCollapseHeadingId
{
	cursor:pointer;
}
.expandIcon
{
	background-color:#CCC;
	padding:3px 4px;
	border-radius:3px;
	font-size:11px;
	cursor:pointer;
}
#familyBenefitsDivId table tr td:first-child ,#localityBasedBenefitsDivId table tr td:first-child
{
	font-size:12px;
}
#familyBenefitsDivId table tr:last-child td,#localityBasedBenefitsDivId table tr:last-child td
{
	background-color:#f9f9f9;
}
#familyBenefitsDivId table thead th,#localityBasedBenefitsDivId table thead th
{
	text-transform:uppercase;
	font-size:12px;
	background-color:#E9E9E9
}
.ulPost li
{
	border:1px solid green;
	padding:0px 5px 5px 5px;
}
.ulPost li .labelStatus 
{
	color:#fff;
}
.ulPost li
{
	list-style:none;
}
.ulPost
{
	padding-left:0px;
}

.partyMeetingsCollapseBody
{
	position:relative;
}
.casteWiseUl
{
	padding-left:0px;
}
.casteWiseUl li
{
	border:1px solid #ddd;
	padding:3px;
	margin-top:2px;
	list-style:none;
}
.mouse-over1
    {
        
       
        color:#fff;
        background:rgba(0,0,0,0.5);
        padding:10px;
        font-size:22px;
		margin-top:20px;
    }


.font-12{
	font-size:12px !important;
}
.mobileDetailsUl
{
	padding:0px;
	margin:0px;
}
.mobileDetailsUl li
{
	list-style:none;
	font-size:12px;
}
.verticalAlign thead th
{
	vertical-align:middle !important;
	text-align:center;
}
.closeIcon
{
	float:right;
	color:#666;
	background:#ccc;
	padding:4px;
	border-radius:50%;
	cursor:pointer;
	margin-bottom:4px;
}
.locWiseEvnAtnCls,.trainingFeedBackCls,.trainingQuizCls
{
	cursor:pointer;
}
.unselectable{
	min-height:24px !important;
}
.tablePartyMeetings thead th
{
	background-color:#ECECEC;
	vertical-align:middle !important;
	text-transform:uppercase;
	text-align:center;
	font-size:12px !important;
}
.tablePartyMeetings thead th , .tablePartyMeetings tr td
{	
	padding:3px !important;
	
}
.heightApply{height:990px !important;}
.pagination li .prev , .pagination li .next
{
	height: 34px;
    min-width: 52px !important;
}
.alertColorFont{
color: rgb(51, 122, 183);
}
.tableHeaderFontSize thead tr th{font-size:11px !important;background-color:#e9e9e9 !important;text-transform:uppercase !important;vertical-align:middle}
.scrollit {
    height:450px;
    overflow-y:auto;
}
#healthReportModalId{
    display: none;
    overflow-y:scroll;
}
#healthReportPdfOrImgId{
    display: none;
    overflow-y:scroll;
}
.table_customHealth tbody tr td{
	padding:2px !important;
	font-size:12px !important;
}
.mouse-over{
	background-color: rgba(0, 0, 0, 0.4);
    border-radius: 4px;
    color: #ffffff;
    cursor: default;
    font-size: 20px;
    font-weight: bold;
    height: 15%;
    left: 20px;
    padding: 20px 10px 35px 15px;
    position: absolute;
    text-align: center;
    top: 25px;
    width: 50%;
    z-index: 99;
	cursor:pointer;
}
</style>
<script>
var globalCadreId = '${cadreId}';
var cadreParticipatedConstiId = '${basicVo.id}';
var cadreParticipatedDistiId = '${basicVo.districtId}';
var cadreParticipatedParliId = '${basicVo.parliament}';
</script>
</head>
<body>

<script src="js/statusColor.js" type="text/javascript"></script>
<div class="modal fade" id="myModalForTableGrieId" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog " role="document" style="width: 90%;">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Candidate Name Complaint Information</h4>
	  </div>
      <div class="modal-body" >
		<div id="modelheightincr">
			<div id="popupContentDiv" ></div>
			<div id="paginationDivId" ></div>			
		</div>		
      </div>
     
    </div>
  </div>
</div>

<div class="modal fade" id="myModal111e" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog " role="document" style="width: 90%;">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel"> CADRE ACTIVITY ATTANDANCE DETAILS </h4>
	  </div>
      <div class="modal-body" >
		<div class="table-responsive" id="activityOverViewAttented"></div>
		
      </div>
     
    </div>
  </div>
</div>
<section>
	<div class="container">
    	<div class="row">
        	<div class="col-md-4 bg_white" style="padding-top:10px;">

			<input type="hidden" value="" id="cadreRole" />
			<input type="hidden" value="" id="cadreLevel" />
			<input type="hidden" value="" id="cadreCommitteeType" />
			<input type="hidden" value="" id="publicRepresentativeTypId" />
				
			<input type="hidden" value="" id="cadreBoothId" />
			<input type="hidden" value="" id="cadrePartNo" />
			<input type="hidden" value="" id="cadrePanchaytId" />
			
			<input type="hidden" value="" id="cadreWardId" />
			<input type="hidden" value="" id="wardName" />
			
			<input type="hidden" value="" id="cadremandalId" />
			<input type="hidden" value="" id="cadreRuralORUrbanId" />
			<input type="hidden" value="" id="cadreConstituencyId" />
			<input type="hidden" value="" id="cadrePConstituencyId" />
			<input type="hidden" value="" id="cadreDistrictId" />
			<input type="hidden" value="" id="cadreStateId" />
			<input type="hidden" value="" id="cadreVoterCardNo" />
			<input type="hidden" value="" id="cadreMemberShipId" />
			
			
			<input type="hidden" value="" id="cadreParticipatedDistrictId" />
			<input type="hidden" value="" id="cadreParticipatedConstituencyId" />
			<input type="hidden" value="" id="cadreParticipatedPConstituencyId" />
			<!--	<div class="row">
					<div class="col-md-12 col-xs-12 col-sm-12">
						<div class="bgWhite">
						  <ul class="nav nav-tabs navTabsCustomNew" role="tablist">
							<li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">identity & address</a></li>
							<li role="presentation"><a href="#profile" aria-controls="profile" role="tab" data-toggle="tab">previous enrolments</a></li>
							<li role="presentation"><a href="#messages" aria-controls="messages" role="tab" data-toggle="tab">family members</a></li>
						  </ul>
						  <div class="tab-content tabContentHead">
							<div role="tabpanel" class="tab-pane active" id="home">
								<p><span class="text-bold">REGISTERED ON</span> : Dec 16 2014</p>
								<p><span class="text-bold">AGE</span> : 41</p>
								<p><span class="text-bold">DOB</span> : May 14 1972</p>
								<p><span class="text-bold">QUALIFICATION</span> : Post Graduation</p>
								<p><span class="text-bold">OCCUPATION</span> : Business</p>
								<p><span class="text-bold">CASTE</span> : Kamma</p>
								<p><span class="text-bold">REG.THROUGH</span> : WEB</p>
							</div>
							<div role="tabpanel" class="tab-pane" id="profile">...</div>
							<div role="tabpanel" class="tab-pane" id="messages">...</div>
						  </div>
						</div>
					</div>
				</div>
			-->
            	<table class="table table-bordered">
                	<tr>
                    	<td class="text-bold"><i class="glyphicon glyphicon-user"></i> PERSONAL DETAILS</td>
                    </tr>
					
					<tr style="background:red" id="deletedReasonId">
                      
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
                                	<p class="m_0"><strong>NAME</strong> : <span id="nameId"></span>
										<span id="reportsInfoId1">
											<span id="reportsInfoId1"><i class="fa fa-heartbeat text-success nameClass"></i>
											</span>
										</span>
									</p>
                                    <p class="m_0"><strong>AGE</strong> : <span id="ageId"></span></p>
                                    <p class="m_0"><strong>DOB</strong> : <span id="dobId"></span></p>
                                    <p class="m_0"><strong>QUALIFICATION</strong> : <span id="qualificationId"></span></p>
                                    <p class="m_0"><strong>OCCUPATION</strong> : <span id="occupationId"></span></p>
                                    <p class="m_0"><strong>CASTE</strong> : <span id="casteFormalId"></span></p>
                                    <p class="m_0"><strong>REGISTERED ON</strong>: <span id="registeredOnId"></span></p>
                                    <p class="m_0"><strong>REG. THROUGH</strong>: <span id="registeredAtId"></span></p>

									<p class="m_0"><strong>Notes</strong>: <i class="glyphicon glyphicon-book remove-icon notesClass" data-toggle="tooltip" data-placement="bottom" style="margin-right: 3px;cursor:pointer;color:gray;" id="notesId" title="Click Here To Get Notes Details"></i>
								<!--
								<c:if test="${fn:contains(sessionScope.USER.entitlements, 'USER_NOTES_ENTITLEMENT' )}">
									<p class="m_0"><strong>Notes </strong>: <i class="glyphicon glyphicon-edit remove-icon" data-toggle="tooltip" data-placement="bottom" style="margin-right: 3px;cursor:pointer;" id="notesId" title="Click Here To Get Notes Details"></i></p>
								</c:if>
								-->
								&nbsp<strong>Profiles</strong>: <span id="profilesInfoId"></span>
								&nbsp<strong>Reports</strong>: <span id="reportsInfoId"></span>
								  		<!--<c:if test="${fn:length(cadreReportVOList) gt 0}">  
											&nbsp&nbsp&nbsp&nbsp<strong>Reports</strong>: <i class="glyphicon glyphicon-list-alt remove-icon"  data-placement="bottom" style="margin-right: 3px;cursor:pointer;color:green;" id="reportsId" title="Click Here To Get Reports Detail" data-toggle="modal" data-target="#reportModelId"></i>
										</c:if>
										<c:if test="${fn:length(cadreReportVOList) eq 0}">  
											&nbsp&nbsp&nbsp&nbsp<strong>Reports</strong>: <i class="glyphicon glyphicon-list-alt remove-icon"  data-toggle="tooltip" data-placement="bottom" style="margin-right: 3px;cursor:pointer;color:red;" id="reportsId" title="No Reports are available" ></i>
										</c:if>-->
										
								
								</p>
								</div>
                            </div>
                        </td>
                    </tr>
                    <tr>
                    	<td>
                        	<i class="glyphicon glyphicon-phone"></i> <span id="mobileNoId"></span> 
							<i class="glyphicon glyphicon-info-sign" style="cursor:pointer;display:none;" title="Click here to get Mobile details" id="mobileDetailsId"></i><span style="float: right" id="wAppImageId"></span><span style="float: right" id="fbUrlImageId"></span>
							<!--<button class="btn btn-success btn-xs col-sm-offset-2" id="leaderMainDetailesId">View Detailes</button>-->
							
                        	<span class="pull-right" id="emailMainSpanId">
	                            <i class="glyphicon glyphicon-envelope"></i> <span id="emailSpanId"></span> 
                            </span><br>
							<!-- <div style="margin-top:10px;margin-left:10px;">
								<span id="fbUrlImageId"></span>
								<span id="wAppImageId"></span>
							</div> -->
							<i class="glyphicon glyphicon-chevron-down changeIcon" style="margin-left:30px;display:none;"></i>
                        </td>
                    </tr>
                    <tr id="mobileDetailsDivId" style="display:none">
						<td>
							<i class="glyphicon glyphicon-remove pull-right text-danger CloseIcon" style="cursor:pointer;"></i>
							<ul class="mobileDetailsUl">
								<li><b>Connection Type:</b><span id="mobileConnectionTypeId" style="margin-left: 10px;"></span></li>
								<li><b>Provider:</b><span id="mobileNetworkProviderId" style="margin-left: 54px;"></span></li>
								<li><b>Brand:</b><span id="mobileBrandId" style="margin-left: 68px;"></span></li>
								<li><b>Feature:</b><span id="mobileFeatureId" style="margin-left: 59px;"></span></li>
								<li><b>Network:</b><span id="mobileNetworkTypeId" style="margin-left: 55px;"></span></li>
							</ul>
						</td>
					</tr>
					<tr class="enrollmentCls">
						<td class="text-bold"><i class="glyphicon glyphicon-bookmark"></i> PREVIOUS ENROLEMENTS</td>
					</tr>
					<tr class="enrollmentCls">
					  <td>
						<ul class="enrolled-mem" id="enrollementDiv">
						  
						 </ul>
					  </td>
					</tr>
                </table>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title pointer" id="identityHeaderId">
							<i class="icon-articles"></i> IDENTITY 
							<span class="pull-right" id="identityHideId" style="display:none;">
								<i class="glyphicon glyphicon-chevron-up"></i>
							</span>
							<span class="pull-right" id="identityShowId">
								<i class="glyphicon glyphicon-chevron-down"></i>
							</span>
						</h4>
					</div>
					<div class="panel-body"  id="identityBodyId">
						<p class="m_0">MEMBERSHIP ID : <span id="memberShipNoId"></span></p>
						<p class="m_0">VOTER CARD NO : <span id="voterIdSpan"></span>&nbsp;&nbsp;<span class="text-success" id="isFamilyId"></span></p>
						<p class="m_0">PARTY POSITION : <span id="positionId"></span></p>
						<p class="m_0">PUBLIC REPRESENTATIVE : <span id="representativeId"></span></p>
						<p class="m_0" id="nominatedDivId" style="display:none;">Nominated Post : &nbsp;&nbsp;<span id="appliedCountId" style="cursor:pointer;" onclick="getMoreApplicationDetails()"></span><span id="nominatedPstStatusId"></span></p>
						<div id="volunteerNullId">
						<p class="m_0">Volunteers Count : <a class="pointer"><span id="volunteerId"></span></a></p>
						</div>
						<p class="m_0" id="debateMainDivId" style="display:none" >TV DEBATOR : DEBATES (<a class="pointer"><span id="debateCountId"></span></a>)</p>
					</div>
				</div>
				<div class="panel panel-default arrowChangeIcon">
					<div class="panel-heading">
						<h4 class="panel-title pointer " id="addressHeaderId">
							<i class="glyphicon glyphicon-map-marker"></i> ADDRESS
							<span class="pull-right" id="addressHideId" style="display:none;">
								<i class="glyphicon glyphicon-chevron-up"></i>
							</span>
							<span class="pull-right" id="addressShowId">
								<i class="glyphicon glyphicon-chevron-down"></i>
							</span>
						</h4>
					</div>
					<div class="panel-body" id="addressBodyId">
						<p class="m_0">H NO :<span id="houseNoId"></span></p>
						<p class="m_0">PANCHAYAT/WARD : <span id="panchayatId"></span></p>
						<p class="m_0">MANDAL/MUNICIPALITY : <span id="mandalId"></span></p>
						<p class="m_0">CONSTITUENCY : <span id="constituencyId"></span></p>
						<p class="m_0">DISTRICT : <span id="districtNoId"></span></p>
						<p class="m_0">STATE : <span id="stateNoId"></span></p>
					</div>
				</div>
				<div class="panel panel-default" id="participatedDivId" style="display:none">
                	<div class="panel-heading" id="participatedConstHeaderId">
                    	<h4 class="panel-title text-bold pointer"><i class="glyphicon glyphicon-file"></i> PARTICIPATED CONSTITUENCY <span class="pull-right" id="participatedConstHideId" style="display:none;"><i class="glyphicon glyphicon-chevron-up"></i></span>
						<span class="pull-right" id="participatedConstShowId"><i class="glyphicon glyphicon-chevron-down"></i></span></h4>
                    </div>
					<div class="panel-body" id="participatedConstBodyId">
                    	<h5 class="m_0">Location : <span id="participatedConstId" class="text-bold"></span></h5>
                     </div> 
                </div>
				<div class="panel panel-default" id="candidateapptID" style="display:none">
					<div class="panel-heading" id="candidateAppointmentId">
                    	<h4 class="panel-title text-bold pointer"><i class="fa fa-calendar-plus-o"></i> CANDIDATE APPOINTMENT <span class="pull-right" id="candidateAppointmentHideId" style="display:none;"><i class="glyphicon glyphicon-chevron-up"></i></span>
						<span class="pull-right" id="candidateAppointmentShowId"><i class="glyphicon glyphicon-chevron-down"></i></span>
						<span class="pull-right"><span class="count-style" id="totalAptId">0</span></span>
						</h4>
					</div>
					<div class="panel-body" id="candidateAppointmentBodyId">
						<img id="candidateAppointmentImg" src="images/icons/loading.gif" style="width:45px;height:45px;margin-left:45%">
						<div id="buildCandidateAppointmentUser"></div>
                    </div> 
				</div>
            	<div class="panel panel-default">
                	<div class="panel-heading" role="tab" id="headingTwo12">
						<a class="accordion-toggle" role="button" data-toggle="collapse" data-parent="#accordion12" href="#comaplaintCountInmgMainDivid" aria-expanded="false" aria-controls="comaplaintCountInmgMainDivid">	
							<h4 class="panel-title text-bold pointer">
								<i class="glyphicon glyphicon-user"></i>
									CANDIDATE GRIEVANCE DETAILS
								<span class="pull-right"><span class="count-style" id="candidateTotalComplaintsDiv">0</span></span>
							</h4>
						</a>
					</div>
					<!-- <center><strong>Grievance Details Not Available.</strong></center> -->
                    <div class="panel-body pad_0" id="comaplaintCountInmgMainDivid">
						<img id="complaintCountImg" src="images/icons/loading.gif" style="width:45px;height:45px;margin-left:45%">
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
				<!--swadhin-->
				<div class="panel panel-default">
				<div class="panel-heading" id="cadreBenefitsHeadingId">
					<h4 class="panel-title text-bold pointer">
						<img src="images/User_icon_BLACK-01.png"> 
						CANDIDATE BENEFITS <span class="pull-right" id="cadreBenefitsHideId" style="display:none;"><i class="glyphicon glyphicon-chevron-up"></i></span>
						<span class="pull-right" id="cadreBenefitsShowId"><i class="glyphicon glyphicon-chevron-down"></i></span>
						<span class="pull-right"><span class="count-style" id="candidateBenifitsCountsId">0</span></span>
					</h4>
				</div>
				<div class="panel-body pad_0" id="cadreBenefitsBodyId">
					<ul class="benefits-block">
						<center><img class="dataLoadingsImgForTabSection" src="images/icons/loading.gif" style="width: 50px; height: 50px;display:none;"></center>
						<li id="candidaterequestedDiv" style="display:none;">
									 FINANCIAL REQUESTED <span class="pull-right" id="candidateRequestAmount">0</span>
						</li>
						<li id="candidateapprovedDiv">APPROVED FINANCIAL SUPPORT <span class="pull-right" id="candidateApprovedAmount">0</span></li>
						<li id="candidatedeathDiv">DEATH INSURANCE REQUESTS <span class="pull-right" >
							<ul  class="hoverclassli">
								<li id="candidateDeathInsurance">0
									
								</li>
							</ul>
							</span></li>
						<li id="candidatehospitalDiv">HOSPITALIZATION INSURANCE REQUESTS <span class="pull-right" >
							<ul  class="hoverclassli">
								<li id="candidateHospitalizationInsurance">0
									
								</li>
							</ul>
							
							</span>
						</li>
						<li id="candidateEducationBenefitDiv">
							<span id="cadreIdSpanForEducationBenefit"></span>	
						</li>
					</ul>
				</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading" id="familyMemberHeadingId">
						<h4 class="panel-title text-bold pointer">
							<img src="dist/img/family-icon.png">&nbsp;FAMILY MEMBERS<span class="pull-right" id="familyMemberHideId" style="display:none;"><i class="glyphicon glyphicon-chevron-up"></i></span><span class="pull-right" id="familyMemberShowId"><i class="glyphicon glyphicon-chevron-down"></i></span>
							<span class="pull-right"><span class="count-style" id="totalFamilymembersCount">0</span></span>
						</h4>
					</div>
					<div class="panel-body familyMemberCls" id="familyMemberBodyId">
					<center><img style="width: 25px; height: 25px;display:none;" src="images/icons/loading.gif" id="dataLoadingsImgForFamilyMembers"/></center>
						<div class="family-members" id="familyMembersDiv"></div>
					</div>
				</div>
				<div class="panel panel-default">
                	<div class="panel-heading" id="familyGrievanceDtlsHeaderId">
					  <h4 class="panel-title text-bold pointer"><img src="images/family_icon.png"> FAMILY GRIEVANCE DETAILS<span class="pull-right" id="fmlyGrvncDtlsSpanId"><i class="glyphicon glyphicon-chevron-down" ></i></span>
						 <span class="pull-right"><span class="count-style" id="totalFamilyComplaints">0</span></span>
						</h4>
					</div>
					 <div class="panel-body pad_0" id="familyGrievanceDtlsBodyId">					 
						<div  id="familyMemberDiv">
							<img id="familyMemberImg" src="images/icons/loading.gif" style="width:45px;height:45px;margin-left:45%">
						</div>
                   		<!--<h5>TOTAL FINANCE REQUESTS <span id="headingId" class="text-bold"></span></h5>
                    	<div id="donutchart2" class="display-style" style="height: 120px;float:left;width:90px;"></div>
                    <ul class="display-style pull-right piechart-list pad_0" id="financeSupportUL">
                        
                    </ul>-->
					
                    </div>
                </div>
				<!--SWADHIN-->
				<div class="panel panel-default">
				<div class="panel-heading" id="familyBenefitsHeadingId">
					<h4 class="panel-title text-bold pointer">
					<img src="images/family_icon.png">
						FAMILY BENEFITS<span class="pull-right" id="familyBenefitsHideId" style="display:none;"><i class="glyphicon glyphicon-chevron-up"></i></span>
						<span class="pull-right" id="familyBenefitsShowId"><i class="glyphicon glyphicon-chevron-down"></i></span>
						<span class="pull-right"><span class="count-style" id="familyBenifitsCountsId">0</span></span>
					</h4>
				</div>
				<div class="panel-body pad_0" id="familyBenefitsBodyId">
					<ul class="benefits-block">
					<center><img class="dataLoadingsImgForTabSection" src="images/icons/loading.gif" style="width: 50px; height: 50px;display:none"></center>
					<li id="familyrequestedDiv" style="display:none;">
								 FINANCIAL REQUESTED <span class="pull-right" id="familyRequestAmount">0</span>
					</li>
					<li id="familyapprovedDiv">APPROVED FINANCIAL SUPPORT <span class="pull-right" id="familyApprovedAmount">0</span></li>
					<li id="familydeathDiv">DEATH INSURANCE REQUESTS <span class="pull-right" >
						<ul  class="hoverclassli">
							<li id="familyDeathInsurance">0
							</li>
						</ul>
						</span></li>
					<li id="familyhospitalDiv">HOSPITALIZATION INSURANCE REQUESTS<span class="pull-right" >
						<ul  class="hoverclassli">
							<li id="familyHospitalizationInsurance">0
								
							</li>
						</ul>
						
						</span></li>
					<li id="familyEducationBenefitDiv">
						<span id="familyIdSpanForEducationBenefit"></span>	
						
					</li>
					</ul>
				</div>
				</div>
				<!--swadhin-->
				<div class="panel panel-default">
					<div class="panel-heading">
						<a class="accordion-toggle" role="button" data-toggle="collapse" data-parent="#accordion12" href="#referralGrievanceDetailsId" aria-expanded="false" aria-controls="referralGrievanceDetailsId">
							<h4 class="panel-title text-bold pointer"><img src="images/icon.png">&nbspREFERRAL GRIEVANCE DETAILS<span class="pull-right" id="referralGrvncDtlsSpanId"></span><span class="pull-right"><span class="count-style" id="refferelTotalCountId">0</span></span></h4>
						</a>
					</div> 
					<div class="panel-body pad_0" id="referralGrievanceDetailsId" >
						<img id="referralGrievanceLoadingImg" src="images/icons/loading.gif" style="width:45px;height:45px;margin-left:45%;display:none"/>
					</div>
				</div>
            	
				<!--SWADHIN-->
				
				<!--<div class="panel panel-default">
                	<div class="panel-heading" id="deathHospitalDivHeaderId">
                    	<h4 class="panel-title text-bold" style="cursor:pointer;"><i class="glyphicon glyphicon-flash"></i> DEATHS AND HOSPITALIZATION <span class="pull-right" id="deathHospitalDivHideId" style="display:none;"><i class="glyphicon glyphicon-chevron-up"></i></span>
						<span class="pull-right" id="deathHospitalDivShowId"><i class="glyphicon glyphicon-chevron-down"></i></span>
						</h4>
                    </div>
					<center><img id="dataLoadingsImgForDeathCount" src="images/icons/loading.gif" style="width: 50px; height: 50px;"></center>
					<div id="deathHospitalDivId">
					</div>
				</div>-->
				<div id="ntrTrustDivId"></div>
				<!--<div class="panel panel-default" id="debateMainDivId" style="display:none">
					<div class="panel-heading">
						<h4 class="panel-title text-bold"><img src="img/cadre_debate.png" style="width: 18px; height: 21px;"><span> TOTAL PARTICPATED DEBATES <span id="debateCountId" class="pull-right count-style pointer">0<span></span></h4>
					</div>
				</div>-->
				
				<!--<div class="panel panel-default">
                	<div class="panel-heading" id="trainingCampDetailsHeaderId">
                    	<h4 class="panel-title text-bold" style="cursor:pointer;"><i class="glyphicon glyphicon-flash"></i> TRAINING CAMP DETAILS <span class="pull-right" id="trainingCampDetailsHideId" style="display:none;"><i class="glyphicon glyphicon-chevron-up" ></i></span><span class="pull-right" id="trainingCampDetailsShowId"><i class="glyphicon glyphicon-chevron-down"></i></span></h4>
                    </div>
					<!--<center>Deaths And Hospitalization Details Not Available.</center> -->
					<!--<center><img id="dataLoadingsImgForTrainingCampParticipation" src="images/icons/loading.gif" style="width: 50px; height: 50px;"></center>
					<div id="trainingCampDetailsBodyId">
						<div id="trainingCampParticipationDivId">
						</div>
					</div>
                </div>-->
				<!--<div class="panel panel-default">
                	<div class="panel-heading" id="grievanceStatusDivId">
                    	<h4 class="panel-title text-bold" style="cursor:pointer;"><i class="glyphicon glyphicon-flash"></i> GRIEVANCE STATUS DETAILS <span class="pull-right" id="grievanceStatusHideId" style="display:none;"><i class="glyphicon glyphicon-chevron-up"></i></span>
						<span class="pull-right" id="grievanceStatusShowId"><i class="glyphicon glyphicon-chevron-down"></i></span>
						</h4>
                    </div>
					<!--<center>Deaths And Hospitalization Details Not Available.</center> -->
					<!--<center><img id="dataLoadingsImgForGrievanceStatusCount" src="images/icons/loading.gif" style="width: 50px; height: 50px;"></center>
					<div id="grievanceStatusMainDivId">
					</div>
				</div>-->
				<!--<div class="panel panel-default">
                	<div class="panel-heading" id="StatusCountsDivId">
                    	<h4 class="panel-title text-bold" style="cursor:pointer;"><i class="glyphicon glyphicon-flash"></i> GRIEVANCE STATUS COUNTS <span class="pull-right" id="statusCountsHideId" style="display:none;"><i class="glyphicon glyphicon-chevron-up"></i></span>
						<span class="pull-right" id="statusCountsShowId"><i class="glyphicon glyphicon-chevron-down"></i></span>
						</h4>
                    </div>
					<center><img id="dataLoadingsImgForStatusCount" src="images/icons/loading.gif" style="width: 50px; height: 50px;"></center>
					<div id="statusCountsMainDivId">
					</div>
				</div>-->
				
            </div>
            <div class="col-md-8">
            	<div class="panel panel-default">
                	<div class="panel-heading" id="cadrMmbrBthHeaderId">
                    	<h4 class="panel-title text-bold pointer"><i class="glyphicon glyphicon-sort-by-attributes" style="transform: rotate(270deg);"></i>&nbsp;&nbsp;&nbsp;CADRE MEMBER BOOTH PERFORMANCE 
							<span>
								<i style="cursor:pointer;" data-placement="top" data-toggle="tooltip" title="OWN CONSTITUENCY" class="glyphicon glyphicon-info-sign reasonClsn participatedClass"></i>
							</span>
							<span class="pull-right" id="cadrMmbrBthShowId"><i class="glyphicon glyphicon-chevron-up"></i>  
							</span><span class="pull-right" id="cadrMmbrBthHideId" style="display:none;">  
							<i class="glyphicon glyphicon-chevron-down"></i></span>
						</h4>
                    </div>
                    <div class="panel-body" id="cadrMmbrBthBodyId">
                    	<div class="panel-group electionPerformanceDiv" id="accordion" role="tablist" aria-multiselectable="true">
                          
                        </div>
                    </div>  
                </div>
                <div class="panel panel-default">
					<div class="panel-heading" id="cadreEnrolementParticepateStatsId">
						<h4 class="panel-title text-bold pointer"><i class="glyphicon glyphicon-stats"></i>&nbsp;&nbsp;&nbsp;CADRE ENROLMENT STATS 
							<span class="pull-right" id="cadreEnrolementParticepateStatsIdShowId"><i class="glyphicon glyphicon-chevron-up"></i>
							</span><span class="pull-right" id="cadreEnrolementParticepateStatsIdHideId" style="display:none;">
							<i class="glyphicon glyphicon-chevron-down"></i></span>
						</h4>
					</div>
					<div class="panel-body" id="cadreEnrolementParticepateStatBodyId">
						<div class="panel panel-default">
								<div class="panel-heading bg_white" id="cadreEnrolmentStatsHeaderId1">
									<h4 class="panel-title text-bold pointer">2016 CADRE ENROLMENT DETAILS
										<span>
											<i id="cadreEnrolementParticepateStatusId1" style="cursor:pointer;" data-placement="top" data-toggle="tooltip" class="glyphicon glyphicon-info-sign reasonCls participatedClass"></i>
										</span>
										<span class="pull-right" id="cadreEnrolmentStatsShowId1"><i class="glyphicon glyphicon-chevron-up"></i>
										</span><span class="pull-right" id="cadreEnrolmentStatsHideId1" style="display:none;">
										<i class="glyphicon glyphicon-chevron-down"></i></span>
									</h4>
								</div>
								<div class="panel-body" id="cadreEnrolmentStatsBodyId1" style="padding:0px 15px;">
									<div class="row table-responsive" id="memberShipCountDiv1"><!--id="memberShipCountDiv"-->
									</div>
								</div>
							</div>
							<div class="panel panel-default">
								<div class="panel-heading bg_white" id="cadreEnrolmentStatsHeaderId">
									<h4 class="panel-title text-bold pointer">2014 CADRE ENROLMENT DETAILS
										<span>
											<i id="cadreEnrolementParticepateStatusId" style="cursor:pointer;" data-placement="top" data-toggle="tooltip" class="glyphicon glyphicon-info-sign reasonCls participatedClass"></i>
										</span>
										<span class="pull-right" id="cadreEnrolmentStatsShowId"><i class="glyphicon glyphicon-chevron-up"></i>
										</span><span class="pull-right" id="cadreEnrolmentStatsHideId" style="display:none;">
										<i class="glyphicon glyphicon-chevron-down"></i></span>
									</h4>
								</div>
								<div class="panel-body" id="cadreEnrolmentStatsBodyId" style="padding:0px 15px;">
									<div class="row table-responsive" id="memberShipCountDiv"><!--id="memberShipCountDiv"-->
									</div>
								</div>
							</div>
							<div class="pull-right">
								<span style="cursor:pointer;display:none;" id="cdrRegDtlsId">Detailed</span>     
							</div>
						</div>
					</div>
                <!-- Tour Block Start -->
				<div class="panel panel-default" id="cadreTourBlockDivId" style="display:none;">
                	<div class="panel-heading" id="toursHeaderId" style="padding:0px 15px">
						<div class="row">
							<div class="col-md-7 col-xs-12 col-sm-8" style="margin-top:10px">
								<h4 class="panel-title text-bold pointer"><i class="glyphicon glyphicon-plane"></i>&nbsp;&nbsp;&nbsp;TOURS 
								</h4>
							</div>
							<div class="col-md-5 col-xs-12 col-sm-4">
								<span id="toursProfileShowId">
									<i class="glyphicon glyphicon-chevron-up pull-right"  style="margin-top:10px;margin-left:5px"></i>
								</span>
								<span class="pull-right" id="toursProfileHideId" style="display:none;">
									<i class="glyphicon glyphicon-chevron-down" style="margin-top:10px;margin-left:5px;cursor:pointer;"></i>
								</span>
								<div style="display:inline-block;width:200px" class="pull-right">
									<div class="input-group pull-right">
										<span class="input-group-addon"><i class="glyphicon glyphicon-calendar" style="cursor:pointer;"></i></span>
										<input class="form-control" id="toursDatePicker" style="width:170px; padding-left:0px; padding-right:0px;" type="text">
									</div>
								</div>
							</div>
						</div>
                    </div>
					<div class="panel-body" id="tourBodyId">
						<div class="row">
							<div class="col-md-12 col-xs-12 col-sm-12">
								<div class="cadre-election" >
									<div id="cadreToursDetailsDiv"></div>
									<!--<div id="tourssErrMsgId"></div>-->
								</div>
							</div>
						</div>
                    </div>					
                </div>
			<!-- Tour Block End -->
				 <div class="panel panel-default" id="" style="">
                	<div class="panel-heading" id="alertHeaderId" style="padding:0px 15px">
						<div class="row">
							<div class="col-md-7 col-xs-12 col-sm-8" style="margin-top:10px">
								<h4 class="panel-title text-bold pointer"><i class="glyphicon glyphicon-folder-open"></i>&nbsp;&nbsp;&nbsp;ALERTS</h4>
							</div>
							<div class="col-md-5 col-xs-12 col-sm-4">
								<span id="alertProfileShowId">
									<i class="glyphicon glyphicon-chevron-up pull-right"  style="margin-top:10px;margin-left:5px"></i>
								</span>
								<span class="pull-right" id="alertProfileHideId" style="display:none;">
									<i class="glyphicon glyphicon-chevron-down" style="margin-top:10px;margin-left:5px"></i>
								</span>
								<div style="display:inline-block;width:200px" class="pull-right">
									<div class="input-group pull-right">
										<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
										<input class="form-control" id="alertsDatePicker" style="width:170px; padding-left:0px; padding-right:0px;" type="text">
									</div>
								</div>
							</div>
									
								
							<!--<div class="col-md-4 col-xs-12 col-sm-4" style="margin-top: 10px;">
								<label class="radio-inline"><input type="radio" name="radioBtn" value="Assigned" checked/>Assigned</label>
								<label class="radio-inline"><input type="radio" name="radioBtn" value="Involved"/>Involved</label>
							</div>
							<div class="col-md-3 col-xs-12 col-sm-3" style="padding-left:0px">
								<label style="font-size:13px;">Alert Types :</label> 
								<select class="form-control" id="typeId" style="font-size:13px;display:inline-block; width: 90px">
									<option value="0">All</option>
									<option value="1">Party</option>
									<option value="2">Govt</option>
									<option value="3">Others</option>
								</select>
							</div>
							<div class="col-md-5 col-xs-12 col-sm-5" style="padding-left: 0px">
								<div style="display:inline-block">
									<div class="input-group pull-right" style="">
										<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
										<input class="form-control" id="alertsDatePicker" style="width:170px; padding-left:0px; padding-right:0px;" type="text">
									</div>
								</div>
								<button class="btn btn-success btn-sm" onclick="getCadreAlertDetails();" style="margin-top: -25px"> SUBMIT </button>-->
								
						</div>
                    </div>
					
                    <div class="panel-body" id="alertBodyId">
						<div class="row">
							<div class="col-md-6 col-xs-12 col-sm-6">
								<label class=""> Alert Type : </label>
								<label class="radio-inline"><input type="radio" name="alertRadioBtn" value="0" class="alerttsCls" onclick="getCadreAlertDetails();" checked/>All</label>
								<label class="radio-inline"><input type="radio" name="alertRadioBtn" value="1" class="alerttsCls" onclick="getCadreAlertDetails();" />Party</label>
								<label class="radio-inline"><input type="radio" name="alertRadioBtn" value="2" class="alerttsCls" onclick="getCadreAlertDetails();" />Govt</label>
								<label class="radio-inline"><input type="radio" name="alertRadioBtn" value="3" class="alerttsCls" onclick="getCadreAlertDetails();" />Others</label>
							</div>
							<div class="col-md-6 col-xs-12 col-sm-6">
								<!--<button class="btn btn-success btn-xs pull-right" style="margin-left:20px" onclick="getCadreAlertDetails();" > SUBMIT </button>-->
								<label class="radio-inline pull-right"><input type="radio" name="radioBtn" value="Involved" onclick="getCadreAlertDetails();"/>Involved</label>
								<label class="radio-inline pull-right" style="margin-right: 31px;"><input type="radio" name="radioBtn" value="Assigned" onclick="getCadreAlertDetails();" checked />Assigned</label>
								<!-- label class="radio-inline pull-right"><input type="radio" name="radioBtn" value="Assigned" />All</label>-->
								<!--<label style="font-size:13px;">Alert Types :</label> 
								<select class="form-control" id="typeId">
									<option value="1">Party</option>
									<option value="2">Govt</option>
									<option value="3">Others</option>
								</select>-->
							</div>
							
							<!--<div class="col-md-1 col-xs-12 col-sm-1" style="padding-left:0px">
								<button class="btn btn-success btn-sm" onclick="getCadreAlertDetails();" style="margin-top: 25px;margin-left:-10px;"> SUBMIT </button>
							</div>-->
						</div>
						<div class="row">
							<div class="col-md-12 col-xs-12 col-sm-12">
								<div class="cadre-election" >
									<div id="alertDetailsDiv"></div>
									<!--<div id="alertsOverViewTAb"> </div>-->
									<div id="alertErrMsgId"></div>
								</div>
							</div>
						</div>
                    </div>
                </div>
						
				
				
					<!--<div class="panel panel-default">
					<div class="panel-heading" id="ivrSummaryHeaderId">
                    	<h4 class="panel-title text-bold" style="cursor:pointer;"><i class="glyphicon glyphicon-folder-open"></i>&nbsp;&nbsp;&nbsp;IVR SUMMARY<span class="pull-right" id="ivrSummaryHideId" style="display:none;"><i class="glyphicon glyphicon-chevron-up"></i></span><span class="pull-right" id="ivrSummaryShowId"><i class="glyphicon glyphicon-chevron-down"></i></span></h4>
                    </div>		
					<div class="panel-body" id="ivrSummaryBodyId">
						<div id="ivrTypeDetailsDivId"></div>
					 </div>
				 </div>-->
				<!-- IVR SUMMARY  END ----> 
                <div class="panel panel-default">
                	<div class="panel-heading" id="cadreActivitiesHeaderId">
                    	<h4 class="panel-title text-bold pointer"><i class="glyphicon glyphicon-record"></i>&nbsp;&nbsp;&nbsp;&nbsp;CADRE PARTICIPATION ACTIVITIES
							<!-- <span>
								<i style="cursor:pointer;" data-placement="top" data-toggle="tooltip" title="OWN CONSTITUENCY" class="glyphicon glyphicon-info-sign reasonCls"></i>
							</span> --> 
							<span class="pull-right" id="cadreActivitiesHideId" style="display:none;"><i class="glyphicon glyphicon-chevron-up"></i>
							</span><span class="pull-right" id="cadreActivitiesShowId">
							<i class="glyphicon glyphicon-chevron-down"></i></span>
						</h4>
                    </div>
                    <div class="panel-body" id="cadreActivitiesBodyId">
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
							 <div class="col-md-4 col-xs-12" id="partyMeetingDescDiv"></div>
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
								  <div class="panel panel-default" id="activityMainDivId">
									<div class="panel-heading" role="tab" id="headingFour">
									  <a role="button" class="collapsed accordion-toggle" data-toggle="collapse" data-parent="#accordion2323" href="#collapseFour" aria-expanded="false" aria-controls="collapseFour">
										<h4 class="panel-title">
										  <span> ACTIVITY CONDUCTED DETAILS</span>
										</h4>
									   </a>
									</div>
									<div id="collapseFour" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingFour">
									  <div class="panel-body">
										<div id="activityTableDivId" class="table-responsive"></div>
										<div id="activityAttendedTableDivId" class="table-responsive"></div>
										<div class="table-responsive" id="activityAttented">
										</div>
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
									  <input class="btn btn-sm btn-primary surveyQuestionsCls pull-right" value="Survey feed back" data-toggle="modal" data-target=".surveyQuestionDetails" type="button" style="margin-bottom:7px;">
										<table class="table table-bordered">
											<thead style="background:#f2f2f2">
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
							
						    </div>
							
                        </div>
                    </div>
					
				
                <div class="panel panel-default" id="electionProfileMainDivId">
                	<div class="panel-heading" id="cadreElectionProfileHeaderId">
                    	<h4 class="panel-title text-bold pointer"><i class="glyphicon glyphicon-folder-open"></i>&nbsp;&nbsp;&nbsp;CANDIDATE ELECTION PROFILE 
							<!--<span>
								<i style="cursor:pointer;" data-placement="top" data-toggle="tooltip" title="Own Constituency" class="glyphicon glyphicon-info-sign reasonCls"></i>
							</span>-->
							<span class="pull-right" id="cadreElectionProfileShowId"><i class="glyphicon glyphicon-chevron-up"></i>
							</span><span class="pull-right" id="cadreElectionProfileHideId" style="display:none;">
							<i class="glyphicon glyphicon-chevron-down"></i></span>
						</h4>
                    </div>
                    <div class="panel-body" id="cadreElectionProfileBodyId">
                    	<div class="cadre-election" >
						<div id="electionProfileDivId"> </div>
                        	
                        </div>
                    </div>
                </div>
				
				<!-- Meetings Start -->
                <div class="panel panel-default" id="commitMeetingDiv" style="display:none;" >
                	<div class="panel-heading  pointer" id="committeeMetingsHeaderId" style="padding-top:0px;padding-bottom:0px;">
						<div class="row">
							<div class="col-md-4" style="margin-top:6px">
								<h4 class="panel-title text-bold"><img src="dist/img/photo.png" > COMMITTEE MEETINGS</h4>
							</div>
							<span style="display:none;"> 
								<i style="cursor:pointer;top:8px;" data-placement="top" data-toggle="tooltip" title="OWN CONSTITUENCY" class="glyphicon glyphicon-info-sign reasonCls participatedClass"></i>
							</span>
							<div class="col-md-offset-3 col-md-5">
								<div class="input-group pull-right">
									<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i>	</span>
									<input type="text" class="form-control" id="meetingDatePicker" style="width: 170px; padding-left: 0px; padding-right: 0px;">
									<span class="pull-right" id="committeeMetingsShowId" style="display:none;"><i style="padding-top: 9px;" class="glyphicon glyphicon-chevron-up pointer"></i></span><span class="pull-right" id="committeeMetingsHideId"><i class="glyphicon glyphicon-chevron-down pointer" style="padding-top: 9px;"></i></span>
								</div>
							</div>
						</div>
                    	
							
						
                    </div>
                    <div class="panel-body pad_5" id="committeeMetingsBodyId">
                    	<div id="committeMeetingDivId">
                        </div>
						<div id="paginationId" class="col-md-4 m_top10" style="margin-left:20%;"></div>
                    </div>
                </div>
                 <!-- Meetings End -->
				 
				 <div class="panel panel-default">
                	<div class="panel-heading" id="deathHospitalDivHeaderId">
                    	<h4 class="panel-title text-bold pointer"><i class="fa fa fa-hospital-o"></i> DEATHS AND HOSPITALIZATION INSURANCE DETAILS 
						<span>
							<i id="deathAndHospitalParticepationId" style="cursor:pointer;" data-placement="top" data-toggle="tooltip" title="" class="glyphicon glyphicon-info-sign reasonCls participatedClass"></i>
						</span>
						<span class="pull-right" id="deathHospitalDivHideId" style="display:none;"><i class="glyphicon glyphicon-chevron-up"></i></span>
						<span class="pull-right" id="deathHospitalDivShowId"><i class="glyphicon glyphicon-chevron-down"></i></span>
						</h4>
                    </div>
					<center><img id="dataLoadingsImgForDeathCount" src="images/icons/loading.gif" style="width: 50px; height: 50px;"></center>
					
					<div id="deathHospitalDivId">
					</div>
				</div>
				
				<div class="panel panel-default">
                	<div class="panel-heading" id="StatusCountsDivId">
                    	<h4 class="panel-title text-bold pointer"><!--<i class="glyphicon glyphicon-flash"></i>--><img src="images/family_icon.png"> GRIEVANCE REQUESTS STATUS DETAILS 
							<span>
								<i id="grievanceRequestParticepationId" style="cursor:pointer;" data-placement="top" data-toggle="tooltip" title="" class="glyphicon glyphicon-info-sign reasonCls participatedClass"></i>
							</span>
							<span class="pull-right" id="statusCountsHideId" style="display:none;"><i class="glyphicon glyphicon-chevron-up"></i></span>
							<span class="pull-right" id="statusCountsShowId"><i class="glyphicon glyphicon-chevron-down"></i></span>
						</h4>
                    </div>
					<center><img id="dataLoadingsImgForStatusCount" src="images/icons/loading.gif" style="width: 50px; height: 50px;"></center>
					<div id="statusCountsMainDivId" class="table-responsive">
					</div>
				</div>  
				<div class="panel panel-default">  
					<div class="panel-heading" id="grievencyBenefitHeaderId">
						<h4 class="panel-title text-bold" style="cursor:pointer;"><!--<i class="glyphicon glyphicon-flash"></i>--><img src="images/family_icon.png"> GRIEVANCE BENIFIT REQUESTS 
							<span>  
								<i id="grievanceBenefitParticepationId" style="cursor:pointer;" data-placement="top" data-toggle="tooltip" title="" class="glyphicon glyphicon-info-sign reasonCls participatedClass"></i>
							</span>
							<span class="pull-right" id="grievencyBenefitHideId" style="display:none;"><i class="glyphicon glyphicon-chevron-up"  ></i></span>
							<span class="pull-right" id="grievencyBenefitShowId"><i class="glyphicon glyphicon-chevron-down"></i></span>
						</h4>
					</div>
					<div class="panel-body" id="grievencyBenefitBodyId">
						<center><img id="dataLoadingsImgForGrievanceRequests" src="images/icons/loading.gif" style="width: 50px; height: 50px;"></center>
						<div id="grievanceRequestsId"></div>
					</div>
				</div>
				
				<div class="panel panel-default" id="trainingCampdetailsDiv">
                	<div class="panel-heading" id="trainingCampDetailsHeaderId">
                    	<h4 class="panel-title text-bold pointer"><img src="images/training.png" style="width:18px;height:17px;"/> TRAINING CAMP DETAILS <span class="pull-right" id="trainingCampDetailsHideId" style="display:none;"><i class="glyphicon glyphicon-chevron-up" ></i></span><span class="pull-right" id="trainingCampDetailsShowId"><i class="glyphicon glyphicon-chevron-down"></i></span>
                    	<span>
							<i style="cursor:pointer;" data-placement="top" data-toggle="tooltip" title="OWN CONSTITUENCY" class="glyphicon glyphicon-info-sign reasonCls participatedClass"></i>
						</span>
                    	</h4>
                    </div>
                    
					<!--<center>Deaths And Hospitalization Details Not Available.</center> -->
					<center><img id="dataLoadingsImgForTrainingCampParticipation" src="images/icons/loading.gif" style="width: 50px; height: 50px;"></center>
					<div id="trainingCampDetailsBodyId">
						<div id="trainingCampParticipationDivId" class="table-responsive">
						</div>
					</div>
                </div>
				
                <div class="panel panel-default">
                	<div class="panel-heading">
					<a id="" class="showbtnCls" title="Click here to Show Committee Details" href="javascript:{newsHideAndShow('newsMainDivId');}">
                    	<h4 class="panel-title text-bold" style=""><i class="glyphicon glyphicon-list-alt"></i>&nbsp;&nbsp;&nbsp;NEWS<span class="pull-right"><i class="glyphicon glyphicon-chevron-down"></i></span>
						 <i class="pull-right glyphicon glyphicon-triangle-top "></i>							
						</h4>
						</a>
						<a id="" class="newsMainDivId" style="display:none;" title="Click here to Hide Committee Details" href="javascript:{newsHideAndShow('newsMainDivId');}">
						<h4 class="panel-title text-bold" style=""><i class="glyphicon glyphicon-list-alt"></i>&nbsp;&nbsp;&nbsp;NEWS<span class="pull-right"><i class="glyphicon glyphicon-chevron-up"></i></span>
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
                    	
						
					<center><img style="width: 100px; height: 100px;margin-top:50px" src="images/icons/loading.gif" id="dataLoadingsImgForNewsId1"/></center>
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
                    	<h4 class="panel-title text-bold">
                        	<i class="glyphicon glyphicon-briefcase"></i>&nbsp;&nbsp;&nbsp;COMMITTEES<span class="pull-right"><i class="glyphicon glyphicon-chevron-down"></i></span> 
							 <i class="pull-right glyphicon glyphicon-triangle-top "></i>							
                        </h4>
						</a>
						<a href="javascript:{hideAndShowCommittee(2);}" title="Click here to Hide Committee Details" style="display:none;" id="hideId">
							<h4 class="panel-title text-bold">
                        	<i class="glyphicon glyphicon-briefcase"></i>&nbsp;&nbsp;&nbsp;COMMITTEES<span class="pull-right"><i class="glyphicon glyphicon-chevron-up"></i></span> 
							 
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
							<div class="col-md-2  col-xs-12 col-sm-2 pull-right">
								<select class="form-control" id="tdpCommitteeYearId" onchange="getLocationwiseCommitteesCount();"></select>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12 col-xs-12 col-sm-12 m_top10">
								<div class="table m_0-responsive m_top10 table-responsive" id="committeesCountDiv"></div>
							</div>
						</div>
                        
						
                    </div>
                </div>
            </div>
        </div>
        <!-- Party Meetings -->
		<div class="row">
			<div class="col-md-12 col-xs-12">
				<div class="panel panel-default">
					<div class="panel-heading partyMeetingsCollapse">
						<h4 class="panel-title text-bold pointer"><i class="fa fa-user-secret "></i>&nbsp;&nbsp;&nbsp;COMMITTEE MEETINGS
							<span class="pull-right"><i class="glyphicon glyphicon-chevron-down"></i></span>
						</h4>
					</div>
					<div class="panel-body partyMeetingsCollapseBody" style="display:none">
						<div class="row">
							<div class="col-md-9 col-xs-12" style="font-size: 13px;">
								<label class="radio-inline">
									<input type="radio" name="partyMetingsLocation" class="partyMeetingsLocCls" value="village">Own Village/Ward
								</label>
								<label class="radio-inline">
									<input type="radio" name="partyMetingsLocation" class="partyMeetingsLocCls" value="mandal">Own Mandal/Muncipality
								</label>
								<label class="radio-inline">
									<input type="radio" name="partyMetingsLocation" class="partyMeetingsLocCls" value="assemblyConstituency">Own Assembly Constituency
								</label>
								<label class="radio-inline">
									<input type="radio" name="partyMetingsLocation" class="partyMeetingsLocCls" value="parliamentConstituency">Own Parliament Constituency
								</label>
								<label class="radio-inline">
									<input type="radio" name="partyMetingsLocation" class="partyMeetingsLocCls" value="district" checked>Own District
								</label>
							</div>
							<div class="col-md-3 col-xs-12">
								<div class="input-group">
									<input type="text" class="form-control datePartyMeetings" id="partyMeetingDateId">
									<span class="input-group-addon">
										<i class="glyphicon glyphicon-calendar"></i>
									</span>
								</div>
							</div>
						</div>
						
						<div id="partyMeetingsLocWiseDiv">
						</div>
						<div id="noteId" style="text-align:center;"></div>  
						
					</div>
				</div>
			</div>
		</div>
		<!-- Party Meetings End-->
		<div class="row">
        	<div class="col-md-12 col-xs-12" >
            	<!--<div class="panel panel-default">
                	<div class="panel-heading" id="casteStatusId" >
						<h4 class="panel-title text-bold" style="cusor:pointer">
							&nbsp;&nbsp;&nbsp;CASTE DETAILS
							<i  onclick="getUpdatedCastePartyInfo()" class="" style="margin-left:5px"></i>
							<span class="pull-right" id="casteStatusHideId" style="display:none;"><i class="glyphicon glyphicon-chevron-up"></i></span>
							<span class="pull-right" id="casteStatusShowId"><i class="glyphicon glyphicon-chevron-down"></i></span>								 
						</h4>
                    </div>
					<div class="panel-body" id="casteBodyDiv">
					<div class="row">
						<div class="col-md-12 col-xs-12 col-sm-12">
						<div>
                        	<label class="radio-inline casteUrbanClass">
                            	<input type="radio" name="casteLocation" class="casteLocCls" value="panchayat">Village/Ward
                            </label>
                            <label class="radio-inline urbanClass">
                            	<input type="radio" name="casteLocation" class="casteLocCls" value="mandal">Mandal/Town/Division
                            </label>
                            <label class="radio-inline">
                            	<input type="radio" name="casteLocation" class="casteLocCls" value="assemblyConstituency" checked>Assembly Constituency
                            </label>
                            <label class="radio-inline">
                            	<input type="radio" name="casteLocation" class="casteLocCls" value="parliamentConstituency">Parliament Constituency
                            </label>
                            <label class="radio-inline">
                            	<input type="radio" name="casteLocation" class="casteLocCls" value="district" >District
                            </label>
                        </div>
							<div id="casteDiv" class="widget blue whitegloss" style="display:block;width: 96%;color:#000;position:relative;margin:auto;">
								<span id="castewiseAjaxDiv" style="display:block; position:absolute;top:10px;right:20px;"><img alt="Processing Image" src="./images/icons/search.gif"></span>
							<div id='LocalCastDiv'>
								<div>
									<div class="row"  style="border:1px solid #DDD;margin-top:10px;" id="castInfoDiv">
										<div class="col-md-6 col-xs-12 col-sm-6" style="margin-top:12px;">
											<div class="row" style="margin-top:25px;">
												<div class="col-md-6 col-xs-12 col-sm-6">
													<ul id='localCastStatsTabContent_header' class="casteWiseUl" ></ul>
												</div>
												<div class="col-md-6 col-xs-12 col-sm-6">
													<div id="localCastDetailsDiv" ></div>
												</div>
											</div>
										</div>
										<div class="col-md-6 col-xs-12 col-sm-6" style="border-left:1px solid #CCC;">
											<div id="localCastChatDiv"  style="position:inherit;max-width:450px;"></div>
										</div>
									</div>
									<div id="castContainerChart">
										<div id="castContainerChartInner">
											<!--<div id="rangeSliderDiv" style="width:500px;margin-left:auto;margin-right:auto;border:1px solid #ccc;padding:5px 20px;margin-top:50px;" >
													<h5 style="text-align:center;">Drag Slider for Building Chart Based on Voters Caste Percentage </h5>
													<div id="slider" class="ui-slider ui-slider-horizontal ui-widget ui-widget-content ui-corner-all" aria-disabled="false"><a href="#" class="ui-slider-handle ui-state-default ui-corner-all" style="left: 0%;"></a>
													</div>
													<p style="padding-bottom:2px;">
														<input type="text" id="amount" readonly style="border: 0; color: #f6931f; font-weight: bold;background-color:#ffffff;" />
													</p>
											</div>-->
											<!--<div id="container" style="min-width: 400px; height: 400px; margin: 0 auto;"></div>-->
									<!--	</div>
									</div>
									<div id="partyWiseDetailsHeadingDiv" style="clear:both;" class="localCastDetailsHeadingDiv"></div>
									<div id='partyWise_header'></div>
									<div class="partyWiseDetailsMainDiv">
										<table style="width:100%;"><tr>
											<td><div id="partyWiseDetailsDiv"></div></td>
												<td><div id="partyWiseChatDiv"></div></td></tr></table></div>
									<div id='localCastStatsTabContent_body' class="yui-skin-sam yui-dt-sortable" style="margin-top:10px;margin-bottom:20px;"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>-->
	</div>
 </div>
		<!--<div class="col-md-12 m_top10 pad_10 block" id="surveyDetailsMainDivId">
				<h4 style="border-bottom:1px solid #999">Survey Details</h4>
					
		</div>-->
		<c:if test="${not fn:contains(sessionScope.USER.entitlements, 'CADRE_SEARCH_ONLY' )}">
		 <div class="row">
        	<div class="col-md-12 col-xs-12">
            	<div class="panel panel-default">
                	<div class="panel-heading">
					<a href="javascript:{hideAndShowSurvey(1);}" title="Click here to Show Committee Details" id="surveyshowId"> 
                    	<h4 class="panel-title text-bold"><i class="glyphicon glyphicon-stats"></i>&nbsp;&nbsp;&nbsp;SURVEY DETAILS<span class="pull-right"><i class="glyphicon glyphicon-chevron-down"></i></span>
							<i class="pull-right glyphicon glyphicon-triangle-top "></i>
						</h4>
						</a>
							<a href="javascript:{hideAndShowSurvey(2);}" title="Click here to Hide Committee Details" style="display:none;" id="surveyhideId">
						<h4 class="panel-title text-bold"><i class="glyphicon glyphicon-stats"></i>&nbsp;&nbsp;&nbsp;SURVEY DETAILS<span class="pull-right"><i class="glyphicon glyphicon-chevron-up"></i></span>
							 <i class="pull-right glyphicon glyphicon-triangle-bottom "></i>
						</h4>
						</a>
                    </div>
                    <div class="panel-body" id="surveyDetailsId" style="display:none;">
						<div>

						  <!-- Nav tabs -->
						  <ul class="nav nav-tabs" role="tablist">
							<li role="presentation" class="active"><a href="#tabsurvey" aria-controls="tabsurvey" role="tab" data-toggle="tab">TAB SURVEY</a></li>
							<li role="presentation"><a href="#ivrsurvey" aria-controls="ivrsurvey" role="tab" data-toggle="tab" onclick="getSurveysOnCandidateCount(); getCandidateParticipatedSurveyCnt();getIVRSurveysOnCandidateAreaCount();">IVR SURVEY</a></li>
						  </ul>

						  <!-- Tab panes -->
						  <div class="tab-content">
							<div role="tabpanel" class="tab-pane active" id="tabsurvey">
								<div class="surveyDetailssCls">	</div>
								<div class="surveyDetailsCls">	</div>
								<center><img id="surveyDataLoadoing" src="images/icons/survey-details.gif" style="width:250px;height:200px;display:none;"/></center>
								<div id="surveyDetailsMainDivId" class=""></div>
							</div>
							<div role="tabpanel" class="tab-pane" id="ivrsurvey">
								<div id="ivrSurveysMainDivId"></div>
								<!--<div class="ivrcandtSurvysCntId"></div>-->
								<div id="ivrSurvysCandtCntId">
								<ul role="tablist" class="nav nav-tabs tab-list display-style" >
							
								<li style="padding:10px 15px;" onclick="getTypeWiseIvrDetailsOFCadre();">CANDIDATE PARTICIPATED SURVEYS&nbsp;&nbsp;&nbsp;&nbsp;<span id="IvrcandiParticipatedId" >0</span></li>
								<li style="padding:10px 15px;" onclick="getIVRSurveysOnCandidateDetails();">SURVEYS ON CANDIDATE &nbsp;&nbsp;&nbsp;&nbsp;<span id="IvrSurveyOnCandiId" >0</span></li>
								<li style="padding:10px 15px;" onclick="getIVRSurveysOnCandidateAreaDetails();">SURVEYS IN CANDIDATE AREA&nbsp;&nbsp;&nbsp;&nbsp;<span id="IvrcandiAreaId" >0</span></li>
								</ul>
								</div>
								<div id="ivrTypeDetailsDivId"></div>
								<div id="ivrDetailsBodyId"></div>
								<div class="ivrSurveyCandtDetailsCls">	</div>
								<center><img id="dataLoadingsImgForIVRDetails" src="images/icons/loading.gif" style="width:50px;height:50px;display:none;margin-top:50px;"/></center>
								<div class="ivrSurvysDetls"></div>
								<div class="ivrDetailsCls" id="ivrsurveyDetailsId"></div>
								<center><img id="ivrsurveyDataLoadoing" src="images/icons/survey-details.gif" style="width:250px;height:200px;display:none;"/></center>
							</div>
						  </div>
						</div>
                    </div>
				</div>
            </div>
        </div>
		</c:if>
		<div class="row">
			<div class="col-md-12 ">
				<div class="panel panel-default">
					<div class="panel-heading locWiseEvnAtnCls">
						<h4 class="panel-title"> <img src="img/attendence.png" style="width: 18px;">&nbsp;&nbsp;&nbsp;
							MAHANADU ATTENDEES
							<i class="glyphicon glyphicon-chevron-down pull-right"></i>
						</h4>
					</div>
					<div class="panel-body locWiseEvnAtnBody in">
						<div class="row" id="evntDateDiv">
							<div class="col-md-4">
								<label>Events</label>
								<select class="form-control" id="eventsId">
								<option value="30">Mahanadu 2016</option>
								<option value="7">Mahanadu 2015</option>
								</select>
							</div>
							<!--<div class="col-md-4">
								<label>Date</label>
								<div class="input-group">
									<input class="form-control" type="text" id="dateEvntBody"/>
									<span class="input-group-addon">
										<i class="glyphicon glyphicon-calendar"></i>
									</span>
								</div>
								
							</div>-->
							<div class="col-md-4">
								<button class="btn btn-success m_top20" type="button" onclick="getCadreLocationWiseEventAttendeeCounts($('#cadreDistrictId').val(),'DISTRICT','DISTRICT','cadreEvntdistrictAttnds','onload','');">Submit</button>
							</div>
						</div>
						<div id="cadreEvntdistrictAttnds" class="m_top20" ></div>
						<div id="errMsgID" class="m_top20" style="display:none;"></div>
						<div id="cadreEvntConstiAttnds" class="m_top20"></div>
						<div id="cadreEvntMandalAttnds" class="m_top20"></div>
					</div>
				</div>
			</div>
		</div>
		<!--benefits start-->
		<div class="row">
			<div class="col-md-12 ">
				<div class="panel panel-default">
					<div class="panel-heading" id="benefitsCollapseHeadingId">
						<h4 class="panel-title"> <img src="img/benefitsIcon.png" style="width: 18px;">&nbsp;&nbsp;&nbsp;
							BENEFITS
							<i class="glyphicon glyphicon-chevron-down pull-right"></i>
						</h4>
					</div>
					<div class="panel-body in" id="benefitsCollapseBodyId">
						<div>
						     <h4 class="text-capital;">PERSONAL BENEFITS<i class="glyphicon expandIcon glyphicon-minus benefiExpandCls" style="margin-left:20px;" ></i></h4>
							 <div class="hideShowCls" id="personalBenefitsDivId"></div>
						</div>	
						<div>
						    <h4 class="text-capital;">FAMILY MEMBERS BENEFITS<i class="glyphicon expandIcon  glyphicon-minus benefiExpandCls" style="margin-left:20px;" ></i></h4>
							<div class="hideShowCls" id="familyBenefitsDivId"></div>
						</div>
						<div>
						    <h4 class="text-capital;">OWN CONSTITUENCY BENEFITS<i class="glyphicon expandIcon  glyphicon-minus benefiExpandCls" style="margin-left:20px;" ></i></h4>
							<div class="hideShowCls" id="constituencyBenefitsDivId"></div>
						</div>
						<div id="participatedConsGBenefitDivId">
						    <h4 class="text-capital;">PARTICIPATED CONSTITUENCY BENEFITS<i class="glyphicon expandIcon glyphicon-minus benefiExpandCls" style="margin-left:20px;" ></i></h4>
							<div class="hideShowCls" id="partConstituencyBenefitsDivId"></div>
						</div>
						<div id="localityConsBenefitDivId">
						    <h4 class="text-capital;">LOCALITY BENEFITS<i class="glyphicon expandIcon glyphicon-minus benefiExpandCls" style="margin-left:20px;" ></i></h4>
							<div class="hideShowCls" id="localityBasedBenefitsDivId"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!--benefits end-->
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
			<div class="col-md-12">
				<div id="feedbackDivId"></div>
			</div>
		</div>
      </div>
      <div class="modal-footer">
       <button type="button" class="btn btn-default btn-success btn-sm" data-dismiss="modal">Close</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
	
<div class="modal fade" id="Ivrmodal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog modal-lg" role="document">
  <div class="col-md-12 col-xs-12">
	 <div class="panel panel-default">
		<div class="panel-heading">
		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="panel-title text-bold">IVR DETAILS - <span style="color:green;" id="ivrCadreNameId"></span></h4>
        </div>
		<div class="panel-body">
		<div id="ivrDetailsdataLoding" class="col-xs-offset-6"></div>
		 <div id="modalBodyId"></div>
		 <div id="paginationDivId"  style="margin-top:35px;"></div>
		 <button type="button" class="btn btn-default pull-right" data-dismiss="modal">Close</button>
		 </div>
	 </div>
  </div>
  </div>
</div>
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
		
		<div class="modal fade" id="ivrDetailsModelId">
		  <div class="modal-dialog modal-lg">
			<div class="modal-content">
			  <div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title text-center"><span id="ivrModalHeadingId"></span></h4>
			  </div>
			  <div class="modal-body">
				<!--<div id="ivrDetailsBodyId">
				</div>-->
				<center><img id="dataLoadingsImgForIVRDetails" src="images/icons/loading.gif" style="width:50px;height:50px;display:none;margin-top:50px;"/></center>
			  </div>
			  <div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			  </div>
			</div><!-- /.modal-content -->
		  </div><!-- /.modal-dialog -->
		</div><!-- /.modal -->
		
		<div class="modal fade" id="deathHospModelDivId">
		  <div class="modal-dialog modal-lg" style="width:90%">
			<div class="modal-content">
			  <div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title text-center"><span id="deathHospModalHeadingId"></span></h4>
			  </div>
			  <div class="modal-body">
				<div id="deathHospModalBodyId">
				</div>
				<div id="statusDivIdForInsurance" class="m_top20"></div>
				<center><img id="dataLoadingsImgForDeathHospDetails" src="images/icons/loading.gif" style="width:50px;height:50px;display:none;margin-top:50px;"/></center>
			  </div>
			  <div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			  </div>
			</div><!-- /.modal-content -->
		  </div><!-- /.modal-dialog -->
		</div><!-- /.modal -->
		<div class="modal fade" id="grievanceDetailsModalDivId">
		  <div class="modal-dialog modal-lg">
			<div class="modal-content">
			  <div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title" id="grievanceDetailsModalHeadingId"></h4>
			  </div>
			  <div class="modal-body">
				<div id="grievanceDetailsModalBodyId">
				</div>
				<div id="statusDivIdForGrievance" class="m_top20"></div>
				<center><img id="dataLoadingsImgForGrievanceStatusDetails" src="images/icons/loading.gif" style="width:50px;height:50px;display:none;margin-top:50px;"/></center>
			  </div>
			  <div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			  </div>
			</div><!-- /.modal-content -->
		  </div><!-- /.modal-dialog -->
		</div><!-- /.modal -->
		
		<div class="modal fade bs-example-modal-sm historyShowModal"  tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">	  
			<div class="modal-dialog modal-lg">
			<div class="modal-content ">
			  <div class="modal-header" style="background-color:#CCCCCC">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title"><span id="appCandidateNameId"></span>APPOINTMENT HISTORY</h4>
			  </div>
			  <div class="modal-body" style="padding:25px 5px 35px 5px;">
				<div id="aptCandidateHistorystatusOverViewDiv"></div>
				<div id="aptCandidateHistoryDiv"></div>
				<div id="appointmentCommentsDiv"></div>
			  </div>
			</div>
		  </div>
		</div>
		
		<div class="modal fade" id="grievanceBenifitsDetailsModalDivId">
		  <div class="modal-dialog modal-lg">
			<div class="modal-content">
			  <div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title text-center"><span id="grievanceBenifitsDetailsModalHeadingId"></span></h4>
			  </div>
			  <div class="modal-body">
				<div id="grievanceBenifitsDetailsModalBodyId">
				</div>
				<!--<div id="statusDivIdForGrievance" class="m_top20"></div>-->
				<center><img id="dataLoadingsImgForGrievanceBenifitsStatusDetails" src="images/icons/loading.gif" style="width:50px;height:50px;display:none;margin-top:50px;"/></center>
			  </div>
			  <div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			  </div>
			</div><!-- /.modal-content -->
		  </div><!-- /.modal-dialog -->
		</div><!-- /.modal -->
	<div class="modal fade" id="viewGrievanceStatusFlow">
		  <div class="modal-dialog modal-sm" style="width:75%">
			<div class="modal-content">
			  <div class="modal-header">
				<button type="button" class="close statusFlowCloseCls" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title text-center"><span id="grievanceDetailsModalHeadingId"></span></h4>
			  </div>
			  <div class="modal-body">
				<div id="grievanceStatusFlowModalBodyId">
				</div>
				<div id="statusDivIdForGrievance" class="m_top20"></div>
				<center><img id="dataLoadingsImgForGrievanceStatusDetails" src="images/icons/loading.gif" style="width:50px;height:50px;display:none;margin-top:50px;"/></center>
			  </div>
			  <div class="modal-footer">
				<button type="button" class="btn btn-default statusFlowCloseCls" data-dismiss="modal">Close</button>
			  </div>
			</div><!-- /.modal-content -->
		  </div><!-- /.modal-dialog -->
		</div><!-- /.modal -->
		
		<div class="modal eventAttendanceModalId" tabindex="-1" role="dialog">
		  <div class="modal-dialog">
			<div class="modal-content">
			  <div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title" id="eventAttendanceModalHeadingId"></h4>
			  </div>
			  <div class="modal-body">
			  <div id="eventAttendanceInfoBodyId"></div>
				<center><img id="dataLoadingsImgForEventAttendanceInfoId" src="images/icons/loading.gif" style="width:50px;height:50px;display:none;margin-top:50px;"/></center>
			  </div>
			  <div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				<!--<button type="button" class="btn btn-primary">Save changes</button>-->
			  </div>
			</div><!— /.modal-content —>
		  </div><!— /.modal-dialog —>
		</div><!— /.modal —>
		<div class="modal fade" id="meetingParticipatingDivId">
		  <div class="modal-dialog modal-sm" style="width:75%">
			<div class="modal-content">
			  <div class="modal-header">
				<button type="button" class="close " data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title text-center"><span id="meetingParticipatingDivModalHeadingId"></span>PARTY MEETING PARTICIPATION INVITED DETAILS </h4>
			  </div>
			  <div class="modal-body">
				<div id="meetingParticipatingDivModalBodyId">
				</div>
				<div id="statusDivIdForGrievance" class="m_top20"></div>
				<center><img id="dataLoadingsImgForGrievanceStatusDetails" src="images/icons/loading.gif" style="width:50px;height:50px;display:none;margin-top:50px;"/></center>
			  </div>
			  <div class="modal-footer">
				<button type="button" class="btn btn-default " data-dismiss="modal">Close</button>
			  </div>
			</div><!-- /.modal-content -->
		  </div><!-- /.modal-dialog -->
		</div><!-- /.modal -->
		
		<!-- Model for Debate Start-->
		<div class="modal" tabindex="-1" role="dialog" id="debateModelDivId">
		  <div class="modal-dialog modal-lg" style="width:90%">
			<div class="modal-content">
			  <div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title">Debate Details</h4>
			  </div>
			  <div class="modal-body">
					<center><img id="dataLoadingsImgForDebateModelId" src="images/icons/loading.gif" style="width:50px;height:50px;display:none;margin-top:50px;"/></center>
				  <div id="debateModelId" class="row">				 
				  </div>
				  <center><img id="dataLoadingsImgForDebateperformId" src="images/icons/loading.gif" style="width:50px;height:50px;display:none;margin-top:50px;"/></center>
				  <div id="debatePerformanceId" class="row">					
				  </div>
					<center><img id="dataLoadingsImgForDebateStrId" src="images/icons/loading.gif" style="width:50px;height:50px;display:none;margin-top:50px;"/></center>
				  <div id="debateStrongWeekId" class="row">					
				  </div>
					<!--<center><img id="dataLoadingsImgForDebate" src="images/icons/loading.gif" style="width:50px;height:50px;display:none;margin-top:50px;"/></center>-->
			  </div>
			  <div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			  </div>
			</div><!-- /.modal-content -->
		  </div><!-- /.modal-dialog -->
		</div><!-- /.modal -->
		
		<!-- Model for Debate End-->
		<!-- Model for Report start swadhin-->
		<div class="modal fade" tabindex="-1" id="reportModelId" role="dialog">
			<div class="modal-dialog" style="width:50%;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title">CADRE REPORT DETAILS</h4>
					</div>
					<div class="modal-body" id="reportDetailsId">
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					</div>
				</div><!— /.modal-content —>
			</div><!— /.modal-dialog —>
		</div><!— /.modal —>
		<!-- Model for Report End-->  
		
		
		<!-- Model for Report start swadhin-->
		<div class="modal fade" tabindex="-1" id="profileModelId" role="dialog">
			<div class="modal-dialog" style="width:50%;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title">CADRE REPORT DETAILS</h4>
					</div>
					<div class="modal-body" id="profileDetailsId">
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					</div>
				</div><!— /.modal-content —>
			</div><!— /.modal-dialog —>
		</div><!— /.modal —>
		<!-- Model for Report End-->  

		<div class="modal fade" tabindex="-1" id="reportModelId1" role="dialog" >
			<div class="modal-dialog modal-sm" style="width:70%;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title">CADRE HEALTH REPORT DETAILS</h4>
					</div>
					<div class="modal-body" >
						<div id="reportDetailsId1"></div>
						<div class ="">
						<h4 class="modal-title m_top20">CADRE CANDIDATE HEALTH REPORT DETAILS</h4>
						<div class="m_top20" id="healthWiseTableId"></div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					</div>
				</div><!— /.modal-content —>
			</div><!— /.modal-dialog —>
		</div><!— /.modal —>
		<div class="modal fade" tabindex="-1" id="healthReportPdfModelId" role="dialog">
			<div class="modal-dialog" style="width:70%;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title">CADRE HEALTH REPORT DETAILS</h4>
					</div>
					<div class="modal-body" id="healthReportPdfDetailsId">
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					</div>
				</div><!— /.modal-content —>
			</div><!— /.modal-dialog —>
		</div><!— /.modal —>
	<!-- Model for pdf Report End-->
	<div class="modal fade" tabindex="-1" id="pdfModelId" role="dialog">  
			<div class="modal-dialog" style="width:80%;">      
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title">CADRE REPORT DETAILS</h4>
					</div>
					<div class="modal-body" id="pdfReportDetailsId">
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					</div>
				</div>
			</div>
	</div>
	<!-- Model for cadre Report start-->
	<div class="modal fade" tabindex="-1" id="leaderCadreId" role="dialog">  
			<div class="modal-dialog" style="width:80%;">      
				<div class="modal-content">
					<div class="modal-header" style="background-color:#999;">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title" id="enrolementTitleId0">CADRE ENROLEMENTS DETAILED VIEW</h4>
					</div>
					<div class="modal-body" id="firstDesigId">
					</div>
					<div>
						<h4 class="modal-title" id="enrolementTitleId1" style="margin-left: 15px;"></h4>
					</div>        
					<div class="modal-body" id="secondDesigId">  
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					</div>  
				</div>
			</div>
	</div>

<div class="modal fade" id="notesModalDivId" style="display:none;">
			  <div class="modal-dialog" style="width:80%">
				<div class="modal-content">
				
				  <div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title text-danger" id="notesModalTitleId"><img id="notesImgId" src="img/consent_clipart_pen_and_paper_le.jpg" style="height:20px;width:20px;"/><span id="addModalId"></span></h4>
				  </div>
				  
				  <div class="modal-body" id="upadateCallerModalBodyId">
					<div class="row">
						<div class="col-md-12 col-xs-12 col-sm-12">
							<div id="errorUpCallId" style="color:red;"></div>
							<input type="hidden" id="hiddencadreNotesId"/>
							<button class="btn btn-success pull-right btn-sm notesClass"><i style="color:#fff;" class="glyphicon glyphicon-refresh" title="Refresh"></i></button>
						</div>
						<div class="col-md-12 col-xs-12 col-sm-12">
							<div class="row">
							<div id="errorUpCallId"></div>
							<!--<div class="col-md-12 col-xs-12 col-sm-12">
								  <ul class="nav nav-tabs" role="tablist">
									<li role="presentation" class="active"><a href="#notesExisting" aria-controls="notesExisting" role="tab" data-toggle="tab" onclick="getCadreNotesInformationDetails(cadreId)">Existing</a></li>
									<li role="presentation"><a href="#notesNew" aria-controls="notesNew" role="tab" data-toggle="tab">Create New Notes</a></li>    
								  </ul>
								  <div class="tab-content">
									<div role="tabpanel" class="tab-pane active"></div>
									<div role="tabpanel" class="tab-pane" id="notesNew">
										<label>Notes:</label>
										
									</div>
								  </div>
								</div>-->
								<div class="col-md-12">
									<textarea id="notesDescriptionId" class="form-control"></textarea>
									<button type="button" class="btn btn-primary btn-sm pull-right" style="display:none;"id="updateButnId" onclick="saveCadreNotesInformationDetails(0,0)">UPDATE</button>
									<button type="button" class="btn btn-primary btn-sm pull-right" id="updateNotesButtonId" onclick="saveCadreNotesInformationDetails(0,0)" style="margin-right: 10px;">ADD</button>
									<div id="NoNotesId" style="color:red;margin-left:385px;"></div>
									<div class="paginationCls"></div>
								</div>
								<div style="margin-left:15px;font-size:15px;font-weight:bold;"> <span>Previous Notes:</span></div>
								<div class="col-md-12">
									<div  id="notesExisting" class="m_top20"></div>
									<div class="paginationCls"></div>
								</div>
							</div>
						</div>
					</div>
				  </div>
				<div class="modal-footer">
					<span class="pull-left" id="upadateNotesId"></span>
					<span id="updatefooterNameId"></span>
					<button type="button" class="btn btn-default btn-sm" id="closeButtonId" data-dismiss="modal">Close</button>
					
				  </div>
				  
				</div>
			  </div>
</div>

<div class="modal fade" id="partyMeetingsModalId" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document" style="width:90%">
    <div class="modal-content">
      <div class="modal-header" style="background-color:#CCC;">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="partyMeetingSummaryHeadingId"></h4>
      </div>
      <div class="modal-body">
		<h4 class="panel-title">MEETINGS SUMMARY </h4>
		<div class="table-responsive">
			<div id="summaryDivId"></div>
		</div>
		<!--<h4 class="panel-title m_top20">TABLE HEADING</h4>-->
        <div class="table-responsive" >
			<div id="detailsId"></div>  
		</div>
      </div>
    </div>
  </div>
</div>
<div class="modal fade" id="volunteerModalDivId">
		  <div class="modal-dialog modal-excess">
			<div class="modal-content">
			  <div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title" id="volunteerDetailsModalHeadingId">Volunteer Details Information</h4>
			  </div>
			  <div class="modal-body">
				<div id="volunteerDetailsModalBodyId">
				</div>
				<div id="statusDivIdForvolunteer" class="m_top20"></div>
				<center><img id="dataLoadiVolunteerDetailsImg" src="images/icons/loading.gif" style="width:50px;height:50px;display:none;margin-top:50px;"/></center>
			  </div>
			  <div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			  </div>
			</div><!-- /.modal-content -->
		  </div><!-- /.modal-dialog -->
		</div>
		
<div class="modal fade" id="nominatedModalDivId">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">APPLIED NOMINATED POST DETAILS</h4>
      </div>
      <div class="modal-body" >
	   <center><img id="viewDetilsId" src="images/icons/loading.gif" style="width:25px;height:20px;display:none;"/></center>
	  <div id="nmtedMdlId" class="row"></div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>	

<div class="modal fade" id="benefitCountModal">
  <div class="modal-dialog" role="document" style="width:80%">
    <div class="modal-content">
      <div class="modal-header" style="background-color:#999999">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="benefitCountModalHeading" style="text-transform:uppercase">Heading</h4>
      </div>
      <div class="modal-body" >
		<div class="row">
			<div class="col-md-12 col-xs-12 col-sm-12">
				<div id="benefitCountCorpBene"></div>
				<div id="customPaginationDivId"></div>
			</div>
		</div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>	

<!-- Alert Modal -->
<div class="modal fade" id="alertModalDivId">
  <div class="modal-dialog" role="document">
    <div class="modal-content" style="width:900px;">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabelId"></h4>
      </div>
      <div class="modal-body" >
	  <center><img id="alertImgId" src="images/icons/loading.gif" style="width:25px;height:20px;display:none;"/></center>
	  <div id="alertModalStrId"></div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>
<!-- Tour Modal Start -->
<div class="modal" tabindex="-1" role="dialog" id="cadreTourModalDivId" style="z-index:9999;">
		  <div class="modal-dialog modal-lg" style="width:85%" role="document">       
			<div class="modal-content" style="border-radius:0px">
			  <div class="modal-header" style="background-color:#CCC">
				<button type="button" class="close closeIconModal" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<span id="tourCadreHeadingId"></span>
			  </div>
			  <div class="modal-body"> 
				<div class="row" style="background: rgb(204, 204, 204) none repeat scroll 0% 0%; padding: 0px 0px 20px; border-radius: 6px; margin: 10px 0px 0px;">
					<div class="col-md-2 col-xs-12 col-sm-4"> 
						<select class="pull-right form-control" id="dateRangeSliderYear" style="margin-top: 46px;">
							<option value="0">Select Year</option>
							<option value="2016">2016</option>
							<option value="2017">2017</option>
							<option value="2018">2018</option>
							<option value="2019">2019</option>
							<option value="2020">2020</option>
							<option value="2021">2021</option>
							<option value="2022">2022</option>
							<option value="2023">2023</option>
							<option value="2024">2024</option>
							<option value="2025">2025</option>
						</select>
					</div>
					<div class="col-md-9 col-xs-12 col-sm-12" style="margin-left: -20px;"> 
						<div id="cadreTourSlider" style="margin-top:7px"></div>
					</div>
					<div class="col-md-1 col-xs-12 col-sm-4 pull-right">
						<button class="btn btn-success pull-right" id="subMitBtn" type="button" style="margin-top: 46px;">SUBMIT</button>
					</div>
				
				</div>
				<div class="row">
					<div class="col-md-12 col-xs-12 col-sm-12"> 
						<div id="cadreTourIndividualDetailsBlock" class="m_top20"></div>
						<div id="cadreMonthWiseComplainceDivId" class="row m_top20"></div>
						<div id="cadreTourMonthWiseDtlsBlockDivId" class="m_top20"></div>
					</div>
				</div>
			  </div>
			  <div class="modal-footer">     
				<button type="button" class="btn btn-default closeIconModal" class="close" data-dismiss="modal">Close</button>
			  </div>
			</div><!-- /.modal-content -->
		  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<div class="modal fade" tabindex="-1" id="tourDocumentModalId" role="dialog" style="z-index:99999;">
	<div class="modal-dialog" style="width:70%;">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close closeShowPdfCls" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title">Tour Document</h4>  
			</div>
			<div class="modal-body" id="tourNewDocumentBodyId">
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default closeShowPdfCls" data-dismiss="modal">Close</button>
			</div>
		</div><!--  /.modal-content -->  
	</div><!--  /.modal-dialog -->
</div><!--  /.modal -->

<!--  Tour Modal End -->

<!-- Alert Details Modal Start -->
     <div class="modal" tabindex="-1" role="dialog" id="cdrModelDivId">
		  <div class="modal-dialog modal-lg">       
			<div class="modal-content" style="border-radius:0px">
			  <div class="modal-header" style="background-color:#999999">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title" id="tourDocHeadingId"></h4>  
			  </div>
			  <div class="modal-body">   
				<div class="row">
					<div class="col-md-12 col-xs-12 col-sm-12"> 
						<div id="cdrModelId"></div>
					</div>
					<div class="col-md-12 col-xs-12 col-sm-12"> 
						<div id="alertDestId" ></div>
					</div>
					<div class="col-md-12 col-xs-12 col-sm-12 m_top10"> 
						<div id="sourceHeadingId"></div>
					</div>
					<div class="col-md-12 col-xs-12 col-sm-12"> 
						<div id="headingNameId" ></div>
					</div>
					<div class="col-md-12 col-xs-12 col-sm-12 m_top10"> 
						<div id="alertDocHeadingId"></div>
					</div>
					<div class="col-md-12 col-xs-12 col-sm-12"> 
						<div id="alertDocId" ></div>
					</div>
					<div class="col-md-12 col-xs-12 col-sm-12 m_top10"> 
						<div id="alertAttachTitId"></div>    
					</div> 
					<div class="col-md-12 col-xs-12 col-sm-12"> 
						<div id="alertAttachImgId"></div>  
					</div>
					<div class="col-md-12 col-xs-12 col-sm-12 m_top10"> 
						<div id="alertInvolvedCandidates"></div>        
					</div>
					<div class="col-md-12 col-xs-12 col-sm-12 m_top10"> 
						<div id="alertAssignedCandidates"></div>  
					</div>
					<div class="col-md-12 col-xs-12 col-sm-12 m_top10"> 
						<div id="alertStatusDiv" ></div>    
					</div>
					<div  class="col-md-12 col-xs-12 col-sm-12"> 
						<div id="alertCommentsDiv"></div>  
					</div> 
					<div  class="col-md-12 col-xs-12 col-sm-12 m_top10"> 
						<div id="alertVerificationDiv"></div>    
					</div>
					<div  class="col-md-12 col-xs-12 col-sm-12"> 
						<div id="alertVerificationDtlsDiv"></div>  
					</div>
				</div>
			  </div>
			  <div class="modal-footer">     
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			  </div>
			</div><!-- /.modal-content -->
		  </div><!-- /.modal-dialog -->
		</div><!-- /.modal -->
		
	 <div class="modal fade" id="myModalShowNew">
		 <div class="modal-dialog modal-lg" role="document" style="width:90%">
			<div class="modal-content">
				<div id="myModalShowNewId"></div>
			</div>
		 </div>  
	</div>    
 <!-- End -->
 <!--srujana-->
 <div class="modal fade" tabindex="-1" id="pdfModelId1" role="dialog">  
			<div class="modal-dialog" style="width:80%;">      
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title">CADRE DOCUMENTS </h4>
					</div>
					<div class="modal-body" id="documentDetailsId">
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					</div>
				</div>
			</div>
	</div>
<div class="modal fade" id="surveyQuestionDetailsDivId"style="display:none">
	<div class="modal-dialog" role="document" style="width:80%;">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalSurveyQueDetailsId"> SURVEY FEED BACK</h4>
      </div>
      <div class="modal-body" >
		  <center>
			<img id="surveyQueDetailsIdImgId" src="images/icons/loading.gif" style="width:25px;height:20px;display:none;"/>
		  </center>
		  <div class="row">
			<div class="col-sm-12" style="margin-top:10px;">
				<div id="quizSurveyDiv"></div>
			</div>
			<div class="col-sm-12">
				<div id="normalSurveyDiv"></div>
			</div>
			</div>
      </div>
      <div class="modal-footer" style="margin-top:10px;">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>
<!-- Model For CadreDetails-->
<div class="modal fade" id="modelForCadDet">
  <div class="modal-dialog" style="width:80%;">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">CANDIDATE HEALTH REPORT DETAILS</h4>
      </div>
      <div class="modal-body">
		<div class="row">
			<div id="candidateReportTabId">
			</div>
		</div>
		<div class="row m_top20">
			<div id="candidateAttributesId"></div>
			<div id="bloodSamplesTabId">
			</div>
		</div>
       </div>
      <div class="modal-footer">
       <button type="button" class="btn btn-default btn-success btn-sm" data-dismiss="modal">Close</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div>
<div class="modal fade" id="healthReportModalId"  role="dialog">
  <div class="modal-dialog " role="document" style="width: 85%;">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">CANDIDATE HEALTH REPORT DETAILS</h4>
	  </div>
      <div class="modal-body" >
		<center><img id="helathReportImgId" src="images/icons/loading.gif" style="width:50px;height:50px;display:none;"/></center>
		<div id="healthModalDetailsDivId"></div>
      </div>
    </div>
  </div>
</div>
<div class="modal fade" id="healthReportPdfOrImgId"  role="dialog">
  <div class="modal-dialog " role="document" style="width: 70%;">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">YEAR WISE CANDIDATE HEALTH REPORT</h4>
	  </div>
      <div class="modal-body" >
		<div class="row">
			<div class="col-sm-12">
				<div id="healthReportImgOrPdfId"></div>
			</div>
		</div>
			
			
      </div>
    </div>
  </div>
</div>
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script src="https://code.jquery.com/ui/1.12.0/jquery-ui.js"></script>
	<script type="text/javascript" src="https://www.google.com/jsapi"></script>
	<!--<script src=" https://code.jquery.com/ui/1.11.1/jqueryui/1.11.1/jquery-ui.js "></script>-->
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
	<script src="dist/JqueryTe/jquery-te-1.4.0.min.js" type="text/javascript"></script>
	   <!--  SERVER SIDE PAGINATION JS -->
    <script src="js/simplePagination/simplePagination.js" type="text/javascript"></script>
	<script src="js/grievance/statusColor.js" type="text/javascript"></script>
	<script src="js/cadreDetails/cadre_details.js" type="text/javascript"></script>
	<script src="js/cadreDetails/cadre_details1.js" type="text/javascript"></script>
	<script type="text/javascript" src="pdfexpand/source/jquery.fancybox.js?v=2.1.5"></script>
	<script type="text/javascript" src="js/googleAnalytics/googleChartsColourPicker.js"></script>
	<script type="text/javascript" src="https://www.google.com/jsapi"></script>
	<script type="text/javascript" src="js/highcharts/js/highcharts3.js"></script>
	<script type="text/javascript" src="js/highcharts/js/highchartColorPicker.js"></script>
	<script src="dist/Timepicker/bootstrap-datetimepicker.min.js" type="text/javascript"></script>
	<script src="newCoreDashBoard/Plugins/RangeSlider/jQDateRangeSlider-withRuler-min.js" type="text/javascript"></script>
	<script src="newCoreDashBoard/Plugins/RangeSlider/jquery-ui.js" type="text/javascript"></script>
	<script src="newCoreDashBoard/Plugins/Slick/slick.js" type="text/javascript"></script>
	<script type="text/javascript" src="newCoreDashBoard/js/simplePagination3.js" ></script>
	<script type="text/javascript" src="pdfexpand/source/jquery.fancybox.js" ></script>
	<script>
	google.load("visualization", "1", {packages:["corechart"]});
	

	$(document).on("click","#nominatedreportsId",function(){
		$("#profileModelId").modal('show');
	})
	
	$("#notesDescriptionId").jqte();
	 
	//var globalCadreId = '${cadreId}';
	var memberCadreId = '${cadreId}';       
	var participatedConstituencyId=0;
	var participatedConstituencyType="";
	var participatedParliamentId = 0;
	var participatedDistrictId = 0;
	
	var participatedConstName = "";
	var participatedParlName = "";
	var participatedDistName = "";
	
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
	
	var membershipId = '${memberShipId}';
	var constituencyId = '${constituencyId}';
	
	//GETTING THE TDP CADRE LOCATION DETAILS BY USER ADDRESS TABLE.
	var tdpCadreDistrictId = '${cadreLocationVO.districtId}';
	var tdpCadreDistrictName = '${cadreLocationVO.districtName}';
	var tdpCadreParliamentConstituencyId = '${cadreLocationVO.parliamentId}';
	var tdpCadreParliamentName = '${cadreLocationVO.parliamentName}';
	var tdpCadreAssemblyConstituencyId = '${cadreLocationVO.constituencyId}';
	var tdpCadreAssemblyConstituencyName = '${cadreLocationVO.constituencyName}';
	var tdpCadreLocalElectionBodyId ='${cadreLocationVO.localElectionBodyId}'; 
	var tdpCadreLocalElectionBodyName='${cadreLocationVO.localElectionBody}'; 
	var tdpCadreTehsilId ='${cadreLocationVO.tehsilId}'; 
	var tdpCadreTehsilName ='${cadreLocationVO.tehsilName}'; 
	var tdpCadreWardId = '${cadreLocationVO.wardId}';
	var tdpCadreWardName = '${cadreLocationVO.wardName}';
	var tdpCadreVillageId = '${cadreLocationVO.villageId}';
	var tdpCadreVillageName = '${cadreLocationVO.villageName}';
	var tdpCadreElectionTypeId = '${cadreLocationVO.electionTypeId}';
	var tdpCadreElectionType = '${cadreLocationVO.electionType}';
	var isLeader = '${cadreLocationVO.isLeader}';
	checkIsLeader(isLeader);
	partyMeetingsDatePickerInstantiation();
	function getCadreEnrollmentYears(){
		 var jsObj={};
		$.ajax({
			type : "GET",
			url : "getCadreEnrollmentYearsAction.action",
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(result != null && result.length > 0){
				for(var i in result){
					$("#tdpCommitteeYearId").append('<option value='+result[i].id+'>'+result[i].electionYear+'</option>');
				}
			}
		});
	}
	function callFunForMembership()
	{
		getCadreEnrollmentYears();
		if((globalCadreId == null || globalCadreId.trim().length == 0) && (membershipId != null && membershipId > 0)){
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
	}
	callFunForMembership();
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
	
		$('#accordionSurvey').on('shown.bs.collapse', function() {
			var surveyId = $(this).find("div.in").attr("attr_survey_id");
			var cadreId= $(this).find("div.in").attr("attr_cadre_id");
			var indexId=$(this).find("div.in").attr("attr_index_id");
			//getTdpCadreSurveyDetails(cadreId,surveyId,indexId,"NotAll",0,'true');
		});

	$(document).on("click",".eachParticipationListCls",function(){
			var constId=$(this).attr("attr_constId");
			var elecType=$(this).attr("attr_election_type");
			var elecYear=$(this).attr("attr_election_year");
			
			var win =window.open("constituencyElectionResultsAction.action?constituencyId="+constId+"&electionType="+elecType+"&electionYear="+elecYear,"constituencyElectionResults","scrollbars=yes,height=600,width=750,left=200,top=200");
			win.focus();
		});
	$('[data-toggle="tooltip"]').tooltip();
		
	$('.table-scroll').scrollator({
		custom_class: 'table-scroll',
	});

$(document).on("click",".newsSubmitBtn",function(){
	getCandidateAndLocationSummaryNews();
});
$(document).on("click",".meetingSubmitBtn",function(){
	getConductedPartyMeetingDetails('','','true','0');
});

var globalDate = "global";
$(document).on("click","#meetingDatePicker",function(){
	globalDate ="meetingGlobal";
});

$("#familyMemberBodyId").collapse('hide');
$("#cadreEnrolementParticepateStatBodyId").collapse('show');
$(document).on("click","#cadreEnrolementParticepateStatsId",function(){
	var isVisible = $( "#cadreEnrolementParticepateStatsIdShowId" ).is( ":visible" );
	if(isVisible==false){
		 $( "#cadreEnrolementParticepateStatsIdShowId" ).show();
		 $( "#cadreEnrolementParticepateStatsIdShowId" ).hide();
	}else{
		$( "#cadreEnrolementParticepateStatsIdShowId" ).hide();
		$( "#cadreEnrolementParticepateStatsIdShowId" ).show();
	}
	$("#cadreEnrolementParticepateStatBodyId").collapse('toggle');
});
$(document).on("click","#familyMemberHeadingId",function(){
	var isVisible = $( "#familyMemberHideId" ).is( ":visible" );
	if(isVisible==false){
		 $( "#familyMemberHideId" ).show();
		 $( "#familyMemberShowId" ).hide();
	}else{
		$( "#familyMemberHideId" ).hide();
		$( "#familyMemberShowId" ).show();
	}
	$("#familyMemberBodyId").collapse('toggle');
});

$("#familyBenefitsBodyId").collapse('hide');
$(document).on("click","#familyBenefitsHeadingId",function(){
	var isVisible = $( "#familyBenefitsHideId" ).is( ":visible" );
	if(isVisible==false){
		 $( "#familyBenefitsHideId" ).show();
		 $( "#familyBenefitsShowId" ).hide();
	}else{
		$( "#familyBenefitsHideId" ).hide();
		$( "#familyBenefitsShowId" ).show();
	}
	$("#familyBenefitsBodyId").collapse('toggle');
});

$("#cadreBenefitsBodyId").collapse('hide');
$(document).on("click","#cadreBenefitsHeadingId",function(){
	var isVisible = $( "#cadreBenefitsHideId" ).is( ":visible" );
	if(isVisible==false){
		 $( "#cadreBenefitsHideId" ).show();
		 $( "#cadreBenefitsShowId" ).hide();
	}else{
		$( "#cadreBenefitsHideId" ).hide();
		$( "#cadreBenefitsShowId" ).show();
	}
	$("#cadreBenefitsBodyId").collapse('toggle');
});

$("#deathHospitalDivId").collapse('hide');
$(document).on("click","#deathHospitalDivHeaderId",function(){
	var isVisible = $( "#deathHospitalDivHideId" ).is( ":visible" );
	if(isVisible==false){
		 $( "#deathHospitalDivHideId" ).show();
		 $( "#deathHospitalDivShowId" ).hide();
	}else{
		$( "#deathHospitalDivHideId" ).hide();
		$( "#deathHospitalDivShowId" ).show();
	}
	$("#deathHospitalDivId").collapse('toggle');
});

$("#familyGrievanceDtlsBodyId").collapse('hide');
$(document).on("click","#familyGrievanceDtlsHeaderId",function(){
	$("#familyGrievanceDtlsBodyId").collapse('toggle');
});

$("#cadreEnrolmentStatsBodyId").collapse('show');
$("#cadreEnrolmentStatsBodyId1").collapse('show');
$(document).on("click","#cadreEnrolmentStatsHeaderId",function(){
	var isVisible = $( "#cadreEnrolmentStatsHideId" ).is( ":visible" );
	if(isVisible==false){
		 $( "#cadreEnrolmentStatsHideId" ).show();
		 $( "#cadreEnrolmentStatsShowId" ).hide();
	}else{
		$( "#cadreEnrolmentStatsHideId" ).hide();
		$( "#cadreEnrolmentStatsShowId" ).show();
	}
	$("#cadreEnrolmentStatsBodyId").collapse('toggle');
});
$(document).on("click","#cadreEnrolmentStatsHeaderId1",function(){
	var isVisible = $( "#cadreEnrolmentStatsHideId1" ).is( ":visible" );
	if(isVisible==false){
		 $( "#cadreEnrolmentStatsHideId1" ).show();
		 $( "#cadreEnrolmentStatsShowId1" ).hide();
	}else{
		$( "#cadreEnrolmentStatsHideId1" ).hide();
		$( "#cadreEnrolmentStatsShowId1" ).show();
	}
	$("#cadreEnrolmentStatsBodyId1").collapse('toggle');
});
$("#ivrSummaryBodyId").collapse('hide');
$(document).on("click","#ivrSummaryHeaderId",function(){  
	var isVisible = $( "#ivrSummaryHideId" ).is( ":visible" );
	if(isVisible==false){
		 $( "#ivrSummaryHideId" ).show();
		 $( "#ivrSummaryShowId" ).hide();
	}else{
		$( "#ivrSummaryHideId" ).hide();
		$( "#ivrSummaryShowId" ).show();
	}
	$("#ivrSummaryBodyId").collapse('toggle');
});

$("#cadreActivitiesBodyId").collapse('hide');
$(document).on("click","#cadreActivitiesHeaderId",function(){
	var isVisible = $( "#cadreActivitiesHideId" ).is( ":visible" );
	if(isVisible==false){
		 $( "#cadreActivitiesHideId" ).show();
		 $( "#cadreActivitiesShowId" ).hide();
	}else{
		$( "#cadreActivitiesHideId" ).hide();
		$( "#cadreActivitiesShowId" ).show();
	}
	$("#cadreActivitiesBodyId").collapse('toggle');
});

$("#cadreElectionProfileBodyId").collapse('show');
$(document).on("click","#cadreElectionProfileHeaderId",function(){
	var isVisible = $( "#cadreElectionProfileHideId" ).is( ":visible" );
	if(isVisible==false){
		 $( "#cadreElectionProfileHideId" ).show();
		 $( "#cadreElectionProfileShowId" ).hide();
	}else{
		$( "#cadreElectionProfileHideId" ).hide();
		$( "#cadreElectionProfileShowId" ).show();
	}
	$("#cadreElectionProfileBodyId").collapse('toggle');
});

$("#participatedConstBodyId").collapse('hide');
$(document).on("click","#participatedConstHeaderId",function(){
	var isVisible = $( "#participatedConstHideId" ).is( ":visible" );
	if(isVisible==false){
		 $( "#participatedConstHideId" ).show();
		 $( "#participatedConstShowId" ).hide();
	}else{
		$( "#participatedConstHideId" ).hide();
		$( "#participatedConstShowId" ).show();
	}
	$("#participatedConstBodyId").collapse('toggle');
});

$("#cadrMmbrBthBodyId").collapse('show');
$(document).on("click","#cadrMmbrBthHeaderId",function(){ 
var isVisible = $( "#cadrMmbrBthHideId" ).is( ":visible" );
if(isVisible==false){
		 $( "#cadrMmbrBthHideId" ).show();
		 $( "#cadrMmbrBthShowId" ).hide();
	}else{
		$( "#cadrMmbrBthHideId" ).hide();
		$( "#cadrMmbrBthShowId" ).show();
	}
	$("#cadrMmbrBthBodyId").collapse('toggle');
});

$("#trainingCampDetailsBodyId").collapse('hide');
$(document).on("click","#trainingCampDetailsHeaderId",function(){ 
var isVisible = $( "#trainingCampDetailsHideId" ).is( ":visible" );
if(isVisible==false){
		 $( "#trainingCampDetailsHideId" ).show();
		 $( "#trainingCampDetailsShowId" ).hide();
	}else{
		$( "#trainingCampDetailsHideId" ).hide();
		$( "#trainingCampDetailsShowId" ).show();
	}
	$("#trainingCampDetailsBodyId").collapse('toggle');
});

$("#identityBodyId").collapse('hide');
$(document).on("click","#identityHeaderId",function(){ 
var isVisible = $( "#identityHideId" ).is( ":visible" );
if(isVisible==false){
		 $( "#identityHideId" ).show();
		 $( "#identityShowId" ).hide();
	}else{
		$( "#identityHideId" ).hide();
		$( "#identityShowId" ).show();
	}
	$("#identityBodyId").collapse('toggle');
});

$("#addressBodyId").collapse('hide');
$(document).on("click","#addressHeaderId",function(){ 
var isVisible = $( "#addressHideId" ).is( ":visible" );
if(isVisible==false){
		 $( "#addressHideId" ).show();
		 $( "#addressShowId" ).hide();
	}else{
		$( "#addressHideId" ).hide();
		$( "#addressShowId" ).show();
	}
	$("#addressBodyId").collapse('toggle');
});
$("#committeeMetingsBodyId").collapse('hide');
$(document).on("click","#committeeMetingsHeaderId",function(){ 
var isVisible = $( "#committeeMetingsHideId" ).is( ":visible" );
if(isVisible==false){
		 $( "#committeeMetingsHideId" ).show();
		 $( "#committeeMetingsShowId" ).hide();
	}else{
		$( "#committeeMetingsHideId" ).hide();
		$( "#committeeMetingsShowId" ).show();
	}
	$("#committeeMetingsBodyId").collapse('toggle');
});
$("#grievencyBenefitBodyId").collapse('hide');
$(document).on("click","#grievencyBenefitHeaderId",function(){ 
var isVisible = $( "#grievencyBenefitHideId" ).is( ":visible" );
if(isVisible==false){
		 $( "#grievencyBenefitHideId" ).show();
		 $( "#grievencyBenefitShowId" ).hide();
	}else{
		$( "#grievencyBenefitHideId" ).hide();
		$( "#grievencyBenefitShowId" ).show();
	}
	$("#grievencyBenefitBodyId").collapse('toggle');
});
$("#comaplaintCountInmgMainDivid").collapse('hide');
$("#referralGrievanceDetailsId").collapse('hide');
	$(document).click(function() {
		$('.survey-hover').hide();
	});
	$(document).on("click",".survey-drop",function(){
		$('.survey-hover').hide();
		$(this).parent().find('.survey-hover').show();
		e.stopPropagation(); 
	});
	


$(document).on("click",".newsRadioCls",function(){
	getCandidateAndLocationSummaryNews();
});

	
	
	$(document).on("click",".ranges li",function(){
		if($(this).text() == "Custom"){
			return;
		}
		if(globalDate == "meetingGlobal"){
			getConductedPartyMeetingDetails('','','true','0');
			globalDate="global";
		}else{
			getCandidateAndLocationSummaryNews();
		}
		
		
	});

$('.committeeLocCls').click(function(){
    getLocationwiseCommitteesCount();
  });
$(".casteLocCls").click(function(){
	 var type = $("input[name='casteLocation']:checked").val();
	 if(type == "parliamentConstituency" || type == "district")
		getUpdatedCasteLocationWiseInfoForParliamentAndDistrict(type); 
	else if(type == "mandal" || type == "panchayat")
		getUpdatedCasteLocationWiseInfoForMandalAndVillage(type);
	else if(type == "assemblyConstituency")
		getUpdatedCastePartyInfo();
});

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
				str+='<div id="cadreDetailsDivId" class="cadreDetailsCls pointer" attr_cadre_id='+result[i].cadreId+' attr_membership_id='+result[i].membershipNo+'><input type="button" value="More Cadre Details" class="btn btn-sm btn-primary pull-right"/></div>';
				</c:if>
			str+='</div>';
			
		}
		$("#popupForDeathDetails").html(str);
		$('.table-scroll-1').scrollator({
			custom_class: 'table-scroll-1',
		});
	
}
</script>
<script>

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
		//getIVRDetails();
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
		//getIVRDetails();
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
		//console.log(styleStr);
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
	



$(document).on("click",".detailsCls",function(){
	
	var programId = $(this).attr("attr_programId");
	var cadreId = globalCadreId;
	
	$("#modelForTrainingDetails").show();
	$("#dataLoadingsImgForTrainingDetails").show();
	getAttendedTrainingCampBatchDetailsOfCadre(programId,cadreId);
	getRemarkSOfCadreByCallPurpose(programId,cadreId);
	getCategoryFeedBackAnswerForCadre();//call for feedback answer
	
});
/*$("#grievanceStatusMainDivId").collapse('hide');
$(document).on("click","#grievanceStatusDivId",function(){
	var isVisible = $( "#grievanceStatusHideId" ).is( ":visible" );
	if(isVisible==false){
		 $( "#grievanceStatusHideId" ).show();
		 $( "#grievanceStatusShowId" ).hide();
	}else{
		$( "#grievanceStatusHideId" ).hide();
		$( "#grievanceStatusShowId" ).show();
	}
	$("#grievanceStatusMainDivId").collapse('toggle');
});*/
$("#casteBodyDiv").collapse('hide');
$(document).on("click","#casteStatusId",function(){
	var isVisible = $( "#casteStatusHideId" ).is( ":visible" );
	if(isVisible==false){
		 $( "#casteStatusHideId" ).show();
		 $( "#casteStatusShowId" ).hide();
	}else{
		$( "#casteStatusHideId" ).hide();
		$( "#casteStatusShowId" ).show();
	}
	$("#casteBodyDiv").collapse('toggle');
});

$("#statusCountsMainDivId").collapse('hide');
$(document).on("click","#StatusCountsDivId",function(){
	var isVisible = $( "#statusCountsHideId" ).is( ":visible" );
	if(isVisible==false){
		 $( "#statusCountsHideId" ).show();
		 $( "#statusCountsShowId" ).hide();
	}else{
		$( "#statusCountsHideId" ).hide();
		$( "#statusCountsShowId" ).show();
	}
	$("#statusCountsMainDivId").collapse('toggle');
});

$("#candidateAppointmentBodyId").collapse('hide');
$(document).on("click","#candidateAppointmentId",function(){
	var isVisible = $( "#candidateAppointmentHideId" ).is( ":visible" );
	if(isVisible==false){
		 $( "#candidateAppointmentHideId" ).show();
		 $( "#candidateAppointmentShowId" ).hide();
	}else{
		$( "#candidateAppointmentHideId" ).hide();
		$( "#candidateAppointmentShowId" ).show();
	}
	$("#candidateAppointmentBodyId").collapse('toggle');
});
function getConductedPartyMeetingDetails(divId,searchTypeStr,isFirst,firstRecord)
{
	$("#paginationId").hide();
	/*
		<input type="hidden" value="" id="cadreLevel" />
		<input type="hidden" value="" id="cadreCommitteeType" />
		<input type="hidden" value="" id="publicRepresentativeTypId" />
	*/
	if(searchTypeStr != null && searchTypeStr.trim().length==0)
	{
		var  status = $('#cadreLevel').val();
		//console.log("srishailam : "+status);
		if(status != null && status.length>0)
		{
			if(status == 'district' || status == 'District' )
			{																
				searchTypeStr='district';
				$('#district').addClass('active');
				$('#districttabs').addClass('active');
				divId = "districttabs";
			}
			else if(status == 'constituency' || status == 'Constituency' )
			{																
				searchTypeStr='constituency';	
				$('#constituency').addClass('active');
				$('#constabs').addClass('active');
				divId = "constabs";			
			}
			else if( status == 'mandal' || status == 'Mandal' || status == 'town' || status == 'Town'  || status == 'division' || status == 'Division'  )
			{																
				searchTypeStr='MandalORTownORDivision';
				$('#MandalORTownORDivision').addClass('active');
				$('#mandaltabs').addClass('active');
				divId = "mandaltabs";
			}
			else if(status == 'ward' || status == 'Ward' || status == 'village' || status == 'Village'  ){
				searchTypeStr='VillageORWard';
				$('#VillageORWard').addClass('active');
				$('#villagetabs').addClass('active');
				divId = "villagetabs";
			}
		}
		else
		{
			//var  status = $('#publicRepresentativeTypId').val();
			//console.log("srishailam111 : "+status);
				searchTypeStr='constituency';	
				//$('#constituency').addClass('active');
				$('#constabs').addClass('active');
				divId = "constabs";	
		}
		
	}
				
	//$('#'+divId+'').html('<center><img style="width: 50px; height: 50px;margin-top:50px" src="images/icons/loading.gif" id="dataLoadingsImgForNewsId"/></center>');
	setTimeout(function(){
	var startDate = $('.meetingSubmitBtn').parent().find('.dp_startDate').val();
	var endDate = $('.meetingSubmitBtn').parent().find('.dp_endDate').val();
	
	var searchType ='';
	$('.meetingCls').each(function(){
		var tabDivId = $(this).attr('id');		
		var isActive =  $('#'+tabDivId+'').hasClass('active');
		//console.log(isActive);
		if(isActive)
		{
			searchTypeStr = tabDivId;
			divId =  $('#'+tabDivId+'').attr('key');
		}			
	});
$('#'+divId+'').html('<center><img style="width: 50px; height: 50px;margin-top:50px" src="images/icons/loading.gif" id="dataLoadingsImgForNewsId"/></center>');
	var committeeLevelId = 0;
	var committeeLevelValue = 0;
	var meetingLevel ="Village/Ward";
	var isMandal = false;
	if(searchTypeStr =='state')
	{
		committeeLevelId = 10;
		committeeLevelValue = $('#cadreStateId').val();
		meetingLevel ="State";
	}
	else if(searchTypeStr =='district')
	{
		committeeLevelId = 11;
		committeeLevelValue = $('#cadreDistrictId').val();
		 meetingLevel ="District";
	}
	else if(searchTypeStr =='constituency')
	{
		committeeLevelId = 4 ;
		committeeLevelValue = $('#cadreConstituencyId').val();
		 meetingLevel ="Constituency";
	}
	else if(searchTypeStr =='MandalORTownORDivision')
	{
		committeeLevelId = 9;
		committeeLevelValue = $('#cadreRuralORUrbanId').val();
		if(committeeLevelValue > 0 )
		{
			if(committeeLevelValue != 1110  && committeeLevelValue != 124)
			{
				committeeLevelId = 7;
				committeeLevelValue = $('#cadremandalId').val();
			}
		}
		else{
			isMandal = true;
			committeeLevelId = 5;
			committeeLevelValue = $('#cadremandalId').val();
		}
		 meetingLevel ="Mandal/Town/Division";
	}
	else if(searchTypeStr =='VillageORWard')
	{
		committeeLevelId = 6;
		committeeLevelValue = $('#cadrePanchaytId').val();
		var wardId = $('#cadreWardId').val();
		if(wardId >0){
			committeeLevelId = 8;
			committeeLevelValue = wardId;
		}
		
		if(isMandal)
		{
			committeeLevelId = 8;
		}
		 meetingLevel ="Village/Ward";
	}
	
	$('#meetingDatePicker').val(startDate+" to "+endDate);
	var jsObj={
			tdpCadreId:globalCadreId,
			searchTypeStr:searchTypeStr,
			committeeLevelId:committeeLevelId,
			committeeLevelValue:committeeLevelValue,
			formDateStr:startDate,
			toDateStr :endDate,
			isFirst:"true",
			firstRecord:firstRecord,
			maxResult:12
		}	
		$.ajax({
				type:'GET',
				 url: 'getAreaWiseConductedPartyMeetingDetailsForCadre.action',
				 data : {task:JSON.stringify(jsObj)} ,
				}).done(function(result){
					if(result !=null){
						buildConductedMeetingDetails(divId,result,meetingLevel,searchTypeStr,firstRecord);
					}
			});
	
	},1000);
}
function buildConductedMeetingDetails(divId,result,meetingLevel,searchTypeStr,firstRecord)
{
		var str='';
		if(result != null){
		str+='<table class="table table-condensed" style="border:1px solid #ddd;margin-top:10px;">';
		str+='<tr>';
		str+='<td class="pad_10" style="background-color:#CCCCCC;">';
		str+=''+meetingLevel+' Level Meeting Details';
		str+='</td>';
		str+='<td  class="pad_10" style="border-left:1px solid #ddd;background-color:#CCCCCC;">';
		str+='<ul class="list-inline m_0">';
		str+='<li>';
		str+='Total Planned Meetings';
		str+='</li>';
		str+='<li>';
		if(result.totalCount != null)
			str+=''+result.totalCount+'';
		else 
			str+='0';
		str+='</li>';
		str+='</ul>';
		str+='</td>';
		str+='<td  class="pad_10" style="border-left:1px solid #ddd;background-color:#CCCCCC;">';
			str+='<ul class="list-inline m_0">';
				str+='<li>';
					str+='Total Conducted Meetings';
				str+='</li>';
				str+='<li>';
					if(result.actualCount != null)
						str+=''+result.actualCount+'';
					else 
						str+='0';
				str+='</li>';
			str+='</ul>';
		str+='</td>';
		str+='</tr>';
		if(result.meetingTrackingVOList != null && result.meetingTrackingVOList.length>0)
		{
			for(var i in result.meetingTrackingVOList)
			{
				str+='<tr>';
				str+='<td>'+result.meetingTrackingVOList[i].monthName+'</td>';
				str+='<td>'+result.meetingTrackingVOList[i].totalCount+'</td>';
				str+='<td>'+result.meetingTrackingVOList[i].actualCount+'</td>';
				str+='</tr>';
			}		
		}
		
		if(result.totalCount >12)
		{
			str+='</tr>';
			str+='<td colspan="3" style="background-color:#ccc">';
			str+='<p class="m_0 text-center">Showing Last 12 Months Details<br/></p>';
			//str+='<p class="m_0 text-center">Showing Last 6 Months Details<br/><a href="javascript:{getConductedPartyMeetingDetails(\''+divId+'\',\''+searchTypeStr+'\',\'false\',\'0\')}"></a></p>';
			str+='</td>';
			str+='</tr>';
		}
			
		
		str+='</table>';
		
	}
	
	$('#'+divId+'').html(str);
	$("#paginationId").show();
	if(firstRecord == 0){
		var totalCount = result.totalCount;
        $("#paginationId").pagination({
          items: totalCount,
          itemsOnPage: 12,
          cssStyle: 'light-theme',
          hrefTextPrefix: '#pages-',
          onPageClick: function(pageNumber) { 
            var firstRecord=(pageNumber-1)*12;
			  getConductedPartyMeetingDetails(divId,searchTypeStr,"true",firstRecord);
               
          }
          
        });
	}
		
}
</script>
<script>
var tableToExcel = (function() {
	var uri = 'data:application/vnd.ms-excel;base64,'
	, template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--></head><body><table>{table}</table></body></html>'
	, base64 = function(s) { return window.btoa(unescape(encodeURIComponent(s))) }
	, format = function(s, c) { return s.replace(/{(\w+)}/g, function(m, p) { return c[p]; }) }
  return function(table, name) {
	if (!table.nodeType) table = document.getElementById(table)
	var ctx = {worksheet: name || 'Worksheet', table: table.innerHTML}
	window.location.href = uri + base64(format(template, ctx))
  }
})()
function generateExcel()
{
 tableToExcel('deathHospLifeCycleTableId', 'Death And Hospitalization Insurance Details Report');
}
function generateExcel1(){
	 tableToExcel('grievanceStatusWiseTableId', 'Grievance Request Status Details Report');
}
$(document).on("click",".notesClass",function(){
	 $("#errorUpCallId").html("");
	$("#notesModalDivId").modal('show');
	getCadreNotesInformationDetails(0);
	var name = $("#nameId").html();
	var notesName = name.toUpperCase(); 
	$("#addModalId").html(" NOTES ON "+notesName);
	$(".jqte_editor").html('');
	$("#updateButnId").hide();
});
//swadhin
function buildReport()
{
	
	var str = '';
	var flag = false;
	<c:if test="${fn:length(cadreReportVOList) gt 0}">  
	flag = true;
	str +='<table id="reportTableId" class="table table-bordered">';
		str +='<thead>';
			str +='<th>REPORT TYPE</th>';
			str +='<th>PREFERABLE STATUS</th>';  
			str +='<th>REPORT DATE</th>';
		str +='</thead>';  
		str +='<tbody>';
			<c:forEach items="${cadreReportVOList}" var="cadreReportVO" varStatus="loop1">
				<c:forEach items="${cadreReportVO.reportVOList}" var="ReportVO" varStatus="loop2">
					str +='<tr>';     
					str +='<td><span filePath="${ReportVO.reportPath}" style="cursor:pointer;" class="showPdfCls" >${ReportVO.reportType}</span></td>'; 
					str +='<td><span filePath="${ReportVO.reportPath}" style="cursor:pointer;"  class="showPdfCls" id="showPdfId" >${ReportVO.status}</span></td>'; 
					str +='<td><span filePath="${ReportVO.reportPath}" style="cursor:pointer;"  class="showPdfCls" >${ReportVO.insertedTime}</span></td>';  
					str +='</tr>';    
				</c:forEach>         
			</c:forEach>
		str +='</tbody>';
	str +='</table>';    
	</c:if>
	if(flag == true)
		{
			$("#reportsInfoId").html('<i class="glyphicon glyphicon-list-alt remove-icon"  data-placement="bottom" style="margin-right: 3px;cursor:pointer;color:green;" id="reportsId" title="Click Here To Get Reports Detail" data-toggle="modal" data-target="#reportModelId"></i>');
			$("#reportDetailsId").html(str);
			$("#reportTableId").dataTable(); 
		}
		else
		{
			$("#reportsInfoId").html('<i class="glyphicon glyphicon-list-alt remove-icon"  data-toggle="tooltip" data-placement="bottom" style="margin-right: 3px;cursor:pointer;color:gray;" id="reportsId" title="No Reports are available" ></i>');
		}
}

function buildCadreHealthReport()
{
	var str = '';
	var flag = false;
	<c:if test="${fn:length(cadreReportHealthVOList) gt 0}">  
	flag = true;
	str +='<div class="row">';
    str +='<div class="col-md-8 col-md-offset-2 col-xs-12 col-sm-10 col-sm-offset-0" style="width:80%; margin-left: 110px;">';
	str +='<table id="reportHealthTableId" class="table table-bordered">';
	str +='<thead>';
	str +='<th>ENROLLMENT YEAR</th>';
	str +='<th>SNO</th>';
	str +='<th>REPORT DATE</th>';     
	str +='</thead>';  
	
	str +='<tbody>';
	<c:forEach items="${cadreReportHealthVOList}" var="cadreReportVO" varStatus="loop1">
	str +='<tr>';
	str +='<td>2014-2016</td>'; 
	str +='<td>${cadreReportVO.sno}</td>';					
	str +='<td><span filePath="${cadreReportVO.reportPath}" style="cursor:pointer;" id="showPdfId1" class="showPdfCls3">${cadreReportVO.reportDate}</span></td>';  
	str +='</tr>';    
	</c:forEach>
	str +='</tbody>';                
	str +='</table>';    
	</c:if>
	str +='</div>';
   str +='</div>';
	$("#reportDetailsId1").html(str);
			$("#reportHealthTableId").dataTable(); 
	if(flag == true)
		{
			$("#reportsInfoId1").html('<i class="fa fa-heartbeat text-success"  data-placement="bottom" style="margin-left: 5px;cursor:pointer;color:green;" id="reportsId1" title="Click Here To Get Health Reports" data-toggle="modal" data-target="#reportModelId1"></i>');	
		}
		else
		{
			$("#reportsInfoId1").html('<i class="fa fa-heartbeat text-success"  data-toggle="tooltip" data-placement="bottom" style="margin-left: 5px;cursor:pointer;color:gray;" id="reportsId1" title="Health Reports are not available" ></i>');
		}
}
</script>
<script>
//getting Dynamic Browser URL
var windowUrl = window.location.href;
var wurl = windowUrl.substr(0,(windowUrl.indexOf("/cadreDetailsAction")));
wurl = wurl.replace("/PartyAnalyst","");

function openPdf(aravind){	
	window.open(aravind);
}

$(document).on('click','.showPdfCls',function(){
		$("#pdfReportDetailsId").html('');
	var str = '';
	var filePath = $(this).attr("filePath");
	if((navigator.userAgent.match(/iPhone/i)) ||  (navigator.userAgent.match(/iPad/i))) {
		$("#pdfModelId").modal("hide");
		window.open(wurl+'/'+filePath+'','toolbar=0,location=0, directories=0, status=0, menubar=0,title=Cadre Reports');
	
		/*setTimeout(function(){
		$(w.document).find('html').append('<head><title>your title</title></head>');}, 2000); */
		//w.onload = function() { this.document.title = "your new title"; }
		

	}else{
		
		$("#pdfModelId").modal("show");
		str += '<iframe src="'+wurl+'/'+filePath+'" width="100%" height="800">';    
		str += '</iframe>';
		$("#pdfReportDetailsId").html(str);
	}
	
}); 

$(document).on('click','.showPdfCls1',function(){  
     
	var str = '';
	
	var filePath = $(this).attr("filePath");
	if((navigator.userAgent.match(/iPhone/i)) ||  (navigator.userAgent.match(/iPad/i))) {
		$("#pdfModelId").modal("hide");
		window.open(wurl+'/Grievance/complaintScannedCopy'+filePath+'','toolbar=0,location=0, directories=0, status=0, menubar=0,title=Cadre Reports');
	}else{
		$("#pdfModelId").modal("show");
		str += '<iframe src="'+wurl+'/Grievance/complaintScannedCopy'+filePath+'" width="100%" height="800">';    
		str += '</iframe>';
		$("#pdfReportDetailsId").html(str);
	}
	
});  

$(document).on('click','.showPdfCls2',function(){  
     
	var str = '';
	
	var filePath = $(this).attr("filePath");
	if((navigator.userAgent.match(/iPhone/i)) ||  (navigator.userAgent.match(/iPad/i))) {
		$("#pdfModelId").modal("hide");
		window.open(wurl+'/'+filePath+'','toolbar=0,location=0, directories=0, status=0, menubar=0,title=Cadre Reports');
	}else{
		$("#pdfModelId").modal("show");
		str += '<iframe src="'+wurl+'/nominated_post_documents/'+filePath+'" width="100%" height="800">';    
		str += '</iframe>';
		$("#pdfReportDetailsId").html(str);
	}
	
}); 

//party meetings.
$(document).on("click",".partyMeetingsCollapse",function(){
	$(".partyMeetingsCollapseBody").toggle();
	$(this).find('.glyphicon').toggleClass('glyphicon-chevron-down').toggleClass('glyphicon-chevron-up');
});

$(document).on('click','.showPdfCls3',function(){        
	var str = '';
	var filePath = $("#showPdfId1").attr("filePath");

	if((navigator.userAgent.match(/iPhone/i)) ||  (navigator.userAgent.match(/iPad/i))) {
		$("#healthReportPdfModelId").modal("hide");
		window.open(wurl+'/'+filePath+'','toolbar=0,location=0, directories=0, status=0, menubar=0,title=Cadre Reports');
	
		/*setTimeout(function(){
		$(w.document).find('html').append('<head><title>your title</title></head>');}, 2000); */
		//w.onload = function() { this.document.title = "your new title"; }
		

	}else{
		
		$("#healthReportPdfModelId").modal("show");
		str += '<iframe src="'+wurl+'/'+filePath+'" width="100%" height="800">';    
		str += '</iframe>';
		$("#healthReportPdfDetailsId").html(str);
	}
	
}); 

function getCandidateAppliedPostsByCadre(value){
	if(value == 1){
		$("#viewDetilsId").show();
	}
		var jsObj={
				globalCadreId :globalCadreId,
				searchType:"Cadre",
				nominateCandId:0
		}
		$.ajax({
			type:"POST",
			url :"getCandidateAppliedPostsByCadreAction.action",
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
		if(value == 0){
		   if(result != null){
			    nominatedPostBuilingDetails(result);
		   } 
		}else{
			$("#viewDetilsId").hide();
			buildCandidateAppliedPostByCadreDetails(result);
		}
   });	
  }
function getMoreApplicationDetails(){
	$("#nmtedMdlId").html('');
	$("#nominatedModalDivId").modal('show');
	getCandidateAppliedPostsByCadre(1);
}

$("#alertBodyId").collapse('hide');
$(document).on("click","#alertHeaderId",function(){
	var isVisible = $( "#alertProfileHideId" ).is( ":visible" );
	if(isVisible==false){
		 $( "#alertProfileHideId" ).show();
		 $( "#alertProfileShowId" ).hide();
	}else{
		$( "#alertProfileHideId" ).hide();
		$( "#alertProfileShowId" ).show();
	}
	$("#alertBodyId").collapse('toggle');
});

$(document).on("click",".invitedDetlsDiv",function(){
						$("#meetingParticipatingDivId").modal("show");
						$("#meetingParticipatingDivModalBodyId").html('');
						var divId = $(this).attr('name');
						var meetingName = $(this).attr('attr_name');
						var meetingTypeId = $(this).attr('key');
						
						$("#meetingHedingSpanId").html(''+meetingName+" - EVENT PARTICIPATION DETAILS");
						
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
									{
									str+='<table class="table table-bordered" style="margin: 10px">';
										str+='<thead>';
										str+='<tr>';
										//str+='<th style=""> LOCATION </th>';
										str+='<th style=""> MEETING NAME  </th>';
										str+='<th style=""> START DATE</th>';
										str+='<th style=""> END DATE</th>';
										str+='<th style=""> ATTENDED STATUS </th>';
										str+='<th style=""> REMARKS </th>';
										str+='</tr>';
										str+='<thead>';
										str+='<tbody>';
										if(result.partyMeetingVOList != null && result.partyMeetingVOList.length>0)
										{
											for(var k in result.partyMeetingVOList)
											{
												if(result.partyMeetingVOList[k].name != null ){
													str+='<tr>';
													//str+='<td>'+result.partyMeetingVOList[k].location+'</td>';
													str+='<td>'+result.partyMeetingVOList[k].name+'</td>';										
													str+='<td>'+result.partyMeetingVOList[k].startDateStr+'</td>';
													str+='<td>'+result.partyMeetingVOList[k].endDateStr+'</td>';
													if(result.partyMeetingVOList[k].attendedCount != null && result.partyMeetingVOList[k].attendedCount>0){	
														//str+='<td> ATTENDED </td>';
														str+='<td> ATTENDED <br>';
														if(result.partyMeetingVOList[k].sessionList != null && result.partyMeetingVOList[k].sessionList.length>0){
															str+='<ul class="sessionInfoUl">';
															for(var s in result.partyMeetingVOList[k].sessionList){
																str+=' <li>'+result.partyMeetingVOList[k].sessionList[s].name+'  <span title="  Attended Time ">  ';
																if(result.partyMeetingVOList[k].sessionList[s].isLate != null && result.partyMeetingVOList[k].sessionList[s].isLate =="true")
																	str+='<span title="  Attended Time " style="color:red;"> - '+result.partyMeetingVOList[k].sessionList[s].attendedTime+' </span></li>';
																else if(result.partyMeetingVOList[k].sessionList[s].isLate != null && result.partyMeetingVOList[k].sessionList[s].isLate =="false"){
																	if(result.partyMeetingVOList[k].sessionList[s].requiredName =='Absent')
																		str+='<span style="color:red;"> - '+result.partyMeetingVOList[k].sessionList[s].requiredName+' </span></li>';
																	else
																		str+='<span title="  Attended Time "  style="color:Green;"> - '+result.partyMeetingVOList[k].sessionList[s].attendedTime+' </span></li>';
																}else
																		str+='<span title="  Attended Time "  > - '+result.partyMeetingVOList[k].sessionList[s].attendedTime+' </span></li>';
																
															}
															str+='</ul>';
														}
														str+='</td>';
													}else
														str+='<td> NOT ATTENDED </td>';
													if(result.partyMeetingVOList[k].request.length>0)
														str+='<td>'+result.partyMeetingVOList[k].request+' </td>';
													else
														str+='<td> - </td>';
													str+='</tr>';
												}												
											}
										}
										str+='</tbody>';
										str+='</table>';
										$("#meetingParticipatingDivModalBodyId").html(str);
										
									//$('#'+divId+'').html(str);
									}
								});
					});
					
getCandidateAppliedPostsByCadre(0);
//buildToursDesignationslist();
/*Month & Year Picker*/
$("#toursDatePicker").datetimepicker({format:'MM-YYYY'});
$('#toursDatePicker').val(moment().subtract(1, 'month').format('MM-YYYY'));
//srujana
	$(document).on('click','.showPdfCls5',function(){  
     
	var str = '';
	
	var filePath = $(this).attr("filePath");
	if((navigator.userAgent.match(/iPhone/i)) ||  (navigator.userAgent.match(/iPad/i))) {
		$("#pdfModelId1").modal("hide");
		//window.open(wurl+'/'+filePath+'','toolbar=0,location=0, directories=0, status=0, menubar=0,title=Cadre Reports');
		window.open(filePath+'','toolbar=0,location=0, directories=0, status=0, menubar=0,title=Cadre Reports');
	}else{
		$("#pdfModelId1").modal("show");
		//str += '<iframe src="'+wurl+'/nominated_post_documents/'+filePath+'" width="100%" height="800">';
		//str += '<iframe src="'+wurl+'/GO_documents/'+filePath+'" width="100%" height="800">';
		//str += '<iframe src="GO_documents/'+filePath+'" width="100%" height="800">';
        str += '<iframe src="'+filePath+'" width="100%" height="800">';		
		str += '</iframe>';
		$("#documentDetailsId").html(str);
		
	}
	
}); 

//getHealthDetails(memberCadreId);

function getHealthDetails(cadreId){
	var jsObj={
		tdpCadreId :10034186
	}
	$.ajax({
		type:"POST",
		url :"getTdpCadreHealthDetailsByCadreAction.action",
		dataType: 'json',
		data: {task:JSON.stringify(jsObj)}
	}).done(function(result){
		
	});
}
$(document).on("click",".surveyQuestionsCls",function(){
	$("#surveyQuestionDetailsDivId").modal('show');
	getSurveyQuestionWithMarksDetailsByTDpCadreId();
	getSurveyQuestionDetails();
});
</script>
</body>
</html>