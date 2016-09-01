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