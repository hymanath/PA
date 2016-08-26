<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<link href="dist/DateRange/daterangepicker.css" type="text/css" rel="stylesheet"/>
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
					<div class="col-xs-12 col-sm-4 col-md-2 pull-right showDatePicker" style="display:none;">
						<div class="input-group">
							<input type="text" id="dateRangeId"	 class="form-control" />
							<span class="input-group-addon">
								<i class="glyphicon glyphicon-calendar"></i>
							</span>
						</div>
					</div>
						<div id="basicCommitteeCountsDiv"></div>
                        <div class="col-md-12 col-xs-12 col-sm-10 col-sm-offset-1 col-md-offset-0 committeesBlock committeesHiddenBlock">
                        	<div class="row">
                            	<div class="col-md-12 col-xs-12 col-sm-12">
                                	<ul class="activeUlCls list-inline pull-right">
                                    	<li class="topFivePoorResults">Top 5 Poor</li>
                                        <li class="active topFiveStrongResults">Top 5 Strong</li>
                                    </ul>
                                </div>
                                <div id="userTypeWiseCommitteesForTopFiveStrongDiv"></div>
                                <div id="userTypeWiseCommitteesForTopFivePoorDiv" style ="display:none"></div>
                            </div>
                        </div>
                        <div class="col-xs-12 col-sm-12 col-md-12">
                        	<i class="glyphicon glyphicon-option-horizontal pull-right moreBlocksIcon" data-toggle="tooltip" data-placement="top" title="Click here for more"></i>
                        </div>
                        <div class="col-md-12 col-xs-12 col-sm-10 col-sm-offset-1 col-md-offset-0 moreBlocks">
                        	<ul class="list-inline pull-right activeUlCls">
                            	<li class="active">Detailed</li>
                                <li>Comparison</li>
                                <li class="basicCommitteesBlockDiv"><i class="fa fa-gears"></i></li>
                            </ul>
							<div class="basicCommitteesBlockDropDown" style="z-index:999">
								<h4 class="text-capital" style="color:#99A0A5;">Select Committees</h4>
								<hr style ="margin-bottom:0px;" />
								<div class="col-xs-12 col-sm-12 col-md-12">
								<label class="checkbox-inline m_top10">
								  <input type="checkbox" id="inlineCheckbox1" value="option1">
								  <div style="margin-top: 3px;"><h5 class="text-capital" style="color:#54616C;"> Main Committee</h5></div>
								</label>
								<hr style ="margin-bottom:0px;" />
								</div>
								
								<div class="col-xs-12 col-sm-12 col-md-12">
								<label class="checkbox-inline m_top10">
								  <input type="checkbox" id="inlineCheckbox1" value="option1">
								<div style="margin-top: 3px;"><h5 class="text-capital" style="color:#54616C;"> Telugu Yuvatha</h5></div>
								</label>
								<hr style ="margin-bottom:0px;" />
								</div>
								
								<div class="col-xs-12 col-sm-12 col-md-12">
								<label class="checkbox-inline m_top10">
								  <input type="checkbox" id="inlineCheckbox1" value="option1">
								  <div style="margin-top: 3px;"><h5 class="text-capital" style="color:#54616C;"> Telugu Mahila</h5></div>
								</label>
								<hr style ="margin-bottom:0px;" />
								</div>
								<div class="col-xs-12 col-sm-12 col-md-12">
								<label class="checkbox-inline m_top10">
								  <input type="checkbox" id="inlineCheckbox1" value="option1">
								  <div style="margin-top: 3px;"><h5 class="text-capital" style="color:#54616C;"> Telugu Rythu</h5></div>
								</label>
								<hr style ="margin-bottom:0px;" />
								</div>
							</div>
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
                                        <!--<div class="col-md-4 col-xs-12 col-sm-12">
                                            <ul class="activeUlCls list-inline">
                                                <li class="active">All</li>
                                                <li>Yet To Start</li>
                                                <li>Completed</li>
                                                <li>Started</li>
                                            </ul>
                                        </div>-->
                                    </div>
                                </div>
                                <div class="panel-body">
									<!--<div id="mainCommittees" class="chartLiD"></div>-->
									
									<div id="districtWiseCommitteesReport" ></div>
									
                                	<!--<div>
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
                                    </div>-->
                                </div>
                            </div>
                        </div>
                        <div class="col-md-12 col-xs-12 col-sm-12 m_top20 moreBlocks">
                            <p><i>Selected:</i> <b>Main Committee</b></p>
                            <div class="panel panel-default panelNew">
                                <div class="panel-heading">
                                    <div class="row">
                                        <div class="col-xs-12 col-sm-8 col-md-10">
                                           <div id="childUserTypeDetailsDiv"></div>
                                        </div>
                                       
                                    </div>
                                </div>
                                <div class="panel-body">
                                    <div class="row">
                                        <div class="col-md-12 col-xs-12 col-sm-12">
                                           <div id="SelectedUserTypeDetailsDiv"></div>
                                        </div>
                                        <div class="col-md-12 col-xs-12 col-sm-12">
                                            <div class="bg_ED pad_15 arrow_top showChildBlockAndTopPoorBlock" style="display:none">
                                                <div id="directChildActivityMemberDiv"></div>
                                                <div class="row m_top20">
                                                    <div class="col-md-8 col-xs-12 col-sm-10 col-sm-offset-1 col-md-offset-0" style="border-right:1px solid #ddd;">
													 <div id="topPoorPerformanceDiv"></div>
                                                       
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

<button  style="display:none" class="userStructureClass" attr_activityMemberId="1" attr_userTypeId="3" attr_userAccessLevelId="3" attr_userAccessLevelValuesString="11,12,15" > ActivityMember </button>

<script src="newCoreDashBoard/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="newCoreDashBoard/js/bootstrap.js" type="text/javascript"></script>
<script src="newCoreDashBoard/js/newCoreDashboard.js" type="text/javascript"></script>
<script src="newCoreDashBoard/js/tdpCommittees.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Highcharts/highcharts.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Date/moment.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Slick/slick.js" type="text/javascript"></script>
<script src="dist/DateRange/moment.js" type="text/javascript"></script>
<script src="dist/DateRange/daterangepicker.js" type="text/javascript"></script>
<script type="text/javascript">

	
	
/* New Design */
      initialiseDatePicker();
	  
	  var loggedInUserId = '${sessionScope.USER.registrationID}';
	  var loggedInUserActivityMemberId = '${requestScope.userDataVO.activityMemberId}';
	  var loggedInUserTypeId = '${requestScope.userDataVO.userTypeId}'; 
	  var loggedInUserAccessLevelId  = '${requestScope.userDataVO.userAccessLevelId}';
	  var loggedInUserAccessLevelValues = getLoggedInUserAccessLevelValues();
	  
	  var globalActivityMemberId = loggedInUserActivityMemberId;
	  var globalUserTypeId = loggedInUserTypeId;
	  var globalUserAccessLevelId = loggedInUserAccessLevelId;
	  var globalUserAccessLevelValues = loggedInUserAccessLevelValues;
	  
	  var globalState = 'AP';

	$(document).ready(function(){
		//Main header remove
		$(".eventsheader").hide();
		$('[data-toggle="tooltip"]').tooltip();
	});
	
	onLoadCalls();
	function onLoadCalls(){
		getCommitteesBasicCountReport();
	}
	
	$(document).on("click",".userStructureClass",function(){
		
        var  clickedActivityMemberId = $(this).attr("attr_activityMemberId");
		var  clickedUserTypeId = $(this).attr("attr_userTypeId");
		var  clickedUserAccessLevelId =  $(this).attr("attr_userAccessLevelId");
		var  clickedUserAccessLevelValuesString = $(this).attr("attr_userAccessLevelValuesString");
		var  clickedUserAccessLevelValuesArray = [];
		if($.trim(clickedUserAccessLevelValuesString).length > 0){
			clickedUserAccessLevelValuesArray = clickedUserAccessLevelValuesString.split(",");
		}
		//MAKE SELECTED MEMBER DATA AS GLOBAL.
	    globalActivityMemberId = clickedActivityMemberId;
	    globalUserTypeId = clickedUserTypeId;
	    globalUserAccessLevelId = clickedUserAccessLevelId;
	    globalUserAccessLevelValues = clickedUserAccessLevelValuesArray;
		
		onLoadCalls();
	});
	

	function getLoggedInUserAccessLevelValues(){
		var loggedInUserAccessLevelValues = [];
		<c:forEach items="${userDataVO.userAccessLevelValuesList}" var="userAccessLevelValue">
		  loggedInUserAccessLevelValues.push( ${userAccessLevelValue} );        
	   </c:forEach>
	   return loggedInUserAccessLevelValues;
	}
	
  </script>
	
	
 
</body>
</html>
