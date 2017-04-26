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
<nav class="">
  <div class="container-fluid nav-gov-dark m_top20">
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
        <div  style="padding-top: 8px" class="col-md-6">
          <div id="reportrange" class="pull-right" style="background: #fff; cursor: pointer; padding: 5px 10px; border: 1px solid #ccc; width: 100%"> <i class="glyphicon glyphicon-calendar fa fa-calendar"></i>&nbsp; <span></span> <b class="caret"></b> </div>
		  
        </div>
      </div>
      <div class="col-md-5">
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
          <h4 class="text-center issue-title">AVERAGE ISSUE <br>
            PENDING DAYS</h4>
          <h3 class=" text-center issue-title">6 DAYS</h3>
        </div>
      </div>
      <div class="col-md-8">
        <div>
          <h1 class="text-center">
            <div id="barGraph"  style="min-width:10px; height: 400px; margin: 0 auto display:none"> </div>
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
</nav>
<section>
  <div class="container">
    <div class="row">
      <h3> Location wise Grevince Report </h3>
      <h5> (1 April 2017 to 25 April 2017)</h5>
    </div>
    <div class="table-responsive">
      <table id="table1" class="table table-bordered " cellspacing="0">
        <colgroup>
        <col span="1" style="background-color:white">
        <col span="1" style="background-color:#ebf4fb">
        <col span="8" style="background-color:white">
        <col span="5" style="background-color:#ecebd6">
        </colgroup>
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
            <th>Wrongly Mapped Designation</th>
            <th>18-April</th>
            <th>17-April</th>
            <th>16-April</th>
            <th>15-April</th>
            <th>14-April</th>
          </tr>
        </thead>
        <tfoot>
          <tr>
            <th>Total</th>
            <th><a class="js-open-modal " href="#" data-modal-id="popup1"> 23</a> </th>
            <th>32</th>
            <th>14</th>
            <th>05</th>
            <th>33</th>
            <th>10</th>
            <th>11</th>
            <th>25</th>
            <th>15</th>
            <th>18</th>
            <th>17</th>
            <th>16</th>
            <th>15</th>
            <th>14</th>
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
            <td>61</td>
            <td>04</td>
            <td>80</td>
            <td>20</td>
            <td>21</td>
            <td>22</td>
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
            <td>61</td>
            <td>04</td>
            <td>80</td>
            <td>20</td>
            <td>21</td>
            <td>22</td>
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
            <td>61</td>
            <td>04</td>
            <td>80</td>
            <td>20</td>
            <td>21</td>
            <td>22</td>
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
            <td>61</td>
            <td>04</td>
            <td>80</td>
            <td>20</td>
            <td>21</td>
            <td>22</td>
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
            <td>61</td>
            <td>04</td>
            <td>80</td>
            <td>20</td>
            <td>21</td>
            <td>22</td>
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
            <td>61</td>
            <td>04</td>
            <td>80</td>
            <td>20</td>
            <td>21</td>
            <td>22</td>
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
            <td>61</td>
            <td>04</td>
            <td>80</td>
            <td>20</td>
            <td>21</td>
            <td>22</td>
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
            <td>61</td>
            <td>04</td>
            <td>80</td>
            <td>20</td>
            <td>21</td>
            <td>22</td>
          </tr>
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
            <td>61</td>
            <td>04</td>
            <td>80</td>
            <td>20</td>
            <td>21</td>
            <td>22</td>
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
            <td>61</td>
            <td>04</td>
            <td>80</td>
            <td>20</td>
            <td>21</td>
            <td>22</td>
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
            <td>61</td>
            <td>04</td>
            <td>80</td>
            <td>20</td>
            <td>21</td>
            <td>22</td>
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
            <td>61</td>
            <td>04</td>
            <td>80</td>
            <td>20</td>
            <td>21</td>
            <td>22</td>
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
            <td>61</td>
            <td>04</td>
            <td>80</td>
            <td>20</td>
            <td>21</td>
            <td>22</td>
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
            <td>61</td>
            <td>04</td>
            <td>80</td>
            <td>20</td>
            <td>21</td>
            <td>22</td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</section>
<div id="popup1" class="modal-box">
  <header> <!--// <a href="#" class="js-modal-close close">×</a>-->
    <h3>Pop Up One</h3>
  </header>
  <div class="modal-body">
    <div>
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
            <th><a href="#"> 23</a> </th>
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
  <footer> <a href="#" class="btn btn-warning js-modal-close">Close</a> </footer>
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

<script>
	
  Highcharts.chart('barGraph', {
 colors: ['#FFCF2C'],
    chart: {
	   backgroundColor: '#3C3D41',
		
       type:'column'
    }, title: {
        text: 'TDP',
		align:'left'
    },
    xAxis: {
	 
	   min:0,
		    lineColor: 'transparent',
		 gridLineWidth: 0,
		minorGridLineWidth: 0,
        type: 'category'
		    },
    yAxis: {
	 title:{
	  text:"aaa",
	  align:"left"
	  },
	lineWidth: 0,
	gridLineWidth: 0,
		minorGridLineWidth:0,
	    allowDecimals: true,
	title:{
	enabled:false
	},
stackLabels: {
			enabled: false,
			style: {
				fontWeight: 'bold',
				color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
			}
		}
	   },
 
 tooltip:
 {
 enabled:true
 },
 
 
    legend: {
        enabled: false
    },
    plotOptions: {
        series: {
            borderWidth:0,
            dataLabels: {
                enabled: true,
               
            }
        }
    },
	  
	      plotOptions: {
        series: {
            borderRadius: 7.5,
			pointWidth:15
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
		lineWidth:1,
        data: [{
            name: 'PENDING',
            y: 4,
           
        }, {
            name: 'NOTIFIED',
            y: 3.5,
           
        }, {
            name: 'ACTION IN PROGRESS',
            y: 2,
           
        },{
            name: 'COMPLETED',
            y: 3,
           
        },{
            name: 'UNABLE TO RESOLVE',
            y: 4,
           
        },{
            name: 'ACTION NOT REQUIRED',
            y: 1,
           
        },{
            name: 'DUPLICATE',
            y: 1,
           
        },{
            name: 'WRONGLY MAPPED DESIGNATION',
            y: 4,
           
        },{
            name: 'WRONGLY MAPPED DEPARTMENT',
            y: 3,
           
        },{
            name: 'REJOINER',
            y: 2,
           
        },{
            name: 'REOPEN',
            y: 3.5,
           
        },{
            name: 'CLOSED',
            y: 3,
           
        },{
            name: 'PROPOSAL',
            y: 4,
           
        }
		]
		 
    }],
   
     });
	

$(function(){

var appendthis =  ("<div class='modal-overlay js-modal-close'></div>");

	$('a[data-modal-id]').click(function(e) {
		e.preventDefault();
    $("body").append(appendthis);
    $(".modal-overlay").fadeTo(500, 0.7);
    //$(".js-modalbox").fadeIn(500);
		var modalBox = $(this).attr('data-modal-id');
		$('#'+modalBox).fadeIn($(this).data());
	});  
  
  
$(".js-modal-close, .modal-overlay").click(function() {
    $(".modal-box, .modal-overlay").fadeOut(500, function() {
        $(".modal-overlay").remove();
    });
 
});
 
$(window).resize(function() {
    $(".modal-box").css({
        top: ($(window).height() - $(".modal-box").outerHeight()) / 2,
        left: ($(window).width() - $(".modal-box").outerWidth()) / 2
    });
});
 
$(window).resize();
 
});

  var _gaq = _gaq || [];
  _gaq.push(['_setAccount', 'UA-36251023-1']);
  _gaq.push(['_setDomainName', 'jqueryscript.net']);
  _gaq.push(['_trackPageview']);

  (function() {
    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
  })();

  
</script>
</body>
</html>
