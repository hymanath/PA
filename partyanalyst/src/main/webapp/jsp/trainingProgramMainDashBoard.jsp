<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Training Program Dashboard</title>
<link href="dist/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="dist/css/custom.css" rel="stylesheet" type="text/css">
<link href="dist/scroll/jquery.mCustomScrollbar.css" rel="stylesheet" type="text/css">
<link href="http://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
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
                        <h3>TRAINING PROGRAMME DASHBOARD<span class="font-10">(<span class="font-10" id="titleName"></span>)</span>
                        <!--<select class="pull-right">
                        	<option>Select Program / View Other Program</option>
                        </select>-->
                        </h3>                            
                    </div>
                    <div class="panel-body" style="background-color:#EFF3F4">
						<section>
                        	<div class="row">
                            	<div class="col-md-6">
                                	<div class="panel panel-default"  id="summaryDivLeftPanel">
                                    	<div class="panel-heading bg_d">
                                        	<h4 class="text-center" style="font-weight:bold;" id="titleSummary">PROGRAMME SUMMARY</h4>
                                        </div>
										<div id="programSummaryDivId"></div>
                                    </div>	
                                </div>
								<div class="col-md-6">
									<div id="districtWiseDetailsId"></div>
								</div>
								<div class="col-md-6 pull-right">
                                	<table class="table table-bordered m_0 bg_ff">
                                    	<tr>
                                        	<td>
                                            	<span>TOTAL TRAINED MEMBERS <span class="pull-right" id="totalTrainedId">0</span></span>
                                            </td>
                                        </tr>
                                        <tr>
                                        	<td>
                                            	<span>MANDAL LEVEL MEMBERS<span class="pull-right" id="mandalLevelTotalId">0</span></span>
                                            </td>
                                        </tr>
                                        <tr>
                                        	<td>
                                            	<span>VILLAGE LEVEL MEMBERS<span class="pull-right" id="villageLevelTotalId">0</span></span>
                                            </td>
                                        </tr>
										<tr>
                                        	<td>
                                            	<span>DISTRICT LEVEL MEMBERS <span class="pull-right" id="districtLevelTotalId">0</span></span>
                                            </td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                        </section> 
                       
						<div class="row">
							<div class="col-md-12">
								<div class="panel panel-default">
									<div class="panel-heading  bg_e9">
										<h5 style="font-weight:bold;">TRAINER FEEDBACK ON TRAINEES</h5>
									</div>
									<div class="panel-body" id="feedbackDetailsId"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
            </div>
        </div>
	</div>
</main>
<footer>
		<img src="dist/img/footer.jpg" width="100%">
</footer>
<script src="dist/js/jquery-1.11.2.min.js" type="text/javascript"></script>
<script src="dist/js/bootstrap.js" type="text/javascript"></script>
<script src="dist/scroll/jquery.mCustomScrollbar.js" type="text/javascript"></script>
<script src="dist/scroll/jquery.mousewheel.js" type="text/javascript"></script>
<script type="text/javascript">

var programId = '${param.pd}';
var campId = '${param.cd}';
var batchId = '${param.bd}';
var dates = '${param.dts}';


$('.close-icon').click(function(){
		$('#collapseInnerOne').removeClass('in');
		$('#headingInnerOne h4 a').addClass('collapsed');
	});
	
$("#mainheading").html("TRAINING PROGRAMME DASHBOARD");

getattendedcountByFeedBacks();
getAttendedCountsByProgramOrCampOrBatch();

if(batchId!=null && batchId>0){
	getAttendedCountSummaryByBatch();
}

if((batchId==null || batchId==0) && (campId==null || campId==0) && (programId!=null && programId >0)){
	getProgramSummary();
}

if(batchId==null || batchId == 0 && campId!=null && campId>0){
	getCampSummary();
}


/* if(batchId==null || batchid==0){
	$("#summaryDivLeftPanel").hide();
}

if(programId!=null && programId>0 || campId!=null && campId>0){
	$("#summaryDivLeftPanel").show();
} */



function getAttendedCountsByProgramOrCampOrBatch()
{
	var jsObj={
		programId:programId,
		campId:campId,
		batchId:batchId,
		dates:dates
	}
	$.ajax({
		type:'POST',
		 url: 'getAttendedCountsByProgramOrCampOrBatchAction.action',
		 data : {task:JSON.stringify(jsObj)} ,
	}).done(function(result){
		if(result != null){
			buildAttendedCountByProgramOrCampOrBatch(result);
		}else{
			$("#districtWiseDetailsId").html("NO DATA AVAILABLE...");
		}
	});
}

function buildAttendedCountByProgramOrCampOrBatch(result)
{
	$("#districtWiseDetailsId").html('');
	var str='';
	
			str+='<table class="table table-bordered bg_ff">';
				str+='<thead class="bg_e9" >';
					str+='<th><h5 style="font-weight:bold;">LOCATION</h5></th>';
					for(var i in result[0].simpleVOList1){
						if(result[0].simpleVOList1[i].id==5 || result[0].simpleVOList1[i].id==6 || result[0].simpleVOList1[i].id==11){
							str+='<th><h5 style="font-weight:bold;">'+result[0].simpleVOList1[i].name.toUpperCase()+'LEVEL</h5></th>';
						}
					}
				str+='</thead>';
				str+='<tbody>';
				for(var i in result){
					
					if(result[i].name != "Others"){
						
							str+='<tr>';
								str+='<td>'+result[i].name+' <span class="pull-right">'+result[i].count+'</span></td>';
								for(var j in result[i].simpleVOList1){
									if(result[i].simpleVOList1[j].id==5 || result[i].simpleVOList1[j].id==6 || result[i].simpleVOList1[j].id==11){
									  str+='<td>'+result[i].simpleVOList1[j].count+'</td>';
									}
								}
							str+='</tr>';
						
					}
						
					}
				str+='</tbody>';
			str+='</table>';
		
	$("#districtWiseDetailsId").html(str);
	
	//total values setting block.
	$('#totalTrainedId').html(result[0].totalCount);
	if(result!=null){
	  if(result[0].simpleVOList2!=null && result[0].simpleVOList2.length>0){
	 	 for(var i in result[0].simpleVOList2){
			 if(result[0].simpleVOList2[i].id==5){
				 $('#mandalLevelTotalId').html(result[0].simpleVOList2[i].count);
			 }
			 if(result[0].simpleVOList2[i].id==6){
				 $('#villageLevelTotalId').html(result[0].simpleVOList2[i].count);
			 }
			 if(result[0].simpleVOList2[i].id==11){
				 $('#districtLevelTotalId').html(result[0].simpleVOList2[i].count);
			 }
		 }
	  }
	}
}
	
function getattendedcountByFeedBacks()
{	
	var jsObj={
		programId:programId,
		campId:campId,
		batchId:batchId,
		dates:dates
	}
	$.ajax({
		type:'POST',
		 url: 'getattendedcountByFeedBacksAction.action',
		 data : {task:JSON.stringify(jsObj)} ,
	}).done(function(result){
		if(result != null){
			buildAttendedCountByFeedBacks(result);
		}else{
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
	$("#feedbackDetailsId").html(str);
}

function getAttendedCountSummaryByBatch(){
	$("#titleSummary").html('BATCH ATTENDANCE SUMMARY');
	$("#programSummaryDivId").html('');
	
	var jsObj = {
		batchId:batchId,
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
			$("#programSummaryDivId").html("NO DATA AVAILABLE...");
		}
	});
}

function buildAttendedCountSummaryByBatch(result){
	
	var str='';
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
	
	$("#programSummaryDivId").html(str);
}

function getProgramSummary(){
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
		str+='<div class="panel-heading bg_d">';
		str+='</div>';
		str+='<div class="panel-body pad_0">';
			str+='<table class="table m_0">';
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
}

function getCampSummary(){
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
		str+='<div class="panel-heading bg_d">';
		str+='</div>';
		str+='<div class="panel-body pad_0">';
			str+='<table class="table m_0">';
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
}

getProgCampBatchNames();
function getProgCampBatchNames(){
	
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

</script>
</body>
</html>	