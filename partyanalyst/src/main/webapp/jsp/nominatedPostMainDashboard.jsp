<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Nominated post dashboard</title>
<link href="dist/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="dist/NominatedPost/custom.css" rel="stylesheet" type="text/css">
<link href='https://fonts.googleapis.com/css?family=Roboto:400,500,700,700italic,900,900italic,400italic,500italic,300italic,300,100italic,100' rel='stylesheet' type='text/css'>
<link href="dist/Plugins/Chosen/chosen.css" rel="stylesheet" type="text/css"/>
<link href="dist/Plugins/Datatables/datatables.css" rel="stylesheet" type="text/css"/>
<link href="dist/NominatedPost/Slick/slick.css" rel="stylesheet" type="text/css"/>
<link href="dist/NominatedPost/Slick/slick-theme.css" rel="stylesheet" type="text/css"/>
<link href="dist/scroll/jquery.mCustomScrollbar.css" rel="stylesheet" type="text/css"/>
<style type="text/css">
    .onoffswitch {
        position: relative; width: 69px;
        -webkit-user-select:none; -moz-user-select:none; -ms-user-select: none;
    }
    .onoffswitch-checkbox {
        display: none;
    }
    .onoffswitch-label {
        display: block; overflow: hidden; cursor: pointer;
        border-radius: 20px;
    }
    .onoffswitch-inner {
        display: block; width: 200%; margin-left: -100%;
        transition: margin 0.3s ease-in 0s;
    }
    .onoffswitch-inner:before, .onoffswitch-inner:after {
        display: block; float: left; width: 50%; height: 30px; padding: 0; line-height: 30px;
        font-size: 14px; color: white; font-family: Trebuchet, Arial, sans-serif; font-weight: bold;
        box-sizing: border-box;
    }
    .onoffswitch-inner:before {
        content: "AP";
        padding-left: 10px;
        background-color: #846663; color: #FFFFFF;
    }
    .onoffswitch-inner:after {
        content: "TS";
        padding-right: 10px;
        background-color: #846663; color: #fff;
        text-align: right;
    }
    .onoffswitch-switch {
        display: block; width: 18px; margin: 6px;
        background: #FFFFFF;
        position: absolute; top: 0; bottom: 0;
        right: 35px;
        border-radius: 20px;
        transition: all 0.3s ease-in 0s; 
    }
    .onoffswitch-checkbox:checked + .onoffswitch-label .onoffswitch-inner {
        margin-left: 0;
    }
    .onoffswitch-checkbox:checked + .onoffswitch-label .onoffswitch-switch {
        right: 0px; 
    }
</style>
</head>
<body>
<div class="container">
	<div class="row">
    	<div class="col-md-12 col-xs-12 col-sm-12">
			<div class="col-md-11 col-xs-12 col-sm-11">
				<ul class="geoGrpahicBreadCrumb">
					<li style="text-transform: uppercase; font-weight: 500;" data-placement="bottom" data-toggle="tooltip" title="Nominated Posts Overview Details"><a href="nominatedPostApplicationReviewAction.action"><i class="glyphicon glyphicon-home" style="color:#fff;"></i></a></li>
					<li class="text-capital">Nominated Post Main Dashboard</li>
				</ul>
			</div>
			<div class="col-md-1 col-xs-12 col-sm-1">
				<div class="onoffswitch">
					<input type="checkbox" name="onoffswitch" class="onoffswitch-checkbox checkedSwitch" id="myonoffswitch" checked>
					<label class="onoffswitch-label stateCls" for="myonoffswitch">
						<span class="onoffswitch-inner"></span>
						<span class="onoffswitch-switch"></span>
					</label>
				</div>
			</div>
        </div>
        <div class="col-md-12 col-xs-12 col-sm-12 m_top20">
        	<h3 class="headingColor">Overview<span class="pull-right plusIconCircle overViewCls"><i class="glyphicon glyphicon-minus"></i></span></h3>
        </div>
        <div class="col-md-12 col-xs-12 col-sm-12 m_top10 overViewHideShow">
        	<div class="row">
            	<div class="col-md-2 col-xs-12 col-sm-2 bg_ff pad_right0">
				     <div id="positonDivId"></div>
                </div>
                <div class="col-md-4 col-xs-12 col-sm-4">
                	<div class="bg_ff pad_10">
                    	<h4 class="text-capital panel-title">all levels overview</h4>
                        <div class="row">
                        	<div class="col-md-12 col-xs-12 col-sm-12">
                              <ul class="nav nav-tabs navTabsSelection overviewUlCls" role="tablist">
                                <li role="presentation" class="active"><a href="#positionsHrfId"  class="overviewHrfCls" attr_report_type="position" aria-controls="positions" role="tab" data-toggle="tab">Position</a></li>
                                <li role="presentation"><a href="#positionsHrfId"  class="overviewHrfCls" attr_report_type="application" attr aria-controls="applicationsTab" role="tab" data-toggle="tab">Application</a></li>
                              </ul>
                              <div class="tab-content navTabsSelectionBody">
                                <div role="tabpanel" class="tab-pane active positionDivCls" id="positionsHrfId">
                                	<div id="chartdiv" style="height:300px;"></div>
                                </div>
                               <!-- <div role="tabpanel" class="tab-pane" id="applicationsHrfId">
                                	<div id="chartdiv1" style="height:300px;"></div>
                                </div>-->
                              </div>    
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-6 col-xs-12 col-sm-6">
					<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
					  <div class="panel panel-default panelDashboard">
						<div class="panel-heading" role="tab" id="headingOne">
						  <h4 class="panel-title locationLevelcollapseCls"  attr_collapse_href_id="collapseOne" attr_level_Id="2" attr_postion_highChart_id="sttLvlPstnHghChrtId" attr_app_highChart_id="sttLvlApplctnHghChrtId"  attr_collapse_level_Id="stateLevlId">
							<a role="button" class="panelDashboardIcon" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
							  state
							</a>
						  </h4>
						</div>
						<div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
						  <div class="panel-body">
							<div class="stateBodyDivCls"></div>
						  </div>
						</div>
					  </div>
					  <div class="panel panel-default panelDashboard">
						<div class="panel-heading" role="tab" id="headingTwo" >
						  <h4 class="panel-title locationLevelcollapseCls" attr_collapse_href_id="collapseTwo" attr_level_Id="3" attr_postion_highChart_id="distLvlPstnHghChrtId" attr_app_highChart_id="distLvlApplctnHghChrtId" attr_collapse_level_Id="districtLevelId">
							<a class="collapsed panelDashboardIcon" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
							  district
							</a>
						  </h4>
						</div>
						<div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
						  <div class="panel-body">
							<div class="districtBodyDivCls"></div>
						  </div>
						</div>
					  </div>
					  <div class="panel panel-default panelDashboard">
						<div class="panel-heading" role="tab" id="headingThree">
						  <h4 class="panel-title locationLevelcollapseCls"  attr_collapse_href_id="collapseThree" attr_postion_highChart_id="conLvlPstnHghChrtId" attr_app_highChart_id="conLvlApplctnHghChrtId" attr_level_Id="4" attr_collapse_level_Id="constituencyLevelId">
							<a class="collapsed panelDashboardIcon" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
							  constituency
							</a>
						  </h4>
						</div>
						<div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
						  <div class="panel-body">
							<div class="constituencyBodyDivCls"></div>
						  </div>
						</div>
					  </div>
					  <div class="panel panel-default panelDashboard">
						<div class="panel-heading" role="tab" id="headingFour">
						  <h4 class="panel-title locationLevelcollapseCls"   attr_postion_highChart_id="manLvlPstnHghChrtId" attr_app_highChart_id="manLvlApplctnHghChrtId" attr_collapse_href_id="collapseFour" attr_level_Id="5" attr_collapse_level_Id="manMunCorLevelId">
							<a class="collapsed panelDashboardIcon" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseFour" aria-expanded="false" aria-controls="collapseFour">
							 Mandal/Muncipality/Corporation
							</a>
						  </h4>
						</div>
						<div id="collapseFour" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingFour">
						  <div class="panel-body">
							<div class="manMunCorBodyDivCls"></div>
						  </div>
						</div>
					  </div>
					</div>
                </div>
            </div>
        </div>
        <div class="col-md-12 col-sm-12 col-xs-12 m_top20 overViewHideShow">
        	<div class="row">
            	<div class="col-md-6 col-xs-12 col-sm-10 col-sm-offset-1 col-md-offset-0">
                	<div class="panel panel-default">
                    	<div class="panel-heading bg_ff">
                        	<h4 class="panel-title text-capital">caste wise position overview(finalized/g.o.issued)</h4>
                        </div>
                        <div class="panel-body pad_0">
                        	<div>
                              <!-- Nav tabs -->
                              <ul class="nav nav-tabs navTabsSelection navTabSelectionSlick selectionTab" role="tablist">
                                <li role="presentation" class="active"><a href="#overall" attr_href_id="overall" aria-controls="overall" attr_level_value="2" class="castePositionCls" role="tab" data-toggle="tab">Overall</a></li>
                                <li role="presentation"><a href="#state" aria-controls="state" attr_href_id="state" role="tab" attr_level_value="2" class="castePositionCls" data-toggle="tab">State</a></li>
                                <li role="presentation"><a href="#district" aria-controls="district" attr_href_id="district" attr_level_value="3" class="castePositionCls" role="tab" data-toggle="tab">district</a></li>
                                <li role="presentation"><a href="#constituency" aria-controls="constituency" attr_href_id="constituency" attr_level_value="4" class="castePositionCls" role="tab" data-toggle="tab">constituency</a></li>
								<li role="presentation"><a href="#mndlmncpltyCrprtn" aria-controls="mndlmncpltyCrprtn" attr_href_id="mndlmncpltyCrprtn" attr_level_value="5" class="castePositionCls" role="tab" data-toggle="tab">Mandal/Muncipality/Corporation</a></li>
                              </ul>
                              <!-- Tab panes -->
                              <div class="tab-content navTabsSelectionBody">
							   <div id="castePostionDivId"></div>
                              </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-6 col-xs-12 col-sm-10 col-sm-offset-1 col-md-offset-0">
                	<div class="panel panel-default">
                    	<div class="panel-heading bg_ff">
                        	<h4 class="panel-title text-capital">caste group wise position overview(finalized/g.o.issued)</h4>
                        </div>
                        <div class="panel-body pad_0">
                        	<div>

                              <!-- Nav tabs -->
                              <ul class="nav nav-tabs navTabsSelection navTabSelectionSlick selectionTab" role="tablist">
                                <li role="presentation" class="active"><a href="#overallCasteGroup" attr_href_id="overallCasteGroup" aria-controls="overallCasteGroup" attr_level_value="2" class="casteGroupCls" role="tab" data-toggle="tab">Overall</a></li>
                                <li role="presentation"><a href="#stateCasteGroup" aria-controls="stateCasteGroup" attr_href_id="stateCasteGroup" role="tab" attr_level_value="2" class="casteGroupCls" data-toggle="tab">State</a></li>
                                <li role="presentation"><a href="#districtCasteGroup" aria-controls="districtCasteGroup" attr_href_id="districtCasteGroup" attr_level_value="3" class="casteGroupCls" role="tab" data-toggle="tab">district</a></li>
                                <li role="presentation"><a href="#constituencyCasteGroup" aria-controls="constituencyCasteGroup" attr_href_id="constituencyCasteGroup" attr_level_value="4" class="casteGroupCls" role="tab" data-toggle="tab">constituency</a></li>
								<li role="presentation"><a href="#mndlmncpltyCrprtnCasteGroup" aria-controls="mndlmncpltyCrprtnCasteGroup" attr_href_id="mndlmncpltyCrprtnCasteGroup" attr_level_value="5" class="casteGroupCls" role="tab" data-toggle="tab">Mandal/Muncipality/Corporation</a></li>
                              </ul>
                              <!-- Tab panes -->
                              <div class="tab-content navTabsSelectionBody">
							   <div id="casteGroupPostionDivId"></div>
                              </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    	<div class="col-md-12 col-sm-12 col-xs-12 overViewHideShow">
        	<div class="bg_ff pad_10">
            	<div class="row">
                    <div class="col-md-2 col-sm-4 col-xs-12">
                    	<label>Post Level</label>
                        <select class="chosenSelect" id="locationLevelId">
                        	
                        </select>
                    </div>
                    <div class="col-md-2 col-sm-4 col-xs-12">    
                    	<label>Department</label>
                        <select class="chosenSelect" id="departmentId">       
                        	<option value="0">ALL</option>
                        </select>
                    </div>
                    <div class="col-md-2 col-sm-4 col-xs-12">
                    	<label>Corporation</label>
                        <select class="chosenSelect" id="corporationId">
                        	<option value="0">ALL</option>
                        </select>
                    </div>
					<div class="col-md-2 col-sm-4 col-xs-12">
                    	<label>Position</label>
                        <select class="chosenSelect" id="positionId">
                        	<option value="0">ALL</option>
                        </select>
                    </div>
                    <div class="col-md-2 col-sm-4 col-xs-12">
                    	<label>Caste Group</label>
                        <select class="chosenSelect" id="casteGroupId">
                        	<option value="0">ALL</option>
                        </select>
                    </div>
                    <div class="col-md-2 col-sm-4 col-xs-12">
                    	<label>Position Status</label>
                        <select class="chosenSelect" id="positionStatusId">
                        	<option value="0">ALL</option>  
							<option value="3">SHORT LISTED</option>
							<option value="6">READY TO FINAL REVIEW</option>
							<option value="5">FINALIZED</option>
							<option value="7">G.O ISSUED</option>  
                        </select>
                    </div>
					
					<div class="col-md-2 col-sm-2 col-xs-12">  
                    	<button type="button" value="getDetails" class="btn btn-success m_top10" onclick="getAllStatusDetails();" id="statusDetailsId">SUBMIT</button>
					</div>
            </div>
        </div>
       </div>
        <div class="col-md-12 col-xs-12 col-sm-12">
        	<div class="row m_top10">
            	<div class="col-md-12 col-xs-12 col-sm-12">
                	<h3 class="headingColor text-capitalize">Demographics Positions Overview<span class="pull-right plusIconCircle demoGraphicsCls"><i class="glyphicon glyphicon-minus"></i></span></h3>
                    <h4 class="showingResult demoGraphicsHideShow m_top10">showing results</h4>
                    <ul class="geoGrpahicBreadCrumb demoGraphicsHideShow">
                        <li><span class="positionCls">All Positions</span></li>
                        <li><span class="locationCls">State</span> </li>
                        <li><span class="departmentCls">All department</span></li>
                        <li><span class="corporationCls">All corporation</span></li>
                        <li><span class="casteGroupsCls">All caste groups</span></li>
                        <li><span class="gOssuedCls">G.O Issued</span></li>
                    </ul>
                </div>
                <div class="col-md-12 col-xs-12 col-sm-12 m_top10 demoGraphicsHideShow">
                	<div class="pad_15 bg_ff">
                    	<div class="row">
                        	<div class="col-md-3 col-xs-12 col-sm-3" id="totalMaleAndFemaleId">
                            	<table class="table table-bordered table-condensed bg_F1 text-center" style="font-size:13px !important;">
                                	<tr>
                                    	<td rowspan="2" style="vertical-align:middle">
                                        	<p class="text-capital">total candidates</p>
                                            <p id="totalMaleFemaleId"></p>
                                        </td>
                                        <td>
                                        	<p class="text-capital">male</p>
                                            <p id="totalMaleId"></p>
                                        </td>
										<td rowspan="2" style="vertical-align:right">
                                        	<p class="text-capital">total Applied Posts</p>
                                            <p id="totalApplicationId"></p>
                                        </td>
                                    </tr>
                                    <tr>
                                    	<td>
                                        	<p class="text-capital">Female</p>
                                            <p id="totalFemaleId"></p>
                                        </td>
                                    </tr>
							    </table>
                            </div>
                            <div class="col-md-4 col-xs-12 col-sm-4" id="totalCasteId">
                            </div>
                            <div class="col-md-5 col-xs-12 col-sm-5" id="totalAgeWiseId">
                            </div>
                        </div>
                        <div class="row m_top20">
                        	<div class="col-md-8 col-xs-12 col-sm-8">
                            	<div class="table-responsive">
                                	<table class="table table-bordered text-center text-capital" id="casteAndAgeWiseId">
                                    </table>
                                </div>
                                <div class="table-responsive m_top20" id="casteNameWiseTotlaCntsId" style="display:none;">
                                </div>
                            </div>
                            <div class="col-md-4 col-xs-12 col-sm-4" id="casteGrpDivId" style="display:none;">
                            	<div style="border:1px solid #ddd;padding:10px">
                                	<h4 class="panel-title">CASTE GROUP</h4>
	                            	<div id="casteGroup" style="height:220px;"></div>
                                </div>
                                <div style="border:1px solid #ddd;padding:10px" class="m_top20">
                                	<h4 class="panel-title">CASTE</h4>
	                                <div id="casteWisePositions"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-12 col-xs-12 col-sm-12">
        	<div class="row m_top10">
            	<div class="col-md-12 col-xs-12 col-sm-12">
                	<h3 class="headingColor text-capitalize">Grographic Positions Overview<span class="pull-right plusIconCircle geoGraphicOver"><i class="glyphicon glyphicon-minus "></i></span></h3>
                    <h4 class="showingResult geoGraphicOverHideShow m_top10">showing results</h4>
                    <ul class="geoGrpahicBreadCrumb geoGraphicOverHideShow">
                        <li><span class="positionCls">All Positions</span></li>
                        <li><span class="locationCls">State</span> </li>
                        <li><span class="departmentCls">All department</span></li>
                        <li><span class="corporationCls">All corporation</span></li>
                        <li><span class="casteGroupsCls">All caste groups</span></li>
                        <li><span class="gOssuedCls">G.O Issued</span></li>
                    </ul>
                </div>
                <div class="col-md-12 col-xs-12 col-sm-12 m_top10 geoGraphicOverHideShow">
                	<div class="pad_10 bg_ff">
                    	<div class="table-responsive" id="statePositionId"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="dist/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="dist/js/bootstrap.js" type="text/javascript"></script>
<script src="dist/Plugins/Chosen/chosen.jquery.js" type="text/javascript"></script>
<script src="dist/Plugins/Datatables/datatables.js" type="text/javascript"></script>
<script src="dist/NominatedPost/Slick/slick.js" type="text/javascript"></script>
<script src="dist/Plugins/Highcharts/highcharts.js" type="text/javascript"></script>
<script src="dist/scroll/jquery.mCustomScrollbar.js"></script>
<script src="dist/scroll/jquery.mousewheel.js"></script>
<script src="dist/NominatedPost/Amcharts/amcharts.js"></script>
<script type="text/javascript" src="js/nominatedPosts/nominatedPostMainDashboard.js"></script>
<script src="dist/NominatedPost/Amcharts/gauge.js"></script>
<script type="text/javascript">
$('.chosenSelect').chosen();
 $(".navTabSelectionSlick li a").on('click', function(e){
    $(".navTabsSelection li").removeClass('active');
    $(this).addClass('active');
});
$(".navTabSelectionSlick").slick({
	  infinite: false,
	  speed: 300,
	  slidesToShow: 3,
	  centerMode: false,
	  variableWidth: true
}); 
$(document).on("click",".overViewCls",function(){
	$(this).find("i").toggleClass("glyphicon-plus").toggleClass("glyphicon-minus");
	$(".overViewHideShow").toggle();
});
$(document).on("click",".demoGraphicsCls",function(){
	$(this).find("i").toggleClass("glyphicon-plus").toggleClass("glyphicon-minus");
	$(".demoGraphicsHideShow").toggle();
});
$(document).on("click",".geoGraphicOver",function(){
	$(this).find("i").toggleClass("glyphicon-plus").toggleClass("glyphicon-minus");
	$(".geoGraphicOverHideShow").toggle();
});
$(document).on("click",".changeIconClass",function(){
	$(".changeIconClass").toggleClass("glyphicon-plus");
	$(this).closest('tr').next('tr.tableStrOuterCls').toggle();
	$(this).toggleClass("glyphicon-minus");
	
	var casteId =$(this).attr("attr_id");
	if($(this).hasClass("glyphicon-minus")){
		casteWisePositionsCountsByPosition(casteId,"expand");
	}else{
		casteWisePositionsCountsByPosition(casteId,"close");
	}
});
</script>
</body>
</html>
