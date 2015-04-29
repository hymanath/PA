<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<title>Mahanadu</title>
<link href="dist/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="dist/css/custom.css" rel="stylesheet" type="text/css">
<link href="dist/Icomoon/style.css" rel="stylesheet" type="text/css">
<link href="dist/css/jquery.dataTables.css" rel="stylesheet" type="text/css">
<link href="dist/css/dataTables.responsive.css" rel="stylesheet" type="text/css">
</head>

<body>
	<div class="custom-head-bg">&nbsp;</div>
<header class="container">
	<div class="row first-row">
    	<div class="col-md-1 col-xs-1 col-sm-1">
        	<img src="dist/img/logo.png" class="header-logo">
        </div>
        <div class="col-md-8 col-xs-9 col-sm-9 header-style" style="padding-left:36px;">
        	<p class="display-style m_0"><img src="dist/img/header-image1.png" class="img-responsive"></p>
            <p class="header-text display-style">PARTY OFFICE EVENT</p>
        </div>
        <div class="col-md-2"></div>     
    </div>
</header>

    

<section class="container">
	<div class="row">
    	<div class="col-md-4 col-sm-6 col-xs-12">
        	<div class="panel panel-default panel-custom-green">
              <div class="panel-heading">overall events status </div>
              <div class="panel-body scrollDiv1"  id="overAllEventDiv">
                
             
              </div>
            </div>
        </div>
        <div class="col-md-8 col-xs-12 col-sm-6">
        		<div id="container" style="width: 100%; height: 100%; margin: 0 auto;box-shadow: 0px 5px 10px 0px rgba(0, 0, 0, 0.75); border-radius:5px;"></div>
        </div>

        </div>
        <div class="row">
        	<div class="col-md-5 col-xs-12 col-sm-6">
            	<div class="panel panel-default panel-custom-default">
                	<div class="panel-heading">total event visits</div>
                    <div class="panel-body">
                        <div id="donutchart" style="width: 100%; height: 100%; margin: 0 auto;border-radius:5px"></div>
    				</div>
				</div>
        	</div>
            <div class="col-md-7 col-xs-12 col-sm-6">
            	<div class="panel panel-default panel-custom-default">
        			<div class="panel-heading">event wise repeated members count</div>
                    <div class="panel-body">
						<div id="columnchart" style="width: 100%; height: 100%; margin: 0 auto;border-radius:5px;"></div>
					</div>
				</div>
        	</div>
        </div>
        <div class="row m_top10">
        <div class="col-md-6">
        	<!--<div class="panel panel-default panel-custom-default">
            	<div class="panel-heading">
                	Today Starting soon Programmes
                </div>
                <div class="panel-body ScrollDiv1">
                	<p class="m_0 text-bold">NTR PHOTO GALLERY</p>
                    <p>Short Description of photo gallery</p>
                    <p class="text-danger">Programme Time: 9:00 am To 5:00 pm</p>
                    <hr class="m_top10"/>
                    <p class="m_0 text-bold">BLOOD DONATION</p>
                    <p>Short Description of photo gallery</p>
                    <p class="text-danger">Programme Time: 9:00 am To 5:00 pm</p>
                    <hr class="m_top10"/>
                    <p class="m_0 text-bold">CHANDRABABU NAIDU SPEECH</p>
                    <p>Short Description of photo gallery</p>
                    <p class="text-danger">Programme Time: 9:00 am To 5:00 pm</p>
                    <hr class="m_top10"/>
                    <p class="m_0 text-bold">CHANDRABABU NAIDU SPEECH</p>
                    <p>Short Description of photo gallery</p>
                    <p class="text-danger m_bottom0">Programme Time: 9:00 am To 5:00 pm</p>
                </div>
            </div>-->
			<div class="panel panel-default panel-custom-default">
            	<div class="panel-heading">
                <p class="m_0 display-style" id="districtHeading">AP DISTRICT WISE</p>
                <div class="onoffswitch pull-right">
                    <input type="checkbox" name="onoffswitch" class="onoffswitch-checkbox" id="myonoffswitch" checked>
                    <label class="onoffswitch-label" for="myonoffswitch">
                        <span class="onoffswitch-inner"></span>
                        <span class="onoffswitch-switch"></span>
                    </label>
                </div>
                
                </div>
                <div class="panel-body" >
                     <div id="districtTableId"></div>
                </div>
            </div>
			
        </div>
        <div class="col-md-6">
        	<div class="panel panel-default panel-custom-default">
            	<div class="panel-heading">
                <p class="m_0 display-style" id="constiHeading">AP CONSTITUENCY WISE</p>
                <div class="onoffswitch pull-right">
                    <input type="checkbox" name="onoffswitch1" class="onoffswitch-checkbox" id="myonoffswitch1" checked>
                    <label class="onoffswitch-label" for="myonoffswitch1">
                        <span class="onoffswitch-inner"></span>
                        <span class="onoffswitch-switch"></span>
                    </label>
                </div>
                
                </div>
                <div class="panel-body">
                   <div id="constiTableId"></div>
                </div>
            </div>
        </div>
    </div>
    <!--<div class="row">
    	<div class="col-md-12">
        	<div class="panel panel-default panel-custom-default">
            	<div class="panel-heading">
                <p class="m_0 display-style" id="constiHeading">AP CONSTITUENCY WISE</p>
                <div class="onoffswitch pull-right">
                    <input type="checkbox" name="onoffswitch1" class="onoffswitch-checkbox" id="myonoffswitch1" checked>
                    <label class="onoffswitch-label" for="myonoffswitch1">
                        <span class="onoffswitch-inner"></span>
                        <span class="onoffswitch-switch"></span>
                    </label>
                </div>
                
                </div>
                <div class="panel-body">
                   <div id="constiTableId"></div>
                </div>
            </div>
        </div>
    </div>-->
</section>
<footer>
	<div class="text-center">
    	All &copy; 2015 Telugu Desam Party
    </div>
</footer>
<script src="dist/js/jquery-1.11.2.min.js" type="text/javascript"></script>
<script src="dist/js/bootstrap.min.js" type="text/javascript"></script>
<script src="dist/js/highcharts.js" type="text/javascript"></script>
<script src="dist/js/jquery.slimscroll.min.js" type="text/javascript"></script>
<script src="dist/js/jquery.dataTables.min.js" type="text/javascript"></script>
<script src="dist/js/dataTables.responsive.js" type="text/javascript"></script>
<script type="text/javascript">
$(function () {
	   $('.DataTableDiv').DataTable( {
        responsive: true,
		"paging":   false,
        "info":     false,
		"searching": false,
		"sDom": '<"top"i>rt<"bottom"flp><"clear">',
		"columnDefs": [
	    { "width": "25%", "targets": 0 }]
    } );
	
	$('.ScrollDiv1').slimScroll({
	height: '390px'
	});
	$('.ScrollDiv2').slimScroll({
	height: '260px'
	});
	$('.ScrollDiv').slimScroll({
	height: '350px'
	});
    $('#container').highcharts({
		chart: {
            type: 'area'
        },
		title: {
            text: 'Visits',
             align: 'center',
        },
		legend: {
            layout: 'horizontal',
            align: 'center',
            verticalAlign: 'top',
            borderWidth: 0,
			enabled: true,
			x: 150 //center
        },
        xAxis: {
            categories: ['9 AM', '10 AM', '11 AM', '12 AM', '1 PM', '2 PM',
                '3 PM', '4 PM', '5 PM', '6 PM', '7 PM', '8 PM']
        },
        yAxis: {
            title: {
                text: 'Visits'
            },
            plotLines: [{
                value: 0,
                width: 1,
                color: '#808080'
            }]
        },
        tooltip: {
            valueSuffix: ' visits'
        },
        series: [{
            name: 'Lunch',
            data: [10000, 3000, 5000, 30000, 40000, 6000, 8000, 1000, 4000, 9000, 500, 12000]
        }, {
            name: 'Lokesh Meeting',
            data: [30000, 1000, 30000, 5000, 60000, 4000, 1000, 8000, 9000, 4000, 1500, 2000]
        }, {
            name: 'Blood Donation',
            data: [3000, 10000, 3000, 50000, 6000, 40000, 10000, 60000, 40000, 40000, 15000, 20000]
        }]
    });
});
/* DONUT CHART */
$(function () {
    $('#columnchart').highcharts({
        chart: {
            type: 'column'
        },
        
        xAxis: {
            categories: ['Nara Lokesh Meeting with Mandal Presidents', 'Lunch Section Party Office', 'Lokesh Meeting', 'Nara Lokesh Meeting With District Presidents', 'Blood Donation In Party Office']
        },
        yAxis: {
            min: 0,
            title: {
                text: ''
            },
            stackLabels: {
                enabled: true,
                style: {
                    fontWeight: 'bold',
                    color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
                }
            }
        },
        legend: {
            align: 'right',
            x: -30,
            verticalAlign: 'top',
            y: 25,
            floating: true,
            backgroundColor: (Highcharts.theme && Highcharts.theme.background2) || 'white',
            borderColor: '#CCC',
            borderWidth: 1,
            shadow: false
        },
        tooltip: {
            formatter: function () {
                return '<b>' + this.x + '</b><br/>' +
                    this.series.name + ': ' + this.y + '<br/>' +
                    'Total: ' + this.point.stackTotal;
            }
        },
        plotOptions: {
            column: {
                stacking: 'normal',
                dataLabels: {
                    enabled: true,
                    color: (Highcharts.theme && Highcharts.theme.dataLabelsColor) || 'white',
                    style: {
                        textShadow: '0 0 3px black'
                    }
                }
            }
        },
        series: [{
            name: 'Nara Lokesh Meeting with Mandal Presidents',
            data: [11002, 10000, 50000, 90000, 50000]
        }, {
            name: 'Lunch Section Party Office',
            data: [46525, 46462, 54684, 4000, 5000]
        }, {
            name: 'Lokesh Meeting',
            data: [55465, 51535, 94655, 54654, 46546]
        }, {
            name: 'Nara Lokesh Meeting With District Presidents',
            data: [94654, 54654, 54655, 84545, 53543]
        }, {
            name: 'Blood Donation In Party Office ',
            data: [95484, 54654, 54453, 73572, 46845]
        }
		]
    });
});

</script>
<script>
/*var myVar=setInterval(function(){myTimer()},1000);
function myTimer() {
    var d = new Date();
    document.getElementById("time").innerHTML = d.toLocaleTimeString();
}*/


function getLocationWiseVisitorsCount(eventId,stateId,reportLevelId)
{
	var jObj = {
			eventId:eventId,			
			stateId:stateId,
			reportLevelId:reportLevelId
		
		}	
		
		$.ajax({
          type:'GET',
          url: 'getLocationWiseVisitorsCountAction.action',
		  data : {task:JSON.stringify(jObj)} ,
        }).done(function(result){
			if(result != null)
			{				
				buildDistrictTable(result,reportLevelId)	
			}
	});
}
function buildDistrictTable(result,reportLevelId){
	var str='';
	str+='<div class="scrollDiv"><table  class="display DataTableDiv" id="table'+reportLevelId+'" cellspacing="0" width="100%"><thead>';
	str+='<tr>';
	if(reportLevelId == 3){
    str+='<th>DISTRICT</th>';
	}else{
	str+='<th>CONSTITUENCY</th>';
	}
	str+='<th>VOTERS</th>';
    str+='<th>CADRES</th>';
	str+='<th>INVITEES</th>';
	str+='<th>NON INVITEES</th>';
    str+='</tr></thead>';
    str+='<tbody>';
	for(var i in result){
		str+='<tr>';
		str+='<td>'+result[i].name+'</td>';
		str+='<td>'+result[i].voterCount+'</td>';
		str+='<td>'+result[i].cadreCount+'</td>';
		str+='<td>'+result[i].invitees+'</td>';
		str+='<td>'+result[i].nonInvitees+'</td>';
		str+='</tr>';
    }                               
	str+='</tbody></table></div>';
	if(reportLevelId == 3)
	$("#districtTableId").html(str);
	else
	$("#constiTableId").html(str);
	$('#table'+reportLevelId).DataTable( {
        responsive: true,
		"paging":   false,
        "info":     false,
		"searching": false,
		"sDom": '<"top"i>rt<"bottom"flp><"clear">',
		"columnDefs": [
	    { "width": "30%", "targets": 0 }]
    } );
	
	$('.scrollDiv').slimScroll({
	height: '350px'
	});
}
$("#myonoffswitch").click(function(){
	if($('#myonoffswitch').is(":checked")){
	getLocationWiseVisitorsCount(2,1,3);
	$("#districtHeading").html("AP DISTRICT WISE");
	}else{
	getLocationWiseVisitorsCount(2,36,3);
	$("#districtHeading").html("TS DISTRICT WISE");
	}
});
$("#myonoffswitch1").click(function(){
	if($('#myonoffswitch1').is(":checked")){
	getLocationWiseVisitorsCount(2,1,4);
	$("#constiHeading").html("AP CONSTITUENCY WISE");
	}else{
	getLocationWiseVisitorsCount(2,36,4);
	$("#constiHeading").html("TS CONSTITUENCY WISE");
	}
});

getSubEventDetails(1,1);
function getSubEventDetails(userId,parentEventId){
	var jObj = {
			parentEventId:parentEventId,			
			userId:userId
		}	
		
		$.ajax({
          type:'GET',
          url: 'getSubEventDetailsAction.action',
		  data : {task:JSON.stringify(jObj)} ,
        }).done(function(result){
		buildStartingPrograms(result);
	});
}
function buildStartingPrograms(result){
    var str='';
	//str+='<div class="panel-body scrollDiv1">';
	for( var i in result){
  
        str+='<p class="m_0 text-bold">'+result[i].userName+'</p>';
        str+='<p>'+result[i].status+'</p>';
		if(result[i].startTime != null && result[i].endTime != null){
        str+='<p class="text-danger">Programme Time:'+result[i].startTime+' To '+result[i].endTime +'</p>';
		}else{
		str+='<p class="text-danger">Programme Time:</p>';
		}
        str+='<hr class="m_top10"/>';
    }              

	$("#startingPgmDivId").html(str);
	$('.scrollDiv1').slimScroll({
	height: '390px'
	});
}

function getSubEventDetails(userId,parentEventId){
	var jObj = {
			parentEventId:parentEventId,			
			userId:userId
		}	
		
		$.ajax({
          type:'GET',
          url: 'getSubEventDetailsAction.action',
		  data : {task:JSON.stringify(jObj)} ,
        }).done(function(result){
		buildStartingPrograms(result);
	});
}
function buildStartingPrograms(result){
    var str='';
	//str+='<div class="panel-body scrollDiv1">';
	/*for( var i in result){
  
        str+='<p class="m_0 text-bold">'+result[i].name+'</p>';
        str+='<p>'+result[i].status+'</p>';
		if(result[i].startTime != null && result[i].endTime != null){
        str+='<p class="text-danger">Programme Time:'+result[i].startTime+' To '+result[i].endTime +'</p>';
		}else{
		str+='<p class="text-danger">Programme Time:</p>';
		}
        str+='<hr class="m_top10"/>';
    }     */    

					str+='<div class="text-center">';
                    str+='<h3 class="display-style m_top0">TOTAL VISITS-</h3>';
                   str+=' <h1 class="display-style m_top0">'+result[0].total+'</h1>';
                 str+=' </div>';
                /* str+=' <div class="display-style">';
                  str+='	<div class="display-style label-custom1">Invitees - 49456</div>';
                   str+=' <div class="pull-right label-custom1 display-style">Non Invitees - 49456</div>';
                   str+=' <br/>';
                 str+=' </div>';*/
                  str+=' <div class="ScrollDiv2">';
                	str+='<hr class="m_bottom10"/>	';
					var dataArr=[] ;
				for(var i in result)
				{
				 
					str+=' <span class="pull-left">'+result[i].name+'</span>';
					var count = result[i].invitees + result[i].nonInvitees;
                    str+='  <span class="pull-right label-custom">'+count+'</span>';
                    str+=' <br/>';
                    str+=' <hr class="m_top10"/>';
					str+=' </div>';
					 var arr = [];
				    arr.push(result[i].name,count);
				    dataArr.push(arr);
		}
	$("#overAllEventDiv").html(str);
	$('.scrollDiv1').slimScroll({
	height: '390px'
	});
	 buildPieChart(dataArr);
}

function buildPieChart(dataArr)
{
console.log(dataArr);
    $('#donutchart').highcharts({
        chart: {
            type: 'pie',
            options3d: {
                enabled: true,
                alpha: 20
            }
        },
         plotOptions: {
            pie: {
                innerSize: 180,
                depth: 20,
				 showInLegend: true,
			
			dataLabels: {
                        enabled: false
                  }
             
            }
        },
			legend: {
				enabled: true,
                align: 'center',
                x: 10,
                verticalAlign: 'bottom',
                y: 20,
                floating: true,
                backgroundColor: (Highcharts.theme && Highcharts.theme.background2) || 'white',
                borderColor: '#CCC',
                borderWidth: 1,
                shadow: false
            },	
		series: [{
            
            data: dataArr
        }]
 
});
}

function insertIntermediateData()
{
	$.ajax({
          type:'POST',
          url: 'insertDataintoEventInfoAction.action',
          dataType: 'json',
          data: {},

          success: function(result){ 
			  
         },
          error:function() { 
           console.log('error', arguments);
         }
    });

}

getLocationWiseVisitorsCount(2,1,3);
getLocationWiseVisitorsCount(2,1,4);

getSubEventDetails(1,1);
</script>

</body>
</html>
