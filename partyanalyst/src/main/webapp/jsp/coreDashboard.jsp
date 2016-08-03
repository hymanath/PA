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
          	<li><a href="#"><i class="glyphicon glyphicon-gift"></i></a></li>
            <li><a href="#"><i class="glyphicon glyphicon-bell"></i></a></li>
            <li class="dropdown profileDropDown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><img src="dist/coreDashboard/img/logo.png" class="profileImage"/> <span class="caret"></span></a>
              <ul class="dropdown-menu">
                <li><a href="#">My Profile</a></li>
                <li><a href="#">Change Password</a></li>
                <li role="separator" class="divider"></li>
                <li><a href="#">Sign Out</a></li>
              </ul>
            </li>
          </ul>
        </div><!-- /.navbar-collapse -->
      </div><!-- /.container-fluid -->
    </nav>
    <div class="navbar navbar-default navbarProfile">
    	<div class="container">
        	<div class="row">
            	<div class="col-md-7 col-xs-12 col-sm-7">
                	<ul class="nav navbar-nav navbar-left headerProfileName">
                        <li class="dropdown profileDropDown toggleViewIcon">
                          <a href="#">ASHOK DAKAVARAM 
                          	<span class="caretBackground">
                            	<span class="caret" style="margin-top: 9px;"></span>
                            </span>
                          </a>
                          <div class="dropdown-menu settingsDropDownOptionsView">
                          	<div class="input-group">
                            	<input type="text" class="form-control"/>
                                <span class="input-group-addon">
                                	<i class="glyphicon glyphicon-search"></i>
                                </span>
                            </div>
                            <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
                              <div class="panel panel-default panelProfileView">
                                <div class="panel-heading" role="tab" id="headingOne">
                                  <a role="button" class="collapsed profieViewCollapse" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                                    <h4 class="panel-title">
                                      GS VIew
                                      <span class="profileImageView">
                                      	<img src="dist/coreDashboard/img/logo.png" />
                                      </span>
                                    </h4>
                                  </a>
                                </div>
                                <div id="collapseOne" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">
                                  <div class="panel-body">
                                    <!---SUB VIEW-->
                                    <div class="panel-group" id="accordionSub" role="tablist" aria-multiselectable="true">
                                      <div class="panel panel-default panelProfileView">
                                        <div class="panel-heading" role="tab" id="headingOneSubView2">
                                          <a role="button" class="collapsed profieViewCollapse" data-toggle="collapse" data-parent="#accordionSub" href="#collapseOneSubView2" aria-expanded="true" aria-controls="collapseOneSubView2">
                                            <h4 class="panel-title">
                                              GS - Nageshwar Rao
                                              <span class="profileImageView">
                                                <img src="dist/coreDashboard/img/logo.png" />
                                              </span>
                                            </h4>
                                          </a>
                                        </div>
                                        <div id="collapseOneSubView2" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOneSubView2">
                                          <div class="panel-body">
                                                <!-- Sub Sub View -->
                                                    <div class="panel-group" id="accordionSubSub" role="tablist" aria-multiselectable="true">
                                                      <div class="panel panel-default panelProfileView">
                                                        <div class="panel-heading" role="tab" id="headingOneSubSubView2">
                                                          <a role="button" class="collapsed profieViewCollapse" data-toggle="collapse" data-parent="#accordionSubSub" href="#collapseOneSubSubView2" aria-expanded="true" aria-controls="collapseOneSubSubView2">
                                                            <h4 class="panel-title">
                                                              GS - G Jaya Nageshwar Reddy
                                                              <span class="profileImageView">
                                                                <img src="dist/img/logo.png" />
                                                              </span>
                                                            </h4>
                                                          settingsDropDownOptionsView                                                          </a>
                                                        </div>
                                                        <div id="collapseOneSubSubView2" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOneSubSubView2">
                                                          <div class="panel-body pad_15">
                                                            <ul class="radioStyling">
                                                              <li>
                                                                <input type="radio" id="f" name="selector">
                                                                <label for="f">MadakaSira - MLA</label>
                                                                
                                                                <div class="check"></div>
                                                              </li>
                                                              <li>
                                                                <input type="radio" id="s" name="selector">
                                                                <label for="s">Hindupur - MLA - Nandamuri BalaKrishna</label>
                                                                
                                                                <div class="check"><div class="inside"></div></div>
                                                              </li>
                                                            </ul>
                                                          </div>
                                                        </div>
                                                      </div>
                                                      <div class="panel panel-default panelProfileView">
                                                        <div class="panel-heading" role="tab" id="headingTwoSubSubView1">
                                                            <a class="collapsed profieViewCollapse" role="button" data-toggle="collapse" data-parent="#accordionSubSub" href="#collapseTwoSubSubView1" aria-expanded="false" aria-controls="collapseTwoSubSubView1">
                                                              <h4 class="panel-title">
                                                                 GS - B Buchaiah Chowdary
                                                                 <span class="profileImageView">
                                                                    <img src="dist/coreDashboard/img/logo.png" />
                                                                  </span>
                                                              </h4>
                                                            </a>
                                                        </div>
                                                        <div id="collapseTwoSubSubView1" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwoSubSubView1">
                                                          <div class="panel-body">
                                                            
                                                          </div>
                                                        </div>
                                                      </div>
                                                      <div class="panel panel-default panelProfileView">
                                                        <div class="panel-heading" role="tab" id="headingThreeSubSubView">
                                                            <a class="collapsed profieViewCollapse" role="button" data-toggle="collapse" data-parent="#accordionSubSub" href="#collapseThreeSubSubView" aria-expanded="false" aria-controls="collapseThreeSubSubView">
                                                              <h4 class="panel-title">
                                                                 GS - B T Naidu
                                                                 <span class="profileImageView">
                                                                    <img src="dist/coreDashboard/img/logo.png" />
                                                                  </span>
                                                              </h4>
                                                            </a>
                                                        </div>
                                                        <div id="collapseThreeSubSubView" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThreeSubSubView">
                                                          <div class="panel-body">
                                                            
                                                          </div>
                                                        </div>
                                                      </div>
                                                    </div>
                                                <!-- Sub Sub View End-->
                                          </div>
                                        </div>
                                      </div>
                                      <div class="panel panel-default panelProfileView">
                                        <div class="panel-heading" role="tab" id="headingTwoSubView1">
                                            <a class="collapsed profieViewCollapse" role="button" data-toggle="collapse" data-parent="#accordionSub" href="#collapseTwoSubView1" aria-expanded="false" aria-controls="collapseTwoSubView1">
                                              <h4 class="panel-title">
                                                 GS - B Buchaiah Chowdary
                                                 <span class="profileImageView">
                                                    <img src="dist/coreDashboard/img/logo.png" />
                                                  </span>
                                              </h4>
                                            </a>
                                        </div>
                                        <div id="collapseTwoSubView1" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwoSubView1">
                                          <div class="panel-body">
                                            
                                          </div>
                                        </div>
                                      </div>
                                      <div class="panel panel-default panelProfileView">
                                        <div class="panel-heading" role="tab" id="headingThreeSubView">
                                            <a class="collapsed profieViewCollapse" role="button" data-toggle="collapse" data-parent="#accordionSub" href="#collapseThreeSubView" aria-expanded="false" aria-controls="collapseThreeSubView">
                                              <h4 class="panel-title">
                                                 GS - B T Naidu
                                                 <span class="profileImageView">
                                                    <img src="dist/coreDashboard/img/logo.png" />
                                                  </span>
                                              </h4>
                                            </a>
                                        </div>
                                        <div id="collapseThreeSubView" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThreeSubView">
                                          <div class="panel-body">
                                            
                                          </div>
                                        </div>
                                      </div>
                                    </div>
                                    <!--SUB VIEW END-->
                                  </div>
                                </div>
                              </div>
                              <div class="panel panel-default panelProfileView">
                                <div class="panel-heading" role="tab" id="headingTwo">
                                    <a class="collapsed profieViewCollapse" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                                      <h4 class="panel-title">
                                          District view
                                          <span class="profileImageView">
                                            <img src="dist/coreDashboard/img/logo.png" />
                                          </span>
                                      </h4>
                                    </a>
                                </div>
                                <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
                                  <div class="panel-body">
                                    
                                  </div>
                                </div>
                              </div>
                              <div class="panel panel-default panelProfileView">
                                <div class="panel-heading" role="tab" id="headingThree">
                                    <a class="collapsed profieViewCollapse" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                                      <h4 class="panel-title">
                                          mp view
                                          <span class="profileImageView">
                                            <img src="dist/coreDashboard/img/logo.png" />
                                          </span>
                                      </h4>
                                    </a>
                                </div>
                                <div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
                                  <div class="panel-body">
                                    
                                  </div>
                                </div>
                              </div>
                            </div>
                          </div>
                        </li>
                    </ul>
                </div>
                <div class="col-md-3 col-xs-12 col-sm-3">
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
                   
                    <div class="settingsDropDown">
                      <a href="#"> <i class="glyphicon glyphicon-cog settingsIcon"></i></a>                      
                      <ul class="settingsDropDownOptions">
                      	<li><a href="#">Dashboard settings</a></li>
                        <li><a href="#">committee settings</a>
                        	<div class="settingsDropDownOptionsSub">
                            	<h4 class="panel-title text-capital text-center">committees block settings</h4>
                                <div class="m_top20">
                                    <ul class="radioStyling">
                                      <li>
                                        <input type="radio" id="f-option" name="selector">
                                        <label for="f-option">Cumulative</label>
                                        
                                        <div class="check"></div>
                                      </li>
                                      <li>
                                        <input type="radio" id="s-option" name="selector">
                                        <label for="s-option">Comparative</label>
                                        
                                        <div class="check"><div class="inside"></div></div>
                                      </li>
                                    </ul>
                                </div>
                                <div>
                                    <label class="m_top20">Select Period</label>
                                    <select class="select selectBoxStyle">
                                        <option>Till Date</option>
                                    </select>
                                </div>
                                <div class="m_top20">
                                	<label class="text-capital">select type of committees</label>
                                    <ul class="committeesSelect">
                                    	<li>
                                        	<div class="squaredOne">
                                              <input type="checkbox" value="None" id="squaredOne" name="check" />
                                              <label for="squaredOne"></label>
                                            </div>
                                            <span>Main Committee</span>
                                        </li>
                                        <li>
                                        	<div class="squaredOne">
                                              <input type="checkbox" value="None" id="squaredOne1" name="check" />
                                              <label for="squaredOne1"></label>
                                            </div>
                                            <span>Telugu Yuvatha</span>
                                        </li>
                                        <li>
                                        	<div class="squaredOne">
                                              <input type="checkbox" value="None" id="squaredOne2" name="check"/>
                                              <label for="squaredOne2"></label>
                                            </div>
                                            <span>Telugu Mahila</span>
                                        </li>
                                    </ul>
                                </div>
                                <div class="m_top20 text-center">
                                	<button class="btn btn-success btnYellow btn-lg">SUBMIT</button>
                                </div>
                            </div>
                        </li>
                        <li><a href="#">meetings</a></li>
                        <li><a href="#">party news</a></li>
                        <li><a href="#">govt news</a></li>
                      </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="navbar navbar-default navbarProfile">
    	<div class="container">
        	<div class="row">
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
				
                <div class="col-md-2 col-xs-12 col-sm-2 pull-right"  style="margin-top: -10px;">
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
							<div ><center ><img style="display: none;" src="images/icons/loading.gif" id="comparitiveLoadingId"></center></div>
							
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
	$(document).on("click",".settingsDropDown",function(){
		$(this).toggleClass("dropdownOpen")
	});
	$(document).on("click",".toggleViewIcon",function(){
		$(this).toggleClass("dropDownView")
	});
	$(document).on("click",".settingsDropDownOptions li,.toggleViewIcon .settingsDropDownOptionsView",function(e){
		e.stopPropagation();
	});
</script>
</body>
</html>