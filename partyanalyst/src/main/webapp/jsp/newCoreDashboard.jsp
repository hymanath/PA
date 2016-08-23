<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>New Core DashBoard</title>
<link href="newCoreDashBoard/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="newCoreDashBoard/css/custom.css" rel="stylesheet" type="text/css">
<link href="newCoreDashBoard/responsive.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Oswald" rel="stylesheet" type="text/css">
<link href="newCoreDashBoard/Plugins/Slick/slick.css" type="text/css" rel="stylesheet"/>
<link href="newCoreDashBoard/Plugins/Slick/slick-theme.css" type="text/css" rel="stylesheet"/>
</head>
<body>
<div class="container m_top20">
	<div class="row">
    	<div class="col-md-6 col-xs-12 col-sm-8 col-sm-offset-2 col-md-offset-0 committeesBlock">
        	<div class="panel panel-default panelNewCustom">
            	<div class="panel-heading">
                	<h4 class="panel-title">committees
                    	<span class="settingsIcon pull-right">
                        	<i class="fa fa-gears"  data-toggle="tooltip" data-placement="top" title="Settings"></i>
                        </span>
                    	<span class="notesIcon pull-right">
                        	<i class="glyphicon glyphicon-list-alt"  data-toggle="tooltip" data-placement="top" title="Notes"></i>
                        </span>
                    	<span class="iconExpand pull-right">
                        	<i class="glyphicon glyphicon-fullscreen" data-toggle="tooltip" data-placement="top" title="Expand"></i>
                        </span>
                    </h4>
                    <div class="notesDropDown">
                    	<h4 class="text-capital">notes
                        	<span class="pull-right">
                            	<i class="glyphicon glyphicon-list-alt"></i>
                            </span>
                        </h4>
                    	<ul class="notesUl m_top20">
                        	<li>
                            	<span class="notesText">notes on committees notes on committee notes on committee notes on committee notes on committee </span>- <span class="text-muted"><i>20-July 2016</i></span>
                            	<i class="glyphicon glyphicon-trash pull-right hoverBlock deleteNotes"></i>
                                <i class="glyphicon glyphicon-edit pull-right hoverBlock editNotes"></i>
                            </li>
                        </ul>
                        <hr/>
                        <label>Create Notes</label>
                        <textarea class="form-control notesArea"></textarea>
                        <button class="btn btn-default btnCustomCreate btn-sm">create</button>
                    </div>
                </div>
                <div class="panel-body">
              		<div class="row">
                    	<div id="basicCommitteeCountsDiv"></div>
                        <div class="col-md-12 col-xs-12 col-sm-10 col-sm-offset-1 col-md-offset-0 committeesBlock committeesHiddenBlock">
                        	<div class="row">
                            	<div class="col-md-12 col-xs-12 col-sm-12">
                                	<ul class="activeUlCls list-inline pull-right">
                                    	<li class="active">Top 5 Poor</li>
                                        <li>Top 5 Strong</li>
                                    </ul>
                                </div>
                                <div class="col-md-12 col-xs-12 col-sm-12">
                                	<h4 class="text-capital">general secretary</h4>
		                            <div id="genSec" style="width:100%;height:100px;"></div>
                                </div>
                                <div class="col-md-12 col-xs-12 col-sm-12">
                                	<h4 class="text-capital">secretary</h4>
		                            <div id="Secretary" style="width:100%;height:100px;"></div>
                                </div>
                                <div class="col-md-12 col-xs-12 col-sm-12">
                                	<h4 class="text-capital">member of parliament</h4>
		                            <div id="memOfParliament" style="width:100%;height:100px;"></div>
                                </div>
                                <div class="col-md-12 col-xs-12 col-sm-12">
                                	<h4 class="text-capital">district incharges</h4>
		                            <div id="disIncharges" style="width:100%;height:100px;"></div>
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-12 col-sm-12 col-md-12">
                        	<i class="glyphicon glyphicon-option-horizontal pull-right moreBlocksIcon" data-toggle="tooltip" data-placement="top" title="Click here for more"></i>
                        </div>
                        <div class="col-md-12 col-xs-12 col-sm-10 col-sm-offset-1 col-md-offset-0 moreBlocks">
                        	<ul class="list-inline pull-right activeUlCls">
                            	<li class="active">Detailed</li>
                                <li>Comparison</li>
                                <li><i class="fa fa-gears"></i></li>
                            </ul>
                        </div>
                        <div class="col-md-12 col-xs-12 col-sm-10 col-sm-offset-1 col-md-offset-0 moreBlocks">
                        	
                        	<div class="panel panel-default">
                            	<div class="panel-body">
                                	<ul class="villageWardUl">
                                    	<li>
                                        	<div id="mainCommittees" class="chartLi"></div>
   											<ul id="customLegend" class="customLegend"></ul>
                                        </li>
                                        <li>
                                        	<div id="teluguYuvatha" class="chartLi"></div>
   											<ul id="customLegend1" class="customLegend"></ul>
                                        </li>
                                        <li>
                                        	<div id="teluguMahila" class="chartLi"></div>
   											<ul id="customLegend2" class="customLegend"></ul>
                                        </li>
                                        <li>
                                        	<div id="teluguRythu" class="chartLi"></div>
   											<ul id="customLegend3" class="customLegend"></ul>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <!--<div class="col-md-12 col-xs-12 col-sm-12 moreBlocks">
                        	<div class="row">
                            	<div class="col-md-6 col-xs-12 col-sm-10 col-sm-offset-1 col-md-offset-0 col-md-offset-0">
                                	<div class="panel panel-default panelNew">
                                    	<div class="panel-heading">
                                        	<span class="headingColor text-capitalize">logically complete - status not updated</span>
                                        </div>
                                        <div class="panel-body">
                                        	<table class="table table-condensed tableLevels">
                                            	<thead class="bg_ED">
                                                	<th>Level</th>
                                                    <th>Main</th>
                                                    <th>%</th>
                                                    <th>Affliated</th>
                                                    <th>%</th>
                                                </thead>
                                                <tr>
                                                	<td>Village/ Ward</td>
                                                    <td>11250</td>
                                                    <td>80%</td>
                                                    <td>1500</td>
                                                    <td>70%</td>
                                                </tr>
                                                <tr>
                                                	<td>Mandal/ Town/ Village</td>
                                                    <td>11250</td>
                                                    <td>80%</td>
                                                    <td>1500</td>
                                                    <td>70%</td>
                                                </tr>
                                                <tr>
                                                	<td>District</td>
                                                    <td>11250</td>
                                                    <td>80%</td>
                                                    <td>1500</td>
                                                    <td>70%</td>
                                                </tr>
                                                <tr>
                                                	<td>State</td>
                                                    <td>11250</td>
                                                    <td>80%</td>
                                                    <td>1500</td>
                                                    <td>70%</td>
                                                </tr>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6 col-xs-12 col-sm-10 col-sm-offset-1 col-md-offset-0 col-md-offset-0">
                                	<div class="panel panel-default panelNew">
                                    	<div class="panel-heading">
                                        	<div class="row">
                                            	<div class="col-md-8 col-xs-12 col-sm-12">
                                                	<span class="headingColor text-capitalize">running status - lapsed</span>
                                                </div>
                                                <div class="col-md-4 col-xs-12 col-sm-12">
                                                	<ul class="activeUlCls list-inline">
                                                    	<li class="active">Main</li>
                                                        <li>Affliated</li>
                                                    </ul>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="panel-body">
                                        	<table class="table table-condensed tableLevels">
                                            	<thead class="bg_ED">
                                                	<th>Level</th>
                                                    <th>&gt; 1Week</th>
                                                    <th>&gt; 2Week</th>
                                                    <th>&gt; 3Week</th>
                                                    <th>&gt; 1months</th>
                                                    <th>&gt; 3months</th>
                                                </thead>
                                                <tr>
                                                	<td>Village/ Ward</td>
                                                    <td>250</td>
                                                    <td>300</td>
                                                    <td>300</td>
                                                    <td>300</td>
                                                    <td>300</td>
                                                </tr>
                                                <tr>
                                                	<td>Mandal/ Town/ Village</td>
                                                    <td>250</td>
                                                    <td>300</td>
                                                    <td>300</td>
                                                    <td>300</td>
                                                    <td>300</td>
                                                </tr>
                                                <tr>
                                                	<td>District</td>
                                                    <td>250</td>
                                                    <td>300</td>
                                                    <td>300</td>
                                                    <td>300</td>
                                                    <td>300</td>
                                                </tr>
                                                <tr>
                                                	<td>State</td>
                                                    <td>250</td>
                                                    <td>300</td>
                                                    <td>300</td>
                                                    <td>300</td>
                                                    <td>300</td>
                                                </tr>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>-->
                        <div class="col-md-12 col-xs-12 col-sm-10 col-sm-offset-1 col-md-offset-0 m_top20 moreBlocks">
                        	<div class="panel panel-default panelNew">
                                <div class="panel-heading">
                                    <div class="row">
                                        <div class="col-md-8 col-xs-12 col-sm-12">
                                            <span class="headingColor text-capitalize">all committees performance cohort</span>
                                        </div>
                                        <div class="col-md-4 col-xs-12 col-sm-12">
                                            <ul class="activeUlCls list-inline">
                                                <li class="active">All</li>
                                                <li>Yet To Start</li>
                                                <li>Completed</li>
                                                <li>Started</li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                                <div class="panel-body">
                                	<div>
                                    	<h4>Main Committee</h4>
                                    	<ul class="graphStructure">
                                        	<li>
                                            	<span attr_percent="33"></span>
                                                <p class="name">koushik</p>
                                            </li>
                                            <li>
                                            	
                                            	<span attr_percent="33"></span>
                                                <span attr_percent="33"></span>
                                                <span attr_percent="33"></span>
                                                <p class="name">koushik</p>
                                            </li>
                                            <li>
                                            	
                                            	<span attr_percent="60"></span>
                                                <span attr_percent="33"></span>
                                                <span attr_percent="33"></span>
                                                <p class="name">koushik</p>
                                            </li>
                                            <li>
                                            	
                                            	<span attr_percent="50"></span>
                                                <span attr_percent="50"></span>
                                                <p class="name">koushik</p>
                                            </li>
                                            <li>
                                            	
                                            	<span attr_percent="33"></span>
                                                <span attr_percent="90"></span>
                                                <span attr_percent="50"></span>
                                                <p class="name">koushik</p>
                                            </li>
                                        </ul>
                                    </div>
                                    <hr/>
                                    <div class="m_top20">
                                    	<h4>Telugu Yuvatha</h4>
                                    	<ul class="graphStructure">
                                        	<li>
                                            	<span attr_percent="33"></span>
                                                <p class="name">koushik</p>
                                            </li>
                                            <li>
                                            	
                                            	<span attr_percent="33"></span>
                                                <span attr_percent="33"></span>
                                                <span attr_percent="33"></span>
                                                <p class="name">koushik</p>
                                            </li>
                                            <li>
                                            	
                                            	<span attr_percent="33"></span>
                                                <span attr_percent="33"></span>
                                                <span attr_percent="33"></span>
                                                <p class="name">koushik</p>
                                            </li>
                                            <li>
                                            	
                                            	<span attr_percent="50"></span>
                                                <span attr_percent="50"></span>
                                                <p class="name">koushik</p>
                                            </li>
                                            <li>
                                            	
                                            	<span attr_percent="50"></span>
                                                <span attr_percent="50"></span>
                                                <p class="name">koushik</p>
                                            </li>
                                        </ul>
                                    </div>
                                    <hr/>
                                    <div class="m_top20">
                                    	<h4>Telugu Mahila</h4>
                                    	<ul class="graphStructure">
                                        	<li>
                                            	<span attr_percent="33"></span>
                                                <p class="name">koushik</p>
                                            </li>
                                            <li>
                                            	
                                            	<span attr_percent="33"></span>
                                                <span attr_percent="33"></span>
                                                <span attr_percent="33"></span>
                                                <p class="name">koushik</p>
                                            </li>
                                            <li>
                                            	
                                            	<span attr_percent="33"></span>
                                                <span attr_percent="33"></span>
                                                <span attr_percent="33"></span>
                                                <p class="name">koushik</p>
                                            </li>
                                            <li>
                                            	
                                            	<span attr_percent="50"></span>
                                                <span attr_percent="50"></span>
                                                <p class="name">koushik</p>
                                            </li>
                                            <li>
                                            	
                                            	<span attr_percent="50"></span>
                                                <span attr_percent="50"></span>
                                                <p class="name">koushik</p>
                                            </li>
                                        </ul>
                                    </div>
                                    <hr/>
                                    <div class="m_top20">
                                    	<h4>Telugu Rythu</h4>
                                    	<ul class="graphStructure">
                                        	<li>
                                            	<span attr_percent="33"></span>
                                                <p class="name">koushik</p>
                                            </li>
                                            <li>
                                            	
                                            	<span attr_percent="33"></span>
                                                <span attr_percent="33"></span>
                                                <span attr_percent="33"></span>
                                                <p class="name">koushik</p>
                                            </li>
                                            <li>
                                            	
                                            	<span attr_percent="33"></span>
                                                <span attr_percent="33"></span>
                                                <span attr_percent="33"></span>
                                                <p class="name">koushik</p>
                                            </li>
                                            <li>
                                            	
                                            	<span attr_percent="50"></span>
                                                <span attr_percent="50"></span>
                                                <p class="name">koushik</p>
                                            </li>
                                            <li>
                                            	
                                            	<span attr_percent="50"></span>
                                                <span attr_percent="50"></span>
                                                <p class="name">koushik</p>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-12 col-xs-12 col-sm-12 m_top20 moreBlocks">
                            <p><i>Selected:</i> <b>Main Committee</b></p>
                            <div class="panel panel-default panelNew">
                                <div class="panel-heading">
                                    <div class="row">
                                        <div class="col-xs-12 col-sm-7 col-md-8">
                                            <ul class="comparisonSelect">
                                                <li class="active">General Secretary<span class="closeIconComparison"></span></li>
                                                <li>Secretary<span class="closeIconComparison"></span></li>
                                                <li>M.P<span class="closeIconComparison"></span></li>
                                                <li>District Incharge<span class="closeIconComparison"></span></li>
                                                <li>Constituency<span class="closeIconComparison"></span></li>
                                            </ul>
                                        </div>
                                        <div class="col-xs-12 col-sm-5 col-md-4">
                                            <ul class="list-inline">
                                                <li>
                                                    <div class="dropdown">
                                                      <button class="btn btn-default dropdown-toggle dateFinalDiv" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                                                        This Week <span class="text-muted">Sunday - Today</span>
                                                        <span class="caret"></span>
                                                      </button>
                                                      <ul class="dropdown-menu calenarUl" aria-labelledby="dropdownMenu1">
                                                        <li><a href="#">LifeTime</a></li>
                                                        <li role="separator" class="divider"></li>
                                                        <li><a href="#">This Week <span class="text-muted">Sunday - Today</span></a></li>
                                                        <li><a href="#">Last Week <span class="text-muted lastWeekDate"></span></a></li>
                                                        <li><a href="#">Last 7 Days <span class="text-muted last7Days"></span></a></li>
                                                        <li><a href="#">First 7 Days <span class="text-muted first7Days"></span></a></li>
                                                        <li role="separator" class="divider"></li>
                                                        <li><a href="#">This Month <span class="text-muted thisMonth"></span></a></li>
                                                        <li><a href="#">Last Month <span class="text-muted lastMonth"></span></a></li>
                                                        <li><a href="#">Last 28 Days <span class="text-muted last28Days"></span></a></li>
                                                        <li><a href="#">Last 30 Days <span class="text-muted last30Days"></span></a></li>
                                                        <li><a href="#">First 28 Days<span class="text-muted first28Days"></span></a></li>
                                                        <li role="separator" class="divider"></li>
                                                        <li><a href="#">This Quarter <span class="text-muted thisQuarter"></span></a></li>
                                                        <li><a href="#">Last Quarter <span class="text-muted lastQuarter"></span></a></li>
                                                        <li><a href="#">Last 90 Days <span class="text-muted Last90Days"></span></a></li>
                                                        <li><a href="#">First 90 Days <span class="text-muted first90Days"></span></a></li>
                                                        <li role="separator" class="divider"></li>
                                                        <li><a href="#">This Year<span class="text-muted thisYear"></span></a></li>
                                                        <li><a href="#">Last Year <span class="text-muted lastYear"></span></a></li>
                                                        <li><a href="#">Last 365 Days <span class="text-muted last365Days"></span></a></li>
                                                        <li><a href="#">First 365 Days <span class="text-muted first365Days"></span></a></li>
                                                        <li role="separator" class="divider"></li>
                                                        <li><a href="#">Custom Range <span class="customRange"></span></a></li>
                                                      </ul>
                                                    </div>
                                                </li>
                                                <li>
                                                    <i class="glyphicon glyphicon-calendar calendarIcon"></i>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                                <div class="panel-body">
                                    <div class="row">
                                        <div class="col-md-12 col-xs-12 col-sm-12">
                                            <ul class="list-inline slickPanelSlider">
                                                <li>
                                                    <div class="panel panel-default panelSlick">
                                                        <div class="panel-heading">
                                                            <h4 class="panel-title">B Jaya Nageshwara Reddy</h4>
                                                            <span class="count">01</span>
                                                        </div>
                                                        <div class="panel-body">
                                                            <h4 class="text-capital">General Secretary</h4>
                                                            <table class="table table-condensed">
                                                                <thead>
                                                                    <th>Total</th>
                                                                    <th>Started</th>
                                                                    <th>Completed</th>
                                                                    <th>%</th>
                                                                </thead>
                                                                <tr>
                                                                    <td>100</td>
                                                                    <td>100</td>
                                                                    <td>100</td>
                                                                    <td>100%</td>
                                                                </tr>
                                                            </table>
                                                        </div>
                                                    </div>
                                                </li>
                                                <li>
                                                    <div class="panel panel-default panelSlick">
                                                        <div class="panel-heading">
                                                            <h4 class="panel-title">B Jaya Nageshwara Reddy</h4>
                                                            <span class="count">01</span>
                                                        </div>
                                                        <div class="panel-body">
                                                            <h4 class="text-capital">General Secretary</h4>
                                                            <table class="table table-condensed">
                                                                <thead>
                                                                    <th>Total</th>
                                                                    <th>Started</th>
                                                                    <th>Completed</th>
                                                                    <th>%</th>
                                                                </thead>
                                                                <tr>
                                                                    <td>100</td>
                                                                    <td>100</td>
                                                                    <td>100</td>
                                                                    <td>100%</td>
                                                                </tr>
                                                            </table>
                                                        </div>
                                                    </div>
                                                </li>
                                                <li>
                                                    <div class="panel panel-default panelSlick">
                                                        <div class="panel-heading">
                                                            <h4 class="panel-title">B Jaya Nageshwara Reddy</h4>
                                                            <span class="count">01</span>
                                                        </div>
                                                        <div class="panel-body">
                                                            <h4 class="text-capital">General Secretary</h4>
                                                            <table class="table table-condensed">
                                                                <thead>
                                                                    <th>Total</th>
                                                                    <th>Started</th>
                                                                    <th>Completed</th>
                                                                    <th>%</th>
                                                                </thead>
                                                                <tr>
                                                                    <td>100</td>
                                                                    <td>100</td>
                                                                    <td>100</td>
                                                                    <td>100%</td>
                                                                </tr>
                                                            </table>
                                                        </div>
                                                    </div>
                                                </li>
                                                <li>
                                                    <div class="panel panel-default panelSlick">
                                                        <div class="panel-heading">
                                                            <h4 class="panel-title">B Jaya Nageshwara Reddy</h4>
                                                            <span class="count">01</span>
                                                        </div>
                                                        <div class="panel-body">
                                                            <h4 class="text-capital">General Secretary</h4>
                                                            <table class="table table-condensed">
                                                                <thead>
                                                                    <th>Total</th>
                                                                    <th>Started</th>
                                                                    <th>Completed</th>
                                                                    <th>%</th>
                                                                </thead>
                                                                <tr>
                                                                    <td>100</td>
                                                                    <td>100</td>
                                                                    <td>100</td>
                                                                    <td>100%</td>
                                                                </tr>
                                                            </table>
                                                        </div>
                                                    </div>
                                                </li>
                                                <li>
                                                    <div class="panel panel-default panelSlick">
                                                        <div class="panel-heading">
                                                            <h4 class="panel-title">B Jaya Nageshwara Reddy</h4>
                                                            <span class="count">01</span>
                                                        </div>
                                                        <div class="panel-body">
                                                            <h4 class="text-capital">General Secretary</h4>
                                                            <table class="table table-condensed">
                                                                <thead>
                                                                    <th>Total</th>
                                                                    <th>Started</th>
                                                                    <th>Completed</th>
                                                                    <th>%</th>
                                                                </thead>
                                                                <tr>
                                                                    <td>100</td>
                                                                    <td>100</td>
                                                                    <td>100</td>
                                                                    <td>100%</td>
                                                                </tr>
                                                            </table>
                                                        </div>
                                                    </div>
                                                </li>
                                                <li>
                                                    <div class="panel panel-default panelSlick">
                                                        <div class="panel-heading">
                                                            <h4 class="panel-title">B Jaya Nageshwara Reddy</h4>
                                                            <span class="count">01</span>
                                                        </div>
                                                        <div class="panel-body">
                                                            <h4 class="text-capital">General Secretary</h4>
                                                            <table class="table table-condensed">
                                                                <thead>
                                                                    <th>Total</th>
                                                                    <th>Started</th>
                                                                    <th>Completed</th>
                                                                    <th>%</th>
                                                                </thead>
                                                                <tr>
                                                                    <td>100</td>
                                                                    <td>100</td>
                                                                    <td>100</td>
                                                                    <td>100%</td>
                                                                </tr>
                                                            </table>
                                                        </div>
                                                    </div>
                                                </li>
                                            </ul>
                                        </div>
                                        <div class="col-md-12 col-xs-12 col-sm-12">
                                            <div class="bg_ED pad_15 arrow_top">
                                                <h4><span  class="text-capital">b.jayanageshwar reddy</span> - <span class="text-capitalize">General Secretary</span></h4>
                                                <table class="table table-condensed tableHoverLevels m_top20">
                                                    <thead class="bg_D8 text-capital">
                                                        <th>% Rank</th>
                                                        <th>Secretary name</th>
                                                        <th>total</th>
                                                        <th>yet to start</th>
                                                        <th>started</th>
                                                        <th>completed</th>
                                                        <th>%</th>
                                                    </thead>
                                                    <tbody>
                                                        <tr>
                                                            <td>
                                                                <span class="tableCount">01</span>
                                                            </td>
                                                            <td>Parvin Raj</td>
                                                            <td>1000</td>
                                                            <td>600</td>
                                                            <td>400</td>
                                                            <td>400</td>
                                                            <td>10%</td>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                <span class="tableCount">02</span>
                                                            </td>
                                                            <td>Parvin Raj</td>
                                                            <td>1000</td>
                                                            <td>600</td>
                                                            <td>400</td>
                                                            <td>400</td>
                                                            <td>10%</td>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                <span class="tableCount">03</span>
                                                            </td>
                                                            <td>Parvin Raj</td>
                                                            <td>1000</td>
                                                            <td>600</td>
                                                            <td>400</td>
                                                            <td>400</td>
                                                            <td>10%</td>
                                                        </tr>
                                                    </tbody>
                                                </table>
                                                <span class="headingColor text-capitalize">←<i><b>back to </b>general secretary view</i></span>
                                                <span class="text-capitalize">B.T.Naidu - <i>Secretary</i></span>
                                                <table class="table table-condensed tableLevels m_top20">
                                                    <thead class="bg_D8 text-capital">
                                                        <th>% Rank</th>
                                                        <th>constituency</th>
                                                        <th>mla name</th>
                                                        <th>total</th>
                                                        <th>yet to start</th>
                                                        <th>started</th>
                                                        <th>completed</th>
                                                        <th>%</th>
                                                    </thead>
                                                    <tbody>
                                                        <tr>
                                                            <td>
                                                                <span class="tableCount">01</span>
                                                            </td>
                                                            <td>madakisara</td>
                                                            <td>K Eeranna</td>
                                                            <td>1000</td>
                                                            <td>600</td>
                                                            <td>400</td>
                                                            <td>400</td>
                                                            <td>10%</td>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                <span class="tableCount">02</span>
                                                            </td>
                                                            <td>madakisara</td>
                                                            <td>K Eeranna</td>
                                                            <td>1000</td>
                                                            <td>600</td>
                                                            <td>400</td>
                                                            <td>400</td>
                                                            <td>10%</td>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                <span class="tableCount">03</span>
                                                            </td>
                                                            <td>madakisara</td>
                                                            <td>K Eeranna</td>
                                                            <td>1000</td>
                                                            <td>600</td>
                                                            <td>400</td>
                                                            <td>400</td>
                                                            <td>10%</td>
                                                        </tr>
                                                    </tbody>
                                                </table>                      
                                                <div class="row">
                                                    <div class="col-md-6 col-xs-12 col-sm-8 col-sm-offset-2 col-md-offset-0">
                                                        <div class="panel panel-default panelNew">
                                                            <div class="panel-heading">
                                                                <span class="headingColor text-capitalize">logically complete - status not updated</span>
                                                            </div>
                                                            <div class="panel-body">
                                                                <table class="table table-condensed tableLevels">
                                                                    <thead class="bg_ED">
                                                                        <th>Level</th>
                                                                        <th>Main</th>
                                                                        <th>%</th>
                                                                        <th>Affliated</th>
                                                                        <th>%</th>
                                                                    </thead>
                                                                    <tr>
                                                                        <td>Village/ Ward</td>
                                                                        <td>11250</td>
                                                                        <td>80%</td>
                                                                        <td>1500</td>
                                                                        <td>70%</td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td>Mandal/ Town/ Village</td>
                                                                        <td>11250</td>
                                                                        <td>80%</td>
                                                                        <td>1500</td>
                                                                        <td>70%</td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td>District</td>
                                                                        <td>11250</td>
                                                                        <td>80%</td>
                                                                        <td>1500</td>
                                                                        <td>70%</td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td>State</td>
                                                                        <td>11250</td>
                                                                        <td>80%</td>
                                                                        <td>1500</td>
                                                                        <td>70%</td>
                                                                    </tr>
                                                                </table>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-6 col-xs-12 col-sm-8 col-sm-offset-2 col-md-offset-0">
                                                        <div class="panel panel-default panelNew">
                                                            <div class="panel-heading">
                                                                <div class="row">
                                                                    <div class="col-md-8 col-xs-12 col-sm-7">
                                                                        <span class="headingColor text-capitalize">running status - lapsed</span>
                                                                    </div>
                                                                    <div class="col-md-4 col-xs-12 col-sm-5">
                                                                        <ul class="activeUlCls list-inline">
                                                                            <li class="active">Main</li>
                                                                            <li>Affliated</li>
                                                                        </ul>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="panel-body">
                                                                <table class="table table-condensed tableLevels">
                                                                    <thead class="bg_ED">
                                                                        <th>Level</th>
                                                                        <th>&gt; 1Week</th>
                                                                        <th>&gt; 2Week</th>
                                                                        <th>&gt; 3Week</th>
                                                                        <th>&gt; 1months</th>
                                                                        <th>&gt; 3months</th>
                                                                    </thead>
                                                                    <tr>
                                                                        <td>Village/ Ward</td>
                                                                        <td>250</td>
                                                                        <td>300</td>
                                                                        <td>300</td>
                                                                        <td>300</td>
                                                                        <td>300</td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td>Mandal/ Town/ Village</td>
                                                                        <td>250</td>
                                                                        <td>300</td>
                                                                        <td>300</td>
                                                                        <td>300</td>
                                                                        <td>300</td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td>District</td>
                                                                        <td>250</td>
                                                                        <td>300</td>
                                                                        <td>300</td>
                                                                        <td>300</td>
                                                                        <td>300</td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td>State</td>
                                                                        <td>250</td>
                                                                        <td>300</td>
                                                                        <td>300</td>
                                                                        <td>300</td>
                                                                        <td>300</td>
                                                                    </tr>
                                                                </table>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-md-8 col-xs-12 col-sm-10 col-sm-offset-1 col-md-offset-0" style="border-right:1px solid #ddd;">
                                                        <b><span class="color_333 pad_5 bg_CC text-capital">top <span class="text-danger">poor</span> performance affliated committees</span></b>
                                                        <div class="row m_top20">
                                                            <div class="col-md-6 col-xs-12 col-sm-6">
                                                                <p class="text-capital">all levels cumulative</p>
                                                                <table class="table tableCumulative">
                                                                    <tr>
                                                                        <td><span class="count" style="background-color:rgba(237, 29, 38,1)">1</span></td>
                                                                        <td>telugu yuvatha</td>
                                                                        <td>
                                                                            <div class="progress progressCustom">
                                                                              <div class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%;">
                                                                                <span class="sr-only">60% Complete</span>
                                                                              </div>
                                                                            </div>
                                                                        </td>
                                                                        <td class="text-danger">50</td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td><span class="count" style="background-color:rgba(237, 29, 38,.8)">1</span></td>
                                                                        <td>telugu yuvatha</td>
                                                                        <td>
                                                                            <div class="progress progressCustom">
                                                                              <div class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%;">
                                                                                <span class="sr-only">60% Complete</span>
                                                                              </div>
                                                                            </div>
                                                                        </td>
                                                                        <td class="text-danger">50</td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td><span class="count" style="background-color:rgba(237, 29, 38,.6)">1</span></td>
                                                                        <td>telugu yuvatha</td>
                                                                        <td>
                                                                            <div class="progress progressCustom">
                                                                              <div class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%;">
                                                                                <span class="sr-only">60% Complete</span>
                                                                              </div>
                                                                            </div>
                                                                        </td>
                                                                        <td class="text-danger">50</td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td><span class="count" style="background-color:rgba(237, 29, 38,.4)">1</span></td>
                                                                        <td>telugu yuvatha</td>
                                                                        <td>
                                                                            <div class="progress progressCustom">
                                                                              <div class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%;">
                                                                                <span class="sr-only">60% Complete</span>
                                                                              </div>
                                                                            </div>
                                                                        </td>
                                                                        <td class="text-danger">50</td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td><span class="count" style="background-color:rgba(237, 29, 38,.2)">1</span></td>
                                                                        <td>telugu yuvatha</td>
                                                                        <td>
                                                                            <div class="progress progressCustom">
                                                                              <div class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%;">
                                                                                <span class="sr-only">60% Complete</span>
                                                                              </div>
                                                                            </div>
                                                                        </td>
                                                                        <td class="text-danger">50</td>
                                                                    </tr>
                                                                </table>
                                                            </div>
                                                            <div class="col-md-6 col-xs-12 col-sm-6">
                                                                <p class="text-capital">mandal / town / division level</p>
                                                                <table class="table tableCumulative">
                                                                    <tr>
                                                                        <td><span class="count" style="background-color:rgba(237, 29, 38,1)">1</span></td>
                                                                        <td>telugu yuvatha</td>
                                                                        <td>
                                                                            <div class="progress progressCustom">
                                                                              <div class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%;">
                                                                                <span class="sr-only">60% Complete</span>
                                                                              </div>
                                                                            </div>
                                                                        </td>
                                                                        <td class="text-danger">50</td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td><span class="count" style="background-color:rgba(237, 29, 38,.8)">1</span></td>
                                                                        <td>telugu yuvatha</td>
                                                                        <td>
                                                                            <div class="progress progressCustom">
                                                                              <div class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%;">
                                                                                <span class="sr-only">60% Complete</span>
                                                                              </div>
                                                                            </div>
                                                                        </td>
                                                                        <td class="text-danger">50</td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td><span class="count" style="background-color:rgba(237, 29, 38,.6)">1</span></td>
                                                                        <td>telugu yuvatha</td>
                                                                        <td>
                                                                            <div class="progress progressCustom">
                                                                              <div class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%;">
                                                                                <span class="sr-only">60% Complete</span>
                                                                              </div>
                                                                            </div>
                                                                        </td>
                                                                        <td class="text-danger">50</td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td><span class="count" style="background-color:rgba(237, 29, 38,.4)">1</span></td>
                                                                        <td>telugu yuvatha</td>
                                                                        <td>
                                                                            <div class="progress progressCustom">
                                                                              <div class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%;">
                                                                                <span class="sr-only">60% Complete</span>
                                                                              </div>
                                                                            </div>
                                                                        </td>
                                                                        <td class="text-danger">50</td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td><span class="count" style="background-color:rgba(237, 29, 38,.2)">1</span></td>
                                                                        <td>telugu yuvatha</td>
                                                                        <td>
                                                                            <div class="progress progressCustom">
                                                                              <div class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%;">
                                                                                <span class="sr-only">60% Complete</span>
                                                                              </div>
                                                                            </div>
                                                                        </td>
                                                                        <td class="text-danger">50</td>
                                                                    </tr>
                                                                </table>
                                                            </div>
                                                            <div class="col-md-6 col-xs-12 col-sm-6 m_top20">
                                                                <p class="text-capital">village / ward level</p>
                                                                <table class="table tableCumulative">
                                                                    <tr>
                                                                        <td><span class="count" style="background-color:rgba(237, 29, 38,1)">1</span></td>
                                                                        <td>telugu yuvatha</td>
                                                                        <td>
                                                                            <div class="progress progressCustom">
                                                                              <div class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%;">
                                                                                <span class="sr-only">60% Complete</span>
                                                                              </div>
                                                                            </div>
                                                                        </td>
                                                                        <td class="text-danger">50</td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td><span class="count" style="background-color:rgba(237, 29, 38,.8)">1</span></td>
                                                                        <td>telugu yuvatha</td>
                                                                        <td>
                                                                            <div class="progress progressCustom">
                                                                              <div class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%;">
                                                                                <span class="sr-only">60% Complete</span>
                                                                              </div>
                                                                            </div>
                                                                        </td>
                                                                        <td class="text-danger">50</td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td><span class="count" style="background-color:rgba(237, 29, 38,.6)">1</span></td>
                                                                        <td>telugu yuvatha</td>
                                                                        <td>
                                                                            <div class="progress progressCustom">
                                                                              <div class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%;">
                                                                                <span class="sr-only">60% Complete</span>
                                                                              </div>
                                                                            </div>
                                                                        </td>
                                                                        <td class="text-danger">50</td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td><span class="count" style="background-color:rgba(237, 29, 38,.4)">1</span></td>
                                                                        <td>telugu yuvatha</td>
                                                                        <td>
                                                                            <div class="progress progressCustom">
                                                                              <div class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%;">
                                                                                <span class="sr-only">60% Complete</span>
                                                                              </div>
                                                                            </div>
                                                                        </td>
                                                                        <td class="text-danger">50</td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td><span class="count" style="background-color:rgba(237, 29, 38,.2)">1</span></td>
                                                                        <td>telugu yuvatha</td>
                                                                        <td>
                                                                            <div class="progress progressCustom">
                                                                              <div class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%;">
                                                                                <span class="sr-only">60% Complete</span>
                                                                              </div>
                                                                            </div>
                                                                        </td>
                                                                        <td class="text-danger">50</td>
                                                                    </tr>
                                                                </table>
                                                            </div>
                                                            <div class="col-md-6 col-xs-12 col-sm-6 m_top20">
                                                                <p class="text-capital">district level</p>
                                                                <table class="table tableCumulative">
                                                                    <tr>
                                                                        <td><span class="count" style="background-color:rgba(237, 29, 38,1)">1</span></td>
                                                                        <td>telugu yuvatha</td>
                                                                        <td>
                                                                            <div class="progress progressCustom">
                                                                              <div class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%;">
                                                                                <span class="sr-only">60% Complete</span>
                                                                              </div>
                                                                            </div>
                                                                        </td>
                                                                        <td class="text-danger">50</td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td><span class="count" style="background-color:rgba(237, 29, 38,.8)">1</span></td>
                                                                        <td>telugu yuvatha</td>
                                                                        <td>
                                                                            <div class="progress progressCustom">
                                                                              <div class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%;">
                                                                                <span class="sr-only">60% Complete</span>
                                                                              </div>
                                                                            </div>
                                                                        </td>
                                                                        <td class="text-danger">50</td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td><span class="count" style="background-color:rgba(237, 29, 38,.6)">1</span></td>
                                                                        <td>telugu yuvatha</td>
                                                                        <td>
                                                                            <div class="progress progressCustom">
                                                                              <div class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%;">
                                                                                <span class="sr-only">60% Complete</span>
                                                                              </div>
                                                                            </div>
                                                                        </td>
                                                                        <td class="text-danger">50</td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td><span class="count" style="background-color:rgba(237, 29, 38,.4)">1</span></td>
                                                                        <td>telugu yuvatha</td>
                                                                        <td>
                                                                            <div class="progress progressCustom">
                                                                              <div class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%;">
                                                                                <span class="sr-only">60% Complete</span>
                                                                              </div>
                                                                            </div>
                                                                        </td>
                                                                        <td class="text-danger">50</td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td><span class="count" style="background-color:rgba(237, 29, 38,.2)">1</span></td>
                                                                        <td>telugu yuvatha</td>
                                                                        <td>
                                                                            <div class="progress progressCustom">
                                                                              <div class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%;">
                                                                                <span class="sr-only">60% Complete</span>
                                                                              </div>
                                                                            </div>
                                                                        </td>
                                                                        <td class="text-danger">50</td>
                                                                    </tr>
                                                                </table>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-4 col-xs-12 col-sm-10 col-sm-offset-1 col-md-offset-0">
                                                        <b><span class="color_333 pad_5 bg_CC text-capital">top <span class="text-danger">poor</span> locations</span></b>
                                                        <div class="row m_top20">
                                                            <div class="col-md-12 col-xs-12 col-sm-12">
                                                                <p class="text-capital">districts</p>
                                                                <table class="table tableCumulative">
                                                                    <tr>
                                                                        <td><span class="count" style="background-color:rgba(237, 29, 38,1)">1</span></td>
                                                                        <td>kurnool</td>
                                                                        <td>
                                                                            <div class="progress progressCustom">
                                                                              <div class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%;">
                                                                                <span class="sr-only">60% Complete</span>
                                                                              </div>
                                                                            </div>
                                                                        </td>
                                                                        <td class="text-danger">50</td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td><span class="count" style="background-color:rgba(237, 29, 38,.8)">1</span></td>
                                                                        <td>chittoor</td>
                                                                        <td>
                                                                            <div class="progress progressCustom">
                                                                              <div class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%;">
                                                                                <span class="sr-only">60% Complete</span>
                                                                              </div>
                                                                            </div>
                                                                        </td>
                                                                        <td class="text-danger">50</td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td><span class="count" style="background-color:rgba(237, 29, 38,.6)">1</span></td>
                                                                        <td>kadapa</td>
                                                                        <td>
                                                                            <div class="progress progressCustom">
                                                                              <div class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%;">
                                                                                <span class="sr-only">60% Complete</span>
                                                                              </div>
                                                                            </div>
                                                                        </td>
                                                                        <td class="text-danger">50</td>
                                                                    </tr>
                                                                </table>
                                                            </div>
                                                            <div class="col-md-12 col-xs-12 col-sm-12 m_top20">
                                                                <p class="text-capital">constituencies</p>
                                                                <table class="table tableCumulative">
                                                                    <tr>
                                                                        <td><span class="count" style="background-color:rgba(237, 29, 38,1)">1</span></td>
                                                                        <td>telugu yuvatha</td>
                                                                        <td>
                                                                            <div class="progress progressCustom">
                                                                              <div class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%;">
                                                                                <span class="sr-only">60% Complete</span>
                                                                              </div>
                                                                            </div>
                                                                        </td>
                                                                        <td class="text-danger">50</td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td><span class="count" style="background-color:rgba(237, 29, 38,.8)">1</span></td>
                                                                        <td>telugu yuvatha</td>
                                                                        <td>
                                                                            <div class="progress progressCustom">
                                                                              <div class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%;">
                                                                                <span class="sr-only">60% Complete</span>
                                                                              </div>
                                                                            </div>
                                                                        </td>
                                                                        <td class="text-danger">50</td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td><span class="count" style="background-color:rgba(237, 29, 38,.6)">1</span></td>
                                                                        <td>telugu yuvatha</td>
                                                                        <td>
                                                                            <div class="progress progressCustom">
                                                                              <div class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%;">
                                                                                <span class="sr-only">60% Complete</span>
                                                                              </div>
                                                                            </div>
                                                                        </td>
                                                                        <td class="text-danger">50</td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td><span class="count" style="background-color:rgba(237, 29, 38,.4)">1</span></td>
                                                                        <td>telugu yuvatha</td>
                                                                        <td>
                                                                            <div class="progress progressCustom">
                                                                              <div class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%;">
                                                                                <span class="sr-only">60% Complete</span>
                                                                              </div>
                                                                            </div>
                                                                        </td>
                                                                        <td class="text-danger">50</td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td><span class="count" style="background-color:rgba(237, 29, 38,.2)">1</span></td>
                                                                        <td>telugu yuvatha</td>
                                                                        <td>
                                                                            <div class="progress progressCustom">
                                                                              <div class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%;">
                                                                                <span class="sr-only">60% Complete</span>
                                                                              </div>
                                                                            </div>
                                                                        </td>
                                                                        <td class="text-danger">50</td>
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
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-6 col-xs-12 col-sm-8 col-sm-offset-2 col-md-offset-0 trainingBlock">
        	<div class="panel panel-default panelNewCustom">
            	<div class="panel-heading">
                    <h4 class="panel-title">training
                        <span class="trainingIconExpand pull-right">
                            <i class="glyphicon glyphicon-fullscreen"></i>
                        </span>
                    </h4>
                </div>
                <div class="panel-body">
                	<div class="row">
                    	<div class="col-md-12 col-xs-12 col-sm-12">
                        </div>
                    	<div class="col-md-6 col-xs-12 col-sm-8 col-sm-offset-2">
                        	<ul class="activeUlCls list-inline">
                            	<li><span class="fa fa-long-arrow-up"></span>top 5 strong</li>
                                <li><span class="fa fa-long-arrow-down"></span>last 5 poor</li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
	</div>
</div>
<script src="newCoreDashBoard/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="newCoreDashBoard/js/bootstrap.js" type="text/javascript"></script>
<script src="newCoreDashBoard/js/newCoreDashboard.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Highcharts/highcharts.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Date/moment.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Slick/slick.js" type="text/javascript"></script>
<script type="text/javascript">
/* New Design */

	var globalUserId = '${sessionScope.USER.registrationID}';
	var globalUserTypeId;
	var globalUserAccessLevelId;
	var globalUserAccessLevelValues = [];
	var globalState = 'AP';
	
	$(document).ready(function(){
		
		//Main header remove
		$(".eventsheader").hide();
		$('[data-toggle="tooltip"]').tooltip();
		
	});
	getUserBasicDetails();
	function onLoadCalls(){
		
		getDistrictWiseCommitteesCountReport();
		getCommitteesBasicCountReport();
		getLevelWiseBasicCommitteesCountReport();
		getUserTypeWiseCommitteesCompletedCounts();
		getUserTypeWiseCommitteesCompletedCounts1();
	}

	function buildgetCommitteesBasicCountReport(result){
		var str='';
		
		str+='<div class="col-md-12 col-xs-12 col-sm-10 col-sm-offset-1 col-md-offset-0 committeesBlock">';
				str+='<ul class="committesBlockUl">';
					str+='<li>';
						str+='<h4 class="text-capital bg_49 pad_custom">main committees</h4>';
						
						str+='<table class="table table-condensed">';
						   str+='<tr>';
								str+='<td>';
								if(result.mainVO.totalCount == null || result.mainVO.totalCount == 0){
									str+='<h3> - </h3>';
								}else{
									str+='<h3>'+result.mainVO.totalCount+'</h3>';
								}
									str+='<h5 class="text-muted text-capitalize">total</h5>';
								str+='</td>';
								str+='<td>';
								if(result.mainVO.startedCount == null || result.mainVO.startedCount == 0){
									str+='<h3> - </h3>';
									str+='<h5 class="text-muted text-capitalize">started</h5>';
								}else{
									str+='<h3>'+result.mainVO.startedCount+'</h3>';
									str+='<h5 class="text-muted text-capitalize">started</h5>';
									str+='<small class="text-success">'+result.mainVO.startedPerc+'%</small>';
								}
								str+='</td>';
								str+='<td>';
								if(result.mainVO.completedCount == null || result.mainVO.completedCount == 0){
									str+='<h3> - </h3>';
									str+='<h5 class="text-muted">Completed</h5>';
								}else{
									str+='<h3>'+result.mainVO.completedCount+'</h3>';
									str+='<h5 class="text-muted">Completed</h5>';
									str+='<small class="text-success">'+result.mainVO.completedPerc+'%</small>';
								}
								str+='</td>';
							str+='</tr>';
						str+='</table>';
					str+='</li>';
					str+='<li>';
						str+='<h4 class="text-capital bg_49 pad_custom">affliated committees</h4>';
						str+='<table class="table table-condensed">';
							str+='<tr>';
								str+='<td>';
								if(result.affliatedVO.startedCount == null || result.affliatedVO.startedCount == 0){
									str+='<h3> - </h3>';
								}else{
									str+='<h3>'+result.affliatedVO.startedCount+'</h3>';
								}
									str+='<h5 class="text-muted text-capitalize">Started</h5>';
								str+='</td>';
								str+='<td>';
								if(result.affliatedVO.completedCount == null || result.affliatedVO.completedCount ==0){
									str+='<h3> - </h3>';
								}else{
									str+='<h3>'+result.affliatedVO.completedCount+'</h3>';
								}
								str+='<h5 class="text-muted text-capitalize">Completed</h5>';
								str+='</td>';
						   str+='</tr>';
						str+='</table>';
					str+='</li>';
					str+='<hr style="margin:0px;">';
					if(result.subList != null && result.subList.length >0){
							str+='<li>';
							str+='<h4 class="text-capitalize m_top10" style="color:#c9c0cc">'+result.subList[3].name+'/Ward</h4>';
							str+='<table class="table table-condensed bg_ED">';
								str+='<tr>';
									str+='<td>';
										str+='<h5 class="text-muted text-capitalize">Total</h5>';
										if(result.subList[3].mainVO.totalCount == null || result.subList[3].mainVO.totalCount == 0){
											str+='<p> - </p>';
										}else{
											str+='<p>'+result.subList[3].mainVO.totalCount+'</p>';
										}
										
								   str+='</td>';
									str+='<td>';
										str+='<h5 class="text-muted text-capitalize">Started</h5>';
										if(result.subList[3].mainVO.startedCount == null || result.subList[3].mainVO.startedCount == 0){
											str+='<p> - </p>';
										}else{
											str+='<p>'+result.subList[3].mainVO.startedCount+' <small class="text-success"> '+result.subList[3].mainVO.startedPerc+'%</small></p>';
										}
										
									str+='</td>';
									str+='<td>';
										str+='<h5 class="text-muted text-capitalize">Completed</h5>';
										if(result.subList[3].mainVO.completedCount == null || result.subList[3].mainVO.completedCount == 0){
											str+='<p> - </p>';
										}else{
											str+='<p>'+result.subList[3].mainVO.completedCount+'<small class="text-success"> '+result.subList[3].mainVO.completedPerc+'%</small></p>';
										}
										
									str+='</td>';
								str+='</tr>';
							str+='</table>';
						str+='</li>';
						str+='<li>';
							str+='<table class="table table-condensed bg_ED" style="margin-top:30px !important;">';
								str+='<tr>';
									str+='<td>';
										str+='<h5 class="text-muted text-capitalize">Started</h5>';
										if(result.subList[3].affliatedVO.startedCount == null || result.subList[3].affliatedVO.startedCount == 0){
											str+='<p> - </p>';
										}else{
											str+='<p>'+result.subList[3].affliatedVO.startedCount+'</p>';
										}
										
									str+='</td>';
									str+='<td>';
										str+='<h5 class="text-muted text-capitalize">Completed</h5>';
										if(result.subList[3].affliatedVO.completedCount == null || result.subList[3].affliatedVO.completedCount == 0){
											str+='<p> - </p>';
										}else{
											str+='<p>'+result.subList[3].affliatedVO.completedCount+'</p>';
										}
										
									str+='</td>';
								str+='</tr>';
							str+='</table>';
						str+='</li>';
						str+='<li>';
							str+='<h4 class="text-capitalize m_top10 text-pink">'+result.subList[2].name+' / town / division</h4>';
							str+='<table class="table table-condensed bg_ED">';
								str+='<tr>';
									str+='<td>';
										str+='<h5 class="text-muted text-capitalize">Total</h5>';
										if(result.subList[2].mainVO.totalCount == null || result.subList[2].mainVO.totalCount ==0){
											str+='<p> - </p>';
										}else{
											str+='<p>'+result.subList[2].mainVO.totalCount+'</p>';
										}
										
									str+='</td>';
									str+='<td>';
										str+='<h5 class="text-muted text-capitalize">Started</h5>';
										if(result.subList[2].mainVO.startedCount == null || result.subList[2].mainVO.startedCount == 0){
											str+='<p> - </p>';
										}else{
											str+='<p>'+result.subList[2].mainVO.startedCount+' <small class="text-success"> '+result.subList[2].mainVO.startedPerc+'%</small></p>';
										}
										
									str+='</td>';
									str+='<td>';
										str+='<h5 class="text-muted text-capitalize">Completed</h5>';
										if(result.subList[2].mainVO.completedCount == null || result.subList[2].mainVO.completedCount == 0){
											str+='<p> - </p>';
										}else{
											str+='<p>'+result.subList[2].mainVO.completedCount+' <small class="text-success"> '+result.subList[2].mainVO.completedPerc+'%</small></p>';
										}
										
								   str+='</td>';
								str+='</tr>';
							str+='</table>';
						str+='</li>';
						str+='<li>';
							str+='<table class="table table-condensed bg_ED" style="margin-top:30px !important;">';
								str+='<tr>';
									str+='<td>';
										str+='<h5 class="text-muted text-capitalize">Started</h5>';
										if(result.subList[2].affliatedVO.startedCount == null || result.subList[2].affliatedVO.startedCount == 0){
											str+='<p> - </p>';
										}else{
											str+='<p>'+result.subList[2].affliatedVO.startedCount+'</p>';
										}
										
									str+='</td>';
									str+='<td>';
										str+='<h5 class="text-muted text-capitalize">Completed</h5>';
										if(result.subList[2].affliatedVO.completedCount == null || result.subList[2].affliatedVO.completedCount == 0){
											str+='<p> - </p>';
										}else{
											str+='<p>'+result.subList[2].affliatedVO.completedCount+'</p>';
										}
										
									str+='</td>';
								str+='</tr>';
							str+='</table>';
						str+='</li>';
						str+='<li>';
							str+='<h4 class="text-capitalize m_top10" style="color:#81a0ad">'+result.subList[1].name+'</h4>';
							str+='<table class="table table-condensed bg_ED">';
								str+='<tr>';
									str+='<td>';
										str+='<h5 class="text-muted text-capitalize">Total</h5>';
										if(result.subList[1].mainVO.totalCount == null || result.subList[1].mainVO.totalCount ==0){
											str+='<p> - </p>';
										}else{
											str+='<p>'+result.subList[1].mainVO.totalCount+'</p>';
										}
										
									str+='</td>';
									str+='<td>';
										str+='<h5 class="text-muted text-capitalize">Started</h5>';
										if(result.subList[1].mainVO.startedCount == null || result.subList[1].mainVO.startedCount == 0){
											str+='<p> - </p>';
										}else{
											str+='<p>'+result.subList[1].mainVO.startedCount+' <small class="text-success"> '+result.subList[1].mainVO.startedPerc+'%</small></p>';
										}
										
									str+='</td>';
									str+='<td>';
										str+='<h5 class="text-muted text-capitalize">Completed</h5>';
										if(result.subList[1].mainVO.completedCount == null || result.subList[1].mainVO.completedCount == 0){
											str+='<p> - </p>';
										}else{
											str+='<p>'+result.subList[1].mainVO.completedCount+' <small class="text-success"> '+result.subList[1].mainVO.completedPerc+'%</small></p>';
										}
										
									str+='</td>';
								str+='</tr>';
							str+='</table>';
						str+='</li>';
						str+='<li>';
							str+='<table class="table table-condensed bg_ED" style="margin-top:30px !important;">';
								str+='<tr>';
									str+='<td>';
										str+='<h5 class="text-muted text-capitalize">Started</h5>';
										if(result.subList[2].affliatedVO.startedCount == null || result.subList[2].affliatedVO.startedCount == 0){
											str+='<p> - </p>';
										}else{
											str+='<p>'+result.subList[2].affliatedVO.startedCount+'</p>';
										}
										
								   str+='</td>';
									str+='<td>';
										str+='<h5 class="text-muted text-capitalize">Completed</h5>';
										if(result.subList[2].affliatedVO.completedCount == null || result.subList[2].affliatedVO.completedCount == 0){
											str+='<p> - </p>';
										}else{
											str+='<p>'+result.subList[2].affliatedVO.completedCount+'</p>';
										}
										
									str+='</td>';
								str+='</tr>';
							str+='</table>';
						str+='</li>';
						str+='<li>';
							str+='<h4 class="text-capitalize m_top10" style="color:#a2cfc9">'+result.subList[0].name+'</h4>';
							str+='<table class="table table-condensed bg_ED">';
								str+='<tr>';
									str+='<td>';
										str+='<h5 class="text-muted text-capitalize">Total</h5>';
										if(result.subList[0].mainVO.totalCount == null || result.subList[0].mainVO.totalCount == 0){
											str+='<p> - </p>';
										}else{
											str+='<p>'+result.subList[0].mainVO.totalCount+'</p>';
										}
										
									str+='</td>';
									str+='<td>';
										str+='<h5 class="text-muted text-capitalize">Started</h5>';
										if(result.subList[0].mainVO.startedCount == null || result.subList[0].mainVO.startedCount == 0){
											str+='<p> - </p>';
										}else{
											str+='<p>'+result.subList[0].mainVO.startedCount+' <small class="text-success"> '+result.subList[0].mainVO.startedPerc+'%</small></p>';
										}
										
									str+='</td>';
									str+='<td>';
										str+='<h5 class="text-muted text-capitalize">Completed</h5>';
										if(result.subList[0].mainVO.completedCount == null || result.subList[0].mainVO.completedCount == 0){
											str+='<p> - </p>';
										}else{
											str+='<p>'+result.subList[0].mainVO.completedCount+' <small class="text-success"> '+result.subList[0].mainVO.completedPerc+'%</small></p>';
										}
										
									str+='</td>';
								str+='</tr>';
							str+='</table>';
						str+='</li>';
						str+='<li style="margin-top:30px;">';
							str+='<h4 class="text-capitalize bg_ED text-muted pad_15">no affliated committees</h4>';
						str+='</li>';
					}
					
				str+='</ul>';
        str+='</div>';
		
		$("#basicCommitteeCountsDiv").html(str)
	}
</script>
</body>
</html>
