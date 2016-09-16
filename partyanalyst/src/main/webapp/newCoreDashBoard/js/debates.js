var customStartDate = moment().subtract(1, 'month').startOf('month').format('DD/MM/YYYY')
var customEndDate = moment().subtract(1, 'month').endOf('month').format('DD/MM/YYYY');
$(document).ready(function(){
	
	
	$("#dateRangeIdForDebates").daterangepicker({
		opens: 'left',
		startDate: moment().subtract(1, 'month').startOf('month'),
        endDate: moment().subtract(1, 'month').endOf('month'),
		locale: {
		  format: 'DD/MM/YYYY'
		},
		ranges: {
           'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')],
           'Last 30 Days': [moment().subtract(29, 'days'), moment()],
		   'Last 3 Months': [moment().subtract(3, 'month'), moment()],
		   'Last 6 Months': [moment().subtract(6, 'month'), moment()],
		   'Last 1 Year': [moment().subtract(1, 'Year'), moment()],
           'This Month': [moment().startOf('month'), moment()],
           'This Year': [moment().startOf('Year'), moment()]
        }
	})
	$('#dateRangeIdForDebates').on('apply.daterangepicker', function(ev, picker) {
	  customStartDate = picker.startDate.format('DD/MM/YYYY');
	  customEndDate = picker.endDate.format('DD/MM/YYYY');
	  getPartyWiseTotalDebateDetails()
	  if($(".debatesIconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
		  $(".debateSpokesCls li").hasClass("active")
		  {
			  if($(".debateSpokesCls li").attr("id") == "debateTopId")
			  {
				  searchType = 'top'  
			  }else{
				  searchType = 'poor'
			  }
		  }
		 getSpokesPersonWiseDebate(searchType);
	  };
	  if($(".moreDebatesBlocksIcon").hasClass("unExpandDebatesBlock"))
	  {
		  getScaleBasedPerformanceCohort();
		  getCandidateOverAllPerformanceCohort();
		  getChannelAndPartyWiseDetails();
		  getRoleBasedPerformanceCohort();
		  getRolesPerformanceOfCandidate(0);
		  getDebateRolesNew();
	  }
	});
});	
 function getPartyWiseTotalDebateDetails(){
		
		$("#partyWiseTotalDebateDetails").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		
		var jsObj={
			startDate: customStartDate ,
			endDate: customEndDate,
			state:globalState
		}
		$.ajax({
			type : 'POST',
			url : 'getPartyWiseTotalDebateDetailsAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			buildPartyWiseTotalDebateDetails(result);			
		});
	}


function buildPartyWiseTotalDebateDetails(result)
{
	var str='';
	if(result !=null){
		for(var i in result){
			
			if(i==0)
			{
				str+='<div class="col-md-12 col-xs-12 col-sm-12 col-md-offset-0">';
			}else{
				str+='<div class="col-md-12 col-xs-12 col-sm-12 col-md-offset-0 m_top20">';
			}
			
				//str+='<h4 class="text-capital"><img src="newCoreDashBoard/img/'+result[i].name+'.png" alt="'+result[i].name+' Icon" class="debatesPartyIcon"/>'+result[i].name+'</h4>';
				str+='<table class="table tableTraining bg_ED tableDebatesMain">';
				  str+='<tbody>';
					str+='<tr>';
						str+='<td style="vertical-align:middle;">';
							str+='<img src="newCoreDashBoard/img/'+result[i].name+'.png" alt="'+result[i].name+' Icon" class="debatesPartyIcon"/>'+result[i].name+'';
						str+='</td>';
						str+='<td>';
							str+='<p class="text-capital">total debates</p>';
							str+='<h4>'+result[i].debateCount+'</h4>';
						str+='</td>';
						str+='<td>';
							str+='<p class="text-capital">total spokes persons</p>';
							str+='<h4>'+result[i].candidateCount+'</h4>';
						str+='</td>';
						str+='<td>';
						if(result[i].scalePerc !=null){
						if(result[i].scalePerc.toString().split(".")[1] ==null  || result[i].scalePerc.toString().split(".")[1] == undefined || result[i].scalePerc.toString().split(".")[1].length<0){								
								result[i].scalePerc = result[i].scalePerc.toString().concat(".0");								
							}
						}
							str+='<p class="text-capital">performance</p>';
							str+='<input class="performanceRating" value="'+result[i].scalePerc+'" type="hidden" class="rating" min=0 max=5 step=0.2 data-size="xs"  data-readonly><span class="label label-default label-xs labelCustom">'+result[i].scalePerc+'</span>';
						str+='</td>';
					str+='</tr>';
				 str+='</tbody>';
				str+='</table>';
			str+='</div>';
			
		}
		$("#partyWiseTotalDebateDetails").html(str);
		$(".performanceRating").rating({
			showClear: false,
			showCaption:true,
			hoverOnClear: true,
			animate:false
		});
		if(result.length> 6)
		{
			$("#partyWiseTotalDebateDetails").mCustomScrollbar({setHeight:'300px'})
		}
	}else{
			$("#partyWiseTotalDebateDetails").html('<h3>NO DATA AVAILABLE</h3>')
		}

}
$(document).on("click","#collapseOneId",function(){
	$("#collapseOneBodyId").collapse("toggle");
	$(".arrowChange").find("i").toggleClass("glyphicon-plus").toggleClass("glyphicon-minus");
});
function getScaleBasedPerformanceCohort(){
		
		$("#scaleBasedPerformanceCohort").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		var jsObj={
			startDate: customStartDate ,
			endDate: customEndDate,
			state:globalState
		}
	    $.ajax({
			type : 'POST',
			url : 'getScaleBasedPerformanceCohortAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			buildScaleBasedPerformanceCohort(result);
		});
	}
function buildScaleBasedPerformanceCohort(result)
{
	var str='';
	if(result !=null){
		str+='<div class="col-md-12 col-xs-12 col-sm-12">';
			str+='<div style="overflow:auto">';
			for(var i in result){
				//str+='<h4 class="text-capital"><img  src="newCoreDashBoard/img/'+result[i].name+'.png" alt="'+result[i].name+'" class="debatesPartyIcon"/>'+result[i].name+'</h4>';
				str+='<table class="table tableDebates tableDebatesMain m_top10">';
				  str+='<tbody>';
					str+='<tr>';
						str+='<td style="width:100px;border-right:1px solid #ddd;vertical-align:middle;">';
							str+='<img  src="newCoreDashBoard/img/'+result[i].name+'.png" alt="'+result[i].name+'" class="debatesPartyIcon"/>'+result[i].name+'';
						str+='</td>';
						str+='<td>';
							str+='<p class="text-capital">overall debates</p>';
							str+='<h4>'+result[i].debateCount+'</h4>';
						str+='</td>';
						
						str+='<td>';
							str+='<p class="text-capital">overall Performance</p>';
							if(result[i].overAllPerc !=null && result[i].overAllPerc>0.0){
								str+='<p class="text-capital"><input class="performanceRating" value="'+(result[i].overAllPerc / (result[i].coreDebateVOList).length).toFixed(1)+'" type="hidden" class="rating" min=0 max=5 step=0.2 data-size="xs"  data-readonly><span class="label label-default label-xs labelCustom">'+(result[i].overAllPerc / (result[i].coreDebateVOList).length).toFixed(1)+'</span></p>';
							}else{
								str+='<p class="text-capital"><input class="performanceRating" value="0.0" type="hidden" class="rating" min=0 max=5 step=0.2 data-size="xs"  data-readonly><span class="label label-default label-xs labelCustom">0.0</span></p>';
							}
							
							
						str+='</td>';
						
						for(var j in result[i].coreDebateVOList){
							str+='<td>';
							if(result[i].coreDebateVOList[j].name !=null && result[i].coreDebateVOList[j].name.length>0){
								str+='<p class="text-capital">'+result[i].coreDebateVOList[j].name.split("(")[0];+'</p>';
							}
							
								//New One
								if(result[i].coreDebateVOList[j].scalePerc !=null){
								if(result[i].coreDebateVOList[j].scalePerc.toString().split(".")[1] ==null  || result[i].coreDebateVOList[j].scalePerc.toString().split(".")[1] == undefined || result[i].coreDebateVOList[j].scalePerc.toString().split(".")[1].length<0){								
										result[i].coreDebateVOList[j].scalePerc = result[i].coreDebateVOList[j].scalePerc.toString().concat(".0");								
									}
								}
								
								str+='<p class="text-capital"><input class="performanceRating" value="'+result[i].coreDebateVOList[j].scalePerc+'" type="hidden" class="rating" min=0 max=5 step=0.2 data-size="xs"  data-readonly><span class="label label-default label-xs labelCustom">'+result[i].coreDebateVOList[j].scalePerc+'</span></p>';
							str+='</td>';
						}
					str+='</tr>';
				 str+='</tbody>';
				str+='</table>';
				str+='<hr class="m_0"/>';
			}
			str+='</div>';
		str+='</div>';
		$("#scaleBasedPerformanceCohort").html(str)
		$(".performanceRating").rating({
			showClear: false,
			showCaption:false,
			hoverOnClear: true,
			animate:false
		});
		if(result.length> 6)
		{
			$("#scaleBasedPerformanceCohort").mCustomScrollbar({setHeight:'300px'})
		}
	}else{
		$("#scaleBasedPerformanceCohort").html('<h3>NO DATA AVAILABLE</h3>')
	}
}
function getCandidateOverAllPerformanceCohort(){
		
		$("#candidateOverAllPerformanceCohort").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		var jsObj={
			startDate: customStartDate ,
			endDate: customEndDate,
			state:globalState
		}
	    $.ajax({
			type : 'POST',
			url : 'getCandidateOverAllPerformanceCohortAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			BuildCandidateOverAllPerformanceCohort(result);
		});
	}
function BuildCandidateOverAllPerformanceCohort(result)
{
	var str='';
	
	if(result !=null){
		str+='<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">';
		for(var i in result){
			str+='<div class="panel panel-default collapseDebates">';
				str+='<div class="panel-heading" role="tab" id="heading'+i+'">';
					if(i == 0)
					{
						str+='<a role="button" class="collapseDebatesIcon" data-toggle="collapse" data-parent="#accordion" href="#collapse'+i+'" aria-expanded="true" aria-controls="collapse'+i+'">';
					}else{
						str+='<a role="button" class="collapseDebatesIcon collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapse'+i+'" aria-expanded="true" aria-controls="collapse'+i+'">';
					}
					
						str+='<h4 class="panel-title"><img  src="newCoreDashBoard/img/'+result[i].coreDebateVOList[0].coreDebateVOList[0].name+'.png" alt="'+result[i].coreDebateVOList[0].coreDebateVOList[0].name+'" class="debatesPartyIcon"/> '+result[i].coreDebateVOList[0].coreDebateVOList[0].name+' spokespersons</h4>';
					str+='</a>';
				str+='</div>';
				if(i == 0)
				{
					str+='<div id="collapse'+i+'" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="heading'+i+'">';
				}else{
					str+='<div id="collapse'+i+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading'+i+'">';
				}
					str+='<div class="panel-body">';
						str+='<div class="row">';
							str+='<div class="col-md-12 col-xs-12 col-sm-12">';
								str+='<div class="table-responsive scroller'+i+'">';
									str+='<table class="table table-bordered tableDebatesMainText m_top10 dataTableSorting text-center">';
										str+='<thead>';
											str+='<th>NAME</th><th>DEBATES </th><th>OVERALL PERFORMANCE </th><th>SUBJECT</th><th>PRESENTATION</th><th>COUNTER ATTACK</th><th>BODY LANGUAGE</th>';
										str+='</thead>';
										str+='<tbody>';
											for(var j in result[i].coreDebateVOList){
												str+='<tr>';
													var canidateName = '';
													if(result[i].coreDebateVOList[j].coreDebateVOList[0].candidateName !=null &&
													result[i].coreDebateVOList[j].coreDebateVOList[0].candidateName.length>0){
													  canidateName = getTitleContent(result[i].coreDebateVOList[j].coreDebateVOList[0].candidateName,20);
													}
													//str+='<td class="text-capitalize">'+canidateName+'</td>';
													str+='<td class="text-capitalize">'+canidateName.toUpperCase()+'</td>';
													str+='<td>';
															str+='<span class="">'+result[i].coreDebateVOList[j].coreDebateVOList[0].debateCount+'</span>';
													str+='</td>';
													
													if(result[i].coreDebateVOList[j].coreDebateVOList[0].overAllPerc !=null){
													if(result[i].coreDebateVOList[j].coreDebateVOList[0].overAllPerc.toString().split(".")[1] ==null  || result[i].coreDebateVOList[j].coreDebateVOList[0].overAllPerc.toString().split(".")[1] == undefined || result[i].coreDebateVOList[j].coreDebateVOList[0].overAllPerc.toString().split(".")[1].length<0){								
															result[i].coreDebateVOList[j].coreDebateVOList[0].overAllPerc = result[i].coreDebateVOList[j].coreDebateVOList[0].scalePerc.toString().concat(".0");								
														}
													}
													
													
													str+='<td>';
															str+='<span class="">'+result[i].coreDebateVOList[j].coreDebateVOList[0].overAllPerc+'</span>';
													str+='</td>';
													
													for(var k in result[i].coreDebateVOList[j].coreDebateVOList){
													
													//Digit Adding
													if(result[i].coreDebateVOList[j].coreDebateVOList[k].scalePerc !=null){
													if(result[i].coreDebateVOList[j].coreDebateVOList[k].scalePerc.toString().split(".")[1] ==null  || result[i].coreDebateVOList[j].coreDebateVOList[k].scalePerc.toString().split(".")[1] == undefined || result[i].coreDebateVOList[j].coreDebateVOList[k].scalePerc.toString().split(".")[1].length<0){								
															result[i].coreDebateVOList[j].coreDebateVOList[k].scalePerc = result[i].coreDebateVOList[j].coreDebateVOList[k].scalePerc.toString().concat(".0");								
														}
													}
													
													str+='<td>';
														/* str+='<p class="text-capital">'+result[i].coreDebateVOList[j].coreDebateVOList[k].charecterName.split("(")[0].toUpperCase()+'</p>'; */
														str+='<input class="performanceRating" value="'+result[i].coreDebateVOList[j].coreDebateVOList[k].scalePerc+'" type="hidden" class="rating" min=0 max=5 step=0.2 data-size="xs"  data-readonly><span class="label label-default label-xs labelCustom">'+result[i].coreDebateVOList[j].coreDebateVOList[k].scalePerc+'</span>';
													str+='</td>';
													}
												str+='</tr>';
											}
										str+='</tbody>';
									str+='</table>';
								str+='</div>';
							str+='<hr class="m_0"/>';
							str+='</div>';
						str+='</div>';
					str+='</div>';
				str+='</div>';
			str+='</div>';
		}
		str+='</div>';
		
		
		$("#candidateOverAllPerformanceCohort").html(str);
		$(".performanceRating").rating({
			showClear: false,
			showCaption:false,
			hoverOnClear: true,
			animate:false
		});
		$(".dataTableSorting").dataTable();
		for(var i in result){
			for(var j in result[i].coreDebateVOList){
				for(var k in result[i].coreDebateVOList[j].coreDebateVOList){
					if(result[i].coreDebateVOList[j].coreDebateVOList[k].length > 6)
					{
						$(".scroller"+i+"").mCustomScrollbar({setHeight:'300px'})
					}
				}
				
			}
		}
		
		
	}else{
		$("#candidateOverAllPerformanceCohort").html('<h3>NO DATA AVAILABLE</h3>')
	}
}
function getChannelAndPartyWiseDetails(){
		
		$("#channelAndPartyWiseDetails").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		var jsObj={
			startDate: customStartDate ,
			endDate: customEndDate,
			state:globalState
		}
	    $.ajax({
			type : 'POST',
			url : 'getChannelAndPartyWiseDetailsAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			buildChannelAndPartyWiseDetails(result);
		});
	}
function buildChannelAndPartyWiseDetails(result)
{
	var str='';
	if(result !=null){
		str+='<div class="table-responsive">';
			str+='<table class="table tableDebatesVs m_top10">';
			  str+='<tbody>';
				
					for(var i in result){
						str+='<tr>';
							
							str+='<td class="b_right1"><img src="newCoreDashBoard/img/'+result[i].coreDebateVOList[0].name+'.png" class="channelLogo" alt="Ntv Logo" onerror="setDefaultImageOfChannel(this)"/>'+result[i].coreDebateVOList[0].name+'</td>';
							for(var j in result[i].coreDebateVOList){
								
								//Digit Adding
								if(result[i].coreDebateVOList[j].scalePerc !=null){
								if(result[i].coreDebateVOList[j].scalePerc.toString().split(".")[1] ==null  || result[i].coreDebateVOList[j].scalePerc.toString().split(".")[1] == undefined || result[i].coreDebateVOList[j].scalePerc.toString().split(".")[1].length<0){								
										result[i].coreDebateVOList[j].scalePerc = result[i].coreDebateVOList[j].scalePerc.toString().concat(".0");								
									}
								}
								
								
								str+='<td>';
									str+='<p class="text-capital"><img  src="newCoreDashBoard/img/'+result[i].coreDebateVOList[j].candidateName+'.png" alt="'+result[i].coreDebateVOList[j].candidateName+'" class="debatesPartyIcon"/> '+result[i].coreDebateVOList[j].candidateName+'</p>';
									str+='<input class="performanceRating" value="'+result[i].coreDebateVOList[j].scalePerc+'" type="hidden" class="rating" min=0 max=5 step=0.2 data-size="xs"  data-readonly><span class="label label-default label-xs labelCustom">'+result[i].coreDebateVOList[j].scalePerc+'</span>';
								str+='</td>';
							}
						str+='</tr>';
					}
			  str+='</tbody>';
			str+='</table>';
		str+='</div>';
		$("#channelAndPartyWiseDetails").html(str);
		$(".performanceRating").rating({
			showClear: false,
			showCaption:false,
			hoverOnClear: true,
			animate:false
		});
	}else{
		$("#channelAndPartyWiseDetails").html('<h3>NO DATA AVAILABLE</h3>')
	}
	
}
function getSpokesPersonWiseDebate(searchType){
		
		$("#SpokesPersonWiseDebate").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		var jsObj={
			startDate: customStartDate ,
			endDate: customEndDate,
			searchType:searchType,
			state:globalState
		}
	    $.ajax({
			type : 'POST',
			url : 'getSpokesPersonWiseDebateAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			buildSpokesPersonWiseDebate(result);
		});
	}
function buildSpokesPersonWiseDebate(result){
	var str='';
	if(result != null){		
		for(var i in result){
			str+='<div class="col-md-12 col-xs-12 col-sm-12">';
			if(result[i].coreDebateVOList != null && result[i].coreDebateVOList.length>0)
				str+='<h5 class="text-capital">'+result[i].coreDebateVOList[0].name+'</h5>';
			else
				str+='';
				if($(window).width() < 500)
				{
					str+='<div id="debates'+i+'" style="width:100%;height:150px;"></div>';
				}else if($(window).width() > 500){
					str+='<div id="debates'+i+'" style="width:100%;height:120px;"></div>';
				}else if($(window).width() > 900){
					str+='<div id="debates'+i+'" style="width:100%;height:80px;"></div>';
				}
				
			str+='</div>';
		}		
		$("#SpokesPersonWiseDebate").html(str);
	}	
	if(result != null && result.length > 0){
		for(var i in result){
			var candidateNameAndCompletedCountArray1 =[];
				for(var j in result[i].coreDebateVOList){
					 var obj1 = {							
							name: result[i].coreDebateVOList[j].candidateName.toUpperCase(),
							y: result[i].coreDebateVOList[j].scalePerc
						};
					
					candidateNameAndCompletedCountArray1.push(obj1);
				}
				var getWidth = $("#debates"+i).parent().width()+'px';
				$("#debates"+i).width(getWidth);
				if(candidateNameAndCompletedCountArray1.length !=0){
					$(function () {
						 $("#debates"+i).highcharts({
							 colors: ['#0066DC'],
							chart: {
								type: 'column'
							},
							title: {
								text: ''
							},
							subtitle: {
								text: ''
							},
							xAxis: {
								min: 0,
								gridLineWidth: 0,
								minorGridLineWidth: 0,
								
								type: 'category',
								labels: {
											formatter: function() {
												if($(window).width() < 799)
												{
													return this.value.toString();
												}else if($(window).width() > 800){
													return this.value.toString().substring(0, 10)+'...';
												}
												
											},
											
										}
								
							},
							yAxis: {
								min: 0,
								gridLineWidth: 0,
								minorGridLineWidth: 0,
								title: {
									text: ''
								}

							},
							legend: {
								enabled: false
							},
							

							tooltip: {
								headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
								pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y}</b>'
							},

							series: [{
								name: '',
								dataLabels: {
									enabled: true,
									 formatter: function() {
										if (this.y === 0) {
											return null;
										} else {
											return Highcharts.numberFormat(this.y);
										}
									}
								},
								data: candidateNameAndCompletedCountArray1
							}],
						 
						});
					});
				}
				
		}
	}else{
		$("#SpokesPersonWiseDebate").html("No Data Available");
	}
	
}
function getRoleBasedPerformanceCohort(){
		
		$("#roleBasedPerformanceCohort").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		var jsObj={
			startDate: customStartDate ,
			endDate: customEndDate,
			state:globalState
		}
	    $.ajax({
			type : 'POST',
			url : 'getRoleBasedPerformanceCohortAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			buildRoleBasedPerformanceCohort(result);
		});
	}
function buildRoleBasedPerformanceCohort(result)
{
	var str='';
	if(result !=null){
		str+='<div class="col-md-12 col-xs-12 col-sm-12">';
			str+='<div style="overflow:auto;">';
			for(var i in result){
			
				//str+='<h4 class="text-capital"><img  src="newCoreDashBoard/img/'+result[i].coreDebateVOList[0].name+'.png" alt="'+result[i].coreDebateVOList[0].name+'" class="debatesPartyIcon"/>'+result[i].coreDebateVOList[0].name+'</h4>';
		
				str+='<table class="table tableDebates tableDebatesMain m_top10">';
				  str+='<tbody>';
					str+='<tr>';
						str+='<td>';
							str+='<img  src="newCoreDashBoard/img/'+result[i].coreDebateVOList[0].name+'.png" alt="'+result[i].coreDebateVOList[0].name+'" class="debatesPartyIcon"/>'+result[i].coreDebateVOList[0].name+'';
						str+='</td>';
						str+='<td>';
							str+='<p class="text-capital">overall debates</p>';
							if(result[i].coreDebateVOList[0].debateCount !=null && result[i].coreDebateVOList[0].debateCount>0){
							  str+='<h4>'+result[i].coreDebateVOList[0].debateCount+'</h4>';
							}
							
						str+='</td>';
						for(var j in result[i].coreDebateVOList){
						str+='<td>';
						
						if(result[i].coreDebateVOList[j].scalePerc !=null){
						if(result[i].coreDebateVOList[j].scalePerc.toString().split(".")[1] ==null  || result[i].coreDebateVOList[j].scalePerc.toString().split(".")[1] == undefined || result[i].coreDebateVOList[j].scalePerc.toString().split(".")[1].length<0){								
								result[i].coreDebateVOList[j].scalePerc = result[i].coreDebateVOList[j].scalePerc.toString().concat(".0");								
							}
						}
							str+='<p class="text-capital">'+result[i].coreDebateVOList[j].candidateName+'</p>';
							str+='<input class="performanceRating" value="'+result[i].coreDebateVOList[j].scalePerc+'" type="hidden" class="rating" min=0 max=5 step=0.2 data-size="xs"  data-readonly><span class="label label-default label-xs labelCustom"  data-readonly>'+result[i].coreDebateVOList[j].scalePerc+'</span>';
						str+='</td>';
						}
					str+='</tr>';
				 str+='</tbody>';
				str+='</table>';
			str+='<hr class="m_0"/>';
			}
			str+='</div>';
		str+='</div>';
		$("#roleBasedPerformanceCohort").html(str)
		$(".performanceRating").rating({
			showClear: false,
			showCaption:false,
			hoverOnClear: true,
			animate:false
		});
		if(result.length> 6)
		{
			$("#roleBasedPerformanceCohort").mCustomScrollbar({setHeight:'300px'})
		}
	}else{
		$("#roleBasedPerformanceCohort").html('<h3>NO DATA AVAILABLE</h3>')
	}
}
/* General Click FUnctionalities*/

$(document).on("click",".debatesIconExpand",function(){
	$(this).find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
	$(".debatesBlock").toggleClass("col-md-6").toggleClass("col-md-12");
	$(".dateRangePickerClsForDebates").toggleClass("hide");
	$(".debatesBlock").css("transition"," ease-in-out, width 0.7s ease-in-out");
	setTimeout(function(){
		$(".debatesHiddenBlock,.moreMeetingsBlocksIcon").toggle();
		getSpokesPersonWiseDebate("top");
	},800);
	if( !$(this).find("i").hasClass( "glyphicon glyphicon-resize-small" )){
		$(".debatesMoreHiddenBlock").hide();	
	}else{
		getSpokesPersonWiseDebate("top");
	}
	if( $(".iconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
		$(".iconExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
		$(".committeesHiddenBlock,.moreBlocks,.moreBlocks1,.moreBlocksDetailAndComp,.moreBlocksIcon,.moreBlocksDistrictlevel").hide();
		$(".committeesBlock,.basicCommitteesBlock,.userTypeCommitteesBlock,.committeesBlock1").toggleClass("col-md-6").toggleClass("col-md-12");
		$(".dateRangePickerCls").toggleClass("hide");
		$(".moreBlocksIcon").removeClass("unExpandBlock");
	}else if( $(".trainingIconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
		$(".trainingIconExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
		$(".trainingsHiddenBlock,.moreTrainingBlocks,.moreTrainingBlocksIcon").hide();
		$(".moreTrainingBlocksIcon").removeClass("unExpandTrainingBlock");
		$(".trainingsBlock").toggleClass("col-md-6").toggleClass("col-md-12");
	}else if( $(".meetingsIconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
		$(".meetingsIconExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
		$(".meetingsHiddenBlock,.moreMeetingsBlocksIcon").hide();
		$(".meetingsBlock").toggleClass("col-md-6").toggleClass("col-md-12");
		$(".dateRangePickerClsForMeetings").toggleClass("hide");
		$(".moreMeetingsBlocks1").hide();
		$(".moreMeetingsBlocksDetailed").hide();
		$(".moreMeetingsBlocksComparision").hide();
		
	}else if( $(".newsIconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
			$(".newsIconExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
			$(".newsHiddenBlock,.morenewsBlocksIcon,.newsHiddenMoreBlock").hide();
			$(".newsBlock").toggleClass("col-md-6").toggleClass("col-md-12");
			$(".dateRangePickerClsForNews").toggleClass("hide");
		}
});
$(document).on("click",".moreDebatesBlocksIcon",function(){
	$(this).addClass("unExpandDebatesBlock")
	$(".debatesMoreHiddenBlock").toggle();
	getScaleBasedPerformanceCohort();
	getCandidateOverAllPerformanceCohort();
	getChannelAndPartyWiseDetails();
	getRoleBasedPerformanceCohort();
	getRolesPerformanceOfCandidate(0);
	getDebateRolesNew();
});

$(document).on("click","#debateTopId",function(){
	getSpokesPersonWiseDebate("top");
});
$(document).on("click","#debateLowId",function(){
	getSpokesPersonWiseDebate("poor");
});

function setDefaultImageOfChannel(img){
	 img.src = "newCoreDashBoard/img/channel.png";
}
function getTitleContent(name,showCharVal){
	ellipsetext=". ."
	var showChar = showCharVal;
	var content = name;
	if(content!=null){
		if(content.length > showChar) {
		var c = content.substr(0, showChar);
		var html = c + ellipsetext;
		return html;
		}
	}
   
	return name;
}

function getRolesPerformanceOfCandidate(roleId){
		
		$("#candidateRolesPerformanceNewId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		
		var jsObj={
			startDate: customStartDate ,
			endDate: customEndDate,
			state:globalState,
			roleId:roleId
		}
		$.ajax({
			type : 'POST',
			url : 'getRolesPerformanceOfCandidateAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			buildRolesPerformanceOfCandidate(result);			
		});
	}
	function buildRolesPerformanceOfCandidate(result){
		var str='';
		if(result !=null && result.length>0){
			str+='<div class="table-responsive">';
				str+='<table class="table tableTopDebates tableDebatesMainText">';			
				for(var i in result){
					str+='<tr>';
							str+='<td class="text-capital" style="width:25% !important" id="'+result[i].id+'">'+result[i].name+'</td>';
							str+='<td class="text-capital">';
								str+='<p>PARTY</p>';
								str+='<p><img src="newCoreDashBoard/img/'+result[i].candidateName+'.png" class="debatesPartyIcon"/>'+result[i].candidateName+'</p>';
							str+='</td>';
							str+='<td class="text-capital">';
								str+='<p>debates</p>';
								str+='<p class="text-muted">'+result[i].debateCount+'</p>';
							str+='</td>';
							
							//Digit Adding
								if(result[i].scalePerc !=null){
							if(result[i].scalePerc.toString().split(".")[1] ==null  || result[i].scalePerc.toString().split(".")[1] == undefined || result[i].scalePerc.toString().split(".")[1].length<0){
										result[i].scalePerc = result[i].scalePerc.toString().concat(".0");				
									}
								}
							
							str+='<td class="text-capital">';
								str+='<p>performance</p>';
								str+='<input class="performanceRating" value="'+result[i].scalePerc+'" type="hidden" class="rating" min=0 max=5 step=0.2 data-size="xs"  data-readonly><span class="label label-default label-xs labelCustom"  data-readonly>'+result[i].scalePerc+'</span>';
							str+='</td>';
						str+='</tr>';
				}
				str+='</table>';
			str+='</div>';
		}
		else{
			str+='<div class="text-capital">No Data Available</div>';
		}		
		$("#candidateRolesPerformanceNewId").html(str);
		$(".performanceRating").rating({
			showClear: false,
			showCaption:false,
			hoverOnClear: true,
			animate:false
		});		
	}
	
	function getDebateRolesNew(){
		
		//$("#candidateRolesBuildId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		
		$.ajax({
			type : 'POST',
			url : 'getDebateRolesNewAction.action',
			dataType : 'json',
			data : {}
		}).done(function(result){
			buildDebateRolesNew(result);			
		});
	}
	function buildDebateRolesNew(result){
		var str='';
		if(result !=null && result.length>0){
			str+='<div class="col-md-6 col-xs-12 col-sm-6 pull-right">';
				str+='<ul class="activeUlCls list-inline pull-right candidateRolesCls">';	
				str+='<li id="0" class="active" style="margin-right:3px;">All</li>';				
				for(var i in result){								
					str+='<li id="'+result[i].id+'" style="margin-right:3px;" >'+result[i].name+'</li>';									
				}	
				str+='</ul>';
			str+='</div>';
			
			$("#candidateRolesBuildId").html(str);
		}
		
	}
	 $(document).on("click",".candidateRolesCls li",function(){		 
		getRolesPerformanceOfCandidate($(this).attr('id')); 
	 });
	/*Notes Functionality*/
	function displayDashboardCommentsForDebates(dashBoardComponentId){
	var jsObj={
		dashBoardComponentId:dashBoardComponentId
	}	
	$.ajax({
	 type: "POST",
	 url: "displayDashboardCommentsAction.action",
	 data: {task :JSON.stringify(jsObj)}
	}).done(function(result){
		if(result != null && result.length >0){
		 var str=''; 
      		 
	     str+='<ul class="notesUlDebates m_top20" style="text-transform: none;font-weight: normal;font-size: 14px;">';  	
            	     
					for(var i in result){ 
                        str+='<li style="margin-top:3px;">'; 
                        str+='<span class="notesTextDebates" id="editTextDebateId'+i+'"  attr_commentId="'+result[i].dashBoardCommentId+'">'+result[i].comment+' </span>- <span class="text-muted"><i>'+result[i].insertedTime+'</i></span>';
					    str+='<i class="glyphicon glyphicon-trash pull-right hoverBlock deleteNotesDebates" attr_cmt_id="editTextDebateId'+i+'" id="'+result[i].dashBoardCommentId+'" onClick="deleteDashBoardcomments(this.id);"></i>';
                        str+='<i class="glyphicon glyphicon-edit pull-right hoverBlock editNotesDebates" attr_cmt_id="editTextDebateId'+i+'" attr_comment="'+result[i].comment+'"></i>';
                        str+='</li>';
					}
                        str+='</ul>';
						/*str+='<hr/>';
						str+='<div id="debateUpId" style="color:red;"></div>';
                        str+='<label>Create Notes</label>';
                        str+='<textarea class="form-control notesAreaDebates"></textarea>';
                        str+='<button class="btn btn-default btnCustomCreateDebates btn-sm "  onClick="savingDashboardCommentFordebates(3);">create</button>';*/
			
			$("#notesDebatesId").html(str);	 
		}
	});
}
function deleteDashBoardcomments(dashboardCommentId)
{
	var jsObj={
		dashboardCommentId : dashboardCommentId
	}	
	$.ajax({
	 type: "POST",
	 url: "deleteDashBoardcommentsAction.action",
	 data: {task :JSON.stringify(jsObj)}
	}).done(function(result){
		if(result != null){	
			if(result.message == "success"){
				
				
			}
		}
			
	});
	
}

function savingDashboardCommentFordebates(dashboardComponentId){  
  var comment=$(".notesAreaDebates").val();
  if(comment.trim() ==""){
		  $("#debateUpId").html("Notes Required.");
		  return;
	  }
	var editId = $("#cmtDebateId").val();
	//$("#"+editId).parent().html(' ');
	$("#"+editId).html(comment);
	 var dashboardCommentId=0;
	 if($(".notesAreaDebates").attr("attr_commentid")>0)
	 {
		dashboardCommentId=$(".notesAreaDebates").attr("attr_commentid");		
	 }
	
	var jsObj={
		comment:comment,
		dashboardComponentId: dashboardComponentId,
		dashboardCommentId : dashboardCommentId
	}	
	$.ajax({
	 type: "POST",
	 url: "savingDashboardCommentAction.action",
	 data: {task :JSON.stringify(jsObj)}
	}).done(function(result){
		if(result != null){	
			if(result.message == "success"){
				
				//$("#debateUpId").html('update succuss');
				displayDashboardCommentsForDebates(3);
			}
		}			
	});
}
$(document).on("click",".notesIconDebates",function(){
	$(this).closest(".panel-heading").find(".notesDropDown").toggle();
});
$(document).on("click",".deleteNotesDebates",function(){
	$(this).closest("li").remove();
});
$(document).on("click",".editNotesDebates",function(){ 
	var commentId = $(this).attr("attr_cmt_id");
	var commentId1 = $(this).parent().find(".notesTextDebates").attr("attr_commentid");
	var notesHtml = $("#"+commentId).html();
	$(".notesAreaDebates").val(notesHtml);  
	$(".notesAreaDebates").attr("attr_commentid",commentId1);  
	$("#cmtId").val(commentId);
	//$("#cmtId").val();
	$("#debateUpId").html('');		
});

$(document).on("click",".btnCustomCreateDebates",function(){
	var getNewNotes = $(".notesAreaDebates").val();
	var todayDate = moment().format("DD MMMM YYYY");
	var cmtId = $("#cmtId").val();
	var commentText = '<span class="notesText" id="'+cmtId+'" >'+getNewNotes+'</span> - <span class="text-muted"><i>'+todayDate+'</i></span> <i  class="glyphicon glyphicon-trash pull-right hoverBlock deleteNotesDebates"></i><i class="glyphicon glyphicon-edit pull-right hoverBlock editNotes" attr_cmt_id="'+cmtId+'"></i>'; 
	if(cmtId>0)
	$(".notesUlDebates").append("<li>"+commentText+"</li>");
	$(".notesAreaDebates").val('');
});
