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
                 <p class="header-text display-style" id="mainheading" style="font-size:35px;"></p>               
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
                        <h4 class="panel-title">TRAINING PROGRAM DASHBOARD<span class="font-10">(Leadership Skills)</span>
                        <select class="pull-right">
                        	<option>Select Program / View Other Program</option>
                        </select>
                        </h4>                            
                    </div>
                    <div class="panel-body" style="background-color:#EFF3F4">
						<section>
                        	<div class="row">
                            	<div class="col-md-6">
                                	<div class="panel panel-default">
                                    	<div class="panel-heading bg_d">
                                        	<h4 class="panel-title text-center">
                                            	PROGRAM SUMMARY
                                            </h4>
                                        </div>
										<div id="programSummaryDivId"></div>
                                    </div>	
                                </div>
                                <div class="col-md-6">
                                	<table class="table table-bordered m_0 bg_ff">
                                    	<tr>
                                        	<td>
                                            	<h4>TOTAL TRAINED MEMBERS <span class="pull-right">1800</span></h4>
                                            </td>
                                        </tr>
                                        <tr>
                                        	<td>
                                            	<h4>MANDAL LEVEL MEMBERS<span class="pull-right">200</span></h4>
                                            </td>
                                        </tr>
                                        <tr>
                                        	<td>
                                            	<h4>VILLAGE LEVEL MEMBERS<span class="pull-right">800</span></h4>
                                            </td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                        </section> 
                        <section>
                        	<div class="row">
                            	<div id="attendedSummaryDetailsDivId"></div>
								<div id="districtWiseDetailsId"></div>
                            </div>
                        </section>
						<div class="row">
							<div class="col-md-12">
								<div class="panel panel-default">
									<div class="panel-heading  bg_e9">
										<h4 class="panel-title">TRAINER FEEDBACK ON TRAINEES</h4>
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
console.log(programId+"--"+campId+"--"+batchId+"--"+dates);
$('.close-icon').click(function(){
		$('#collapseInnerOne').removeClass('in');
		$('#headingInnerOne h4 a').addClass('collapsed');
	});
	
$("#mainheading").html("TRAINING PROGRAM DASHBOARD");

getattendedcountByFeedBacks();
getAttendedCountsByProgramOrCampOrBatch();
getAttendedCountSummaryByBatch();
getProgramSummary();

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
	str+='<div class="col-md-6">';
			str+='<table class="table table-bordered bg_ff">';
				str+='<thead class="bg_e9">';
					str+='<th><h4>LOCATION</h4></th>';
					str+='<th><h4>DISTRICT LEVEL</h4></th>';
					str+='<th><h4>MANDAL LEVEL</h4></th>';
					str+='<th><h4>VILLAGE LEVEL</h4></th>';
				str+='</thead>';
				str+='<tbody>';
				for(var i in result){
					if(result[i].name != "Others"){
						str+='<tr>';
							str+='<td>'+result[i].name+' <span class="pull-right">'+result[i].count+'</span></td>';
							for(var j in result[i].simpleVOList1){
								str+='<td>'+result[i].simpleVOList1[j].count+'</td>';
							}
						str+='</tr>';
						}
					}
				str+='</tbody>';
			str+='</table>';
		str+='</div>';
		
	$("#districtWiseDetailsId").html(str);
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

function getAttendedCountSummaryByBatch()
{
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
			$("#attendedSummaryDetailsDivId").html("NO DATA AVAILABLE...");
		}
	});
}

function buildAttendedCountSummaryByBatch(result)
{
	$("#attendedSummaryDetailsDivId").html('');
	var str='';
	str+='<div class="col-md-6">';
		str+='<table class="table table-bordered text-center">';
			str+='<tr>';
				str+='<td colspan="2" class="bg_ff"><h3 class="m_0 text-center">TOTAL CONFIRMED PEOPLE - '+result.total+'</h3></td>';
			str+='</tr>';
			str+='<tr>';
				str+='<td colspan="2" class="bg_ff"><h3 class="m_0 text-center">TOTAL ATTENDED PEOPLE - '+result.count+'('+result.dateString+'%)</h3></td>';
			str+='</tr>';
			for(var i in result.simpleVOList1){
				str+='<tr class="bg_e9">';
					str+='<td colspan="2"><h4 class="m_0 text-center">'+result.simpleVOList1[i].name+' ('+result.simpleVOList1[i].dateString+')</h4></td>';
				str+='</tr>';
				str+='<tr  class="bg_ff">';
					str+='<td>Attend - '+result.simpleVOList1[i].total+'</td>';
					str+='<td>Absent - '+result.simpleVOList1[i].count+'</td>';
				str+='</tr>';
			}
		str+='</table>';
	str+='</div>';
	
	$("#attendedSummaryDetailsDivId").html(str);
}

function getProgramSummary()
{
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
</script>
</body>
</html>	