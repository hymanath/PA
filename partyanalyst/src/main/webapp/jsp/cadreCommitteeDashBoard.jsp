<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Committee Dashboard</title>
	<!-- Bootstrap -->
    <link href="js/cadreCommittee/dist/css/bootstrap.min.css" rel="stylesheet"/>
	 <!-- Custom Styles -->
    <link href="css/cadreCommitee/css/style.css" rel="stylesheet"/>
    	<!--Bootstrap DatePicker-->
    <!--<link href="bootstrap-daterangepicker/daterangepicker-bs3.css" rel="stylesheet" />-->
		<!--Hover Menu-->
    <link href="css/cadreCommitee/css/jquery.smartmenus.bootstrap.css" rel="stylesheet" />
    	<!--Circle-->
   <!-- <link href="js/cadreCommittee/dist/css/jquery.circliful.css" rel="stylesheet" />-->
		
</head>
<body>
	<header style="align:center;background-color:#ef4036; display:flex;border-bottom:4px solid #13a751;">
		 	<div class="col-md-6 col-md-offset-3 col-xs-6 col-xs-offset-3 col-sm-6 col-sm-offset-3 text-center">
				<img src="images/cadreCommitee/Committees_2014_logo.png" class="m_top10" title="Committee Logo" alt="committee" />
			</div>
			<div class="col-md-3  col-xs-3 col-sm-3">
               
                    <a href="#" class="dropdown-toggle btn btn-default btn-xs m_top20" data-toggle="dropdown" aria-expanded="false" style="margin-top: 60px;">
                    Menu <img src="images/cadreCommitee/menu_icon.png" />
                    </a>
                    <ul class="dropdown-menu" role="menu" aria-labelledby="drop6" style="    background-color: rgb(239, 64, 54);top: 91px;">
                      <li><a tabindex="-1" href="#">Memu #1</a></li>
                      <li><a tabindex="-1" href="#">Memu #2</a></li>
                      <li><a tabindex="-1" href="#">Memu #3</a></li>
                      <li role="presentation" class="divider" style="background-color: rgba(229, 229, 229,0.6);"></li>
                      <li><a tabindex="-1" href="#">Sign Out</a></li>
                    </ul>
                 
            </div>
	</header>
	
	<div class="container">
    	<div class="row" style="text-align:center;">
                <div class="col-md-6 col-md-offset-3 col-xs-8 col-xs-offset-2 col-sm-6 col-sm-offset-3 ">
                    <h3 class="panel-header">COMMITTEE DASHBOARD</h3>
                    <hr style="border-color:#F00;margin:0px 0px 10px 0px;" />
                </div>
        </div>
		
		<div class="row">
               <!-- <div class="col-md-12 col-xs-12  col-sm-12" style="padding-right:0px; padding-bottom:5px;">
					<div id="reportrange" class="pull-right active" style="background:rgba(0,0,0,0.1); cursor: pointer; padding: 5px 10px; border: 1px solid rgba(0,0,0,0.1);">
					  <i class="glyphicon glyphicon-calendar fa fa-calendar"></i>
					  <span></span> <b class="caret"></b>
					</div>
                </div>-->
        </div>
		
    	<div class="row">
			<table class="table table-bordered" width="100%"  >
				<tr>
					<td width="22%" style="background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.1);text-align:center;padding: 0px;">
						<div class="col-md-12">
							<h4>ANHDRA PRADESH</h4>
							<div class="row" id="totalAPCount">
								
							</div>
							<!--
							<div class="myStat2" data-dimension="180" data-text="35%" data-info="COMPLETED" data-width="10" data-fontsize="34" data-percent="35" data-fgcolor="#349866" data-bgcolor="#6D6024"></div>-->
				
						</div>
					</td>
					<td width="78%" style="padding:0px;">
						<table width="100%" class="table table-bordered" style="background-color:transparent; margin-bottom:0px;"  >
							<tr style="background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.1);">
								<td colspan="6">
									<button id="mandal" class="btn btn-xs btn-success" onclick="getCommitteeDetails('AP',this.value)">Mandal</button> | 
									<button id="town" class="btn btn-xs btn-success" onclick="getCommitteeDetails('AP',this.id)">Town</button> |	
									<button id="division" class="btn btn-xs btn-success" onclick="getCommitteeDetails('AP',this.id)">Division</button> |									
									<button id="mandalAll" class="btn btn-xs btn-default" onclick="getCommitteeDetails('AP',$(this.id))">All</button>
								</td>
							</tr>
							<tr>
								<!--<td style="padding:10px;" width="18%"><b>TOTAL MAIN </b>Committees<h4 class="m_top0">2000</h4></td>
								<td style="padding:10px;" width="10%"><span class="text-success">Started</span> Committees<br/><h4 style="display:inline">10%</h4>[100]</td>
								<td style="padding:10px;" width="10%"><span class="text-danger">Completed</span> Committees<br/><table><tr><td  class="row-table"><h4 class="row-table m_top0">50%</h4></td><td  class="row-table"><ul class="nav navbar-nav">
                              <li><a>[1000]</a>
                                    <ul class="list-group dropdown-menu arrow_box">
										  <li class="list-group-item">
											<span class="badge">14</span>
											Cras justo odio
										  </li>
										  <li class="list-group-item">
											<span class="badge">2</span>
											Dapibus ac facilisis in
										  </li>
										  <li class="list-group-item">
											<span class="badge">1</span>
											Morbi leo risus
										  </li>
									</ul>
									
									
								</li></td></tr></table></td>
								<td style="padding:10px;" width="20%">Affliated Committees<br/><span class="text-success">Started Areas</span><br/><h4 class="m_top0">100</h4></td>
								<td style="padding:10px;" width="20%"><span class="text-success">Total Started</span><br/>Affliated Committees<br/>
                                <h4 class="m_top0">
								<ul class="nav navbar-nav">
                              <li>
								<a>100</a>
                                <ul class="dropdown-menu arrow_box list-group">
                                <div class="panel panel-default m_bottom0">
                                <div class="panel-heading m_top0">COMMITTEE TYPE<span class="pull-right">TOTAL</span></div></div>
                                  <li class="list-group m_bottom0"><a>TELUGU RYTHU COMMITTEE<span class="pull-right">10</span></a></li>
                                  <li class="list-group m_bottom0"><a>SC CELL COMMITTEE<span class="pull-right">10</span></a></li>
                                  <li class="list-group m_bottom0"><a>BC CELL COMMITTEE<span class="pull-right">10</span></a>
                                    <ul class="list-group dropdown-menu-border">
										  <li class="list-group-item">
											<span class="badge">14</span>
											Cras justo odio
										  </li>
										  <li class="list-group-item">
											<span class="badge">2</span>
											Dapibus ac facilisis in
										  </li>
										  <li class="list-group-item">
											<span class="badge">1</span>
											Morbi leo risus
										  </li>
									</ul>	
                                  </li>
                                  <li class="list-group m_bottom0"><a>TELUGU MAHILA COMMITTEE<span class="pull-right">10</span></a></li>                                 
                                  <li><a>TELUGU YUVATHA COMMITTEE<span class="pull-right">10</span></a></li>
                                </ul>
                              </li>
                            </ul></h4></td>
								<td style="padding:10px;" width="28%">TOTAL <b>MEMBERS</b><h4 class="m_top0">2000</h4></td>-->
							</tr>
							<tr style="background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.1);">
								<td colspan="6">
										<button class="btn btn-xs btn-success">Village</button> | 
										<button class="btn btn-xs btn-success">Ward</button> |
										<button class="btn btn-xs btn-success">Division</button> |
										<button class="btn btn-xs btn-default">All</button>
								</td>
							</tr>
							<tr>
								<td style="padding:10px;" width="18%"><b>TOTAL MAIN </b>Committees<h4 class="m_top0">2000</h4></td>
								<td style="padding:10px;" width="10%"><span class="text-success">Started</span> Committees<br/><h4 style="display:inline">10%</h4>[100]</td>
							   <td style="padding:10px;" width="10%"><span class="text-danger">Completed</span> Committees<br/><table><tr><td  class="row-table"><h4 class="row-table m_top0">50%</h4></td><td  class="row-table">
							   <ul class="nav navbar-nav">
								<li>
									<a>[1000]</a>
                                    <ul class="list-group dropdown-menu arrow_box">
										  <li class="list-group-item">
											<span class="badge">14</span>
											Cras justo odio
										  </li>
										  <li class="list-group-item">
											<span class="badge">2</span>
											Dapibus ac facilisis in
										  </li>
										  <li class="list-group-item">
											<span class="badge">1</span>
											Morbi leo risus
										  </li>
									</ul>
								</li>	
									
									</td></tr></table></td>
								<td style="padding:10px;" width="20%">Affliated Committees<br/><span class="text-success">Started Areas</span><br/><h4 class="m_top0">100</h4></td>
								<td style="padding:10px;" width="20%"><span class="text-success">Total Started</span><br/>Affliated Committees<br/><h4 class="m_top0"> <ul class="nav navbar-nav">
                              <li><a>100</a>
                                <ul class="dropdown-menu arrow_box list-group" style="width:1200px !important">
                                <div class="panel panel-default m_bottom0">
                                <div class="panel-heading">COMMITTEE TYPE<span class="pull-right">TOTAL</span></div></div>
                                  <li class="list-group m_bottom0"><a>TELUGU RYTHU COMMITTEE<span class="pull-right">10</span></a></li>
                                  <li class="list-group m_bottom0"><a>SC CELL COMMITTEE<span class="pull-right">10</span></a></li>
                                  <li class="list-group m_bottom0"><a>BC CELL COMMITTEE<span class="pull-right">10</span></a>
                                    <ul class="list-group dropdown-menu-border">
										  <li class="list-group-item">
											<span class="badge">14</span>
											Cras justo odio
										  </li>
										  <li class="list-group-item">
											<span class="badge">2</span>
											Dapibus ac facilisis in
										  </li>
										  <li class="list-group-item">
											<span class="badge">1</span>
											Morbi leo risus
										  </li>
									</ul>
                                  </li>
                                  <li class="list-group m_bottom0"><a>TELUGU MAHILA COMMITTEE<span class="pull-right">10</span></a></li>                                 
                                  <li><a>TELUGU YUVATHA COMMITTEE<span class="pull-right">10</span></a></li>
                                </ul>
                              </li>
                            </ul></h4></td>
								<td style="padding:10px;" width="28%">TOTAL <b>MEMBERS</b><h4 class="m_top0">2000</h4></td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
        </div>
		
        <div class="row">
			<table class="table table-bordered" width="100%"  >
        	<tr>
        	    <td width="22%" style="background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.1);text-align:center;padding: 0px;">
					<div class="col-md-12" >
						<h4>TELANGANA</h4>
						<div class="row" id="totalTSCount">
								
						</div>
						<!--<div class="row ">
							<div class="col-md-5 col-md-offset-1 col-xs-3"><span style="font-size:2em;">40000</span></div>
							<div style="" class="col-md-6 col-xs-3 text-left"><small>TOTAL MAIN COMMITTEES</small></div>
						</div>
						<div class="myStat3" data-dimension="180" data-text="35%" data-info="COMPLETED" data-width="10" data-fontsize="34" data-percent="35" data-fgcolor="#349866" data-bgcolor="#6D6024"></div>-->
					</div>
				</td>
				
        	    <td width="78%" style="padding:0px;">
					<table width="100%" class="table table-bordered" style="background-color:transparent; margin-bottom:0px;"  >
						<tr style="background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.1);">
							<td colspan="6">
								<button class="btn btn-xs btn-success">Mandal</button> | 
								<button class="btn btn-xs btn-success">Town</button> |
								<button class="btn btn-xs btn-success">GVMC</button> |
								<button class="btn btn-xs btn-default">All</button>
							</td>
						</tr>
						<tr>
							<td style="padding:10px;" width="18%"><b>TOTAL MAIN </b>Committees<h4 class="m_top0">2000</h4></td>
							<td style="padding:10px;" width="10%"><span class="text-success">Started</span> Committees<br/><h4 style="display:inline">10%</h4>[100]</td>
						   <td style="padding:10px;" width="10%"><span class="text-danger">Completed</span> Committees<br/><table><tr><td  class="row-table"><h4 class="row-table m_top0">50%</h4></td><td  class="row-table"><ul class="nav navbar-nav">
                              <li><a>[1000]</a>
                                   <ul class="list-group dropdown-menu arrow_box">
										  <li class="list-group-item">
											<span class="badge">14</span>
											Cras justo odio
										  </li>
										  <li class="list-group-item">
											<span class="badge">2</span>
											Dapibus ac facilisis in
										  </li>
										  <li class="list-group-item">
											<span class="badge">1</span>
											Morbi leo risus
										  </li>
									</ul>
									
									
								</li></td></tr></table></td>
							<td style="padding:10px;" width="20%">Affliated Committees<br/><span class="text-success">Started Areas</span><br/><h4 class="m_top0">100</h4></td>
							<td style="padding:10px;" width="20%"><span class="text-success">Total Started</span><br/>Affliated Committees<br/><h4 class="m_top0"> <ul class="nav navbar-nav">
                              <li><a>100</a>
                                <ul class="dropdown-menu arrow_box list-group" style="width:1200px !important">
                                <div class="panel panel-default m_bottom0">
                                <div class="panel-heading">COMMITTEE TYPE<span class="pull-right">TOTAL</span></div></div>
                                  <li class="list-group m_bottom0"><a>TELUGU RYTHU COMMITTEE<span class="pull-right">10</span></a></li>
                                  <li class="list-group m_bottom0"><a>SC CELL COMMITTEE<span class="pull-right">10</span></a></li>
                                  <li class="list-group m_bottom0"><a>BC CELL COMMITTEE<span class="pull-right">10</span></a>
                                    <ul class="list-group dropdown-menu-border">
										  <li class="list-group-item">
											<span class="badge">14</span>
											Cras justo odio
										  </li>
										  <li class="list-group-item">
											<span class="badge">2</span>
											Dapibus ac facilisis in
										  </li>
										  <li class="list-group-item">
											<span class="badge">1</span>
											Morbi leo risus
										  </li>
									</ul>
                                  </li>
                                  <li class="list-group m_bottom0"><a>TELUGU MAHILA COMMITTEE<span class="pull-right">10</span></a></li>                                 
                                  <li><a>TELUGU YUVATHA COMMITTEE<span class="pull-right">10</span></a></li>
                                </ul>
                              </li>
                            </ul></h4></td>
							<td style="padding:10px;" width="28%">TOTAL <b>MEMBERS</b><h4 class="m_top0">2000</h4></td>
						</tr>
						<tr style="background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.1);">
							<td colspan="6">
								<button class="btn btn-xs btn-success">Village</button> | 
								<button class="btn btn-xs btn-success">Ward</button> |
								<button class="btn btn-xs btn-success">Division</button> |
								<button class="btn btn-xs btn-default">All</button>
							</td>
						</tr>
						<tr>
							<td style="padding:10px;" width="18%"><b>TOTAL MAIN </b>Committees<h4 class="m_top0">2000</h4></td>
							<td style="padding:10px;" width="10%"><span class="text-success">Started</span> Committees<br/><h4 style="display:inline">10%</h4>[100]</td>
						   <td style="padding:10px;" width="10%"><span class="text-danger">Completed</span> Committees<br/><table><tr><td  class="row-table"><h4 class="row-table m_top0">50%</h4></td><td  class="row-table">
						   <ul class="nav navbar-nav">
                              <li><a>[1000]</a>
                                   
									<ul class="list-group dropdown-menu arrow_box">
										  <li class="list-group-item">
											<span class="badge">14</span>
											Cras justo odio
										  </li>
										  <li class="list-group-item">
											<span class="badge">2</span>
											Dapibus ac facilisis in
										  </li>
										  <li class="list-group-item">
											<span class="badge">1</span>
											Morbi leo risus
										  </li>
									</ul>
									
								</li>
									</td></tr></table></td>
							<td style="padding:10px;" width="20%">Affliated Committees<br/><span class="text-success">Started Areas</span><br/><h4 class="m_top0">100</h4></td>
							<td style="padding:10px;" width="20%"><span class="text-success">Total Started</span><br/>Affliated Committees<br/><h4 class="m_top0"> <ul class="nav navbar-nav">
                              <li><a>100</a>
                                <ul class="dropdown-menu arrow_box list-group" style="width:1200px !important">
                                <div class="panel panel-default m_bottom0">
                                <div class="panel-heading">COMMITTEE TYPE<span class="pull-right">TOTAL</span></div></div>
                                  <li class="list-group m_bottom0"><a>TELUGU RYTHU COMMITTEE<span class="pull-right">10</span></a></li>
                                  <li class="list-group m_bottom0"><a>SC CELL COMMITTEE<span class="pull-right">10</span></a></li>
                                  <li class="list-group m_bottom0"><a>BC CELL COMMITTEE<span class="pull-right">10</span></a>
                                   <ul class="list-group dropdown-menu-border">
										  <li class="list-group-item">
											<span class="badge">14</span>
											Cras justo odio
										  </li>
										  <li class="list-group-item">
											<span class="badge">2</span>
											Dapibus ac facilisis in
										  </li>
										  <li class="list-group-item">
											<span class="badge">1</span>
											Morbi leo risus
										  </li>
									</ul>
                                  </li>
                                  <li class="list-group m_bottom0"><a>TELUGU MAHILA COMMITTEE<span class="pull-right">10</span></a></li>                                 
                                  <li><a>TELUGU YUVATHA COMMITTEE<span class="pull-right">10</span></a></li>
                                  <li><a>TELUGU YUVATHA COMMITTEE<span class="pull-right">10</span></a></li>
                                  <li><a>TELUGU YUVATHA COMMITTEE<span class="pull-right">10</span></a></li>
                                  <li><a>TELUGU YUVATHA COMMITTEE<span class="pull-right">10</span></a></li><li><a>TELUGU YUVATHA COMMITTEE<span class="pull-right">10</span></a></li>
                                  <li><a>TELUGU YUVATHA COMMITTEE<span class="pull-right">10</span></a></li>
                                  <li><a>TELUGU YUVATHA COMMITTEE<span class="pull-right">10</span></a></li>
                                  
                                </ul>
                              </li>
                            </ul></h4></td>
							<td style="padding:10px;" width="28%">TOTAL <b>MEMBERS</b><h4 class="m_top0">2000</h4></td>
						</tr>
					</table>
				</td>
      	    </tr>
      	  </table>
        </div>
		
        <!--<div class="row m_top20">        	 
            <div class="row">
                <div class="col-md-10 col-xs-12 col-sm-12">
                    <h4 class="text-success" style="display:inline-block">DISTRICT WISE COMMITTEES
					 <span class="btn btn-success btn-xs form-inline">
						<label class="radio"><input type="radio" name="selectstate">&nbsp;AP &nbsp;&nbsp;&nbsp;</label>
						<label class="radio"><input type="radio" name="selectstate">&nbsp;TS &nbsp;&nbsp;&nbsp;</label>
					</span>
					</h4>
                </div>
               
            </div>
			
            <div class="row">
            	<div class="col-md-12 col-xs-12 col-md-12">
                	<table class="table table-yellow-bordered table-condensed " style="width:100%; background-color:rgba(0,0,0,0.1);">
                        <thead> 
							<tr style="background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.1);">
								<th style="text-align:center">DISTRICT</th>
								<th style="text-align:center" colspan="6">TOWN/MANDAL/GVMC</th>
								<th style="text-align:center" colspan="6">VILLAGE/WARD/DIVISION</th>
							</tr>
						
							<tr style="background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.1);">
								<th >Name</th>
								<th>Total</th>
								<th>Started</th>                            
								<th>Completed</th>
								<th>Members</th>
								<th width="15%" colspan="2" style="padding:0px;">Affliated Committe
									<table class="table table-yellow-bordered" style="margin-bottom:0px; background:transparent;">
										<tr>
											<td style="padding:0px 0px 0px 2px;">
												<small style="font-size:10px;">STARTED AREAS</small>
											</td>
											<td style="padding:0px 0px 0px 6px;">
												<small style="font-size:10px;">TOTAL STARTED</small>
											</td>
										</tr>
									</table>
								</th>
								<th>Total</th>
								<th><small>Started</small></th>
								<th><small>Completed</small></th>
								<th><small>Members</small></th>
								<th width="15%" colspan="2" style="padding:0px;">Affliated Committe
									<table class="table table-yellow-bordered" style="margin-bottom:0px; background:transparent;">
										<tr>
											<td style="padding:0px 0px 0px 2px;">
												<small style="font-size:10px;">STARTED AREAS</small>
											</td>
											<td style="padding:0px 0px 0px 6px;">
												<small style="font-size:10px;">TOTAL STARTED</small>
											</td>
										</tr>
									</table>
								</th>
							</tr>
                        </thead>
                        <tr>
                            <td>Anantapur</td>
                            <td>1000</td>
                            <td>50% <span class="sparkline"></span></td>
                            <td>500</td>
                            <td>500</td>
                            <td>3000</td>
                            <td>3000</td>
                            <td>1000</td>
                            <td>60% <span class="sparkline"></span></td>
                            <td>16000</td>
                            <td>500</td>
                            <td>1000</td>
                            <td>1000</td>
                        </tr>
                        <tr>
                            <td>Chitoor</td>
                            <td>1000</td>
                            <td>50% <span class="sparkline"></span></td>
                            <td>500</td>
                            <td>500</td>
                            <td>3000</td>
                            <td>3000</td>
                            <td>1000</td>
                            <td>60% <span class="sparkline"></span></td>
                            <td>16000</td>
                            <td>500</td>
                            <td>1000</td>
                            <td>1000</td>
                        </tr>
                        <tr>
                            <td>East Godavari</td>
                            <td>1000</td>
                            <td>50% <span class="sparkline"></span></td>
                            <td>500</td>
                            <td>500</td>
                            <td>3000</td>
                            <td>3000</td>
                            <td>1000</td>
                            <td>60% <span class="sparkline"></span></td>
                            <td>16000</td>
                            <td>500</td>
                            <td>1000</td>
                            <td>1000</td>
                        </tr>
                        <tr>
                            <td>Guntur</td>
                            <td>1000</td>
                            <td>50% <span class="sparkline"></span></td>
                            <td>500</td>
                            <td>500</td>
                            <td>3000</td>
                            <td>3000</td>
                            <td>1000</td>
                            <td>60% <span class="sparkline"></span></td>
                            <td>16000</td>
                            <td>500</td>
                            <td>1000</td>
                            <td>1000</td>
                        </tr>
                        <tr>
                            <td>Nellore</td>
                            <td>1000</td>
                            <td>50% <span class="sparkline"></span></td>
                            <td>500</td>
                            <td>500</td>
                            <td>3000</td>
                            <td>3000</td>
                            <td>1000</td>
                            <td>60% <span class="sparkline"></span></td>
                            <td>16000</td>
                            <td>500</td>
                            <td>1000</td>
                            <td>1000</td>
                        </tr>
                        <tr>
                            <td>Prakasham</td>
                            <td>1000</td>
                            <td>50% <span class="sparkline"></span></td>
                            <td>500</td>
                            <td>500</td>
                            <td>3000</td>
                            <td>3000</td>
                            <td>1000</td>
                            <td>60% <span class="sparkline"></span></td>
                            <td>16000</td>
                            <td>500</td>
                            <td>1000</td>
                            <td>1000</td>
                        </tr>
                        <tr>
                            <td>Krishna</td>
                            <td>1000</td>
                            <td>50% <span class="sparkline"></span></td>
                            <td>500</td>
                            <td>500</td>
                            <td>3000</td>
                            <td>3000</td>
                            <td>1000</td>
                            <td>60% <span class="sparkline"></span></td>
                            <td>16000</td>
                            <td>500</td>
                            <td>1000</td>
                            <td>1000</td>
                        </tr>
                        <tr>
                            <td>Viajayanagarm</td>
                            <td>1000</td>
                            <td>50% <span class="sparkline"></span></td>
                            <td>500</td>
                            <td>500</td>
                            <td>3000</td>
                            <td>3000</td>
                            <td>1000</td>
                            <td>60% <span class="sparkline"></span></td>
                            <td>16000</td>
                            <td>500</td>
                            <td>1000</td>
                            <td>1000</td>
                        </tr>
                    </table>                    
                </div>
            </div>
        </div>-->
    </div>


	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/cadreCommittee/dist/js/bootstrap.min.js"></script>
	<!--Circle js file-->
	
    <script src="js/cadreCommittee/dist/js/jquery.circliful.min.js"></script>
    <!--Bootstrap Date Picker-->
    <!--<script src="bootstrap-daterangepicker/daterangepicker.js" type="text/javascript"></script>   
	<script src="bootstrap-daterangepicker/moment.min.js" type="text/javascript"></script>  --> 
    <!--Jquery Sparkline-->
    <script src="js/cadreCommittee/js/jquery.sparkline.js" type="text/javascript"></script>
    <!-- Custom JS File-->
    <script src="js/cadreCommittee/js/custom.js" type="text/javascript"></script>
    <!--Hover Menu-->
    <script src="js/cadreCommittee/js/jquery.smartmenus.min.js" type="text/javascript"></script>
    <script src="js/cadreCommittee/js/jquery.smartmenus.bootstrap.min.js" type="text/javascript"></script>
    
	<script>
	$( document ).ready(function() {
		//$('.myStat2').circliful();
		//$('.myStat3').circliful();
    });
	
	</script>
	
	<!----Bootstrap Date Range Picker Script---->
		 <script type="text/javascript">
               $(document).ready(function() {

                  /*var cb = function(start, end, label) {
                    console.log(start.toISOString(), end.toISOString(), label);
                    $('#reportrange span').html(start.format('MMMM D, YYYY') + ' - ' + end.format('MMMM D, YYYY'));
                    //alert("Callback has fired: [" + start.format('MMMM D, YYYY') + " to " + end.format('MMMM D, YYYY') + ", label = " + label + "]");
                  }

                  var optionSet1 = {
                    startDate: moment().subtract(29, 'days'),
                    endDate: moment(),
                    minDate: '01/01/2012',
                    maxDate: '12/31/2014',
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
                  });*/

               });
               </script>
			   
			   		
	<script>
	function getCommitteeDetails(state,level){
		var levelIdsArr = new Array();
		var state = state; 
		if(level == 'mandal')
		{
		   levelIdsArr.push(5);
		}else if(level == 'town')
		{
		   levelIdsArr.push(7);
		}else if(level == 'division')
		{
		   levelIdsArr.push(9);
		}else if(level == 'village')
		{
		   levelIdsArr.push(6);
		}else if(level == 'ward')
		{
		   levelIdsArr.push(8);
		}else if(level == 'mandalAll')
		{
		    levelIdsArr.push(5);
			levelIdsArr.push(7);
			levelIdsArr.push(9);
		}else if(level == 'villageAll')
		{
		    levelIdsArr.push(6);
			levelIdsArr.push(8);
		}

		var jObj = {
			levelIdsArr : levelIdsArr,
			state:state,
			task:"committeeDetails"
		}
		
		
		$.ajax({
          type:'GET',
          url: 'getDashBoardLocationWiseDetails.action',
		  data : {task:JSON.stringify(jObj)} ,
        }).done(function(result){
			var str='';
			str+='<td style="padding:10px;" width="10%"><span class="text-success">Started</span> Committees<br/><h4 style="display:inline">10%</h4>[100]</td>';
			str+='<td style="padding:10px;" width="10%"><span class="text-danger">Completed</span> Committees<br/><table><tr><td  class="row-table"><h4 class="row-table m_top0">50%</h4></td><td  class="row-table"><ul class="nav navbar-nav">';
			str+='<a>[1000]</a>';
			str+='</td></tr></table></td>';
			str+='<td style="padding:10px;" width="20%">Affliated Committees<br/><span class="text-success">Started Areas</span><br/><h4 class="m_top0">100</h4></td>';
			str+='<td style="padding:10px;" width="20%"><span class="text-success">Total Started</span><br/>Affliated Committees<br/><h4 class="m_top0"><a>100</a>';
			str+='<td style="padding:10px;" width="28%">TOTAL <b>MEMBERS</b><h4 class="m_top0">2000</h4></td>';
			if(state == "AP"){
			if(level == 'mandal' || level == 'town' || level == 'division' || level == 'mandalAll')
			{
				
			}
			
			else if(level == 'village' || level == 'ward' || level == 'villageAll')
			{
				
			}
			}
		});
	}
	function getCommitteeCountByState(state){
	
		var state = state; 
		var jObj = {
			state:state,
			task:"committeeDetails"
		}
				
		$.ajax({
          type:'GET',
          url: 'getTotalCommitteeCntsByStateAction.action',
		  data : {task:JSON.stringify(jObj)} ,
        }).done(function(result){
				var str='';
				str+='<div class="col-md-5 col-md-offset-1 col-xs-3"><span style="font-size:2em;">'+result.totalCommittees+'</span></div>';
				str+='<div style="" class="col-md-6 col-xs-3 text-left"><small>TOTAL MAIN COMMITTEES</small></div>';
				if(state == "AP"){					
					$("#totalAPCount").html(str);
				}
				if(state == "TS"){
					$("#totalTSCount").html(str);
				}
		
		});
	}
	
	getCommitteeCountByState("AP");
	getCommitteeCountByState("TS");
	//getCommitteeDetails("AP","mandalAll");
	//getCommitteeDetails("AP","villageAll");	
	//getCommitteeDetails("TS","mandalAll");	
	//getCommitteeDetails("TS","villageAll");

	function buildData(result,state){
	var str='';
	str+='<td style="padding:10px;" width="10%"><span class="text-success">Started</span> Committees<br/><h4 style="display:inline">10%</h4>[100]</td>';
	str+='<td style="padding:10px;" width="10%"><span class="text-danger">Completed</span> Committees<br/><table><tr><td  class="row-table"><h4 class="row-table m_top0">50%</h4></td><td  class="row-table"><ul class="nav navbar-nav">';
    str+='<a>[1000]</a>';
    str+='</td></tr></table></td>';
	str+='<td style="padding:10px;" width="20%">Affliated Committees<br/><span class="text-success">Started Areas</span><br/><h4 class="m_top0">100</h4></td>';
	str+='<td style="padding:10px;" width="20%"><span class="text-success">Total Started</span><br/>Affliated Committees<br/><h4 class="m_top0"><a>100</a>';
    str+='<td style="padding:10px;" width="28%">TOTAL <b>MEMBERS</b><h4 class="m_top0">2000</h4></td>';
	}
	</script>
			   
			   
</body>
</html>
