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
<link href="newCoreDashBoard/Plugins/Slick/slick.css" type="text/css" rel="stylesheet"/>
<link href="newCoreDashBoard/Plugins/Slick/slick-theme.css" type="text/css" rel="stylesheet"/>
<link href="newCoreDashBoard/css/custom.css" rel="stylesheet" type="text/css">
<style>
.dateColorCls{
	background-color:#FFCF2D;
}
</style>
</head>
<body>
<body>
<nav>
        <div class="row nav-gov-dark">
            
            <div class="container">
                <div class="logo-gov" href="#"> <img src="img/logo.png" class="img-responsive" alt="logo"> </div>
            </div>
        </div>
        <div class=" bg-gov-dark">
        
            <div class="container">
                <div id="menu1" class="col-md-6">       
                    <div class="col-md-6">
                        <ul class="nav navbar-nav">        
                            <li> <a href="#" attr_range_val="month" class="daterangeClorCls rangeTypeCls">MONTH</a> </li>  
                            <li> <a href="#" attr_range_val="week" class="daterangeClorCls rangeTypeCls" >WEEK</a> </li>
                            <li> <a href="#" attr_range_val="day" class="daterangeClorCls rangeTypeCls  dateColorCls">DAY</a> </li>
                        </ul>
                    </div>      
                    <div style="padding-top: 8px" class="col-md-6">
                        <!--<div id="reportrange" class="pull-right" style="background: #fff; cursor: pointer; padding: 5px 10px; border: 1px solid #ccc; width: 100%"> 
                             <i class="glyphicon glyphicon-calendar fa fa-calendar"></i>&nbsp; <span></span> <b class="caret"></b> </div> -->
							 <div class="input-group">
							<input type="text" class="form-control" id="reportrange"/>
							<span class="input-group-addon">
							<i class="glyphicon glyphicon-calendar"></i>
							</span>   							 
						</div>
                    </div>
                </div>
                 
				<div class="col-md-3">
                    <div style="padding-top: 8px" class=" dropdown">
                        <select id="selectMediaId"class="selectpicker" onChange="getMediaInformation();">
							<option value="0">All</option>
							<option value="1">Call Center</option>
							<option value="2">Print Media</option>
						    <option value="3">Electronic Media</option>
                        </select>
                    </div>
                </div>
                <div class="col-md-3">
                    <div style="padding-top: 8px" class=" dropdown pull-right" >
                        <select id="selecDepartmentId" class="selectpicker"  onChange="getDepartmentInformation();">
							<option value="0">Select Department</option>
							<option value="49">Ruler Water Supply</option>
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
  <div class="modal" tabindex="-1" role="dialog" id="cdrModelDivId">
		  <div class="modal-dialog modal-lg">       
			<div class="modal-content" style="border-radius:0px">
			  <div class="modal-header" style="background-color:#999999">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title" id="tourDocHeadingId">Cadre Registration Comparison Details</h4>  
			  </div>
			  <div class="modal-body">   
				<div class="row">
					<div class="col-md-12 col-xs-12 col-sm-12"> 
						<div id="cdrModelId"></div>
					</div>
					<div class="col-md-12 col-xs-12 col-sm-12"> 
						<div id="alertDestId" ></div>
					</div>
					<div class="col-md-12 col-xs-12 col-sm-12 m_top10"> 
						<div id="sourceHeadingId"></div>
					</div>
					<div class="col-md-12 col-xs-12 col-sm-12"> 
						<div id="headingNameId" ></div>
					</div>
					<div class="col-md-12 col-xs-12 col-sm-12 m_top10"> 
						<div id="alertDocHeadingId"></div>
					</div>
					<div class="col-md-12 col-xs-12 col-sm-12"> 
						<div id="alertDocId" ></div>
					</div>
					<div class="col-md-12 col-xs-12 col-sm-12 m_top10"> 
						<div id="alertAttachTitId"></div>    
					</div> 
					<div class="col-md-12 col-xs-12 col-sm-12"> 
						<div id="alertAttachImgId"></div>  
					</div>
					<div class="col-md-12 col-xs-12 col-sm-12 m_top10"> 
						<div id="alertGroupAttachTitId"></div>    
					</div> 
					<div class="col-md-12 col-xs-12 col-sm-12"> 
						<div id="alertGroupAttachImgId"></div>  
					</div>
					<div class="col-md-12 col-xs-12 col-sm-12 m_top10"> 
						<div id="alertInvolvedCandidates"></div>        
					</div>
					<div class="col-md-12 col-xs-12 col-sm-12 m_top10"> 
						<div id="alertAssignedCandidates"></div>  
					</div>
					<div class="col-md-12 col-xs-12 col-sm-12 m_top10"> 
						<div id="alertStatusDiv" ></div>    
					</div>
					<div  class="col-md-12 col-xs-12 col-sm-12"> 
						<div id="alertCommentsDiv"></div>  
					</div> 
					<div  class="col-md-12 col-xs-12 col-sm-12 m_top10"> 
						<div id="alertVerificationDiv"></div>    
					</div>
					<div  class="col-md-12 col-xs-12 col-sm-12"> 
						<div id="alertVerificationDtlsDiv"></div>  
					</div>
				</div>
			  </div>
			  <div class="modal-footer">     
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			  </div>
			</div><!-- /.modal-content -->
		  </div><!-- /.modal-dialog -->
		</div><!-- /.modal -->
		<div class="modal fade" id="myModalShowNew">
	<div class="modal-dialog modal-lg" role="document" style="width:90%">
		<div class="modal-content">
			<div id="myModalShowNewId"></div>
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
<script src="newCoreDashBoard/Plugins/Slick/slick.js" type="text/javascript"></script>

	<script type="text/javascript">
	var windowUrl = window.location.href;
	var wurl = windowUrl.substr(0,(windowUrl.indexOf("/updateToursDetailsAction")));
	wurl = wurl.replace("/PartyAnalyst","");
	
     
</script>
</body>
</html>
