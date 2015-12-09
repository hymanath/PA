<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title> Activities Dashboard </title>
<link href="dist/activity/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="dist/activity/css/custom.css" rel="stylesheet" type="text/css">
<link href="dist/activity/Icomoon/style.css" rel="stylesheet" type="text/css">
<link href="http://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
<link href="dist/activity/Date/daterangepicker.css" rel="stylesheet" type="text/css">
<link href="dist/activity/SelectDropDown/dropkick.css" rel="stylesheet" type="text/css">
<link href="dist/activity/Slick/slick.css" rel="stylesheet" type="text/css">
<link href="dist/activity/Slick/slick-theme.css" rel="stylesheet" type="text/css">
<link href="dist/activity/FancyBox/jquery.fancybox.css" rel="stylesheet" type="text/css">
<style type="text/css">
.dk-selected 
{
	background:#ccc;
}
.table-col tr td
{
	border-top:0px !important;
	vertical-align:middle !important
}
.panel-group .panel-customtd
{
	border-radius:0px;
}
.panel-customtd .panel-heading
{
	padding:21px 5px;
}
.panel-heading:hover > .btn-hover
{
	display:block
}
.btn-hover
{
	display:none;
	float:right;
	position:relative;
	margin-right:3px;
	top:-25px
}
.panel-customtd .panel-heading:hover , .panel-customtd .panel-heading:hover table
{
	background:#dcd0c4;
	border-radius:0px;
}
.panel-customtd .panel-heading:hover:after
{
	
}
.panel-group3 .panel-default .panel-body
{
	background:#efeae5
}
.slick-training
{
	background:#fff;
	height:30px;
	margin-left:50px;
}
</style>
</head>

<body>
<div class="container">
	<div class="row">
    	<div class="col-md-12 col-sm-12 col-xs-12">
        	<div class="panel panel-default panel-custom">
            	<div class="panel-heading pad_0">
                	<select class="select">
                    	<option>JANA CHAITANYA YATRA</option>
                    </select>
                </div>
                <div class="panel-body">
                	<div class="panel panel-default panel-custom1">
                    	<div class="panel-heading bg_66">
                        	<h4 class="panel-title">VILLAGE/WARD</h4>
                        </div>
                        <div class="panel-body pad_0" id="stateWiseViewDid" >
                        	<!--<div class="table-responsive">
                                <table class="table table-bordered bg_ff">
                                    <tr class="text-center">
                                    	<td class="pad_0" rowspan="2" style="box-sizing:none;"><img src="dist/activity/img/andhrap.jpg" class="img-responsive head-td" style="height:150px;" ></td>
                                        <td><h3 class="m_top20 m_bottom10">16689</h3><hr class="custom-hr"/></td>
                                        <td><h3 class="m_top20 m_bottom10">16689</h3><hr class="custom-hr"/></td>
                                        <td><h3 class="m_top20 m_bottom10">16689</h3><hr class="custom-hr"/></td>
                                        <td><h3 class="m_top20 m_bottom10">16689</h3><hr class="custom-hr"/></td>
                                        <td><h3 class="m_top20 m_bottom10">16689</h3><hr class="custom-hr"/></td>
                                        <td><h3 class="m_top20 m_bottom10">16689</h3><hr class="custom-hr"/></td>
                                    </tr>
                                    <tr>
                                    	<td class="bg_ef text-center">TOTAL VILLAGE/WARD</td>
                                        <td class="bg_ef text-center">PLANNED VILLAGE/WARD</td>
                                        <td class="bg_ef text-center">INFO CELL COVERED</td>
                                        <td class="bg_ef text-center">IVR COVERED</td>
                                        <td class="bg_ef text-center">WHATSAPP IMAGE COVERED</td>
                                        <td class="bg_ef text-center">NO OF IMAGE RECEIVED @ WHATSAPP</td>
                                    </tr>
                                </table>
                            </div>-->
                        </div>
                    </div>
                    <div class="table-responsive" id="alignmentWidth">
					<!--
                        <table class="table table-condensed">
                            <caption class="cap-custom"><b>DISTRICT WISE - VILLAGE/WARD</b></caption>
                            <tr class="font-12">
                                <td class="dynWidth" style="width:220px">LOCATION NAME</td>
                                <td class="getChildWidth">TOTAL</td>
                                <td class="getChildWidth2">PLANNED</td>
                                <td class="getChildWidth3">INFO CELL COVERED</td>
                                <td class="getChildWidth4">INFO CELL COVERED %</td>
                                <td class="getChildWidth5">IVR COVERED</td>
                                <td class="getChildWidth6">IVR COVERED %</td>
                                <td class="getChildWidth7">WHATSAPP IMAGE COVERED</td>
                                <td class="getChildWidth8">WHATSAPP IMAGE COVERED %</td>
                                <td class="getChildWidth9">NO OF IMAGE RECEIVED @ WHATSAPP</td>
                            </tr>
                            <tr>
                            	<td colspan="10" class="pad_0">
                                	<div class="panel-group m_0" id="accordion" role="tablist" aria-multiselectable="true">
                                      <div class="panel panel-default panel-customtd">
                                        <div class="panel-heading" role="tab" id="headingOne">
                                            <a role="button" class="accordion-toggle collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                                            <table class="table table-col table-condensed" style="display:inline" >
                                            	<tr>
                                                	<td style="width:210px;">Srikakulam District</td>
                                                    <td class="dynChildWidth">100</td>
                                                    <td class="dynChildWidth2">200</td>
                                                    <td class="dynChildWidth3">200</td>
                                                    <td class="dynChildWidth4">200</td>
                                                    <td class="dynChildWidth5">200</td>
                                                    <td class="dynChildWidth6">200</td>
                                                    <td class="dynChildWidth7">200</td>
                                                    <td class="dynChildWidth8">200</td>
                                                    <td class="dynChildWidth9">200</td>
                                                </tr>
                                            </table>
                                            </a>
                                            <button type="button" class="btn btn-custom btn-hover btn-xs"><i class="glyphicon glyphicon-align-justify"></i></button>
                                            <button type="button" class="btn btn-custom btn-hover btn-xs">Day Wise</button>
                                        </div>
                                        <div id="collapseOne" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">
                                          <div class="panel-body">
                                            <div class="panel-group panel-group1 m_0" id="accordion1" role="tablist" aria-multiselectable="true">
                                              <div class="panel panel-default panel-customtd">
                                                <div class="panel-heading" role="tab" id="headingOne1">
                                                    <a role="button" class="accordion1-toggle collapsed" data-toggle="collapse" data-parent="#accordion1" href="#collapseOne1" aria-expanded="true" aria-controls="collapseOne1">
                                                    <table class="table table-col table-condensed" style="display:inline" >
                                                        <tr>
                                                            <td style="width:200px;">Srikakulam Constituency</td>
                                                            <td class="dynChildWidth">100</td>
                                                            <td class="dynChildWidth2">200</td>
                                                            <td class="dynChildWidth3">200</td>
                                                            <td class="dynChildWidth4">200</td>
                                                            <td class="dynChildWidth5">200</td>
                                                            <td class="dynChildWidth6">200</td>
                                                            <td class="dynChildWidth7">200</td>
                                                            <td class="dynChildWidth8">200</td>
                                                            <td class="dynChildWidth9">200</td>
                                                        </tr>
                                                    </table>
                                                    </a>
                                                </div>
                                                <div id="collapseOne1" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne1">
                                                  <div class="panel-body">
                                                    <div class="panel-group panel-group2 m_0" id="accordion2" role="tablist" aria-multiselectable="true">
                                                      <div class="panel panel-default panel-customtd">
                                                        <div class="panel-heading" role="tab" id="headingOne11">
                                                            <a role="button" class="accordion2-toggle collapsed" data-toggle="collapse" data-parent="#accordion2" href="#collapseOne11" aria-expanded="true" aria-controls="collapseOne11">
                                                            <table class="table table-col table-condensed" style="display:inline" >
                                                                <tr>
                                                                    <td style="width:188px;">Srikakulam Mandal</td>
                                                                    <td class="dynChildWidth">100</td>
                                                                    <td class="dynChildWidth2">200</td>
                                                                    <td class="dynChildWidth3">200</td>
                                                                    <td class="dynChildWidth4">200</td>
                                                                    <td class="dynChildWidth5">200</td>
                                                                    <td class="dynChildWidth6">200</td>
                                                                    <td class="dynChildWidth7">200</td>
                                                                    <td class="dynChildWidth8">200</td>
                                                                    <td class="dynChildWidth9">200</td>
                                                                </tr>
                                                            </table>
                                                            </a>
                                                        </div>
                                                        <div id="collapseOne11" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne11">
                                                          <div class="panel-body">
                                                            <div class="panel-group panel-group3 m_0" id="accordion3" role="tablist" aria-multiselectable="true">
                                                              <div class="panel panel-default panel-customtd">
                                                                <div class="panel-heading" role="tab" id="headingOne111">
                                                                    <a role="button" class="accordion3-toggle collapsed" data-toggle="collapse" data-parent="#accordion3" href="#collapseOne111" aria-expanded="true" aria-controls="collapseOne111">
                                                                    <table class="table table-col table-condensed" style="display:inline" >
                                                                        <tr>
                                                                            <td style="width:175px;">Srikakulam Panchayat</td>
                                                                            <td class="dynChildWidth">100</td>
                                                                            <td class="dynChildWidth2">200</td>
                                                                            <td class="dynChildWidth3">200</td>
                                                                            <td class="dynChildWidth4">200</td>
                                                                            <td class="dynChildWidth5">200</td>
                                                                            <td class="dynChildWidth6">200</td>
                                                                            <td class="dynChildWidth7">200</td>
                                                                            <td class="dynChildWidth8">200</td>
                                                                            <td class="dynChildWidth9">200</td>
                                                                        </tr>
                                                                    </table>
                                                                    </a>
                                                                </div>
                                                                <div id="collapseOne111" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne111">
                                                                  <div class="panel-body">	
                                                                  </div>
                                                                </div>
                                                              </div>
                                                              <div class="panel panel-default panel-customtd">
                                                                <div class="panel-heading" role="tab" id="headingTwo111">
                                                                  <a class="collapsed accordion3-toggle" role="button" data-toggle="collapse" data-parent="#accordion3" href="#collapseTwo111" aria-expanded="false" aria-controls="collapseTwo111">
                                                                    <table class="table table-col table-condensed" style="display:inline" >
                                                                        <tr>
                                                                            <td style="width:175px;">Ananthapur District</td>
                                                                            <td class="dynChildWidth">100</td>
                                                                            <td class="dynChildWidth2">200</td>
                                                                            <td class="dynChildWidth3">200</td>
                                                                            <td class="dynChildWidth4">200</td>
                                                                            <td class="dynChildWidth5">200</td>
                                                                            <td class="dynChildWidth6">200</td>
                                                                            <td class="dynChildWidth7">200</td>
                                                                            <td class="dynChildWidth8">200</td>
                                                                            <td class="dynChildWidth9">200</td>
                                                                        </tr>
                                                                    </table>
                                                                  </a>
                                                                </div>
                                                                <div id="collapseTwo111" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo111">
                                                                  <div class="panel-body">
                                                                  </div>
                                                                </div>
                                                              </div>
                                                              <div class="panel panel-default panel-customtd ">
                                                                <div class="panel-heading" role="tab" id="headingThree111">
                                                                  <a class="collapsed accordion3-toggle" role="button" data-toggle="collapse" data-parent="#accordion3" href="#collapseThree111" aria-expanded="false" aria-controls="collapseThree111">
                                                                  <table class="table table-col table-condensed" style="display:inline" >
                                                                        <tr>
                                                                            <td style="width:175px;">Ananthapur constituency</td>
                                                                            <td class="dynChildWidth">100</td>
                                                                            <td class="dynChildWidth2">200</td>
                                                                            <td class="dynChildWidth3">200</td>
                                                                            <td class="dynChildWidth4">200</td>
                                                                            <td class="dynChildWidth5">200</td>
                                                                            <td class="dynChildWidth6">200</td>
                                                                            <td class="dynChildWidth7">200</td>
                                                                            <td class="dynChildWidth8">200</td>
                                                                            <td class="dynChildWidth9">200</td>
                                                                        </tr>
                                                                    </table>
                                                                  </a>
                                                                </div>
                                                                <div id="collapseThree111" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree111">
                                                                  <div class="panel-body">
                                                                  </div>
                                                                </div>
                                                              </div>
                                                            </div>

                                                          </div>
                                                        </div>
                                                      </div>
                                                      <div class="panel panel-default panel-customtd">
                                                        <div class="panel-heading" role="tab" id="headingTwo11">
                                                          <a class="collapsed accordion2-toggle" role="button" data-toggle="collapse" data-parent="#accordion2" href="#collapseTwo11" aria-expanded="false" aria-controls="collapseTwo11">
                                                            <table class="table table-col table-condensed" style="display:inline" >
                                                                <tr>
                                                                    <td style="width:190px;">Ananthapur mandal</td>
                                                                    <td class="dynChildWidth">100</td>
                                                                    <td class="dynChildWidth2">200</td>
                                                                    <td class="dynChildWidth3">200</td>
                                                                    <td class="dynChildWidth4">200</td>
                                                                    <td class="dynChildWidth5">200</td>
                                                                    <td class="dynChildWidth6">200</td>
                                                                    <td class="dynChildWidth7">200</td>
                                                                    <td class="dynChildWidth8">200</td>
                                                                    <td class="dynChildWidth9">200</td>
                                                                </tr>
                                                            </table>
                                                          </a>
                                                        </div>
                                                        <div id="collapseTwo11" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo11">
                                                          <div class="panel-body">
                                                          </div>
                                                        </div>
                                                      </div>
                                                      <div class="panel panel-default panel-customtd ">
                                                        <div class="panel-heading" role="tab" id="headingThree11">
                                                          <a class="collapsed accordion2-toggle" role="button" data-toggle="collapse" data-parent="#accordion2" href="#collapseThree11" aria-expanded="false" aria-controls="collapseThree11">
                                                          <table class="table table-col table-condensed" style="display:inline" >
                                                                <tr>
                                                                    <td style="width:190px;">Srikakulam</td>
                                                                    <td class="dynChildWidth">100</td>
                                                                    <td class="dynChildWidth2">200</td>
                                                                    <td class="dynChildWidth3">200</td>
                                                                    <td class="dynChildWidth4">200</td>
                                                                    <td class="dynChildWidth5">200</td>
                                                                    <td class="dynChildWidth6">200</td>
                                                                    <td class="dynChildWidth7">200</td>
                                                                    <td class="dynChildWidth8">200</td>
                                                                    <td class="dynChildWidth9">200</td>
                                                                </tr>
                                                            </table>
                                                          </a>
                                                        </div>
                                                        <div id="collapseThree11" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree11">
                                                          <div class="panel-body">
                                                          </div>
                                                        </div>
                                                      </div>
                                                    </div>

                                                  </div>
                                                </div>
                                              </div>
                                              <div class="panel panel-default panel-customtd">
                                                <div class="panel-heading" role="tab" id="headingTwo1">
                                                  <a class="collapsed accordion1-toggle" role="button" data-toggle="collapse" data-parent="#accordion1" href="#collapseTwo1" aria-expanded="false" aria-controls="collapseTwo1">
                                                    <table class="table table-col table-condensed" style="display:inline" >
                                                        <tr>
                                                            <td style="width:200px;">Ananthapur Constituency</td>
                                                            <td class="dynChildWidth">100</td>
                                                            <td class="dynChildWidth2">200</td>
                                                            <td class="dynChildWidth3">200</td>
                                                            <td class="dynChildWidth4">200</td>
                                                            <td class="dynChildWidth5">200</td>
                                                            <td class="dynChildWidth6">200</td>
                                                            <td class="dynChildWidth7">200</td>
                                                            <td class="dynChildWidth8">200</td>
                                                            <td class="dynChildWidth9">200</td>
                                                        </tr>
                                                    </table>
                                                  </a>
                                                </div>
                                                <div id="collapseTwo1" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo1">
                                                  <div class="panel-body">
                                                  </div>
                                                </div>
                                              </div>
                                              <div class="panel panel-default panel-customtd ">
                                                <div class="panel-heading" role="tab" id="headingThree1">
                                                  <a class="collapsed accordion1-toggle" role="button" data-toggle="collapse" data-parent="#accordion1" href="#collapseThree1" aria-expanded="false" aria-controls="collapseThree1">
                                                  <table class="table table-col table-condensed" style="display:inline" >
                                                        <tr>
                                                            <td style="width:200px;">Srikakulam</td>
                                                            <td class="dynChildWidth">100</td>
                                                            <td class="dynChildWidth2">200</td>
                                                            <td class="dynChildWidth3">200</td>
                                                            <td class="dynChildWidth4">200</td>
                                                            <td class="dynChildWidth5">200</td>
                                                            <td class="dynChildWidth6">200</td>
                                                            <td class="dynChildWidth7">200</td>
                                                            <td class="dynChildWidth8">200</td>
                                                            <td class="dynChildWidth9">200</td>
                                                        </tr>
                                                    </table>
                                                  </a>
                                                </div>
                                                <div id="collapseThree1" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree1">
                                                  <div class="panel-body">
                                                  </div>
                                                </div>
                                              </div>
                                            </div>
                                          </div>
                                        </div>
                                      </div>
                                      <div>
                                        <ul class="villageDays getwidthForRes">
                                            <li >
                                                <table class="table table-col table-condensed" style="display:inline" >
                                                    <tr>
                                                        <td style="width:175px;"><span class="days">DAY 1</span></td>
                                                        <td class="dynChildWidth">100</td>
                                                        <td class="dynChildWidth2">200</td>
                                                        <td class="dynChildWidth3">200</td>
                                                        <td class="dynChildWidth4">200</td>
                                                        <td class="dynChildWidth5">200</td>
                                                        <td class="dynChildWidth6">200</td>
                                                        <td class="dynChildWidth7">200</td>
                                                        <td class="dynChildWidth8">200</td>
                                                        <td class="dynChildWidth9">200</td>
                                                    </tr>
                                                </table>
                                                <ul class="slick-training ">
                                                  <li>
                                                 	 <a class="fancybox" rel="group" href="dist/activity/img/searchicon.png"><img src="dist/activity/img/searchicon.png" alt="" style="height:25px" /></a>
                                                  </li>
                                                  <li>
                                                   	<a class="fancybox" rel="group" href="dist/activity/img/searchicon.png"><img src="dist/activity/img/searchicon.png" alt="" style="height:25px" /></a>
                                                  </li>
                                                </ul>
                                                
                                            </li>
                                            <li>
                                                <table class="table table-col table-condensed" style="display:inline" >
                                                    <tr>
                                                        <td style="width:175px;"><span class="days">DAY 2</span></td>
                                                        <td class="dynChildWidth">100</td>
                                                        <td class="dynChildWidth2">200</td>
                                                        <td class="dynChildWidth3">200</td>
                                                        <td class="dynChildWidth4">200</td>
                                                        <td class="dynChildWidth5">200</td>
                                                        <td class="dynChildWidth6">200</td>
                                                        <td class="dynChildWidth7">200</td>
                                                        <td class="dynChildWidth8">200</td>
                                                        <td class="dynChildWidth9">200</td>
                                                    </tr>
                                                </table>
                                                
                                            </li>
                                        </ul>
                                    </div>

                                      <div class="panel panel-default panel-customtd">
                                        <div class="panel-heading" role="tab" id="headingTwo">
                                          <a class="collapsed accordion-toggle" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                                            <table class="table table-col table-condensed" style="display:inline" >
                                            	<tr>
                                                	<td style="width:210px;">Srikakulam</td>
                                                    <td class="dynChildWidth">100</td>
                                                    <td class="dynChildWidth2">200</td>
                                                    <td class="dynChildWidth3">200</td>
                                                    <td class="dynChildWidth4">200</td>
                                                    <td class="dynChildWidth5">200</td>
                                                    <td class="dynChildWidth6">200</td>
                                                    <td class="dynChildWidth7">200</td>
                                                    <td class="dynChildWidth8">200</td>
                                                    <td class="dynChildWidth9">200</td>
                                                </tr>
                                            </table>
                                          </a>
                                        </div>
                                        <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
                                          <div class="panel-body">
                                          </div>
                                        </div>
                                      </div>
                                      <div class="panel panel-default panel-customtd ">
                                        <div class="panel-heading" role="tab" id="headingThree">
                                          <a class="collapsed accordion-toggle" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                                          <table class="table table-col table-condensed" style="display:inline" >
                                            	<tr>
                                                	<td style="width:210px;">Srikakulam</td>
                                                    <td class="dynChildWidth">100</td>
                                                    <td class="dynChildWidth2">200</td>
                                                    <td class="dynChildWidth3">200</td>
                                                    <td class="dynChildWidth4">200</td>
                                                    <td class="dynChildWidth5">200</td>
                                                    <td class="dynChildWidth6">200</td>
                                                    <td class="dynChildWidth7">200</td>
                                                    <td class="dynChildWidth8">200</td>
                                                    <td class="dynChildWidth9">200</td>
                                                </tr>
                                            </table>
                                          </a>
                                        </div>
                                        <div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
                                          <div class="panel-body">
                                          </div>
                                        </div>
                                      </div>
                                    </div>
                                </td>
                            </tr>
                        </table>-->
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="dist/activity/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="dist/activity/js/bootstrap.js" type="text/javascript"></script>
<script src="dist/activity/js/custom.js" type="text/javascript"></script>
<script src="dist/activity/Date/moment.js" type="text/javascript"></script>
<script src="dist/activity/Date/daterangepicker.js" type="text/javascript"></script>
<script src="dist/activity/SelectDropDown/dropkick.js" type="text/javascript"></script>
<script src="dist/activity/Slick/slick.js" type="text/javascript"></script>
<script src="dist/activity/FancyBox/jquery.fancybox.js" type="text/javascript"></script>
<script type="text/javascript">
$(".select").dropkick();
$(".panel-heading","click",function(){
	if($(this).find(".accordion-toggle,.PlusnMinusSign1,.accordion1-toggle,.accordion2-toggle,.accordion3-toggle").hasClass("collapsed")){
		$(this).parent().parent().find(".bod").removeClass("bod");
		$(this).addClass("bod")
	}else{
		$(this).removeClass("bod")
	}
});
//alert($(".getChildWidth9").width())

//$(".your-class").slick();
$(".villageDays").hide();
$(".btn-hover").click(function()
{
	$(".villageDays").toggle();
	var getWidth=$(".getwidthForRes").width()-50;
	var responsive=$("#alignmentWidth").width();
	$(".slick-training").css("width",getWidth);

	$('.slick-training').slick({
      dots: false,
      slide: 'li',
      infinite: false,
      speed: 300,
      variableWidth: true,
      });
})
$(".fancybox").fancybox();


function getActivityNames()
{
	$('#ActivityList').find('option').remove();
	$('#ActivityList').append('<option value="0"> Select Activity </option>');	
	var jObj = {
			activityTypeId : $('#activityTypeList').val(),
			activityLevelId:$('#activityLevelList').val(),
			task:"activityDetails"
		};
		
		$.ajax({
          type:'GET',
          url: 'getActivityDetails.action',
         data : {task:JSON.stringify(jObj)} ,
        }).done(function(result){			
			if(result != null && result.length >0)
			{
				for(var i in result)
					$('#ActivityList').append('<option value="'+result[i].id+'">'+result[i].name+'</option>');	
			}
		});
		
}

function buildResult(result)
{
	$('#stateWiseViewDid').html('');
	var str='';
	
	str+='<div class="table-responsive">';
	str+='<table class="table table-bordered bg_ff">';
	str+='<tr class="text-center">';
	str+='<td class="pad_0" rowspan="2" style="box-sizing:none;"><img src="dist/activity/img/andhrap.jpg" class="img-responsive head-td" style="height:150px;" ></td>';
	str+='<td><h3 class="m_top20 m_bottom10">'+result.totalCount+'</h3><hr class="custom-hr"/></td>';
	
	if(result.activityVoList != null && result.activityVoList.length>0){
		str+='<td><h3 class="m_top20 m_bottom10">'+result.activityVoList[0].plannedCount+'</h3><hr class="custom-hr"/></td>';
		str+='<td><h3 class="m_top20 m_bottom10">'+result.activityVoList[0].notPlannedCount+'</h3><hr class="custom-hr"/></td>';
		if(result.activityVoList[0].activityVoList != null && result.activityVoList[0].activityVoList.length>0){
			str+='<td><h3 class="m_top20 m_bottom10">'+result.activityVoList[0].activityVoList[0].conductedCount+' ('+result.activityVoList[0].activityVoList[0].percentage+'%)</h3><hr class="custom-hr"/></td>';
			str+='<td><h3 class="m_top20 m_bottom10">'+result.activityVoList[0].activityVoList[1].conductedCount+' ('+result.activityVoList[0].activityVoList[1].percentage+'%)</h3><hr class="custom-hr"/></td>';
			str+='<td><h3 class="m_top20 m_bottom10">'+result.activityVoList[0].activityVoList[2].conductedCount+' ('+result.activityVoList[0].activityVoList[2].percentage+'%)</h3><hr class="custom-hr"/></td>';
			str+='<td><h3 class="m_top20 m_bottom10">'+result.activityVoList[0].activityVoList[3].conductedCount+' ('+result.activityVoList[0].activityVoList[3].percentage+'%)</h3><hr class="custom-hr"/></td>';
		}		
	}
	
	str+='</tr>';
	str+='<tr>';
	str+='<td class="bg_ef text-center">TOTAL VILLAGE/WARD</td>';
	str+='<td class="bg_ef text-center">PLANNED VILLAGE/WARD</td>';
	str+='<td class="bg_ef text-center">NOT PLANNED VILLAGE/WARD</td>';
	str+='<td class="bg_ef text-center">INFO CELL COVERED</td>';
	str+='<td class="bg_ef text-center">IVR COVERED</td>';
	str+='<td class="bg_ef text-center">WHATSAPP IMAGES COVERED</td>';
	str+='<td class="bg_ef text-center">NO OF IMAGE RECEIVED @ WHATSAPP</td>';
	str+='</tr>';
	str+='</table>';
	str+='</div>';
	$('#stateWiseViewDid').html(str);
	dynamicwidth();
}
function getActivityDetailsBySearchCriteria(locationId,searchType,divId)
{
		var jObj = {
		stateId:1,
		searchType:searchType,
		conditionType:"locationWiseResult",		
		startDate:"30-11-2015",
		endDate:"08-12-2015",
		locationId:locationId,
		activityScopeId:1,
		activityLevelId:1,
		task:"getActivityDetailsBySearchCriteria"
		};
		$('#'+divId+'').html('<div style="text-align: center" ><img src="./images/Loading-data.gif" /></div>');
		
		$.ajax({
          type:'GET',
          url: 'getActivityDetailsBySearchCriteria.action',
         data : {task:JSON.stringify(jObj)} ,
        }).done(function(result){
			console.log(result);
			if(result != null)
				if(searchType == 'state')
					buildResult(result);
				else if(searchType == 'district')
					buildsLocationsResult(result,divId);
				else if(searchType == 'constituency')
					buildConstituencyResult(result,divId,locationId);
				else if(searchType == 'mandal')
					buildMandalResult(result,divId,locationId);
				else if(searchType == 'village')
					buildVillageResult(result,divId,locationId);
		});
}


function buildVillageResult(result,divId,locationId)
{	
	var str='';
	if(result.activityVoList != null && result.activityVoList.length>0)
	{
		for(var i in result.activityVoList)
			{				
			str+='<div class="panel-body">';
			str+='<div class="panel-group panel-group1 m_0" id="collapseOne1LevelMandal1'+i+'" role="tablist" aria-multiselectable="true">';
			str+='<div class="panel panel-default panel-customtd">';
			
			str+='<div class="panel-heading" role="tab" id="headingOneLevelMandal1'+i+'">';
			//str+='<a role="button" onclick="getActivityDetailsBySearchCriteria(\''+result.activityVoList[i].id+'\',\'village\',\'panchayatLevelId'+i+'\');" class="accordion1Level1'+i+'-toggle accordion-toggle collapsed" data-toggle="collapse" data-parent="#collapseOne1LevelMandal1'+i+'" href="#collapseOne1LevelPanchayat1'+i+'" aria-expanded="true" aria-controls="collapseOne1LevelMandal1'+i+'">';
			
				str+='<table class="table table-col table-condensed" style="display:inline" >';
				str+='<tr>';
				str+='<td style="width:210px;">'+result.activityVoList[i].name+'</td>';
				if(result.activityVoList[i].totalCount != null && result.activityVoList[i].totalCount >0)
					str+='<td class="dynChildWidth">'+result.activityVoList[i].totalCount+'</td>';
				else
					str+='<td class="dynChildWidth"> - </td>';
				
				if(result.activityVoList[i].plannedCount != null && result.activityVoList[i].plannedCount >0)
					str+='<td class="dynChildWidth">'+result.activityVoList[i].plannedCount+'</td>';
				else
					str+='<td class="dynChildWidth"> - </td>';
				
				if(result.activityVoList[i].notPlannedCount != null && result.activityVoList[i].notPlannedCount >0)
					str+='<td class="dynChildWidth">'+result.activityVoList[i].notPlannedCount+'</td>';
				else
					str+='<td class="dynChildWidth"> - </td>';

				if(result.activityVoList[i].activityVoList != null && result.activityVoList[i].activityVoList.length>0)
				{
					for(var j in result.activityVoList[i].activityVoList)
					{
						str+='<td class="dynChildWidth3">'+result.activityVoList[i].activityVoList[j].conductedCount+'</td>';
					}
				}				
				str+='<td class="dynChildWidth3"> - </td>';
				str+='<td class="dynChildWidth3"> - </td>';
				
				str+='<tr>';
				str+='</table>';
			//	str+='</a>';
			str+='<button type="button" class="btn btn-custom btn-hover btn-xs"><i class="glyphicon glyphicon-align-justify"></i></button>';
			str+='<button type="button" class="btn btn-custom btn-hover btn-xs">Day Wise</button>';
				str+='</div>';

					str+='</div>';
				str+='</div>';
			str+='</div>';
			
			}
	}
	
	$('#'+divId+'').html(str);
	dynamicwidth();
}


function buildMandalResult(result,divId,locationId)
{	
	var str='';
	if(result.activityVoList != null && result.activityVoList.length>0)
	{
		for(var i in result.activityVoList)
			{				
			str+='<div class="panel-body">';
			str+='<div class="panel-group panel-group1 m_0" id="collapseOne1LevelMandal1'+i+'" role="tablist" aria-multiselectable="true">';
			str+='<div class="panel panel-default panel-customtd">';
			
			str+='<div class="panel-heading" role="tab" id="headingOneLevelMandal1'+i+'">';
			str+='<a role="button" onclick="getActivityDetailsBySearchCriteria(\''+result.activityVoList[i].id+'\',\'village\',\'panchayatLevelId'+i+'\');" class="accordion1Level1'+i+'-toggle accordion-toggle collapsed" data-toggle="collapse" data-parent="#collapseOne1LevelMandal1'+i+'" href="#collapseOne1LevelPanchayat1'+i+'" aria-expanded="true" aria-controls="collapseOne1LevelMandal1'+i+'">';
			
				str+='<table class="table table-col table-condensed" style="display:inline" >';
				str+='<tr>';
				str+='<td style="width:210px;">'+result.activityVoList[i].name+'</td>';
				if(result.activityVoList[i].totalCount != null && result.activityVoList[i].totalCount >0)
					str+='<td class="dynChildWidth">'+result.activityVoList[i].totalCount+'</td>';
				else
					str+='<td class="dynChildWidth"> - </td>';
				
				if(result.activityVoList[i].plannedCount != null && result.activityVoList[i].plannedCount >0)
					str+='<td class="dynChildWidth">'+result.activityVoList[i].plannedCount+'</td>';
				else
					str+='<td class="dynChildWidth"> - </td>';
				
				if(result.activityVoList[i].notPlannedCount != null && result.activityVoList[i].notPlannedCount >0)
					str+='<td class="dynChildWidth">'+result.activityVoList[i].notPlannedCount+'</td>';
				else
					str+='<td class="dynChildWidth"> - </td>';
				if(result.activityVoList[i].activityVoList != null && result.activityVoList[i].activityVoList.length>0)
				{
					for(var j in result.activityVoList[i].activityVoList)
					{
						str+='<td class="dynChildWidth3">'+result.activityVoList[i].activityVoList[j].conductedCount+'</td>';
					}
				}				
				str+='<td class="dynChildWidth3"> - </td>';
				str+='<td class="dynChildWidth3"> - </td>';
				
				str+='<tr>';
				str+='</table>';
				str+='</a>';
				str+='<button type="button" class="btn btn-custom btn-hover btn-xs"><i class="glyphicon glyphicon-align-justify"></i></button>';
			str+='<button type="button" class="btn btn-custom btn-hover btn-xs">Day Wise</button>';
				str+='</div>';
				str+='<div id="collapseOne1LevelPanchayat1'+i+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOneLevelMandal1'+i+'">';
					str+='<div id="panchayatLevelId'+i+'"></div>';
					str+='</div>';
				str+='</div>';
			str+='</div>';
			str+='</div>';
			str+='</div>';
			str+='</div>';
			
			}
	}
	
	$('#'+divId+'').html(str);
	dynamicwidth();
}

function buildConstituencyResult(result,divId,locationId)
{	
	var str='';
	if(result.activityVoList != null && result.activityVoList.length>0)
	{
		for(var i in result.activityVoList)
			{				
			str+='<div class="panel-body">';
			str+='<div class="panel-group panel-group1 m_0" id="accordion1Level1'+i+'" role="tablist" aria-multiselectable="true">';
			str+='<div class="panel panel-default panel-customtd">';
			
			str+='<div class="panel-heading" role="tab" id="headingOneLevel1'+i+'">';
			str+='<a role="button" onclick="getActivityDetailsBySearchCriteria(\''+result.activityVoList[i].id+'\',\'mandal\',\'mandalLevelId'+i+'\');" class="accordion1Level1'+i+'-toggle accordion-toggle collapsed" data-toggle="collapse" data-parent="#accordion1Level1'+i+'" href="#collapseOne1LevelMandal1'+i+'" aria-expanded="true" aria-controls="collapseOne1LevelMandal1'+i+'">';
			
				str+='<table class="table table-col table-condensed" style="display:inline" >';
				str+='<tr>';
				str+='<td style="width:210px;">'+result.activityVoList[i].name+'</td>';
				if(result.activityVoList[i].totalCount != null && result.activityVoList[i].totalCount >0)
					str+='<td class="dynChildWidth">'+result.activityVoList[i].totalCount+'</td>';
				else
					str+='<td class="dynChildWidth"> - </td>';
				
				if(result.activityVoList[i].plannedCount != null && result.activityVoList[i].plannedCount >0)
					str+='<td class="dynChildWidth">'+result.activityVoList[i].plannedCount+'</td>';
				else
					str+='<td class="dynChildWidth"> - </td>';
				
				if(result.activityVoList[i].notPlannedCount != null && result.activityVoList[i].notPlannedCount >0)
					str+='<td class="dynChildWidth">'+result.activityVoList[i].notPlannedCount+'</td>';
				else
					str+='<td class="dynChildWidth"> - </td>';
				if(result.activityVoList[i].activityVoList != null && result.activityVoList[i].activityVoList.length>0)
				{
					for(var j in result.activityVoList[i].activityVoList)
					{
						str+='<td class="dynChildWidth3">'+result.activityVoList[i].activityVoList[j].conductedCount+'</td>';
					}
				}				
				str+='<td class="dynChildWidth3"> - </td>';
				str+='<td class="dynChildWidth3"> - </td>';
				
				str+='<tr>';
				str+='</table>';
				str+='</a>';
				str+='<button type="button" class="btn btn-custom btn-hover btn-xs"><i class="glyphicon glyphicon-align-justify"></i></button>';
			str+='<button type="button" class="btn btn-custom btn-hover btn-xs">Day Wise</button>';
				str+='</div>';
				
				str+='<div id="collapseOne1LevelMandal1'+i+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOneLevel1'+i+'">';
				str+='<div id="mandalLevelId'+i+'"></div>';
			
				str+='</div>';
				str+='</div>';
			str+='</div>';
			str+='</div>';
			str+='</div>';
			str+='</div>';
			
			}
	}
	
	$('#'+divId+'').html(str);
	dynamicwidth();
}
function buildsLocationsResult(result,divId){
	
	$('#'+divId+'').html('');
	var str='';
	str+='<table class="table table-condensed">';
	str+='<caption class="cap-custom"><b>DISTRICT WISE - VILLAGE/WARD</b></caption>';
	str+='<tr class="font-12">';
	str+='<td class="dynWidth" style="width:220px">LOCATION NAME</td>';
	str+='<td class="getChildWidth">TOTAL</td>';
	str+='<td class="getChildWidth2">PLANNED</td>';
	str+='<td class="getChildWidth10">NOT PLANNED</td>';
	str+='<td class="getChildWidth3">INFO CELL COVERED</td>';
	str+='<td class="getChildWidth4">INFO CELL COVERED %</td>';
	str+='<td class="getChildWidth5">IVR COVERED</td>';
	str+='<td class="getChildWidth6">IVR COVERED %</td>';
	str+='<td class="getChildWidth7">WHATSAPP IMAGES COVERED</td>';
	str+='<td class="getChildWidth9">NO OF IMAGE RECEIVED @ WHATSAPP</td>';
	str+='</tr>';
		
    
	if(result.activityVoList != null && result.activityVoList.length>0)
	{
		for(var i in result.activityVoList)
		{
			str+='<tr>';
			str+='<td colspan="11" class="pad_0">';
			str+='<div class="panel-group m_0" id="accordion'+i+'" role="tablist" aria-multiselectable="true">';
			str+='<div class="panel panel-default panel-customtd">';
			
			str+='<div class="panel-heading" role="tab" id="headingOneLevel1'+i+'">';
			str+='<a role="button" onclick="getActivityDetailsBySearchCriteria(\''+result.activityVoList[i].id+'\',\'constituency\',\'constituencyLevelId'+i+'\');" class="accordion'+i+'-toggle accordion-toggle PlusnMinusSign collapsed" data-toggle="collapse" data-parent="#accordion'+i+'" href="#collapseOneLevel1'+i+'" aria-expanded="true" aria-controls="collapseOneLevel1'+i+'">';
			str+='<table class="table table-col table-condensed" style="display:inline" >';
			str+='<tr>';
			str+='<td style="width:210px;">'+result.activityVoList[i].name+'</td>';
			if(result.activityVoList[i].totalCount != null && result.activityVoList[i].totalCount >0)
					str+='<td class="dynChildWidth">'+result.activityVoList[i].totalCount+'</td>';
				else
					str+='<td class="dynChildWidth"> - </td>';
				
				if(result.activityVoList[i].plannedCount != null && result.activityVoList[i].plannedCount >0)
					str+='<td class="dynChildWidth">'+result.activityVoList[i].plannedCount+'</td>';
				else
					str+='<td class="dynChildWidth"> - </td>';
				
				if(result.activityVoList[i].notPlannedCount != null && result.activityVoList[i].notPlannedCount >0)
					str+='<td class="dynChildWidth">'+result.activityVoList[i].notPlannedCount+'</td>';
				else
					str+='<td class="dynChildWidth"> - </td>';
			if(result.activityVoList[i].activityVoList != null && result.activityVoList[i].activityVoList.length>0)
			{
				for(var j in result.activityVoList[i].activityVoList)
				{
					str+='<td class="dynChildWidth3">'+result.activityVoList[i].activityVoList[j].conductedCount+'</td>';
				}
			}
			
			str+='<td class="dynChildWidth3"> - </td>';
			str+='<td class="dynChildWidth3"> - </td>';
			
			str+='</tr>';
			str+='</table>';
			str+='</a>';
			str+='<button type="button" class="btn btn-custom btn-hover btn-xs"><i class="glyphicon glyphicon-align-justify"></i></button>';
			str+='<button type="button" class="btn btn-custom btn-hover btn-xs" onclick="getDaywiseInfo(\'district\','+result.activityVoList[i].id+',\'dayWiseInfo'+result.activityVoList[i].id+'\')">Day Wise</button>';
			str+='</div>';			
			str+='<div id="constituencyLevelId'+i+'">';
			str+='</div>';
			str+='<div id="collapseOneLevel1'+i+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOneLevel1'+i+'">';
			
			str+='<div id="constituencyLevelId'+i+'">';
			str+='</div>';

		
		str+='</div>';
		str+='</div>';
		str+='<div id="dayWiseInfo'+result.activityVoList[i].id+'">';
		str+='</div>';
		
		str+='</td>';
		str+='</tr>';
		}		
	}

	str+='</table>';
	$('#'+divId+'').html(str);
	dynamicwidth();

}
function dynamicwidth()
{
	$(".dynChildWidth9").css("width",$(".getChildWidth9").width());
	$(".dynChildWidth8").css("width",$(".getChildWidth8").width()+10);
	$(".dynChildWidth7").css("width",$(".getChildWidth7").width()+10);
	$(".dynChildWidth6").css("width",$(".getChildWidth6").width()+12);
	$(".dynChildWidth5").css("width",$(".getChildWidth5").width()+8);
	$(".dynChildWidth4").css("width",$(".getChildWidth4").width()+8);
	$(".dynChildWidth3").css("width",$(".getChildWidth3").width()+8);
	$(".dynChildWidth2").css("width",$(".getChildWidth2").width()+8);
	$(".dynChildWidth").css("width",$(".getChildWidth").width()+12);
}


function getDaywiseInfo(searchType,locationId,divId)
{
		var jObj = {
		stateId:1,
		searchType:searchType,
		conditionType:"daywiseResult",
		locationId:locationId,
		activityScopeId:1,
		activityLevelId:1,
		startDate:"30-11-2015",
		endDate:"08-12-2015",
		task:"getActivityDetailsBySearchCriteria"
		};
			$('#'+divId+'').html('<div style="text-align: center" ><img src="./images/Loading-data.gif" /></div>');
		
		$.ajax({
          type:'GET',
          url: 'getActivityDetailsBySearchCriteria.action',
         data : {task:JSON.stringify(jObj)} ,
        }).done(function(result){
			console.log(result);
			if(result != null){
				buildDayWiseResults(result,divId);
			}
		});
}

function buildDayWiseResults(result,divId)
{
	var str='';
	
	str+='<div>';
	str+='<ul class="villageDays getwidthForRes">';
	if(result.activityVoList != null && result.activityVoList.length>0)
	{
		
		for(var i in result.activityVoList)
		{
			var clsCount = 0;
			str+='<li>';
			str+='<table class="table table-col table-condensed" style="display:inline" >';
			str+='<tr>';
			str+='<td style="width:175px;"><span class="days">'+result.activityVoList[i].name+'</span></td>';
			if(result.activityVoList[i].totalCount != null && result.activityVoList[i].totalCount>0)
				str+='<td class="dynChildWidth3">'+result.activityVoList[i].totalCount+'</td>';
			else
				str+='<td class="dynChildWidth3"> - </td>';
					
			clsCount = parseInt(clsCount)+parseInt(i+1);
			if(result.activityVoList[i].activityVoList != null && result.activityVoList[i].activityVoList.length>0)
			{
				for(var j in result.activityVoList[i].activityVoList)
				{
					clsCount = parseInt(clsCount)+parseInt(j+1);	
					str+='<td class="dynChildWidth3">'+result.activityVoList[i].activityVoList[j].conductedCount+'</td>';
					str+='<td class="dynChildWidth3">'+result.activityVoList[i].activityVoList[j].percentage+'</td>';
				}
			}
			str+='</tr>';
			str+='</table>';
			/*
			str+='<ul class="slick-training ">';
			str+='<li>';
			str+='<a class="fancybox" rel="group" href="dist/activity/img/searchicon.png"><img src="dist/activity/img/searchicon.png" alt="" style="height:25px" /></a>';
			str+='</li>';
			str+='<li>';
			str+='<a class="fancybox" rel="group" href="dist/activity/img/searchicon.png"><img src="dist/activity/img/searchicon.png" alt="" style="height:25px" /></a>';
			str+='</li>';
			str+='</ul>';
*/
			str+='</li>';
		}
	}
	
	
	
	str+='</ul>';
	str+='</div>';
	$('#'+divId+'').html(str);
	dynamicwidth();
}
getActivityDetailsBySearchCriteria(1,'state','stateWiseViewDid');
getActivityDetailsBySearchCriteria(1,'district','alignmentWidth');
</script>
</body>
</html>
