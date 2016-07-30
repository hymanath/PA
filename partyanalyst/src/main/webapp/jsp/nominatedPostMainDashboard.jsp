<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>NOMINATED POST PROFILE CREATION</title>
<link href="dist/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="dist/NominatedPost/custom.css" rel="stylesheet" type="text/css">
<link href="http://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
<link href="dist/Plugins/Chosen/chosen.css" rel="stylesheet" type="text/css"/>
<link href="dist/Plugins/Datatables/datatables.css" rel="stylesheet" type="text/css"/>
<link href="dist/NominatedPost/Slick/slick.css" rel="stylesheet" type="text/css"/>
<link href="dist/NominatedPost/Slick/slick-theme.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="container">
	<div class="row">
    	<div class="col-md-12 col-xs-12 col-sm-12">
        	<ol class="breadcrumb">
            	<li><i class="glyphicon glyphicon-home"></i></li>
                <li>Nominated Post Main Dashboard</li>
            </ol>
        </div>
        <div class="col-md-12 col-xs-12 col-sm-12">
        	<h3 class="headingColor">Overview</h3>
        </div>
        <div class="col-md-12 col-xs-12 col-sm-12">
        	<div class="row">
            	<div class="col-md-2 col-xs-12 col-sm-2 bg_ff pad_right0">
                      <ul class="nav nav-tabs tabsCustomFinal" role="tablist">
                        <li role="presentation" class="active"><a href="#departments1" aria-controls="departments1" role="tab" data-toggle="tab">Labour </a></li>
                        <li role="presentation"><a href="#departments2" aria-controls="departments2" role="tab" data-toggle="tab">Health,Medical & Family Welfare</a></li>
                        <li role="presentation"><a href="#departments3" aria-controls="messages" role="tab" data-toggle="tab">Roads & Buildings</a></li>
                        <li role="presentation"><a href="#departments4" aria-controls="settings" role="tab" data-toggle="tab">Transport</a></li>
                        <li role="presentation"><a href="#departments5" aria-controls="settings" role="tab" data-toggle="tab">Planning</a></li>
                        <li role="presentation"><a href="#departments6" aria-controls="settings" role="tab" data-toggle="tab">MA & UD</a></li>
                        <li role="presentation"><a href="#departments6" aria-controls="settings" role="tab" data-toggle="tab">MA & UD</a></li>
                        <li role="presentation"><a href="#departments6" aria-controls="settings" role="tab" data-toggle="tab">MA & UD</a></li>
                        <li role="presentation"><a href="#departments6" aria-controls="settings" role="tab" data-toggle="tab">MA & UD</a></li>
                        <li role="presentation"><a href="#departments6" aria-controls="settings" role="tab" data-toggle="tab">MA & UD</a></li>
                        <li role="presentation"><a href="#departments6" aria-controls="settings" role="tab" data-toggle="tab">MA & UD</a></li>
                        <li role="presentation"><a href="#departments6" aria-controls="settings" role="tab" data-toggle="tab">MA & UD</a></li>
                        <li role="presentation"><a href="#departments6" aria-controls="settings" role="tab" data-toggle="tab">MA & UD</a></li>
                        <li role="presentation"><a href="#departments6" aria-controls="settings" role="tab" data-toggle="tab">MA & UD</a></li>
                      </ul>
                </div>
                <div class="col-md-4 col-xs-12 col-sm-4">
                	<div class="bg_ff pad_10">
                    	<h4 class="text-capital panel-title">all levels overview</h4>
                        <div class="row">
                        	<div class="col-md-12 col-xs-12 col-sm-12">
                              <ul class="nav nav-tabs navTabsSelection" role="tablist">
                                <li role="presentation" class="active"><a href="#positions" aria-controls="positions" role="tab" data-toggle="tab">Home</a></li>
                                <li role="presentation"><a href="#applicationsTab" aria-controls="applicationsTab" role="tab" data-toggle="tab">Profile</a></li>
                              </ul>
                              <div class="tab-content navTabsSelectionBody">
                                <div role="tabpanel" class="tab-pane active" id="positions">
                                	<div id="chartdiv"></div>
                                </div>
                                <div role="tabpanel" class="tab-pane" id="applicationsTab">
                                	<div id="chartdiv1"></div>
                                </div>
                              </div>    
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-6 col-xs-12 col-sm-6">
                	<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
                      <div class="panel panel-default panelDashboard">
                        <div class="panel-heading" role="tab" id="headingOne">
                          <a role="button" data-toggle="collapse" class="panelDashboardIcon" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                            <h4 class="panel-title">
                              state
                            </h4>
                          </a>
                        </div>
                        <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
                          <div class="panel-body">
                            <div class="row">
                            	<div class="col-md-6 col-xs-12 col-sm-6">
                                	<div id="Positions" style="height:80px;"></div>
                                    <ul class="positionsUl">
                                    	<li class="total"><span class="statusBox"></span>Total Applications<span class="count">1200</span></li>
                                        <li class="noCandidate"><span class="statusBox"></span>Total Applications<span class="count">1200</span></li>
                                        <li class="shortListed"><span class="statusBox"></span>Total Applications<span class="count">1200</span></li>
                                        <li class="notStarted"><span class="statusBox"></span>Total Applications<span class="count">1200</span></li>
                                        <li class="readyForReview"><span class="statusBox"></span>Total Applications<span class="count">1200</span></li>
                                        <li class="finalised"><span class="statusBox"></span>Total Applications<span class="count">1200</span></li>
                                        <li class="goIssued"><span class="statusBox"></span>Total Applications<span class="count">1200</span></li>
                                    </ul>
                                </div>
                            	<div class="col-md-6 col-xs-12 col-sm-6">
                                	<div id="applications"></div>
                                </div>
                            </div>
                          </div>
                        </div>
                      </div>
                      <div class="panel panel-default panelDashboard">
                        <div class="panel-heading" role="tab" id="headingTwo">
                          <a class="collapsed panelDashboardIcon" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                            <h4 class="panel-title">District</h4>
                          </a>
                        </div>
                        <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
                          <div class="panel-body">
                            
                          </div>
                        </div>
                      </div>
                      <div class="panel panel-default panelDashboard">
                        <div class="panel-heading" role="tab" id="headingThree">
                          <a class="collapsed panelDashboardIcon" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                            <h4 class="panel-title">Constituency</h4>
                          </a>
                        </div>
                        <div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
                          <div class="panel-body">
                            
                          </div>
                        </div>
                      </div>
                      <div class="panel panel-default panelDashboard">
                        <div class="panel-heading" role="tab" id="headingThree1">
                          <h4 class="panel-title">
                            <a class="collapsed panelDashboardIcon" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseThree1" aria-expanded="false" aria-controls="collapseThree1">
                              Mandal/Muncipality/Corporation
                            </a>
                          </h4>
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
        <div class="col-md-12 col-sm-12 col-xs-12 m_top20">
        	<div class="row">
            	<div class="col-md-8 col-xs-12 col-sm-8">
                	<div class="panel panel-default">
                    	<div class="panel-heading bg_ff">
                        	<h4 class="panel-title text-capital">caste wise position overview</h4>
                        </div>
                        <div class="panel-body pad_0">
                        	<div>
                              <!-- Nav tabs -->
                              <ul class="nav nav-tabs navTabsSelection" role="tablist">
                                <li role="presentation" class="active"><a href="#overall" aria-controls="overall" role="tab" data-toggle="tab">Overall</a></li>
                                <li role="presentation"><a href="#state" aria-controls="state" role="tab" data-toggle="tab">State</a></li>
                                <li role="presentation"><a href="#district" aria-controls="district" role="tab" data-toggle="tab">district</a></li>
                                <li role="presentation"><a href="#constituency" aria-controls="constituency" role="tab" data-toggle="tab">constituency</a></li>
                              </ul>
                            
                              <!-- Tab panes -->
                              <div class="tab-content navTabsSelectionBody">
                                <div role="tabpanel" class="tab-pane active pad_10 pad_right0" id="overall">
                                	<div class="row">
                                    	<div class="col-md-2 pad_right0" style="-webkit-box-shadow: 10px 0 5px -2px #888; box-shadow: 10px 0 5px -2px #888;">
                                        	<table class="table table-bordered">
                                            	<tr>
                                                	<td>&nbsp;</td>
                                                </tr>
                                                <tr>
                                                	<td>Chairman</td>
                                                </tr>
                                            	<tr>
                                                	<td>Chairman</td>
                                                </tr>
                                            	<tr>
                                                	<td>Chairman</td>
                                                </tr>
                                            	<tr>
                                                	<td>Chairman</td>
                                                </tr>
                                            	<tr>
                                                	<td>Chairman</td>
                                                </tr>
                                            	<tr>
                                                	<td>Chairman</td>
                                                </tr>
                                            </table>
                                        </div>
                                        <div class="col-md-10 pad_left0">
                                        	<div class="table-responsive">
                                                <table class="table table-bordered dataTableCaste" style="margin:0px !important">
                                                    <thead class="text-capital">
                                                        <th>REDDY</th>
                                                        <th>kamma</th>
                                                        <th>velama</th>
                                                        <th>kapu</th>
                                                        <th>nayudu</th>
                                                        <th>reddy</th>
                                                        <th>malla</th>
                                                        <th>madiga</th>
                                                    </thead>
                                                    <tr>
                                                        <td>52</td>
                                                        <td>52</td>
                                                        <td>52</td>
                                                        <td>52</td>
                                                        <td>52</td>
                                                        <td>52</td>
                                                        <td>52</td>
                                                        <td>52</td>
                                                    </tr>
                                                    <tr>
                                                        <td>52</td>
                                                        <td>52</td>
                                                        <td>52</td>
                                                        <td>52</td>
                                                        <td>52</td>
                                                        <td>52</td>
                                                        <td>52</td>
                                                        <td>52</td>
                                                    </tr>
                                                    <tr>
                                                        <td>52</td>
                                                        <td>52</td>
                                                        <td>52</td>
                                                        <td>52</td>
                                                        <td>52</td>
                                                        <td>52</td>
                                                        <td>52</td>
                                                        <td>52</td>
                                                    </tr>
                                                    <tr>
                                                        <td>52</td>
                                                        <td>52</td>
                                                        <td>52</td>
                                                        <td>52</td>
                                                        <td>52</td>
                                                        <td>52</td>
                                                        <td>52</td>
                                                        <td>52</td>
                                                    </tr>
                                                    <tr>
                                                        <td>52</td>
                                                        <td>52</td>
                                                        <td>52</td>
                                                        <td>52</td>
                                                        <td>52</td>
                                                        <td>52</td>
                                                        <td>52</td>
                                                        <td>52</td>
                                                    </tr>
                                                    <tr>
                                                        <td>52</td>
                                                        <td>52</td>
                                                        <td>52</td>
                                                        <td>52</td>
                                                        <td>52</td>
                                                        <td>52</td>
                                                        <td>52</td>
                                                        <td>52</td>
                                                    </tr>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div role="tabpanel" class="tab-pane" id="state">...</div>
                                <div role="tabpanel" class="tab-pane" id="district">...</div>
                                <div role="tabpanel" class="tab-pane" id="constituency">...</div>
                              </div>
                            
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-4 col-xs-12 col-sm-4">
                	<div class="panel panel-default">
                    	<div class="panel-heading bg_ff">
                        	<h4 class="panel-title text-capital">caste group wise position overview</h4>
                        </div>
                        <div class="panel-body pad_0">
                        	<div>

                              <!-- Nav tabs -->
                              <ul class="nav nav-tabs navTabsSelection navTabSelectionSlick" role="tablist">
                                <li role="presentation" class="active NavTab"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">Home</a></li>
                                <li role="presentation" class="NavTab"><a href="#profile" aria-controls="profile" role="tab" data-toggle="tab">Profile</a></li>
                                <li role="presentation" class="NavTab"><a href="#messages" aria-controls="messages" role="tab" data-toggle="tab">Messages</a></li>
                                <li role="presentation" class="NavTab"><a href="#settings" aria-controls="settings" role="tab" data-toggle="tab">Settings</a></li>
                              </ul>
                            
                              <!-- Tab panes -->
                              <div class="tab-content navTabsSelectionBody">
                                <div role="tabpanel" class="tab-pane active" id="home">
                                	<div class="table-responsive">
                                    	<table class="table table-bordered dataTableCasteGroup">
                                        	<thead>
                                            	<th></th>
                                                <th>OC</th>
                                                <th>SC</th>
                                                <th>ST</th>
                                                <th>BC</th>
                                            </thead>
                                            <tbody>
                                            	<tr>
                                                	<td>Chairmen</td>
                                                    <td>52</td>
                                                    <td>52</td>
                                                    <td>62</td>
                                                    <td>62</td>
                                                </tr>
                                                <tr>
                                                	<td>Chairmen</td>
                                                    <td>52</td>
                                                    <td>52</td>
                                                    <td>62</td>
                                                    <td>62</td>
                                                </tr>
                                                <tr>
                                                	<td>Chairmen</td>
                                                    <td>52</td>
                                                    <td>52</td>
                                                    <td>62</td>
                                                    <td>62</td>
                                                </tr>
                                                <tr>
                                                	<td>Chairmen</td>
                                                    <td>52</td>
                                                    <td>52</td>
                                                    <td>62</td>
                                                    <td>62</td>
                                                </tr>
                                                <tr>
                                                	<td>Chairmen</td>
                                                    <td>52</td>
                                                    <td>52</td>
                                                    <td>62</td>
                                                    <td>62</td>
                                                </tr>
                                                <tr>
                                                	<td>Chairmen</td>
                                                    <td>52</td>
                                                    <td>52</td>
                                                    <td>62</td>
                                                    <td>62</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                                <div role="tabpanel" class="tab-pane" id="profile">
                                	<div class="table-responsive">
                                    	<table class="table table-bordered dataTableCasteGroup">
                                        	<thead>
                                            	<th></th>
                                                <th>OC</th>
                                                <th>SC</th>
                                                <th>ST</th>
                                                <th>BC</th>
                                            </thead>
                                            <tbody>
                                            	<tr>
                                                	<td>Chairmen</td>
                                                    <td>52</td>
                                                    <td>52</td>
                                                    <td>62</td>
                                                    <td>62</td>
                                                </tr>
                                                <tr>
                                                	<td>Chairmen</td>
                                                    <td>52</td>
                                                    <td>52</td>
                                                    <td>62</td>
                                                    <td>62</td>
                                                </tr>
                                                <tr>
                                                	<td>Chairmen</td>
                                                    <td>52</td>
                                                    <td>52</td>
                                                    <td>62</td>
                                                    <td>62</td>
                                                </tr>
                                                <tr>
                                                	<td>Chairmen</td>
                                                    <td>52</td>
                                                    <td>52</td>
                                                    <td>62</td>
                                                    <td>62</td>
                                                </tr>
                                                <tr>
                                                	<td>Chairmen</td>
                                                    <td>52</td>
                                                    <td>52</td>
                                                    <td>62</td>
                                                    <td>62</td>
                                                </tr>
                                                <tr>
                                                	<td>Chairmen</td>
                                                    <td>52</td>
                                                    <td>52</td>
                                                    <td>62</td>
                                                    <td>62</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                                <div role="tabpanel" class="tab-pane" id="messages">
                                	<div class="table-responsive">
                                    	<table class="table table-bordered dataTableCasteGroup">
                                        	<thead>
                                            	<th></th>
                                                <th>OC</th>
                                                <th>SC</th>
                                                <th>ST</th>
                                                <th>BC</th>
                                            </thead>
                                            <tbody>
                                            	<tr>
                                                	<td>Chairmen</td>
                                                    <td>52</td>
                                                    <td>52</td>
                                                    <td>62</td>
                                                    <td>62</td>
                                                </tr>
                                                <tr>
                                                	<td>Chairmen</td>
                                                    <td>52</td>
                                                    <td>52</td>
                                                    <td>62</td>
                                                    <td>62</td>
                                                </tr>
                                                <tr>
                                                	<td>Chairmen</td>
                                                    <td>52</td>
                                                    <td>52</td>
                                                    <td>62</td>
                                                    <td>62</td>
                                                </tr>
                                                <tr>
                                                	<td>Chairmen</td>
                                                    <td>52</td>
                                                    <td>52</td>
                                                    <td>62</td>
                                                    <td>62</td>
                                                </tr>
                                                <tr>
                                                	<td>Chairmen</td>
                                                    <td>52</td>
                                                    <td>52</td>
                                                    <td>62</td>
                                                    <td>62</td>
                                                </tr>
                                                <tr>
                                                	<td>Chairmen</td>
                                                    <td>52</td>
                                                    <td>52</td>
                                                    <td>62</td>
                                                    <td>62</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                                <div role="tabpanel" class="tab-pane" id="settings">
                                	<div class="table-responsive">
                                    	<table class="table table-bordered dataTableCasteGroup">
                                        	<thead>
                                            	<th></th>
                                                <th>OC</th>
                                                <th>SC</th>
                                                <th>ST</th>
                                                <th>BC</th>
                                            </thead>
                                            <tbody>
                                            	<tr>
                                                	<td>Chairmen</td>
                                                    <td>52</td>
                                                    <td>52</td>
                                                    <td>62</td>
                                                    <td>62</td>
                                                </tr>
                                                <tr>
                                                	<td>Chairmen</td>
                                                    <td>52</td>
                                                    <td>52</td>
                                                    <td>62</td>
                                                    <td>62</td>
                                                </tr>
                                                <tr>
                                                	<td>Chairmen</td>
                                                    <td>52</td>
                                                    <td>52</td>
                                                    <td>62</td>
                                                    <td>62</td>
                                                </tr>
                                                <tr>
                                                	<td>Chairmen</td>
                                                    <td>52</td>
                                                    <td>52</td>
                                                    <td>62</td>
                                                    <td>62</td>
                                                </tr>
                                                <tr>
                                                	<td>Chairmen</td>
                                                    <td>52</td>
                                                    <td>52</td>
                                                    <td>62</td>
                                                    <td>62</td>
                                                </tr>
                                                <tr>
                                                	<td>Chairmen</td>
                                                    <td>52</td>
                                                    <td>52</td>
                                                    <td>62</td>
                                                    <td>62</td>
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
    	<div class="col-md-12 col-sm-12 col-xs-12">
        	<div class="bg_ff pad_10">
            	<div class="row">
                	<div class="col-md-2 col-sm-4 col-xs-12">
                    	<label>Position</label>
                        <select class="chosenSelect" id="positionId">
                        	<option value="all">ALL</option>
                        </select>
                    </div>
                    <div class="col-md-2 col-sm-4 col-xs-12">
                    	<label>Location Level</label>
                        <select class="chosenSelect" id="locationLevelId">
                        	<option value="all">ALL</option>  
                        </select>
                    </div>
                    <div class="col-md-2 col-sm-4 col-xs-12">    
                    	<label>Department</label>
                        <select class="chosenSelect" id="departmentId">       
                        	<option value="all">ALL</option>
                        </select>
                    </div>
                    <div class="col-md-2 col-sm-4 col-xs-12">
                    	<label>Corporation</label>
                        <select class="chosenSelect" id="corporationId">
                        	<option value="all">ALL</option>
                        </select>
                    </div>
                    <div class="col-md-2 col-sm-4 col-xs-12">
                    	<label>Caste Group</label>
                        <select class="chosenSelect" id="casteGroupId">
                        	<option value="all">ALL</option>
                        </select>
                    </div>
                    <div class="col-md-2 col-sm-4 col-xs-12">
                    	<label>Position Status</label>
                        <select class="chosenSelect" id="positionStatusId">
                        	<option value="all">ALL</option>  
                        </select>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-12 col-xs-12 col-sm-12">
        	<div class="row m_top10">
            	<div class="col-md-12 col-xs-12 col-sm-12">
                	<h4>Demographics Positions Overview</h4>
                    <h4 class="showingResult">showing results</h4>
                    <ul class="geoGrpahicBreadCrumb">
                        <li><span>All</span> Positions</li>
                        <li><span>All</span> locaion level</li>
                        <li><span>All</span> department</li>
                        <li><span>All</span> corporation</li>
                        <li><span>All</span> caste groups</li>
                        <li><span>G.O Issued</span></li>
                    </ul>
                </div>
                <div class="col-md-12 col-xs-12 col-sm-12 m_top10">
                	<div class="pad_15 bg_ff">
                    	<div class="row">
                        	<div class="col-md-2 col-xs-12 col-sm-2">
                            	<table class="table table-bordered table-condensed bg_F1">
                                	<tr>
                                    	<td rowspan="2" style="vertical-align:middle">
                                        	<p class="text-capital">total</p>
                                            <p>1200</p>
                                        </td>
                                        <td>
                                        	<p class="text-capital">male</p>
                                            <p>1200</p>
                                        </td>
                                    </tr>
                                    <tr>
                                    	<td>
                                        	<p class="text-capital">Female</p>
                                            <p>1200</p>
                                        </td>
                                    </tr>
                                </table>
                            </div>
                            <div class="col-md-5 col-xs-12 col-sm-5">
                            	<table class="table table-bordered bg_D4 tablePadding">
                                	<tr class="text-center">
                                    	<td>OC</td>
                                        <td>BC</td>
                                        <td>SC</td>
                                        <td>ST</td>
                                    </tr>
                                    <tr class="text-center">
                                    	<td>400</td>
                                        <td>400</td>
                                        <td>400</td>
                                        <td>400</td>
                                    </tr>
                                </table>
                            </div>
                            <div class="col-md-5 col-xs-12 col-sm-5">
                            	<table class="table table-bordered bg_CB tablePadding">
                                	<tr>
                                    	<td>20-29 Age</td>
                                        <td>30-39 Age</td>
                                        <td>40-49 Age</td>
                                        <td>50-59 Age</td>
                                        <td>60+ Age</td>
                                    </tr>
                                    <tr>
                                    	<td class="text-center">100</td>
                                        <td class="text-center">100</td>
                                        <td class="text-center">100</td>
                                        <td class="text-center">100</td>
                                        <td class="text-center">100</td>
                                    </tr>
                                </table>
                            </div>
                        </div>
                        <div class="row m_top20">
                        	<div class="col-md-8 col-xs-12 col-sm-8">
                            	<div class="table-responsive">
                                	<table class="table table-bordered text-center text-capital">
                                    	<thead class="bg_ef text-capital text-center">
                                        	<tr>
                                            	<th rowspan="2" style="vertical-align:middle;">caste<br/> group</th>
                                                <th colspan="3">total</th>
                                                <th colspan="2">20 - 29</th>
                                                <th colspan="2">20 - 29</th>
                                                <th colspan="2">20 - 29</th>
                                                <th colspan="2">20 - 29</th>
                                                <th colspan="2">60+</th>
                                            </tr>
                                            <tr>
                                            	<th>T</th>
                                                <th>M</th>
                                                <th>F</th>
                                                <th>M</th>
                                                <th>F</th>
                                                <th>M</th>
                                                <th>F</th>
                                                <th>M</th>
                                                <th>F</th>
                                                <th>M</th>
                                                <th>F</th>
                                                <th>M</th>
                                                <th>F</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                        	<tr>
                                            	<td>OC</td>
                                                <td>35</td>
                                                <td>35</td>
                                                <td>35</td>
                                                <td>35</td>
                                                <td>35</td>
                                                <td>35</td>
                                                <td>35</td>
                                                <td>35</td>
                                                <td>35</td>
                                                <td>35</td>
                                                <td>35</td>
                                                <td>35</td>
                                                <td>35</td>
                                            </tr>
                                            <tr>
                                            	<td>OC</td>
                                                <td>35</td>
                                                <td>35</td>
                                                <td>35</td>
                                                <td>35</td>
                                                <td>35</td>
                                                <td>35</td>
                                                <td>35</td>
                                                <td>35</td>
                                                <td>35</td>
                                                <td>35</td>
                                                <td>35</td>
                                                <td>35</td>
                                                <td>35</td>
                                            </tr>
                                            <tr>
                                            	<td>OC</td>
                                                <td>35</td>
                                                <td>35</td>
                                                <td>35</td>
                                                <td>35</td>
                                                <td>35</td>
                                                <td>35</td>
                                                <td>35</td>
                                                <td>35</td>
                                                <td>35</td>
                                                <td>35</td>
                                                <td>35</td>
                                                <td>35</td>
                                                <td>35</td>
                                            </tr>
                                            <tr>
                                            	<td>OC</td>
                                                <td>35</td>
                                                <td>35</td>
                                                <td>35</td>
                                                <td>35</td>
                                                <td>35</td>
                                                <td>35</td>
                                                <td>35</td>
                                                <td>35</td>
                                                <td>35</td>
                                                <td>35</td>
                                                <td>35</td>
                                                <td>35</td>
                                                <td>35</td>
                                            </tr>
                                            <tr class="bg_cc text-center text-capital">
                                            	<td>total</td>
                                                <td colspan="3">760</td>
                                                <td colspan="2">446</td>
                                                <td colspan="2">446</td>
                                                <td colspan="2">446</td>
                                                <td colspan="2">446</td>
                                                <td colspan="2">446</td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                                <div class="table-responsive m_top20">
                                	<table class="table table-condensed" style="border:1px solid #ddd;">
                                        <thead class="bg_ef text-capital">
                                            <th>District</th>
                                            <th>Finalised Positions Total</th>
                                            <th>Male</th>
                                            <th>Female</th>
                                            <th>20-29 AGE</th>
                                            <th>30-39 AGE</th>
                                            <th>50-59 AGE</th>
                                            <th>60+ AGE</th>
                                            <th></th>
                                        </thead>
                                        <tbody>
                                            <tr class="text-capital">
                                                <td>east godavari</td>
                                                <td>10</td>
                                                <td>10</td>
                                                <td>10</td>
                                                <td>10</td>
                                                <td>10</td>
                                                <td>10</td>
                                                <td>10</td>
                                                <td><i class="glyphicon glyphicon-plus changeIconClass"></i></td>
                                            </tr>
                                            <tr class="showHideTr" style="display:none;">
                                                <td colspan="9" class="pad_15">
                                                    <table class="table table-condensed table-striped">
                                                        <thead class="text-capital">
                                                            <th>Position</th>
                                                            <th>total positions</th>
                                                            <th>M</th>
                                                            <th>F</th>
                                                            <th>20-29</th>
                                                            <th>30-39</th>
                                                            <th>50-59</th>
                                                            <th>60+</th>
                                                        </thead>
                                                        <tbody class="text-capital">
                                                            <tr>
                                                                <td>charimen</td>
                                                                <td>04</td>
                                                                <td>04</td>
                                                                <td>04</td>
                                                                <td>04</td>
                                                                <td>04</td>
                                                                <td>04</td>
                                                                <td>04</td>
                                                            </tr>
                                                            <tr>
                                                                <td>charimen</td>
                                                                <td>04</td>
                                                                <td>04</td>
                                                                <td>04</td>
                                                                <td>04</td>
                                                                <td>04</td>
                                                                <td>04</td>
                                                                <td>04</td>
                                                            </tr>
                                                            <tr>
                                                                <td>charimen</td>
                                                                <td>04</td>
                                                                <td>04</td>
                                                                <td>04</td>
                                                                <td>04</td>
                                                                <td>04</td>
                                                                <td>04</td>
                                                                <td>04</td>
                                                            </tr>
                                                            <tr>
                                                                <td>charimen</td>
                                                                <td>04</td>
                                                                <td>04</td>
                                                                <td>04</td>
                                                                <td>04</td>
                                                                <td>04</td>
                                                                <td>04</td>
                                                                <td>04</td>
                                                            </tr>
                                                        </tbody>
                                                    </table>
                                                </td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                            <div class="col-md-4 col-xs-12 col-sm-4">
                            	<div style="border:1px solid #ddd;padding:10px">
                                	<h4 class="panel-title">CASTE GROUP</h4>
	                            	<div id="casteGroup" style="height:220px;"></div>
                                </div>
                                <div style="border:1px solid #ddd;padding:10px" class="m_top20">
                                	<h4 class="panel-title">CASTE GROUP</h4>
	                                <div id="casteWisePositions"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-12 col-xs-12 col-sm-12">
        	<div class="row m_top10">
            	<div class="col-md-12 col-xs-12 col-sm-12">
                	<h4>Grographic Positions Overview</h4>
                    <h4 class="showingResult">showing results</h4>
                    <ul class="geoGrpahicBreadCrumb">
                        <li><span>All</span> Positions</li>
                        <li><span>All</span> locaion level</li>
                        <li><span>All</span> department</li>
                        <li><span>All</span> corporation</li>
                        <li><span>All</span> caste groups</li>
                        <li><span>G.O Issued</span></li>
                    </ul>
                </div>
                <div class="col-md-12 col-xs-12 col-sm-12 m_top10">
                	<div class="pad_10 bg_ff">
                    	<div class="table-responsive">
                        	<table class="table table-condensed" style="border:1px solid #ddd;">
                            	<thead class="bg_ef text-capital">
                                	<th>District</th>
                                    <th>Finalised Positions Total</th>
                                    <th>Male</th>
                                    <th>Female</th>
                                    <th>20-29 AGE</th>
                                    <th>30-39 AGE</th>
                                    <th>50-59 AGE</th>
                                    <th>60+ AGE</th>
                                    <th></th>
                                </thead>
                                <tbody>
                                	<tr class="text-capital">
                                    	<td>east godavari</td>
                                        <td>10</td>
                                        <td>10</td>
                                        <td>10</td>
                                        <td>10</td>
                                        <td>10</td>
                                        <td>10</td>
                                        <td>10</td>
                                        <td><i class="glyphicon glyphicon-plus changeIconClass"></i></td>
                                    </tr>
                                    <tr class="showHideTr">
                                    	<td colspan="9" class="pad_15">
                                        	<table class="table table-condensed table-striped">
                                            	<thead class="text-capital">
                                                	<th>Position</th>
                                                    <th>total positions</th>
                                                    <th>M</th>
                                                    <th>F</th>
                                                    <th>20-29</th>
                                                    <th>30-39</th>
                                                    <th>50-59</th>
                                                    <th>60+</th>
                                                </thead>
                                                <tbody class="text-capital">
                                                	<tr>
                                                    	<td>charimen</td>
                                                        <td>04</td>
                                                        <td>04</td>
                                                        <td>04</td>
                                                        <td>04</td>
                                                        <td>04</td>
                                                        <td>04</td>
                                                        <td>04</td>
                                                    </tr>
                                                    <tr>
                                                    	<td>charimen</td>
                                                        <td>04</td>
                                                        <td>04</td>
                                                        <td>04</td>
                                                        <td>04</td>
                                                        <td>04</td>
                                                        <td>04</td>
                                                        <td>04</td>
                                                    </tr>
                                                    <tr>
                                                    	<td>charimen</td>
                                                        <td>04</td>
                                                        <td>04</td>
                                                        <td>04</td>
                                                        <td>04</td>
                                                        <td>04</td>
                                                        <td>04</td>
                                                        <td>04</td>
                                                    </tr>
                                                    <tr>
                                                    	<td>charimen</td>
                                                        <td>04</td>
                                                        <td>04</td>
                                                        <td>04</td>
                                                        <td>04</td>
                                                        <td>04</td>
                                                        <td>04</td>
                                                        <td>04</td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </td>
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
<script src="dist/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="dist/js/bootstrap.js" type="text/javascript"></script>
<script src="dist/Plugins/Chosen/chosen.jquery.js" type="text/javascript"></script>
<script src="dist/Plugins/Datatables/datatables.js" type="text/javascript"></script>
<script src="dist/NominatedPost/Slick/slick.js" type="text/javascript"></script>
<script src="dist/Plugins/Highcharts/highcharts.js" type="text/javascript"></script>
<script src="dist/NominatedPost/Amcharts/amcharts.js"></script>
<script type="text/javascript" src="js/nominatedPosts/nominatedPostMainDashboard.js"></script>
<script src="dist/NominatedPost/Amcharts/gauge.js"></script>
<script type="text/javascript">
$('.chosenSelect').chosen();
$(".navTabSelectionSlick li a").on('click', function(e){
    $(".navTabsSelection li").removeClass('active');
    $(this).addClass('active');
});
$(".navTabSelectionSlick").slick({
	  infinite: false,
	  speed: 300,
	  slidesToShow: 3,
	  centerMode: false,
	  variableWidth: true
});
$(".dataTableCasteGroup").dataTable({
	"sDom": '<"top">rt<"bottom"><"clear"iflp>'
});
$(".dataTableCaste").dataTable({
	"sDom": '<"top">rt<"bottom"><"clear"iflp>'
});
$(function () {
    $('#casteGroup').highcharts({
		colors:['#E58D45','#867DC0','#65A7E1','#7DC1C2'],
        chart: {
            type: 'pie',
            options3d: {
                enabled: true,
                alpha: 45
            }
        },
        plotOptions: {
            pie: {
                innerSize: 100,
                depth: 45
            }
        },
        series: [{
            name: 'CASTE GROUP',
            data: [
                ['OC', 25],
                ['BC', 25],
                ['SC', 25],
                ['ST', 25]
            ]
        }]
    });
	$('#Positions').highcharts({
		color:['#FED501','#E58D45','#DD675D','#65A7E1','#7DC1C0','#5C2D25'],
        chart: {
            type: 'column'
        },
        xAxis: {
			labels: {
                enabled: false
            },
            categories: [
                'Jan',
                'Feb',
                'Mar',
                'Apr',
                'May',
                'Jun',
                'Jul',
                'Aug',
                'Sep',
                'Oct',
                'Nov',
                'Dec'
            ],
            crosshair: true
        },
        yAxis: {
            min: 0,
            title: {
                text: ' '
            },
			labels: {
                enabled: false
            }
        },
        tooltip: {
            headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
            pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                '<td style="padding:0"><b>{point.y:.1f} mm</b></td></tr>',
            footerFormat: '</table>',
            shared: true,
            useHTML: true
        },
        plotOptions: {
            column: {
                pointPadding: 0.2,
                borderWidth: 0
            }
        },
        series: [{
            name: 'Tokyo',
            data: [49.9, 71.5, 106.4, 129.2, 144.0, 176.0, 135.6, 148.5, 216.4, 194.1, 95.6, 54.4]

        }]
    });
	$('#applications').highcharts({
		colors:['#E58D45','#867DC0','#65A7E1','#7DC1C2'],
        chart: {
            type: 'pie',
            options3d: {
                enabled: true,
                alpha: 45
            }
        },
        plotOptions: {
            pie: {
                innerSize: 100,
                depth: 45
            }
        },
        series: [{
            name: 'CASTE GROUP',
            data: [
                ['OC', 25],
                ['BC', 25],
                ['SC', 25],
                ['ST', 25]
            ]
        }]
    });
	 $('#casteWisePositions').highcharts({
        chart: {
            type: 'bar'
        },
        xAxis: {
            categories: ['Reddy', 'Kappu', 'Valma', 'Nayudu', 'Madiga'],
            title: {
                text: null
            }
        },
        yAxis: {
            min: 0,
            title: {
                text: 'POSITIONS',
                align: 'high'
            },
            labels: {
                overflow: 'justify'
            }
        },
        tooltip: {
            valueSuffix: ' CASTE'
        },
        plotOptions: {
            bar: {
                dataLabels: {
                    enabled: true
                }
            }
        },
        legend: {
            layout: 'vertical',
            align: 'right',
            verticalAlign: 'top',
            x: -40,
            y: 80,
            floating: true,
            borderWidth: 1,
            backgroundColor: ((Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'),
            shadow: true
        },
        credits: {
            enabled: false
        },
        series: [{
            name: 'CASTE',
            data: [107, 31, 635, 203, 2]
        }]
    });
});
$(document).on("click",".changeIconClass",function(){
	$(this).closest('tr').next('tr.showHideTr').toggle();
	$(this).toggleClass("glyphicon-minus");
});
var gaugeChart = AmCharts.makeChart("chartdiv", {
  "type": "gauge",
  "theme": "light",
  "axes": [{
    "axisAlpha": 0,
    "tickAlpha": 0,
    "labelsEnabled": false,
    "startValue": 0,
    "endValue": 100,
    "startAngle": 0,
    "endAngle": 270,
    "bands": [{
      "color": "#fff",
      "startValue": 0,
      "endValue": 100,
      "radius": "100%",
      "innerRadius": "85%"
    }, {
      "color": "#FDD401",
      "startValue": 0,
      "endValue": 80,
      "radius": "100%",
      "innerRadius": "95%",
      "balloonText": "90%"
    },{
      "color": "#fff",
      "startValue": 0,
      "endValue": 100,
      "radius": "88%",
      "innerRadius": "88%"
    }, {
      "color": "#E48D45",
      "startValue": 0,
      "endValue": 80,
      "radius": "88%",
      "innerRadius": "83%",
      "balloonText": "90%"
    },{
      "color": "#fff",
      "startValue": 0,
      "endValue": 100,
      "radius": "75%",
      "innerRadius": "75%"
    }, {
      "color": "#DE675D",
      "startValue": 0,
      "endValue": 80,
      "radius": "75%",
      "innerRadius": "70%",
      "balloonText": "90%"
    },{
      "color": "#fff",
      "startValue": 0,
      "endValue": 100,
      "radius": "65%",
      "innerRadius": "65%"
    }, {
      "color": "#65A6E2",
      "startValue": 0,
      "endValue": 80,
      "radius": "65%",
      "innerRadius": "60%",
      "balloonText": "90%"
    }, {
      "color": "#fff",
      "startValue": 0,
      "endValue": 100,
      "radius": "50%",
      "innerRadius": "50%"
    }, {
      "color": "#867CC0",
      "startValue": 0,
      "endValue": 35,
      "radius": "50%",
      "innerRadius": "45%",
      "balloonText": "35%"
    }, {
      "color": "#fff",
      "startValue": 0,
      "endValue": 100,
      "radius": "35%",
      "innerRadius": "35%"
    }, {
      "color": "#7DC0C2",
      "startValue": 0,
      "endValue": 92,
      "radius": "35%",
      "innerRadius": "30%",
      "balloonText": "92%"
    }, {
      "color": "#fff",
      "startValue": 0,
      "endValue": 100,
      "radius": "20%",
      "innerRadius": "0%"
    }, {
      "color": "#61342D",
      "startValue": 0,
      "endValue": 48,
      "radius": "20%",
      "innerRadius": "15%",
      "balloonText": "48%"
    }]
  }],
  "allLabels": [{
    "text": "1 option",
    "x": "49%",
    "y": "5%",
    "size": 10,
    "bold": true,
    "color": "#333",
    "align": "right"
  }, {
    "text": "2 option",
    "x": "49%",
    "y": "10%",
    "size": 10,
    "bold": true,
    "color": "#333",
    "align": "right"
  }, {
    "text": "3 option",
    "x": "49%",
    "y": "15%",
    "size": 10,
    "bold": true,
    "color": "#333",
    "align": "right"
  }, {
    "text": "4 option",
    "x": "49%",
    "y": "20%",
    "size": 10,
    "bold": true,
    "color": "#333",
    "align": "right"
  }, {
    "text": "5 option",
    "x": "49%",
    "y": "26%",
    "size": 10,
    "bold": true,
    "color": "#333",
    "align": "right"
  }, {
    "text": "6 option",
    "x": "49%",
    "y": "33%",
    "size": 10,
    "bold": true,
    "color": "#333",
    "align": "right"
  }, {
    "text": "7 option",
    "x": "49%",
    "y": "40%",
    "size": 10,
    "bold": true,
    "color": "#333",
    "align": "right"
  }],
  "export": {
    "enabled": false
  }
});
</script>
</body>
</html>
