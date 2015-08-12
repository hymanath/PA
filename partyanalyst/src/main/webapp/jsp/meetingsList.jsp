<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Undialled List</title>

<link type="text/css" href="dist/css/bootstrap.css" rel="stylesheet" />
<link href="css/Training/scroll/jquery.mCustomScrollbar.css" rel="stylesheet" type="text/css"/>
<link href="http://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
<link href="dist/DateRange/daterangepicker.css" rel="stylesheet" type="text/css">
<link href="dist/Timepicker/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css">
<link href="dist/daterange/daterangepicker-bs3.css" type="text/css" rel="stylesheet">
<link href="dist/Icomoon/style.css" rel="stylesheet" type="text/css">


<link href="dist/css/custom.css" rel="stylesheet" type="text/css">
<style>
header.eventsheader { 
 background:url("dist/img/header-footer.png") no-repeat scroll center bottom / 100% auto #fed501;
 background-origin: border-box;
 background-repeat: no-repeat;
 height: 71px; 
}
</style>

</head>
<body>
<header  class="eventsheader">
<!-- <img src="css/Training/img/header.jpg" width="100%"> -->
	<div class="container">
        <div class="row">
            <div class="col-md-2 col-xs-4 col-sm-1">
                <img src="dist/img/logo.png" class="img-responsive">
            </div>
            <div class="col-md-1 col-xs-1 col-sm-1">
                <img src="dist/img/CBN1.png" class="img-responsive">
            </div>
            <div class="col-md-6 col-xs-7 col-sm-7 text-center">               
                 <p class="header-text display-style" id="mainheading" style="font-size:38px;"></p>               
            </div>
            <div class="col-md-1 col-xs-1 col-sm-1"><img src="dist/img/NTR1.png" class="img-responsive" />   
            </div>
			<div class="col-md-2 col-xs-1 col-sm-1">
				<div class="" style="color:white;margin-top: 5px;"><b> Welcome ${sessionScope.UserName} </b></div>
                    <a href="#" class="dropdown-toggle btn btn-default btn-xs m_top10" data-toggle="dropdown" aria-expanded="false" style="margin-top: 5px;">
                    Menu <img src="images/menu_icon.png" />
                    </a>
					<ul class="dropdown-menu" role="menu" aria-labelledby="drop6" style="    background-color: rgb(239, 64, 54);">
					   <!--<li><a href="mahanaduCadreVisitInfoAction.action"><span>ENTRY/EXIT DASHBOARD</span></a> </li>-->
					   <li><a href="dashBoardAction.action"><span>DASHBOARD</span></a> </li>
					   <li><a tabindex="-1" href="newlogoutAction.action">Sign Out</a></li>
					
                    </ul>   
            </div>			
        </div>       
    </div>
	
	
</header>
<main>
	<div class="container">
    	<div class="row">
        	<section>
            	<div class="col-md-12">
                	<div class="panel panel-default">
                    	<div class="panel-heading">
                        	<h4 class="panel-title">Meetings List
                            	<span class="pull-right">
                                	<select id="meetingLocationLevel"></select>
                                </span>
                            </h4>
                        </div>
                        <div class="panel-body">
                        	<div class="row">
                            	<div class="col-md-3">
                                	<label>Type Of Meeting</label>
                                    <select class="form-control" id="typeOfMeeting"></select>
                                </div>
                            	<div class="col-md-3">
                                	<label>Meeting Location</label>
                                    <select class="form-control" disabled>
                                    	<option>District level</option>
                                    </select>
                                </div>
                            	<div class="col-md-3">
                                	<label>Meeting Called By</label>
                                    <select class="form-control" disabled>
                                    	<option>District level</option>
                                    </select>
                                </div>
                            	<div class="col-md-3">
                                	<label>Meeting Date & TIme</label>
                                    <div class="input-group">
                                    	<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
                                        <input type="text" class="form-control" id="birthday">
                                    </div>
                                </div>
                            </div>
                        	<div class="m_top20">
                            	<table class="table table-bordered">
                                	<thead class="bg_d">
                                    	<th>Meeting Name</th>
                                        <th>Meeting Location</th>
                                        <th>Schedule Date</th>
                                        <th></th>
                                    </thead>
                                    <tr>
                                    	<td>General Body Meeting</td>
                                        <td>Kandukur</td>
                                        <td>Aug-25-2015</td>
                                        <td><button class="btn btn-success btn-sm">UPDATE</button></td>
                                    </tr>
                                    <tr>
                                    	<td>Party Presentative Meeting</td>
                                        <td>Kandukur</td>
                                        <td>Aug-25-2015</td>
                                        <td><button class="btn btn-success btn-sm">UPDATE</button></td>
                                    </tr>
                                    <tr>
                                    	<td>Party Presentative Meeting</td>
                                        <td>Kandukur</td>
                                        <td>Aug-25-2015</td>
                                        <td><button class="btn btn-success btn-sm">UPDATE</button></td>
                                    </tr>
                                    <tr>
                                    	<td>Party Presentative Meeting</td>
                                        <td>Kandukur</td>
                                        <td>Aug-25-2015</td>
                                        <td><button class="btn btn-success btn-sm">UPDATE</button></td>
                                    </tr><tr>
                                    	<td>Party Presentative Meeting</td>
                                        <td>Kandukur</td>
                                        <td>Aug-25-2015</td>
                                        <td><button class="btn btn-success btn-sm">UPDATE</button></td>
                                    </tr>
                                    <tr>
                                    	<td>Party Presentative Meeting</td>
                                        <td>Kandukur</td>
                                        <td>Aug-25-2015</td>
                                        <td><button class="btn btn-success btn-sm">UPDATE</button></td>
                                    </tr>
                                    <tr>
                                    	<td>Party Presentative Meeting</td>
                                        <td>Kandukur</td>
                                        <td>Aug-25-2015</td>
                                        <td><button class="btn btn-success btn-sm">UPDATE</button></td>
                                    </tr>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </div>
    </div>
</main>
<footer>
		<img src="css/Training/img/footer.jpg" width="100%">
</footer>
<script src="dist/js/jquery-1.11.2.min.js" type="text/javascript"></script>
<script src="dist/js/bootstrap.js" type="text/javascript"></script>
<script src="dist/DateRange/moment.js" type="text/javascript"></script>
<script src="dist/DateRange/daterangepicker.js" type="text/javascript"></script>
<script src="dist/Timepicker/bootstrap-datetimepicker.min.js" type="text/javascript"></script>
<script type="text/javascript">
$(document).ready(function() {
	  $('#birthday').daterangepicker(
	  {
		   singleDatePicker: true, 
		   timePicker: true,
		   format: 'DD/MM/YYYY h:mm A'
	  }, function(start, end, label) {
		console.log(start.toISOString(), end.toISOString(), label);
	  });
	  
	  getTheMeetingLevelDetails();
	  getMeetingTypes();
   });
   
   $("#mainheading").html("TRAINING PROGRAM");
</script>
<script>
	function getTheMeetingLevelDetails(){
		var jsObj =	{}
		
		$.ajax(
		{
			type: "POST",
			url:"getTheMeetingLevelDetailsAction.action",
			data:{task :JSON.stringify(jsObj)}
		}
		).done(function(result){
			if(result!=null && result.length>0){
				for(var i in result){
				$("#meetingLocationLevel").append('<option value="'+result[i].locationId+'">'+result[i].locationLevel+'</option>');
				}
			}
			
		});
	}
	
	function getMeetingTypes(){
		var jsObj =	{}
		
		$.ajax(
		{
			type: "POST",
			url:"getMeetingTypesAction.action",
			data:{task :JSON.stringify(jsObj)}
		}
		).done(function(result){
			$("#typeOfMeeting").append('<option value="0">Select Meeting Type</option>');
			if(result!=null && result.length>0){
				for(var i in result){
					$("#typeOfMeeting").append('<option value="'+result[i].id+'">'+result[i].meetingType+'</option>');
				}
			}
			
			
		});
	}
	
	$("#typeOfMeeting").change(function() {
		var meetingType = $(this).val();
		var locationLevel = $("#meetingLocationLevel").val();
		var meetingLocation = 0;
		var jsObj =	{
						meetingType:meetingType,
						locationLevel:locationLevel,
						meetingLocation:meetingLocation
					}
					
		$.ajax(
		{
			type: "POST",
			url:"getAllMeetingsAction.action",
			data:{task :JSON.stringify(jsObj)}
		}
		).done(function(result){
			
		});
	});
	
</script>
</body>
</html>