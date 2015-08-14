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
<link href="js/cadreCommittee/bootstrapDaterangepicker/daterangepicker-bs3.css" rel="stylesheet" type="text/css" />
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
								<img src='./images/icons/search.gif' class="offset7"  id="searchDataImgForMeetingsList" style="width:20px;height:20px;display:none;"/>
                            </h4>
                        </div>
                        <div class="panel-body">
                        	<div class="row">
                            	<div class="col-md-3">
                                	<label>Type Of Meeting</label>
                                    <select class="form-control" id="typeOfMeeting"></select>
									<img src='./images/icons/search.gif' class="offset7"  id="searchDataImgFortypeOfMeeting" style="width:20px;height:20px;display:none;"/>
                                </div>
                            	<!--<div class="col-md-3">
                                	<label>Meeting Location</label>
                                    <select class="form-control" id="meetingLocation"></select>
                                </div>-->
                            	<!--<div class="col-md-3">
                                	<label>Meeting Called By</label>
                                    <select class="form-control" disabled>
                                    	<option>District level</option>
                                    </select>
                                </div>-->
                            	<div class="col-md-3">
                                	<label>Select Date</label>
                                    <div class="input-group">
                                    	<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
                                        <input type="text" class="form-control" id="reportrange">
                                    </div>
								</div>
								<!--<div class="col-md-3">
                                	<label>Meeting End Date</label> 
									<div class="input-group">
                                    	<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
                                        <input type="text" class="form-control" id="endDate">
                                    </div>
								</div>-->	
								
                            </div>
							<div class="row m_top10" >
							<div class="col-md-3" id="stateShowId" style="display:none;">
                                	<label>State</label>
                                    <select class="form-control" id="statesDivId">
									<option>Select State</option>
									<option value="0">All</option>
									<option value="1">AndhraPradesh</option>
									<option value="36">Telangana</option>
									</select>
                            </div>
							<div class="col-md-1" style="height: 44px; width: 10px;">
								<img src='./images/icons/search.gif' class="offset7"  id="searchDataImgForDist" style="margin-left: -13px;margin-top: 30px;width:20px;height:20px;display:none;"/>
							</div>
							<div class="col-md-3" id="DistrictShowId" style="display:none;">
                                	<label>District</label>
                                    <select class="form-control" id="districtId">
									<option>Select District</option>
									</select>
                            </div>
							<div class="col-md-1" style="height: 44px; width: 10px;">
								<img src='./images/icons/search.gif' class="offset7"  id="searchDataImgForcons" style="margin-left: -13px;margin-top: 30px;width:20px;height:20px;display:none;"/>
							</div>
							<div class="col-md-3" id="ConstShowId" style="display:none;">
                                	<label>Constituency</label>
                                    <select class="form-control" id="constituencyId">
									<option>Select Constituency</option>
									</select>
                            </div>
							<div class="col-md-3" id="ManTwnDivShowId" style="display:none;">
                                	<label>Mandal/Town/Division</label>
                                    <select class="form-control" id="manTowDivId">
									<option>Select Mandal/Town/Division</option>
									</select>
                            </div>
							<div class="col-md-3" id="VillWardShowId" style="display:none;">
                                	<label>Village/Ward</label>
                                    <select class="form-control" id="villWardId">
									<option>Select Village/Ward</option>
									</select>
                            </div>
							</div>
							<div class="row col-xs-1 pull-right" >
							<button class="btn btn-success btn-sm btn-block" id="viewMeetings">View</button>
							</div>
							<div class="row m_top20" style="padding:10px;">
								<div id="meetingDetailsTableId"><img src='./images/icons/search.gif' class="offset7"  id="searchDataImgForResults" style="margin-left:0px;margin-top: 30px;width:20px;height:20px;display:none;"/></div>
                            	<!--<table class="m_top20 table table-bordered">
                                	<thead class="bg_d">
                                    	<th>Meeting Type</th>
                                        <th>Meeting Location</th>
										<th>Meeting Name</th>
                                        <th>Schedule Date</th>
                                        <th></th>
                                    </thead>
									<tbody id="">
									</tbody>-->
                                    <!--<tr>
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
                                    </tr>-->
                               <!-- </table>-->
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
<script src="js/cadreCommittee/bootstrapDaterangepicker/moment.min.js" type="text/javascript"></script>
<script src="js/cadreCommittee/bootstrapDaterangepicker/daterangepicker.js" type="text/javascript"></script>
<script src="dist/Timepicker/bootstrap-datetimepicker.min.js" type="text/javascript"></script>
<script type="text/javascript">
$(document).ready(function() {
	   /* $('#fromDate').daterangepicker(
	  {
		   singleDatePicker: true, 
		   timePicker: true,
		   format: 'DD/MM/YYYY h:mm A'
	  }, function(start, end, label) {
		//console.log(start.toISOString(), end.toISOString(), label);
	  }); 
	  
	  $('#endDate').daterangepicker(
	  {
		   singleDatePicker: true, 
		   timePicker: true,
		   format: 'DD/MM/YYYY h:mm A'
	  }, function(start, end, label) {
		//console.log(start.toISOString(), end.toISOString(), label);
	  });  */
	  
	  var cb = function(start, end, label) {
		 console.log(start.toISOString(), end.toISOString(), label);
		 }

		 var optionSet1 = {
		 startDate: moment().subtract(29, 'days'),
		 endDate: moment(),
		 minDate: '01/01/2012',
		 maxDate: '12/31/2015',
		 //dateLimit: { days: 60 },
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
		 applyClass: 'btn-small btn-primary newsSubmitBtn',
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
		 });
	  
	  
	getTheMeetingLevelDetails();
	getMeetingTypes();
});
   
   $("#mainheading").html("TRAINING PROGRAM");
</script>
<script>
	function getTheMeetingLevelDetails(){
		$("#searchDataImgForMeetingsList").show();
		var jsObj =	{}
		
		$.ajax(
		{
			type: "POST",
			url:"getTheMeetingLevelDetailsAction.action",
			data:{task :JSON.stringify(jsObj)}
		}
		).done(function(result){
			$("#meetingLocationLevel").append('<option value="0">Select Location Type</option>');
			if(result!=null && result.length>0){
				for(var i in result){
				$("#meetingLocationLevel").append('<option value="'+result[i].locationId+'">'+result[i].locationLevel+'</option>');
				}
			}
			$("#searchDataImgForMeetingsList").hide();
		});
	}
	
	function getMeetingTypes(){
		$("#searchDataImgFortypeOfMeeting").show();
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
			
			$("#searchDataImgFortypeOfMeeting").hide();
		});
	}
	
	$("#viewMeetings").click(function() {
		$("#searchDataImgForResults").show();
		if($("#meetingLocationLevel").val()==0){
			alert("Please Select Meeting Location");
			return;
		}
		var locationLevel = $("#meetingLocationLevel").val();
		
		if($("#typeOfMeeting").val()==0){
			alert("Pleas Select Meeting");
			return;
		}
		var meetingType = $("#typeOfMeeting").val();
		
		var sateId=0;
		var districtId=0;
		var constituencyId=0;
		var mandalTownDivisonId=0;
		var villageWardId =0;
		
		if(locationLevel==1){
			if($("#statesDivId").val()=="Select State"){
				alert("Please Select State");
				return;
			}else{
				sateId = $("#statesDivId").val();
			}
		}
		
		
		if(locationLevel==2){
			if($("#statesDivId").val()=="Select State"){
				alert("Please Select State");
				return;
			}else if($("#districtId").val()=="" || $("#districtId").val()=="Select District"){
				alert("Please Select District");
				return;
			}else{
				sateId = $("#statesDivId").val();
				districtId = $("#districtId").val();
			}
			
		}
		
		
		if(locationLevel==3){
			if($("#statesDivId").val()=="Select State"){
				alert("Please Select State");
				return;
			}else if($("#districtId").val()=="" || $("#districtId").val()=="Select District"){
				alert("Please Select District");
				return;
			}else if($("#constituencyId").val()=="" || $("#constituencyId").val()=="Select Constituency"){
				alert("Please Select Constituency");
				return;
			}else{
				sateId = $("#statesDivId").val();
				districtId = $("#districtId").val();
				constituencyId = $("#constituencyId").val();
			}
		}	
		
		
		if(locationLevel==4){
			if($("#statesDivId").val()=="Select State"){
				alert("Please Select State");
				return;
			}else if($("#districtId").val()=="" || $("#districtId").val()=="Select District"){
				alert("Please Select District");
				return;
			}else if($("#constituencyId").val()=="" || $("#constituencyId").val()=="Select Constituency"){
				alert("Please Select Constituency");
				return;
			}else if($("#manTowDivId").val()=="" || $("#manTowDivId").val()=="Select Mandal/Town/Division"){
				alert("Please Select Mandal/Town/Division");
				return;
			}else{
				sateId = $("#statesDivId").val();
				districtId = $("#districtId").val();
				constituencyId = $("#constituencyId").val();
				mandalTownDivisonId = $("#manTowDivId").val();
			}
		}
		
		
		if(locationLevel==5){
			if($("#statesDivId").val()=="Select State"){
				alert("Please Select State");
				return;
			}else if($("#districtId").val()=="" || $("#districtId").val()=="Select District"){
				alert("Please Select District");
				return;
			}else if($("#constituencyId").val()=="" || $("#constituencyId").val()=="Select Constituency"){
				alert("Please Select Constituency");
				return;
			}else if($("#manTowDivId").val()=="" || $("#manTowDivId").val()=="Select Mandal/Town/Division"){
				alert("Please Select Mandal/Town/Division");
				return;
			}else if($("#villWardId").val()=="" || $("#villWardId").val()=="Select Village/Ward"){
				alert("Please Select Village/Ward");
				return;
			}else{
				sateId = $("#statesDivId").val();
				districtId = $("#districtId").val();
				constituencyId = $("#constituencyId").val();
				mandalTownDivisonId = $("#manTowDivId").val();
				villageWardId = $("#villWardId").val();
			}
		}
		
				
		var startDate = $(".dp_startDate").val();
		var endDate = $(".dp_endDate").val();
		var jsObj =	{
						meetingType:meetingType,
						locationLevel:locationLevel,
						sateId:sateId,
						districtId:districtId,
						constituencyId:constituencyId,
						mandalTownDivisonId:mandalTownDivisonId,
						villageWardId:villageWardId,
						startDate:startDate,
						endDate:endDate
					}
					
		$.ajax(
		{
			type: "POST",
			url:"getAllMeetingsAction.action",
			data:{task :JSON.stringify(jsObj)}
		}
		).done(function(result){
			var str='';
			if(result!=null && result.length>0){
				str+='<table class="m_top20 table table-bordered">';
				str+='<thead class="bg_d">';
				str+='<th>Meeting Type</th>';
				str+='<th>Meeting Location</th>';
				str+='<th>Meeting Name</th>';
				str+='<th>Schedule Date</th>';
				str+='<th></th>';
				str+='</thead>';
				str+='<tbody id="">';
				for(var i in result){
					str+='<tr>';
					str+='<td>'+result[i].meetingType+'</td>';
					str+='<td>'+result[i].location+'</td>';
					str+='<td>'+result[i].meetingName+'</td>';
					str+='<td>'+result[i].startTime+' to '+result[i].endTime+'</td>';
					str+='<td><button class="btn btn-success btn-sm" onclick="updateMeeting(\''+result[i].partyMeetingId+'\');">UPDATE</button></td>';
				}
				str+='</tbody>';
				str+='</table>';
			}else{
				str+='No Meetings Found';
			}
			$("#searchDataImgForResults").hide();
			$('#meetingDetailsTableId').html(str);
		});
	});
	function getDistrictsForStates(state){
		
	$("#searchDataImgForDist").show();
	
	$("#districtId  option").remove();
	$("#districtId").append('<option value="">Select District</option>');
	$("#constituencyId  option").remove();
	$("#constituencyId").append('<option value="">Select Constituency</option>');
	$("#manTowDivId  option").remove();
	$("#manTowDivId").append('<option value="">Select Mandal/Town/Divison</option>');
	$("#villWardId  option").remove();
	$("#villWardId").append('<option value="">Select Village/Ward</option>');
   var jsObj=
   {				
				stateId:state,
				elmtId:"districtList_d",
                type:"default",
				task:"getDistrictsForState"				
	}
    $.ajax({
          type:'GET',
          url: 'getDistrictsForStateAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
	   $("#searchDataImgForDist").hide();
     for(var i in result){
	   if(result[i].id == 0){
          $("#districtId").append('<option value='+result[i].id+'>ALL</option>');
	   }else{
	      $("#districtId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
	   }
	 }
   });
  }
	function getConstituenciesForDistricts(district){
	    $("#searchDataImgForcons").show();
		$("#constituencyId  option").remove();
		$("#constituencyId").append('<option value="">Select Constituency</option>');
		
		var jsObj={				
			districtId:district,
			elmtId:"districtList_d",
            type:"default",
			task:"getConstituenciesForDistricts"				
		}
		$.ajax({
			  type:'GET',
			  url: 'getConstituenciesForADistrictAjaxAction.action',
			  dataType: 'json',
			  data: {task:JSON.stringify(jsObj)}
	   }).done(function(result){
		   $("#searchDataImgForcons").hide();
			for(var i in result){
			   if(result[i].id == 0){
				  $("#constituencyId").append('<option value='+result[i].id+'>ALL</option>');
			   }else{
				  $("#constituencyId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
			   }
			}
		});
	}
	$( "#statesDivId" ).change(function() {
		getDistrictsForStates(this.value);
	});
	$( "#districtId" ).change(function() {
		getConstituenciesForDistricts(this.value);
	});
$("#meetingLocationLevel").change(function(){
	if($("#meetingLocationLevel").val()== 2 ){
		$("#stateShowId").show();
		$("#DistrictShowId").show();
		$("#ConstShowId").hide();
		$("#ManTwnDivShowId").hide();
		$("#VillWardShowId").hide();
		
	}else if($("#meetingLocationLevel").val()== 3){
		$("#stateShowId").show();
		$("#DistrictShowId").show();
		$("#ConstShowId").show();
		$("#ManTwnDivShowId").hide();
		$("#VillWardShowId").hide();
		
	}else if($("#meetingLocationLevel").val()== 4){
		$("#stateShowId").show();
		$("#DistrictShowId").show();
		$("#ConstShowId").show();
		$("#ManTwnDivShowId").show();
		$("#VillWardShowId").hide();
		
	}else if($("#meetingLocationLevel").val()== 5){
		$("#stateShowId").show();
		$("#DistrictShowId").show();
		$("#ConstShowId").show();
		$("#ManTwnDivShowId").show();
		$("#VillWardShowId").show();
		
	}else if($("#meetingLocationLevel").val()== 1){
	
			$("#stateShowId").show();
			$("#DistrictShowId").hide();
			$("#ConstShowId").hide();
			$("#ManTwnDivShowId").hide();
			$("#VillWardShowId").hide();
			
	}else{
		
	}
	
		});
		
		$("#constituencyId").change(function(){
			getMandalVillageDetails(4);
		});
		$("#manTowDivId").change(function(){
			getMandalVillageDetails(5);
		});
		
	function getMandalVillageDetails(locationLevel){
		//$("#manTowDivId  option").remove();
		//$("#villWardId option").remove();
		var stateId = $("#statesDivId").val();
		var districtId = $("#districtId").val();
		var constituencyId = $("#constituencyId").val();
		var mandalId = "0";
		if(locationLevel==4){
			$("#manTowDivId").html("")
		}
		if(locationLevel==5){
			mandalId = $("#manTowDivId").val();
			$("#villWardId").html("");
		}
		
	   var jsObj={				
			stateId : stateId,
			districtId : districtId,
			constituencyId : constituencyId,//228
			mandalId : mandalId,
			locationLevel : locationLevel,
			task:"getConstituenciesForDistricts"
		}
		$.ajax({
			  type:'GET',
			  url: 'getSubLevelLctnsForConstituencyAndMandalAction.action',
			  dataType: 'json',
			  data: {task:JSON.stringify(jsObj)}
	   }).done(function(result){
			  var divId = "#manTowDivId";
			  $(divId).append("<option value=''>Select Mandal/Town/Divison</option>");
			  if(locationLevel ==5){
				  divId = "#villWardId";
				  $(divId).append("<option value=''>Select Village/Ward</option>");
			  }
			  
			  $(divId).append("<option value='0'>ALL</option>");
			  
			for(var i in result){
				$(divId).append('<option value='+result[i].locationId+'>'+result[i].locationName+'</option>');
			}
		});
	}
		
	function updateMeeting(meetingMeetingId){
		window.open('meetingDetailsList.action?meetingMeetingId='+meetingMeetingId+'', '_blank');
		//alert(meetingType+"--"+meetingLocationLevel);
	}
</script>
</body>
</html>