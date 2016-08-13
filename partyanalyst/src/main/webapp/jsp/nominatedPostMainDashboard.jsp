<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>NOMINATED POST PROFILE CREATION</title>
<link href="dist/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="dist/NominatedPost/custom.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
<link href="dist/Plugins/Chosen/chosen.css" rel="stylesheet" type="text/css"/>
<link href="dist/Plugins/Datatables/datatables.css" rel="stylesheet" type="text/css"/>
<link href="dist/NominatedPost/Slick/slick.css" rel="stylesheet" type="text/css"/>
<link href="dist/NominatedPost/Slick/slick-theme.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="container">
	<div class="row">
    	<div class="col-md-12 col-xs-12 col-sm-12">
        	<ol class="breadcrumb">
            	<li><i class="glyphicon glyphicon-home"></i></li>
                <li>Nominated Post Main Dashboard</li>
            </ol>
        </div>
        <div class="col-md-12 col-xs-12 col-sm-12">
        	<h3 class="headingColor">Overview</h3>
        </div>
        <div class="col-md-12 col-xs-12 col-sm-12">
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
        <div class="col-md-12 col-sm-12 col-xs-12 m_top20">
        	<div class="row">
            	<div class="col-md-6 col-xs-12 col-sm-10 col-sm-offset-1 col-md-offset-0">
                	<div class="panel panel-default">
                    	<div class="panel-heading bg_ff">
                        	<h4 class="panel-title text-capital">caste wise position overview</h4>
                        </div>
                        <div class="panel-body pad_0">
                        	<div>
                              <!-- Nav tabs -->
                              <ul class="nav nav-tabs navTabsSelection navTabSelectionSlick selectionTab" role="tablist">
                                <li role="presentation" class="active"><a href="#overall" attr_href_id="overall" aria-controls="overall" attr_level_value="0" class="castePositionCls" role="tab" data-toggle="tab">Overall</a></li>
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
                        	<h4 class="panel-title text-capital">caste group wise position overview</h4>
                        </div>
                        <div class="panel-body pad_0">
                        	<div>

                              <!-- Nav tabs -->
                              <ul class="nav nav-tabs navTabsSelection navTabSelectionSlick selectionTab" role="tablist">
                                <li role="presentation" class="active"><a href="#overallCasteGroup" attr_href_id="overallCasteGroup" aria-controls="overallCasteGroup" attr_level_value="0" class="casteGroupCls" role="tab" data-toggle="tab">Overall</a></li>
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
    	<div class="col-md-12 col-sm-12 col-xs-12">
        	<div class="bg_ff pad_10">
            	<div class="row">
                	<div class="col-md-2 col-sm-4 col-xs-12">
                    	<label>Position</label>
                        <select class="chosenSelect" id="positionId">
                        	<option value="0">ALL</option>
                        </select>
                    </div>
                    <div class="col-md-2 col-sm-4 col-xs-12">
                    	<label>Location Level</label>
                        <select class="chosenSelect" id="locationLevelId">
                        	<option value="0">ALL</option>  
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
                    	<label>Caste Group</label>
                        <select class="chosenSelect" id="casteGroupId">
                        	<option value="0">ALL</option>
                        </select>
                    </div>
                    <div class="col-md-2 col-sm-4 col-xs-12">
                    	<label>Position Status</label>
                        <select class="chosenSelect" id="positionStatusId">
                        	<option value="0">ALL</option>  
                        </select>
                    </div>
					
					<div class="col-md-2 col-sm-2 col-xs-12">  
                    	<button type="button" value="getDetails" class="btn btn-success m_top10" onclick="getAllStatusDetails();" id="statusDetailsId">SUBMIT</button>
					</div>
            </div>
        </div>
        <div class="col-md-12 col-xs-12 col-sm-12">
        	<div class="row m_top10">
            	<div class="col-md-12 col-xs-12 col-sm-12">
                	<h4>Demographics Positions Overview</h4>
                    <h4 class="showingResult">showing results</h4>
                    <ul class="geoGrpahicBreadCrumb">
                        <li><span>All</span> Positions</li>
                        <li><span>All</span> locaion level</li>
                        <li><span>All</span> department</li>
                        <li><span>All</span> corporation</li>
                        <li><span>All</span> caste groups</li>
                        <li><span>G.O Issued</span></li>
                    </ul>
                </div>
                <div class="col-md-12 col-xs-12 col-sm-12 m_top10">
                	<div class="pad_15 bg_ff">
                    	<div class="row">
                        	<div class="col-md-2 col-xs-12 col-sm-2" id="totalMaleAndFemaleId">
                            	<table class="table table-bordered table-condensed bg_F1" style="font-size:13px !important;">
                                	<tr>
                                    	<td rowspan="2" style="vertical-align:middle">
                                        	<p class="text-capital">total</p>
                                            <p id="totalMaleFemaleId"></p>
                                        </td>
                                        <td>
                                        	<p class="text-capital">male</p>
                                            <p id="totalMaleId"></p>
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
                            <div class="col-md-4 col-xs-12 col-sm-5" id="totalCasteId">
                            </div>
                            <div class="col-md-6 col-xs-12 col-sm-5" id="totalAgeWiseId">
                            </div>
                        </div>
                        <div class="row m_top20">
                        	<div class="col-md-8 col-xs-12 col-sm-8">
                            	<div class="table-responsive">
                                	<table class="table table-bordered text-center text-capital" id="casteAndAgeWiseId">
                                    </table>
                                </div>
                                <div class="table-responsive m_top20" id="casteNameWiseTotlaCntsId">
                                </div>
                            </div>
                            <div class="col-md-4 col-xs-12 col-sm-4" id="casteGrpDivId" style="display:none;">
                            	<div style="border:1px solid #ddd;padding:10px">
                                	<h4 class="panel-title">CASTE GROUP</h4>
	                            	<div id="casteGroup" style="height:220px;"></div>
                                </div>
                                <div style="border:1px solid #ddd;padding:10px" class="m_top20">
                                	<h4 class="panel-title">CASTE GROUP</h4>
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
                	<h4>Grographic Positions Overview</h4>
                    <h4 class="showingResult">showing results</h4>
                    <ul class="geoGrpahicBreadCrumb">
                        <li><span>All</span> Positions</li>
                        <li><span>All</span> locaion level</li>
                        <li><span>All</span> department</li>
                        <li><span>All</span> corporation</li>
                        <li><span>All</span> caste groups</li>
                        <li><span>G.O Issued</span></li>
                    </ul>
                </div>
                <div class="col-md-12 col-xs-12 col-sm-12 m_top10">
                	<div class="pad_10 bg_ff">
                    	<div class="table-responsive" id="statePositionId">
							<!--
                        	<table class="table table-condensed" style="border:1px solid #ddd;">
                            	<thead class="bg_ef text-capital">
                                	<th>District</th>
                                    <th>Finalised Positions Total</th>
                                    <th>Male</th>
                                    <th>Female</th>
                                    <th>20-29 AGE</th>
                                    <th>30-39 AGE</th>
                                    <th>50-59 AGE</th>  
                                    <th>60+ AGE</th>
                                    <th></th>
                                </thead>
                                <tbody>
                                	<tr class="text-capital">
                                    	<td>east godavari</td>
                                        <td>10</td>
                                        <td>10</td>
                                        <td>10</td>
                                        <td>10</td>
                                        <td>10</td>
                                        <td>10</td>
                                        <td>10</td>
                                        <td><i class="glyphicon glyphicon-plus changeIconClass"></i></td>
                                    </tr>
                                    <tr class="showHideTr" id="districtPositionId">
                                    </tr>
                                </tbody>
                            </table>
							-->
                        </div>
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
 /*$(function () {
    $('#casteGroup').highcharts({
		colors:['#E58D45','#867DC0','#65A7E1','#7DC1C2'],
        chart: {
            type: 'pie',
            options3d: {
                enabled: true,
                alpha: 45
            }
        },
        plotOptions: {
            pie: {
                innerSize: 100,
                depth: 45
            }
        },
        series: [{
            name: 'CASTE GROUP',
            data: [
                ['OC', 25],
                ['BC', 25],
                ['SC', 25],
                ['ST', 25]
            ]
        }]
    }); */
	/*  $('#Positions').highcharts({
		color:['#FED501','#E58D45','#DD675D','#65A7E1','#7DC1C0','#5C2D25'],
        chart: {
            type: 'column'
        },
        xAxis: {
			labels: {
                enabled: false
            },
            categories: [
                'Jan',
                'Feb',
                'Mar',
                'Apr',
                'May',
                'Jun',
                'Jul',
                'Aug',
                'Sep',
                'Oct',
                'Nov',
                'Dec'
            ],
            crosshair: true
        },
        yAxis: {
            min: 0,
            title: {
                text: ' '
            },
			labels: {
                enabled: false
            }
        },
        tooltip: {
            headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
            pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                '<td style="padding:0"><b>{point.y:.1f} mm</b></td></tr>',
            footerFormat: '</table>',
            shared: true,
            useHTML: true
        },
        plotOptions: {
            column: {
                pointPadding: 0.2,
                borderWidth: 0
            }
        },
        series: [{
            name: 'Tokyo',
            data: [49.9, 71.5, 106.4, 129.2, 144.0, 176.0, 135.6, 148.5, 216.4, 194.1, 95.6, 54.4]

        }]
    });  */
	/* $('#applications').highcharts({
		colors:['#E58D45','#867DC0','#65A7E1','#7DC1C2'],
        chart: {
            type: 'pie',
            options3d: {
                enabled: true,
                alpha: 45
            }
        },
        plotOptions: {
            pie: {
                innerSize: 100,
                depth: 45
            }
        },
        series: [{
            name: 'CASTE GROUP',
            data: [
                ['OC', 25],
                ['BC', 25],
                ['SC', 25],
                ['ST', 25]
            ]
        }]
    }); */
	/*  $('#casteWisePositions').highcharts({
        chart: {
            type: 'bar'
        },
        xAxis: {
            categories: ['Reddy', 'Kappu', 'Valma', 'Nayudu', 'Madiga'],
            title: {
                text: null
            }
        },
        yAxis: {
            min: 0,
			gridLineWidth: 0,
            minorGridLineWidth: 0,
            min: 0,
            title: {
                text: 'POSITIONS',
                align: 'high'
            },
            labels: {
                overflow: 'justify'
            }
        },
        tooltip: {
            valueSuffix: ' CASTE'
        },
        plotOptions: {
            bar: {
                dataLabels: {
                    enabled: true
                }
            }
        },
        legend: {
            layout: 'vertical',
            align: 'right',
            verticalAlign: 'top',
            x: -40,
            y: 80,
            floating: true,
            borderWidth: 1,
            backgroundColor: ((Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'),
            shadow: true
        },
        credits: {
            enabled: false
        },
        series: [{
            name: 'CASTE',
            data: [107, 31, 635, 203, 2]
        }]
    }); 
});*/
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
 /*   var gaugeChart = AmCharts.makeChart("chartdiv1", {
  "type": "gauge",
  "theme": "light",
  "axes": [{
    "axisAlpha": 0,
    "tickAlpha": 0,
    "labelsEnabled": false,
    "startValue": 0,
    "endValue": 100,
    "startAngle": 0,
    "endAngle": 270,
    "bands": [{
      "color": "#fff",
      "startValue": 0,
      "endValue": 100,
      "radius": "100%",
      "innerRadius": "85%"
    }, {['100%','100%','88%','88%','75%','75%','65%','65%','50%','50%','35%','35%','20%','20%']
      "color": "#FDD401",
      "startValue": 0,
      "endValue": 80,
      "radius": "100%",
      "innerRadius": "95%",
      "balloonText": "90%"
    },{
      "color": "#fff",
      "startValue": 0,
      "endValue": 100,
      "radius": "88%",
      "innerRadius": "88%"
    }, {
      "color": "#E48D45",
      "startValue": 0,
      "endValue": 80,
      "radius": "88%",
      "innerRadius": "83%",
      "balloonText": "90%"
    },{
      "color": "#fff",
      "startValue": 0,
      "endValue": 100,
      "radius": "75%",
      "innerRadius": "75%"
    }, {
      "color": "#DE675D",
      "startValue": 0,
      "endValue": 80,
      "radius": "75%",
      "innerRadius": "70%",
      "balloonText": "90%"
    },{
      "color": "#fff",
      "startValue": 0,
      "endValue": 100,
      "radius": "65%",
      "innerRadius": "65%"
    }, {
      "color": "#65A6E2",
      "startValue": 0,
      "endValue": 80,
      "radius": "65%",
      "innerRadius": "60%",
      "balloonText": "90%"
    }, {
      "color": "#fff",
      "startValue": 0,
      "endValue": 100,
      "radius": "50%",
      "innerRadius": "50%"
    }, {
      "color": "#867CC0",
      "startValue": 0,
      "endValue": 35,
      "radius": "50%",
      "innerRadius": "45%",
      "balloonText": "35"
    }, {
      "color": "#fff",
      "startValue": 0,
      "endValue": 100,
      "radius": "35%",
      "innerRadius": "35%"
    }, {
      "color": "#7DC0C2",
      "startValue": 0,
      "endValue": 92,
      "radius": "35%",
      "innerRadius": "30%",
      "balloonText": "92"
    } , {
      "color": "#fff",
      "startValue": 0,
      "endValue": 100,
      "radius": "20%",
      "innerRadius": "0%"
    }, {
      "color": "#61342D",
      "startValue": 0,
      "endValue": 48,
      "radius": "20%",
      "innerRadius": "15%",
      "balloonText": "48%"
    } ]
  }],
  "allLabels": [{
    "text": "1 option",
    "x": "49%",
    "y": "5%",
    "size": 10,
    "bold": true,
    "color": "#333",
    "align": "right"
  }, {
    "text": "2 option",
    "x": "49%",
    "y": "10%",
    "size": 10,
    "bold": true,
    "color": "#333",
    "align": "right"
  }, {
    "text": "3 option",
    "x": "49%",
    "y": "15%",
    "size": 10,
    "bold": true,
    "color": "#333",
    "align": "right"
  }, {
    "text": "4 option",
    "x": "49%",
    "y": "20%",
    "size": 10,
    "bold": true,
    "color": "#333",
    "align": "right"
  }, {
    "text": "5 option",
    "x": "49%",
    "y": "26%",
    "size": 10,
    "bold": true,
    "color": "#333",
    "align": "right"
  }, {
    "text": "6 option",
    "x": "49%",
    "y": "33%",
    "size": 10,
    "bold": true,
    "color": "#333",
    "align": "right"
  }, {
    "text": "7 option",
    "x": "49%",
    "y": "40%",
    "size": 10,
    "bold": true,
    "color": "#333",
    "align": "right"
  } ],
  "export": {
    "enabled": false
  }
}); */  
</script>
</body>
</html>
