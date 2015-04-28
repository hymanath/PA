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
<script type="text/Javascript" src="js/homePage/jquery.js"></script>
</head>

<body>
	<div class="custom-head-bg">&nbsp;</div>
<header class="container">
	<div class="row first-row">
    	<div class="col-md-1 col-xs-1 col-sm-1">
        	<img src="dist/img/logo.png" class="header-logo">
        </div>
        <div class="col-md-8 col-xs-9 col-sm-9 header-style" style="padding-left:36px;">
        	<p class="display-style m_0"><img src="dist/img/header-image.png" class="img-responsive"></p>
            <p class="header-text display-style">MAHANADU 2015</p>
        </div>
        <div class="col-md-2"></div>
        <div class="col-md-2 col-sm-2 col-xs-2" >
            <div style="" class="header-time">1</div>
            <p class="header-live-time" id="time"></p>
        </div>
        
    </div>
</header>
    
    	<img src="dist/img/header-footer.png" class="header-footer" style="margin-top: -27px;">
    

<section class="container">
	<div class="row">
    <!--	<div class="col-md-4 col-sm-6 col-xs-12">
        	<div class="panel panel-default panel-custom-green">
              <div class="panel-heading">overall <span class="text-italic">[ap &amp; ts] </span> </div>
              <div class="panel-body">
                <h3 class="display-style m_top0">TOTAL VISITS-</h3>
                <h1 class="display-style m_top0">50245</h1>
                <hr class="m_0"/>
                <div class="display-style">
                	<h3 class="m_bottom0">Invitees</h3>
	                <h4 class="m_top0">49456</h4>
                </div>
                <div class="display-style pull-right">
                	<h3 class="m_bottom0">Non-Invitees</h3>
	                <h4 class="m_top0">49456</h4>
                </div>
                <div class="progress custom-progress-bar">
                  <div class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%;">
                    <span class="sr-only">60% Complete</span>
                  </div>
                </div>
                <p class="text-center m_bottom0 m_top10">4% higher<span style="color:#000000"> than Yesterday</span></p>
              </div>
            </div>
        </div>
    	<div class="col-md-4 col-sm-6 col-xs-12">
        	<div class="panel panel-default panel-custom-red">
              <div class="panel-heading">andhra pradesh</div>
              <div class="panel-body">
                <h3 class="display-style m_top0">TOTAL VISITS-</h3>
                <h1 class="display-style m_top0">50245</h1>
                <hr class="m_0"/>
                <div class="display-style">
                	<h3 class="m_bottom0">Invitees</h3>
	                <h4 class="m_top0">49456</h4>
                </div>
                <div class="display-style pull-right">
                	<h3 class="m_bottom0">Non-Invitees</h3>
	                <h4 class="m_top0">49456</h4>
                </div>
                <div class="progress custom-progress-bar">
                  <div class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%;">
                    <span class="sr-only">60% Complete</span>
                  </div>
                </div>
                <p class="text-center m_bottom0 m_top10">4% higher<span style="color:#000000"> than Yesterday</span></p>
              </div>
            </div>
        </div>
    	<div class="col-md-4 col-sm-6 col-xs-12">
        	<div class="panel panel-default panel-custom-coffee">
              <div class="panel-heading">telangana</div>
              <div class="panel-body">
                <h3 class="display-style m_top0">TOTAL VISITS-</h3>
                <h1 class="display-style m_top0">50245</h1>
                <hr class="m_0"/>
                <div class="display-style">
                	<h3 class="m_bottom0">Invitees</h3>
	                <h4 class="m_top0">49456</h4>
                </div>
                <div class="display-style pull-right">
                	<h3 class="m_bottom0">Non-Invitees</h3>
	                <h4 class="m_top0">49456</h4>
                </div>
                <div class="progress custom-progress-bar">
                  <div class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%;">
                    <span class="sr-only">60% Complete</span>
                  </div>
                </div>
                <p class="text-center m_bottom0 m_top10">4% higher<span style="color:#000000"> than Yesterday</span></p>
              </div>
            </div>
        </div>
      
      -->
      <div class="col-md-4">
        	<div class="panel panel-default panel-custom-green">
              <div class="panel-heading">overall [ap&ts] </div>
              <div class="panel-body">
              <div class="text-center">
                <h4 class="display-style m_top0">TOTAL VISITS-</h4>
                <h2 class="display-style m_top0">50245</h2>
                </div>
                <hr class="m_0"/>
                <div class="display-style">
                	<h3 class="m_bottom0 m_top10">Invitees</h3>
	                <h4 class="m_top0">49456</h4>
                </div>
                <div class="display-style pull-right">
                	<h3 class="m_bottom0 m_top10">Non-Invitees</h3>
	                <h4 class="m_top0">49456</h4>
                </div>
                <div class="progress custom-progress-bar">
                  <div class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%;">
                    <span class="sr-only">60% Complete</span>
                  </div>
                </div>
                <p class="text-center m_bottom0 m_top10">4% higher<span style="color:#000000"> than Yesterday</span></p>
              </div>
            </div>
        </div>
    	<div class="col-md-4">
        	<div class="panel panel-default panel-custom-red">
              <div class="panel-heading">Andhra Pradesh</div>
              <div class="panel-body">
                <!--<h3 class="display-style m_top0">TOTAL VISITS</h3><br/>
                <h1 class="display-style m_top0">50245</h1>-->
                <ul class="list-group list-inline text-center" style="margin-bottom:0px;">
                	<li class="pull-left">Total Visits<p style="font-size:22px;">41253</p></li>
                    <li style="padding:0px">Invitees<p style="font-size:22px;">41253</p></li>
                    <li class="pull-right">Non Invitees<p style="font-size:22px;">41253</p></li>
                </ul>
                <hr class="m_0"/>
                <h4 class="m_0 text-center">REVISITED</h4>
                <div class="display-style">
                    <div class="display-style">
                        <h4 class="m_bottom0">Invitees-495845</h4>
                        
                    </div>
                    <div class="display-style pull-right" style="padding-left:30px;">
                        <h4 class="m_bottom0">Non-Invitees 49456</h4>
                    </div>
                </div>
                <div class="progress custom-progress-bar">
                  <div class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%;">
                    <span class="sr-only">60% Complete</span>
                  </div>
                </div>
                <p class="text-center m_bottom0 m_top10">4% higher<span style="color:#000000"> than Yesterday</span></p>
              </div>
            </div>
        </div>
    	<div class="col-md-4">
        	<div class="panel panel-default panel-custom-coffee">
              <div class="panel-heading">telangana</div>
              <div class="panel-body">
               <!--<h3 class="display-style m_top0">TOTAL VISITS</h3><br/>
                <h1 class="display-style m_top0">50245</h1>-->
                <ul class="list-group list-inline text-center m_bottom0">
                	<li class="pull-left">Total Visits<p class="small_font">41253</p></li>
                    <li style="padding:0px">Invitees<p class="small_font">41253</p></li>
                    <li class="pull-right">Non Invitees<p class="small_font">41253</p></li>
                </ul>
                <hr class="m_0"/>
               <h4 class="m_0 text-center">REVISITED</h4>
                <div class="display-style">
                    <div class="display-style pull-left">
                        <h4 class="m_bottom0">Invitees-495845</h4>
                        
                    </div>
                    <div class="display-style" style="padding-left:30px">
                        <h4 class="m_bottom0">Non-Invitees 49456</h4>
                    </div>
                </div>
                <div class="progress custom-progress-bar col-md-12">
                  <div class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%;">
                    <span class="sr-only">60% Complete</span>
                  </div>
                </div>
                <p class="text-center m_bottom0 m_top10">4% higher<span style="color:#000000"> than Yesterday</span></p>
              </div>
            </div>
        </div>
      
      
        </div>
        <div class="row">
     
  	    <div class="col-md-5 col-sm-6">
			<div class="panel panel-default panel-custom-gallery">
            	<div class="panel-heading">today running programmes</div>
                <div class="panel-body">
                	<div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
                      <!-- Wrapper for slides -->
                      <!--<h2 class="text-center m_0">PHOTO GALLERY</h2>-->
                      <div class="carousel-inner" role="listbox">
                        <div class="item active">
                          <h2 class="text-center m_0">PHOTO GALLERY</h2>
                          <h1 class="text-center big_font">1456</h1>
                           <div class=" gallery-invites">
                                <hr class="m_0"/>
                                <div class="display-style">
                                    <h4 class="m_bottom0">Invitees</h4>
                                    <h5 class="m_top0">49456</h5>
                                </div>
                                <div class="display-style pull-right">
                                    <h4 class="m_bottom0">Non-Invitees</h4>
                                    <h5 class="m_top0">49456</h5>
                                </div>
                                <hr class="m_0"/>
                                <h4 class="text-center m_bottom0 m_top10">Programme Time 9AM to 5PM</h4>
                            </div>
                        </div>
                        <div class="item">
                          <h2 class="text-center m_0">BLOOD CAMP</h2>
                          <h1 class="text-center big_font">1456</h1>
                          <div class=" gallery-invites">
                                <hr class="m_0"/>
                                <div class="display-style">
                                    <h4 class="m_bottom0">Invitees</h4>
                                    <h5 class="m_top0">49456</h5>
                                </div>
                                <div class="display-style pull-right">
                                    <h4 class="m_bottom0">Non-Invitees</h4>
                                    <h5 class="m_top0">49456</h5>
                                </div>
                                <hr class="m_0"/>
                                <h4 class="text-center m_bottom0 m_top10">Programme Time 9AM to 5PM</h4>
                            </div>
                        </div>
                        <div class="item">
                          <h2 class="text-center m_0">FOOD</h2>
                          <h1 class="text-center big_font">1456</h1>
                          <div class=" gallery-invites">
                                <hr class="m_0"/>
                                <div class="display-style">
                                    <h4 class="m_bottom0">Invitees</h4>
                                    <h5 class="m_top0">49456</h5>
                                </div>
                                <div class="display-style pull-right">
                                    <h4 class="m_bottom0">Non-Invitees</h4>
                                    <h5 class="m_top0">49456</h5>
                                </div>
                                <hr class="m_0"/>
                                <h4 class="text-center m_bottom0 m_top10">Programme Time 9AM to 5PM</h4>
                            </div>
                        </div>
                      </div>
                    
                      <!-- Controls -->
                      <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
                        <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                        <span class="sr-only">Previous</span>
                      </a>
                      <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
                        <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                        <span class="sr-only">Next</span>
                      </a>
                    </div>
                    <!--<div class=" gallery-invites">
                        <hr class="m_0"/>
                        <div class="display-style">
                            <h4 class="m_bottom0">Invitees</h4>
                            <h5 class="m_top0">49456</h5>
                        </div>
                        <div class="display-style pull-right">
                            <h4 class="m_bottom0">Non-Invitees</h4>
                            <h5 class="m_top0">49456</h5>
                        </div>
                        <hr class="m_0"/>
                        <h4 class="text-center m_bottom0 m_top10">Programme Time 9AM to 5PM</h4>
                    </div>-->
                </div>
            </div>        
        </div>
        <div class="col-md-7 col-xs-12 col-sm-6">
        		<div id="container" style="width: 100%; height: 100%; margin: 0 auto;box-shadow: 0px 5px 10px 0px rgba(0, 0, 0, 0.75);
"></div>

        </div>
        </div>
        <div class="row m_top10">
        <div class="col-md-5">
        	<div class="panel panel-default panel-custom-default">
            	<div class="panel-heading">
                	Today Starting soon Programmes
                </div>
				 <div id="startingPgmDivId" class="panel-body scrollDiv1" style="height:390px;"></div>
               <!-- <div class="panel-body scrollDiv1">
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
                </div>-->
            </div>
        </div>

        <div class="col-md-7">
        	<div class="panel panel-default panel-custom-default">
            	<div class="panel-heading">
                <p class="m_0 display-style" id="districtHeading">andhra pradesh</p>
                <div class="onoffswitch pull-right">
                    <input type="checkbox" name="onoffswitch" class="onoffswitch-checkbox" id="myonoffswitch" checked>
                    <label class="onoffswitch-label" for="myonoffswitch">
                        <span class="onoffswitch-inner"></span>
                        <span class="onoffswitch-switch"></span>
                    </label>
                </div>
                
                </div>
                <div class="panel-body" style="min-height:350px;">
                  
                       
                          <div id="districtTableId"></div>
                                
                         
                 
                </div>
            </div>
        </div>
    </div>
    <div class="row">
    	<div class="col-md-12">
        	<div class="panel panel-default panel-custom-default">
            	<div class="panel-heading">
                <p class="m_0 display-style" id="constiHeading">Andhra Pradesh</p>
                <div class="onoffswitch pull-right">
                    <input type="checkbox" name="onoffswitch1" class="onoffswitch-checkbox" id="myonoffswitch1" checked>
                    <label class="onoffswitch-label" for="myonoffswitch1">
                        <span class="onoffswitch-inner"></span>
                        <span class="onoffswitch-switch"></span>
                    </label>
                </div>
                
                </div>
                <div class="panel-body"><div id="constiTableId">
                  
                       
                </div></div>
            </div>
        </div>
    </div>
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
	
	
	
    $('#container').highcharts({
		chart: {
            type: 'area'
        },
		title: {
            text: 'Visits',
            x: -100 //center
        },
		legend: {
            layout: 'vertical',
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
            name: 'Today',
            data: [10000, 3000, 5000, 30000, 40000, 6000, 8000, 1000, 4000, 9000, 500, 12000]
        }, {
            name: 'Yesterday',
            data: [30000, 1000, 30000, 5000, 60000, 4000, 1000, 8000, 9000, 4000, 1500, 2000]
        }]
    });
});

</script>
<script>
var myVar=setInterval(function(){myTimer()},1000);

function myTimer() {
    var d = new Date();
    document.getElementById("time").innerHTML = d.toLocaleTimeString();
}
function testIt()
{
	$.ajax({
          type:'POST',
          url: 'insertDataintoEventInfoAction.action',
          dataType: 'json',
          data: {},

          success: function(result){ 
			  
         },
          error:function() { 
           //console.log('error', arguments);
         }
    });
}
getLocationWiseVisitorsCount(2,1,3);
getLocationWiseVisitorsCount(2,1,4);

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
	str+='<div class="ScrollDiv"><table  class="display DataTableDiv" id="table'+reportLevelId+'" cellspacing="0" width="100%"><thead>';
	str+='<tr>';
	if(reportLevelId == 3){
    str+='<th>DISTRICT NAME</th>';
	}else{
	str+='<th>CONSTITUENCY NAME</th>';
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
	
	$('.ScrollDiv').slimScroll({
	height: '350px'
	});
}
$("#myonoffswitch").click(function(){
	if($('#myonoffswitch').is(":checked")){
	getLocationWiseVisitorsCount(2,1,3);
	$("#districtHeading").html("Andhra Pradesh");
	}else{
	getLocationWiseVisitorsCount(2,36,3);
	$("#districtHeading").html("Telangana");
	}
});
$("#myonoffswitch1").click(function(){
	if($('#myonoffswitch1').is(":checked")){
	getLocationWiseVisitorsCount(2,1,4);
	$("#constiHeading").html("Andhra Pradesh");
	}else{
	getLocationWiseVisitorsCount(2,36,4);
	$("#constiHeading").html("Telangana");
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
getLocationWiseVisitorsCount(2,1,2);

</script>
<script>
</script>
</body>
</html>
