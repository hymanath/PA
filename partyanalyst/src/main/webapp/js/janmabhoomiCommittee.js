var spinner = '<div class="row"><div class="col-sm-12"><div class="d2d-loader"><div class="loader"></div><img src="D2D_Assests/images/login_logo.png"/></div></div></div>';

onLoadCalls();
function onLoadCalls(){
	getJbCommitteeStatusCount();	
	 levelWiseCommitteeData("level")
}
function highcharts(id,type,data,plotOptions,title,tooltip,legend){
	'use strict';
	$('#'+id).highcharts({
		colors:['#FF9900','#8D4553','#CCCCCC','#F25C81','#0D9615'],
		chart: type,
		title: title,
		tooltip:tooltip,
		subtitle: {
			text: null
		},
		plotOptions: plotOptions,
		legend:legend,
		series: data
	});
}
function getJbCommitteeStatusCount(){

	var jsObj={
		"fromDateStr"	:"",
		"toDateStr"	    :""
	}
   $.ajax({
	  type : "POST",
	  url : "getJbCommitteeStatusCountAction.action",
	  dataType : 'json',
	  data : {task :JSON.stringify(jsObj)}
	}).done(function(result){ 
		if(result !=null){
			buildJbCommitteeMainBlockStatusCount(result);
		}
		if(result !=null && result.positinsList !=null && result.positinsList.length>0){
			buildCommitteeWiseDetailsBlock(result);
		}
	});
}

function buildJbCommitteeMainBlockStatusCount(result){
	
	var str='';
		str+='<div class="col-sm-2">';
			str+='<h4 class="pull-left barLengthCss" style="background-color:#F7931D;"> </h4>';
			str+='<h3 class="m_top20" style="margin-left: 15px;"><b>'+result.notStartedCommitteeCnt+'</b></h3>';
			str+='<h5 class="m_top20" style="margin-left: 15px;color:#12A89D;">'+result.notStartedCommitteePerc+' %</h5>';
			str+='<h5 class="m_top10" style="margin-left: 15px;line-height: 20px;""><b>Not-Started <br>Committess</b></h5>';
		str+='</div>';
		str+='<div class="col-sm-2">';
			str+='<h4 class="pull-left barLengthCss" style="background-color:#12A89D;"> </h4>';
			str+='<h3 class="m_top20" style="margin-left: 15px;"><b>'+result.inprogressCommitteeCnt+'</b></h3>';
			str+='<h5 class="m_top20" style="margin-left: 15px;color:#12A89D;">'+result.inprogressCommitteePerc+' %</h5>';
			str+='<h5 class="m_top10" style="margin-left: 15px;line-height: 20px;""><b> Inprogress/ <br/>Running <br> Committess</b></h5>';
		str+='</div>';
		str+='<div class="col-sm-2">';
			str+='<h4 class="pull-left barLengthCss" style="background-color:#20409A;"> </h4>';
			str+='<h3 class="m_top20" style="margin-left: 15px;"><b>'+result.readyForApprovelCommitteeCnt+'</b></h3>';
			str+='<h5 class="m_top20" style="margin-left: 15px;color:#12A89D;">'+result.readyForApprovelCommitteeperc+' %</h5>';
			str+='<h5 class="m_top10" style="margin-left: 15px;line-height: 20px;""><b>Ready for<br>Approvel</b></h5>';
		str+='</div>';
		str+='<div class="col-sm-2">';
			str+='<h4 class="pull-left barLengthCss" style="background-color:#7E3D97;"> </h4>';
			str+='<h3 class="m_top20" style="margin-left: 15px;"><b>'+result.totalApprovedCommitteeCnt+'</b></h3>';
			str+='<h5 class="m_top20" style="margin-left: 15px;color:#12A89D;">'+result.totalApprovedCommitteeperc+' %</h5>';
			str+='<h5 class="m_top10" style="margin-left: 15px;line-height: 20px;""><b>Total Approved<br>Committess</b></h5>';
		str+='</div>';
		str+='<div class="col-sm-2">';
			str+='<h4 class="pull-left barLengthCss" style="background-color:#00A651;"> </h4>';
			str+='<h3 class="m_top20" style="margin-left: 15px;"><b>'+result.submitedCommittees+'</b></h3>';
			str+='<h5 class="m_top20" style="margin-left: 15px;color:#12A89D;">'+result.submitedCommitteesperc+' %</h5>';
			str+='<h5 class="m_top10" style="margin-left: 15px;line-height: 20px;""><b>Total District <br>Collector Approved & Letter Submited <br>Committees</b></h5>';
		str+='</div>';
	
	
	var notStartedCount	=parseFloat(result.notStartedCommitteePerc)
	var inProgressCount	=parseFloat(result.inprogressCommitteePerc)
	var readyApprovalCount	=parseFloat(result.readyForApprovelCommitteeperc)
	var approvalCount	=parseFloat(result.totalApprovedCommitteeperc)
	var collectorAppLettSub	=parseFloat(result.submitedCommitteesperc)
	
	var id = 'committeeMainBlockGraphId';
	var type = {
		type: 'pie',
		backgroundColor:'transparent',
		options3d: {
			enabled: true,
			alpha: 25
		}
	};
	var title = {
		text: ''
	};
	var tooltip = {
		useHTML: true,
		backgroundColor: '#FCFFC5', 
		formatter: function() {
			return "<b style='color:"+this.point.color+"'>"+this.point.name+" -<br/>("+(this.y)+"%)</b>";
		}  
	}; 
	var plotOptions ={
		pie: {
			innerSize: 80,
			depth: 25,
			dataLabels: {
				enabled: false,
				formatter: function() {
					return (this.y) + ' %';
				},
				distance: -10,
				color:'#333'
			},
			showInLegend: false
		},
	};
	var legend={enabled: false};
	var data = [{
		name: '',
		data: [
			{
			  name: 'Not-Started',
			  y: notStartedCount,
			  color:"#F7931D"
			},
			{
			  name: 'Inprogress/Running',
			  y: inProgressCount,
			  color:"#4BBCB4"
			},
			{
			  name: 'Ready for Approvel',
			  y: readyApprovalCount,
			  color:"#20409A"
			},
			{
			  name: 'Approved',
			  y: approvalCount,
			  color:"#7E3D97"
			},
			{
			  name: 'Collector Approved Letter Submited',
			  y: collectorAppLettSub,
			  color:"#00A651"
			}
		]
	}];
		highcharts(id,type,data,plotOptions,title,tooltip,legend);
	$("#overAllCommitteeMainBlockId").html(str);
}
function buildCommitteeWiseDetailsBlock(result){
	
	var str='';
	
	str+='<div class="panel panel-default">';
	 str+='<div class="panel-heading panel_white">';
		str+='<h3 class="text_bold">Committee Overview</h3>';
	  str+='</div>';
	  str+='<div class="panel-body">';
		str+='<table class="table table_custom_JB">';
			str+='<thead>';
				str+='<tr>';
					str+='<th>Committee Levels</th>';
					str+='<th>Total</th>';
					str+='<th class="text-center" style="color:#F7931D">Not Started</th>';
					str+='<th class="text-center" style="color:#12A89D">Inprogress/ <br/>Running</th>';
					str+='<th class="text-center" style="color:#20409A">Ready for <br/>Approvel</th>';
					str+='<th class="text-center" style="color:#7E3D97">Approved</th>';
					str+='<th class="text-center" style="color:#00A651">Letter <br/>Submited</th>';
				str+='</tr>';
			str+='</thead>';
			str+='<tbody>';
				for(var i in result.positinsList){
					str+='<tr>';
						str+='<td>'+result.positinsList[i].name+'</td>';
						str+='<td><h5 class="borderContCss" style="border:1px solid #000;font-weight:bold;">'+result.positinsList[i].totalCommitteeCnt+'</h5></td>';
						str+='<td><h5 class="borderContCss" style="border:1px solid #F7931D"><span class="text_bold">'+result.positinsList[i].notStartedCommitteeCnt+'</span> <span class="pull-right">'+result.positinsList[i].notStartedCommitteePerc+' %</span></h5></td>';
						
						str+='<td><h5 class="borderContCss" style="border:1px solid #12A89D"><span class="text_bold">'+result.positinsList[i].inprogressCommitteeCnt+'</span> <span class="pull-right">'+result.positinsList[i].inprogressCommitteePerc+' %</span></h5></td>';
						
						str+='<td><h5 class="borderContCss" style="border:1px solid #20409A"><span class="text_bold">'+result.positinsList[i].readyForApprovelCommitteeCnt+'</span> <span class="pull-right">'+result.positinsList[i].readyForApprovelCommitteeperc+' %</span></h5></td>';
						
						str+='<td><h5 class="borderContCss" style="border:1px solid #7E3D97"><span class="text_bold">'+result.positinsList[i].totalApprovedCommitteeCnt+'</span> <span class="pull-right">'+result.positinsList[i].totalApprovedCommitteeperc+' %</span></h5></td>';
						
						str+='<td><h5 class="borderContCss" style="border:1px solid #00A651"><span class="text_bold">'+result.positinsList[i].submitedCommittees+'</span> <span class="pull-right">'+result.positinsList[i].submitedCommitteesperc+' %</span></h5></td>';
					str+='</tr>';
				}
				
			str+='</tbody>';
		str+='</table>';
	  str+='</div>';
	str+='</div>';

	$("#committeeWiseDetailsDivId").html(str);
}
function levelWiseCommitteeData(divId)
{
	var levelWiseSBArr = ['district','parliament','constituency'];
	
	var collapse='';
		for(var i in levelWiseSBArr)
		{
			collapse+='<div class="panel-group" id="accordion'+divId.replace(/\s+/g, '')+''+levelWiseSBArr[i]+'" role="tablist" aria-multiselectable="true">';
				collapse+='<div class="panel panel-default panel-black">';
					collapse+='<div class="panel-heading" role="tab" id="heading'+divId+''+levelWiseSBArr[i]+'">';
						if(i == 0)
						{
							collapse+='<a role="button" class="panelCollapseIcon '+divId.replace(/\s+/g, '')+''+levelWiseSBArr[i]+'"  data-toggle="collapse" data-parent="#accordion'+divId.replace(/\s+/g, '')+''+levelWiseSBArr[i]+'" href="#collapse'+divId.replace(/\s+/g, '')+''+levelWiseSBArr[i]+'" aria-expanded="true" aria-controls="collapse'+divId.replace(/\s+/g, '')+''+levelWiseSBArr[i]+'">';
						}else{
							collapse+='<a role="button" class="panelCollapseIcon collapsed '+divId.replace(/\s+/g, '')+''+levelWiseSBArr[i]+'"  data-toggle="collapse" data-parent="#accordion'+divId.replace(/\s+/g, '')+''+levelWiseSBArr[i]+'" href="#collapse'+divId.replace(/\s+/g, '')+''+levelWiseSBArr[i]+'" aria-expanded="true" aria-controls="collapse'+divId.replace(/\s+/g, '')+''+levelWiseSBArr[i]+'">';
						}
						collapse+='<h4 class="panel-title text-capital">'+levelWiseSBArr[i]+' Wise Committee Details</h4>';
							
						collapse+='</a>';
					collapse+='</div>';
					if(i == 0)
					{
						collapse+='<div id="collapse'+divId.replace(/\s+/g, '')+''+levelWiseSBArr[i]+'" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="heading'+divId.replace(/\s+/g, '')+''+levelWiseSBArr[i]+'">';
					}else{
						collapse+='<div id="collapse'+divId.replace(/\s+/g, '')+''+levelWiseSBArr[i]+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading'+divId.replace(/\s+/g, '')+''+levelWiseSBArr[i]+'">';
					}
					
						collapse+='<div class="panel-body">';
							collapse+='<div id="'+divId.replace(/\s+/g, '')+''+levelWiseSBArr[i]+'"></div>';
						collapse+='</div>';
					collapse+='</div>';
				collapse+='</div>';
			collapse+='</div>';
		}
	$("#levelWisecommitteeDetailsDivId").html(collapse);
	for(var i in levelWiseSBArr){
		getDistrictWiseCommitteeDetails(levelWiseSBArr[i],divId);
	}
}
function getDistrictWiseCommitteeDetails(blockType,divId){
	var jsObj={
		
 		"fromDate"			:"",
 		"endDate"			:"",
		type				:blockType
 	}
 	   $.ajax({
 		  type : "POST",
 		  url : "getDistrictWiseCommitteeDetailsAction.action",
 		  dataType : 'json',
 		  data : {task :JSON.stringify(jsObj)}
 		}).done(function(result){ 
			if(result !=null && result.length>0){
				buildDistrictWiseCommitteeDetails(result,blockType,divId);
			}
 		});
  }
  
function buildDistrictWiseCommitteeDetails(result,blockType,divId){
	
	var str='';
	str+='<div class="table-responsive">';
		str+='<table class="table table-bordered table-condensed table_custom_level">';
			str+='<thead style="background-color:#CED0D0;">';
				str+='<tr>';
					str+='<th rowspan="2">Location Name</th>';
					if(blockType == "district"){
						str+='<th rowspan="2">District Committee</th>';
					}/* else{
						str+='<th rowspan="2">Constituency Committee</th>';
					} */
					
					for(var i in result[0].list){
						var length = result[0].list.length;
						str+='<th colspan="'+length+'">'+result[0].list[i].name+'</th>';
					}
				str+='</tr>';
				str+='<tr>';
				for(var i in result[0].list){
					str+='<th>Total</th>';
					str+='<th>Not-started</th>';
					str+='<th>Inprogress/ Running</th>';
					str+='<th>Ready&nbsp;for Approvel</th>';
					str+='<th>Approved</th>';
					str+='<th>Letter Submited</th>';
				}
				str+='</tr>';
			str+='</thead>';
			str+='<tbody>';
				for(var i in result){
					var totalCount=0;
					str+='<tr>';
						str+='<td>'+result[i].name+'</td>';
						if(blockType == "district"){
							str+='<td>'+result[i].statusType+'</td>';
						}
						for(var j in result[i].list){
							totalCount =result[i].list[j].notStartedCommitteeCnt+result[i].list[j].inprogressCommitteeCnt+result[i].list[j].readyForApprovelCommitteeCnt+result[i].list[j].totalApprovedCommitteeCnt+result[i].list[j].submitedCommittees
							str+='<td>'+totalCount+'</td>';
							str+='<td>'+result[i].list[j].notStartedCommitteeCnt+'</td>';
							str+='<td>'+result[i].list[j].inprogressCommitteeCnt+'</td>';
							str+='<td>'+result[i].list[j].readyForApprovelCommitteeCnt+'</td>';
							str+='<td>'+result[i].list[j].totalApprovedCommitteeCnt+'</td>';
							str+='<td>'+result[i].list[j].submitedCommittees+'</td>';
						}
						
					str+='</tr>';
				}
			str+='</tbody>';
		str+='</table>';
	str+='</div>';
	
	$("#"+divId+blockType).html(str);
}
  
//getJanmabhoomiCommitteeOverview();//3rd
function getJanmabhoomiCommitteeOverview(){
	var jsObj={
 		"fromDate"			:"",
 		"toDate"			:"",
		committeId:1
 	}
 	   $.ajax({
 		  type : "POST",
 		  url : "getJanmabhoomiCommitteeOverviewAction.action",
 		  dataType : 'json',
 		  data : {task :JSON.stringify(jsObj)}
 		}).done(function(result){ 
			
 		});
  }
  //getJanmabhoomiCommitteesByLocIdAndCommLvlId();
  
  function getJanmabhoomiCommitteesByLocIdAndCommLvlId(){
	  alert(6)
	var jsObj={
		
 		"fromDate"			:"",
 		"endDate"			:"",
		locationId:11,
		levelId:3,
		committeeLvlId:3,
		status:"total"
	}
 	   $.ajax({
 		  type : "POST",
 		  url : "getJanmabhoomiCommitteesByLocIdAndCommLvlIdAction.action",
 		  dataType : 'json',
 		  data : {task :JSON.stringify(jsObj)}
 		}).done(function(result){ 
			
 		});
  }
  //getJanmabhoomiCommitteeOverviewAction1();
function getJanmabhoomiCommitteeOverviewAction1(){  
var locationLevel=null;
var locationValue=null;
var memberShipCardNo=null;
    var jsObj={
    "locationLevel"     :2,//4
    "locationValue"     :36,//323
    "memberShipCardNo" :"12345678",//12345678
    "voterCardNo"      :""//zdr1655333,XFS0758567
    }
     $.ajax({
      type : "POST",
      url : "searchByMemberIdOrVoterIdAction.action",
      dataType : 'json',
      data : {task :JSON.stringify(jsObj)}
    }).done(function(result){ 
      console.log(result);
    });
}