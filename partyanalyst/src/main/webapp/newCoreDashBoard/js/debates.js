var customStartDate = moment().subtract(1, 'month').startOf('month').format('DD/MM/YYYY')
var customEndDate = moment().subtract(1, 'month').endOf('month').format('DD/MM/YYYY');
$(document).ready(function(){
	
	getPartyWiseTotalDebateDetails();
	$("#dateRangeIdForDebates").daterangepicker({
		opens: 'left',
		startDate: moment().subtract(1, 'month').startOf('month'),
        endDate: moment().subtract(1, 'month').endOf('month'),
		locale: {
		  format: 'DD/MM/YYYY'
		},
		ranges: {
           'Today': [moment(), moment()],
           'Yesterday': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
           'Last 7 Days': [moment().subtract(6, 'days'), moment()],
           'Last 30 Days': [moment().subtract(29, 'days'), moment()],
           'This Month': [moment().startOf('month'), moment().endOf('month')],
           'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
        }
	})
	$('#dateRangeIdForDebates').on('apply.daterangepicker', function(ev, picker) {
	  customStartDate = picker.startDate.format('DD/MM/YYYY');
	  customEndDate = picker.endDate.format('DD/MM/YYYY');
	  getPartyWiseTotalDebateDetails()
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
			
			str+='<div class="col-md-12 col-xs-12 col-sm-12 col-md-offset-0 m_top20">';
				str+='<h4 class="text-capital"><img src="newCoreDashBoard/img/'+result[i].name+'.png" alt="'+result[i].name+' Icon" class="debatesPartyIcon"/>'+result[i].name+'</h4>';
				str+='<table class="table tableTraining bg_ED m_top10">';
				  str+='<tbody>';
					str+='<tr>';
						str+='<td>';
							str+='<p class="text-capital">total debates</p>';
							str+='<h4>'+result[i].debateCount+'</h4>';
						str+='</td>';
						str+='<td>';
							str+='<p class="text-capital">total spokes persons</p>';
							str+='<h4>'+result[i].candidateCount+'</h4>';
						str+='</td>';
						str+='<td>';
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

function getScaleBasedPerformanceCohort(){
		
		$("#scaleBasedPerformanceCohort").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		var jsObj={
			startDate: ' ' ,
			endDate: ' ',
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
		for(var i in result){
			str+='<div class="col-md-12 col-xs-12 col-sm-12">';
				//str+='<h4 class="text-capital"><img  src="newCoreDashBoard/img/'+result[i].name+'.png" alt="'+result[i].name+'" class="debatesPartyIcon"/>'+result[i].name+'</h4>';
				str+='<table class="table tableDebates m_top10">';
				  str+='<tbody>';
					str+='<tr>';
						str+='<td style="width:100px;border-right:1px solid #ddd;vertical-align:middle;">';
							str+='<img  src="newCoreDashBoard/img/'+result[i].name+'.png" alt="'+result[i].name+'" class="debatesPartyIcon"/>'+result[i].name+'';
						str+='</td>';
						str+='<td>';
							str+='<p class="text-capital">overall debates</p>';
							str+='<h4>'+result[i].debateCount+'</h4>';
						str+='</td>';
						for(var j in result[i].coreDebateVOList){
							str+='<td>';
							if(result[i].coreDebateVOList[j].name !=null && result[i].coreDebateVOList[j].name.length>0){
								str+='<p class="text-capital">'+result[i].coreDebateVOList[j].name.split("(")[0];+'</p>';
							}
								str+='<p class="text-capital"><input class="performanceRating" value="'+result[i].coreDebateVOList[j].scalePerc+'" type="hidden" class="rating" min=0 max=5 step=0.2 data-size="xs"  data-readonly><span class="label label-default label-xs labelCustom">'+result[i].coreDebateVOList[j].scalePerc+'</span></p>';
							str+='</td>';
						}
					str+='</tr>';
				 str+='</tbody>';
				str+='</table>';
				str+='<hr class="m_0"/>';
			str+='</div>';
		}
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
			startDate: ' ' ,
			endDate: ' ',
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
								str+='<div class="scroller'+i+'">';
									str+='<table class="table tableDebates m_top10 dataTableSorting">';
										str+='<thead>';
											str+='<th>Name</th><th>Debates </th><th>SUBJECT</th><th>PRESENTATION</th><th>COUNTER ATTACK</th><th>BODY LANGUAGE</th>';
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
													for(var k in result[i].coreDebateVOList[j].coreDebateVOList){
													
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
		
		$("#basicCommitteeCountsDiv").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		var jsObj={
			startDate: ' ' ,
			endDate: ' ',
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
			startDate: ' ' ,
			endDate: ' ',
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
				str+='<div id="debates'+i+'" style="width:100%;height:100px;"></div>';
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
											return this.value.toString().substring(0, 10)+'...';
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
							name: 'Completed',
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
	}else{
		$("#SpokesPersonWiseDebate").html("No Data Available");
	}
	
}
function getRoleBasedPerformanceCohort(){
		
		$("#basicCommitteeCountsDiv").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		var jsObj={
			startDate: ' ' ,
			endDate: ' ',
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
		for(var i in result){
			str+='<div class="col-md-12 col-xs-12 col-sm-12">';
					//str+='<h4 class="text-capital"><img  src="newCoreDashBoard/img/'+result[i].coreDebateVOList[0].name+'.png" alt="'+result[i].coreDebateVOList[0].name+'" class="debatesPartyIcon"/>'+result[i].coreDebateVOList[0].name+'</h4>';
			
					str+='<table class="table tableDebates m_top10">';
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
								str+='<p class="text-capital">'+result[i].coreDebateVOList[j].candidateName+'</p>';
								str+='<input class="performanceRating" value="'+result[i].coreDebateVOList[j].scalePerc+'" type="hidden" class="rating" min=0 max=5 step=0.2 data-size="xs"  data-readonly><span class="label label-default label-xs labelCustom"  data-readonly>'+result[i].coreDebateVOList[j].scalePerc+'</span>';
							str+='</td>';
							}
						str+='</tr>';
					 str+='</tbody>';
					str+='</table>';
					
			
			str+='<hr class="m_0"/>';
			str+='</div>';
		}
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
		$(".committeesHiddenBlock,.moreBlocks,.moreBlocks1,.moreBlocksDetailAndComp,.moreBlocksIcon").hide();
		$(".committeesBlock,.basicCommitteesBlock,.userTypeCommitteesBlock,.committeesBlock1").toggleClass("col-md-6").toggleClass("col-md-12");
		$(".dateRangePickerCls").toggleClass("hide");
		$(".moreBlocksIcon").removeClass("unExpandBlock");
	}else if( $(".trainingIconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
		$(".trainingIconExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
		$(".trainingsHiddenBlock,.moreTrainingBlocks,.moreTrainingBlocksIcon").hide();
		$(".moreTrainingBlocksIcon").removeClass("unExpandTrainingBlock");
		$(".trainingsBlock").toggleClass("col-md-6").toggleClass("col-md-12");
	}
});
$(document).on("click",".moreDebatesBlocksIcon",function(){
	$(this).addClass("unExpandDebatesBlock")
	$(".debatesMoreHiddenBlock").toggle();
	getScaleBasedPerformanceCohort();
	getCandidateOverAllPerformanceCohort();
	getChannelAndPartyWiseDetails();
	getRoleBasedPerformanceCohort();
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