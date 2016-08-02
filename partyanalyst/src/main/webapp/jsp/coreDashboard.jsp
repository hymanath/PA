<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Core DashBoard</title>
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
            <span class="logoText">TDP PARTY</span>
          </a>
        </div>
    
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse " id="bs-example-navbar-collapse-1">
          <ul class="nav navbar-nav navbar-right">
            <li><a href="#"><i class="glyphicon glyphicon-bell"></i></a></li>
            <li class="dropdown profileDropDown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><img src="dist/coreDashboard/img/logo.png" class="profileImage"/> <span class="caret"></span></a>
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
                   
                    <div class="dropdown settingsDropDown">
                      <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"> <i class="glyphicon glyphicon-cog settingsIcon"></i></a>
                      <ul class="settingsDropDownOptions">
                        <li><a href="#">Action</a></li>
                        <li><a href="#">Another action</a></li>
                        <li><a href="#">Something else here</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="#">Separated link</a></li>
                      </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="navbar navbar-default navbarProfile">
    	<div class="container">
        	<div class="row">
            	<div class="col-md-4 col-xs-12 col-sm-4">
                	<p class="m_top10 text-capitalize">Dashboard</p>
                </div>
				<div class="col-md-4 col-xs-12 col-sm-4 col-xs-offset-2">
				<p class="m_top10">
                	<label class="radio-inline">
					  <input type="radio" name="inlineRadioOptions" id="cumulativeId" value="cumulative" checked> COMULATIVE
					</label>
					<label class="radio-inline">
					  <input type="radio" name="inlineRadioOptions" id="comparitiveId" value="comparitive"> COMPARITIVE
					</label>
					</p>
                </div>
                <div class="col-md-2 col-xs-12 col-sm-2">
                	<ul class="list-inline profileSelection">
                    	<li class="active" >
                        	<a  style="cursor:pointer;text-decoration:none;" class="stateCls">AP</a>
                        </li>
                        <li>
                        	<a  style="cursor:pointer;text-decoration:none;" class="stateCls">TS</a>
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
                	<h4 class="panel-title"><span class="coreIcons committeesIcon">committees</span>
                    	<i class="glyphicon glyphicon-option-vertical pull-right"></i>
                    </h4>
                </div>
				 <div class="panel-body">
                	<div class="row">
                    	<div class="col-md-12 col-xs-12 col-sm-12">
                        	<div id="committeesForComulative" class="chart"></div>
							<div class="row">
							<div id="CamparitiveBasicBlock" style ="display:none;"></div>
							<!--<div class="col-xs-6"><div id="committeesForCamparitive" class="" style ="display:none;width:100%;height:250px;"></div></div>
							<div class="col-xs-6"><div id="committeesForCamparitive1" class="" style ="display:none;width:100%;height:250px;"></div></div>-->
							
							
							</div>
							
                        </div>
						<div id="levelWiseComulativeForCommittees" style ="display:none;"></div>
						<div id="levelWiseComparativeForCommittees" style ="display:none;"></div>
						<!--<div class="col-md-4 col-xs-12 col-sm-4"><div id="committeesForCamparitivelarge" class="chart " style ="display:none;"></div></div>
						<div class="col-md-4 col-xs-12 col-sm-4"><div id="committeesForCamparitivelarge1" class="chart " style ="display:none;"></div></div>
						<div class="col-md-4 col-xs-12 col-sm-4"><div id="committeesForCamparitivelarge2" class="chart " style ="display:none;"></div></div>-->
						
                        <div class="col-md-12 col-xs-12 col-sm-12">
                        	<i class="glyphicon glyphicon-option-horizontal pull-right committeesExpandIcon " style ="cursor:pointer"></i>
                        </div>
                    </div>
                </div>
			</div>
        </div>
        <div class="col-md-4 col-xs-12 col-sm-4">
        	<div class="panel panel-default panelCustomBlock">
            	<div class="panel-heading">
                	<h4 class="panel-title"><span class="coreIcons meetingsIcon">meetings</span>
                    	<i class="glyphicon glyphicon-option-vertical pull-right"></i>
                    </h4>
                </div>
                <div class="panel-body">
                	<div id="meetings" class="chart"></div>
                    <i class="glyphicon glyphicon-option-horizontal pull-right expandIcon"></i>
                </div>
            </div>
        </div>
        <div class="col-md-4 col-xs-12 col-sm-4">
        	<div class="panel panel-default panelCustomBlock">
            	<div class="panel-heading">
                	<h4 class="panel-title"><span class="coreIcons newsIcon">news</span>
                    	<i class="glyphicon glyphicon-option-vertical pull-right"></i>
                    </h4>
                </div>
                <div class="panel-body">
                	<div id="news" class="chart"></div>
                    <i class="glyphicon glyphicon-option-horizontal pull-right expandIcon"></i>                    
                </div>
            </div>
        </div>
        <div class="col-md-4 col-xs-12 col-sm-4">
        	<div class="panel panel-default panelCustomBlock">
            	<div class="panel-heading">
                	<h4 class="panel-title"><span class="coreIcons eventsIcon">events</span>
                    	<i class="glyphicon glyphicon-option-vertical pull-right"></i>
                    </h4>
                </div>
                <div class="panel-body">
                	<div id="events" class="chart"></div>
                    <i class="glyphicon glyphicon-option-horizontal pull-right expandIcon"></i>                    
                </div>
            </div>
        </div>
        <div class="col-md-4 col-xs-12 col-sm-4">
        	<div class="panel panel-default panelCustomBlock">
            	<div class="panel-heading">
                	<h4 class="panel-title"><span class="coreIcons trainingIcon">trainings</span>
                    	<i class="glyphicon glyphicon-option-vertical pull-right"></i>
                    </h4>
                </div>
                <div class="panel-body">
                	<div id="trainings" class="chart"></div>
                    <i class="glyphicon glyphicon-option-horizontal pull-right expandIcon"></i>                    
                </div>
            </div>
        </div>
        <div class="col-md-4 col-xs-12 col-sm-4">
        	<div class="panel panel-default panelCustomBlock">
            	<div class="panel-heading">
                	<h4 class="panel-title"><span class="coreIcons debateIcon">debates</span>
                    	<i class="glyphicon glyphicon-option-vertical pull-right"></i>
                    </h4>
                </div>
                <div class="panel-body">
                	<div id="debates" class="chart"></div>
                    <i class="glyphicon glyphicon-option-horizontal pull-right expandIcon"></i>                    
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
	var globalState = 'TS';
	
	getUserBasicDetails();
	function onLoadCalls(){
		getCommitteesCumulativeBasicReportChart();
		getCommitteesCumulaticeOverallReportCharts();
		
	}
	
	$(document).ready(function(){
		
		//Main header remove
		$(".eventsheader").hide();
		blockHeights();
		buildBasicgraphs();
		
		$("#cumulativeId").prop("checked",true);
	});
	
	 
	$(document).on("click",".committeesExpandIcon",function(){
		$(".committeesExpandIcon").addClass("expandIcon");
		$(".committeesBlock").removeClass("col-md-4").addClass("col-md-12");
		if($("#cumulativeId").is(':checked')){
			$("#committeesForComulative").hide();
			$("#levelWiseComulativeForCommittees").show();
		}
		if($("#comparitiveId").is(':checked')){
			$("#CamparitiveBasicBlock").hide();
			$("#levelWiseComparativeForCommittees").show();
		
		}
		blockHeights();
	});
	$(document).on("click",".expandIcon",function(){
		$(".committeesBlock").addClass("col-md-4").removeClass("col-md-12");
		$(".committeesExpandIcon").removeClass("expandIcon");
		if($("#cumulativeId").is(':checked')){
			$("#committeesForComulative").show();
			$("#levelWiseComulativeForCommittees").hide();
			$("#levelWiseComparativeForCommittees").hide();
		}
		if($("#comparitiveId").is(':checked')){
			$("#CamparitiveBasicBlock").show();
			$("#levelWiseComparativeForCommittees").hide();
		}
		blockHeights();
	});
   $(document).on("click","#comparitiveId",function(){
	   $(".committeesBlock").addClass("col-md-4").removeClass("col-md-12");
		$("#committeesForComulative").hide();
		$("#CamparitiveBasicBlock").show();
		$("#levelWiseComulativeForCommittees").hide();
		$("#levelWiseComparativeForCommittees").hide();
		getCommitteesComparativeBascicReportChart();
		getCommitteesComparativeOverallReportChart();
	}); 
    $(document).on("click","#cumulativeId",function(){
		$(".committeesBlock").addClass("col-md-4").removeClass("col-md-12");
		$("#committeesForComulative").show();
		$("#CamparitiveBasicBlock").hide();
		$("#levelWiseComparativeForCommittees").hide();
		
	}); 
	
	
    $(".stateCls").click(function(event){
      $(".stateCls").parent().removeClass("active");
	  $(this).parent().addClass("active");
	  globalState = $(this).html();
	  event.preventDefault();
    });
</script>
</body>
</html>