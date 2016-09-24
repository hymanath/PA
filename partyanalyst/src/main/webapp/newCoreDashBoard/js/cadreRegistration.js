

$(document).on("click",".cadreExpand",function(){
	$(this).find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
	$(".cadreBlock").toggleClass("col-md-6").toggleClass("col-md-12");
	$(".cadreBlock").css("transition"," ease-in-out, width 0.7s ease-in-out");
	setTimeout(function(){
		$(".moreCadreBlock,.moreBlocksCadreIcon").toggle();
		//getSpokesPersonWiseDebate("top");
	},800);
	if( !$(this).find("i").hasClass( "glyphicon glyphicon-resize-small" )){
		$(".moreCadreBlock,.moreBlocksCadreIcon").hide();	
	}else{
		//getSpokesPersonWiseDebate("top");
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
	}else if( $(".eventsIconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
		$(".eventsIconExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
		$(".eventsHiddenBlock").hide();
		$(".eventsBlock").toggleClass("col-md-6").toggleClass("col-md-12");
		$(".dateRangePickerClsForEvents").toggleClass("hide");
	}else if( $(".debatesIconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
		$(".debatesIconExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
		$(".debatesMoreHiddenBlock,.debatesHiddenBlock,.dateRangePickerClsForDebates").hide();
		$(".moreDebatesBlocksIcon").removeClass("unExpandDebatesBlock");
		$(".debatesBlock").toggleClass("col-md-6").toggleClass("col-md-12");
	}else if( $(".attendaceIconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
		$(".attendaceIconExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
		$(".attendanceBlockMore,.moreAttBlocks,.moreAttBlocksIcon").hide();
		$(".dateRangePickerClsForAttendance").toggleClass('hide');
		$(".attendanceBlock").toggleClass("col-md-6").toggleClass("col-md-12");
	}
});

$(document).on("click",".moreBlocksCadreIcon",function(){
	$(".moreBlocksCadre").toggle();
})
$('#apRegistration').highcharts({

    chart: {
      type: 'column'
    },

    title: {
      text: null
    },

    xAxis: {
      categories: ['Srikakulam', 'Vizianagaram', 'Vishakapatnam', 'East Godavari', 'West Godavari']
    },

    yAxis: {
      allowDecimals: false,
      min: 0,
      title: {
        text: null
      }
    },

    tooltip: {
      formatter: function () {
        return '<b>' + this.x + '</b><br/>' +
          this.series.name + ': ' + this.y + '<br/>' +
          'Total: ' + this.point.stackTotal;
      }
    },

    plotOptions: {
      column: {
        stacking: 'normal'
      }
    },

    series: [{
      name: '2016 Renewal',
      data: [1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9],
      stack: 'Registration',
      color:'#F76800'
    }, {
      name: '2016 New',
      data: [9,8,7,6,5,4,0,2,1,0,9,8,7,6,5,4,3,2,1],
      stack: 'Registration',
      color:'#31AA74'
    }, {
      name: '2014',
      data: [8,8,8,8,8,8,12,8,8,8,8,8,8,15,8,8,8,8,8],
      stack: 'Old',
      color:'#FCCD00'
    }]
  });
$('#apRegistrationCons').highcharts({
    chart: {
      type: 'bar'
    },

    title: {
      text: null
    },

    xAxis: {
      categories: ['Srikakulam', 'Vizianagaram', 'Vishakapatnam', 'East Godavari', 'West Godavari']
    },

    yAxis: {
      allowDecimals: false,
      min: 0,
      title: {
        text: null
      }
    },

    tooltip: {
      formatter: function () {
        return '<b>' + this.x + '</b><br/>' +
          this.series.name + ': ' + this.y + '<br/>' +
          'Total: ' + this.point.stackTotal;
      }
    },

    plotOptions: {
      column: {
        stacking: 'normal'
      }
    },

   series: [{
      name: '2016 Renewal',
      data: [1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9],
      stack: 'Registration',
      color:'#F76800'
    }, {
      name: '2016 New',
      data: [9,8,7,6,5,4,0,2,1,0,9,8,7,6,5,4,3,2,1],
      stack: 'Registration',
      color:'#31AA74'
    }, {
      name: '2014',
      data: [8,8,8,8,8,8,12,8,8,8,8,8,8,15,8,8,8,8,8],
      stack: 'Old',
      color:'#FCCD00'
    }]
  });

$('#genSec').highcharts({
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
		floating: true,
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
