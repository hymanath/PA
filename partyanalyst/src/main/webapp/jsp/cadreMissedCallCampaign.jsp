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

 <link href="js/cadreCommittee/bootstrapDaterangepicker/daterangepicker-bs3.css" rel="stylesheet" />

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
                	<td width="430" style="text-align:center;padding:0px;">
                    	<div id="chartdiv" style="margin-bottom:-80px;margin-top:-20px"></div>
                        <h5 style="margin-bottom:0px;">TOTAL MISSED CALLS</h5>
                       <h3 style="margin-top:0px;" id="totalMissedCallsId"></h3>
                        <table class="table table-bordered" style="margin-bottom:0px;">
                        	<tr style="word-wrap:break-word">
                            	<td>
                                <small>
                                	<b>Numbers Mismatched Cadre Missed Calls</b> 
                                </small>
                                <h3 id="mismatchedId"></h3>
                                </td>
                                <td>
                                <small>
                                	<b>Single Member Registered Numbers</b>
                                </small>
                                <h3 id="singleRegId"></h3>
                                </td>
                                <td>
                                <small>
                                	<b>Multi Member Registered Numbers</b>
                                </small>
                                <h3 id="multiRegId"></h3>
                                </td>
                            </tr>
                        </table>
                    </td>
                    <td width="350px" style="padding:0px">
					<div id="districtWiseProgressBars"></div>
                    		
                    </td>
                    <td style="background-color:#f4f4f4">
                    	<label class="label label-custom">All<i class="icon icon-remove"></i></label>
						<ul style="margin-top:80px;">
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
                    </td>
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

<script type="text/javascript" src="js/custom.js"></script>


<script type="text/javascript">

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
					getMissedCallDetails();
					getMissedCallDetailsByDistrict();
               });
			   
function getMissedCallDetails(){
	var stateId = $("input[type='radio'][name='select']:checked").val();	
	var startDate=$(".dp_startDate").val();
	var endDate=$(".dp_endDate").val();
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
		$("#totalMissedCallsId").html(result.totalCount);
		$("#mismatchedId").html(result.mismatchedCnt);
		$("#singleRegId").html(result.singleMemberRegCnt);
		$("#multiRegId").html(result.multiMemberRegCnt);
	});
}
var chartData = [];
function getMissedCallDetailsByDistrict(){
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
		buildDistrictWiseCount(result);
	});
	
	
	
}

function buildDistrictWiseCount(result){
		var str ='';
		str+=''
		str+='<h6 style="border:1px solid #dddddd;background-color:#f4f4f4;padding:5px;margin:0px">ANDHRA PRADESH & TELANGANA district wise missed calls percentages</h6>';
        str+='<div style="padding:2px;font-size:10px;">';
		 str+='<label class="radio inline">';
        str+='<input type="radio" type="radio"  id="multi" style="vertical-align: text-bottom;" class="districtRd" value="1" name="radionBtn" checked="true"/>';
        str+='<small>Multi Member Registered</small>';
        str+='</label>';
        str+='<label class="radio inline">';
        str+='<input type="radio" id="single" style="vertical-align: text-bottom;" class="districtRd" value="0" name="radionBtn" />';
        str+='<small>Single Member Registered</small>';
        str+='</label>';
       
        str+='</div>';
		for(var i in result){
		var perc =result[i].districtCount / result[0].totalCount * 100 ;
		var perc1 = perc.toFixed(2);
			
			
				if(perc1 <= 10){
					str+='<ul  class="slimscrollar vPoor" style="padding:8px;margin-top:5px;border-top:1px solid #dddddd">';
					str+='<li class="">';
					//str+='<span class="progresslabelcolor" style="background-color:#0F0">';
					str+='</span><span id="'+result[i].districtId+'" style="cursor:pointer" onClick="missedCallDetailsForADistrict('+result[i].districtId+');">'+result[i].name+'</span>( '+perc1+'%) '+result[i].districtCount+'';
					str+='<div class="progress progress-danger  progress-striped active">';					
				}else if(perc1 > 10 && perc1 <= 20 ){
					str+='<ul  class="slimscrollar poor" style="padding:8px;margin-top:5px;border-top:1px solid #dddddd">';
					str+='<li class="">';
					//str+='<span class="progresslabelcolor" style="background-color:#0F0">';
					str+='</span><span id="'+result[i].districtId+'" style="cursor:pointer" onClick="missedCallDetailsForADistrict('+result[i].districtId+');">'+result[i].name+'</span>( '+perc1+'%) '+result[i].districtCount+'';
					str+='<div class="progress progress-warning  progress-striped active">';
				}else if(perc1 > 20 && perc1 <= 40 ){
					str+='<ul  class="slimscrollar ok" style="padding:8px;margin-top:5px;border-top:1px solid #dddddd">';
					str+='<li class="">';
					//str+='<span class="progresslabelcolor" style="background-color:#0F0">';
					str+='</span><span id="'+result[i].districtId+'" style="cursor:pointer" onClick="missedCallDetailsForADistrict('+result[i].districtId+');">'+result[i].name+'</span>( '+perc1+'%) '+result[i].districtCount+'';
					str+='<div class="progress progress-primary  progress-striped active">';
				}else if(perc1 > 40 && perc1 <= 60 ){
					str+='<ul  class="slimscrollar good" style="padding:8px;margin-top:5px;border-top:1px solid #dddddd">';
					str+='<li class="">';
					//str+='<span class="progresslabelcolor" style="background-color:#0F0">';
					str+='</span><span id="'+result[i].districtId+'" style="cursor:pointer" onClick="missedCallDetailsForADistrict('+result[i].districtId+');"> '+result[i].name+'</span>( '+perc1+'%) '+result[i].districtCount+'';
					str+='<div class="progress progress-info  progress-striped active">';
				}else if(perc1 > 60){
					str+='<ul  class="slimscrollar vGood" style="padding:8px;margin-top:5px;border-top:1px solid #dddddd">';
					str+='<li class="">';
					//str+='<span class="progresslabelcolor" style="background-color:#0F0">';
					str+='</span id="'+result[i].districtId+'"><span style="cursor:pointer" onClick="missedCallDetailsForADistrict('+result[i].districtId+');">'+result[i].name+'</span>( '+perc1+'%) '+result[i].districtCount+'';
					str+='<div class="progress progress-success progress-striped active">';
				}

			str+='<div class="bar" style="width: '+perc1+'%"></div>';
			str+='</div></li></ul>';
		}
		$("#districtWiseProgressBars").html(str);
	}
	
	function getSingleMemberCountByDistrict()
	{
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
		buildDistrictWiseCount(result);
	});
	
	
	}
	
	$(document).on("click",".percentagefilter1",function(){
	   $(".vGood,.good,.ok,.vPoor,.poor").hide();
	    var clas= $(this).attr("attr-id");
		
	   $( "."+clas ).each(function( index ) {
        $( this ).show();
       });
	});
	
	$(document).on('click','.rangeButton',function(){
			
		getMissedCallDetails();
		getMissedCallDetailsByDistrict();
		$('input:radio[name="select"][id="AllId"]').prop('checked', true);
		$('input:radio[name="select"][id="districtId"]').prop('checked', true);
			
	});
	$(".districtRd").click(function(){
		//$("#distSummaryBody").html('<td style="text-align:center" colspan="13"><img id="summaryAjax" src="./images/Loading-data.gif" alt="Processing Image"/></td>');
		
		var levelSelected1 = $("input[type='radio'][name='radionBtn']:checked").val();
		if(levelSelected1 == 0){			
			getMissedCallDetailsByDistrict();
		}
		else if(levelSelected1 == 1){
			getSingleMemberCountByDistrict();
		}
	});
	
	$(".stateRd").click(function(){
		getMissedCallDetails();
		getMissedCallDetailsByDistrict();
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
				var districtName=result[0].name.toUpperCase();
				if(result!=null){
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
                    str+='<th>Printed Count</th>';
                    str+='<th>Received Missed Calls</th>';
                    str+='<th>Mismatched Calls</th>';
                    str+='<th>Single Member Registered</th>';
                    str+='<th>Multi Member Registered</th>';
                str+='</thead>';
                str+='<tbody>';
				if(result.length>0){
				 for(var i in result){
					 str+='<tr>';
                	
                    	str+='<td>'+result[i].constituencyName+'</td>';
                        str+='<td>'+result[i].totalCount+'</td>';
                        str+='<td>'+result[i].printedCount+'</td>';
                        str+='<td>'+result[i].missedCallsCount+'</td>';
                        str+='<td>NA</td>';
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
		
</script>
</body>
</html>
