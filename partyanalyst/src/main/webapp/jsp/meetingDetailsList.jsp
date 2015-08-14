<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Meeting</title>

<link type="text/css" href="dist/css/bootstrap.css" rel="stylesheet" />
<link href="http://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
<link href="dist/daterange/daterangepicker-bs3.css" type="text/css" rel="stylesheet">
<link href="css/Training/scroll/jquery.mCustomScrollbar.css" rel="stylesheet" type="text/css"/>
<link href="dist/Timepicker/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css">
<link href="css/Training/css/basic.css" rel="stylesheet" type="text/css">
<link href="css/Training/css/dropzone.css" rel="stylesheet" type="text/css">
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
                        	<h4 class="panel-title" id="meetingType"></h4><h6 id="location"></h6>
                        </div>
                        <div class="panel-body">
                        	<div class="row">
                            	<!--<div class="col-md-3">
                                	<label>Type Of Meeting</label>
                                    <select class="form-control" disabled>
                                    	<option>District level</option>
                                    </select>
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
                                </div>-->
                                <div class="col-md-6 m_top20">
                                	<div class="panel panel-default">
                                    	<div class="panel-heading">
                                        	<h4 class="panel-title">Meeting Minutes</h4>
                                        </div>
                                        <div class="panel-body">
                                        	<!-- <div class="input-group bin-div">
                                            	<input type="text" class="form-control">
                                                <span class="input-group-addon">
                                                	<i class="glyphicon glyphicon-trash"></i>
                                                </span>
                                            </div>
                                            <div class="input-group bin-div m_top10">
                                            	<input type="text" class="form-control">
                                                <span class="input-group-addon">
                                                	<i class="glyphicon glyphicon-trash"></i>
                                                </span>
                                            </div>
                                            <div class="input-group bin-div m_top10">
                                            	<input type="text" class="form-control">
                                                <span class="input-group-addon">
                                                	<i class="glyphicon glyphicon-trash"></i>
                                                </span>
                                            </div>
                                            <div class="pull-right m_top10">
                                            	<button class="btn btn-success btn-xs" id="add-fields">ADD</button>
                                            </div> -->
											
											<div id="addMoreDiv">
												<div class="input-group bin-div m_top10" id="list1">
													<input type="text" class="form-control" id="minutes1"></input>
													<span class="input-group-addon trash" attr_txt="minutes1">
														<i class="glyphicon glyphicon-trash"></i>
													</span>
												</div>
											</div>	
											
												<div class="input-group bin-div m_top10" id="list" style="display:none;">
													<input type="text" class="form-control txtbox"></input>
													<span class="input-group-addon trash">
														<i class="glyphicon glyphicon-trash"></i>
													</span>
												</div>
											
											<button class="btn btn-success btn-xs pull-right m_top20" onclick="myFunction()">ADD</button>
											
                                            <div class="row">
                                            	<div class="col-md-12">
                                                	<h4><i class="icon-upload"></i> Upload File</h4>
                                                    <p class="m_0">Drag and drop file below box or click on box to upload file</p>
                                            		<form id="my-awesome-dropzone" action="/target" class="dropzone"></form>
                                                    <p class="m_0 font-10 pull-right">Note: Multiple files upload. Allowed Types: PDF,Word,Excel,Jpg,JPEG,PNG</p>
                                            	</div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                 <div class="col-md-6 m_top20">
                                	<div class="panel panel-default">
                                    	<div class="panel-heading">
                                        	<h4 class="panel-title">ATR</h4>
                                        </div>
                                        <div class="panel-body">
                                        	<div class="row">
                                                <div class="col-md-12">
                                                    <label>ATR Raised by</label>
                                                    <select class="form-control">
                                                        <option>Kavali</option>
                                                    </select>
                                                </div>
                                                <div class="col-md-12 m_top20">
                                                    <label>Grievance Given</label>
                                                    <select class="form-control">
                                                        <option>Kavali</option>
                                                    </select>
                                                </div>
                                                <div class="col-md-12 m_top20">
                                                    <label>Action Taken</label>
                                                    <select class="form-control">
                                                        <option>Kavali</option>
                                                    </select>
                                                </div>
                                            </div>
                                            
                                            <div class="pull-right m_top10">
                                            	<button class="btn btn-success btn-xs">ADD</button>
                                            </div>
                                            <div class="row">
                                            	<div class="col-md-12">
                                                	<div class="grievance-training">
                                                        <ul>
                                                            <li>
                                                            	<button class="btn btn-custom-g btn-xs">Edit</button>
                                                             	<div class="data">
	                                                                <p class="m_0"><span class="text-bold">ATR Raised by</span> - kavali</p>   
                                                                    <p><span class="text-bold">Grievance Given:</span>
                                                                    Grievance text</p>
                                                                    <p class="m_top10"><span class="text-bold">Action Taken:</span>
                                                                    Grievance text</p>
                                                                </div>
                                                                <div class="data-edit">
                                                                	<div class="row">
                                                                        <div class="col-md-12">
                                                                            <label>ATR Raised by</label>
                                                                            <select class="form-control">
                                                                                <option>Kavali</option>
                                                                            </select>
                                                                        </div>
                                                                        <div class="col-md-12 m_top20">
                                                                            <label>Grievance Given</label>
                                                                            <select class="form-control">
                                                                                <option>Kavali</option>
                                                                            </select>
                                                                        </div>
                                                                        <div class="col-md-12 m_top20">
                                                                            <label>Action Taken</label>
                                                                            <select class="form-control">
                                                                                <option>Kavali</option>
                                                                            </select>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                             </li>
                                                            <li>
                                                            	<button class="btn btn-custom-g btn-xs">Edit</button>
                                                             	<div class="data">
	                                                                <p class="m_0">ATR Raised by - kavali</p>   
                                                                    <p><span class="text-bold">Grievance Given:</span>
                                                                    Grievance text</p>
                                                                    <p class="m_top10"><span class="text-bold">Action Taken:</span>
                                                                    Grievance text</p>
                                                                </div>
                                                             </li>
                                                        </ul>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                            	<div class="col-md-12">
                                                	<h4><i class="icon-upload"></i> Upload File</h4>
                                                    <p class="m_0">Drag and drop file below box or click on box to upload file</p>
                                            		<form action="/target" class="dropzone"></form>
                                                    <p class="m_0 font-10 pull-right">Note: Multiple files upload. Allowed Types: PDF,Word,Excel,Jpg,JPEG,PNG</p>
                                            	</div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
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
<script src="js/TrainingProgram/component.js" type="text/javascript"></script>
<script src="js/TrainingProgram/fileupload.js" type="text/javascript"></script>
<script src="dist/Timepicker/bootstrap-datetimepicker.min.js" type="text/javascript"></script>

<script type="text/javascript">

	$('.btn-custom-g').click(function(){
		$('.data-edit').show();
		$('.data').hide();
	});
	
	$('#add-fields').click(function(){
		$('div.bin-div').show();
	});
	
	$(document).ready(function() {
	  $('#birthday').daterangepicker(
	  {
		   singleDatePicker: true, 
		   timePicker: true,
		   format: 'DD/MM/YYYY h:mm A'
	  }, function(start, end, label) {
		console.log(start.toISOString(), end.toISOString(), label);
	  });
   });
			   
	$("#mainheading").html("TRAINING PROGRAM");
	
	var mainDivCount=1;
	function myFunction() {
		mainDivCount = parseInt(mainDivCount)+1;
		var c = $("#list").clone(true);
			c.removeAttr("style");
			c.attr("id","list"+mainDivCount)
			c.find(".txtbox").attr("id","minutes"+mainDivCount); 
			c.find(".trash").attr("attr_txt","list"+mainDivCount)
		$("#addMoreDiv").append(c);
	}
	
	$(".trash").click(function(){
		var divId = $(this).attr("attr_txt");
		$("#"+divId).remove();
		$(this).remove();
	});
	
</script>
</body>
</html>
