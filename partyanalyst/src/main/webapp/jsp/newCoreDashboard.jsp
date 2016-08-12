<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>New Core DashBoard</title>
<link href="newCoreDashBoard/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="newCoreDashBoard/css/custom.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Oswald" rel="stylesheet" type="text/css">
<link href="newCoreDashBoard/Plugins/Slick/slick.css" type="text/css" rel="stylesheet"/>
<link href="newCoreDashBoard/Plugins/Slick/slick-theme.css" type="text/css" rel="stylesheet"/>
</head>
<body>
<header>
	<nav class="navbar navbar-default navbarHeader">
      <div class="container">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">
          	<span class="logo"></span>
            <span class="logoText">TDP PARTY</span>
          </a>
        </div>
    
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse " id="bs-example-navbar-collapse-1">
          <ul class="nav navbar-nav navbar-right">
          	<li><a href="#"><i class="glyphicon glyphicon-gift"></i></a></li>
            <li><a href="#"><i class="glyphicon glyphicon-bell"></i></a></li>
            <li class="dropdown profileDropDown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><img src="dist/img/logo.png" class="profileImage"/> <span class="caret"></span></a>
              <ul class="dropdown-menu">
                <li><a href="#">My Profile</a></li>
                <li><a href="#">Change Password</a></li>
                <li role="separator" class="divider"></li>
                <li><a href="#">Sign Out</a></li>
              </ul>
            </li>
          </ul>
        </div><!-- /.navbar-collapse -->
      </div><!-- /.container-fluid -->
    </nav>
    <div class="navbar navbar-default navbarProfile">
    	<div class="container">
        	<div class="row">
            	<div class="col-md-7 col-xs-12 col-sm-7">
                	<ul class="nav navbar-nav navbar-left headerProfileName">
                        <li class="dropdown profileDropDown toggleViewIcon">
                          <a href="#">ASHOK DAKAVARAM 
                          	<span class="caretBackground">
                            	<span class="caret"></span>
                            </span>
                          </a>
                          <div class="dropdown-menu settingsDropDownOptionsView">
                          	<div class="input-group">
                            	<input type="text" class="form-control"/>
                                <span class="input-group-addon">
                                	<i class="glyphicon glyphicon-search"></i>
                                </span>
                            </div>
                            <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
                              <div class="panel panel-default panelProfileView">
                                <div class="panel-heading" role="tab" id="headingOne">
                                  <a role="button" class="collapsed profieViewCollapse" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                                    <h4 class="panel-title">
                                      GS VIew
                                      <span class="profileImageView">
                                      	<img src="dist/img/logo.png" />
                                      </span>
                                    </h4>
                                  </a>
                                </div>
                                <div id="collapseOne" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">
                                  <div class="panel-body">
                                    <!---SUB VIEW-->
                                    <div class="panel-group" id="accordionSub" role="tablist" aria-multiselectable="true">
                                      <div class="panel panel-default panelProfileView">
                                        <div class="panel-heading" role="tab" id="headingOneSubView2">
                                          <a role="button" class="collapsed profieViewCollapse" data-toggle="collapse" data-parent="#accordionSub" href="#collapseOneSubView2" aria-expanded="true" aria-controls="collapseOneSubView2">
                                            <h4 class="panel-title">
                                              GS - Nageshwar Rao
                                              <span class="profileImageView">
                                                <img src="dist/img/logo.png" />
                                              </span>
                                            </h4>
                                          </a>
                                        </div>
                                        <div id="collapseOneSubView2" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOneSubView2">
                                          <div class="panel-body">
                                                <!-- Sub Sub View -->
                                                    <div class="panel-group" id="accordionSubSub" role="tablist" aria-multiselectable="true">
                                                      <div class="panel panel-default panelProfileView">
                                                        <div class="panel-heading" role="tab" id="headingOneSubSubView2">
                                                          <a role="button" class="collapsed profieViewCollapse" data-toggle="collapse" data-parent="#accordionSubSub" href="#collapseOneSubSubView2" aria-expanded="true" aria-controls="collapseOneSubSubView2">
                                                            <h4 class="panel-title">
                                                              GS - G Jaya Nageshwar Reddy
                                                              <span class="profileImageView">
                                                                <img src="dist/img/logo.png" />
                                                              </span>
                                                            </h4>
                                                          settingsDropDownOptionsView                                                          </a>
                                                        </div>
                                                        <div id="collapseOneSubSubView2" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOneSubSubView2">
                                                          <div class="panel-body pad_15">
                                                            <ul class="radioStyling">
                                                              <li>
                                                                <input type="radio" id="f" name="selector">
                                                                <label for="f">MadakaSira - MLA</label>
                                                                
                                                                <div class="check"></div>
                                                              </li>
                                                              <li>
                                                                <input type="radio" id="s" name="selector">
                                                                <label for="s">Hindupur - MLA - Nandamuri BalaKrishna</label>
                                                                
                                                                <div class="check"><div class="inside"></div></div>
                                                              </li>
                                                            </ul>
                                                          </div>
                                                        </div>
                                                      </div>
                                                      <div class="panel panel-default panelProfileView">
                                                        <div class="panel-heading" role="tab" id="headingTwoSubSubView1">
                                                            <a class="collapsed profieViewCollapse" role="button" data-toggle="collapse" data-parent="#accordionSubSub" href="#collapseTwoSubSubView1" aria-expanded="false" aria-controls="collapseTwoSubSubView1">
                                                              <h4 class="panel-title">
                                                                 GS - B Buchaiah Chowdary
                                                                 <span class="profileImageView">
                                                                    <img src="dist/img/logo.png" />
                                                                  </span>
                                                              </h4>
                                                            </a>
                                                        </div>
                                                        <div id="collapseTwoSubSubView1" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwoSubSubView1">
                                                          <div class="panel-body">
                                                            
                                                          </div>
                                                        </div>
                                                      </div>
                                                      <div class="panel panel-default panelProfileView">
                                                        <div class="panel-heading" role="tab" id="headingThreeSubSubView">
                                                            <a class="collapsed profieViewCollapse" role="button" data-toggle="collapse" data-parent="#accordionSubSub" href="#collapseThreeSubSubView" aria-expanded="false" aria-controls="collapseThreeSubSubView">
                                                              <h4 class="panel-title">
                                                                 GS - B T Naidu
                                                                 <span class="profileImageView">
                                                                    <img src="dist/img/logo.png" />
                                                                  </span>
                                                              </h4>
                                                            </a>
                                                        </div>
                                                        <div id="collapseThreeSubSubView" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThreeSubSubView">
                                                          <div class="panel-body">
                                                            
                                                          </div>
                                                        </div>
                                                      </div>
                                                    </div>
                                                <!-- Sub Sub View End-->
                                          </div>
                                        </div>
                                      </div>
                                      <div class="panel panel-default panelProfileView">
                                        <div class="panel-heading" role="tab" id="headingTwoSubView1">
                                            <a class="collapsed profieViewCollapse" role="button" data-toggle="collapse" data-parent="#accordionSub" href="#collapseTwoSubView1" aria-expanded="false" aria-controls="collapseTwoSubView1">
                                              <h4 class="panel-title">
                                                 GS - B Buchaiah Chowdary
                                                 <span class="profileImageView">
                                                    <img src="dist/img/logo.png" />
                                                  </span>
                                              </h4>
                                            </a>
                                        </div>
                                        <div id="collapseTwoSubView1" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwoSubView1">
                                          <div class="panel-body">
                                            
                                          </div>
                                        </div>
                                      </div>
                                      <div class="panel panel-default panelProfileView">
                                        <div class="panel-heading" role="tab" id="headingThreeSubView">
                                            <a class="collapsed profieViewCollapse" role="button" data-toggle="collapse" data-parent="#accordionSub" href="#collapseThreeSubView" aria-expanded="false" aria-controls="collapseThreeSubView">
                                              <h4 class="panel-title">
                                                 GS - B T Naidu
                                                 <span class="profileImageView">
                                                    <img src="dist/img/logo.png" />
                                                  </span>
                                              </h4>
                                            </a>
                                        </div>
                                        <div id="collapseThreeSubView" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThreeSubView">
                                          <div class="panel-body">
                                            
                                          </div>
                                        </div>
                                      </div>
                                    </div>
                                    <!--SUB VIEW END-->
                                  </div>
                                </div>
                              </div>
                              <div class="panel panel-default panelProfileView">
                                <div class="panel-heading" role="tab" id="headingTwo">
                                    <a class="collapsed profieViewCollapse" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                                      <h4 class="panel-title">
                                          District view
                                          <span class="profileImageView">
                                            <img src="dist/img/logo.png" />
                                          </span>
                                      </h4>
                                    </a>
                                </div>
                                <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
                                  <div class="panel-body">
                                    
                                  </div>
                                </div>
                              </div>
                              <div class="panel panel-default panelProfileView">
                                <div class="panel-heading" role="tab" id="headingThree">
                                    <a class="collapsed profieViewCollapse" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                                      <h4 class="panel-title">
                                          mp view
                                          <span class="profileImageView">
                                            <img src="dist/img/logo.png" />
                                          </span>
                                      </h4>
                                    </a>
                                </div>
                                <div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
                                  <div class="panel-body">
                                    
                                  </div>
                                </div>
                              </div>
                            </div>
                          </div>
                        </li>
                    </ul>
                </div>
                <div class="col-md-3 col-xs-12 col-sm-3">
                	<ul class="list-inline profileSelection">
                    	<li class="active">
                        	<a href="#">general</a>
                        </li>
                        <li>
                        	<a href="#">profile</a>
                        </li>
                    </ul>
                </div>
                <div class="col-md-2 col-xs-12 col-sm-2">
                	<button class="btn btn-success btnYellow"><img src="dist/img/Icon.png" alt="Icon" class="img-responsive"/>query builder</button>
                   
                    <div class="settingsDropDown">
                      <a href="#"> <i class="glyphicon glyphicon-cog settingsIcon"></i></a>                      
                      <ul class="settingsDropDownOptions">
                      	<li><a href="#">Dashboard settings</a></li>
                        <li><a href="#">committee settings</a>
                        	<div class="settingsDropDownOptionsSub">
                            	<h4 class="panel-title text-capital text-center">committees block settings</h4>
                                <div class="m_top20">
                                    <ul class="radioStyling">
                                      <li>
                                        <input type="radio" id="f-option" name="selector">
                                        <label for="f-option">Cumulative</label>
                                        
                                        <div class="check"></div>
                                      </li>
                                      <li>
                                        <input type="radio" id="s-option" name="selector">
                                        <label for="s-option">Comparative</label>
                                        
                                        <div class="check"><div class="inside"></div></div>
                                      </li>
                                    </ul>
                                </div>
                                <div>
                                    <label class="m_top20">Select Period</label>
                                    <select class="select selectBoxStyle">
                                        <option>Till Date</option>
                                    </select>
                                </div>
                                <div class="m_top20">
                                	<label class="text-capital">select type of committees</label>
                                    <ul class="committeesSelect">
                                    	<li>
                                        	<div class="squaredOne">
                                              <input type="checkbox" value="None" id="squaredOne" name="check" />
                                              <label for="squaredOne"></label>
                                            </div>
                                            <span>Main Committee</span>
                                        </li>
                                        <li>
                                        	<div class="squaredOne">
                                              <input type="checkbox" value="None" id="squaredOne1" name="check" />
                                              <label for="squaredOne1"></label>
                                            </div>
                                            <span>Telugu Yuvatha</span>
                                        </li>
                                        <li>
                                        	<div class="squaredOne">
                                              <input type="checkbox" value="None" id="squaredOne2" name="check"/>
                                              <label for="squaredOne2"></label>
                                            </div>
                                            <span>Telugu Mahila</span>
                                        </li>
                                    </ul>
                                </div>
                                <div class="m_top20 text-center">
                                	<button class="btn btn-success btnYellow btn-lg">SUBMIT</button>
                                </div>
                            </div>
                        </li>
                        <li><a href="#">meetings</a></li>
                        <li><a href="#">party news</a></li>
                        <li><a href="#">govt news</a></li>
                      </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="navbar navbar-default navbarProfile">
    	<div class="container">
        	<div class="row">
                <div class="col-md-3 col-xs-12 col-sm-3 col-md-offset-9 col-sm-offset-9">
                	<ul class="list-inline profileSelection">
                    	<li class="active">
                        	<a href="#">AP</a>
                        </li>
                        <li>
                        	<a href="#">TS</a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</header>
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
                    	<div class="col-md-12 col-xs-12 col-sm-6 committeesBlock">
                        	<ul class="committesBlockUl">
                            	<li>
                                	<h4 class="text-capital bg_49 pad_custom">main committees</h4>
                                </li>
                                <li>
                                	<h4 class="text-capital bg_49 pad_custom">affliated committees</h4>
                                </li>
                                <li style="border-bottom:1px solid #ddd;">
                                	<table class="table table-condensed">
                                        <tr>
                                            <td>
                                                <h3>9531</h3>
                                                <h5 class="text-muted text-capitalize">total</h5>
                                            </td>
                                            <td>
                                                <h3>9531</h3>
                                                <h5 class="text-muted text-capitalize">started</h5>
                                                <small class="text-success">12.2%</small>
                                            </td>
                                            <td>
                                                <h3>9531</h3>
                                                <h5 class="text-muted">Completed</h5>
                                                <small class="text-success">12.2%</small>
                                            </td>
                                        </tr>
                                    </table>
                                </li>
                                <li style="border-bottom:1px solid #ddd;padding-bottom:20px;">
                                	<table class="table table-condensed">
                                        <tr>
                                            <td>
                                                <h3>9531</h3>
                                                <h5 class="text-muted text-capitalize">Started</h5>
                                            </td>
                                            <td>
                                                <h3>9531</h3>
                                                <h5 class="text-muted text-capitalize">Completed</h5>
                                            </td>
                                        </tr>
                                    </table>
                                </li>
                                <li>
                                	<h4 class="text-capitalize m_top10" style="color:#c9c0cc">Village / Ward</h4>
                                    <table class="table table-condensed bg_ED">
                                        <tr>
                                            <td>
                                                <h5 class="text-muted text-capitalize">Total</h5>
                                                <p>16612</p>
                                            </td>
                                            <td>
                                                <h5 class="text-muted text-capitalize">Started</h5>
                                                <p>16612 <small class="text-success"> 7.2%</small></p>
                                            </td>
                                            <td>
                                                <h5 class="text-muted text-capitalize">Completed</h5>
                                                <p>16612 <small class="text-success"> 7.2%</small></p>
                                            </td>
                                        </tr>
                                    </table>
                                </li>
                                <li>
                                	<table class="table table-condensed bg_ED" style="margin-top:30px !important;">
                                        <tr>
                                            <td>
                                                <h5 class="text-muted text-capitalize">Started</h5>
                                                <p>16612</p>
                                            </td>
                                            <td>
                                                <h5 class="text-muted text-capitalize">Completed</h5>
                                                <p>16612</p>
                                            </td>
                                        </tr>
                                    </table>
                                </li>
                                <li>
                                	<h4 class="text-capitalize m_top10 text-pink">mandal / town / division</h4>
                                    <table class="table table-condensed bg_ED">
                                        <tr>
                                            <td>
                                                <h5 class="text-muted text-capitalize">Total</h5>
                                                <p>16612</p>
                                            </td>
                                            <td>
                                                <h5 class="text-muted text-capitalize">Started</h5>
                                                <p>16612 <small class="text-success"> 7.2%</small></p>
                                            </td>
                                            <td>
                                                <h5 class="text-muted text-capitalize">Completed</h5>
                                                <p>16612 <small class="text-success"> 7.2%</small></p>
                                            </td>
                                        </tr>
                                    </table>
                                </li>
                                <li>
                                	<table class="table table-condensed bg_ED" style="margin-top:30px !important;">
                                        <tr>
                                            <td>
                                                <h5 class="text-muted text-capitalize">Started</h5>
                                                <p>16612</p>
                                            </td>
                                            <td>
                                                <h5 class="text-muted text-capitalize">Completed</h5>
                                                <p>16612</p>
                                            </td>
                                        </tr>
                                    </table>
                                </li>
                                <li>
                                	<h4 class="text-capitalize m_top10" style="color:#81a0ad">district</h4>
                                    <table class="table table-condensed bg_ED">
                                        <tr>
                                            <td>
                                                <h5 class="text-muted text-capitalize">Total</h5>
                                                <p>16612</p>
                                            </td>
                                            <td>
                                                <h5 class="text-muted text-capitalize">Started</h5>
                                                <p>16612 <small class="text-success"> 7.2%</small></p>
                                            </td>
                                            <td>
                                                <h5 class="text-muted text-capitalize">Completed</h5>
                                                <p>16612 <small class="text-success"> 7.2%</small></p>
                                            </td>
                                        </tr>
                                    </table>
                                </li>
                                <li>
                                	<table class="table table-condensed bg_ED" style="margin-top:30px !important;">
                                        <tr>
                                            <td>
                                                <h5 class="text-muted text-capitalize">Started</h5>
                                                <p>16612</p>
                                            </td>
                                            <td>
                                                <h5 class="text-muted text-capitalize">Completed</h5>
                                                <p>16612</p>
                                            </td>
                                        </tr>
                                    </table>
                                </li>
                                <li>
                                	<h4 class="text-capitalize m_top10" style="color:#a2cfc9">state</h4>
                                    <table class="table table-condensed bg_ED">
                                        <tr>
                                            <td>
                                                <h5 class="text-muted text-capitalize">Total</h5>
                                                <p>16612</p>
                                            </td>
                                            <td>
                                                <h5 class="text-muted text-capitalize">Started</h5>
                                                <p>16612 <small class="text-success"> 7.2%</small></p>
                                            </td>
                                            <td>
                                                <h5 class="text-muted text-capitalize">Completed</h5>
                                                <p>16612 <small class="text-success"> 7.2%</small></p>
                                            </td>
                                        </tr>
                                    </table>
                                </li>
                                <li style="margin-top:30px;">
                                	<h4 class="text-capitalize bg_ED text-muted pad_15">no affliated committees</h4>
                                </li>
                            </ul>
                            
                        </div>
                        <div class="col-md-12 col-xs-12 col-sm-6 committeesBlock committeesHiddenBlock">
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
                        <div class="col-md-12 col-xs-12 col-sm-12 moreBlocks">
                        	<ul class="list-inline pull-right activeUlCls">
                            	<li class="active">Detailed</li>
                                <li>Comparison</li>
                                <li><i class="fa fa-gears"></i></li>
                            </ul>
                        </div>
                        <div class="col-md-12 col-xs-12 col-sm-12 moreBlocks m_top20">
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
                        <div class="col-md-12 col-xs-12 col-sm-12 moreBlocks">
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
                        </div>
                        <div class="col-md-12 col-xs-12 col-sm-12 m_top20 moreBlocks">
                        	<div class="panel panel-default panelNew">
                                <div class="panel-heading">
                                    <div class="row">
                                        <div class="col-md-8 col-xs-12 col-sm-7">
                                            <span class="headingColor text-capitalize">all committees performance cohort</span>
                                        </div>
                                        <div class="col-md-4 col-xs-12 col-sm-5">
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
                        
                    </div>
                </div>
            </div>
        </div>
	</div>
</div>
<!--
<div class="col-md-12 col-xs-12 col-sm-12 m_top20">
	<p><i>Selected:</i> <b>Main Committee</b></p>
	<div class="panel panel-default panelNew">
    	<div class="panel-heading">
        	<div class="row">
            	<div class="col-xs-12 col-sm-8 col-md-9">
                	<ul class="comparisonSelect">
                    	<li class="active">General Secretary<span class="closeIconComparison"></span></li>
                        <li>Secretary<span class="closeIconComparison"></span></li>
                        <li>M.P<span class="closeIconComparison"></span></li>
                        <li>District Incharge<span class="closeIconComparison"></span></li>
                        <li>Constituency<span class="closeIconComparison"></span></li>
                    </ul>
                </div>
                <div class="col-xs-12 col-sm-4 col-md-3">
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
                                <li><a href="#">Last 7 Days <span class="last7Days"></span></a></li>
                                <li><a href="#">First 7 Days <span class="first7Days"></span></a></li>
                                <li role="separator" class="divider"></li>
                                <li><a href="#">This Month <span class="thisMonth"></span></a></li>
                                <li><a href="#">Last Month <span class="lastMonth"></span></a></li>
                                <li><a href="#">Last 28 Days <span class="last28Days"></span></a></li>
                                <li><a href="#">Last 30 Days <span class="last30Days"></span></a></li>
                                <li><a href="#">First 28 Days<span class="first28Days"></span></a></li>
                                <li role="separator" class="divider"></li>
                                <li><a href="#">This Quarter <span class="thisQuarter"></span></a></li>
                                <li><a href="#">Last Quarter <span class="lastQuarter"></span></a></li>
                                <li><a href="#">Last 90 Days <span class="Last90Days"></span></a></li>
                                <li><a href="#">First 90 Days <span class="first90Days"></span></a></li>
                                <li role="separator" class="divider"></li>
                                <li><a href="#">This Year<span class="thisYear"></span></a></li>
                                <li><a href="#">Last Year <span class="lastYear"></span></a></li>
                                <li><a href="#">Last 365 Days <span class="last7Days"></span></a></li>
                                <li><a href="#">First 365 Days <span class="last7Days"></span></a></li>
                                <li role="separator" class="divider"></li>
                                <li><a href="#">Custom Range <span class="last7Days"></span></a></li>
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
        <div class="panel-body"></div>
    </div>                       	
</div>-->
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
		initialiseGraph();
		moreBlocks();
	});
	getUserBasicDetails();
	function onLoadCalls(){
		
		getDistrictWiseCommitteesCountReport();
		getCommitteesBasicCountReport();
		getLevelWiseBasicCommitteesCountReport();
		getUserTypeWiseCommitteesCompletedCounts();
	}


$(document).on("click",".iconExpand",function(){
	$(this).find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
	$(".committeesBlock").toggleClass("col-md-6").toggleClass("col-md-12");
	$(".committeesBlock1").toggleClass("col-md-6").toggleClass("col-md-12");
	$(".committeesBlock").css("transition"," ease-in-out, width 0.7s ease-in-out");
	$(".moreBlocks").hide();
	setTimeout(function(){
		$(".committeesHiddenBlock,.moreBlocksIcon").toggle();
		initialiseGraph();
	},800);
});
/* Notes Functionality Complete*/
$(document).on("click",".notesIcon",function(){
	$(this).closest(".panel-heading").find(".notesDropDown").toggle();
});
$(document).on("click",".btnCustomCreate",function(){
	var getNewNotes = $(".notesArea").val();
	var todayDate = moment().format("DD MMMM YYYY");
	var commentText = '<span class="notesText">'+getNewNotes+'</span> - <span class="text-muted"><i>'+todayDate+'</i></span> <i class="glyphicon glyphicon-trash pull-right hoverBlock deleteNotes"></i><i class="glyphicon glyphicon-edit pull-right hoverBlock editNotes"></i>'; 
	$(".notesUl").append("<li>"+commentText+"</li>");
	$(".notesArea").val('');	
});
$(document).on("click",".editNotes",function(){
	var notesHtml = $(this).closest("li").find(".notesText").html();
	$(".notesArea").val(notesHtml);
});
$(document).on("click",".deleteNotes",function(){
	$(this).closest("li").remove();
});
$(document).on("click",".moreBlocksIcon",function(){
	$(".moreBlocks").toggle();
	setTimeout(function(){
		moreBlocks();
		customBuildGraph();
	},1000);
});
$(document).on("click",".activeUlCls li",function(){
	$(this).closest("ul").find("li").removeClass("active");
	$(this).addClass("active");
});

function customBuildGraph()
{
	var ids = $(document).find(".graphStructure").length;
	var j = 0
	$(".graphStructure").each(function(){
		j = j + 1;
		$(this).attr("id","graphStructure_"+j+"");
		var getWidth = $("#graphStructure_"+j+"").width();
		
		var getNoOfLis = getWidth / $("#graphStructure_"+j+" li").length - 4;
		var kk = getNoOfLis.toFixed() + 'px';
		$("#graphStructure_"+j+" li").width(kk);
		
		var myColors = ['#66728C','#F56800','#31AA74'];
		var i = 0;
		$("#graphStructure_"+j+" li").each(function(){
			var l = $(this).find("span").length;
			var getNoOfSpans = $(this).find("span").length;
			$(this).find("span").each(function(){
				$(this).css('background-color', myColors[i]);
				i = (i + 1) % getNoOfSpans;
			});
			$(this).find("span").each(function(){
				var getPercentage = $(this).attr("attr_percent");
				if(l > 1)
				{
					$(this).css("height",getPercentage+'%');
				}else{
					$(this).css("height","100%");
				}
			});
		});
		
	});
}
$(document).on("click",".comparisonSelect li",function(){
	if($(this).hasClass("active") == true)
	{
		$(this).removeClass("active");
	}else{
		$(".comparisonSelect li").removeClass("active");
		$(this).addClass("active");
	}
});
comparisonDates()
function comparisonDates()
{
	$(".last7Days").html(moment().subtract(7,'days').format("DD/MM/YYYY") + ' - '+ moment().format("DD/MM/YYYY"));
/*	<button class="btn btn-default dropdown-toggle dateFinalDiv" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
	This Week <span class="text-muted">Sunday - Today</span>
	<span class="caret"></span>
  </button>
  <ul class="dropdown-menu calenarUl" aria-labelledby="dropdownMenu1">
	<li><a href="#">LifeTime</a></li>
	<li role="separator" class="divider"></li>
	<li><a href="#">This Week <span class="text-muted">Sunday - Today</span></a></li>
	<li><a href="#">Last Week <span class="lastWeekDate"></span></a></li>
	<li><a href="#">Last 7 Days <span class="last7Days"></span></a></li>
	<li><a href="#">First 7 Days <span class="first7Days"></span></a></li>
	<li role="separator" class="divider"></li>
	<li><a href="#">This Month <span class="thisMonth"></span></a></li>
	<li><a href="#">Last Month <span class="lastMonth"></span></a></li>
	<li><a href="#">Last 28 Days <span class="last28Days"></span></a></li>
	<li><a href="#">Last 30 Days <span class="last30Days"></span></a></li>
	<li><a href="#">First 28 Days<span class="first28Days"></span></a></li>
	<li role="separator" class="divider"></li>
	<li><a href="#">This Quarter <span class="thisQuarter"></span></a></li>
	<li><a href="#">Last Quarter <span class="lastQuarter"></span></a></li>
	<li><a href="#">Last 90 Days <span class="Last90Days"></span></a></li>
	<li><a href="#">First 90 Days <span class="first90Days"></span></a></li>
	<li role="separator" class="divider"></li>
	<li><a href="#">This Year<span class="thisYear"></span></a></li>
	<li><a href="#">Last Year <span class="lastYear"></span></a></li>
	<li><a href="#">Last 365 Days <span class="last7Days"></span></a></li>
	<li><a href="#">First 365 Days <span class="last7Days"></span></a></li>
	<li role="separator" class="divider"></li>
	<li><a href="#">Custom Range <span class="last7Days"></span></a></li>
  </ul>*/
}
</script>
</body>
</html>
