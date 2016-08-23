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
                            	<div class="panel-body ">
								<div id="levelWiseBasicCommittees"></div>
                                
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
		getUserTypeWiseCommitteesCompletedCounts();
		getUserTypeWiseCommitteesCompletedCounts1();
	}

	
</script>
</body>
</html>
