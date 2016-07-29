<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href="dist/coreDashboard/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="dist/coreDashboard/css/custom.css" rel="stylesheet" type="text/css">
<link href="http://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Oswald" rel="stylesheet" type="text/css">


</head>
<body>
<header>
	<nav class="navbar navbar-default navbarHeader">
      <div class="container">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">
          	<span class="logo"></span>
            <span>TDP PARTY</span>
          </a>
        </div>
    
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse " id="bs-example-navbar-collapse-1">
          <ul class="nav navbar-nav navbar-right">
            <li><a href="#"><i class="glyphicon glyphicon-bell"></i></a></li>
            <li class="dropdown profileDropDown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><img src="dist/img/logo.png"/> <span class="caret"></span></a>
              <ul class="dropdown-menu">
                <li><a href="#">Action</a></li>
                <li><a href="#">Another action</a></li>
                <li><a href="#">Something else here</a></li>
                <li role="separator" class="divider"></li>
                <li><a href="#">Separated link</a></li>
              </ul>
            </li>
          </ul>
          <div class="navbar-form navbar-right">
          	<div class="input-group headerSearch">
            	<span class="input-group-addon">
                	<i class="glyphicon glyphicon-search"></i>
                </span>
                <input type="text" class="form-control" placeholder="Search...">
            </div>
          </div>
        </div><!-- /.navbar-collapse -->
      </div><!-- /.container-fluid -->
    </nav>
    <div class="navbar navbar-default navbarProfile">
    	<div class="container">
        	<div class="row">
            	<div class="col-md-8 col-xs-12 col-sm-8">
                	<ul class="nav navbar-nav navbar-left headerProfileName">
                        <li class="dropdown profileDropDown">
                          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">ASHOK DAKAVARAM 
                          	<span class="caretBackground">
                            	<span class="caret"></span>
                            </span>
                          </a>
                          <ul class="dropdown-menu">
                            <li><a href="#">Action</a></li>
                            <li><a href="#">Another action</a></li>
                            <li><a href="#">Something else here</a></li>
                            <li role="separator" class="divider"></li>
                            <li><a href="#">Separated link</a></li>
                          </ul>
                        </li>
                    </ul>
                </div>
                <div class="col-md-2 col-xs-12 col-sm-2">
                	<ul class="list-inline profileSelection">
                    	<li class="active">
                        	<a href="#">general</a>
                        </li>
                        <li>
                        	<a href="#">profile</a>
                        </li>
                    </ul>
                </div>
                <div class="col-md-2 col-xs-12 col-sm-2">
                	<button class="btn btn-success btnYellow"><img src="dist/coreDashboard/img/Icon.png" alt="Icon" class="img-responsive"/>query builder</button>
                    <i class="glyphicon glyphicon-cog settingsIcon"></i>
                </div>
            </div>
        </div>
    </div>
    <div class="navbar navbar-default navbarProfile">
    	<div class="container">
        	<div class="row">
            	<div class="col-md-10 col-xs-12 col-sm-10">
                	<p class="m_top10 text-capitalize">Dashboard</p>
                </div>
                <div class="col-md-2 col-xs-12 col-sm-2">
                	<ul class="list-inline profileSelection">
                    	<li class="active">
                        	<a href="#">AP</a>
                        </li>
                        <li>
                        	<a href="#">TS</a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</header>
<div class="container m_top20">
	<div class="row">
    	<div class="col-md-4 col-xs-12 col-sm-4 committeesBlock">
        	<div class="panel panel-default panelCustomBlock">
            	<div class="panel-heading">
                	<h4 class="panel-title"><span class="coreIcons committeesIcon">committees</span></h4>
                </div>
                <div class="panel-body">
                	<div id="committees" class="chart"></div>
                    <i class="glyphicon glyphicon-option-horizontal pull-right committeesExpandIcon"></i>
                </div>
            </div>
        </div>
        <div class="col-md-4 col-xs-12 col-sm-4">
        	<div class="panel panel-default panelCustomBlock">
            	<div class="panel-heading">
                	<h4 class="panel-title"><span class="coreIcons meetingsIcon">meetings</span></h4>
                </div>
                <div class="panel-body">
                	<div id="meetings" class="chart"></div>
                    <i class="glyphicon glyphicon-option-horizontal pull-right"></i>
                </div>
            </div>
        </div>
        <div class="col-md-4 col-xs-12 col-sm-4">
        	<div class="panel panel-default panelCustomBlock">
            	<div class="panel-heading">
                	<h4 class="panel-title"><span class="coreIcons newsIcon">news</span></h4>
                </div>
                <div class="panel-body">
                	<div id="news" class="chart"></div>
                    <i class="glyphicon glyphicon-option-horizontal pull-right"></i>                    
                </div>
            </div>
        </div>
        <div class="col-md-4 col-xs-12 col-sm-4">
        	<div class="panel panel-default panelCustomBlock">
            	<div class="panel-heading">
                	<h4 class="panel-title"><span class="coreIcons eventsIcon">events</span></h4>
                </div>
                <div class="panel-body">
                	<div id="events" class="chart"></div>
                    <i class="glyphicon glyphicon-option-horizontal pull-right"></i>                    
                </div>
            </div>
        </div>
        <div class="col-md-4 col-xs-12 col-sm-4">
        	<div class="panel panel-default panelCustomBlock">
            	<div class="panel-heading">
                	<h4 class="panel-title"><span class="coreIcons trainingIcon">trainings</span></h4>
                </div>
                <div class="panel-body">
                	<div id="trainings" class="chart"></div>
                    <i class="glyphicon glyphicon-option-horizontal pull-right"></i>                    
                </div>
            </div>
        </div>
        <div class="col-md-4 col-xs-12 col-sm-4">
        	<div class="panel panel-default panelCustomBlock">
            	<div class="panel-heading">
                	<h4 class="panel-title"><span class="coreIcons debateIcon">debates</span></h4>
                </div>
                <div class="panel-body">
                	<div id="debates" class="chart"></div>
                    <i class="glyphicon glyphicon-option-horizontal pull-right"></i>                    
                </div>
            </div>
        </div>
    </div>
</div>
<script src="dist/coreDashboard/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="dist/coreDashboard/js/bootstrap.js" type="text/javascript"></script>
<script src="dist/coreDashboard/js/coreDashboard.js" type="text/javascript"></script>
<script src="dist/coreDashboard/Plugins/Highcharts/highcharts.js" type="text/javascript"></script>
<script type="text/javascript">

 //GLOBAL VARIABLES.
	var globalUserId = '${sessionScope.USER.registrationID}';
	var globalUserTypeId;
	var globalUserAccessLevelId;
	var globalUserAccessLevelValues = [];
	
	getUserBasicDetails();
	function onLoadCalls(){
		getMainCommitteeCountDetails();
		getCommitteesWiseLevelsBasedDetails();
	}
	
	$(document).ready(function(){
		$(".eventsheader").hide();
		
	$('#meetings').highcharts({
		colors:['#D6EB59','#FF5C54','#D6EB59'],
		chart: {
			plotBackgroundColor: null,
			plotBorderWidth: null,
			plotShadow: false,
			type: 'pie'
		},
		title: {
                text: null
            },
		tooltip: {
			pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
		},
		legend: {
			enabled: true,
			floating: true,
			verticalAlign: 'top',
			align:'left',
			y:30,
			x:-20,
			width:50,
			useHtml: ' {point.x}'
		},
		plotOptions: {
			pie: {
				allowPointSelect: true,
				cursor: 'pointer',
				dataLabels: {
					enabled: false
				},
				showInLegend: true
			}
		},
		
		series: [{
			name: 'Brands',
			colorByPoint: true,
			data: [{
				name: 'Conducted',
				y: 46.33
			}, {
				name: 'Not Conducted',
				y: 24.03,
			}]
		}]
	});
	$('#news').highcharts({
		colors:['#90E3FA','#FF8A83','#D6EB59'],
		chart: {
			plotBackgroundColor: null,
			plotBorderWidth: null,
			plotShadow: false,
			type: 'pie'
		},
		title: {
                text: null
            },
		tooltip: {
			pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
		},
		legend: {
			enabled: true,
			floating: true,
			verticalAlign: 'top',
			align:'left',
			y:30,
			x:-20,
			width:50,
			useHtml: ' {point.x}'
		},
		plotOptions: {
			pie: {
				allowPointSelect: true,
				cursor: 'pointer',
				dataLabels: {
					enabled: false
				},
				showInLegend: true
			}
		},
		
		series: [{
			name: 'Brands',
			colorByPoint: true,
			data: [{
				name: 'Positive',
				y: 46.33
			}, {
				name: 'Negative',
				y: 24.03,
			}, {
				name: 'Neutral',
				y: 24.03,
			}]
		}]
	});
	$('#events').highcharts({
		colors:['#CBE7B9','#00C7F7','#D6EB59'],
		chart: {
			plotBackgroundColor: null,
			plotBorderWidth: null,
			plotShadow: false,
			type: 'pie'
		},
		title: {
                text: null
            },
		tooltip: {
			pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
		},
		legend: {
			enabled: true,
			floating: true,
			verticalAlign: 'top',
			align:'left',
			y:30,
			x:-20,
			width:50,
			useHtml: ' {point.x}'
		},
		plotOptions: {
			pie: {
				allowPointSelect: true,
				cursor: 'pointer',
				dataLabels: {
					enabled: false
				},
				showInLegend: true
			}
		},
		series: [{
			name: 'Brands',
			colorByPoint: true,
			data: [{
				name: 'Conducted',
				y: 46.33
			}, {
				name: 'Non Conducted',
				y: 24.03,
			}]
		}]
	});
	$('#trainings').highcharts({
		colors:['#CBE7B9','#00C7F7','#D6EB59'],
		chart: {
			plotBackgroundColor: null,
			plotBorderWidth: null,
			plotShadow: false,
			type: 'pie'
		},
		title: {
                text: null
            },
		tooltip: {
			pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
		},
		legend: {
			enabled: true,
			floating: true,
			verticalAlign: 'top',
			align:'left',
			y:30,
			x:-20,
			width:50,
			useHtml: ' {point.x}'
		},
		plotOptions: {
			pie: {
				allowPointSelect: true,
				cursor: 'pointer',
				dataLabels: {
					enabled: false
				},
				showInLegend: true
			}
		},
		series: [{
			name: 'Brands',
			colorByPoint: true,
			data: [{
				name: 'Upcoming',
				y: 46.33
			}, {
				name: 'Running',
				y: 24.03,
			}, {
				name: 'Completed',
				y: 10.38
			}]
		}]
	});
	$('#debates').highcharts({
		colors:['#CBE7B9','#00C7F7','#D6EB59'],
		chart: {
			plotBackgroundColor: null,
			plotBorderWidth: null,
			plotShadow: false,
			type: 'pie'
		},
		title: {
                text: null
            },
		tooltip: {
			pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
		},
		legend: {
			enabled: true,
			floating: true,
			verticalAlign: 'top',
			align:'left',
			y:30,
			x:-20,
			width:50,
			useHtml: ' {point.x}'
		},
		plotOptions: {
			pie: {
				allowPointSelect: true,
				cursor: 'pointer',
				dataLabels: {
					enabled: false
				},
				showInLegend: true
			}
		},
		series: [{
			name: 'Brands',
			colorByPoint: true,
			data: [{
				name: 'TDP',
				y: 46.33
			}, {
				name: 'OTHERS',
				y: 24.03,
			}]
		}]
	});
	});
	
	
     
	$(document).on("click",".committeesExpandIcon",function(){
		$(".committeesBlock").toggleClass("col-md-4").toggleClass("col-md-8")
	});
    
    function getCommitteesWiseLevelsBasedDetails(){
       
       var basicCommitteeIdsArray= [];
       basicCommitteeIdsArray.push(1);
       basicCommitteeIdsArray.push(2);
       basicCommitteeIdsArray.push(3);
	   
       var startDateString = '01/01/2015';
	   var endDateString = '28/07/2016';
	   var state = 'AP';
	   
 	   var jsObj ={ 
	                userAccessLevelId : globalUserAccessLevelId,
 			        userAccessLevelValuesArray : globalUserAccessLevelValues,
 			        state : state,
 			        basicCommitteeIdsArray:basicCommitteeIdsArray,
 			        startDateString : startDateString,
 			        endDateString :   endDateString
 			      };
 	   
 	   $.ajax({
 			type : 'POST',
 			url : 'getCommitteesWiseLevelsBasedDetailsAction.action',
 			dataType : 'json',
 			data : {task:JSON.stringify(jsObj)}
 		}).done(function(result){
 			//alert(123);
 		});
 	}
    
</script>
</body>
</html>