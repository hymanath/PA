<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags"%>
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
<style>
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
            	<table class="table m_0 table-bordered">
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
                    <tr>
                    	<td class="text-bold"><i class="icon-articles"></i> IDENTITY</td>
                    </tr>
                    <tr>
                    	<td>
                        	<p class="m_0">MEMBERSHIP ID : <span id="memberShipNoId"></span></p>
                            <p class="m_0">VOTER CARD NO : <span id="voterIdSpan"></span></p>
                            <p class="m_0">PARTY POSITION : <span id="positionId"></span></p>
                            <p class="m_0">PUBLIC REPRESENTATIVE : <span id="representativeId"></span></p>
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
            	<div class="panel panel-default">
                	<div class="panel-heading">
                    	<h4 class="panel-title text-bold"><i class="glyphicon glyphicon-hand-right"></i> GRIEVANCE DETAILS</h4>
                    </div>
					<!-- <center><strong>Grievance Details Not Available.</strong></center> -->
                    <div class="panel-body">
                    	<h4 class="m_0">TOTAL COMPLAINTS <span id="totalComplaintsId" class="text-bold"></span></h4>
                        <div class="display-style">
                            <div id="donutchart" class="display-style" style="height: 120px;float:left;width:150px;"></div>
                            <ul class="display-style pull-right graph-list" style="padding-right:20px;padding-left:0px;" id="complaintStatusUL">
                            </ul>
                        </div>
                    </div> 
                </div>
            	<div class="panel panel-default">
                	<div class="panel-heading">
                    	<h4 class="panel-title text-bold"><i class="glyphicon glyphicon-usd"></i> FINANCE SUPPORT</h4>
                    </div>
					 <div class="panel-body">
                   		<h4 id="headingId"></h4>
                    	<div id="donutchart2" class="display-style" style="height: 120px;float:left;width:90px;"></div>
                    <ul class="display-style pull-right piechart-list pad_0" id="financeSupportUL">
                        
                    </ul>
                    </div>
                </div>
                <div class="panel panel-default">
                	<div class="panel-heading">
                    	<h4 class="panel-title text-bold"><i class="glyphicon glyphicon-flash"></i> DEATHS AND HOSPITALIZATION</h4>
                    </div>
					<center><strong>Deaths And Hospitalization Details Not Available.</strong></center>
					<!--<div class="panel-body pad_0">
                    	<table class="table m_0 table-bordered m_0">
                        	<tr>
                            	<td>Location</td>
                                <td>Death</td>
                                <td>Hospitalization</td>
                            </tr>
                            <tr>
                            	<td>District</td>
                                <td>10</td>
                                <td>07</td>
                            </tr>
                            <tr>
                            	<td>Parliament Constituency</td>
                                <td>02</td>
                                <td>15</td>
                            </tr>
                            <tr>
                            	<td>Assembly Constituency</td>
                                <td>08</td>
                                <td>12</td>
                            </tr>
                            <tr>
                            	<td>Mandal Deaths</td>
                                <td>06</td>
                                <td>20</td>
                            </tr>
                        </table>
                    </div> -->
                </div>
                <div class="panel panel-default">
                	<div class="panel-heading">
                    	<h4 class="panel-title text-bold">
                        	<img src="dist/img/family-icon.png">&nbsp;&nbsp;&nbsp;FAMILY MEMBERS
                        </h4>
                    </div>
                    <div class="panel-body">
                    	<div class="family-members" id="familyMembersDiv">
                           <!-- <ul>
                                <li>
                                    <div class="media">
                                        <div class="media-left">
                                            <img src="dist/img/family-member.png" class="img-responsive img-circle" alt="profile">
                                        </div>
                                        <div class="media-body">
                                            <p class="m_0">Ramu
                                                <span class="pull-right">
                                                    <i class="glyphicon glyphicon-check"></i>
                                                </span>
                                            </p>
                                            <p class="m_0">Relation : Father</p>
                                            <p class="m_0">Age : 55</p>
                                        </div>
                                    </div>
                                    
                                </li>
                                <li>
                                    <div class="media">
                                        <div class="media-left">
                                            <img src="dist/img/family-member.png" class="img-responsive img-circle" alt="profile">
                                        </div>
                                        <div class="media-body">
                                            <p class="m_0">Geetha
                                                <span class="pull-right">
                                                    <i class="glyphicon glyphicon-check"></i>
                                                </span>
                                            </p>
                                            <p class="m_0">Relation : Mother</p>
                                            <p class="m_0">Age : 55</p>
                                        </div>
                                    </div>
                                    
                                </li>
                                <li>
                                    <div class="media">
                                        <div class="media-left">
                                            <img src="dist/img/family-member.png" class="img-responsive img-circle" alt="profile">
                                        </div>
                                        <div class="media-body">
                                            <p class="m_0">Shekar
                                                <span class="pull-right">
                                                    <i class="glyphicon glyphicon-check"></i>
                                                </span>
                                            </p>
                                            <p class="m_0">Relation : Son</p>
                                            <p class="m_0">Age : 55</p>
                                        </div>
                                    </div>
                                    
                                </li>
                            </ul> -->
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
                           <!-- <div class="panel panel-default">
                            <div class="panel-heading  bg_white" role="tab" id="headingOne">
                              <h4 class="panel-title text-bold">
                                <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                                  2014 PERFORMANCE
                                  <span class="pull-right">
                                  	<i class="glyphicon glyphicon-chevron-up"></i>
                                  </span>
                                </a>
                              </h4>
                            </div>
                            <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
                              <div class="panel-body">
                              	<div class="row">
                                	<div class="col-md-2">
                                    	<div id="myStathalf" data-dimension="100%" data-percent="35" data-fgcolor="#330000" data-bgcolor="#cccccc" data-type="half" ></div>
                                        <p class="m_-40 text-center">Own Booth</p>
                                    </div>
                                    <div class="col-md-2">
                                    	<div id="myStathalf1" data-dimension="100%" data-percent="35" data-fgcolor="#330000" data-bgcolor="#cccccc" data-type="half" ></div>
                                        <p class="m_-40 text-center">Own Panchayat</p>
                                    </div>
                                    <div class="col-md-2">
                                    	<div id="myStathalf2" data-dimension="100%" data-percent="35" data-fgcolor="#330000" data-bgcolor="#cccccc" data-type="half" ></div>
                                        <p class="m_-40 text-center">Own Mandal</p>
                                    </div>
                                    <div class="col-md-2">
                                    	<div id="myStathalf3" data-dimension="100%" data-percent="35" data-fgcolor="#330000" data-bgcolor="#cccccc" data-type="half" ></div>
                                        <p class="m_-40 text-center">Own Assembly Constituency</p>
                                    </div>
                                    <div class="col-md-2">
                                    	<div id="myStathalf9" data-dimension="100%" data-percent="35" data-fgcolor="#330000" data-bgcolor="#cccccc" data-type="half" ></div>
                                        <p class="m_-40 text-center">Own Parliament Constituency</p>
                                    </div>
                                    <div class="col-md-2">
                                    	<div id="myStathalf10" data-dimension="100%" data-percent="35" data-fgcolor="#330000" data-bgcolor="#cccccc" data-type="half" ></div>
                                        <p class="m_-40 text-center">Own District</p>
                                    </div>
                                </div>
                              </div>
                            </div>
                          </div>
                          <div class="panel panel-default">
                            <div class="panel-heading bg_white" role="tab" id="headingTwo">
                              <h4 class="panel-title text-bold">
                                <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                                  2009 PERFORMANCE
                                  <span class="pull-right">
                                  	<i class="glyphicon glyphicon-chevron-down"></i>
                                  </span>
                                </a>
                              </h4>
                            </div>
                            <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
                              <div class="panel-body">
                                <div class="row">
                                	<div class="col-md-2">
                                    	<div id="myStathalf5" data-dimension="100%" data-percent="35" data-fgcolor="#330000" data-bgcolor="#cccccc" data-type="half" ></div>
                                       <p class="m_-40 text-center">Own Booth</p>
                                    </div>
                                    <div class="col-md-2">
                                    	<div id="myStathalf6" data-dimension="100%" data-percent="35" data-fgcolor="#330000" data-bgcolor="#cccccc" data-type="half" ></div>
                                        <p class="m_-40 text-center">Own Panchayat</p>
                                    </div>
                                    <div class="col-md-2">
                                    	<div id="myStathalf7" data-dimension="100%" data-percent="35" data-fgcolor="#330000" data-bgcolor="#cccccc" data-type="half" ></div>
                                        <p class="m_-40 text-center">Own Mandal</p>
                                    </div>
                                    <div class="col-md-2">
                                    	<div id="myStathalf8" data-dimension="100%" data-percent="35" data-fgcolor="#330000" data-bgcolor="#cccccc" data-type="half" ></div>
                                        <p class="m_-40 text-center">Own Assembly Constituency</p>
                                    </div>
                                    <div class="col-md-2">
                                    	<div id="myStathalf11" data-dimension="100%" data-percent="35" data-fgcolor="#330000" data-bgcolor="#cccccc" data-type="half" ></div>
                                        <p class="m_-40 text-center">Own Parliament Constituency</p>
                                    </div>
                                    <div class="col-md-2">
                                    	<div id="myStathalf12" data-dimension="100%" data-percent="35" data-fgcolor="#330000" data-bgcolor="#cccccc" data-type="half" ></div>
                                        <p class="m_-40 text-center">Own District</p>
                                    </div>
                                </div>
                              </div>
                            </div>
                          </div> -->
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                	<div class="panel-heading">
                    	<h4 class="panel-title text-bold"><i class="glyphicon glyphicon-stats"></i>&nbsp;&nbsp;&nbsp;2014 CADRE ENROLMENT STATS</h4>
                    </div>
                    <div class="panel-body">
                    	<div class="row" id="memberShipCountDiv"><!--id="memberShipCountDiv"-->
						   <!--<div class="col-md-12 col-xs-12 col-sm-12">
								<div id="memberShipCountDiv" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
						   </div>
                            <div class="col-md-2">
                                <div id="myStathalf13" data-dimension="100%" data-percent="35" data-fgcolor="#330000" data-bgcolor="#cccccc" data-type="half" ></div>
                            </div>
                            <div class="col-md-2">
                                <div id="myStathalf14" data-dimension="100%" data-percent="35" data-fgcolor="#330000" data-bgcolor="#cccccc" data-type="half" ></div>
                            </div>
                            <div class="col-md-2">
                                <div id="myStathalf15" data-dimension="100%" data-percent="35" data-fgcolor="#330000" data-bgcolor="#cccccc" data-type="half" ></div>
                            </div>
                            <div class="col-md-2">
                                <div id="myStathalf16" data-dimension="100%" data-percent="35" data-fgcolor="#330000" data-bgcolor="#cccccc" data-type="half" ></div>
                            </div>
                            <div class="col-md-2">
                                <div id="myStathalf17" data-dimension="100%" data-percent="35" data-fgcolor="#330000" data-bgcolor="#cccccc" data-type="half" ></div>
                            </div>
                            <div class="col-md-2">
                                <div id="myStathalf18" data-dimension="100%" data-percent="35" data-fgcolor="#330000" data-bgcolor="#cccccc" data-type="half" ></div>
                            </div> -->
                        </div>
                    </div>
				
                </div>
                <div class="panel panel-default">
                	<div class="panel-heading">
                    	<h4 class="panel-title text-bold"><i class="glyphicon glyphicon-record"></i>&nbsp;&nbsp;&nbsp;&nbsp;EVENT PARTICIPATION ACTIVITIES & TRAINING DETAILS</h4>
                    </div>
                    <div class="panel-body">
                    	<div class="row">
                        	<div class="col-md-6 col-xs-12">
                            	<table class="table m_0 table-bordered">
                                	<thead >
                                    	<th class="text-center" colspan="3">EVENT INVITATIONS</th>
                                    </thead>
                                    <tr class="text-center">
                                    	<td>0<br/>Sent</td>
                                        <td>0<br/>Participated</td>
                                        <td>0<br/>Absent</td>
                                    </tr>
                                </table>
                            </div>
                            <div class="col-md-6 col-xs-12">
                            	<table class="table m_0 table-bordered">
                                	<thead>
                                    	<th class="text-center" colspan="3">TRAINING'S</th>
                                    </thead>
                                    <tr class="text-center">
                                    	<td>0<br/>Suggested</td>
                                        <td>0<br/>Attended</td>
                                        <td>0<br/>Absent</td>
                                    </tr>
                                </table>
                            </div>
                            <div class="col-md-12 col-xs-12" id="participationTableMainDivId" style="display:none;">
								<h4 style="border-bottom:1px solid #999">Event Participation Details</h4>
								<div id="participationTableDivId">
								</div>
							
                            	<!--<table class="table m_0-bordered table">
                                    <thead>
                                        <tr>
                                            <th colspan="3" class="text-center">EVENT PARTICIPATION DETAILS</th>
                                        </tr>
                                        <tr>
                                            <th>MAIN EVENT</th>
                                            <th>SUB EVENT</th>
                                            <th>ATTENDED COUNT(DAYS)</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    	<tr>
                                        	<td rowspan="3">Party Office</td>
                                            <td>Main Event</td>
                                            <td>1</td>
                                        </tr>
                                        <tr>
                                        	<td>Food</td>
                                            <td>2</td>
                                        </tr>
                                        <tr>
                                        	<td>Exit</td>
                                            <td>1</td>
                                        </tr>
                                        <tr>
                                        	<td>MAHANADU 2015</td>
                                            <td>Main Entry</td>
                                            <td>1</td>
                                        </tr>
                                    </tbody>
                                </table>  -->
                            </div>
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
                        	<!--<ul>
                            	<li>Won in 2014 election with 62.59 votes gain for TELUGU DESAM party in kuppam consituency</li>
                                <li>Won in 2014 election with 62.59 votes gain for TELUGU DESAM party in kuppam consituency</li>
                                <li>Won in 2014 election with 62.59 votes gain for TELUGU DESAM party in kuppam consituency</li>
                                <li>Won in 2014 election with 62.59 votes gain for TELUGU DESAM party in kuppam consituency</li>
                                <li>Won in 2014 election with 62.59 votes gain for TELUGU DESAM party in kuppam consituency</li>
                                <li>Won in 2014 election with 62.59 votes gain for TELUGU DESAM party in kuppam consituency</li>
                            </ul>-->
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                	<div class="panel-heading">
                    	<h4 class="panel-title text-bold" style="display:inline-block;"><i class="glyphicon glyphicon-list-alt"></i>&nbsp;&nbsp;&nbsp;NEWS</h4>
						<div class="pull-right">
							<div class="calendar-style"  id="reportrange">
								<div class="caret"></div>
								<i class="glyphicon glyphicon-calendar"></i>
							</div>
						</div>
					</div>
					
					<div class="panel-body" id="newsMainDivId">
                    	<!--<div>
							
							<label class="radio-inline">
                            	<input type="radio" name="radio" id="districtRadioNewsId" class="newsRadioCls">District
                            </label>
							<label class="radio-inline">
                            	<input type="radio" name="radio" id="pConstiRadioNewsId" class="newsRadioCls">Parliament Constituency
                            </label>
							<label class="radio-inline">
                            	<input type="radio" name="radio" id="aConstiRadioNewsId" class="newsRadioCls">Assembly Constituency
                            </label>
							<label class="radio-inline hidingMandalCls" style="display:none;">
                            	<input type="radio" name="radio" id="mandalRadioNewsId" class="newsRadioCls">Mandal
                            </label>
                        	<label class="radio-inline hidingMandalCls" style="display:none;">
                            	<input type="radio" name="radio" id="panchayatRadioNewsId" class="newsRadioCls">Panchayat
                            </label>
                            
                            <div class="pull-right">
                            	<div class="calendar-style"  id="reportrange">
                                	<div class="caret"></div>
                                	<i class="glyphicon glyphicon-calendar"></i>
                                </div>
                            </div>
                        </div> 
								<div class="pull-right">
									<div class="calendar-style"  id="reportrange" style="margin-top:-16px;">
										<div class="caret"></div>
										<i class="glyphicon glyphicon-calendar"></i>
									</div>
								</div>-->
						
					<center><img style="width: 100px; height: 100px;margin-top:50px" src="images/icons/loading.gif" id="dataLoadingsImgForNewsId"/></center>
					<div id="hideShowNewsDiv" style="display:none;">
                        <div class="panel panel-default">
                        	<div class="panel-heading bg_f9">
                            	<h4 class="panel-title text-bold text-center">
                                	CANDIDATE CATEGORY WISE
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
											<label class="radio-inline">
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
										<div class="row" id="propertiesId"></div>
									</div>
									
                                	
                                    
                                </div>
								<!--<div class="row" id="issuesMainDiv">
									<div class="col-md-6 col-xs-12 col-md-offset-3">
                                    	<div class="panel panel-default m_0">
                                        	<div class="panel-heading bg_f9">
                                            	<h4 class="panel-title text-bold">ISSUES<span class="pull-right" id="issuesCount">TOTAL COUNT - 0</span></h4>
                                            </div>
                                            <div class="panel-body pad_0">
                                            	<table class="table m_0 m_0" id="issuesSummary">
                                                	
                                                </table>
                                            </div>
                                        </div>
                                    </div>
								</div>-->
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
		<div class="row">
        	<div class="col-md-12 col-xs-12">
            	<div class="panel panel-default">
                	<div class="panel-heading">
                    	<h4 class="panel-title">
                        	<i class="glyphicon glyphicon-briefcase"></i>&nbsp;&nbsp;&nbsp;COMMITTEES
                        </h4>
                    </div>
					<!--<center><h3>Data Not Available.</h3></center>-->
                    <div class="panel-body">
                    	<div>
                        	<label class="radio-inline">
                            	<input type="radio">Panchayat
                            </label>
                            <label class="radio-inline">
                            	<input type="radio">Mandal
                            </label>
                            <label class="radio-inline">
                            	<input type="radio">Assembly Constituency
                            </label>
                            <label class="radio-inline">
                            	<input type="radio">Parliament Constituency
                            </label>
                            <label class="radio-inline">
                            	<input type="radio">District
                            </label>
                        </div>
                        <div class="table m_0-responsive m_top10">
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
                    	<h4 class="panel-title text-bold"><i class="glyphicon glyphicon-stats"></i>&nbsp;&nbsp;&nbsp;SURVEY DETAILS</h4>
                    </div>
                    <div class="panel-body">
					<div class="surveyDetailssCls">	</div>
					<div class="surveyDetailsCls">	</div>
                    	<div id="surveyDetailsMainDivId">						
                    	</div>
                    </div>
                </div>
            </div>
        </div>
       <!-- <div class="row">
        	<div class="col-md-12">
            	<div class="panel panel-default">
                	<div class="panel-heading">
                    	<h4 class="panel-title text-bold"><i class="glyphicon glyphicon-credit-card"></i>&nbsp;&nbsp;&nbsp;SURVEY DETAILS</h4>
                    </div>
					<div></div>
					<center><h3>Survey Details Not Available.</h3></center>
                    <div class="panel-body">
                    	<div>
                          <!-- Nav tabs -->
                          <!--<ul class="nav nav-tabs tab-list" role="tablist">
                            <li class="active"><a href="#area" class="text-bold" data-toggle="tab">SURVEYS IN CANDIDATE AREA&nbsp;&nbsp;&nbsp;&nbsp;10</a></li>
                            <li><a href="#participated" class="text-bold" data-toggle="tab">CANDIDATE PARTICIPATED SURVEYS&nbsp;&nbsp;&nbsp;&nbsp;10</a></li>
							<li class="text-danger bg_white">Negative Count : 10</li>
                          </ul>
                        
                          <!-- Tab panes -->
                          <!--<div class="tab-content m_top20">
                            <div role="tabpanel" class="tab-pane active" id="area">
                            	<div class="panel-group" id="accordion1" role="tablist" aria-multiselectable="true">
								   <div class="panel panel-default">
                                    <div id="heading1" role="tab" class="panel-heading bg_f9">
                                      <a aria-controls="collapse1" aria-expanded="true" href="#collapse1" data-parent="#accordion1" data-toggle="collapse" role="button">
	                                      <h4 class="panel-title text-bold">
                                          T7 SA SURVEY FIELD<span class="font-10 text-danger">&nbsp;&nbsp;Negative Count  10</span>
                                          <span class="pull-right"><i class="glyphicon glyphicon-triangle-top"></i></span>
	                                      </h4>
                                      </a>
                                    </div>
                                    <div aria-labelledby="heading1" role="tabpanel" class="panel-collapse collapse in" id="collapse1">
                                      <div class="panel-body pad_0">
                                      	<p class="pad_10 m_0">ప్రథిపక్ష నయకుదు జగన్ ప మి అబిప్రయం</p>
                                        <table class="table">
                                        	<thead>
                                            	<tr><th>Location</th>
                                                <th>No Of Samples</th>
                                                <th>పర్వలెదు</th>
                                                <th>బగుంది</th>
                                                <th>బాగాలెదు</th>
                                                <th>తెలియదు</th>
                                            </tr></thead>
                                            <tbody>
                                            	<tr>
                                                	<td>District</td>
                                                    <td>50</td>
                                                    <td>30%</td>
                                                    <td>20%</td>
                                                    <td>10%</td>
                                                    <td>50%</td>
                                                </tr>
                                                <tr>
                                                	<td>AC</td>
                                                    <td>40</td>
                                                    <td>30%</td>
                                                    <td>20%</td>
                                                    <td>10%</td>
                                                    <td>50%</td>
                                                </tr>
                                                <tr>
                                                	<td>PC</td>
                                                    <td>46</td>
                                                    <td>30%</td>
                                                    <td>20%</td>
                                                    <td>10%</td>
                                                    <td>50%</td>
                                                </tr>
                                                <tr>
                                                	<td>Mandal</td>
                                                    <td>45</td>
                                                    <td>30%</td>
                                                    <td>20%</td>
                                                    <td>10%</td>
                                                    <td>50%</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                      </div>
                                    </div>
                                  </div>
                                   <div class="panel panel-default">
                                    <div id="heading2" role="tab" class="panel-heading bg_f9">
                                      <a aria-controls="collapse2" aria-expanded="false" href="#collapse2" data-parent="#accordion1" data-toggle="collapse" role="button" class="collapsed">
                                      	<h4 class="panel-title text-bold">
                                          T5 SA SURVEY FIELD
                                          <span class="font-10 text-danger">&nbsp;&nbsp;Negative Count  10</span>
                                          <span class="pull-right"><i class="glyphicon glyphicon-triangle-top"></i></span>
                                        </h4>
                                      </a>
                                    </div>
                                    <div aria-labelledby="heading2" role="tabpanel" class="panel-collapse collapse" id="collapse2">
                                      <div class="panel-body">
                                        <p class="pad_10 m_0">ప్రథిపక్ష నయకుదు జగన్ ప మి అబిప్రయం</p>
                                        <table class="table">
                                        	<thead>
                                            	<tr><th>Location</th>
                                                <th>No Of Samples</th>
                                                <th>పర్వలెదు</th>
                                                <th>బగుంది</th>
                                                <th>బాగాలెదు</th>
                                                <th>తెలియదు</th>
                                            </tr></thead>
                                            <tbody>
                                            	<tr>
                                                	<td>District</td>
                                                    <td>50</td>
                                                    <td>30%</td>
                                                    <td>20%</td>
                                                    <td>10%</td>
                                                    <td>50%</td>
                                                </tr>
                                                <tr>
                                                	<td>AC</td>
                                                    <td>40</td>
                                                    <td>30%</td>
                                                    <td>20%</td>
                                                    <td>10%</td>
                                                    <td>50%</td>
                                                </tr>
                                                <tr>
                                                	<td>PC</td>
                                                    <td>46</td>
                                                    <td>30%</td>
                                                    <td>20%</td>
                                                    <td>10%</td>
                                                    <td>50%</td>
                                                </tr>
                                                <tr>
                                                	<td>Mandal</td>
                                                    <td>45</td>
                                                    <td>30%</td>
                                                    <td>20%</td>
                                                    <td>10%</td>
                                                    <td>50%</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                      </div>
                                    </div>
                                  </div>
								  <div class="panel panel-default">
                                    <div id="headingThree" role="tab" class="panel-heading bg_f9">
                                      <a aria-controls="collapse3" aria-expanded="false" href="#collapse3" data-parent="#accordion1" data-toggle="collapse" role="button" class="collapsed">
                                      	<h4 class="panel-title text-bold">
                                         T2 SA SURVEY FIELD
                                         <span class="font-10 text-danger">&nbsp;&nbsp;Negative Count  10</span>
                                          <span class="pull-right"><i class="glyphicon glyphicon-triangle-top"></i></span>
                                        </h4>
                                      </a>
                                    </div>
                                    <div aria-labelledby="heading3" role="tabpanel" class="panel-collapse collapse" id="collapse3">
                                      <div class="panel-body">
                                        <p class="pad_10 m_0">ప్రథిపక్ష నయకుదు జగన్ ప మి అబిప్రయం</p>
                                        <table class="table">
                                        	<thead>
                                            	<tr><th>Location</th>
                                                <th>No Of Samples</th>
                                                <th>పర్వలెదు</th>
                                                <th>బగుంది</th>
                                                <th>బాగాలెదు</th>
                                                <th>తెలియదు</th>
                                            </tr></thead>
                                            <tbody>
                                            	<tr>
                                                	<td>District</td>
                                                    <td>50</td>
                                                    <td>30%</td>
                                                    <td>20%</td>
                                                    <td>10%</td>
                                                    <td>50%</td>
                                                </tr>
                                                <tr>
                                                	<td>AC</td>
                                                    <td>40</td>
                                                    <td>30%</td>
                                                    <td>20%</td>
                                                    <td>10%</td>
                                                    <td>50%</td>
                                                </tr>
                                                <tr>
                                                	<td>PC</td>
                                                    <td>46</td>
                                                    <td>30%</td>
                                                    <td>20%</td>
                                                    <td>10%</td>
                                                    <td>50%</td>
                                                </tr>
                                                <tr>
                                                	<td>Mandal</td>
                                                    <td>45</td>
                                                    <td>30%</td>
                                                    <td>20%</td>
                                                    <td>10%</td>
                                                    <td>50%</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                      </div>
                                    </div>
                                  </div>
								  </div>
                            </div>
                            <div role="tabpanel" class="tab-pane" id="participated">
                            	<div class="panel-group" id="accordion1" role="tablist" aria-multiselectable="true">
                                  <div class="panel panel-default">
                                    <div class="panel-heading" role="tab" id="heading1">
                                      <a role="button" data-toggle="collapse" data-parent="#accordion1" href="#collapse1" aria-expanded="true" aria-controls="collapse1">
	                                      <h4 class="panel-title text-bold">
                                          T7 SA SURVEY FIELD
	                                      </h4>
                                      </a>
                                    </div>
                                    <div id="collapse1" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="heading1">
                                      <div class="panel-body">
                                        <table class="table m_0 table-bordered">
                                        	<thead>
                                            	<th width="70%">Question</th>
                                                <th width="30%">Answer</th>
                                            </thead>
                                            <tbody>
                                            	<tr>
                                                	<td>ఇ ఛానల్ లూఇ ఛానల్ లూఇ ఛానల్ లూఇ ఛానల్ లూఇ ఛానల్ లూఇ ఛానల్ లూ</td>
                                                    <td>మాటీవీ</td>
                                                </tr>
                                                <tr>
                                                	<td>ఇ ఛానల్ లూఇ ఛానల్ లూఇ ఛానల్ లూఇ ఛానల్ లూఇ ఛానల్ లూఇ ఛానల్ లూ</td>
                                                    <td>మాటీవీ</td>
                                                </tr>
                                                <tr>
                                                	<td>ఇ ఛానల్ లూఇ ఛానల్ లూఇ ఛానల్ లూఇ ఛానల్ లూఇ ఛానల్ లూఇ ఛానల్ లూ</td>
                                                    <td>మాటీవీ</td>
                                                </tr>
                                                <tr>
                                                	<td>ఇ ఛానల్ లూఇ ఛానల్ లూఇ ఛానల్ లూఇ ఛానల్ లూఇ ఛానల్ లూఇ ఛానల్ లూ</td>
                                                    <td>మాటీవీ</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                      </div>
                                    </div>
                                  </div>
                                  <div class="panel panel-default">
                                    <div class="panel-heading" role="tab" id="heading2">
                                      <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion1" href="#collapse2" aria-expanded="false" aria-controls="collapse2">
                                      	<h4 class="panel-title text-bold">
                                          T5 SA SURVEY FIELD
                                        </h4>
                                      </a>
                                    </div>
                                    <div id="collapse2" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading2">
                                      <div class="panel-body">
                                        <table class="table m_0 table-bordered">
                                        	<thead>
                                            	<th width="70%">Question</th>
                                                <th width="30%">Answer</th>
                                            </thead>
                                            <tbody>
                                            	<tr>
                                                	<td>ఇ ఛానల్ లూఇ ఛానల్ లూఇ ఛానల్ లూఇ ఛానల్ లూఇ ఛానల్ లూఇ ఛానల్ లూ</td>
                                                    <td>మాటీవీ</td>
                                                </tr>
                                                <tr>
                                                	<td>ఇ ఛానల్ లూఇ ఛానల్ లూఇ ఛానల్ లూఇ ఛానల్ లూఇ ఛానల్ లూఇ ఛానల్ లూ</td>
                                                    <td>మాటీవీ</td>
                                                </tr>
                                                <tr>
                                                	<td>ఇ ఛానల్ లూఇ ఛానల్ లూఇ ఛానల్ లూఇ ఛానల్ లూఇ ఛానల్ లూఇ ఛానల్ లూ</td>
                                                    <td>మాటీవీ</td>
                                                </tr>
                                                <tr>
                                                	<td>ఇ ఛానల్ లూఇ ఛానల్ లూఇ ఛానల్ లూఇ ఛానల్ లూఇ ఛానల్ లూఇ ఛానల్ లూ</td>
                                                    <td>మాటీవీ</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                      </div>
                                    </div>
                                  </div>
                                  <div class="panel panel-default">
                                    <div class="panel-heading" role="tab" id="headingThree">
                                      <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion1" href="#collapse3" aria-expanded="false" aria-controls="collapse3">
                                      	<h4 class="panel-title text-bold">
                                         T2 SA SURVEY FIELD
                                        </h4>
                                      </a>
                                    </div>
                                    <div id="collapse3" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading3">
                                      <div class="panel-body">
                                        <table class="table m_0 table-bordered">
                                        	<thead>
                                            	<th width="70%">Question</th>
                                                <th width="30%">Answer</th>
                                            </thead>
                                            <tbody>
                                            	<tr>
                                                	<td>ఇ ఛానల్ లూఇ ఛానల్ లూఇ ఛానల్ లూఇ ఛానల్ లూఇ ఛానల్ లూఇ ఛానల్ లూ</td>
                                                    <td>మాటీవీ</td>
                                                </tr>
                                                <tr>
                                                	<td>ఇ ఛానల్ లూఇ ఛానల్ లూఇ ఛానల్ లూఇ ఛానల్ లూఇ ఛానల్ లూఇ ఛానల్ లూ</td>
                                                    <td>మాటీవీ</td>
                                                </tr>
                                                <tr>
                                                	<td>ఇ ఛానల్ లూఇ ఛానల్ లూఇ ఛానల్ లూఇ ఛానల్ లూఇ ఛానల్ లూఇ ఛానల్ లూ</td>
                                                    <td>మాటీవీ</td>
                                                </tr>
                                                <tr>
                                                	<td>ఇ ఛానల్ లూఇ ఛానల్ లూఇ ఛానల్ లూఇ ఛానల్ లూఇ ఛానల్ లూఇ ఛానల్ లూ</td>
                                                    <td>మాటీవీ</td>
                                                </tr>
                                            </tbody>
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
        </div>-->
    </div>
</section>
		
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script src=" http://code.jquery.com/ui/1.11.1/jqueryui/1.11.1/jquery-ui.js "></script>
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
	
	<script>
	
	var globalCadreId = '${cadreId}';
	//var globalCadreId;
	/*  $(document).ready(function() {
			globalCadreId='${param.cadreId}';
			//globalMembershipId='${param.memberShipId}';
			cadreFormalDetailedInformation(globalCadreId);
			//complaintDetailsOfCadre(globalCadreId,globalMembershipId);
			getEventDetailsOfCadre(globalCadreId);
			getTdpCadreSurveyDetails(globalCadreId,0,null);
			
			
	 }); */
	
	 var globalCandidateId; 
	 var ownBoothDetailsVo;
		function cadreFormalDetailedInformation(globalCadreId){
			var localCadreId=globalCadreId;
			//loading images showing
			$("#dataLoadingsImgForownBoothDetailsId").show();
			$("#dataLoadingsImgForImagePath").show();
			
			
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
				if(result !=null){
						$("#dataLoadingsImgForownBoothDetailsId").show();
						$("#dataLoadingsImgForImagePath").show();
						
				//nameId dobId ageId qualificationId occupationId voterIdSpan panchayatId mandalId constituencyId positionId representativeId
					$("#nameId").html(result.name);
					 $("#dobId").html(result.dateOfBirth); 
					 $("#ageId").html(result.age);
					 $("#qualificationId").html(result.qualification);
					 $("#occupationId").html(result.occupation);
					 $("#voterIdSpan").html(result.voterIdCardNo);
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
					 $("#stateNoId").html(result.stateName);
					 $("#houseNoId").html(result.houseNo);
					 
					 
					 if(result.imagePath !=null && result.imagePath !=""){
						 $("#imagePathId").html('<img src="'+result.imagePath+'" class="media-object img-circle" style="border:1px solid #ccc;margin-top:10px;" width="80px" height="80px;">'); 
					 }else{
						 $("#imagePathId").html('<img src="images/search_details_member_imahe.png" class="media-object img-circle" style="border:1px solid #ccc;margin-top:10px;" width="80px" height="80px;">');
					 }
					 
					 //assigning radio ButtonIds for News 
					 
					 $("#panchayatRadioNewsId").val(result.tehsilId);
					 $("#mandalRadioNewsId").val(result.panchayatId);
					 
					 //Hiding Panchayat && Mandal Div
					 if(result.localElectionBody ==0 || result.localElectionBody ==null){
						$(".hidingMandalCls").show();
						//$('#panchayatRadioNewsId').prop('checked', true);
					 }else{
						//$('#aConstiRadioNewsId').prop('checked', true);
						 $(".hidingMandalCls").hide();					 
					 }
					 $('#districtRadioNewsId').prop('checked', true);
					 
					  $("#aConstiRadioNewsId").val(result.constituencyId);
					  $("#pConstiRadioNewsId").val(result.pConstituencyId);
					  $("#districtRadioNewsId").val(result.districtId);
					  
					  //Calling WebService 
						   getCandidateAndLocationSummaryNews();

					
					 //
					  $("#dataLoadingsImgForownBoothDetailsId").hide();
					  $("#dataLoadingsImgForImagePath").hide();
					 
					 if(result.ccmVO !=null && result.ccmVO!=""){
						 ownBoothDetailsVo=result.ccmVO;
						 buildingOwnBoothDetails(ownBoothDetailsVo);
					 }
					 
					 
					 complaintDetailsOfCadre(localCadreId,result.membershipNo);
					 getCandidateElectDetatails(localCadreId);
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
			}).done(function(results){
			if(results !=null && results.length>0 && results !=""){
				$("#participationTableMainDivId").show();
				var str='';
					str+='<table class="table m_0 table-bordered table-responsive">';
					str+='<thead>';
						str+='<th>MAIN EVENT</th>';
						str+='<th>SUB EVENT</th>';
						str+='<th>ATTENDED COUNT(DAYS)</th>';
					str+='</thead>';
					str+='<tbody>';
				if(results !=null && results.length>0){
					for(var i in results){
						var subLength;
						if(results[i].knownList !=null){
							subLength=results[i].knownList.length;
						}
					/* 	var participationType;
						
							if(results[i].id ==1){
								participationType="PARTY OFFICE";
							}
							else if(results[i].id ==7){
								participationType="MAHANADU";
							}else{
								participationType="OTHER";
							} */
								//str+='<td  rowspan='+subLength+'>'+participationType+'</td>';
								
								if(results[i].knownList !=null){
									for(var j in results[i].knownList){
										str+='<tr>';
										if(j==0){
											str+='<td style="border-bottom:#fff;">'+results[i].name+'</td>';
										}else{
											str+='<td style="border:#fff;"></td>';
										}
										str+='<td>'+results[i].knownList[j].name+'</td>';
										str+='<td>'+results[i].knownList[j].total+'</td>';
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
				   $("#participationTableDivId").html(str);
				}else{
					str+='</tbody>';
				    str+='</table>';	
					$("#participationTableDivId").html("Data Not Available.");
					}   
				}else{
					$("#participationTableMainDivId").hide();
				}
				
			});
		}

	function getTdpCadreSurveyDetails(globalCadreId,surveyId,indexId,searchTypeStr,divId){
	var temp="ajax"+surveyId+"";
			$("#"+temp).show();
			var localCadreId=globalCadreId;
			var surveyId=surveyId;
			var indexId=indexId;
			$('.allSurveyDtlsCls').hide();
			if(surveyId !=0 && localCadreId !=0){
				$("#dataLoadingsImg").show();
			}
			if(surveyId>0)
				searchTypeStr = "NotAll";
			
			var jsObj={
				cadreId:localCadreId,
				surveyId:surveyId,
				searchTypeStr: searchTypeStr,
			}
			
			$.ajax({
				type:'GET',
				 url: 'getTdpCadreSurveyDetailsAction.action',
				 data : {task:JSON.stringify(jsObj)} ,
			}).done(function(result){
			
			if(result !=null){
				$("#"+temp).hide();
				$("#surveyDetailsMainDivId").show();
				if(result.verifierVOList !=null){
				if(surveyId ==0 && localCadreId !=0){
					var str='';
					if(searchTypeStr == 'All')
					{
						str+='<ul class="nav nav-tabs tab-list display-style" role="tablist">';
						str+='<li class="active"><a href="#area" onclick="getTdpCadreSurveyDetails('+globalCadreId+','+surveyId+',\'null\',\'All\');" class="text-bold" data-toggle="tab">SURVEYS IN CANDIDATE AREA&nbsp;&nbsp;&nbsp;&nbsp;'+result.totalCount+'</a></li>';
						str+='<li style="margin-top: -8px;"><a href="#participated" onclick="getTdpCadreSurveyDetails('+globalCadreId+','+surveyId+',\'null\',\'NotAll\');" class="text-bold" data-toggle="tab">CANDIDATE PARTICIPATED SURVEYS&nbsp;&nbsp;&nbsp;&nbsp;'+result.count+'</a></li>';
						str+='</ul>';
						$('.surveyDetailssCls').html(str);
					}
					
					
					str='';
					str+='<div class="tab-content m_top20">';
					str+='<div role="tabpanel" class="tab-pane active" id="area">';
					str+='<div class="panel-group" id="accordion1" role="tablist" aria-multiselectable="true">';
					

							for(var i in result.verifierVOList){
								str+='<div class="panel panel-default">';
								str+='<div class="panel-heading bg_f9" role="tab" id="heading'+i+'">';
								str+='<a role="button" data-toggle="collapse" data-parent="#accordion1" onclick="getTdpCadreSurveyDetails('+globalCadreId+','+result.verifierVOList[i].id+',\'null\',\'NotAll\',\'surveyTable'+i+'\');" aria-expanded="true" aria-controls="" style="cursor:pointer;">';
								str+='<h4 class="panel-title text-bold">';
								str+=''+result.verifierVOList[i].name+'';
								str+='<span class="pull-right"><i class="glyphicon glyphicon-triangle-top"></i></span>';
								str+='</h4><img id="ajax'+result.verifierVOList[i].id+'" src="images/icons/loading.gif" style="display:none;width:25px;height:20px;">';
								str+='</a>';
								str+='</div>';
								
								
								str+='<div id="surveyTable'+i+'" class="panel-collapse collapse in allSurveyDtlsCls" role="" aria-labelledby="" style="display:none;">';
								str+='<div class="panel-body">';
								
								str+='</div>';
								str+='</div>';
								
								
								str+='</div>';
						str+='</div>';

							}

					
					str+='</div>';
					str+='</div> ';                     
					str+='</div>';
					$('.surveyDetailsCls').html(str);
					
				}
				else if(surveyId !=0 && surveyId !=0 ){
					buildingSurveyQuestionsDetails(result,surveyId,indexId,divId);
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
			getTdpCadreSurveyDetails(cadreId,surveyId,indexId,"NotAll",0);
		});

		function buildingSurveyQuestionsDetails(results,surveyId,indexId,divId){
			$("#dataLoadingsImg").hide();
			var str='';
				if(results.verifierVOList !=null){
					str+='<div class="panel-body">';
									str+='<table class="table m_0 table-bordered">';
										str+='<thead>';
											str+='<th style="text-align:center;">Question</th>';
											str+='<th style="text-align:center;">Answer</th>';
										str+='</thead>';
										str+='<tbody>';
										
										for(var i in results.verifierVOList){
											str+='<tr>';
												str+='<td>'+results.verifierVOList[i].name+'</td>';
												str+='<td>'+results.verifierVOList[i].option+'</td>'; 
											str+='</tr>';
								if(results.verifierVOList[i].verifierVOList != null && results.verifierVOList[i].verifierVOList.length>0)
											{
												var colCount = 0;
												str+='<tr>';
													str+='<td colspan="2">';
													str+='<table class="table table-bordered">';
														str+='<thead >';
															str+='<th style="text-align:center;background-color:ligtgrey;">';
																str+=' Location ';
															str+='</th>';
															for(var k in results.verifierVOList[i].verifierVOList[4].verifierVOList)
															{	
																str+='<th style="background-color:lightgrey;">';
																str+=''+results.verifierVOList[i].verifierVOList[4].verifierVOList[k].option+'';
																str+='</th>';
															colCount = colCount+1;
															}
														str+='</thead>';
														str+='<tbody>';
														for(var k in results.verifierVOList[i].verifierVOList)
															{	
																str+='<tr>';
																	str+='<td style="text-align:center;">';
																	str+=''+results.verifierVOList[i].verifierVOList[k].name+'';
																	str+='</td>';	
																if(results.verifierVOList[i].verifierVOList[k].verifierVOList != null &&
																 results.verifierVOList[i].verifierVOList[k].verifierVOList.length>0)
																{
																	var buildCoutn = 0;
																	for(var s in results.verifierVOList[i].verifierVOList[k].verifierVOList)
																	{
																		var perc = results.verifierVOList[i].verifierVOList[k].verifierVOList[s].percentage;
																		perc = parseFloat(perc).toFixed(2);
																		str+='<td style="text-align:center;">';
																		str+=''+perc+'';
																		str+='</td>';
																		buildCoutn = buildCoutn+1;
																	}
																	if(buildCoutn < colCount)
																	{
																		do{
																			str+='<td style="text-align:center;"> - </td>';
																			buildCoutn=buildCoutn+1;
																		}while(buildCoutn<colCount);
																			
																	}
																}
																str+='<tr>';																
															}
														str+='</tbody>';
														str+='</table>';
													str+='</td>'; 
												str+='</tr>';
											}
										}
											
										str+='</tbody>';
									str+='</table>';
					str+='</div>';
					
				}else{
					str+='<div>"Data Not Available."</div>';
				}
				$("#"+divId+"").show();		
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
	
</script>		
<script type="text/javascript">
$('.table-scroll').scrollator({
	custom_class: 'table-scroll',
});
/* $('.survey-hover').hide();
$('#survey-dropdown').click(function(){
	$('.survey-hover').toggle();
	
}); */


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
/*$(function () {
	Highcharts.setOptions({
        
    });
    $('#donutchart').highcharts({
        chart: {
            type: 'pie',
			backgroundColor: 'transparent',
			colors: ['#ff9933', '#33cccc', '#ff3333', '#663300'],
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
            data: [
                ['In Progress', 157],
                ['Completed', 100],
                ['Not Eligible', 100],
                ['Not Possible', 100],
				['Not Completed', 100],
            ]
        }]
    });
});*/
/*$(function () {
	Highcharts.setOptions({
        
    });
    $('#donutchart1').highcharts({
        chart: {
            type: 'pie',
			backgroundColor: 'transparent',
			colors: ['#ff9933', '#33cccc', '#ff3333', '#663300'],
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
            data: [
                ['In Progress', 157],
                ['Completed', 100],
                ['Not Eligible', 100],
                ['Not Possible', 100],
				['Not Completed', 100],
            ]
        }]
    });
});*/


 function getTotalMemberShipRegistrationsInCadreLocation()
 {
	$("#memberShipCountDiv").html('<img alt="Processing Image" src="./images/icons/search.gif">');
	  $.ajax({
			type : "POST",
			url  : "getTotalMemberShipRegsInCadreLocationAction.action",
			data : {tdpCadreId:globalCadreId}
		  }).done(function(result){
			if(result != null)
				  buildTotalMemberShipRegInCadreLocation(result);
			  else
				$("#memberShipCountDiv").html('No Data Available.');  
		})
			
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

function buildTotalMemberShipRegInCadreLocation(result)
{
	
	var str = '';
	str += '<div class="col-md-2">';
    str += '<div class="fulCircleCls" data-dimension="100%" data-text="'+result.boothPerc+'%" data-percent="'+result.boothPerc+'" data-fgcolor="#330000" data-bgcolor="#cccccc" data-type="half" data-info="Own Booth"></div>';
	str += '</div>';
	if(result.cadreLocation =="Mandal")
	{
		str += '<div class="col-md-2">';
        str += '<div class="fulCircleCls" data-dimension="100%" data-text="'+result.panchPerc+'%" data-percent="'+result.panchPerc+'" data-fgcolor="#330000" data-bgcolor="#cccccc" data-type="half" data-info="Own Panchayat"></div>';
        str += '</div>';
	 }
	
	str += '<div class="col-md-2">';
    str += '<div class="fulCircleCls" data-dimension="100%" data-text="'+result.mandalPerc+'%" data-percent="'+result.mandalPerc+'" data-fgcolor="#330000" data-bgcolor="#cccccc" data-type="half" data-info="Own Mandal/Muncipality"></div>';
    str += '</div>';
	
	str += '<div class="col-md-2">';
    str += '<div class="fulCircleCls" data-dimension="100%" data-text="'+result.constiPerc+'%" data-percent="'+result.constiPerc+'" data-fgcolor="#330000" data-bgcolor="#cccccc" data-type="half" data-info="Own AC"></div>';
    str += '</div>';
	
	str += '<div class="col-md-2">';
    str += '<div class="fulCircleCls" data-dimension="100%" data-text="'+result.districtPerc+'%" data-percent="'+result.districtPerc+'" data-fgcolor="#330000" data-bgcolor="#cccccc" data-type="half" data-info="Own District"></div>';
    str += '</div>';
	
	str += '<div class="col-md-2">';
    str += '<div class="fulCircleCls" data-dimension="100%" data-text="'+result.parConsPerc+'%" data-percent="'+result.parConsPerc+'" data-fgcolor="#330000" data-bgcolor="#cccccc" data-type="half" data-info="Own PC"></div>';
	str += '</div>';
	
	 $("#memberShipCountDiv").html(str);
	 $('.fulCircleCls').circliful();
}


function getCadreFamilyDetailsByCadreId()
{
	$("#familyMembersDiv").html('<img alt="Processing Image" src="./images/icons/search.gif">');
		  $.ajax({
		  type : "POST",
		  url  : "getCadreFamilyDetailsAction.action",
		  data : {tdpCadreId:globalCadreId}
	  }).done(function(result){
		if(result != null && result.length > 0)
		  buildCadreFamilyDetails(result);  
	  });
 } 
 
 function buildCadreFamilyDetails(result)
 {
	 var str = '';
	 str += '<ul>';
	 for(var i in result)
	 {
		 str += '<li>';
         str += '<div class="media">';
         str += '<div class="media-left">';
         str += '<img src="dist/img/family-member.png" class="img-responsive media-object img-circle" alt="profile">';
         str += '</div>';
         str += '<div class="media-body">';
         str += '<div class="m_0">'+result[i].name+'';
         str += '<span class="pull-right">';
		 if((result[i].education != null&&result[i].education.trim().length > 0) || (result[i].occupation != null && result[i].occupation.trim().length > 0) || (result[i].count != null && result[i].count.trim().length > 0))
           str += '<img class="img-responsive" src="img/survey.png" style="cursor:pointer;" id="survey-dropdown" onclick="surveyShowHide('+i+')">';
	    else
		  str += '<img class="img-responsive" src="img/survey.png" id="survey-dropdown">';
		 str += '</span>';
		 str += '<ul class="survey-hover'+i+' arrow_box3" style="display:none">';
        
		 if(result[i].education != null && result[i].education.trim().length > 0)
			 str += '<li>Education : <span class="pull-right">'+result[i].education+'</span></li>';
		
         if(result[i].occupation != null && result[i].occupation.trim().length > 0)
			 str += '<li>Occupation : <span class="pull-right">'+result[i].occupation+'</span></li>';
		 
         if(result[i].count != null && result[i].count.trim().length > 0)
			 str += '<li>Participated in Survey : <span class="pull-right">'+result[i].count+'</span></li>';
		 
		 str += '</ul>';
         str += '</div>';
         str += '<p class="m_0">Relation : <span class="textTransFormCls">'+result[i].relation+'</span>';
		 if(result[i].relativeName != null && result[i].relativeName.trim().length > 0)
		  str += ' - <span class="textTransFormCls">'+result[i].relativeName+'</span></p>';
         str += '<p class="m_0">Age : ';
		 if(result[i].age != null)
		   str +=''+result[i].age+'';
	   else
		 str += '';
         str += '</p></div>';
         str += '</div>';
         str += '</li>';
	 }
	 str += '</ul>';
	$("#familyMembersDiv").html(str);
 }
 
 
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
		
		str += '<div class="row">';
		str += '<div class="col-md-12">';
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
        str += '<div class="fulCircleCls1" data-dimension="100%" data-text="'+result[i].panchPerc+'%" data-percent="'+result[i].panchPerc+'" data-fgcolor="#330000" data-bgcolor="#cccccc" data-type="half" data-info="Own Panchayat"></div>';
        str += '</td>';
	 }
	
	str += '<td>';
    str += '<div class="fulCircleCls1" data-dimension="100%" data-text="'+result[i].mandalPerc+'%" data-percent="'+result[i].mandalPerc+'" data-fgcolor="#330000" data-bgcolor="#cccccc" data-type="half" data-info="Own Mandal/Muncipality"></div>';
    str += '</td>';
	
	str += '<td>';
    str += '<div class="fulCircleCls1" data-dimension="100%" data-text="'+result[i].constiPerc+'%" data-percent="'+result[i].constiPerc+'" data-fgcolor="#330000" data-bgcolor="#cccccc" data-type="half" data-info="Own AC"></div>';
    str += '</td>';
	
	str += '<td>';
    str += '<div class="fulCircleCls1" data-dimension="100%" data-text="'+result[i].parConsPerc+'%" data-percent="'+result[i].parConsPerc+'" data-fgcolor="#330000" data-bgcolor="#cccccc" data-type="half" data-info="Own PC"></div>';
	str += '</td>';
	
	str += '<td>';
    str += '<div class="fulCircleCls1" data-dimension="100%" data-text="'+result[i].districtPerc+'%" data-percent="'+result[i].districtPerc+'" data-fgcolor="#330000" data-bgcolor="#cccccc" data-type="half" data-info="Own District"></div>';
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

function getApprovedFinancialSupprotForCadre()
{
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
	    var data= new Array();
		data.push(result[i].name,result[i].donationAmount);
	  if(result[i].name !='')
	  dataArr.push(data);
	   if(result[i].name == "CM Relief Fund" && result[i].donationAmount != null && result[i].donationAmount > 0)
	  colorsArr.push('#4A0EAE');  
	  if(result[i].name == "Party Fund" && result[i].donationAmount != null && result[i].donationAmount > 0)	  
	  colorsArr.push('#9B9AC6'); 
	 
	
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
	$("#headingId").html("TOTAL FINANCE REQUESTS  "+result[0].totalRequests+"");
	if((result[0].donationAmount != null && result[0].donationAmount > 0 ) || (result[1].donationAmount != null && result[1].donationAmount > 0))
	{	
	  var str = '';
		
		str += '<li class="financial-by-party" >Financial By party '+result[0].count+'';
		if(result[0].donationAmount != null)
		 str += '['+result[0].donationAmount+'/-]</li>';
		else
		 str += '[0/-]</li>';
	 
		str += '<li class="financial-by-govt" >Financial by govt '+result[1].count+'';
		if(result[1].donationAmount != null)
		 str += '['+result[1].donationAmount+'/-]</li>';
		else
		 str += '[0/-]</li>';
 
      $("#financeSupportUL").html(str);
	}	
	
}




function surveyShowHide(num)
{
	$('.survey-hover'+num+'').toggle();
}
 
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
		url: wurl+"/CommunityNewsPortal/webservice/getCandidateAndLocationSummary/"+startDate+"/"+endDate+"/"+locationType+"/"+locationId+"/"+candidateId+""
		//url: "http://localhost:8080/CommunityNewsPortal/webservice/getCandidateAndLocationSummary/"+startDate+"/"+endDate+"/"+locationType+"/"+locationId+"/"+candidateId+""
	}).then(function(result) {
		$("#dataLoadingsImgForNewsId").hide();
		$("#hideShowNewsDiv").show();
		
		if(result !=null && result !=""){
			var str="";
				str+='<div class="panel-body pad_0">';
					if(result.candidateSummary !=null){
                            	str+='<table class="table m_0 table-bordered m_0">';
                                	str+='<tr>';
                                    	str+='<td>TOTAL ARTICLES  <span class="pull-right text-bold">'+result.candidateSummary.totalCount+'</span></td>';
                                        str+='<td>POSITIVE ARTICLES  <span class="pull-right text-bold">'+result.candidateSummary.positiveCount+'</span></td>';
                                        str+='<td>NEGATIVE ARTICLES  <span class="pull-right text-bold">'+result.candidateSummary.negativeCount+'</span></td>';
                                        str+='<td>NEUTRAL ARTICLES <span class="pull-right text-bold">'+result.candidateSummary.neutralCount+'</span></td>';
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
													str+='<td><span class="text-success">'+result.candidateSummary.categoryList[i].positiveCount+'</span></td>';
													str+='<td><span class="text-danger">'+result.candidateSummary.categoryList[i].negativeCount+'</span></td>';
													str+='<td><span class="text-warning">'+result.candidateSummary.categoryList[i].neutralCount+'</span></td>';
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
					$("#candidateCategoryWiseNewsId").html("Category Wise Data Not Available.");
				}
				if(result.departmentSummary !=null && result.departmentSummary.length>0){
					buildingIssuesTable(result.departmentSummary);
				}else{
					$("#issuesSummary").html("<center><h5>&nbsp No Data Available.</h5></center>");
				}

				if(result.locationSummary !=null){
					$("#propertiesId").html("");
					buildingPropertiesResult(result.locationSummary);
				}else{
					$("#propertiesId").html("Location Wise Data Not Available.");
				}
		}else{
			$("#newsMainDivId").html("Problem Ocuured While Getting Data..Please Consider Admin..");
		}
	}); 
}
	
	function buildingIssuesTable(result){
		$("#issuesSummary").html("");
		$("#issuesCount").html("");
		var str = "";
		var ttlCount = result[0].totalCount;
		$("#issuesCount").html(" TOTAL COUNT - "+ttlCount);
		for(var i in result){
			str +="<tr>";
			str +="<td width='80%'>"+result[i].partyName+"</td>";
			str +="<td>"+result[i].count+"</td>";
			str +="</tr>";
		}
		$("#issuesSummary").html(str);
	}
	
	function buildingPropertiesResult(result){
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
                        str+='<thead id="divId" style="display:none;">';
                        str+='<tr>';
                        str+='<th>Party Name</th>';
                        str+='<th>Positive</th>';
                        str+='<th>Negative</th>';
                        str+='<th>Neutral</th>';
                        str+='</tr>';
                        str+='</thead>';
						$("#divId").show();
						for(var j in props[i].partiesList){
							
							var condCheck=true;
							for(var m in props[i].partiesList[j].oppenentsList){
								if(props[i].partiesList[j].oppenentsList[m].count ==0 && props[i].partiesList[j].oppenentsList[m].count ==0 && props[i].   partiesList[j].oppenentsList[m].count ==0){
									condCheck=false;
								}
							}
							if(condCheck){
								totalCheck=true;
									str+='<tr>';
										str+='<td>'+props[i].partiesList[j].partyName+'</td>';
										for(var k in props[i].partiesList[j].oppenentsList){
											str+='<td>';
											if(props[i].partiesList[j].oppenentsList[k].aliasName=='Pos'){
												str+='<span class="text-success">'+props[i].partiesList[j].oppenentsList[k].count+'</span>';
											}else if(props[i].partiesList[j].oppenentsList[k].aliasName=='neg'){
												str+='<span class="text-danger">'+props[i].partiesList[j].oppenentsList[k].count+'</span>';
											}else if(props[i].partiesList[j].oppenentsList[k].aliasName=='Neutral'){
												str+='<span class="text-warning">'+props[i].partiesList[j].oppenentsList[k].count+'</span>';
											}
											str+='</td>';
									str+='</tr>';
								}	
							}
							
						}
                        
                        str+='</table>';
						 if(!totalCheck){
							 $("#divId").hide();
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
											str+='<td>'+props[i].partiesList[j].oppenentsList[k].count+'</td>';
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
									str+='<td>'+props[i].partiesList[j].count+'</td>';
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
									str+='<td>'+props[i].partiesList[j].count+'</td>';
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
if((globalCadreId == null || globalCadreId.trim().length == 0) && (membershipId != null && membershipId > 0))
{
		getCadreIdByMemberShipId();
}
else
{
		getCategoryWiseStatusCount();
		getTotalMemberShipRegistrationsInCadreLocation();		
		getCadreFamilyDetailsByCadreId();
		getElectionPerformanceInCadreLocation();
		getApprovedFinancialSupprotForCadre();
		cadreFormalDetailedInformation(globalCadreId);
		getEventDetailsOfCadre(globalCadreId);
		getTdpCadreSurveyDetails(globalCadreId,0,null,"All",0);
		
}
function getCadreIdByMemberShipId()
{
	$.ajax({
		type : "POST",
		url  : "getCadreIdByMembershipIdAction.action",
		data : {membershipId:"38324292"}
		
	}).done(function(result){
		console.log(result);
		if(result != null)
		{
			globalCadreId = result;
			getCategoryWiseStatusCount();
			getTotalMemberShipRegistrationsInCadreLocation();		
			getCadreFamilyDetailsByCadreId();
			getElectionPerformanceInCadreLocation();
			getApprovedFinancialSupprotForCadre();
			cadreFormalDetailedInformation(globalCadreId);
			getEventDetailsOfCadre(globalCadreId);
			getTdpCadreSurveyDetails(globalCadreId,0,null,"All",0);
		}
		
		
	});
	
}

//getLocationwiseCommitteesCount();

function getLocationwiseCommitteesCount()
{
	
	$.ajax({
		type : "POST",
		url  : "getLocationwiseCommitteesCountAction.action",
		data : {locationType : "district",tdpCadreId:globalCadreId}
	}).done(function(result){
		console.log(result);
	});
}

//getCadreIdByMemberShipId();
//getCategoryWiseStatusCount();
</script>

</body>
</html>