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
    <link href="js/cadreCommittee/bootstrapDaterangepicker/daterangepicker-bs3.css" rel="stylesheet" />
		<!--Hover Menu-->
    <link href="css/cadreCommitee/css/jquery.smartmenus.bootstrap.css" rel="stylesheet" />
    	<!--Circle-->
    <link href="js/cadreCommittee/dist/css/jquery.circliful.css" rel="stylesheet" />
	<style>
	.circle-text{
		line-height: 180px; font-size: 34px; top: 32px;  left: 0px; !important
	}
	.circle-info-half{
		line-height: 225px; left: 0px;  top: 34px;!important
	}
	</style>	
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
                      <li><a tabindex="-1" href="cadreCommitteeAction.action">Home</a></li>
				  	  <li><a tabindex="-1" href="cadreCommitteeSummaryAction.action">Summary Report</a></li>
				      <li><a tabindex="-1" href="cadreCommitteeRequestAction.action">Request For Positions Increase</a></li>
				      <li><a tabindex="-1" href="committeeDashBoardAction.action">Committee DashBoard</a></li>
                      <li role="presentation" class="divider" style="background-color: rgba(229, 229, 229,0.6);"></li>
                     <li><a tabindex="-1" href="newlogoutAction.action">Sign Out</a></li>
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
               <div class="col-md-12 col-xs-12  col-sm-12" style="padding-right:0px; padding-bottom:5px;">
					<div id="reportrange" class="pull-right" style="background:rgba(0,0,0,0.1); cursor: pointer; padding: 5px 10px; border: 1px solid rgba(0,0,0,0.1);">
					  <i class="glyphicon glyphicon-calendar fa fa-calendar"></i>
					  <span></span> <b class="caret"></b>
					</div>
                </div>
        </div>
		
    	<div class="row">
			<table class="table table-bordered" width="100%"  >
				<tr>
					<td width="22%" style="background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.1);text-align:center;padding: 0px;">
						<div class="col-md-12">
							<h4>ANHDRA PRADESH</h4>
							<div class="row" id="totalAPCount">
								
							</div>
						
				
						</div>
					</td>
					<td width="78%" style="padding:0px;">
						<table width="100%" class="table table-bordered" style="background-color:transparent; margin-bottom:0px;"  >
							<tr style="background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.1);">
								<td colspan="6">
									<button id="mandal" class="btn btn-xs btn-success" onclick="getCommitteeDetails('AP','mandal')">Mandal</button> | 
									<button id="town" class="btn btn-xs btn-success" onclick="getCommitteeDetails('AP','town')">Town</button> |	
									<button id="division" class="btn btn-xs btn-success" onclick="getCommitteeDetails('AP','division')">Division</button> |									
									<button id="mandalAll" class="btn btn-xs btn-default" onclick="getCommitteeDetails('AP','mandalAll')">All</button>
								</td>
							</tr>
							<tr>
								<td style="padding:10px;" width="18%"><b>TOTAL MAIN </b>Committees<h4 class="m_top0"><div id="div1"></div></h4></td>
								
								<td style="padding:10px;" width="10%">
									<span class="text-success">Started</span> Committees<br/>
									<table>
										<tr>
											<td class="row-table">
												<h4 class="m_top0"><div id="div29"></div></h4>
											</td>
											<td class="row-table">
												<h5 class="m_top0"><div id="div2"></div></h5>
											</td>
										</tr>
									</table>
								</td>
								
								<td style="padding:10px;" width="10%"><span class="text-danger">Completed</span> Committees<br/><table><tr><td  class="row-table"><h4 class="row-table m_top0"><div id="div3"></div></h4></td><td  class="row-table"><ul class="nav navbar-nav">
                              <li><a><div id="div4"></div></a>
                                  
									
									
								</li></td></tr></table></td>
								<td style="padding:10px;" width="20%">Affliated Committees<br/><span class="text-success">Started</span><br/><h4 class="m_top0"><div id="div5"></div></h4></td>
								<td style="padding:10px;" width="20%"><span class="text-success">Completed </span><br/>Affliated Committees<br/>
                                <h4 class="m_top0">
								<ul class="nav navbar-nav">
                              <li>
								<a><div id="div6"></div></a>
                              </h4></td>
								<td style="padding:10px;" width="28%">TOTAL<br/> <b>MEMBERS</b><h4 class="m_top0"><div id="div7"></div></h4></td>
							<div id="apMandalDiv"></div>
							</tr>
							<tr style="background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.1);">
								<td colspan="6">
										<button id="village"  class="btn btn-xs btn-success" onclick="getCommitteeDetails('AP','village')">Village</button> | 
										<button id="ward" class="btn btn-xs btn-success" onclick="getCommitteeDetails('AP','ward')">Ward</button> |										
										<button id="villageAll" class="btn btn-xs btn-default" onclick="getCommitteeDetails('AP','villageAll')">All</button>
								</td>
							</tr>
							<tr>
								<td style="padding:10px;" width="18%"><b>TOTAL MAIN </b>Committees<h4 class="m_top0"><div id="div8"></div></h4></td>
								<td style="padding:10px;" width="10%">
									<span class="text-success">Started</span> Committees<br/>
									<table>
										<tr>
											<td class="row-table">
												<h4 class="m_top0"><div id="div32"></div></h4>
											</td>
											<td class="row-table">
												<h5 class="m_top0"><div id="div9"></div></h5>
											</td>
										</tr>
									</table>
								</td>
							   <td style="padding:10px;" width="10%"><span class="text-danger">Completed</span> Committees<br/><table><tr><td  class="row-table"><h4 class="row-table m_top0"><div id="div10"></div></h4></td><td  class="row-table">
							   <ul class="nav navbar-nav">
								<li>
									<a><div id="div11"></div></a>
                                   
								</li>	
									
									</td></tr></table></td>
								<td style="padding:10px;" width="20%">Affliated Committees<br/><span class="text-success">Started</span><br/><h4 class="m_top0"><div id="div12"></div></h4></td> 
								<td style="padding:10px;" width="20%"><span class="text-success">Completed</span><br/>Affliated Committees<br/><h4 class="m_top0"> <ul class="nav navbar-nav">
                              <li><a><div id="div13"></div></a>
                               </h4></td>
								<td style="padding:10px;" width="28%">TOTAL <br/><b>MEMBERS</b><h4 class="m_top0"><div id="div14"></div></h4></td>
								<div id="apVillageDiv"></div>
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
								<button id="tsMandal" class="btn btn-xs btn-success" onclick="getCommitteeDetails('TS','mandal')";>Mandal</button> | 
								<button id="tsTown" class="btn btn-xs btn-success" onclick="getCommitteeDetails('TS','town')";>Town</button> |
								<button id="tsDivision" class="btn btn-xs btn-success" onclick="getCommitteeDetails('TS','division')";>Division</button> |
								<button id="tsMandalAll" class="btn btn-xs btn-default" onclick="getCommitteeDetails('TS','mandalAll')";>All</button>
							</td>
						</tr>
						<tr>
							<td style="padding:10px;" width="18%"><b>TOTAL MAIN </b>Committees<h4 class="m_top0"><div id="div15"></div></h4></td>
							<td style="padding:10px;" width="10%">
									<span class="text-success">Started</span> Committees<br/>
									<table>
										<tr>
											<td class="row-table">
												<h4 class="m_top0"><div id="div35"></div></h4>
											</td>
											<td class="row-table">
												<h5 class="m_top0"><div id="div16"></div></h5>
											</td>
										</tr>
									</table>
								</td>
						   <td style="padding:10px;" width="10%"><span class="text-danger">Completed</span> Committees<br/><table><tr><td  class="row-table"><h4 class="row-table m_top0"><div id="div17"></div></h4></td><td  class="row-table"><ul class="nav navbar-nav">
                              <li><a><div id="div18"></div></a>
                                 
									
								</li></td></tr></table></td>
							<td style="padding:10px;" width="20%">Affliated Committees<br/><span class="text-success">Started</span><br/><h4 class="m_top0"><div id="div19"></div></h4></td>
							<td style="padding:10px;" width="20%"><span class="text-success">Completed</span><br/>Affliated Committees<br/><h4 class="m_top0"> <ul class="nav navbar-nav">
                              <li><a><div id="div20"></div></a>
                               
                                </h4></td>
							<td style="padding:10px;" width="28%">TOTAL<br/> <b>MEMBERS</b><h4 class="m_top0"><div id="div21"></div></h4></td>
							<div id="tsMandalDiv"></div>
						</tr>
						<tr style="background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.1);">
							<td colspan="6">
								<button id="tsVillage" class="btn btn-xs btn-success" onclick="getCommitteeDetails('TS','village')";>Village</button> | 
								<button id="tsWard" class="btn btn-xs btn-success" onclick="getCommitteeDetails('TS','ward')";>Ward</button> |
								
								<button id="tsVillageAll" class="btn btn-xs btn-default" onclick="getCommitteeDetails('TS','villageAll')";>All</button>
							</td>
						</tr>
						<tr>
							<td style="padding:10px;" width="18%"><b>TOTAL MAIN </b>Committees<h4 class="m_top0"><div id="div22"></div></h4></td>
							<td style="padding:10px;" width="10%">
									<span class="text-success">Started</span> Committees<br/>
									<table>
										<tr>
											<td class="row-table">
												<h4 class="m_top0"><div id="div38"></div></h4>
											</td>
											<td class="row-table">
												<h5 class="m_top0"><div id="div23"></div></h5>
											</td>
										</tr>
									</table>
								</td>
						   <td style="padding:10px;" width="10%"><span class="text-danger">Completed</span> Committees<br/><table><tr><td  class="row-table"><h4 class="row-table m_top0"><div id="div24"></div></h4></td><td  class="row-table">
						   <ul class="nav navbar-nav">
                              <li><a><div id="div25"></div></a>
                                   
									
								</li>
									</td></tr></table></td>
							<td style="padding:10px;" width="20%">Affliated Committees<br/><span class="text-success">Started</span><br/><h4 class="m_top0"><div id="div26"></div></h4></td>
							<td style="padding:10px;" width="20%"><span class="text-success">Completed</span><br/>Affliated Committees<br/><h4 class="m_top0"> <ul class="nav navbar-nav">
                              <li><a><div id="div27"></div></a>
                             </h4></td>
							<td style="padding:10px;" width="28%">TOTAL<br/> <b>MEMBERS</b><h4 class="m_top0"><div id="div28"></div></h4></td> 
							<div id="tsVillageDiv"></div>
						</tr>
					</table>
				</td>
      	    </tr>
      	  </table>
        </div>
		
        <div class="row m_top20">        	 
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
        </div>
    </div>


	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/cadreCommittee/dist/js/bootstrap.min.js"></script>
	<!--Circle js file-->
	
    <script src="js/cadreCommittee/dist/js/jquery.circliful.min.js"></script>
    <!--Bootstrap Date Picker-->
   <script src="js/cadreCommittee/bootstrapDaterangepicker/moment.min.js" type="text/javascript"></script> 
	<script src="js/cadreCommittee/bootstrapDaterangepicker/daterangepicker.js" type="text/javascript"></script>   
    <!--Jquery Sparkline-->
    <script src="js/cadreCommittee/js/jquery.sparkline.js" type="text/javascript"></script>
    <!-- Custom JS File-->
    <script src="js/cadreCommittee/js/custom.js" type="text/javascript"></script>
    <!--Hover Menu-->
    <script src="js/cadreCommittee/js/jquery.smartmenus.min.js" type="text/javascript"></script>
    <script src="js/cadreCommittee/js/jquery.smartmenus.bootstrap.min.js" type="text/javascript"></script>
    
	<script>
	
	</script>
	
	<!----Bootstrap Date Range Picker Script---->
		<script type="text/javascript">
               $(document).ready(function() {

                  var cb = function(start, end, label) {
                    console.log(start.toISOString(), end.toISOString(), label);
                    $('#reportrange span').html(start.format('MMMM D, YYYY') + ' - ' + end.format('MMMM D, YYYY'));
                    //alert("Callback has fired: [" + start.format('MMMM D, YYYY') + " to " + end.format('MMMM D, YYYY') + ", label = " + label + "]");
                  }

                  var optionSet1 = {
                    startDate: moment().subtract(29, 'days'),
                    endDate: moment(),
                    minDate: '01/01/2012',
                    maxDate: '12/31/2015',
                    dateLimit: { days: 60 },
                    showDropdowns: true,
                    showWeekNumbers: true,
                    timePicker: false,
                    timePickerIncrement: 1,
                    timePicker12Hour: true,
                   /* ranges: {
                       'Today': [moment(), moment()],
                       'Yesterday': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
                       'Last 7 Days': [moment().subtract(6, 'days'), moment()],
                       'Last 30 Days': [moment().subtract(29, 'days'), moment()],
                       'This Month': [moment().startOf('month'), moment().endOf('month')],
                       'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
                    }, */
                    opens: 'left',
                    buttonClasses: ['btn btn-default'],
                    applyClass: 'btn-small btn-success',
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

                
					getCommitteeCountByState("AP");
					getCommitteeCountByState("TS");
					getCommitteeDetails("AP","mandalAll");
					getCommitteeDetails("AP","villageAll");	
					getCommitteeDetails("TS","mandalAll");	
					getCommitteeDetails("TS","villageAll");
               });
			   
               </script>
	<!----/Bootstrap Date Range Picker Script END---->
			   
			   		
	<script>
	
	
		
		
	
	
	function getCommitteeDetails(state,level){
	
	var startDate=$(".dp_startDate").val();
	var endDate=$(".dp_endDate").val();
	
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
			startDate:startDate,
			endDate:endDate,
			task:"committeeDetails"
		}
		$.ajax({
          type:'GET',
          url: 'getDashBoardLocationWiseDetails.action',
		  data : {task:JSON.stringify(jObj)} ,
        }).done(function(result){
			var str='';
			var str1='';
			var str2='';
			var str3='';
			if(result != null){
				if(state == "AP"){
					if(level == 'mandal' || level == 'town' || level == 'division' || level == 'mandalAll')
					{
						$("#div1").html(result.committeesCount);
						
						$("#div29").html(result.startedCommitteePerc+"%");
						$("#div2").html(result.mainCommittees);						
						
						$("#div3").html(result.completedCommitteePerc+"%");
						$("#div4").html(result.completedCommittees);
						
						//$("#div30").html(result.afflCommitteesPerc);
						$("#div5").html(result.afflCommittees);
						
						$("#div6").html(result.affliatedCompleted);
						//$("#div31").html(result.affliatedCompletedPerc);
												
						$("#div7").html(result.membersCount);
					}
					
					if(level == 'village' || level == 'ward' || level == 'villageAll')
					{
						$("#div8").html(result.committeesCount);
						
						$("#div32").html(result.startedCommitteePerc+"%");
						$("#div9").html(result.mainCommittees);						
						
						$("#div10").html(result.completedCommitteePerc+"%");
						$("#div11").html(result.completedCommittees);
						
						//$("#div33").html(result.afflCommitteesPerc);
						$("#div12").html(result.afflCommittees);
						
						$("#div13").html(result.affliatedCompleted);
						//$("#div34").html(result.affliatedCompletedPerc);
												
						$("#div14").html(result.membersCount);
					
					}
				}
				if(state == "TS"){
					if(level == 'mandal' || level == 'town' || level == 'division' || level == 'mandalAll')
					{
					
						$("#div15").html(result.committeesCount);
						
						$("#div35").html(result.startedCommitteePerc+"%");
						$("#div16").html(result.mainCommittees);						
						
						$("#div17").html(result.completedCommitteePerc+"%");
						$("#div18").html(result.completedCommittees);
						
						//$("#div36").html(result.afflCommitteesPerc);
						$("#div19").html(result.afflCommittees);
						
						//$("#div37").html(result.affliatedCompletedPerc);
						$("#div20").html(result.affliatedCompleted);
						
												
						$("#div21").html(result.membersCount);
					
					
					}
					
				 if(level == 'village' || level == 'ward' || level == 'villageAll')
					{
					
						$("#div22").html(result.committeesCount);
						
						$("#div38").html(result.startedCommitteePerc+"%");
						$("#div23").html(result.mainCommittees);						
						
						$("#div24").html(result.completedCommitteePerc+"%");
						$("#div25").html(result.completedCommittees);
						
						//$("#div39").html(result.afflCommitteesPerc);
						$("#div26").html(result.afflCommittees);
						
						//$("#div40").html(result.affliatedCompletedPerc);
						$("#div27").html(result.affliatedCompleted);
																	
						$("#div28").html(result.membersCount);	
					}
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
					str+='<div class="myStatAP" data-dimension="180" data-text="'+result.totalCntPerc+'%" data-info="COMPLETED" data-width="10" data-fontsize="34" data-percent="'+result.totalCntPerc+'" data-fgcolor="#349866" data-bgcolor="#6D6024" style="margin-left: 40px;"></div>';				
					$("#totalAPCount").html(str);
					$('.myStatAP').circliful();
				}
				if(state == "TS"){
					str+='<div class="myStatTS" data-dimension="180" data-text="'+result.totalCntPerc+'%" data-info="COMPLETED" data-width="10" data-fontsize="34" data-percent="'+result.totalCntPerc+'" data-fgcolor="#349866" data-bgcolor="#6D6024" style="margin-left: 40px;"></div>';
					$("#totalTSCount").html(str);
					$('.myStatTS').circliful();					
				}
		
		});
	}
	</script>
			   
			   
</body>
</html>
