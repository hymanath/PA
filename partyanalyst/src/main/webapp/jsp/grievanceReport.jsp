<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta charset="utf-8">
<title>Location wise Grevince Reports</title>
<link href="alertDepartment/css/responsive.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Roboto:100,100i,300,300i,400,400i,500,500i,700,700i,900,900i" rel="stylesheet">
<link rel="stylesheet" href="dist/css/font-awesome.css">
<link href="alertDepartment/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.css" type="text/css" rel="stylesheet"/>
<!-- Include Date Range Picker -->
<link href="dist/DateRange/daterangepicker.css" type="text/css" rel="stylesheet"/>
<link rel="stylesheet" href="css/grievanceReport.css" type="text/css"/>
<style>
</style>
</head>
<body>
<nav>
        <div class="row nav-gov-dark">
            
            <div class="container">
                <div class="logo-gov" href="#"> <img src="img/logo.png" class="img-responsive" alt="logo"> </div>
            </div>
        </div>
        <div class=" bg-gov-dark">
        
            <div class="container">
                <div id="menu1" class="col-md-7">
                    <div class="col-md-6">
                        <ul class="nav navbar-nav">
                            <li> <a href="#">MONTH</a> </li>
                            <li> <a href="#">WEEK</a> </li>
                            <li> <a href="#">DAY</a> </li>
                        </ul>
                    </div>
                    <div style="padding-top: 8px" class="col-md-6">
                        <div id="reportrange" class="pull-right" style="background: #fff; cursor: pointer; padding: 5px 10px; border: 1px solid #ccc; width: 100%"> 
                            <i class="glyphicon glyphicon-calendar fa fa-calendar"></i>&nbsp; <span></span> <b class="caret"></b> </div>
                    </div>
                </div>
                 
				<div class="col-md-2">
                    <div style="padding-top: 8px" class=" dropdown">
                        <select class="selectpicker">
                        <option>Select Department Name</option>
                        <option>Ketchup</option>
                        <option>Relish</option>
                        </select>
                    </div>
                </div>
                <div class="col-md-3">
                    <div style="padding-top: 8px" class=" dropdown pull-right">
                        <select class="selectpicker">
                        <option>Select Department Name</option>
                        <option>Ketchup</option>
                        <option>Relish</option>
                        </select>
                    </div>
                </div>
                
            </div>
        </div>
    </nav>

    <!-- /.container-fluid -->

    <div class="container-fluid bg-gov-dark">
        <div class="row">
            <div class="col-md-2">
                <div style="padding-top: 80px">
                    <h4 class="text-center issue-title">TYPE OF ISSUES</h4>
                    <h3 class=" text-center issue-title">35</h3>
                    <h4 class="text-center issue-title">MAX ISSUES IN</h4>
                    <h4 class="text-center issue-title"> REVENUE DEPT</h4>
                    <h3 class=" text-center issue-title">35</h3>
                    <h4 class="text-center issue-title">AVERAGE ISSUE <br> PENDING DAYS</h4>
                    <h3 class=" text-center issue-title">6 DAYS</h3>
                </div>
            </div>
            <div class="col-md-8">
                <div>
                    <h1 class="text-center">
                        <div id="barGraph" style="min-width:10px; height: 400px; margin: 0 auto display:none"> </div>
                    </h1>
                </div>
            </div>
            <div class="col-md-2">
                <div style="padding-top: 80px">
                    <h4 class="text-center issue-title total">TOTAL</h4>
                    <h1 style="font-size: 6em ;" class=" text-center issue-title">250</h1>
                </div>
            </div>
        </div>
    </div>

    <section class="container">
        <div  class="row">
            <div>
                <h3> Location wise Grevince Report </h3>
                <h5> (1 April 2017 to 25 April 2017)</h5>
            </div>
            <div class="table-responsive" id="grivenaceTableId">
               
            </div>
        </div>
    </section>

  
  <!-- Modal -->
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog modal-lg">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Modal Header</h4>
        </div>
        <div class="modal-body">
                      <div class="table-responsive">
                <table class=" table">
                    <col span="1" style="background-color:#ebf4fb">
                    <col span="8" style="background-color:#ebecd5">
                    <thead>
                        <tr>
                            <th>District</th>
                            <th>Total</th>
                            <th>Action In Progress</th>
                            <th>Action Not Required</th>
                            <th>Closed</th>
                            <th>Completed</th>
                            <th>Notified</th>
                            <th>Proposal</th>
                            <th>Rejoinder</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>20</td>
                            <td>20</td>
                            <td>20</td>
                            <td>20</td>
                            <td>20</td>
                            <td>20</td>
                            <td>20</td>
                            <td>20</td>
                            <td>20</td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <div class="table-responsive">
                <table id="table2" class="table  table-bordered" cellspacing="0" width="100%">
                    <thead>
                        <tr>
                            <th>District</th>
                            <th>Total</th>
                            <th>Action In Progress</th>
                            <th>Action Not Required</th>
                            <th>Closed</th>
                            <th>Completed</th>
                            <th>Notified</th>
                            <th>Proposal</th>
                            <th>Rejoinder</th>
                        </tr>
                    </thead>
                    <tfoot>
                        <tr>
                            <th>Total</th>
                            <th> 23 </th>
                            <th>32</th>
                            <th>14</th>
                            <th>05</th>
                            <th>33</th>
                            <th>10</th>
                            <th>11</th>
                            <th>25</th>
                        </tr>
                    </tfoot>
                    <tbody>
                        <tr>
                            <td><i class="glyphicon glyphicon-plus-sign"></i>East Godavari</td>
                            <td>11</td>
                            <td>23</td>
                            <td>61</td>
                            <td>25</td>
                            <td>8</td>
                            <td>32</td>
                            <td>20</td>
                            <td>21</td>
                        </tr>
                        <tr>
                            <td><i class="glyphicon glyphicon-plus-sign"></i>West Godavari</td>
                            <td>11</td>
                            <td>23</td>
                            <td>61</td>
                            <td>25</td>
                            <td>8</td>
                            <td>32</td>
                            <td>20</td>
                            <td>21</td>
                        </tr>
                        <tr>
                            <td><i class="glyphicon glyphicon-plus-sign"></i>Krishna</td>
                            <td>11</td>
                            <td>23</td>
                            <td>61</td>
                            <td>25</td>
                            <td>8</td>
                            <td>32</td>
                            <td>20</td>
                            <td>21</td>
                        </tr>
                        <tr>
                            <td><i class="glyphicon glyphicon-plus-sign"></i>Guntur</td>
                            <td>11</td>
                            <td>23</td>
                            <td>61</td>
                            <td>25</td>
                            <td>8</td>
                            <td>32</td>
                            <td>20</td>
                            <td>21</td>
                        </tr>
                        <tr>
                            <td><i class="glyphicon glyphicon-plus-sign"></i>Prakasam</td>
                            <td>11</td>
                            <td>23</td>
                            <td>61</td>
                            <td>25</td>
                            <td>8</td>
                            <td>32</td>
                            <td>20</td>
                            <td>21</td>
                        </tr>
                        <tr>
                            <td><i class="glyphicon glyphicon-plus-sign"></i>Nellore</td>
                            <td>11</td>
                            <td>23</td>
                            <td>61</td>
                            <td>25</td>
                            <td>8</td>
                            <td>32</td>
                            <td>20</td>
                            <td>21</td>
                        </tr>
                        <tr>
                            <td><i class="glyphicon glyphicon-plus-sign"></i>Srikakulam</td>
                            <td>11</td>
                            <td>23</td>
                            <td>61</td>
                            <td>25</td>
                            <td>8</td>
                            <td>32</td>
                            <td>20</td>
                            <td>21</td>
                        </tr>
                        <tr>
                            <td><i class="glyphicon glyphicon-plus-sign"></i>Vizianagaram</td>
                            <td>11</td>
                            <td>23</td>
                            <td>61</td>
                            <td>25</td>
                            <td>8</td>
                            <td>32</td>
                            <td>20</td>
                            <td>21</td>
                            <tr>
                                <td><i class="glyphicon glyphicon-plus-sign"></i>Visakhapatnam</td>
                                <td>11</td>
                                <td>23</td>
                                <td>61</td>
                                <td>25</td>
                                <td>8</td>
                                <td>32</td>
                                <td>20</td>
                                <td>21</td>
                            </tr>
                            <tr>
                                <td><i class="glyphicon glyphicon-plus-sign"></i>Kurnool</td>
                                <td>11</td>
                                <td>23</td>
                                <td>61</td>
                                <td>25</td>
                                <td>8</td>
                                <td>32</td>
                                <td>20</td>
                                <td>21</td>
                            </tr>
                            <tr>
                                <td><i class="glyphicon glyphicon-plus-sign"></i>Chittoor</td>
                                <td>11</td>
                                <td>23</td>
                                <td>61</td>
                                <td>25</td>
                                <td>8</td>
                                <td>32</td>
                                <td>20</td>
                                <td>21</td>
                            </tr>
                            <tr>
                                <td><i class="glyphicon glyphicon-plus-sign"></i>Kadapa</td>
                                <td>11</td>
                                <td>23</td>
                                <td>61</td>
                                <td>25</td>
                                <td>8</td>
                                <td>32</td>
                                <td>20</td>
                                <td>21</td>
                            </tr>
                            <tr>
                                <td><i class="glyphicon glyphicon-plus-sign"></i>Anantapur</td>
                                <td>11</td>
                                <td>23</td>
                                <td>61</td>
                                <td>25</td>
                                <td>8</td>
                                <td>32</td>
                                <td>20</td>
                                <td>21</td>
                            </tr>
                            <tr>
                                <td><i class="glyphicon glyphicon-plus-sign"></i>Rayalaseema</td>
                                <td>11</td>
                                <td>23</td>
                                <td>61</td>
                                <td>25</td>
                                <td>8</td>
                                <td>32</td>
                                <td>20</td>
                                <td>21</td>
                            </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>
    </div>
  </div>
</div>

    
    
    
      <!-- Modal -->
  <div class="modal fade" id="myModal2" role="dialog">
    <div class="modal-dialog modal-lg">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Modal Header</h4>
        </div>
        <div class="modal-body">
                     <div class="table-responsive">
                <table class="table table-inr-x" style="border-collapse:collapse;">
                    <thead>
                        <tr>
                            <th>Location Name</th>
                            <th>Total</th>
                            <th>Action in progress</th>
                            <th>Action not required</th>
                            <th>Closed</th>
                            <th>Notified</th>
                            <th>Proposal</th>
                            <th>Rejoiner</th>
                            <th>Wrongly mapped destination</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr data-toggle="collapse" data-target="#demo1" class="accordion-toggle">
                            <td><button class="btn btn-default btn-xs"><span class="glyphicon glyphicon-plus-sign"></span></button> Mandal
                            </td>
                            <td><a href="#">25</a></td>
                            <td>12</td>
                            <td>32</td>
                            <td>20</td>
                            <td>5</td>
                            <td>9</td>
                            <td>2</td>
                            <td>13</td>
                        </tr>
                        <tr>
                            <td colspan="12" class="hiddenRow">
                                <div class="accordian-body collapse" id="demo1">
                                    <table class="table table-inr">
                                        <thead>
                                            <tr>
                                                <th>Location Name</th>
                                                <th>Total</th>
                                                <th>Action in progress</th>
                                                <th>Action not required</th>
                                                <th>Closed</th>
                                                <th>Notified</th>
                                                <th>Proposal</th>
                                                <th>Rejoiner</th>
                                                <th>Wrongly mapped destination</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td>Location Name</td>
                                                <td>25</td>
                                                <td>12</td>
                                                <td>32</td>
                                                <td>20</td>
                                                <td>5</td>
                                                <td>9</td>
                                                <td>2</td>
                                                <td>13</td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </td>
                        </tr>
                        <tr data-toggle="collapse" data-target="#demo2" class="accordion-toggle">
                            <td><button class="btn btn-default btn-xs"><span class="glyphicon glyphicon-plus-sign"></span></button> Mandal
                            </td>
                            <td><a href="#">25</a></td>
                            <td>12</td>
                            <td>32</td>
                            <td>20</td>
                            <td>5</td>
                            <td>9</td>
                            <td>2</td>
                            <td>13</td>
                        </tr>
                        <tr>
                            <td colspan="12" class="hiddenRow">
                                <div id="demo2" class="accordian-body collapse">
                                    <table class="table table-striped table-inr">
                                        <thead>
                                            <tr>
                                                <th>Location Name</th>
                                                <th>Total</th>
                                                <th>Action in progress</th>
                                                <th>Action not required</th>
                                                <th>Closed</th>
                                                <th>Notified</th>
                                                <th>Proposal</th>
                                                <th>Rejoiner</th>
                                                <th>Wrongly mapped destination</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td>Location Name</td>
                                                <td>25</td>
                                                <td>12</td>
                                                <td>32</td>
                                                <td>20</td>
                                                <td>5</td>
                                                <td>9</td>
                                                <td>2</td>
                                                <td>13</td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </td>
                        </tr>
                        <tr data-toggle="collapse" data-target="#demo3" class="accordion-toggle">
                            <td><button class="btn btn-default btn-xs"><span class="glyphicon glyphicon-plus-sign"></span></button> Mandal
                            </td>
                            <td><a href="#">25</a></td>
                            <td>12</td>
                            <td>32</td>
                            <td>20</td>
                            <td>5</td>
                            <td>9</td>
                            <td>2</td>
                            <td>13</td>
                        </tr>
                        <tr>
                            <td colspan="12" class="hiddenRow">
                                <div id="demo3" class="accordian-body collapse">
                                    <table class="table table-striped table-inr">
                                        <thead>
                                            <tr>
                                                <th>Location Name</th>
                                                <th>Total</th>
                                                <th>Action in progress</th>
                                                <th>Action not required</th>
                                                <th>Closed</th>
                                                <th>Notified</th>
                                                <th>Proposal</th>
                                                <th>Rejoiner</th>
                                                <th>Wrongly mapped destination</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td>Location Name</td>
                                                <td>25</td>
                                                <td>12</td>
                                                <td>32</td>
                                                <td>20</td>
                                                <td>5</td>
                                                <td>9</td>
                                                <td>2</td>
                                                <td>13</td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>
      
    </div>
  </div>
    
    <script src="newCoreDashBoard/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="newCoreDashBoard/js/bootstrap.min.js" type="text/javascript"></script>

<script src="newCoreDashBoard/Plugins/Highcharts/highcharts.js" type="text/javascript"></script>
<script src="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Date/moment.js" type="text/javascript"></script>
<script src="dist/DateRange/daterangepicker.js" type="text/javascript"></script>
<script src="dist/scroll/jquery.mousewheel.js" type="text/javascript"></script>
<script src="dist/alertDashBoard/dist/Plugins/Chosen/chosen.jquery.js" type="text/javascript"></script>
<script src="js/grievanceReport/grievanceReport.js" type="text/javascript"></script>
<script type="text/javascript" src="https://www.google.com/jsapi"></script>




    <script type="text/javascript">
        Highcharts.chart('barGraph', {
            colors: ['#FFCF2C'],
            chart: {
                backgroundColor: '#3C3D41',

                type: 'column'
            },
            title: {
                text: 'TDP',
                align: 'left'
            },
            xAxis: {

                min: 0,
                lineColor: 'transparent',
                gridLineWidth: 0,
                minorGridLineWidth: 0,
                type: 'category'
            },
            yAxis: {
                title: {
                    text: "aaa",
                    align: "left"
                },
                lineWidth: 0,
                gridLineWidth: 0,
                minorGridLineWidth: 0,
                allowDecimals: true,
                title: {
                    enabled: false
                },
                stackLabels: {
                    enabled: false,
                    style: {
                        fontWeight: 'bold',
                        color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
                    }
                }
            },

            tooltip: {
                enabled: true
            },


            legend: {
                enabled: false
            },
            plotOptions: {
                series: {
                    borderWidth: 0,
                    dataLabels: {
                        enabled: true,

                    }
                }
            },

            plotOptions: {
                series: {
                    borderRadius: 7.5,
                    pointWidth: 15
                }
            },

            exporting: {
                buttons: {
                    contextButton: {
                        enabled: false
                    },

                }
            },

            series: [{
                name: 'Brands',
                colorByPoint: false,
                lineWidth: 1,
                data: [{
                    name: 'PENDING',
                    y: 4,

                }, {
                    name: 'NOTIFIED',
                    y: 3.5,

                }, {
                    name: 'ACTION IN PROGRESS',
                    y: 2,

                }, {
                    name: 'COMPLETED',
                    y: 3,

                }, {
                    name: 'UNABLE TO RESOLVE',
                    y: 4,

                }, {
                    name: 'ACTION NOT REQUIRED',
                    y: 1,

                }, {
                    name: 'DUPLICATE',
                    y: 1,

                }, {
                    name: 'WRONGLY MAPPED DESIGNATION',
                    y: 4,

                }, {
                    name: 'WRONGLY MAPPED DEPARTMENT',
                    y: 3,

                }, {
                    name: 'REJOINER',
                    y: 2,

                }, {
                    name: 'REOPEN',
                    y: 3.5,

                }, {
                    name: 'CLOSED',
                    y: 3,

                }, {
                    name: 'PROPOSAL',
                    y: 4,

                }]

            }],

        });
</script>
</body>
</html>
