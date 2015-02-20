<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Cadre Missed Call Campaign</title>
<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<style type="text/css">
*{border-radius:0px !important;}
#chartdiv {
	width		: 100%;
	height		: 440px;
	font-size	: 11px;
}
.progress{height:5px !important;}	
.progresslabelcolor{width:10px;height:10px;display:inline-block;border:1px solid #CCC;}	
ul
{
    list-style-type: none;
	margin-left:0px;
}
</style>
<script type="text/javascript" src="js/bootStrapDateRange/daterangepicker.js"></script>
<link rel="stylesheet" type="text/css" href="css/daterangepicker-bs2.css"/>
</head>

<body>
<header></header>
<section>
	<div class="container">
    		<table class="table table-bordered">
				<caption style="background-color:#f4f4f4;border:1px solid #dddddd">
                	<h3>CADRE MISSED CALL CAMPAIGN</h3>
                </caption>
                <tr style="background-color:#f4f4f4">
                	<td colspan="3">
                    	<label class="radio inline">
                            <input type="radio" name="select" />
                                All
                        </label>
                    	<label class="radio inline">
                            <input type="radio" name="select" />
                                Andhra Pradesh
                        </label>
                    	<label class="radio inline">
                            <input type="radio" name="select" />
                                Telangana
                        </label>
                        <div class="dropdowncalendar pull-right" style="background: #fff; cursor: pointer; padding: 5px 10px; border: 1px solid #ccc">
                          <i class="icon icon-calendar"></i>
                       </div>
                    </td>
                </tr>
                <tr>
                	<td width="430" style="text-align:center;padding:0px;">
                    	<div id="chartdiv" style="margin-bottom:-80px;margin-top:-20px"></div>
                        <h5 style="margin-bottom:0px;">TOTAL MISSED CALLS</h5>
                        <h3 style="margin-top:0px;">1000</h3>
                        <table class="table table-bordered" style="margin-bottom:0px;">
                        	<tr style="word-wrap:break-word">
                            	<td>
                                <small>
                                	<b>Numbers Mismatched Cadre Missed Calls</b>
                                </small>
                                <h3>100</h3>
                                </td>
                                <td>
                                <small>
                                	<b>Single Member Registered Numbers</b>
                                </small>
                                <h3>400</h3>
                                </td>
                                <td>
                                <small>
                                	<b>Multi Member Registered Numbers</b>
                                </small>
                                <h3>400</h3>
                                </td>
                            </tr>
                        </table>
                    </td>
                    <td width="350px" style="padding:0px">
                    	<h6 style="border:1px solid #dddddd;background-color:#f4f4f4;padding:5px;margin:0px">ANDHRA PRADESH & TELANGANA district wise missed calls percentages</h6>
                    	<div style="padding:2px;font-size:10px;">
                            <label class="radio inline">
                                <input type="radio" name="select" />
                                    <small>Single Member Registered</small>
                            </label>
                            <label class="radio inline">
                                <input type="radio" name="select" />
                                    <small>Multi Member Registered</small>
                            </label>
                        </div>

                        <ul  class="slimscrollar" style="padding:8px;margin-top:5px;border-top:1px solid #dddddd">
                        <li class="percentagefilter6">
                        	<span class="progresslabelcolor" style="background-color:#0F0"></span>
                            Anantapur (10%) 225
                            <div class="progress progress-success progress-striped active">
                                  <div class="bar" style="width: 20%"></div>
                            </div>
                        </li>
						<li class="percentagefilter7">
                            <span class="progresslabelcolor" style="background-color:#9900cc"></span>
                            Chittor (10%) 225
                            <div class="progress progress-danger progress-striped active">
                                  <div class="bar" style="width: 20%"></div>
                            </div>
                        </li>
						<li class="percentagefilter8">
                            <span class="progresslabelcolor" style="background-color:#cccccc"></span>
							East Godavari (10%) 225
                            <div class="progress progress-warning progress-striped active">
                                  <div class="bar" style="width: 20%"></div>
                            </div>
						</li>
   						<li class="percentagefilter9">
                            <span class="progresslabelcolor" style="background-color:#fe9900"></span>
							Guntur (50%) 25
                            <div class="progress progress-danger progress-striped active">
                                  <div class="bar" style="width: 20%"></div>
                            </div>
                        </li>
                        <li class="percentagefilter10">
                            <span class="progresslabelcolor" style="background-color:#663332"></span>
                            Kadapa (1%) 22
                            <div class="progress progress-info progress-striped active">
                                  <div class="bar" style="width: 20%"></div>
                            </div>
                        </li>
                        <li class="percentagefilter11">
                            <span class="progresslabelcolor" style="background-color:#00ccff"></span>
                            Krishna (50%) 925
                            <div class="progress progress-warning progress-striped active">
                                  <div class="bar" style="width: 20%"></div>
                            </div>
                        </li>
                        <li class="percentagefilter12">
                            <span class="progresslabelcolor" style="background-color:#003499"></span>
                            Kadapa (60%) 425
                            <div class="progress progress-danger progress-striped active">
                                  <div class="bar" style="width: 20%"></div>
                            </div>
                        </li>
                        <li class="percentagefilter13">
                            <span class="progresslabelcolor" style="background-color:#ff00cc"></span>
                            Prakasam (30%) 295
                            <div class="progress progress-danger progress-striped active">
                                  <div class="bar" style="width: 20%"></div>
                            </div>
                        </li>
                        <li class="percentagefilter14">
                            <span class="progresslabelcolor" style="background-color:#006600"></span>
                            Nellore (20%) 255
                            <div class="progress progress-danger progress-striped active">
                                  <div class="bar" style="width: 20%"></div>
                            </div>
                        </li>
                        <li class="percentagefilter15">
                            <span class="progresslabelcolor" style="background-color:#01ffc9"></span>
                            Srikakulam (10%) 225
                            <div class="progress progress-danger progress-striped active">
                                  <div class="bar" style="width: 20%"></div>
                            </div>
                        </li>
                        <li class="percentagefilter16">
                            <span class="progresslabelcolor" style="background-color:#ccfe00"></span>
                            Vishakapatnam (10%) 225
                            <div class="progress progress-danger progress-striped active">
                                  <div class="bar" style="width: 20%"></div>
                            </div>
                        </li>
                        <li class="percentagefilter17">
                            <span class="progresslabelcolor" style="background-color:#ffcb01"></span>
                            Vizianagaram (10%) 225
                            <div class="progress progress-danger progress-striped active">
                                  <div class="bar" style="width: 20%"></div>
                            </div>
                        </li>
                        </ul>	
                    </td>
                    <td style="background-color:#f4f4f4">
                    	<label class="label label-custom">Filters<i class="icon icon-remove"></i></label>
						<ul style="margin-top:80px;">
                        	<li style="cursor:pointer;border-bottom:1px solid #dddddd;margin-bottom:10px;" class="percentagefilter1">
                            	<div style="border-radius:2px !important;margin-bottom:0px;width:30px;height:20px !important;" class="progress progress-success progress-striped active">
                                  <div class="bar" style="width:100%"></div>
                                </div>
                                <span >VERY GOOD [0-10%]</span>
                            </li>
                        	<li style="cursor:pointer;border-bottom:1px solid #dddddd;margin-bottom:10px;" class="percentagefilter2">
                            	<div style="border-radius:2px !important;margin-bottom:0px;width:30px;height:20px !important;" class="progress progress-info progress-striped active">
                                  <div class="bar" style="width:100%"></div>
                                </div>
                                <span>GOOD [0-10%]</span>
                            </li>
                        	<li style="cursor:pointer;border-bottom:1px solid #dddddd;margin-bottom:10px;" class="percentagefilter3">
                            	<div style="border-radius:2px !important;margin-bottom:0px;width:30px;height:20px !important;" class="progress progress-primary progress-striped active">
                                  <div class="bar" style="width:100%"></div>
                                </div>
                                <span>OK [0-10%]</span>
                            </li>
                        	<li style="cursor:pointer;border-bottom:1px solid #dddddd;margin-bottom:10px;" class="percentagefilter4">
                            	<div style="border-radius:2px !important;margin-bottom:0px;width:30px;height:20px !important;" class="progress progress-warning progress-striped active">
                                  <div class="bar" style="width:100%"></div>
                                </div>
                                <span>POOR [0-10%]</span>
                            </li>
                        	<li style="cursor:pointer;border-bottom:1px solid #dddddd;margin-bottom:10px;" class="percentagefilter5">
                            	<div style="border-radius:2px !important;margin-bottom:0px;width:30px;height:20px !important;" class="progress progress-danger progress-striped active">
                                  <div class="bar" style="width:100%"></div>
                                </div>
                                <span>VERY POOR [0-10%]</span>
                            </li>
                            <li>
                            	<b><i><u>NOTE:</u>Click to View percentage range Wise details</i></b>
                          </li>
                        </ul>
                    </td>
                </tr>
            </table>
        <div style="border:3px solid #CCC">
        	<table class="table table-bordered" style="margin-bottom:0px;">
            	<caption style="background-color:#f4f4f4;font-size:16px;">
                	<b class="pull-left" style="padding-top:10px;">ANATAPUR DISTRICT MISSEDCALL DETAILS</b><span class="pull-left" style="padding-top:10px;font-size:10px">(FROM DATE 2-FEB-2015 TO DATE 19-FEB-2015)</span> 	
                	<span class="pull-right" style=" cursor:pointer;background-color:#CCC;padding:10px;">
                    	<i class="icon icon-remove"></i>
                    </span>
                </caption>
                <thead style="font-size:11px;">
                	<th>Constituency</th>
                    <th>Registered Count</th>
                    <th>Printed Count</th>
                    <th>Received Missed Calls</th>
                    <th>Mismatched Calls</th>
                    <th>Single Member Registered</th>
                    <th>Multi Member Registered</th>
                </thead>
                <tbody>
                	<tr>
                    	<td>Rayadurga</td>
                        <td>2564</td>
                        <td>2000</td>
                        <td>250</td>
                        <td>10</td>
                        <td>150</td>
                        <td>90</td>
                    </tr>
                	<tr>
                    	<td>Urvakonda</td>
                        <td>2564</td>
                        <td>2000</td>
                        <td>250</td>
                        <td>10</td>
                        <td>150</td>
                        <td>90</td>
                    </tr>
                	<tr>
                    	<td>Guntakal</td>
                        <td>2564</td>
                        <td>2000</td>
                        <td>250</td>
                        <td>10</td>
                        <td>150</td>
                        <td>90</td>
                    </tr>
                	<tr>
                    	<td>Tadpatri</td>
                        <td>2564</td>
                        <td>2000</td>
                        <td>250</td>
                        <td>10</td>
                        <td>150</td>
                        <td>90</td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</section>


<script src="js/jquery-1.11.2.min.js" type="text/javascript"></script>
<script src="js/bootstrap.min.js" type="text/javascript"></script>
<script type="text/javascript" src="js/moment.min.js"></script>

<script type="text/javascript" src="amcharts/amcharts.js"></script>
<script type="text/javascript" src="amcharts/pie.js"></script>
<script type="text/javascript" src="amcharts/none.js"></script>
<script type="text/javascript" src="js/jquery.slimscroll.min.js"></script>
<script type="text/javascript" src="js/custom.js"></script>
<script type="text/javascript">
var chart = AmCharts.makeChart("chartdiv", {
    "type": "pie",
    "theme": "none",
    "dataProvider": [{
        "title": "KADAPA",
        "value": 4852
    },{
        "title": "CHITTOR",
        "value": 4852
    },{
        "title": "ANANTAPUR",
        "value": 4852
    },{
        "title": "EAST GODAVARI",
        "value": 4852
    },{
        "title": "KRISHNA",
        "value": 4852
    },{
        "title": "KURNOOL",
        "value": 4852
    }, {
        "title": "PRAKASHAM",
        "value": 9899
    }, {
        "title": "NELLORE",
        "value": 9899
    }, {
        "title": "SRIKAKULAM",
        "value": 9899
    }, {
        "title": "VISAKAPATNAM",
        "value": 9899
    }, {
        "title": "VIZIANAGARAM",
        "value": 9899
    }, {
        "title": "WEST GODAVARI",
        "value": 9899
    }],
    "titleField": "title",
    "valueField": "value",
    "labelRadius": 5,

    "radius": "30%",
    "innerRadius": "60%",
    "labelText": "[[]]"
});
$(function(){
    $('.slimscrollar').slimScroll({
        height: '450px'
    });
});
</script>
<script type="text/javascript">
	$(document).ready(function() {
	  var cb = function(start, end, label) {
		console.log(start.toISOString(), end.toISOString(), label);
		$('.dropdowncalendar span').html(start.format('MMMM D, YYYY') + ' - ' + end.format('MMMM D, YYYY'));
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

	  $('.dropdowncalendar span').html(moment().subtract(29, 'days').format('MMMM D, YYYY') + ' - ' + moment().format('MMMM D, YYYY'));

	  $('.dropdowncalendar').daterangepicker(optionSet1, cb);

	  $('.dropdowncalendar').on('show.daterangepicker', function() { console.log("show event fired"); });
	  $('.dropdowncalendar').on('hide.daterangepicker', function() { console.log("hide event fired"); });
	  $('.dropdowncalendar').on('apply.daterangepicker', function(ev, picker) { 
		console.log("apply event fired, start/end dates are " 
		  + picker.startDate.format('MMMM D, YYYY') 
		  + " to " 
		  + picker.endDate.format('MMMM D, YYYY')
		); 
	  });
	  $('.dropdowncalendar').on('cancel.daterangepicker', function(ev, picker) { console.log("cancel event fired"); });

	  $('#options1').click(function() {
		$('.dropdowncalendar').data('daterangepicker').setOptions(optionSet1, cb);
	  });

	  $('#options2').click(function() {
		$('.dropdowncalendar').data('daterangepicker').setOptions(optionSet2, cb);
	  });

	  $('#destroy').click(function() {
		$('.dropdowncalendar').data('daterangepicker').remove();
	  });
    });
</script>
</body>
</html>
