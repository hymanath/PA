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
#trigger{
  font-size: 12px !important;
    height: 15px;
    line-height: 15px;
    padding: 3px;
}
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

.slimscrollar
{
height:425px;
overflow-y:scroll;
}

#districtTableId_length{
display:none;
}

#districtWiseProgressBars,#districtTableId_filter{font-size:10px !important;font-family:verdana;font-weight:bold;}
#districtTableId tr.odd td.sorting_1 {
    background-color: #d3d3d3 !important;
}
#districtTableId tr.even td.sorting_1 {
    background-color: #fafafa !important;
}
#districtTableId tr.odd {
    background-color: #f3f3f3 !important;
}
.prev {
    background: url("") no-repeat scroll -272px top rgba(0, 0, 0, 0) !important;
	}
.next {
    background: url("") no-repeat scroll -290px top rgba(0, 0, 0, 0) !important;
	}
</style>

<!-- <link href="js/cadreCommittee/bootstrapDaterangepicker/daterangepicker-bs3.css" rel="stylesheet" />-->
<link href="css/daterangepicker-bs2.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"/> 
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
                            <input type="radio" name="select" id="AllId" class="stateRd" value="0" checked="true"/>
                                All
                        </label>
                    	<label class="radio inline">
                            <input type="radio" name="select" id="APId" class="stateRd" value="1" />
                                Andhra Pradesh
                        </label>
                    	<label class="radio inline">
                            <input type="radio" name="select" id="TSId" class="stateRd" value="2"/>
                                Telangana
                        </label>
                    
					
					<div id="reportrange" class="pull-right" style="background:rgba(0,0,0,0.1); cursor: pointer; padding: 5px 10px; border: 1px solid rgba(0,0,0,0.1);">
					  <i class="icon icon-calendar"></i>
					  <span id="selectedDate"></span> <b class="caret" style="margin-top: 9px;"></b>				
					</div>
        </div>
                    </td>
                </tr>
                <tr>
                	<td width="47%" style="text-align:center;padding:0px;">
                    	<div id="chartdiv" style="margin-bottom:-80px;margin-top:-20px"></div>
                        <h5 style="margin-bottom:0px;">TOTAL MISSED CALLS</h5>
                       <center><img class="imgStyle totalData" src="images/icons/search.gif"></img><h3 style="margin-top:0px;" id="totalMissedCallsId"></h3></center>
                        <table class="table table-bordered" style="margin-bottom:0px;">
                        	<tr style="word-wrap:break-word">
                            	<td>
                                <small>
                                	<b>Numbers Mismatched Cadre Missed Calls</b> 
                                </small>
                                <center><img class="imgStyle totalData" src="images/icons/search.gif"></img><h3 id="mismatchedId"></h3></center>
                                </td>
                                <td>
                                <small>
                                	<b>Single Member Registered Numbers</b>
                                </small>
                                <center><img class="imgStyle totalData" src="images/icons/search.gif"></img><h3 id="singleRegId"></h3></center>
                                </td>
                                <td>
                                <small>
                                	<b>Multi Member Registered Numbers</b>
                                </small>
                                <center><img class="imgStyle totalData" src="images/icons/search.gif"></img><h3 id="multiRegId"></h3></center>
                                </td>
                            </tr>
                        </table>
                    </td>
                    <td width="53%" style="padding:0px">
					<div>
						<h6 id="textId" style="border:1px solid #dddddd;background-color:#f4f4f4;padding:5px;margin:0px">ANDHRA PRADESH & TELANGANA DISTRICT WISE MISSED CALLS PERCENTAGE</h6>
                    	<div style="padding:2px;font-size:10px;">
                           
							<label class="radio inline">
							<input type="radio" id="single" style="vertical-align: text-bottom;" class="districtRd" value="1" name="radionBtn" checked="true"/>
							<small>Based on Single Member</small>
							</label>
							<label class="radio inline">
							<input type="radio" type="radio"  id="multi" style="vertical-align: text-bottom;" class="districtRd" value="0" name="radionBtn" />
							<small>Based on Multi Member</small>
							</label>
                        </div>
						<center><img src="images/Loading-data.gif" class="offset7"  id="distProcessImgId" style=" margin-left:0px;margin-top: 130px;width:70px;height:60px;display:none;overflow: auto;"/></center>
						<div id="districtWiseProgressBars"  class="scrollable_div" style="margin-top: 15px; max-height:460px;"></div>
					</div>
                    		
                    </td>
                    <!--<td style="background-color:#f4f4f4">
                    	<label class="label label-custom  allIconId">Clear Filters</label>
						<ul style="margin-top:20px;">
                        	<li style="cursor:pointer;border-bottom:1px solid #dddddd;margin-bottom:10px;" class="percentagefilter1" attr-id="vGood">
                            	<div style="border-radius:2px !important;margin-bottom:0px;width:30px;height:20px !important;" class="progress progress-success progress-striped active">
                                  <div class="bar" style="width:100%"></div>
                                </div>
                                <span >VERY GOOD [>60%]</span>
                            </li>
                        	<li style="cursor:pointer;border-bottom:1px solid #dddddd;margin-bottom:10px;" class="percentagefilter1" attr-id="good">
                            	<div style="border-radius:2px !important;margin-bottom:0px;width:30px;height:20px !important;" class="progress progress-info progress-striped active">
                                  <div class="bar" style="width:100%"></div>
                                </div>
                                <span>GOOD [41-60%]</span>
                            </li>
                        	<li style="cursor:pointer;border-bottom:1px solid #dddddd;margin-bottom:10px;" class="percentagefilter1" attr-id="ok">
                            	<div style="border-radius:2px !important;margin-bottom:0px;width:30px;height:20px !important;" class="progress progress-primary progress-striped active">
                                  <div class="bar" style="width:100%"></div>
                                </div>
                                <span>OK [21-40%]</span>
                            </li>
                        	<li style="cursor:pointer;border-bottom:1px solid #dddddd;margin-bottom:10px;" class="percentagefilter1" attr-id="poor">
                            	<div style="border-radius:2px !important;margin-bottom:0px;width:30px;height:20px !important;" class="progress progress-warning progress-striped active">
                                  <div class="bar" style="width:100%"></div>
                                </div>
                                <span>POOR [0-20%]</span>
                            </li>
                        	<li style="cursor:pointer;border-bottom:1px solid #dddddd;margin-bottom:10px;" class="percentagefilter1" attr-id="vPoor">
                            	<div style="border-radius:2px !important;margin-bottom:0px;width:30px;height:20px !important;" class="progress progress-danger progress-striped active">
                                  <div class="bar" style="width:100%"></div>
                                </div>
                                <span>VERY POOR [0-10%]</span>
                            </li>
                            <li>
                            	<b><i><u>NOTE:</u>Click to View percentage range Wise details</i></b>
                          </li>
                        </ul>
                    </td>-->
                </tr>
            </table>
        <div style="border:3px solid #CCC">
        	<img src="images/Loading-data.gif" class="offset7"  id="processImgId" style=" margin-left:419px;margin-top: 20px;width:70px;height:60px;display:none;"/>
            <div id="constituenciesDiv"></div>
        </div>
    </div>
</section>


<script src="js/jquery-1.11.2.min.js" type="text/javascript"></script>
<script type="text/javascript" src="js/amcharts/amcharts.js"></script>
<script type="text/javascript" src="js/amcharts/pie.js"></script>
<script type="text/javascript" src="js/amcharts/none.js"></script>
<script type="text/javascript" src="js/jquery.slimscroll.min.js"></script>
<script src="js/cadreCommittee/bootstrapDaterangepicker/moment.min.js" type="text/javascript"></script> 
<script src="js/cadreCommittee/bootstrapDaterangepicker/daterangepicker.js" type="text/javascript"></script>   
<script type="text/javascript" src="js/jquery.dataTables.js"></script>
<link rel="stylesheet" type="text/css" href="js/scrollator/fm.scrollator.jquery.css">	
<script type="text/javascript" src="js/scrollator/fm.scrollator.jquery.js"></script>

<script type="text/javascript">
	$("#trigger").parent().addClass("span0")
	$("#trigger").parent().removeClass("span3")
	$('.scrollable_div').scrollator();

	 $(document).ready(function() {
                  var cb = function(start, end, label) {
                    console.log(start.toISOString(), end.toISOString(), label);
                    $('#reportrange span').html(start.format('MMMM D, YYYY') + ' - ' + end.format('MMMM D, YYYY'));
                  
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
                    applyClass: 'btn-small btn-success rangeButton',
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

                  $('#reportrange').on('show.daterangepicker', function() { console.log(""); });
                  $('#reportrange').on('hide.daterangepicker', function() { console.log(""); });
                  $('#reportrange').on('apply.daterangepicker', function(ev, picker) { 
                    console.log("apply event fired, start/end dates are " 
                      + picker.startDate.format('MMMM D, YYYY') 
                      + " to " 
                      + picker.endDate.format('MMMM D, YYYY')
                    ); 
                  });
                  $('#reportrange').on('cancel.daterangepicker', function(ev, picker) { console.log(""); });		
					getMissedCallDetails();
					getSingleMemberCountByDistrict();
					//getMissedCallDetailsByDistrict();
               });
			   
function getMissedCallDetails(){
	$("#multiRegId,#singleRegId,#mismatchedId,#totalMissedCallsId").html("");
	var stateId = $("input[type='radio'][name='select']:checked").val();	
	var startDate=$(".dp_startDate").val();
	var endDate=$(".dp_endDate").val();
	$(".totalData").show();
	var jobj = {
		fromDate:startDate,
		toDate:endDate,
		stateId:stateId
	}				
	$.ajax({
	  type:'GET',
	  url: 'getMissedCallDetailsAction.action',
	  data : {task:JSON.stringify(jobj)} ,
	}).done(function(result){
		$(".totalData").hide();
		$("#totalMissedCallsId").html(result.totalCount);
		if(stateId == 0){
		$("#mismatchedId").html(result.mismatchedCnt);
		}else{
		$("#mismatchedId").html("NA");
		}
		$("#singleRegId").html(result.singleMemberRegCnt);
		$("#multiRegId").html(result.multiMemberRegCnt);
	});
}

function getMissedCallDetailsByDistrict(){

	$("#districtWiseProgressBars").html("");
	//$("#chartdiv").html("");
	$("#distProcessImgId").show();
	
	var stateId = $("input[type='radio'][name='select']:checked").val();	
	var startDate=$(".dp_startDate").val();
	var endDate=$(".dp_endDate").val();
	var jObj = {
		fromDate:startDate,
		toDate:endDate,
		stateId:stateId,
		task:"getMissedCallDetails"
	}				
	$.ajax({
	  type:'GET',
	  url: 'getMissedCallDetailsDistrictWiseAction.action',
	  data : {task:JSON.stringify(jObj)} ,
	}).done(function(result){
	chartData = new Array();
	if(result != null && result.length >0){
	/*$("#chartdiv").show();
		for (var i in result) {      
			chartData.push({
				title: result[i].name,
				value: result[i].districtCount
			});
		}
		var chart = AmCharts.makeChart("chartdiv", {
		"type": "pie",
		"theme": "none",			
		"titleField": "title",
		"valueField": "value",
		"labelRadius": 5,
		"radius": "30%",
		"innerRadius": "60%",
		"labelText": "[[]]",
		"dataProvider": chartData
		});
		}
		else{
		$("#chartdiv").hide();
		}*/
		buildDistrictWiseCount(result,"multi",stateId);
		}
	});
	
	
	
}

function buildDistrictWiseCount(result,type,stateId){
		$("#distProcessImgId").hide();
		$("#districtWiseProgressBars").html("");
		var str ='';
		str+='';
		
		if(stateId == 1){
		$("#textId").html("ANDHRA PRADESH DISTRICT WISE MISSED CALLS PERCENTAGES");
		}
		else if(stateId == 2){
		$("#textId").html("TELANGANA DISTRICT WISE MISSED CALLS PERCENTAGES");
		
		}
		
       
		/*str+='<ul  class="slimscrollar" style="padding:8px;margin-top:5px;border-top:1px solid #dddddd">';	
		if(result != null && result.length > 0){
		for(var i in result){
		var perc = result[i].districtCount / result[i].totalCount * 100 ;		
		var perc1 = perc.toFixed(2);
		
			
				if(perc1 <= 10){
					
					str+='<li class="vPoor">';
					//str+='<span class="progresslabelcolor" style="background-color:#0F0">';
					str+='</span><span id="'+result[i].districtId+'" style="cursor:pointer" onClick="missedCallDetailsForADistrict('+result[i].districtId+');">'+result[i].name+'</span>( '+perc1+'%) '+result[i].districtCount+'<span style="float:right"> Registered Count :'+result[i].totalCount+'</span>';
					str+='<div class="progress progress-danger  progress-striped active">';	
					
				}else if(perc1 > 10 && perc1 <= 20 ){
				
					str+='<li class="poor">';
					//str+='<span class="progresslabelcolor" style="background-color:#0F0">';
					str+='</span><span id="'+result[i].districtId+'" style="cursor:pointer" onClick="missedCallDetailsForADistrict('+result[i].districtId+');">'+result[i].name+'</span>( '+perc1+'%) '+result[i].districtCount+'<span style="float:right"> Registered Count :'+result[i].totalCount+'</span>';
					str+='<div class="progress progress-warning  progress-striped active">';
				}else if(perc1 > 20 && perc1 <= 40 ){

					str+='<li class="ok">';
					//str+='<span class="progresslabelcolor" style="background-color:#0F0">';
					str+='</span><span id="'+result[i].districtId+'" style="cursor:pointer" onClick="missedCallDetailsForADistrict('+result[i].districtId+');">'+result[i].name+'</span>( '+perc1+'%) '+result[i].districtCount+'<span style="float:right"> Registered Count :'+result[i].totalCount+'</span>';
					str+='<div class="progress progress-primary  progress-striped active">';
				}else if(perc1 > 40 && perc1 <= 60 ){
				
					str+='<li class="good">';
					//str+='<span class="progresslabelcolor" style="background-color:#0F0">';
					str+='</span><span id="'+result[i].districtId+'" style="cursor:pointer" onClick="missedCallDetailsForADistrict('+result[i].districtId+');"> '+result[i].name+'</span>( '+perc1+'%) '+result[i].districtCount+'<span style="float:right"> Registered Count :'+result[i].totalCount+'</span>';
					str+='<div class="progress progress-info  progress-striped active">';
				}else if(perc1 > 60){
					
					str+='<li class="vGood">';
					//str+='<span class="progresslabelcolor" style="background-color:#0F0">';
					str+='</span id="'+result[i].districtId+'"><span style="cursor:pointer" onClick="missedCallDetailsForADistrict('+result[i].districtId+');">'+result[i].name+'</span>( '+perc1+'%) '+result[i].districtCount+'<span style="float:right"> Registered Count :'+result[i].totalCount+'</span>';
					str+='<div class="progress progress-success progress-striped active">';
				}

			str+='<div class="bar" style="width: '+perc1+'%"></div>';
			str+='</div></li>';
		}
		str+='</ul>';*/
		
		if(result != null && result.length > 0){
		str+='<table class="table table-bordered" id="districtTableId">';
		str+='<thead><tr>';
		str+='<th>District</th>';
		str+='<th>Registered Cadres</th>';
		str+='<th>Missed Calls</th>';
		str+='<th>Missed Calls %</th>';
		str+='</tr></thead>';
		str+='<tbody>';
		for(var i in result){	
		var perc = result[i].districtCount / result[i].totalCount * 100 ;		
		var perc1 = perc.toFixed(2);		
			str+='<tr>';
			str+='<td><a href="javascript:{}" onClick="missedCallDetailsForADistrict('+result[i].districtId+');"> '+result[i].name+' </a></td>';
			str+='<td>'+result[i].totalCount+'</td>';
			str+='<td>'+result[i].districtCount+'</td>';
			str+='<td>'+perc1+'</td>';
			str+='</tr>';
		}
		str+='</tbody>';
		str+='</table>';
		$("#districtWiseProgressBars").html(str).css("margin-top:200px;");
		
		$("#districtTableId").dataTable({
			"iDisplayLength": -1,
			"aLengthMenu": [[20, 50, 100, -1], [20, 50, 100, "All"]]
		});
		}
		
		else{
				$("#districtWiseProgressBars").html("No Data Available").css("margin-top:200px;");
		}
		
	
	}
	var chartData1 = new Array();
	function getSingleMemberCountByDistrict()
	{	
	$("#chartdiv").html("");
	$("#districtWiseProgressBars").html("");
	$("#distProcessImgId").show();
		var stateId = $("input[type='radio'][name='select']:checked").val();	
		var startDate=$(".dp_startDate").val();
		var endDate=$(".dp_endDate").val();
		var jObj = {
		fromDate:startDate,
		toDate:endDate,
		stateId:stateId,
		task:"getSingleMember"
	}
	
	$.ajax({
	  type:'GET',
	  url: 'getMissedCallDetailsDistrictWiseAction.action',
	  data : {task:JSON.stringify(jObj)} ,
	}).done(function(result){
		chartData1 = new Array();
		if(result != null && result.length > 0){
		
		$("#chartdiv").show();
		for (var i in result) {      
			chartData1.push({
				title: result[i].name,
				value: result[i].districtCount
			});
		}
		var chart = AmCharts.makeChart("chartdiv", {
		"type": "pie",
		"theme": "none",			
		"titleField": "title",
		"valueField": "value",
		"labelRadius": 5,
		"radius": "30%",
		"innerRadius": "60%",
		"labelText": "[[]]",
		"dataProvider": chartData1
		});
		}
		else{
		$("#chartdiv").hide();
		}
		buildDistrictWiseCount(result,"single",stateId);
	});
	
	
	}
	
	$(document).on("click",".percentagefilter1",function(){
	   $(".vGood,.good,.ok,.vPoor,.poor").hide();
	    var clas= $(this).attr("attr-id");
		
	   $( "."+clas ).each(function( index ) {
        $( this ).show();
       });
	});
	
	$(document).on("click",".allIconId",function(){

	   $(".vGood,.good,.ok,.vPoor,.poor").show();
	   
	});
	
	$(document).on('click','.rangeButton',function(){
			
		getMissedCallDetails();
		getSingleMemberCountByDistrict();
		//getMissedCallDetailsByDistrict();
		$('input:radio[name="select"][id="AllId"]').prop('checked', true);
		$('input:radio[name="radionBtn"][id="single"]').prop('checked', true);
		$("#constituenciesDiv").html("");	
	});

	$(document).on('click','.districtRd',function(){
		//$("#distSummaryBody").html('<td style="text-align:center" colspan="13"><img id="summaryAjax" src="./images/Loading-data.gif" alt="Processing Image"/></td>');
		
		var levelSelected1 = $("input[type='radio'][name='radionBtn']:checked").val();
		if(levelSelected1 == 0){			
			getMissedCallDetailsByDistrict();
		}
		else if(levelSelected1 == 1){
			//getSingleMemberCountByDistrict();
			getSingleMemberCountByDistrict1();
		}
		$("#constituenciesDiv").html("");
	});
	
	$(".stateRd").click(function(){
		getMissedCallDetails();
		getSingleMemberCountByDistrict();
		$('#single').prop("checked","checked");
		//getMissedCallDetailsByDistrict();
		$("#constituenciesDiv").html("");
	});
	
function missedCallDetailsForADistrict(districtId){
		
		$("#constituenciesDiv").html("");
		$("#processImgId").show();
		
		//var districtId='23';
		//var fromDate='2015-02-15';
       // var toDate='2015-02-17';
       var startDate=$(".dp_startDate").val();
	   var endDate=$(".dp_endDate").val();
	   
   	   var jsobj={districtId:districtId,fromDate:startDate, toDate:endDate} ;
	   
	
		 $.ajax({
				type : "GET",
				url : "missedCallDetailsForADistrictAction.action",
				data: {task :JSON.stringify(jsobj)}
			}).done(function(result){
				$("#processImgId").hide();
				
				if(result!=null && result.length > 0){
				var districtName=result[0].name.toUpperCase();
				var str='';
				str+='<table class="table table-bordered" style="margin-bottom:0px;">';
            	str+='<caption style="background-color:#f4f4f4;font-size:16px;">';
				str+='<b class="pull-left" style="padding-top:10px;">'+districtName+' DISTRICT MISSED CALL DETAILS</b><span class="pull-left" style="padding-top:10px;font-size:10px">(FROM DATE '+startDate+' TO DATE '+endDate+')</span> 	';
				str+='<span class="pull-right" style=" cursor:pointer;background-color:#CCC;padding:10px;">';
				str+='<i class="icon icon-remove"></i>';
				str+='</span>';
                str+='</caption>';
                str+='<thead style="font-size:11px;">';
                	str+='<th>Constituency</th>';
                    str+='<th>Registered Count</th>';
                   // str+='<th>Printed Count</th>';
                    str+='<th>Received Missed Calls</th>';
                   // str+='<th>Mismatched Calls</th>';
                    str+='<th>Single Member Registered</th>';
                    str+='<th>Multi Member Registered</th>';
                str+='</thead>';
                str+='<tbody>';
				if(result.length>0){
				 for(var i in result){
					 str+='<tr>';               	
                    	str+='<td>'+result[i].constituencyName+'</td>';
                        str+='<td>'+result[i].totalCount+'</td>';
                      //  str+='<td>'+result[i].printedCount+'</td>';
                        str+='<td>'+result[i].missedCallsCount+'</td>';
                       // str+='<td>NA</td>';
                        str+='<td>'+result[i].singleMemberRegCnt+'</td>';
                        str+='<td>'+result[i].multiMemberRegCnt+'</td>';
                    str+='</tr>';
                 }
				}
				str+='</tbody>';
				str+='</table>';
				$("#constituenciesDiv").html(str);
		}
	});
	}
	
	function getSingleMemberCountByDistrict1()
	{	
	$("#districtWiseProgressBars").html("");
	$("#distProcessImgId").show();
		var stateId = $("input[type='radio'][name='select']:checked").val();	
		var startDate=$(".dp_startDate").val();
		var endDate=$(".dp_endDate").val();
		var jObj = {
		fromDate:startDate,
		toDate:endDate,
		stateId:stateId,
		task:"getSingleMember"
	}
	
	$.ajax({
	  type:'GET',
	  url: 'getMissedCallDetailsDistrictWiseAction.action',
	  data : {task:JSON.stringify(jObj)} ,
	}).done(function(result){
		
		if(result != null && result.length > 0){
		buildDistrictWiseCount(result,"single",stateId);
		}
	});
	}
		
</script>
</body>
</html>
