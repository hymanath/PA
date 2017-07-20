<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Training Program Dashboard</title>
<link href="dist/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="dist/css/custom.css" rel="stylesheet" type="text/css">
<link href="dist/scroll/jquery.mCustomScrollbar.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">

<style type="text/css">
.panel-group .panel
{
	margin-top:0px !important;
}
.panel .panel-default
{
	border-width:1px !important;
	border-color:#CCC
}
.panel-group .panel-default .panel-body
{
	padding:15px
}

.bg_yellow
{
	background:#F4F2E6 !important
}
.bg_ff
{background:#fff;}
footer{background-color:#5c2d25;color:#ccc;padding:30px}
header.trainingHeader {  
    background:url("dist/img/header-footer.png") no-repeat scroll center bottom / 100% auto  #fed501;
    background-origin: border-box;
    background-repeat: no-repeat;
    height: 71px;   
}
</style>
</head>
<body>
<header class="trainingHeader">
	<div class="container">
        <div class="row">
            <div class="col-md-2 col-xs-4 col-sm-1">
                <img src="dist/img/logo.png" class="img-responsive">
            </div>
            <div class="col-md-1 col-xs-1 col-sm-1">
                <img src="dist/img/CBN1.png" class="img-responsive">
            </div>
            <div class="col-md-6 col-xs-7 col-sm-7 text-center">               
                 <p class="header-text display-style" id="mainheading" style="font-size:30px;"></p>
            </div>
            <div class="col-md-1 col-xs-1 col-sm-1"><img src="dist/img/NTR1.png" class="img-responsive" />   
            </div>
			<div class="col-md-2 col-xs-1 col-sm-1">
				<div class="" style="color:white;margin-top: 5px;"><b> Welcome ${sessionScope.UserName} </b></div>
                    <a href="#" class="dropdown-toggle btn btn-default btn-xs m_top10" data-toggle="dropdown" aria-expanded="false" style="margin-top: 5px;">
                    Menu <img src="images/menu_icon.png" />
                    </a>
					<ul class="dropdown-menu" role="menu" aria-labelledby="drop6" style="    background-color: rgb(239, 64, 54);">
					   <!--<li><a href="mahanaduCadreVisitInfoAction.action"><span>ENTRY/EXIT DASHBOARD</span></a> </li>-->
					   <li><a href="dashBoardAction.action"><span>DASHBOARD</span></a> </li>
					   <!--<li><a href="callCenterTrainingAgentDashBoard.action"><span>CALLERS DASHBOARD</span></a> </li>-->
					   <li><a tabindex="-1" href="newlogoutAction.action">Sign Out</a></li>
					
                    </ul>   
            </div>			
        </div>       
    </div>
</header>
<main>
	<div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="panel panel-default" style="box-shadow:0px 0px 5px rgba(0,0,0,0.2)">
                    <div class="panel-heading bg_c">
                        <h4 class="panel-title"><b>TRAINING PROGRAMME DASHBOARD</b> <span class="font-12"> (<span class="text-danger" id="titleName"></span>)</span>
                        <!--<span class="pull-right">
							<input type="radio" value="completed" name="comOrAll" class="comAllRadio" id="completedRadio"/><label>&nbsp; Completed</label>
							<input type="radio" value="all" name="comOrAll" class="comAllRadio" id="allRadio"/><label> &nbsp;All</label>
						</span>-->
						<div class="col-sm-4 pull-right">
						<span class="" style="font-size:15px">Select Enrollment</span>
						<select id="enrlmntYrId" style="width: 108px;display:inline-block;padding:2px 6px;height:25px;margin-top: -3px;" id="">
							<option value="3">2014-2016</option> 
							<option value="4">2016-2018</option> 
						</select>
                        </h4> 
						</div>
                    </div>
					
                    <div class="panel-body" style="background-color:#EFF3F4">
						<section>
                        	<div class="row">
                            	<div class="col-md-8">
                                	<div class="panel panel-default"  id="summaryDivLeftPanel">
                                    	<div class="panel-heading bg_d">
                                        	<h4 class="text-center panel-title" style="font-weight:bold;" id="titleSummary">PROGRAMME SUMMARY</h4>
                                        </div>
										<div id="programSummaryDivId"></div>
										<center><img id="loadingImg3" src="images/icons/survey-details.gif" style="width:150px;height:150px;display:none;"/></center>
                                    </div>	
                                </div>
								<div class="col-md-4 pull-right">
                                	<table class="table table-bordered m_0 bg_ff">
                                    	<tr>
                                        	<td>
                                            	<span>TOTAL TRAINED MEMBERS <span class="pull-right" id="totalTrainedId">0</span></span>
                                            </td>
                                        </tr>
										<tr>
                                        	<td>
                                            	<span>DISTRICT LEVEL MEMBERS <span class="pull-right" id="districtLevelTotalId">0</span></span>
												<center><img id="loadingImg4" src="images/icons/survey-details.gif" style="width:150px;height:150px;display:none;"/></center>
                                            </td>
                                        </tr>
                                        <tr>
                                        	<td>
                                            	<span>MANDAL/TOWN/DIVISION LEVEL MEMBERS<span class="pull-right" id="mandalLevelTotalId">0</span></span>
                                            </td>
                                        </tr>
                                        <tr>
                                        	<td>
                                            	<span>VILLAGE/WARD LEVEL MEMBERS<span class="pull-right" id="villageLevelTotalId">0</span></span>
                                            </td>
                                        </tr>
										<tr>
                                        	<td>
                                            	<span>OTHERS<span class="pull-right" id="otherCommitteeId">0</span></span>
                                            </td>
                                        </tr>
										<tr>
											<td>&nbsp;</td>
										</tr>
										
                                    </table>
                                </div>
								<div class="col-md-12" style="background:#ababab;text-color:#ccc;">
									<center><div>
										<input type="radio" value="dist" checked class="distrconstdtls" name="distconst"/><label> DISTRICT</label>
										<input type="radio" value="const" class="distrconstdtls" name="distconst"/><label>  CONSTITUENCY</label>
									</div></center>
									<div id="districtWiseDetailsId"></div>
									<center><img id="loadingImg2" src="images/icons/survey-details.gif" style="width:150px;height:150px;display:none;"/></center>
								</div>
								
                            </div>
                        </section> 
                       
						<div class="row">
							<div class="col-md-12">
								<div class="panel panel-default">
									<div class="panel-heading  bg_e9">
										<h4 class="panel-title"><b>TRAINER FEEDBACK ON TRAINEES</b></h4>
									</div>
									<div class="panel-body" id="feedbackDetailsId"></div>
									<center><img id="loadingImg1" src="images/icons/survey-details.gif" style="width:150px;height:150px;display:none;"/></center>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12">
								<div class="panel panel-default">
									<div class="panel-heading  bg_e9">
										<h4 class="panel-title"><b>SURVEY DETAILS</b></h4>
									</div>
									<div class="panel-body" id="surveyDetailsId"></div>
									<center><img id="surveyDataLoadoing" src="images/icons/survey-details.gif" style="width:150px;height:150px;display:none;"/></center>
								</div>
							</div>
						</div>
					</div>
				</div>
            </div>
        </div>
	</div>
</main>

<div class="themeControll">

  <div class="linkinner">
  		<div class="row">
        	<div class="col-md-10 col-md-offset-1 m_top10">
                <label>Select Program Type</label>
                <select class="form-control custom-select" id="programTypeSelectId">
					<option value="all">All</option> 
                    <option value="c">Completed</option> 
					<option value="r">Running</option> 
					<option value="u">UpComming</option> 
			    </select>
            </div>
            <div class="col-md-10 col-md-offset-1">
                <label>Select Program</label>
                <select class="form-control custom-select" id="programSelectId">
                   <option value="1">Leadership Skills-2014-16</option> 
				   <option value="8">Leadership Skills-2016-18</option> 
                </select>
            </div>
            <div class="col-md-10 col-md-offset-1">
                <label>Select Center</label>
                <select class="form-control custom-select" onchange="getBatches();" id="centerSelectId">
					<option value="0">Select Center</option>
                	<option value="1">SVV Center</option>
                    <option value="2">EWK Center</option>
                    <option value="3">GPN Center</option>
                    <option value="4">AKKC Center</option>
                </select>
            </div>
			<div class="col-md-10 col-md-offset-1">
                <label>Select Batch</label>
                <select class="form-control custom-select" id="batchSelectId">
                   	<option value="0">Select Batch</option>
                </select>
            </div>
            <div class="col-md-10 col-md-offset-1 locationDiv" id="stateDiv" style="display:none;">
                <label>Select State</label>
                <select class="form-control custom-select locationCls" id="stateId" onchange="getDistrictsForStates();">
					<option value="">Select State</option>
                   	<option value="0">All</option>
					<option value="1">AndhraPradesh</option>
					<option value="36">Telangana</option>
                </select>
            </div>
            <div class="col-md-10 col-md-offset-1 locationDiv" id="districtDiv" style="display:none;">
                <label>Select District</label><img src='./images/icons/search.gif' class="offset7"  id="imgForDist" style="width:15px;height:15px;display:none;"/>
                <select class="form-control custom-select locationCls" id="districtId" onchange="getConstituenciesForDistricts(this.value);">
                   <option>Select District</option>
                </select>
            </div>
            <div class="col-md-10 col-md-offset-1 locationDiv" id="constituencyDiv" style="display:none;">
                <label>Select Constituency</label><img src='./images/icons/search.gif' class="offset7"  id="imgForConsti" style="width:15px;height:15px;display:none;"/>
                <select class="form-control custom-select locationCls" id="constituencyId" onchange="getMandalCorporationsByConstituency();">
                    <option>Select Constituency</option>
                </select>
            </div>
			
			<div class="col-md-10 col-md-offset-1 locationDiv" id="mandalDiv" style="display:none;">
                <label>Mandal/Town/Divison</label><img src='./images/icons/search.gif' class="offset7"  id="imgForMandl" style="width:15px;height:15px;display:none;"/>
                <select class="form-control custom-select locationCls" id="mandalId" onchange="getPanchayatWardByMandal();">
                   <option>Select Mandal/Town/Divison</option>
                </select>
            </div>
			<div class="col-md-10 col-md-offset-1 locationDiv" id="panchayatDiv" style="display:none;">
                <label>Village/Ward</label><img src='./images/icons/search.gif' class="offset7"  id="imgForPanc" style="width:15px;height:15px;display:none;"/>
                <select class="form-control custom-select locationCls" id="panchayatId">
                   <option>Select Village/Ward</option>
                </select>
            </div>
			
           
            <div class="col-md-10 col-md-offset-1 m_top20" style="margin-bottom:10px;">
			  <button class="btn btn-block btn-success btn-sm btn-custom" onClick="updateFunctions();">OK</button>
            </div>
        </div>
  </div>

  <p class="tbtn"> <i class="glyphicon glyphicon-filter"></i> FILTERS</p>
</div>

<footer>
		<p class="text-center">All &copy; 2015. Telugu Desam Party</p>
</footer>
<script src="dist/js/jquery-1.11.2.min.js" type="text/javascript"></script>
<script src="dist/js/bootstrap.js" type="text/javascript"></script>
<script src="dist/scroll/jquery.mCustomScrollbar.js" type="text/javascript"></script>
<script src="dist/scroll/jquery.mousewheel.js" type="text/javascript"></script>
<script type="text/javascript" src="js/jquery.dataTables.js"></script>
<link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"/> 
<script type="text/javascript">

var programId = '${param.pd}';
var campId = '${param.cd}';
var batchId = '${param.bd}';
var dates = '${param.dts}';
var callFrom = '${param.cf}';
var enrollmentYrId = '${param.eyi}';

if(callFrom=="c"){
	$("#completedRadio").prop("checked", true)
}

$('.close-icon').click(function(){
		$('#collapseInnerOne').removeClass('in');
		$('#headingInnerOne h4 a').addClass('collapsed');
	});
	
$("#mainheading").html("TRAINING PROGRAMME DASHBOARD");
$("#enrlmntYrId").val(enrollmentYrId);

if((batchId==null || batchId==0) && (campId==null || campId==0) && (programId!=null && programId >0)){
	//getProgramSummary();
}

if(batchId==null || batchId == 0 && campId!=null && campId>0){
	//getCampSummary();
}


/* if(batchId==null || batchid==0){
	$("#summaryDivLeftPanel").hide();
}

if(programId!=null && programId>0 || campId!=null && campId>0){
	$("#summaryDivLeftPanel").show();
} */

$(".tbtn").click(function(){
		$(".themeControll").toggleClass("active");
	});

function getAttendedCountsByProgramOrCampOrBatch(fromType,enrollmentYrId)
{
	$("#totalTrainedId").html("");
	$('#mandalLevelTotalId').html("");
	$('#villageLevelTotalId').html("");
	$('#districtLevelTotalId').html("");
	$('#otherCommitteeId').html("");
	$("#districtWiseDetailsId").html("");
	$("#loadingImg2").show();
	var enrollmentYrIds = [];
	enrollmentYrIds.push(enrollmentYrId);
	var programId = 0;
	var programIds =[];
			if(enrollmentYrIds == 4){
				programIds.push(8);
			}else{
				programIds.push(6,7,1,5);
			}
	var jsObj={
		//programId:programId,
		programIds:programIds,
		campId:campId,
		batchId:batchId,
		dates:dates,
		callFrom:callFrom,
		fromType:fromType,
		enrollmentYrIds:enrollmentYrIds
	}
	$.ajax({
		type:'POST',
		 url: 'getAttendedCountsByProgramOrCampOrBatchAction.action',
		 data : {task:JSON.stringify(jsObj)} ,
	}).done(function(result){
		if(result != null){
			buildAttendedCountByProgramOrCampOrBatch(result,fromType);
		}else{
			$("#loadingImg2").hide();
			$("#districtWiseDetailsId").html("NO DATA AVAILABLE...");
		}
	});
}

function buildAttendedCountByProgramOrCampOrBatch(result,fromType)
{
	var str='';
	
			str+='<table class="table table-bordered bg_ff" id="attendedTable">';
				str+='<thead class="bg_e9" >';
					str+='<th>LOCATION</th>';
					str+='<th>ATTENDED</th>';
					if(fromType=="dist"){
						str+='<th>DISTRICT LEVEL</th>';
					}else if(fromType=="const"){
						str+='<th>CONSTITUENCY LEVEL</th>';
					}
					
					str+='<th>MANDAL/TOWN/DIVISION LEVEL</th>';
					str+='<th>VILLAGE/WARD LEVEL</th>';
					str+='<th>OTHERS</th>';
				str+='</thead>';
				str+='<tbody>';
				
				for(var i in result){
					if(result[i].count != 0 || result[i].total != 0){
					  if(result[i].name != "Others"){
						
							str+='<tr>';
								str+='<td>'+result[i].name+'</td>';
								str+='<td>'+result[i].count+' IA&nbsp;-'+result[i].total+' NIA</td>';
								for(var j in result[i].simpleVOList1){
									if(result[i].simpleVOList1[j].id==5 || result[i].simpleVOList1[j].id==6 || result[i].simpleVOList1[j].id==11){
									  str+='<td>'+result[i].simpleVOList1[j].totalCount+'</td>';
									}
								}
							 str+='<td>'+result[i].total+'</td>';	
							str+='</tr>';
						
					}
					}		
				}
				str+='</tbody>';
			str+='</table>';
		
	$("#districtWiseDetailsId").html(str);
	$("#loadingImg2").hide();
	$("#attendedTable").dataTable({
		"iDisplayLength": 20,
		"aLengthMenu": [[20, 50, 100, -1], [20, 50, 100, "All"]]
		});
	
	
	//total values setting block.
	
	$('#totalTrainedId').html(result[0].totalCount);
	if(result!=null){
	  if(result[0].simpleVOList2!=null && result[0].simpleVOList2.length>0){
	 	 for(var i in result[0].simpleVOList2){
			 if(result[0].simpleVOList2[i].id==5){
				 $('#mandalLevelTotalId').html(result[0].simpleVOList2[i].totalCount);
			 }
			 if(result[0].simpleVOList2[i].id==6){
				 $('#villageLevelTotalId').html(result[0].simpleVOList2[i].totalCount);
			 }
			 if(result[0].simpleVOList2[i].id==11){
				 $('#districtLevelTotalId').html(result[0].simpleVOList2[i].totalCount);
			 }
		 }
		 $('#otherCommitteeId').html(result[0].simpleVOList2[0].total);
	  }
	}
}
	
function getattendedcountByFeedBacks(enrollmentYrId)
{
	$("#feedbackDetailsId").html("");
	$("#loadingImg1").show();
var enrollmentYrIds = [];
enrollmentYrIds.push(enrollmentYrId);
var programIds =[];
			if(enrollmentYrIds == 4){
				programIds.push(8);
			}else{
				programIds.push(6,7,1,5);
			}
	var jsObj={
		campId:campId,
		batchId:batchId,
		callFrom:callFrom,
		dates:dates,
		enrollmentYrIds:enrollmentYrIds,
		programIds:programIds
	}
	$.ajax({
		type:'POST',
		 url: 'getattendedcountByFeedBacksAction.action',
		 data : {task:JSON.stringify(jsObj)} ,
	}).done(function(result){
		if(result != null){
			buildAttendedCountByFeedBacks(result);
		}else{
			$("#loadingImg1").hide();
			$("#feedbackDetailsId").html("NO DATA AVAILABLE...");
		}
	});
}

function buildAttendedCountByFeedBacks(result)
{
	$("#feedbackDetailsId").html('');
	var str='';
	str+='<div class="row">';
	
	str+='<div class="col-md-8">';
		str+='<table class="table m_0 m_top10 table-bordered">';
			str+='<thead class="bg_e9">';
				str+='<th></th>';
				str+='<th>Total</th>';
				for(var j in result.list[0].subList){
					str+='<th>'+result.list[0].subList[j].name+'</th>';
				}
			str+='</thead>';
			str+='<tr>';
				str+='<td>Leadership Level</td>';
				str+='<td>'+result.list[0].count+'</td>';
				for(var j in result.list[0].subList){
					str+='<td>'+result.list[0].subList[j].count+'</td>';
				}
			str+='</tr>';
		str+='</table>';
		str+='</div>';
		str+='<div class="col-md-4">';
		str+='<table class="table table-bordered bg_ff">';
			str+='<thead class=" bg_ff">';
				str+='<tr>';
				str+='<th style="width:50%">Achievements - '+result.achievementCount+'</th>';
				str+='</tr>';
				str+='<tr>';
				str+='<th>Goals - '+result.gaoalCount+'</th>';
				str+='</tr>';
			str+='</thead>';
		str+='</table>';
		str+='</div>';
		str+='</div>';
		
		str+='<div class="row">';
		str+='<div class="col-md-8">';
		str+='<table class="table table-bordered m_top10">';
			str+='<thead class="bg_e9">';
				str+='<th></th>';
				str+='<th>Total</th>';
				for(var j in result.list[1].subList){
					str+='<th>'+result.list[1].subList[j].name+'</th>';
				}
			str+='</thead>';
			str+='<tr>';
				str+='<td>Communication Skills</td>';
				str+='<td>'+result.list[1].count+'</td>';
				for(var j in result.list[1].subList){
					str+='<td>'+result.list[1].subList[j].count+'</td>';
				}
			str+='</tr>';
			str+='<tr>';
				str+='<td>Leadership Skills</td>';
				str+='<td>'+result.list[2].count+'</td>';
				for(var j in result.list[2].subList){
					str+='<td>'+result.list[2].subList[j].count+'</td>';
				}
			str+='</tr>';
		str+='</table>';
		str+='</div>';
		str+='<div class="col-md-4" style="margin-top:10px;">';
		str+='<table class="table table-bordered m_0 m_top10">';
			str+='<thead class="bg_e9">';
				str+='<tr>';
				str+='<th colspan="5" class="text-center">HEALTH</th>';
				str+='</tr>';
				str+='<th></th>';
				str+='<th>Total</th>';
				for(var j in result.list[3].subList){
					str+='<th>'+result.list[3].subList[j].name+'</th>';
				}
			str+='</thead>';
			str+='<tr>';
				str+='<td>Health</td>';
				str+='<td>'+result.list[3].count+'</td>';
				for(var j in result.list[3].subList){
					str+='<td>'+result.list[3].subList[j].count+'</td>';
				}
			str+='</tr>';
		str+='</table>';	
		str+='</div>';
		str+='</div>';
		
		str+='<div class="row">';
		 str+='<div class="col-md-12">';
		   str+='<table class="table table-bordered m_0 m_top10">';
			str+='<thead class="bg_e9">';
			  str+='<th></th>';
			  str+='<th class="text-center">Yes</th>';
			  str+='<th class="text-center">No</th>';
			  str+='<th class="text-center">Not Answered</th>';
			  str+='<th class="text-center">Total</th>';
			str+='</thead>';
			str+='<tbody>';
			
			str+='<tr>';
			str+='<td>Smart Phone Exist</td>'
			
			for(var j in result.list[4].subList){
				if(result.list[4].subList[j].name=='Yes'){
					str+='<td class="text-center">'+result.list[4].subList[j].count+'<br>';
						str+='<table class="table table-bordered m_0">';
						str+='<tr>';
						str+='<th colspan="3" class="text-center">Having Whatsapp</td>';
						str+='<th colspan="3" class="text-center">Sharing In Whatsapp</td>';
						str+='</tr>';
						str+='<tr>';
						str+='<td>Yes</td>';
						str+='<td>No</td>';
						str+='<td>Not Answered</td>';
						str+='<td>Yes</td>';
						str+='<td>No</td>';
						str+='<td>Not Answered</td>';
						str+='</tr>';
						str+='<tr>';
						
						for(var i in result.list[5].subList){
							str+='<td>'+result.list[5].subList[i].count+'</td>';
						}
						for(var i in result.list[6].subList){
							str+='<td>'+result.list[6].subList[i].count+'</td>';
						}
						str+='</tr>';
						str+='</table>';
						
					str+='</td>';
					
				}else{
					str+='<td class="text-center">'+result.list[4].subList[j].count+'</td>';
				}
			}
			str+='<td>'+result.list[4].count+'</td>'
			str+='</tr>';
			
			str+='<tr>';
			str+='<td>Facebook Known</td>'
			for(var j in result.list[7].subList){
				str+='<td class="text-center">'+result.list[7].subList[j].count+'</td>'
			}
			str+='<td class="text-center">'+result.list[7].count+'</td>'
			str+='</tr>';
			
		    str+='</tbody>';
		   str+='</table>';
		 str+='</div>';
		str+='</div>';
		
	$("#feedbackDetailsId").html(str);
	$("#loadingImg1").hide();
}

function getAttendedCountSummaryByBatch(enrollmentYrId){
	$("#programSummaryDivId").html("");
	$("#loadingImg3").show();
	$("#titleSummary").html('BATCH WISE ATTENDANCE SUMMARY');
	var enrollmentYrIds =[];
	enrollmentYrIds.push(enrollmentYrId);
	var programIds =[];
			if(enrollmentYrIds == 4){
				programIds.push(8);
			}else{
				programIds.push(6,7,1,5);
			}
			var programId = 0;
	var jsObj = {
		//programId:programId,
		programIds:programIds,
		campId:campId,
		batchId:batchId,
		callFrom:callFrom,
		enrollmentYrIds:enrollmentYrIds,
		dates:dates
	}
	
	$.ajax({
		type:'POST',
		url :'getAttendedCountSummaryByBatchAction.action',
		data:{task:JSON.stringify(jsObj)},
	}).done(function(result){
		if(result != null){
			buildAttendedCountSummaryByBatch(result);
		}else{
			$("#loadingImg3").hide();
			$("#programSummaryDivId").html("NO DATA AVAILABLE...");
		}
	});
}

function buildAttendedCountSummaryByBatch(result){
	
	
	if(result!=null && result.length>0){
		var str='';
			str+='<table class="table table-bordered m_0">';
			str+='<thead class="bg_d">';
			str+='<tr>';
			str+='<th>Center</th>';
			str+='<th>Batch</th>';
			str+='<th>Day 1 Count</th>';
			str+='<th>Day 2 Count</th>';
			
			var flag=false;
			for(var i in result){
				if(result[i].simpleVOList1 !=null && result[i].simpleVOList1.length>0 && 
					 result[i].simpleVOList1.length>2){						 
						flag = true;
				}
			}
			if(flag){
				str+='<th>Day 3 Count</th>';
			}
			
			
			str+='<th>1 Day Attended Members</th>';
			str+='<th>2 Days Attended Members</th>';
			if(flag){
				str+='<th>3 Days Attended Members</th>';
			}			
			str+='</tr>';
			str+='</thead>';
			str+='<tbody>';
			for(var i in result){
				str+='<tr>';
				str+='<td>'+result[i].centerName+'</td>';
				str+='<td>'+result[i].batchName+'</td>';
				var innternalFlag=false;
				if(result[i].simpleVOList1 !=null && result[i].simpleVOList1.length<=2){
					if(flag){
						innternalFlag = true;
					}
				}
				for(var j in result[i].simpleVOList1){
					
					
					if(result[i].simpleVOList1[j].inviteeAttendedCount!=0  || result[i].simpleVOList1[j].nonInviteeAttendedCount!=0){
						var inviteeAttendedCount='<b>'+result[i].simpleVOList1[j].inviteeAttendedCount+'</b>'+" IA";
						var nonInviteeAttendedCount='<b>'+result[i].simpleVOList1[j].nonInviteeAttendedCount+'</b>'+" NIA";
						var counts=inviteeAttendedCount+" - "+nonInviteeAttendedCount;
						str+='<td>'+counts+'</td>';
					}else{
						str+='<td>0</td>';
					}
				}
				if(innternalFlag){
							str+='<td>0</td>';
				}
				if((result[i].oneDayInvitedAttendedCount!=null && result[i].oneDayInvitedAttendedCount!=0) || (result[i].oneDayNonInvitedAttendedCount!=null && result[i].oneDayNonInvitedAttendedCount!=0)){
				  str+='<td><b>'+result[i].oneDayInvitedAttendedCount+'</b> IA  -<b>'+result[i].oneDayNonInvitedAttendedCount+'</b> NIA </td>';	
				}else{
					str+='<td>0</td>'
				}
				if( (result[i].twoDaysInvitedAttendedCount!=null && result[i].twoDaysInvitedAttendedCount!=0) || (result[i].twoDaysNonInvitedAttendedCount!=null && result[i].twoDaysNonInvitedAttendedCount!=0)){
				  str+='<td><b>'+result[i].twoDaysInvitedAttendedCount+'</b> IA  -<b>'+result[i].twoDaysNonInvitedAttendedCount+'</b> NIA </td>';	
				}else{
					str+='<td>0</td>'
				}
				
				if(flag){
					if((result[i].threeDaysInvitedAttendedCount!=null && result[i].threeDaysInvitedAttendedCount!=0) || (result[i].threeDaysNonInvitedAttendedCount!=null && result[i].threeDaysNonInvitedAttendedCount!=0)){
						str+='<td><b>'+result[i].threeDaysInvitedAttendedCount+'</b> IA  -<b>'+result[i].threeDaysNonInvitedAttendedCount+'</b> NIA </td>';
					}else{
						str+='<td>0</td>'
					}
				}
				str+='</tr>';
			}
			str+='</tbody>';
			str+='</table>';
				$("#programSummaryDivId").html(str);
				$("#loadingImg3").hide();
		}else{
			$("#loadingImg3").hide();
			$("#programSummaryDivId").html("<h4 style='font-weight:bold;margin-left:10px;'>No Data Available</h4>");
		}
			
	/*var str='';
		 str+='<table class="table table-bordered text-center">';
			str+='<tr>';
				str+='<td colspan="2" class="bg_ff"><h4 class="m_0 text-center">TOTAL CONFIRMED PEOPLE - '+result.total+'</h4></td>';
			str+='</tr>';
			str+='<tr>';
				str+='<td colspan="2" class="bg_ff"><h4 class="m_0 text-center">TOTAL ATTENDED PEOPLE - '+result.count+'('+result.dateString+'%)</h4></td>';
			str+='</tr>';
			for(var i in result.simpleVOList1){
				str+='<tr class="bg_e9">';
					str+='<td colspan="2"><h5 class="m_0 text-center" style="font-weight:bold;">'+result.simpleVOList1[i].name+' ('+result.simpleVOList1[i].dateString+')</h5></td>';
				str+='</tr>';
				str+='<tr  class="bg_ff">';
					str+='<td>Attend - '+result.simpleVOList1[i].total+'</td>';
					str+='<td>Absent - '+result.simpleVOList1[i].count+'</td>';
				str+='</tr>';
			}
		str+='</table>'; 
	
	$("#programSummaryDivId").html(str);*/
}

/*function getProgramSummary(){
	$("#titleSummary").html('PROGRAMME SUMMARY');
	$("#programSummaryDivId").html('');
	var jsObj = {
		programId:programId,
		dates:dates
	}
	
	$.ajax({
		type:'POST',
		url :'getProgramSummaryAction.action',
		data:{task:JSON.stringify(jsObj)},
	}).done(function(result){
		if(result != null){
			buildProgramSummaryDetails(result);
		}else{
			$("#programSummaryDivId").html("NO DATA AVAILABLE...");
		}
	});
}

function buildProgramSummaryDetails(result)
{
	$("#programSummaryDivId").html('');
	var str='';
		str+='<div class="panel-body pad_0">';
			str+='<table class="table table-condensed m_0">';
				str+='<tr class="bg_yellow">';
					str+='<td>TOTAL CENTERS - '+result.id+'</td>';
					str+='<td>TOTAL BATCHES - '+result.count+'</td>';
					str+='<td>TOTAL MEMBERS - '+result.total+'</td>';
				str+='</tr>';
				for(var i in result.simpleVOList1){
					str+='<tr>';
						str+='<td>'+result.simpleVOList1[i].name+'</td>';
						str+='<td>BATCHES - '+result.simpleVOList1[i].count+'</td>';
						str+='<td>MEMBERS - '+result.simpleVOList1[i].total+'</td>';
					str+='</tr>';
				}
			str+='</table>';
		str+='</div>';
	$("#programSummaryDivId").html(str);
}*/

/*function getCampSummary(){
	$("#titleSummary").html('CAMP SUMMARY');
	$("#programSummaryDivId").html('');
	var jsObj = {
		programId:programId,
		campId:campId,
		dates:dates
	}
	
	$.ajax({
		type:'POST',
		url :'getCampSummaryAction.action',
		data:{task:JSON.stringify(jsObj)},
	}).done(function(result){
		if(result != null){
			buildCampSummaryDetails(result);
		}else{
			$("#programSummaryDivId").html("NO DATA AVAILABLE...");
		}
	});
}


function buildCampSummaryDetails(result)
{
	$("#programSummaryDivId").html('');
	var str='';
		str+='<div class="panel-body pad_0">';
			str+='<table class="table table-condensed m_0">';
				str+='<tr class="bg_yellow">';
					str+='<td>CENTER Name</td>';
					str+='<td>BATCHES COUNT</td>';
					str+='<td>ATTENDED COUNT</td>';
				str+='</tr>';
				str+='<tr>';
					str+='<td>'+result.name+'</td>';
					str+='<td>'+result.count+'</td>';
					str+='<td>'+result.total+'</td>';
				str+='</tr>';
			str+='</table>';
		str+='</div>';
	$("#programSummaryDivId").html(str);
}*/

function getProgCampBatchNames(){
   if($("#enrlmntYrId").val() == 3){
	   programId = 1;
   }else if($("#enrlmntYrId").val() == 4){
	   programId = 8;
   }
	var jsObj = {
		programId:programId,
		campId:campId,
		batchId:batchId
	}
	
	$.ajax({
		type:'POST',
		url :'getProgCampBatchNamesAction.action',
		data:{task:JSON.stringify(jsObj)},
	}).done(function(result){
		if(result!=null){
			var temp="";
			if(result.progName!=""){
				temp=temp+""+result.progName;
			}
			if(result.campName!=""){
				temp=temp+" - "+result.campName;
			}
			if(result.batchName!=""){
				temp=temp+" - "+result.batchName;
			}
			$("#titleName").html(temp);
		}
	});
}

function getSurveyDetails()
{
	$("#surveyDataLoadoing").show();
	
	$.ajax({
		//url: "http://localhost:8080/Survey/WebService/getTrainingSurveyDetails/"+programId+"/"+campId+"/"+batchId+""
		  url: "https://mytdp.com/Survey/WebService/getTrainingSurveyDetails/"+programId+"/"+campId+"/"+batchId+""
	}).then(function(result) {
		if(result != null && result.length > 0){
			buildSurveyDetails(result);
		}else{
			$("#surveyDataLoadoing").hide();
			$("#surveyDetailsId").html("NO DATA AVAILABLE...");
		}
	});
}

function buildSurveyDetails(result)
{
	$("#surveyDetailsId").html('');
	if(result != null && result.length > 0){
		var str='';
		str+='<div class="panel-group" style="margin-top:20px" id="accordion121" role="tablist" aria-multiselectable="true">';
		for(var i in result){
		 str+='<div class="panel panel-default">';
				str+='<div class="panel-heading" role="tab" id="headingOne'+i+'" attr_survy_divId="candiConstSurveyId'+i+'">';
					str+='<a role="button" class="accordion-toggle" data-toggle="collapse" data-parent="#accordion121" href="#collapseOne'+i+'" aria-expanded="true" aria-controls="collapseOne'+i+'">';
						str+='<h4 class="panel-title">';
							str+=''+result[i].name+'';
						str+='<i class="pull-right glyphicon glyphicon-triangle-bottom "></i></h4>';
					str+='</a>';
				str+='</div>';
				
				str+='<div id="collapseOne'+i+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne'+i+'">';
					str+='<div class="panel-body">';
					for(var j in result[i].verifierVOList)
					{
						str+='<table class="table table-bordered">';
						str+='<tr><td colspan="3" style="text-weight:bold">'+result[i].verifierVOList[j].name+'</td></tr>';
						str+='<b><tr><td>Option</td><td>Count</td><td>Percentage</td></tr></b>';
						for(var k in result[i].verifierVOList[j].verifierVOList){
							str+='<tr>';
								str+='<td>'+result[i].verifierVOList[j].verifierVOList[k].option+'</td>';
								str+='<td>'+result[i].verifierVOList[j].verifierVOList[k].count+'</td>';
								var percentage = Number(result[i].verifierVOList[j].verifierVOList[k].percentage);
								percentage = percentage.toFixed(2);
								str+='<td>'+percentage+'</td>';
							str+='</tr>';
						}
						str+='</table>';
					}
					str+='</div>';
				str+='</div>';
			  str+='</div>';
			  
		}
		str+='</div>';
	}
	
	$("#surveyDataLoadoing").hide();
	$("#surveyDetailsId").html(str);
}
	var selectedRadio='dist';
	$(".distrconstdtls").click(function(){
		selectedRadio=$(this).val();
		getAttendedCountsByProgramOrCampOrBatch($(this).val(),$("#enrlmntYrId").val());
	});
	
	/* $(".comAllRadio").click(function(){
		if($(this).val()=="all"){
			callFrom="all";
			getattendedcountByFeedBacks();
			getAttendedCountsByProgramOrCampOrBatch($('input[name=distconst]:checked').val());
			getAttendedCountSummaryByBatch();
		}else if($(this).val()=="completed"){
			callFrom="completed";
			getattendedcountByFeedBacks();
			getAttendedCountsByProgramOrCampOrBatch($('input[name=distconst]:checked').val());
			getAttendedCountSummaryByBatch();
		}
	}); */
	
	function getBatches(){
		var jsObj = {
			programType:$("#programTypeSelectId").val(),
			programId:$("#programSelectId").val(),
			campId:$("#centerSelectId").val()
		}
		
		$.ajax({
			type:'POST',
			url :'getBatchesAction.action',
			data:{task:JSON.stringify(jsObj)},
		}).done(function(result){
			if(result!=null && result.length>0){
				var str='';
				str+='<option value="0">Select Batch</option>';
				for(var i in result){
					str+='<option value="'+result[i].id+'">'+result[i].name+'</option>';
				}
				$("#batchSelectId").html(str);
			}
		});
	}
	$(document).on('change', '#enrlmntYrId', function(){
		var value = $(this).val();
		var valueText = $("#enrlmntYrId option:selected").html();
		if(value == 3){
			$('#programSelectId').html("<option value='1'>Training Program"+valueText+"</option>"); 
		}else if($(this).val() == 4){
			$('#programSelectId').html("<option value='8'>Training Program"+valueText+"</option>");
		}
		$("#programTypeSelectId").val('all');
		$("#centerSelectId").val(0);
		$("#batchSelectId").val(0);
		getattendedcountByFeedBacks($(this).val());
		getAttendedCountsByProgramOrCampOrBatch(selectedRadio,$(this).val());
		getAttendedCountSummaryByBatch($(this).val());
		getProgCampBatchNames();
		getSurveyDetails();
	});
	
	function updateFunctions(){
		programId = $("#programSelectId").val();
		campId = $("#centerSelectId").val();
		batchId = $("#batchSelectId").val();
		callFrom = $("#programTypeSelectId").val();
		if($(".themeControll").hasClass("active")){
			$(".themeControll").removeClass("active");
		}
		var enrollmentYrId = $("#enrlmntYrId").val();
		getattendedcountByFeedBacks(enrollmentYrId);
		getAttendedCountsByProgramOrCampOrBatch(selectedRadio,enrollmentYrId);
		getAttendedCountSummaryByBatch(enrollmentYrId);
		getProgCampBatchNames();
		getSurveyDetails();
	}
	onloadCalls();
	function onloadCalls(){
		var enrollmentYrId = $("#enrlmntYrId").val();
		var valueText = $("#enrlmntYrId option:selected").html();
		if(enrollmentYrId == 3){
			$('#programSelectId').html("<option value='1'>Training Program"+valueText+"</option>"); 
		}else if(enrollmentYrId == 4){
			$('#programSelectId').html("<option value='8'>Training Program"+valueText+"</option>");
		}
		getattendedcountByFeedBacks(enrollmentYrId);
		getAttendedCountsByProgramOrCampOrBatch(selectedRadio,enrollmentYrId);
		getAttendedCountSummaryByBatch(enrollmentYrId);
		getProgCampBatchNames();
		getSurveyDetails();
	}
</script>
</body>
</html>	