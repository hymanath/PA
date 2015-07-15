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
	
	<!--<style type="text/css">
			.block{background-color:#fff !important;margin-top:30px;}
			body{background:#d9dbd4 none repeat scroll 0 0}
			.pad_10{padding:10px;}
		</style>  -->
</head>
<body>
<section>
	<div class="container">
    	<div class="row">
        	<div class="col-md-4 bg_white">
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
									</div><!--<img src="dist/img/profile.png" class="img-responsive img-rounded" alt="Profile Image">-->
                                </div>
                                <div class="media-body">
                                	<p class="m_0"><strong>NAME</strong> : <span id="nameId"></span></p>
                                    <p class="m_0"><strong>AGE</strong> : <span id="ageId"></span></p>
                                    <p class="m_0"><strong>DOB</strong> : <span id="dobId"></span></p>
                                    <p class="m_0"><strong>QUALIFICATION</strong> : <span id="qualificationId"></span></p>
                                    <p class="m_0"><strong>OCCUPATION</strong> : <span id="occupationId"></span></p>
                                    <p class="m_0"><strong>CASTE</strong> : <span id="casteFormalId"></span></p>
                                    <p class="m_0"><strong>REGISTERED ON</strong>: <span id="registeredOnId"></span></p>
                                    <p class="m_0"><strong>REGISTERED AT</strong>: <span id="registeredAtId"></span></p>
                                </div>
                            </div>
                        </td>
                    </tr>
                    <tr>
                    	<td>
                        	<i class="glyphicon glyphicon-phone"></i> <span id="mobileNoId"></span> 
                        	<span class="pull-right">
	                            <i class="glyphicon glyphicon-envelope"></i><span id="emailSpanId"></span> 
                            </span>
                        </td>
                    </tr>
                    <tr>
                    	<td><i class="icon-articles"></i> IDENTITY</td>
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
                    	<td><i class="glyphicon glyphicon-map-marker"></i> ADDRESS</td>
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
                    	<h4 class="panel-title"><i class="glyphicon glyphicon-hand-right"></i> GRIEVANCE DETAILS</h4>
                    </div>
                    <div class="panel-body">
                    	<h5 class="m_0">TOTAL COMPLAINTS 8</h5>
                        <div class="display-style">
                            <div id="donutchart" class="display-style" style="height: 120px;float:left;width:150px;"></div>
                            <ul class="display-style pull-right graph-list" style="padding-right:20px;padding-left:0px;">
                                <li class="show-dropdown"><span class="inp"></span>Not Verified<span class="pull-right">157
                                	<ul class="count-hover arrow_box3">
                                    	<li>Govt<span class="pull-right">25</span></li>
                                        <li>Party<span class="pull-right">25</span></li>
                                        <li>Welfare<span class="pull-right">25</span></li>
                                    </ul>
                                </span></li>
                                <li class="show-dropdown"><span class="cmp"></span>In Progress<span class="pull-right">100
                                	<ul class="count-hover arrow_box3">
                                    	<li>Govt<span class="pull-right">25</span></li>
                                        <li>Party<span class="pull-right">25</span></li>
                                        <li>Welfare<span class="pull-right">25</span></li>
                                    </ul>
                                </span></li>
                                <li class="show-dropdown"><span class="ncmp"></span>Completed<span class="pull-right ">100
                                	<ul class="count-hover arrow_box3">
                                    	<li>Govt<span class="pull-right">25</span></li>
                                        <li>Party<span class="pull-right">25</span></li>
                                        <li>Welfare<span class="pull-right">25</span></li>
                                    </ul>
                                </span></li>
                                <li class="show-dropdown"><span class="note"></span>Not Eligible<span class="pull-right show-dropdown">100
                                	<ul class="count-hover arrow_box3">
                                    	<li>Govt<span class="pull-right">25</span></li>
                                        <li>Party<span class="pull-right">25</span></li>
                                        <li>Welfare<span class="pull-right">25</span></li>
                                    </ul>
                                </span></li>
                                <li class="show-dropdown"><span class="note"></span>Not Possible<span class="pull-right">100
                                	<ul class="count-hover arrow_box3">
                                    	<li>Govt<span class="pull-right">25</span></li>
                                        <li>Party<span class="pull-right">25</span></li>
                                        <li>Welfare<span class="pull-right">25</span></li>
                                    </ul>
                                </span></li>
                            </ul>
                        </div>
                    </div>
                </div>
            	<div class="panel panel-default">
                	<div class="panel-heading">
                    	<h4 class="panel-title"><i class="glyphicon glyphicon-usd"></i> FINANCE SUPPORT</h4>
                    </div>
                    <div class="panel-body">
                   		<h4>TOTAL REQUEST FOR<br/> FINANCE SUPPORT 210 [140000/-]</h4>
                    	<div id="donutchart1" class="display-style" style="height: 120px;float:left;width:150px;"></div>
                    <ul class="display-style pull-right piechart-list pad_0">
                        <li class="financial-by-party">Financial By party 10[80000/-]</li>
                        <li class="financial-by-govt">Financial by govt 50[30000/-]</li>
                    </ul>
                    </div>
                </div>
                <div class="panel panel-default">
                	<div class="panel-heading">
                    	<h4 class="panel-title"><i class="glyphicon glyphicon-flash"></i> DEATHS AND HOSPITALIZATION</h4>
                    </div>
                    <div class="panel-body pad_0">
                    	<table class="table table-bordered m_0">
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
                    </div>
                </div>
                <div class="panel panel-default">
                	<div class="panel-heading">
                    	<h4 class="panel-title">
                        	<img src="dist/img/family-icon.png">&nbsp;&nbsp;&nbsp;FAMILY MEMBERS
                        </h4>
                    </div>
                    <div class="panel-body">
                    	<div class="family-members">
                            <ul>
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
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-md-8">
            	<div class="panel panel-default">
                	<div class="panel-heading">
                    	<h4 class="panel-title">CADRE MEMBER BOOTH PERFORMANCE</h4>
                    </div>
                    <div class="panel-body">
                    	<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
                          <div class="panel panel-default">
                            <div class="panel-heading  bg_white" role="tab" id="headingOne">
                              <h4 class="panel-title">
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
                              <h4 class="panel-title">
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
                          </div>
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                	<div class="panel-heading">
                    	<h4 class="panel-title">CADRE ENROLMENT STATS</h4>
                    </div>
                    <div class="panel-body">
                    	<div class="row">
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
                            </div>
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                	<div class="panel-heading">
                    	<h4 class="panel-title"><i class="glyphicon glyphicon-record"></i>&nbsp;&nbsp;&nbsp;&nbsp;EVENT PARTICIPATION ACTIVITIES & TRAINING DETAILS</h4>
                    </div>
                    <div class="panel-body">
                    	<div class="row">
                        	<div class="col-md-6 col-xs-12">
                            	<table class="table table-bordered">
                                	<thead >
                                    	<th class="text-center" colspan="3">EVENT INVITATIONS</th>
                                    </thead>
                                    <tr class="text-center">
                                    	<td>30<br/>Sent</td>
                                        <td>20<br/>Participated</td>
                                        <td>08<br/>Absent</td>
                                    </tr>
                                </table>
                            </div>
                            <div class="col-md-6 col-xs-12">
                            	<table class="table table-bordered">
                                	<thead>
                                    	<th class="text-center" colspan="3">TRAINING'S</th>
                                    </thead>
                                    <tr class="text-center">
                                    	<td>30<br/>Suggested</td>
                                        <td>20<br/>Attended</td>
                                        <td>08<br/>Absent</td>
                                    </tr>
                                </table>
                            </div>
                            <div class="col-md-12 col-xs-12">
                            	<table class="table-bordered table">
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
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                	<div class="panel-heading">
                    	<h4 class="panel-title"><i class="glyphicon glyphicon-folder-open"></i>&nbsp;&nbsp;&nbsp;CADRE ELECTION PROFILE</h4>
                    </div>
                    <div class="panel-body">
                    	<div class="cadre-election">
                        	<ul>
                            	<li>Won in 2014 election with 62.59 votes gain for TELUGU DESAM party in kuppam consituency</li>
                                <li>Won in 2014 election with 62.59 votes gain for TELUGU DESAM party in kuppam consituency</li>
                                <li>Won in 2014 election with 62.59 votes gain for TELUGU DESAM party in kuppam consituency</li>
                                <li>Won in 2014 election with 62.59 votes gain for TELUGU DESAM party in kuppam consituency</li>
                                <li>Won in 2014 election with 62.59 votes gain for TELUGU DESAM party in kuppam consituency</li>
                                <li>Won in 2014 election with 62.59 votes gain for TELUGU DESAM party in kuppam consituency</li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                	<div class="panel-heading">
                    	<h4 class="panel-title"><i class="glyphicon glyphicon-list-alt"></i>&nbsp;&nbsp;&nbsp;NEWS</h4>
                    </div>
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
                            <div class="pull-right">
                            	<div class="calendar-style"  id="reportrange">
                                	<div class="caret"></div>
                                	<i class="glyphicon glyphicon-calendar"></i>
                                </div>
                            </div>
                        </div>
                        <div class="panel panel-default m_top20">
                        	<div class="panel-heading bg_f9">
                            	<h4 class="panel-title text-center">
                                	CANDIDATE CATEGORY WISE
                                </h4>
                            </div>
                            <div class="panel-body pad_0">
                            	<table class="table table-bordered m_0">
                                	<tr>
                                    	<td>TOTAL ARTICLES  <span class="pull-right text-bold">03</span></td>
                                        <td>POSITIVE ARTICLES  <span class="pull-right text-bold">03</span></td>
                                        <td>NEGATIVE ARTICLES  <span class="pull-right text-bold">03</span></td>
                                        <td>NEUTRAL ARTICLES <span class="pull-right text-bold">03</span></td>
                                    </tr>
                                </table>
                                <table class="table m_0">
                                	<thead>
                                    	<th width="50%">CATEGORY NAME</th>
                                        <th>POSITIVE</th>
                                        <th>NEGATIVE</th>
                                        <th>NEUTRAL</th>
                                    </thead>
                                    <tbody>
                                    	<tr>
                                        	<td>NON CONVENTIONAL ENERGY</td>
                                            <td><span class="text-success">20</span></td>
                                            <td><span class="text-danger">10</span></td>
                                            <td><span class="text-warning">20</span></td>
                                        </tr>
                                    	<tr>
                                        	<td>VILLAGE HEALTH</td>
                                            <td><span class="text-success">20</span></td>
                                            <td><span class="text-danger">10</span></td>
                                            <td><span class="text-warning">20</span></td>
                                        </tr>
                                    	<tr>
                                        	<td>ROADS & BUILDINGS</td>
                                            <td><span class="text-success">20</span></td>
                                            <td><span class="text-danger">10</span></td>
                                            <td><span class="text-warning">20</span></td>
                                        </tr>
                                    	<tr>
                                        	<td>OTHER MUNCIPAL CORPORATIONS</td>
                                            <td><span class="text-success">20</span></td>
                                            <td><span class="text-danger">10</span></td>
                                            <td><span class="text-warning">20</span></td>
                                        </tr>
                                    	<tr>
                                        	<td>RURAL WATER SUPPLY SCHEME-FLORIDE</td>
                                            <td><span class="text-success">20</span></td>
                                            <td><span class="text-danger">10</span></td>
                                            <td><span class="text-warning">20</span></td>
                                        </tr>
                                    	<tr>
                                        	<td>NON CONVENTIONAL ENERGY</td>
                                            <td><span class="text-success">20</span></td>
                                            <td><span class="text-danger">10</span></td>
                                            <td><span class="text-warning">20</span></td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <div class="panel panel-default">
                        	<div class="panel-heading bg_white">
                            	<h4 class="panel-title text-center">CANDIDATE LOCATION SPECIFIC NEWS</h4>
                            </div>
                            <div class="panel-body pad_0">
                            	<div class="row">
                                	<div class="col-md-6">
                                    	<div class="panel panel-default">
                                        	<div class="panel-heading bg_f9">
                                            	<h4 class="panel-title text-center text-capitalize">ANALYSIS STORIES</h4>
                                            </div>
                                            <div class="panel-body pad_0">
                                            	<table class="table m_0">
                                                	<thead>
                                                    	<tr>
                                                        	<th>Party Name</th>
                                                            <th>Positive</th>
                                                            <th>Negative</th>
                                                            <th>Neutral</th>
                                                        </tr>
                                                    </thead>
                                                    <tr>
                                                    	<td>TDP</td>
                                                        <td><span class="text-success">5</span></td>
                                                        <td><span class="text-danger">5</span></td>
                                                        <td><span class="text-warning">0</span></td>
                                                    </tr>
                                                    <tr>
                                                    	<td>BJP</td>
                                                        <td><span class="text-success">5</span></td>
                                                        <td><span class="text-danger">5</span></td>
                                                        <td><span class="text-warning">0</span></td>
                                                    </tr>
                                                    <tr>
                                                    	<td>YSRC</td>
                                                        <td><span class="text-success">5</span></td>
                                                        <td><span class="text-danger">5</span></td>
                                                        <td><span class="text-warning">0</span></td>
                                                    </tr>
                                                    <tr>
                                                    	<td>INC</td>
                                                        <td><span class="text-success">5</span></td>
                                                        <td><span class="text-danger">5</span></td>
                                                        <td><span class="text-warning">0</span></td>
                                                    </tr>
                                                    <tr>
                                                    	<td>TRS</td>
                                                        <td><span class="text-success">5</span></td>
                                                        <td><span class="text-danger">5</span></td>
                                                        <td><span class="text-warning">0</span></td>
                                                    </tr>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                    	<div class="panel panel-default">
                                        	<div class="panel-heading bg_f9" style="padding-left:5px;padding-right:5px;">
                                            	<h4 class="panel-title text-center text-capitalize">INTERNAL FIGHTS</h4>
                                            </div>
                                            <div class="panel-body pad_0">
                                            	<table class="table table-bordered m_0">
                                                	<tr>
                                                    	<td>TDP</td>
                                                        <td>10</td>
                                                    </tr>
                                                    <tr>
                                                    	<td>BJP</td>
                                                        <td>10</td>
                                                    </tr>
                                                    <tr>
                                                    	<td>YSRC</td>
                                                        <td>10</td>
                                                    </tr>
                                                    <tr>
                                                    	<td>INC</td>
                                                        <td>10</td>
                                                    </tr>
                                                    <tr>
                                                    	<td>TRS</td>
                                                        <td>10</td>
                                                    </tr>
                                                    <tr>
                                                    	<td>&nbsp;</td>
                                                        <td>&nbsp;</td>
                                                    </tr>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                    	<div class="panel panel-default">
                                        	<div class="panel-heading bg_f9">
                                            	<h4 class="panel-title text-center text-capitalize">SPOT NEWS</h4>
                                            </div>
                                            <div class="panel-body pad_0">
                                            	<table class="table table-bordered m_0">
                                                	<tr>
                                                    	<td>TDP</td>
                                                        <td>10</td>
                                                    </tr>
                                                    <tr>
                                                    	<td>BJP</td>
                                                        <td>10</td>
                                                    </tr>
                                                    <tr>
                                                    	<td>YSRC</td>
                                                        <td>10</td>
                                                    </tr>
                                                    <tr>
                                                    	<td>INC</td>
                                                        <td>10</td>
                                                    </tr>
                                                    <tr>
                                                    	<td>TRS</td>
                                                        <td>10</td>
                                                    </tr>
                                                    <tr>
                                                    	<td>&nbsp;</td>
                                                        <td>&nbsp;</td>
                                                    </tr>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6 col-xs-12">
                                    	<div class="panel panel-default m_0">
                                        	<div class="panel-heading bg_f9">
                                            	<h4 class="panel-title text-center">PRESS & PARTY MEETINGS</h4>
                                            </div>
                                            <div class="panel-body pad_0">
                                            	<table class="table m_0">
                                                	<thead>
                                                    	<th>Party Name</th>
                                                        <th>Press</th>
                                                        <th>Party</th>
                                                    </thead>
                                                    <tbody>
                                                    	<tr>
                                                        	<td>TDP</td>
                                                            <td>10</td>
                                                            <td>5</td>
                                                        </tr>
                                                        <tr>
                                                        	<td>BJP</td>
                                                            <td>10</td>
                                                            <td>4</td>
                                                        </tr>
                                                        <tr>
                                                        	<td>YSRC</td>
                                                            <td>10</td>
                                                            <td>8</td>
                                                        </tr>
                                                        <tr>
                                                        	<td>INC</td>
                                                            <td>10</td>
                                                            <td>3</td>
                                                        </tr>
                                                        <tr>
                                                        	<td>TRS</td>
                                                            <td>10</td>
                                                            <td>1</td>
                                                        </tr>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6 col-xs-12">
                                    	<div class="panel panel-default m_0">
                                        	<div class="panel-heading bg_f9">
                                            	<h4 class="panel-title">ISSUES<span class="pull-right">TOTAL - 20</span></h4>
                                            </div>
                                            <div class="panel-body pad_0 table-scroll">
                                            	<table class="table m_0">
                                                	<tr>
                                                    	<td width="80%">Muncipal Corporation</td>
                                                        <td>5</td>
                                                    </tr>
                                                    <tr>
                                                    	<td>R&B</td>
                                                        <td>2</td>
                                                    </tr>
                                                    <tr>
                                                    	<td>RTC</td>
                                                        <td>45</td>
                                                    </tr>
                                                    <tr>
                                                    	<td>Irrigation</td>
                                                        <td>6</td>
                                                    </tr>
                                                    <tr>
                                                    	<td>Agriculture</td>
                                                        <td>4</td>
                                                    </tr>
                                                    <tr>
                                                    	<td>Health & Medical</td>
                                                        <td>5</td>
                                                    </tr>
                                                    <tr>
                                                    	<td>Health & Medical</td>
                                                        <td>5</td>
                                                    </tr>
                                                    <tr>
                                                    	<td>Health & Medical</td>
                                                        <td>5</td>
                                                    </tr>
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
        	<div class="col-md-12">
            	<div class="panel panel-default">
                	<div class="panel-heading">
                    	<h4 class="panel-title"><i class="glyphicon glyphicon-stats"></i>&nbsp;&nbsp;&nbsp;SURVEY DETAILS</h4>
                    </div>
                    <div class="panel-body">
                    	<div>
                          <!-- Nav tabs -->
                          <ul class="nav nav-tabs tab-list" role="tablist">
                            <li class="active"><a href="#area" class="text-bold" data-toggle="tab">SURVEYS IN CANDIDATE AREA&nbsp;&nbsp;&nbsp;&nbsp;10</a></li>
                            <li><a href="#participated" class="text-bold" data-toggle="tab">CANDIDATE PARTICIPATED SURVEYS&nbsp;&nbsp;&nbsp;&nbsp;10</a></li>
                          </ul>
                        
                          <!-- Tab panes -->
                          <div class="tab-content m_top20">
                            <div role="tabpanel" class="tab-pane active" id="area">
                            	<div class="panel-group" id="accordion1" role="tablist" aria-multiselectable="true">
                                  <div class="panel panel-default">
                                    <div class="panel-heading bg_f9" role="tab" id="heading1">
                                      <a role="button" data-toggle="collapse" data-parent="#accordion1" href="#collapse1" aria-expanded="true" aria-controls="collapse1">
	                                      <h4 class="panel-title">
                                          T7 SA SURVEY FIELD
	                                      </h4>
                                      </a>
                                    </div>
                                    <div id="collapse1" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="heading1">
                                      <div class="panel-body">
                                        <table class="table table-bordered">
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
                                    <div class="panel-heading bg_f9" role="tab" id="heading2">
                                      <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion1" href="#collapse2" aria-expanded="false" aria-controls="collapse2">
                                      	<h4 class="panel-title">
                                          T5 SA SURVEY FIELD
                                        </h4>
                                      </a>
                                    </div>
                                    <div id="collapse2" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading2">
                                      <div class="panel-body">
                                        <table class="table table-bordered">
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
                                    <div class="panel-heading bg_f9" role="tab" id="headingThree">
                                      <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion1" href="#collapse3" aria-expanded="false" aria-controls="collapse3">
                                      	<h4 class="panel-title">
                                         T2 SA SURVEY FIELD
                                        </h4>
                                      </a>
                                    </div>
                                    <div id="collapse3" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading3">
                                      <div class="panel-body">
                                        <table class="table table-bordered">
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
                            <div role="tabpanel" class="tab-pane" id="participated">
                            	<div class="panel-group" id="accordion1" role="tablist" aria-multiselectable="true">
                                  <div class="panel panel-default">
                                    <div class="panel-heading" role="tab" id="heading1">
                                      <a role="button" data-toggle="collapse" data-parent="#accordion1" href="#collapse1" aria-expanded="true" aria-controls="collapse1">
	                                      <h4 class="panel-title">
                                          T7 SA SURVEY FIELD
	                                      </h4>
                                      </a>
                                    </div>
                                    <div id="collapse1" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="heading1">
                                      <div class="panel-body">
                                        <table class="table table-bordered">
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
                                      	<h4 class="panel-title">
                                          T5 SA SURVEY FIELD
                                        </h4>
                                      </a>
                                    </div>
                                    <div id="collapse2" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading2">
                                      <div class="panel-body">
                                        <table class="table table-bordered">
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
                                      	<h4 class="panel-title">
                                         T2 SA SURVEY FIELD
                                        </h4>
                                      </a>
                                    </div>
                                    <div id="collapse3" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading3">
                                      <div class="panel-body">
                                        <table class="table table-bordered">
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
        </div>
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
	
	var globalCadreId;
	 $(document).ready(function() {
			globalCadreId='${param.cadreId}';
			//globalMembershipId='${param.memberShipId}';
			cadreFormalDetailedInformation(globalCadreId);
			//complaintDetailsOfCadre(globalCadreId,globalMembershipId);
			getEventDetailsOfCadre(globalCadreId);
			getTdpCadreSurveyDetails(globalCadreId,0,null);
			
	 });
		
			
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
					 $("#registeredOnId").html(result.registeredOn);
					 $("#registeredAtId").html(result.registeredTime);
					 $("#emailSpanId").html(result.emailId);
					 $("#districtNoId").html(result.districtName);
					 $("#stateNoId").html(result.stateName);
					 $("#houseNoId").html(result.houseNo);
					 
					 
					 if(result.imagePath !=null && result.imagePath !=""){
						 $("#imagePathId").html('<img src="'+result.imagePath+'" class="media-object img-circle" style="border:1px solid #ccc;margin-top:10px;" width="80px" height="80px;">'); 
					 }else{
						 $("#imagePathId").html('<img src="images/search_details_member_imahe.png" class="media-object img-circle" style="border:1px solid #ccc;margin-top:10px;" width="80px" height="80px;">');
					 }
					 
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
				//$("#dataLoadingsImgForComplaint").hide();
				var str='';
				if(result !=null){
					if(result.knownList !=null && result.knownList.length>0){
						$("#grievanceDetailsMainDivId").show();
					
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
				}
				
				
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
				var str='';
				
					str+='<table class="table table-bordered table-responsive">';
					str+='<thead>';
						str+='<th>Main Event</th>';
						str+='<th>Sub Event</th>';
						str+='<th>Attended Count(Days)</th>';
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
			});
		}

	function getTdpCadreSurveyDetails(globalCadreId,surveyId,indexId){
			var localCadreId=globalCadreId;
			var surveyId=surveyId;
			var indexId=indexId;
			if(surveyId !=0 && localCadreId !=0){
				$("#dataLoadingsImg").show();
			}
			var jsObj={
				cadreId:localCadreId,
				surveyId:surveyId
			}
			
			$.ajax({
				type:'GET',
				 url: 'getTdpCadreSurveyDetailsAction.action',
				 data : {task:JSON.stringify(jsObj)} ,
			}).done(function(result){
			
			if(result !=null){
				$("#surveyDetailsMainDivId").show();
				if(result.verifierVOList !=null){
				if(surveyId ==0 && localCadreId !=0){
					var str='';
							for(var i in result.verifierVOList){
								str+='<div class="panel panel-default">';
								str+='<div class="panel-heading hideshowCls" role="tab" id="heading'+result.verifierVOList[i].id+''+i+'">';
								  str+='<h4 class="panel-title">';
									str+='<a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapse'+result.verifierVOList[i].id+''+i+'" aria-expanded="true" aria-controls="collapse'+result.verifierVOList[i].id+''+i+'">'+result.verifierVOList[i].name+'</a>';
								  str+='</h4>';
								str+='</div>';
								str+='<div id="collapse'+result.verifierVOList[i].id+''+i+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading'+result.verifierVOList[i].id+''+i+'" attr_survey_id='+result.verifierVOList[i].id+' attr_cadre_id='+localCadreId+' attr_index_id='+i+'><img id="dataLoadingsImg" src="images/icons/loading.gif" style="width:25px;height:20px;display:none;"/>';
								str+='</div>';
							  str+='</div>';
							}
						 
					$('.surveyDetailsCls').html(str);
					
				}
				else if(surveyId !=0 && surveyId !=0 ){
					buildingSurveyQuestionsDetails(result,surveyId,indexId);
				}
			 }		
			}else{
				if(surveyId ==0 && localCadreId !=0){
					$('.surveyDetailsCls').html('<div>Data Not Available.</div>');
				}
				else if(surveyId !=0 && surveyId !=0 ){
					$("#collapse"+surveyId+''+indexId).html('<div>Data Not Available.</div>');	
				}
				$("#surveyDetailsMainDivId").hide();
				
			}
		});
			
	}
		
		$('#accordion').on('shown.bs.collapse', function() {
			var surveyId = $(this).find("div.in").attr("attr_survey_id");
			var cadreId= $(this).find("div.in").attr("attr_cadre_id");
			var indexId=$(this).find("div.in").attr("attr_index_id");
			getTdpCadreSurveyDetails(cadreId,surveyId,indexId);
		});

		function buildingSurveyQuestionsDetails(results,surveyId,indexId){
			$("#dataLoadingsImg").hide();
			var str='';
				if(results.verifierVOList !=null){
					str+='<div class="panel-body">';
									str+='<table class="table table-bordered">';
										str+='<thead>';
											str+='<th>Question</th>';
											str+='<th>Answer</th>';
										str+='</thead>';
										str+='<tbody>';
										
										for(var i in results.verifierVOList){
											str+='<tr>';
												str+='<td>'+results.verifierVOList[i].name+'</td>';
												str+='<td>'+results.verifierVOList[i].option+'</td>'; 
											str+='</tr>';
										}
											
										str+='</tbody>';
									str+='</table>';
					str+='</div>';
					
				}else{
					str+='<div>"Data Not Available."</div>';
				}
				$("#collapse"+surveyId+''+indexId).html(str);		
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
							str+='<ul class="wl-sub-details">';
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
				/* else{
					$("#electionProfileMainDivId").hide();
				} */
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
$('#myStathalf').circliful();
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
$('#myStathalf18').circliful();
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
	dateLimit: { days: 60 },
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
	applyClass: 'btn-small btn-primary',
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
</script>
<script type="text/javascript">
$(function () {
	Highcharts.setOptions({
        colors: ['#ff9933', '#33cccc', '#ff3333', '#663300']
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
            data: [
                ['In Progress', 157],
                ['Completed', 100],
                ['Not Eligible', 100],
                ['Not Possible', 100],
				['Not Completed', 100],
            ]
        }]
    });
});
$(function () {
	Highcharts.setOptions({
        colors: ['#ff9933', '#33cccc', '#ff3333', '#663300']
    });
    $('#donutchart1').highcharts({
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
            data: [
                ['In Progress', 157],
                ['Completed', 100],
                ['Not Eligible', 100],
                ['Not Possible', 100],
				['Not Completed', 100],
            ]
        }]
    });
});
		
</script>
</body>
</html>