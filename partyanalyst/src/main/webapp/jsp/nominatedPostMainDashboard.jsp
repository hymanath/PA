<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
<link rel="stylesheet" type="text/css" href="styles/simplePagination-1/simplePagination.css"/>
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
	.popUpDetailsClickCls{
	cursor:pointer;
	text-decoration:underline;
	font-weight:bold;
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
                                <li role="presentation" id="postsTabId"  class=" statusSCls active"><a href="#positionsHrfId"  class="overviewHrfCls" attr_report_type="position" aria-controls="positions" role="tab" data-toggle="tab">Posts</a></li>
                                <li role="presentation" class="statusSCls" ><a href="#positionsHrfId"  class="overviewHrfCls" attr_report_type="application" attr aria-controls="applicationsTab" role="tab" data-toggle="tab">Applications</a></li>
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
                        	<h4 class="panel-title text-capital">caste wise position overview - finalized/g.o.issued</h4>
                        </div>
                        <div class="panel-body pad_0">
                        	<div>
                              <!-- Nav tabs -->
                              <ul class="nav nav-tabs navTabsSelection navTabSelectionSlick selectionTab casteWiseDetaislCls" role="tablist">
                               <li role="presentation" class="active selectCls"  id="allCasteId"><a href="#overall" attr_href_id="overall" aria-controls="overall" attr_level_value="2" class="castePositionCls" role="tab" data-toggle="tab" attr_heading="OVERALL">Overall</a></li>
                                <li role="presentation" class="selectCls" id="stateCasteId"><a href="#state" aria-controls="state" attr_href_id="state" role="tab" attr_level_value="2" class="castePositionCls" data-toggle="tab" attr_heading="STATE">State</a></li>
                                <li role="presentation"  class="selectCls"  id="districtCasteId"><a href="#district" aria-controls="district" attr_href_id="district" attr_level_value="3" class="castePositionCls" role="tab" data-toggle="tab" attr_heading="DISTRICT">district</a></li>
                                <li role="presentation"  class="selectCls"  id="assemblyCasteId"><a href="#constituency" aria-controls="constituency" attr_href_id="constituency" attr_level_value="4" class="castePositionCls" role="tab" data-toggle="tab" attr_heading="CONSTITUENCY">constituency</a></li>
								<li role="presentation"  class="selectCls"  id="mandalCasteId"><a href="#mndlmncpltyCrprtn" aria-controls="mndlmncpltyCrprtn" attr_href_id="mndlmncpltyCrprtn" attr_level_value="5" class="castePositionCls" role="tab" data-toggle="tab" attr_heading="MANDAL/MUNCIPALITY/CORPORATION">Mandal/Muncipality/Corporation</a></li>
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
                        	<h4 class="panel-title text-capital">caste group wise position overview - finalized/g.o.issued</h4>
                        </div>
                        <div class="panel-body pad_0">
                        	<div>

                              <!-- Nav tabs -->
                              <ul class="nav nav-tabs navTabsSelection navTabSelectionSlick selectionTab casteCategryWiseDetaislCls" role="tablist">
                                <li role="presentation" class="active castGrupCls" id="allCasteGrpId" ><a href="#overallCasteGroup" attr_href_id="overallCasteGroup" aria-controls="overallCasteGroup" attr_level_value="2" class="casteGroupCls" role="tab" data-toggle="tab" attr_heading="OVERALL">Overall</a></li>
                                <li role="presentation" class="castGrupCls" id="stateCasteGrpId" ><a href="#stateCasteGroup" aria-controls="stateCasteGroup" attr_href_id="stateCasteGroup" role="tab" attr_level_value="2" class="casteGroupCls" data-toggle="tab" attr_heading="STATE">State</a></li>
                                <li role="presentation" class="castGrupCls" id="districtCasteGrpId" ><a href="#districtCasteGroup" aria-controls="districtCasteGroup" attr_href_id="districtCasteGroup" attr_level_value="3" class="casteGroupCls" role="tab" data-toggle="tab" attr_heading="DISTRICT">district</a></li>
                                <li role="presentation"  class="castGrupCls" id="assemblyCasteGrpId" ><a href="#constituencyCasteGroup" aria-controls="constituencyCasteGroup" attr_href_id="constituencyCasteGroup" attr_level_value="4" class="casteGroupCls" role="tab" data-toggle="tab" attr_heading="CONSTITUENCY">constituency</a></li>
								<li role="presentation"  class="castGrupCls" id="mandalCasteGrpId" ><a href="#mndlmncpltyCrprtnCasteGroup" aria-controls="mndlmncpltyCrprtnCasteGroup" attr_href_id="mndlmncpltyCrprtnCasteGroup" attr_level_value="5" class="casteGroupCls" role="tab" data-toggle="tab" attr_heading="MANDAL/MUNCIPALITY/CORPORATION">Mandal/Muncipality/Corporation</a></li>
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
                    	<label>Application Status</label>
                        <select class="chosenSelect" id="positionStatusId">
                        	<option value="0">ALL</option>  
							<option value="3">Shortlisted</option>
							<option value="6">Ready For Final Review</option>
							<option value="5">Finalized</option>
							<option value="7">G.O Issued</option>  
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
                    <div style="margin-top:25px"><h4 class="showingResult demoGraphicsHideShow m_top10" style="ma">showing results</h4></div>
                    <ul class="geoGrpahicBreadCrumb demoGraphicsHideShow"  style="font-size:15px;">
                        <li><span class="locationCls">State</span> </li>
                        <li><span class="departmentCls">All department</span></li>
                        <li><span class="corporationCls">All corporation</span></li>
                        <li><span class="positionCls">All Positions</span></li>
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
                                <div class="table-responsive m_top20" id="casteNameWiseTotlaCntsId">
                                </div>
                            </div>
                            <div class="col-md-4 col-xs-12 col-sm-4" id="casteGrpDivId" style="display:none;">
                            	<div style="border:1px solid #ddd;padding:10px">
                                	<h4 class="panel-title">CASTE GROUP</h4>
	                            	<div id="casteGroup" style="height:220px;"></div>
                                </div>
                                <div style="border:1px solid #ddd;padding:10px" class="m_top20">
                                	<h4 class="panel-title">CASTE</h4>
									<div id="casteGrpDivIdScroll">
										<div id="casteWisePositions"></div>
									</div>
									<div id="casteWisePositionsScrollDiv"></div>
                                </div>
                            </div>
                        </div>   
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-12 col-xs-12 col-sm-12 geographicCls" style="display:none;">
        	<div class="row m_top10">
            	<div class="col-md-12 col-xs-12 col-sm-12">
                	<h3 class="headingColor text-capitalize">Geographic Positions Overview (Posts Location Wise)<span class="pull-right plusIconCircle geoGraphicOver2"><i class="glyphicon glyphicon-minus "></i></span></h3>
					<div style="margin-top:25px">
                    <h4 class="showingResult geoGraphicOverHideShow2 m_top10">showing results</h4></div>
                    <ul class="geoGrpahicBreadCrumb geoGraphicOverHideShow2" style="font-size:15px;">
                        <li><span class="locationCls">State</span> </li>
                        <li><span class="departmentCls">All department</span></li>
                        <li><span class="corporationCls">All corporation</span></li>
                        <li><span class="positionCls">All Positions</span></li>
                        <li><span class="casteGroupsCls">All caste groups</span></li>
                        <li><span class="gOssuedCls">G.O Issued</span></li>
                    </ul>
                </div>
                <div class="col-md-12 col-xs-12 col-sm-12 m_top10 geoGraphicOverHideShow2">
                	<div class="pad_10 bg_ff" id="tableschrollId">
                    	<div class="table-responsive" id="statePositionId"></div>
                    </div>
                </div>
            </div>
        </div>
		<div class="col-md-12 col-xs-12 col-sm-12 loctnLvlCntDivCls m_top20" style="display:none;">
		 <div class="row m_top10">
        		<!--<div class="col-md-12 col-xs-12 col-sm-12">
                	<h3 class="headingColor text-capitalize">Geographic Positions Overview (Candidates Location Wise)<span class="pull-right plusIconCircle geoGraphicOver"><i class="glyphicon glyphicon-minus "></i></span></h3>
					<div style="margin-top:25px">
                    <h4 class="showingResult geoGraphicOverHideShow m_top10">showing results</h4></div>
                   <!-- <ul class="geoGrpahicBreadCrumb geoGraphicOverHideShow" style="font-size:15px;">
                        <li><span class="locationCls">State</span> </li>
                        <li><span class="departmentCls">All department</span></li>
                        <li><span class="corporationCls">All corporation</span></li>
                        <li><span class="positionCls">All Positions</span></li>
                        <li><span class="casteGroupsCls">All caste groups</span></li>
                        <li><span class="gOssuedCls">G.O Issued</span></li>
                    </ul>-->	
					<!--<div class="col-md-3 col-sm-4 col-xs-12 geoGraphicOverHideShow">
                    	<input  type="radio" name="checkBoxName1" checked="true"  style="" value="1" class="radioBtnCls1 hideshowchkbxCls"/><span> Overall View </span>
                       <input  type="radio" name="checkBoxName1"   style="" value="2" class="radioBtnCls1" /><span> Demographical View </span>  
                   </div>	
					<div class="col-md-9 col-sm-8 col-xs-12 geoGraphicOverHideShow">					
						<div>
							<label><b> Geographical View : </b></label>
							<span class="form-inline" style="margin-left:10px;">
								<label>
									<input  type="radio" name="checkBoxName" checked="true"  style="" value="district" class="radioBtnCls radioBtnClsDist"/><span>District View</span>
								</label>
								<label style="margin-left:10px;">
									<input  type="radio" name="checkBoxName" style="" value="constituency" class="radioBtnCls"/><span> Constituency View </span>
								</label>
							</span>
						</div>
					</div>
					<div id="errMsgId" style="color:red;"></div>
					<div class="col-md-3 col-sm-4 col-xs-12 m_top10 districtLvlCls">
                    	<label>
						District Level : </label>
						
						<div id="distcsLevelId"></div> 
                    </div>
					<div class="col-md-2 col-sm-4 col-xs-12 m_top10 constituenyCls">
                    	<label>
						Constituency Level : </label> 
						<div>
							<select  id="consLevelId" class="chosenSelect form-control" multiple></select>
						</div> 
                    </div>
					<div class="col-md-2 col-sm-4 col-xs-12 m_top10 geoGraphicOverHideShow">
                    	<label>
						Position Level : </label>
                        <select class="chosenSelect" id="locationsLevelId">
                        </select>
                    </div>
					<div class="col-md-2 col-sm-4 col-xs-12 m_top10 geoGraphicOverHideShow">
                    	<label>Position</label>
                        <select class="chosenSelect" id="candPositonId">
                        	<option value="0">ALL</option>
                        </select>
                    </div>
					<div class="col-md-2 col-sm-4 col-xs-12" style="margin-top: 5px">
					<div class=" m_top10 pull-right" style="margin-left: 0px; margin-right: 31px;margin-top: 18px;display:none;" id="getDetailsId"> 
                    	<button type="button" value="getDetailsVal" class="btn btn-success m_top10" onclick="getLocationAndBoardLevelWiseCasteCatgryPostsData();" id="">SUBMIT</button>
					</div>
					</div>
                </div>-->
				<!--srujana-->
			<h3 class="headingColor text-capitalize">Geographic Positions Overview (Candidates Location Wise)<span class="pull-right plusIconCircle geoGraphicOver"><i class="glyphicon glyphicon-minus "></i></span></h3>
			<div class ="row loctnLvlCntDivBlockCls collapse in">
				<div class="col-md-3 col-sm-4 col-xs-12 geoGraphicOverHideShow" style="display :none;">
                    <input  type="radio" name="checkBoxName1"   style="" value="1" class="radioBtnCls1 hideshowchkbxCls" style="display :none;"/><span> Overall View </span>
                    <input  type="radio" name="checkBoxName1"  checked="true" style="" value="2" class="radioBtnCls1" style="display:none;" /><span> Demographical View </span>  
                </div>
				<div class="col-md-9 col-sm-8 col-xs-12 geoGraphicOverHideShow">					
					<div>
						<label><b> GEO-LEVEL-REPORT : </b></label>
						<span class="form-inline" style="margin-left:10px;">
							<label>
								<input  type="radio" name="checkBoxName" checked="true" style="" value="district" class="radioBtnCls radioBtnClsDist"/><span>District</span>
							</label>
							<label style="margin-left:10px;">
								<input  type="radio" name="checkBoxName" style="" value="constituency" class="radioBtnCls"/><span> Constituency</span>
							</label>
						</span>
					</div>
				</div>
				<div class="col-md-3 col-sm-4 col-xs-12 m_top10 districtLvlCls" style=" margin-right: 10px;">
				<div id="errMsgId" style="color:red;"></div>
					<label>
					District: </label>
					<!--<div id="errMsgId" style="color:red;"></div>-->
					<div id="distcsLevelId"></div> 
				</div>
				<div class="col-md-2 col-sm-4 col-xs-12 m_top10 constituenyCls">
					<label>
					Constituency: </label>
					<!--<div id="errMsgId" style="color:red;"></div>-->
					<div>
						<select  id="consLevelId" class="chosenSelect form-control" multiple></select>
					</div> 
				</div>
			</div>
			<div class="row loctnLvlCntDivBlockCls collapse in">
				<div class="col-md-12 col-xs-12 col-sm-12 m_top20">
					<div class="bg_cc" style="padding:10px;">
						<div class="row">
							<div class="col-md-2 col-xs-12 col-sm-6">
								<label>Position</label>
								<input id="" class="checkboxCls geoLevlCheckedCls"  efectiveId="PostnId" name="checkbox"  type="checkbox" value="positionVal">
								<select attr_no="" multiple class="chosenSelect deptBoardPostnCls" id="PostnId" name="" data-placeholder ="ALL">
									<option value="0">ALL</option>
								</select>
							</div>
							<div class="col-md-2 col-xs-12 col-sm-6">
								<label>Caste Group</label>
								<input id="" class=" checkboxCls geoLevlCheckedCls"  name="checkbox" efectiveId="casteGroupId1" type="checkbox" value="casteGroupVal" >
								<select attr_no="" multiple class="chosenSelect " id="casteGroupId1" name="" data-placeholder ="ALL" onchange="getCastListByCasteCatgryId(this.id);">
									<option value="0">ALL</option>
								</select>
						   </div>
							<div class="col-md-2 col-xs-12 col-sm-6">
								<label>Caste</label>
								<input id="" class=" checkboxCls geoLevlCheckedCls"  name="checkbox"  efectiveId ="casteId" type="checkbox" value="casteVal" />
								<select attr_no="" multiple class="chosenSelect " id="casteId" name="" data-placeholder ="ALL" onchange="buildDetails(this.value)">
									<option value="0">ALL</option>
								</select>
							</div>
							<div class="col-md-2 col-xs-12 col-sm-6">
								<label>Gender</label>
								<input id="" class=" checkboxCls geoLevlCheckedCls"  name="checkbox"  type="checkbox" value="genderVal" efectiveId ="genderId"> 
								<select class="form-control" id="genderId" name="" data-placeholder ="ALL"  style="display:none;height: 30px;">
									<option value="0">ALL</option>
									<option value="M">Male</option>
									<option value="F">FeMale</option>
								</select>
							</div>
							<div class="col-md-2 col-xs-12 col-sm-6">
								<label>Age</label>
								<input id="" class=" checkboxCls geoLevlCheckedCls"  name="checkbox"  type="checkbox" value="ageVal" efectiveId ="ageId" >
								<select attr_no="" multiple class="chosenSelect " id="ageId" name="" data-placeholder ="ALL">
									<option value="0">ALL</option>
								</select>
							</div>
							<div class="col-md-2 col-xs-12 col-sm-6">
								<button class="btn btn-success" onclick="getGeoLevelReportDetails();" id="detailsBtn"> Get Details </button>
							</div>
						</div>
						<div class="row">
							<div class="" style="margin-left: 15px;" id="ErrorsrDiv">
							
							</div>
						</div>
					</div>
				</div>
				
				<div class="col-md-12 col-xs-12 col-sm-12 m_top10 geoGraphicOverHideShow">
                	<div class="pad_10 bg_ff" id="">
                    	<div class="table-responsive" id="loctnLvlCntId" style="display : none;"></div>
                    	<div class="table-responsive" id="loctnLvlCntId1" style="display : none;"></div>
						<div class="table-responsive" id="totalReportDiv"></div>
                    </div>
                </div>
			</div>
        </div>
    </div>
</div>
<!-- Graphs click modal start  -->
<div class="modal fade" id="openModalDiv" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" style="z-index:9999;">
	<div class="modal-dialog" role="document" style="width:95%">
		<div class="modal-content">
			<div class="modal-header" style="background-color:#CCCCCC">
				<div class="row" >
					<div class="col-md-8">
						<h4 class="theme-title-color text-capital" id="TitleId" >Title</h4>
						<p id="subTitleId"></p>
					</div>
					<div class="col-sm-3 pull-right">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					</div>
				</div>
			</div>
			<div class="modal-body">        
				<div class="row">
					<div class="col-sm-12">
						<div id="openPostDetailsModalDivId"></div>
					</div>  
				</div>
				<div class="row paginationCls">
					<div class="col-md-12 m_top20">
						<div class="paginationId"></div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>
<div class="modal fade" id="departmentPostModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" style="z-index:9999;">
	<div class="modal-dialog" role="document" style="width:90%;margin:auto">
		<div class="modal-content">
			<div class="modal-header" style="background-color:#CCCCCC">
				<div class="row" >
					<div class="col-md-8">
						<h3 class="modal-title text-capitalize" id="deptHeadingId" >Title</h3>
					</div>
					<div class="col-sm-3 pull-right">
						<button type="button" class="close closeShowPdfCls" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					</div>
				</div>
			</div>
			<div class="modal-body">        
				<div class="row">
					<div class="col-sm-12">
						<div id="departmentDetailsModalDivId"></div>
					</div>  
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default closeShowPdfCls" data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>
<!-- Graphs click modal end  -->
<div class="modal fade" id="nominatedCandadteModalId" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	  <div class="modal-dialog modal-lg" role="document" style="width: 85%;">
		<div class="modal-content">
		  <div class="modal-header">
		  <div class="modelHeading"></div>
			<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		  </div>
		  <div class="modal-body">
		  <div id="loadingImgId"></div>
			<div class="table table-condensed " id="nominatedPostCandidateDetailsId">
	
			</div>
		  </div>
		  <div class="modal-footer">
			<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
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
<script type="text/javascript" src="js/simplePagination/simplePagination.js" ></script>
<script type="text/javascript">

$('.chosenSelect').chosen();
//$("#geoViewType").trigger("chosen:updated");
 $(".navTabSelectionSlick li a").on('click', function(e){
   // $(".navTabsSelection li").removeClass('active');
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
	$(".hideshowchkbxCls,.radioBtnClsDist").trigger('click');
	$(".loctnLvlCntDivBlockCls").collapse('toggle');
	$(".constituenyCls,.districtLvlCls,#getDetailsId").hide();
	$("#totalReportDiv").html('');
	$("#errMsgId").html('');
	$(".radioBtnCls1").trigger('click');
	$(".districtLvlCls").show();
	$(".geoLevlCheckedCls").each(function(){
		var efectiveId= $(this).attr('efectiveId');
		if(efectiveId == 'genderId'){
			if($(this).is(":checked") ){
				$(this).prop('checked',false);
				$("#"+efectiveId+"").hide();
			}
		}else{
			if($(this).is(":checked") ){
				$(this).prop('checked',false);
				$("#"+efectiveId+"_chosen").hide();
			}
		}
		
		
	});
});
$(document).on("click",".geoGraphicOver2",function(){
	$(this).find("i").toggleClass("glyphicon-plus").toggleClass("glyphicon-minus");
	$(".geoGraphicOverHideShow2").toggle();
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
