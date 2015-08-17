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
<title>Training Camp Main Dashboard</title>
	<link href="js/cadreCommittee/dist/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
	<link href="css/Training/css/custom.css" rel="stylesheet" type="text/css"/>
	<link href="js/cadreCommittee/bootstrapDaterangepicker/daterangepicker-bs3.css" rel="stylesheet" type="text/css" />
	<link href="js/cadreCommittee/dist/css/jquery.circliful.css" rel="stylesheet" type="text/css" />
	<link href="css/Training/scroll/jquery.mCustomScrollbar.css" rel="stylesheet" type="text/css"/>
	<link href="http://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
	<!-- <link href="dist/css/bootstrap.css" rel="stylesheet" type="text/css">
	<link href="dist/css/custom.css" rel="stylesheet" type="text/css">
	<link href="dist/DateRange/daterangepicker.css" rel="stylesheet" type="text/css">
	<link href="dist/css/jquery.circliful.css" rel="stylesheet" type="text/css">
	<link href="dist/scroll/jquery.mCustomScrollbar.css" rel="stylesheet" type="text/css">
	<link href="http://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css"> -->
</head>
<body>
<header>
	<img src="dist/img/header.jpg" width="100%" alt="">
</header>
<main>
	<div class="container">
    	<div class="row">
        	<div class="col-md-12">
           	  <div class="panel panel-default">
                	<div class="panel-heading">
                    	<h4 class="panel-title"></h4>
                    </div>
                    <div class="panel-body">
                    	<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
                          <div class="panel panel-default">
                            <div class="panel-heading" role="tab" id="headingOne">
                              <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                              	<h4 class="panel-title">
                                  SVV_Batch_2015_3
                                  <span class="pull-right">
                                  	<i class="glyphicon glyphicon-chevron-down"></i>
                                  </span>
                                </h4>
                              </a>
                            </div>
                            <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
                              <div class="panel-body">
                                <table class="table table-bordered m_0">
                                	<thead>
										<th></th>
                                    	<th>Name</th>
                                        <th>Mobile</th>
                                        <th>Constituency</th>
                                        <th>Achievements</th>
                                        <th>Goals</th>
                                        <th>Leadership <br/>Level</th>
                                        <th>Communication<br/> Skills</th>
                                        <th>Leadership Skills</th>
                                        <th>Health</th>
                                        <th></th>
                                    </thead>
                                    <tr>
										<td><img src="dist/img/profile-img.png" style="height:40px" class="img-reponsive"></td>
                                    	<td>Mahendar</td>
                                        <td>9986448445</td>
                                        <td>Makthal</td>
                                        <td><i class="glyphicon glyphicon-ok text-success text-center"></i></td>
                                        <td><i class="glyphicon glyphicon-ok text-success text-center"></i></td>
                                        <td><i class="glyphicon glyphicon-ok text-success text-center"></i></td>
                                        <td><i class="glyphicon glyphicon-ok text-success text-center"></i></td>
                                        <td><i class="glyphicon glyphicon-ok text-success text-center"></i></td>
                                        <td><i class="glyphicon glyphicon-remove text-danger text-center"></i></td>
                                        <td><button  type="button" class="btn btn-success btn-xs" data-toggle="modal" data-target="#myModal">UPDATE</button></td>
                                    </tr>
                                    <tr>
										<td><img src="dist/img/profile-img.png" style="height:40px" class="img-reponsive"></td>
                                    	<td>Mahendar</td>
                                        <td>9986448445</td>
                                        <td>Makthal</td>
                                        <td><i class="glyphicon glyphicon-remove text-danger text-center"></i></td>
                                        <td><i class="glyphicon glyphicon-ok text-success text-center"></i></td>
                                        <td><i class="glyphicon glyphicon-remove text-danger text-center"></i></td>
                                        <td><i class="glyphicon glyphicon-ok text-success text-center"></i></td>
                                        <td><i class="glyphicon glyphicon-ok text-success text-center"></i></td>
                                        <td><i class="glyphicon glyphicon-ok text-success text-center"></i></td>
                                        <td><button  type="button" class="btn btn-success btn-xs" data-toggle="modal" data-target="#myModal">UPDATE</button></td>
                                    </tr>
                                    <tr>
										<td><img src="dist/img/profile-img.png" style="height:40px" class="img-reponsive"></td>
                                    	<td>Mahendar</td>
                                        <td>9986448445</td>
                                        <td>Makthal</td>
                                        <td><i class="glyphicon glyphicon-ok text-success text-center"></i></td>
                                        <td><i class="glyphicon glyphicon-ok text-success text-center"></i></td>
                                        <td><i class="glyphicon glyphicon-ok text-success text-center"></i></td>
                                        <td><i class="glyphicon glyphicon-ok text-success text-center"></i></td>
                                        <td><i class="glyphicon glyphicon-ok text-success text-center"></i></td>
                                        <td><i class="glyphicon glyphicon-remove text-danger text-center"></i></td>
                                        <td><button  type="button" class="btn btn-success btn-xs" data-toggle="modal" data-target="#myModal">UPDATE</button></td>
                                    </tr>
                                    <tr>
										<td><img src="dist/img/profile-img.png" style="height:40px" class="img-reponsive"></td>
                                    	<td>Mahendar</td>
                                        <td>9986448445</td>
                                        <td>Makthal</td>
                                        <td><i class="glyphicon glyphicon-remove text-danger text-center"></i></td>
                                        <td><i class="glyphicon glyphicon-ok text-success text-center"></i></td>
                                        <td><i class="glyphicon glyphicon-remove text-danger text-center"></i></td>
                                        <td><i class="glyphicon glyphicon-ok text-success text-center"></i></td>
                                        <td><i class="glyphicon glyphicon-ok text-success text-center"></i></td>
                                        <td><i class="glyphicon glyphicon-ok text-success text-center"></i></td>
                                        <td><button  type="button" class="btn btn-success btn-xs" data-toggle="modal" data-target="#myModal">UPDATE</button></td>
                                    </tr>
                                    <tr>
										<td><img src="dist/img/profile-img.png" style="height:40px" class="img-reponsive"></td>
                                    	<td>Mahendar</td>
                                        <td>9986448445</td>
                                        <td>Makthal</td>
                                        <td><i class="glyphicon glyphicon-ok text-success text-center"></i></td>
                                        <td><i class="glyphicon glyphicon-ok text-success text-center"></i></td>
                                        <td><i class="glyphicon glyphicon-ok text-success text-center"></i></td>
                                        <td><i class="glyphicon glyphicon-ok text-success text-center"></i></td>
                                        <td><i class="glyphicon glyphicon-ok text-success text-center"></i></td>
                                        <td><i class="glyphicon glyphicon-remove text-danger text-center"></i></td>
                                        <td><button  type="button" class="btn btn-success btn-xs" data-toggle="modal" data-target="#myModal">UPDATE</button></td>
                                    </tr>
                                    <tr>
										<td><img src="dist/img/profile-img.png" style="height:40px" class="img-reponsive"></td>
                                    	<td>Mahendar</td>
                                        <td>9986448445</td>
                                        <td>Makthal</td>
                                        <td><i class="glyphicon glyphicon-remove text-danger text-center"></i></td>
                                        <td><i class="glyphicon glyphicon-ok text-success text-center"></i></td>
                                        <td><i class="glyphicon glyphicon-remove text-danger text-center"></i></td>
                                        <td><i class="glyphicon glyphicon-ok text-success text-center"></i></td>
                                        <td><i class="glyphicon glyphicon-ok text-success text-center"></i></td>
                                        <td><i class="glyphicon glyphicon-ok text-success text-center"></i></td>
                                        <td><button  type="button" class="btn btn-success btn-xs" data-toggle="modal" data-target="#myModal">UPDATE</button></td>
                                    </tr>
                                </table>
                              </div>
                            </div>
                          </div>
                          <div class="panel panel-default">
                            <div class="panel-heading" role="tab" id="headingTwo">
                                <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                              	<h4 class="panel-title">
                                  SVV_Batch_2015_2
                                  <span class="pull-right">
                                  	<i class="glyphicon glyphicon-chevron-down"></i>
                                  </span>
                                </h4>
                                </a>
                            </div>
                            <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
                              <div class="panel-body">
                                <table class="table table-bordered m_0">
                                	<thead>
										<th></th>
										<th>Name</th>
                                        <th>Mobile</th>
                                        <th>Constituency</th>
                                        <th>Achievements</th>
                                        <th>Goals</th>
                                        <th>Leadership <br/>Level</th>
                                        <th>Communication<br/> Skills</th>
                                        <th>Leadership Skills</th>
                                        <th>Health</th>
                                        <th></th>
                                    </thead>
                                    <tr>
										<td><img src="dist/img/profile-img.png" style="height:40px" class="img-reponsive"></td>
                                    	<td>Mahendar</td>
                                        <td>9986448445</td>
                                        <td>Makthal</td>
                                        <td><i class="glyphicon glyphicon-ok text-success text-center"></i></td>
                                        <td><i class="glyphicon glyphicon-ok text-success text-center"></i></td>
                                        <td><i class="glyphicon glyphicon-ok text-success text-center"></i></td>
                                        <td><i class="glyphicon glyphicon-ok text-success text-center"></i></td>
                                        <td><i class="glyphicon glyphicon-ok text-success text-center"></i></td>
                                        <td><i class="glyphicon glyphicon-remove text-danger text-center"></i></td>
                                        <td><button  type="button" class="btn btn-success btn-xs" data-toggle="modal" data-target="#myModal">UPDATE</button></td>
                                    </tr>
                                    <tr>
										<td><img src="dist/img/profile-img.png" style="height:40px" class="img-reponsive"></td>
                                    	<td>Mahendar</td>
                                        <td>9986448445</td>
                                        <td>Makthal</td>
                                        <td><i class="glyphicon glyphicon-remove text-danger text-center"></i></td>
                                        <td><i class="glyphicon glyphicon-ok text-success text-center"></i></td>
                                        <td><i class="glyphicon glyphicon-remove text-danger text-center"></i></td>
                                        <td><i class="glyphicon glyphicon-ok text-success text-center"></i></td>
                                        <td><i class="glyphicon glyphicon-ok text-success text-center"></i></td>
                                        <td><i class="glyphicon glyphicon-ok text-success text-center"></i></td>
                                        <td><button  type="button" class="btn btn-success btn-xs" data-toggle="modal" data-target="#myModal">UPDATE</button></td>
                                    </tr>
                                    <tr>
										<td><img src="dist/img/profile-img.png" style="height:40px" class="img-reponsive"></td>
                                    	<td>Mahendar</td>
                                        <td>9986448445</td>
                                        <td>Makthal</td>
                                        <td><i class="glyphicon glyphicon-ok text-success text-center"></i></td>
                                        <td><i class="glyphicon glyphicon-ok text-success text-center"></i></td>
                                        <td><i class="glyphicon glyphicon-ok text-success text-center"></i></td>
                                        <td><i class="glyphicon glyphicon-ok text-success text-center"></i></td>
                                        <td><i class="glyphicon glyphicon-ok text-success text-center"></i></td>
                                        <td><i class="glyphicon glyphicon-remove text-danger text-center"></i></td>
                                        <td><button  type="button" class="btn btn-success btn-xs" data-toggle="modal" data-target="#myModal">UPDATE</button></td>
                                    </tr>
                                    <tr>
										<td><img src="dist/img/profile-img.png" style="height:40px" class="img-reponsive"></td>
                                    	<td>Mahendar</td>
                                        <td>9986448445</td>
                                        <td>Makthal</td>
                                        <td><i class="glyphicon glyphicon-remove text-danger text-center"></i></td>
                                        <td><i class="glyphicon glyphicon-ok text-success text-center"></i></td>
                                        <td><i class="glyphicon glyphicon-remove text-danger text-center"></i></td>
                                        <td><i class="glyphicon glyphicon-ok text-success text-center"></i></td>
                                        <td><i class="glyphicon glyphicon-ok text-success text-center"></i></td>
                                        <td><i class="glyphicon glyphicon-ok text-success text-center"></i></td>
                                        <td><button  type="button" class="btn btn-success btn-xs" data-toggle="modal" data-target="#myModal">UPDATE</button></td>
                                    </tr>
                                    <tr>
										<td><img src="dist/img/profile-img.png" style="height:40px" class="img-reponsive"></td>
                                    	<td>Mahendar</td>
                                        <td>9986448445</td>
                                        <td>Makthal</td>
                                        <td><i class="glyphicon glyphicon-ok text-success text-center"></i></td>
                                        <td><i class="glyphicon glyphicon-ok text-success text-center"></i></td>
                                        <td><i class="glyphicon glyphicon-ok text-success text-center"></i></td>
                                        <td><i class="glyphicon glyphicon-ok text-success text-center"></i></td>
                                        <td><i class="glyphicon glyphicon-ok text-success text-center"></i></td>
                                        <td><i class="glyphicon glyphicon-remove text-danger text-center"></i></td>
                                        <td><button  type="button" class="btn btn-success btn-xs" data-toggle="modal" data-target="#myModal">UPDATE</button></td>
                                    </tr>
                                    <tr>
										<td><img src="dist/img/profile-img.png" style="height:40px" class="img-reponsive"></td>
                                    	<td>Mahendar</td>
                                        <td>9986448445</td>
                                        <td>Makthal</td>
                                        <td><i class="glyphicon glyphicon-remove text-danger text-center"></i></td>
                                        <td><i class="glyphicon glyphicon-ok text-success text-center"></i></td>
                                        <td><i class="glyphicon glyphicon-remove text-danger text-center"></i></td>
                                        <td><i class="glyphicon glyphicon-ok text-success text-center"></i></td>
                                        <td><i class="glyphicon glyphicon-ok text-success text-center"></i></td>
                                        <td><i class="glyphicon glyphicon-ok text-success text-center"></i></td>
                                        <td><button  type="button" class="btn btn-success btn-xs" data-toggle="modal" data-target="#myModal">UPDATE</button></td>
                                    </tr>
                                </table>

                              </div>
                            </div>
                          </div>
                          <div class="panel panel-default">
                            <div class="panel-heading" role="tab" id="headingThree">
                                <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                              	<h4 class="panel-title">
                                  SVV_Batch_2015_1
                                  <span class="pull-right">
                                  	<i class="glyphicon glyphicon-chevron-down"></i>
                                  </span>
                                </h4>
                                </a>
                            </div>
                            <div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
                              <div class="panel-body">
                                <table class="table table-bordered m_0">
                                	<thead>
										<th></th>
										<th>Name</th>
                                        <th>Mobile</th>
                                        <th>Constituency</th>
                                        <th>Achievements</th>
                                        <th>Goals</th>
                                        <th>Leadership <br/>Level</th>
                                        <th>Communication<br/> Skills</th>
                                        <th>Leadership Skills</th>
                                        <th>Health</th>
                                        <th></th>
                                    </thead>
                                    <tr>
										<td><img src="dist/img/profile-img.png" style="height:40px" class="img-reponsive"></td>
                                    	<td>Mahendar</td>
                                        <td>9986448445</td>
                                        <td>Makthal</td>
                                        <td><i class="glyphicon glyphicon-ok text-success text-center"></i></td>
                                        <td><i class="glyphicon glyphicon-ok text-success text-center"></i></td>
                                        <td><i class="glyphicon glyphicon-ok text-success text-center"></i></td>
                                        <td><i class="glyphicon glyphicon-ok text-success text-center"></i></td>
                                        <td><i class="glyphicon glyphicon-ok text-success text-center"></i></td>
                                        <td><i class="glyphicon glyphicon-remove text-danger text-center"></i></td>
                                        <td><button  type="button" class="btn btn-success btn-xs" data-toggle="modal" data-target="#myModal">UPDATE</button></td>
                                    </tr>
                                    <tr>
										<td><img src="dist/img/profile-img.png" style="height:40px" class="img-reponsive"></td>
                                    	<td>Mahendar</td>
                                        <td>9986448445</td>
                                        <td>Makthal</td>
                                        <td><i class="glyphicon glyphicon-remove text-danger text-center"></i></td>
                                        <td><i class="glyphicon glyphicon-ok text-success text-center"></i></td>
                                        <td><i class="glyphicon glyphicon-remove text-danger text-center"></i></td>
                                        <td><i class="glyphicon glyphicon-ok text-success text-center"></i></td>
                                        <td><i class="glyphicon glyphicon-ok text-success text-center"></i></td>
                                        <td><i class="glyphicon glyphicon-ok text-success text-center"></i></td>
                                        <td><button  type="button" class="btn btn-success btn-xs" data-toggle="modal" data-target="#myModal">UPDATE</button></td>
                                    </tr>
                                    <tr>
										<td><img src="dist/img/profile-img.png" style="height:40px" class="img-reponsive"></td>
                                    	<td>Mahendar</td>
                                        <td>9986448445</td>
                                        <td>Makthal</td>
                                        <td><i class="glyphicon glyphicon-ok text-success text-center"></i></td>
                                        <td><i class="glyphicon glyphicon-ok text-success text-center"></i></td>
                                        <td><i class="glyphicon glyphicon-ok text-success text-center"></i></td>
                                        <td><i class="glyphicon glyphicon-ok text-success text-center"></i></td>
                                        <td><i class="glyphicon glyphicon-ok text-success text-center"></i></td>
                                        <td><i class="glyphicon glyphicon-remove text-danger text-center"></i></td>
                                        <td><button  type="button" class="btn btn-success btn-xs" data-toggle="modal" data-target="#myModal">UPDATE</button></td>
                                    </tr>
                                    <tr>
										<td><img src="dist/img/profile-img.png" style="height:40px" class="img-reponsive"></td>
                                    	<td>Mahendar</td>
                                        <td>9986448445</td>
                                        <td>Makthal</td>
                                        <td><i class="glyphicon glyphicon-remove text-danger text-center"></i></td>
                                        <td><i class="glyphicon glyphicon-ok text-success text-center"></i></td>
                                        <td><i class="glyphicon glyphicon-remove text-danger text-center"></i></td>
                                        <td><i class="glyphicon glyphicon-ok text-success text-center"></i></td>
                                        <td><i class="glyphicon glyphicon-ok text-success text-center"></i></td>
                                        <td><i class="glyphicon glyphicon-ok text-success text-center"></i></td>
                                        <td><button  type="button" class="btn btn-success btn-xs" data-toggle="modal" data-target="#myModal">UPDATE</button></td>
                                    </tr>
                                    <tr>
										<td><img src="dist/img/profile-img.png" style="height:40px" class="img-reponsive"></td>
                                    	<td>Mahendar</td>
                                        <td>9986448445</td>
                                        <td>Makthal</td>
                                        <td><i class="glyphicon glyphicon-ok text-success text-center"></i></td>
                                        <td><i class="glyphicon glyphicon-ok text-success text-center"></i></td>
                                        <td><i class="glyphicon glyphicon-ok text-success text-center"></i></td>
                                        <td><i class="glyphicon glyphicon-ok text-success text-center"></i></td>
                                        <td><i class="glyphicon glyphicon-ok text-success text-center"></i></td>
                                        <td><i class="glyphicon glyphicon-remove text-danger text-center"></i></td>
                                        <td><button  type="button" class="btn btn-success btn-xs" data-toggle="modal" data-target="#myModal">UPDATE</button></td>
                                    </tr>
                                    <tr>
										<td><img src="dist/img/profile-img.png" style="height:40px" class="img-reponsive"></td>
                                    	<td>Mahendar</td>
                                        <td>9986448445</td>
                                        <td>Makthal</td>
                                        <td><i class="glyphicon glyphicon-remove text-danger text-center"></i></td>
                                        <td><i class="glyphicon glyphicon-ok text-success text-center"></i></td>
                                        <td><i class="glyphicon glyphicon-remove text-danger text-center"></i></td>
                                        <td><i class="glyphicon glyphicon-ok text-success text-center"></i></td>
                                        <td><i class="glyphicon glyphicon-ok text-success text-center"></i></td>
                                        <td><i class="glyphicon glyphicon-ok text-success text-center"></i></td>
                                        <td><button  type="button" class="btn btn-success btn-xs" data-toggle="modal" data-target="#myModal">UPDATE</button></td>
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
</main>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">UPDATE USER</h4>
      </div>
      <div class="modal-body">
        <div class="row">
        	<div class="col-md-11">
            	<label>Achievments</label>
                <input class="form-control" type="text">
            </div>
            <div class="col-md-1">
            	<i class="glyphicon glyphicon-plus m_top20 add-plus"></i>
            </div>
        </div>
        <div class="row">
        	<div class="col-md-6">
            	<label>Goals</label>
                <input class="form-control" type="text">
            </div>
            <div class="col-md-5">
            	<label>Date & Time</label>
                <div class="input-group">
                	<span class="input-group-addon">
                    	<i class="glyphicon glyphicon-calendar"></i>
                    </span>
                    <input class="form-control" type="text">
                </div>
            </div>
            <div class="col-md-1">
            	<i class="glyphicon glyphicon-plus m_top20 add-plus"></i>
            </div>
        </div>
        <div class="row">
        	<div class="col-md-12">
            	<label>Leadership Level</label>
                <select class="form-control">
                	<option>District</option>
                    <option>State</option>
                    <option>Constituency</option>
                </select>
            </div>
        </div>
        <div class="row">
        	<div class="col-md-12">
            	<label>Communication Skills</label>
                <select class="form-control">
                	<option>Average</option>
                    <option>Poor</option>
                    <option>Good</option>
                    <option>Very Good</option>
                    <option>Excellent</option>
                </select>
            </div>
        </div>
        <div class="row">
        	<div class="col-md-12">
            	<label>Leadership Skills</label>
                <select class="form-control">
                	<option>Average</option>
                    <option>Poor</option>
                    <option>Good</option>
                    <option>Very Good</option>
                    <option>Excellent</option>
                </select>
            </div>
        </div>
        <div class="row">
        	<div class="col-md-12">
            	<label>Health</label>
                <select class="form-control">
                	<option></option>
                </select>
            </div>
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save</button>
      </div>
    </div>
  </div>
</div>
<footer>
		<img src="dist/img/footer.jpg" width="100%">
</footer>
	<script src="js/jquery-1.11.2.min.js" type="text/javascript"></script>
	<script src="js/cadreCommittee/dist/js/bootstrap.min.js"></script>
	<script src="js/cadreCommittee/bootstrapDaterangepicker/moment.min.js" type="text/javascript"></script>
	<script src="js/cadreCommittee/bootstrapDaterangepicker/daterangepicker.js" type="text/javascript"></script>
	<script src="css/Training/scroll/jquery.mCustomScrollbar.js" type="text/javascript"></script>
	<script src="css/Training/scroll/jquery.mousewheel.js" type="text/javascript"></script>
	<script src="js/highcharts/js/highcharts.js" type="text/javascript"></script>
	<!-- <script src="dist/js/jquery-1.11.2.min.js" type="text/javascript"></script>
	<script src="dist/js/bootstrap.js" type="text/javascript"></script>
	<script src="dist/DateRange/moment.js" type="text/javascript"></script>	
	<script src="dist/DateRange/daterangepicker.js" type="text/javascript"></script>
	<script src="dist/scroll/jquery.mCustomScrollbar.js" type="text/javascript"></script>
	<script src="dist/scroll/jquery.mousewheel.js" type="text/javascript"></script>
	<script src="dist/HighCharts/highcharts.js" type="text/javascript"></script> -->

</body>
</html>