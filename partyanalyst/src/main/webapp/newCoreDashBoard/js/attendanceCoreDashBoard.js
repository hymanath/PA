$(document).on("click",".attendaceIconExpand",function(){
	$(this).find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
	$(".attendanceBlock").toggleClass("col-md-6").toggleClass("col-md-12");
	$(".attendanceBlock").css("transition"," ease-in-out, width 0.7s ease-in-out");
	$(".attendanceBlockMore,.moreAttBlocksIcon").toggle();
	$(".dateRangePickerClsForAttendance").toggleClass("hide")
});

$("#dateRangeIdForAttendance").daterangepicker({
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
$('#dateRangeIdForAttendance').on('apply.daterangepicker', function(ev, picker) {
  customStartDate = picker.startDate.format('DD/MM/YYYY');
  customEndDate = picker.endDate.format('DD/MM/YYYY');  
});

$(document).on("click",".moreAttBlocksIcon",function(){
	$(".moreAttBlocks").toggle();
});

$('#attendance').highcharts({
		colors: ['#FFC21F'],
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
				min: 0,
				gridLineWidth: 0,
				minorGridLineWidth: 0,
				categories: ['JAN', 'FEB', 'MAR'],
				title: {
					text: null
				},
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
					text: null,
					align: 'high'
				},
				labels: {
					overflow: 'justify',
					enabled: false,
				}
			},
			tooltip: {
				pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.y}</b><br/>',
				shared: true,
				valueSuffix: '%'
			},
			plotOptions: {
				column: {
					stacking: 'normal',      
					dataLabels: {
					enabled: true,
					 formatter: function() {
						if (this.y === 0) {
							return null;
						} else {
							return Highcharts.numberFormat(this.y,2) +"%";
						}
					}
				  
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
			name: 'Attendance',
			data: [107, 31, 635, 203, 2]
		}]
	});