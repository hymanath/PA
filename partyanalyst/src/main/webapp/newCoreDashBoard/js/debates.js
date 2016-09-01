getPartyWiseTotalDebateDetails()
getSpokesPersonWiseDebate()
getScaleBasedPerformanceCohort()
getCandidateOverAllPerformanceCohort()
getChannelAndPartyWiseDetails()
getRoleBasedPerformanceCohort()
function getPartyWiseTotalDebateDetails(){
		
		$("#partyWiseTotalDebateDetails").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		var jsObj={
			startDate: ' ' ,
			endDate: ' ' 
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
			
			str+='<div class="col-md-12 col-xs-12 col-sm-12 col-md-offset-0 m_top10">';
				str+='<h4 class="text-capital"><img src="newCoreDashBoard/img/tdpIcon.png" alt="tdpIcon" class="debatesPartyIcon"/>'+result[i].name+'</h4>';
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
							str+='<input class="performanceRating" value="'+result[i].scalePerc+'" type="number" class="rating" min=0 max=5 step=0.2 data-size="xs">';
						str+='</td>';
					str+='</tr>';
				 str+='</tbody>';
				str+='</table>';
			str+='</div>';
			
		}
		$("#partyWiseTotalDebateDetails").html(str);
		$(".performanceRating").rating({
			showClear: false,
			showCaption:false,
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
function getSpokesPersonWiseDebate(){
		
		$("#basicCommitteeCountsDiv").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		
		
		var jsObj={
			startDate: ' ' ,
			endDate: ' ' ,
			searchType:' '
		}
	    $.ajax({
			type : 'POST',
			url : 'getSpokesPersonWiseDebateAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			
			
		});
	}
function getScaleBasedPerformanceCohort(){
		
		$("#scaleBasedPerformanceCohort").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		var jsObj={
			startDate: ' ' ,
			endDate: ' ' 
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
				str+='<h4 class="text-capital"><img  src="newCoreDashBoard/img/tdpIcon.png" alt="tdpIcon" class="debatesPartyIcon"/>'+result[i].name+'</h4>';
				str+='<table class="table tableDebates m_top10">';
				  str+='<tbody>';
					str+='<tr>';
						str+='<td>';
							str+='<p class="text-capital">overall debates</p>';
							str+='<h4>'+result[i].debateCount+'</h4>';
						str+='</td>';
						for(var j in result[i].coreDebateVOList){
							str+='<td>';
								str+='<p class="text-capital">'+result[i].coreDebateVOList[j].name+'</p>';
								str+='<input class="performanceRating" value="'+result[i].coreDebateVOList[j].scalePerc+'" type="number" class="rating" min=0 max=5 step=0.2 data-size="xs">';
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
			endDate: ' ' 
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
					
						str+='<h4 class="panel-title"><img  src="newCoreDashBoard/img/tdpIcon.png" alt="tdpIcon" class="debatesPartyIcon"/> '+result[i].coreDebateVOList[0].coreDebateVOList[0].name+' spokespersons</h4>';
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
									str+='<table class="table tableDebates m_top10">';
										str+='<tbody>';
											for(var j in result[i].coreDebateVOList){
												str+='<tr>';
													str+='<td class="text-capitalize">'+result[i].coreDebateVOList[j].coreDebateVOList[0].candidateName+'</td>';
													for(var k in result[i].coreDebateVOList[j].coreDebateVOList){
													str+='<td>';
														str+='<p class="text-capital">'+result[i].coreDebateVOList[j].coreDebateVOList[k].charecterName+'</p>';
														str+='<input class="performanceRating" value="'+result[i].coreDebateVOList[j].scalePerc+'" type="number" class="rating" min=0 max=5 step=0.2 data-size="xs">';
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
		for(var i in result){
			for(var j in result[i].coreDebateVOList){
				for(var k in result[i].coreDebateVOList[j].coreDebateVOList){
					if(result[i].coreDebateVOList[j].coreDebateVOList[k].length > 6)
					{
						alert(1)
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
			endDate: ' ' 
		}
	    $.ajax({
			type : 'POST',
			url : 'getChannelAndPartyWiseDetailsAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			buildChannelAndPartyWiseDetails(result)
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
							
							str+='<td class="b_right1"><img src="newCoreDashBoard/img/'+result[i].coreDebateVOList[0].name+'.png" class="channelLogo" alt="Ntv Logo"/>'+result[i].coreDebateVOList[0].name+'</td>';
							for(var j in result[i].coreDebateVOList){
								str+='<td>';
									str+='<p class="text-capital"><img  src="newCoreDashBoard/img/tdpIcon.png" alt="tdpIcon" class="debatesPartyIcon"/> '+result[i].coreDebateVOList[j].candidateName+'</p>';
									str+='<input class="performanceRating" value="'+result[i].coreDebateVOList[j].scalePerc+'" type="number" class="rating" min=0 max=5 step=0.2 data-size="xs">';
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
function getRoleBasedPerformanceCohort(){
		
		$("#basicCommitteeCountsDiv").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		var jsObj={
			startDate: ' ' ,
			endDate: ' ' 
		}
	    $.ajax({
			type : 'POST',
			url : 'getRoleBasedPerformanceCohortAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			buildRoleBasedPerformanceCohort(result)
		});
	}
function buildRoleBasedPerformanceCohort(result)
{
	var str='';
	if(result !=null){
		for(var i in result){
			str+='<div class="col-md-12 col-xs-12 col-sm-12">';
					str+='<h4 class="text-capital"><img  src="newCoreDashBoard/img/tdpIcon.png" alt="tdpIcon" class="debatesPartyIcon"/>'+result[i].coreDebateVOList[0].name+'</h4>';
			
					str+='<table class="table tableDebates m_top10">';
					  str+='<tbody>';
						str+='<tr>';
							str+='<td>';
								str+='<p class="text-capital">overall debates</p>';
								str+='<h4>10000</h4>';
								
							str+='</td>';
							for(var j in result[i].coreDebateVOList){
							str+='<td>';
								str+='<p class="text-capital">'+result[i].coreDebateVOList[j].candidateName+'</p>';
								str+='<input class="performanceRating" value="'+result[i].coreDebateVOList[j].scalePerc+'" type="number" class="rating" min=0 max=5 step=0.2 data-size="xs">';
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
	$(".debatesBlock").css("transition"," ease-in-out, width 0.7s ease-in-out");
	setTimeout(function(){
		$(".debatesHiddenBlock,.moreMeetingsBlocksIcon").toggle();
		initialiseDebatesGraphs();
	},800);
});
$(document).on("click",".moreDebatesBlocksIcon",function(){
	$(".debatesMoreHiddenBlock").toggle();
});


function initialiseDebatesGraphs()
{
	var getWidth = $("#genSecMeetings").parent().width()+'px';
	$("#genSecMeetings").width(getWidth);
	$(function () {
		$('#tdp').highcharts({
			colors: ['#0066DC'],
			chart: {
				type: 'column'
			},
			title: {
				text: null
			},
			subtitle: {
				text: null
			},
			xAxis: {
				categories: ['B Jaya Nageshwar Reddy', 'G Buchaiah Chowdary', 'Nimmala Ramanaidu', 'Reddy Subramanyam', 'Varla Ramaiah'],
				title: {
					text: null
				}
			},
			yAxis: {
				min: 0,
				title: {
					text: null,
					align: 'high'
				},
				labels: {
					overflow: 'justify',
					enabled: false,
				}
			},
			tooltip: {
				valueSuffix: '%'
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
				floating: false,
				enabled:false,
				borderWidth: 1,
				backgroundColor: ((Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'),
				shadow: true
			},
			credits: {
				enabled: false
			},
			series: [{
				name: 'Year 1800',
				data: [107, 31, 635, 203, 2]
			}]
		});
	});
	$(function () {
		$('#ysrc').highcharts({
			colors: ['#0066DC'],
			chart: {
				type: 'column'
			},
			title: {
				text: null
			},
			subtitle: {
				text: null
			},
			xAxis: {
				categories: ['B Jaya Nageshwar Reddy', 'G Buchaiah Chowdary', 'Nimmala Ramanaidu', 'Reddy Subramanyam', 'Varla Ramaiah'],
				title: {
					text: null
				}
			},
			yAxis: {
				min: 0,
				title: {
					text: null,
					align: 'high'
				},
				labels: {
					overflow: 'justify',
					enabled: false,
				}
			},
			tooltip: {
				valueSuffix: '%'
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
				floating: false,
				enabled:false,
				borderWidth: 1,
				backgroundColor: ((Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'),
				shadow: true
			},
			credits: {
				enabled: false
			},
			series: [{
				name: 'Year 1800',
				data: [107, 31, 635, 203, 2]
			}]
		});
	});
	$(function () {
		$('#bjp').highcharts({
			colors: ['#0066DC'],
			chart: {
				type: 'column'
			},
			title: {
				text: null
			},
			subtitle: {
				text: null
			},
			xAxis: {
				categories: ['B Jaya Nageshwar Reddy', 'G Buchaiah Chowdary', 'Nimmala Ramanaidu', 'Reddy Subramanyam', 'Varla Ramaiah'],
				title: {
					text: null
				}
			},
			yAxis: {
				min: 0,
				title: {
					text: null,
					align: 'high'
				},
				labels: {
					overflow: 'justify',
					enabled: false,
				}
			},
			tooltip: {
				valueSuffix: '%'
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
				floating: false,
				enabled:false,
				borderWidth: 1,
				backgroundColor: ((Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'),
				shadow: true
			},
			credits: {
				enabled: false
			},
			series: [{
				name: 'Year 1800',
				data: [107, 31, 635, 203, 2]
			}]
		});
	});
	$(function () {
		$('#inc').highcharts({
			colors: ['#0066DC'],
			chart: {
				type: 'column'
			},
			title: {
				text: null
			},
			subtitle: {
				text: null
			},
			xAxis: {
				categories: ['Parthasarathi', 'Satyanarayana Murthy', 'Nimmala Ramanaidu', 'Reddy Subramanyam', 'Varla Ramaiah'],
				title: {
					text: null
				}
			},
			yAxis: {
				min: 0,
				title: {
					text: null,
					align: 'high'
				},
				labels: {
					overflow: 'justify',
					enabled: false,
				}
			},
			tooltip: {
				valueSuffix: '%'
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
				floating: false,
				enabled:false,
				borderWidth: 1,
				backgroundColor: ((Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'),
				shadow: true
			},
			credits: {
				enabled: false
			},
			series: [{
				name: 'Year 1800',
				data: [107, 31, 635, 203, 2]
			}]
		});
	});
}