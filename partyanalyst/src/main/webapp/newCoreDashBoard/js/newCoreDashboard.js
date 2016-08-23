
	function getUserBasicDetails(){
		
		var jsObj ={userId:globalUserId}
		$.ajax({
			type : 'POST',
			url : 'getUserBasicDetailsAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(result != null){
				globalUserTypeId = result.userTypeId;
				globalUserAccessLevelId = result.userAccessLevelId;
				globalUserAccessLevelValues = result.userAccessLevelValuesList;
				
				onLoadCalls();
			}
		});
	}

function getDistrictWiseCommitteesCountReport(){
		
		var state ='AP';
		var basicCommitteeIdsArray= [];
       basicCommitteeIdsArray.push(1);
       basicCommitteeIdsArray.push(2);
       basicCommitteeIdsArray.push(3);
      
	   
       var startDateString = '01/01/2014';
	   var endDateString = '11/08/2016';
		
		var jsObj ={state:state,
					basicCommitteeIdsArray : basicCommitteeIdsArray,
					startDateString : startDateString,
 			        endDateString :   endDateString
					}
		
		$.ajax({
			type : 'POST',
			url : 'getDistrictWiseCommitteesCountReportAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			
			
		});
	}
	
	function getCommitteesBasicCountReport(){
		
		var state ='AP';
		var basicCommitteeIdsArray= [];
       basicCommitteeIdsArray.push(1);
       basicCommitteeIdsArray.push(2);
       basicCommitteeIdsArray.push(3);
      
	   
       var startDateString = '01/01/2014';
	   var endDateString = '11/08/2016';
		
		var jsObj ={userAccessLevelId:globalUserAccessLevelId,
					userAccessLevelValuesArray:globalUserAccessLevelValues,
					state:state,
					basicCommitteeIdsArray : basicCommitteeIdsArray,
					startDateString : startDateString,
 			        endDateString :   endDateString
					}
		
		$.ajax({
			type : 'POST',
			url : 'getCommitteesBasicCountReportAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			buildgetCommitteesBasicCountReport(result);
			
		});
	}
	
	function getLevelWiseBasicCommitteesCountReport(){
		
		var state ='AP';
		var basicCommitteeIdsArray= [];
       basicCommitteeIdsArray.push(1);
       basicCommitteeIdsArray.push(2);
       basicCommitteeIdsArray.push(3);
      
	   
       var startDateString = '01/01/2014';
	   var endDateString = '11/08/2016';
		
		var jsObj ={userAccessLevelId:globalUserAccessLevelId,
					userAccessLevelValuesArray:globalUserAccessLevelValues,
					state:state,
					basicCommitteeIdsArray : basicCommitteeIdsArray,
					startDateString : startDateString,
 			        endDateString :   endDateString
					}
		
		$.ajax({
			type : 'POST',
			url : 'getLevelWiseBasicCommitteesCountReportAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			
			
		});
	}
	
	function getUserTypeWiseCommitteesCompletedCounts(){
		
		var state ='AP';
		var basicCommitteeIdsArray= [];
       basicCommitteeIdsArray.push(1);
       basicCommitteeIdsArray.push(2);
       basicCommitteeIdsArray.push(3);
      
	   
       var startDateString = '01/01/2014';
	   var endDateString = '11/08/2016';
		
		var jsObj ={
			          userAccessLevelId:globalUserAccessLevelId,
					  userAccessLevelValuesArray:globalUserAccessLevelValues,
					  state:state,
					  basicCommitteeIdsArray : basicCommitteeIdsArray,
					  startDateString : startDateString,
 			          endDateString :   endDateString,
					  userId :1,
					  activityMemberId : 0,
					  userTypeId : globalUserTypeId
					}
		
		$.ajax({
			type : 'POST',
			url : 'getUserTypeWiseCommitteesCompletedCountsAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			
			
		});
	}
	
	function getUserTypeWiseCommitteesCompletedCounts1(){
		
		var state ='AP';
		var basicCommitteeIdsArray= [];
       basicCommitteeIdsArray.push(1);
       basicCommitteeIdsArray.push(2);
       basicCommitteeIdsArray.push(3);
      
	   
       var startDateString = '01/01/2014';
	   var endDateString = '11/08/2016';
		
		var jsObj ={
			          userAccessLevelId:globalUserAccessLevelId,
					  userAccessLevelValuesArray:globalUserAccessLevelValues,
					  state:state,
					  basicCommitteeIdsArray : basicCommitteeIdsArray,
					  startDateString : startDateString,
 			          endDateString :   endDateString,
					  userId :1,
					  activityMemberId : 0,
					  userTypeId : globalUserTypeId
					}
		
		$.ajax({
			type : 'POST',
			url : 'getUserTypeWiseCommitteesCompletedCountsAction1.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			
			
		});
	}
	
	function initialiseGraph()
	{
		var getWidth = $("#genSec").parent().width()+'px';
		$("#genSec").width(getWidth);
		$(function () {
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
						overflow: 'justify'
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
		});
		$(function () {
			$('#Secretary').highcharts({
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
						overflow: 'justify'
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
		});
		$(function () {
			$('#memOfParliament').highcharts({
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
						overflow: 'justify'
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
		});
		$(function () {
			$('#disIncharges').highcharts({
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
						overflow: 'justify'
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
		});
	}
	
	function moreBlocks()
	{
		
		$(function () {
			$('#mainCommittees').highcharts({
				colors: ['#F56800','#53BF8B','#A1B1BE'],
				chart: {
					type: 'pie',
					options3d: {
						enabled: true,
						alpha: 45
					}
				},
				title: {
					text: null
				},
				subtitle: {
					text: null
				},
				
				plotOptions: {
					pie: {
						innerSize: 100,
						depth: 45,
						dataLabels:{
							enabled: false
						},
						showInLegend: false
					},
					
					
				},
				series: [{
					name: 'Delivered amount',
					data: [
						['Started', 8],
						['Completed', 3],
						['Yet To Start', 1]
					]
				}]
			}, function (chart) {
				var $legend = ' ';
				$legend = $('#customLegend');
		
				$.each(chart.series[0].data, function (j, data) {
		
					$legend.append('<li class="item"><div class="symbol" style="background-color:'+data.color+'"></div><div class="serieName">' + data.name + '</div></li>');
		
				});
				
				$('#customLegend .item').click(function(){
					$(this).toggleClass("disabled");
					var inx = $(this).index(),
						point = chart.series[0].data[inx];
				   
					if(point.visible)
					{
						point.setVisible(false);
					}else
					{
						point.setVisible(true);
					}
				});        
		
			});
		});
		$(function () {
			$('#teluguYuvatha').highcharts({
				colors: ['#F56800','#53BF8B','#A1B1BE'],
				chart: {
					type: 'pie',
					options3d: {
						enabled: true,
						alpha: 45
					}
				},
				title: {
					text: null
				},
				subtitle: {
					text: null
				},
				
				plotOptions: {
					pie: {
						innerSize: 100,
						depth: 45,
						dataLabels:{
							enabled: false
						},
						showInLegend: false
					},
					
					
				},
				series: [{
					name: 'Delivered amount',
					data: [
						['Started', 8],
						['Completed', 3]
					]
				}]
			}, function (chart) {
				var $legend = ' ';
				$legend = $('#customLegend1');
		
				$.each(chart.series[0].data, function (j, data) {
		
					$legend.append('<li class="item"><div class="symbol" style="background-color:'+data.color+'"></div><div class="serieName">' + data.name + '</div></li>');
		
				});
				
				$('#customLegend1 .item').click(function(){
					$(this).toggleClass("disabled");
					var inx = $(this).index(),
						point = chart.series[0].data[inx];
				   
					if(point.visible)
					{
						point.setVisible(false);
					}else
					{
						point.setVisible(true);
					}
				});        
		
			});
		});
		$(function () {
			$('#teluguMahila').highcharts({
				colors: ['#F56800','#53BF8B','#A1B1BE'],
				chart: {
					type: 'pie',
					options3d: {
						enabled: true,
						alpha: 45
					}
				},
				title: {
					text: null
				},
				subtitle: {
					text: null
				},
				
				plotOptions: {
					pie: {
						innerSize: 100,
						depth: 45,
						dataLabels:{
							enabled: false
						},
						showInLegend: false
					},
				},
				series: [{
					name: 'Delivered amount',
					data: [
						['Started', 8],
						['Completed', 3],
					]
				}]
			}, function (chart) {
				var $legend = ' ';
				$legend = $('#customLegend2');
		
				$.each(chart.series[0].data, function (j, data) {
		
					$legend.append('<li class="item"><div class="symbol" style="background-color:'+data.color+'"></div><div class="serieName">' + data.name + '</div></li>');
		
				});
				
				$('#customLegend2 .item').click(function(){
					$(this).toggleClass("disabled");
					var inx = $(this).index(),
						point = chart.series[0].data[inx];
				   
					if(point.visible)
					{
						point.setVisible(false);
					}else
					{
						point.setVisible(true);
					}
				});        
		
			});
		});
		$(function () {
			$('#teluguRythu').highcharts({
				colors: ['#F56800','#53BF8B','#A1B1BE'],
				chart: {
					type: 'pie',
					options3d: {
						enabled: true,
						alpha: 45
					}
				},
				title: {
					text: null
				},
				subtitle: {
					text: null
				},
				
				plotOptions: {
					pie: {
						innerSize: 100,
						depth: 45,
						dataLabels:{
							enabled: false
						},
						showInLegend: false
					},
				},
				series: [{
					name: 'Delivered amount',
					data: [
						['Started', 8],
						['Completed', 3],
					]
				}]
			}, function (chart) {
				var $legend = ' ';
				$legend = $('#customLegend3');
		
				$.each(chart.series[0].data, function (j, data) {
		
					$legend.append('<li class="item"><div class="symbol" style="background-color:'+data.color+'"></div><div class="serieName">' + data.name + '</div></li>');
		
				});
				
				$('#customLegend3 .item').click(function(){
					$(this).toggleClass("disabled");
					var inx = $(this).index(),
						point = chart.series[0].data[inx];
				   
					if(point.visible)
					{
						point.setVisible(false);
					}else
					{
						point.setVisible(true);
					}
				});        
		
			});
		});
		$(".slickPanelSlider").slick({
		 slide: 'li',
		 slidesToShow: 3,
		 slidesToScroll: 3,
		 infinite: false,
			  responsive: [
				{
				  breakpoint: 1024,
				  settings: {
					slidesToShow: 3,
					slidesToScroll: 3,
					infinite: false,
					dots: false
				  }
				},
				{
				  breakpoint: 800,
				  settings: {
					slidesToShow: 2,
					slidesToScroll: 2
				  }
				},
				{
				  breakpoint: 600,
				  settings: {
					slidesToShow: 1,
					slidesToScroll: 1
				  }
				},
				{
				  breakpoint: 480,
				  settings: {
					slidesToShow: 1,
					slidesToScroll: 1
				  }
				}
				// You can unslick at a given breakpoint now by adding:
				// settings: "unslick"
				// instead of a settings object
			  ]
		});
		$(".villageWardUl").slick({
			 slide: 'li',
			 slidesToShow: 3,
			 slidesToScroll: 3,
			 infinite: false,
			  responsive: [
				{
				  breakpoint: 1024,
				  settings: {
					slidesToShow: 3,
					slidesToScroll: 3,
					infinite: false,
					dots: false
				  }
				},
				{
				  breakpoint: 800,
				  settings: {
					slidesToShow: 2,
					slidesToScroll: 2
				  }
				},
				{
				  breakpoint: 600,
				  settings: {
					slidesToShow: 1,
					slidesToScroll: 1
				  }
				},
				{
				  breakpoint: 480,
				  settings: {
					slidesToShow: 1,
					slidesToScroll: 1
				  }
				}
				// You can unslick at a given breakpoint now by adding:
				// settings: "unslick"
				// instead of a settings object
			  ]
		});
	}
	
	$(document).on("click",".iconExpand",function(){
		$(this).find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
		$(".committeesBlock").toggleClass("col-md-6").toggleClass("col-md-12");
		$(".committeesBlock1").toggleClass("col-md-6").toggleClass("col-md-12");
		$(".committeesBlock").css("transition"," ease-in-out, width 0.7s ease-in-out");
		setTimeout(function(){
			$(".committeesHiddenBlock,.moreBlocksIcon").toggle();
			initialiseGraph();
		},800);
	});
	/* Notes Functionality Complete*/
	$(document).on("click",".notesIcon",function(){
		$(this).closest(".panel-heading").find(".notesDropDown").toggle();
	});
	$(document).on("click",".btnCustomCreate",function(){
		var getNewNotes = $(".notesArea").val();
		var todayDate = moment().format("DD MMMM YYYY");
		var commentText = '<span class="notesText">'+getNewNotes+'</span> - <span class="text-muted"><i>'+todayDate+'</i></span> <i class="glyphicon glyphicon-trash pull-right hoverBlock deleteNotes"></i><i class="glyphicon glyphicon-edit pull-right hoverBlock editNotes"></i>'; 
		$(".notesUl").append("<li>"+commentText+"</li>");
		$(".notesArea").val('');	
	});
	$(document).on("click",".editNotes",function(){
		var notesHtml = $(this).closest("li").find(".notesText").html();
		$(".notesArea").val(notesHtml);
	});
	$(document).on("click",".deleteNotes",function(){
		$(this).closest("li").remove();
	});
	$(document).on("click",".moreBlocksIcon",function(){
		$(".moreBlocks").toggle();
		setTimeout(function(){
			moreBlocks();
			customBuildGraph();
		},1000);
	});
	$(document).on("click",".activeUlCls li",function(){
		$(this).closest("ul").find("li").removeClass("active");
		$(this).addClass("active");
	});
	
	function customBuildGraph()
	{
		var j = 0;
		$(".graphStructure").each(function(){
			j = j + 1;
			$(this).attr("id","graphStructure_"+j+"");
			var getWidth = $("#graphStructure_"+j+"").width();
			
			var getNoOfLis = getWidth / $("#graphStructure_"+j+" li").length - 4;
			var kk = getNoOfLis.toFixed() + 'px';
			$("#graphStructure_"+j+" li").width(kk);
			
			var myColors = ['#66728C','#F56800','#31AA74'];
			var i = 0;
			$("#graphStructure_"+j+" li").each(function(){
				var l = $(this).find("span").length;
				var getNoOfSpans = $(this).find("span").length;
				$(this).find("span").each(function(){
					$(this).css('background-color', myColors[i]);
					i = (i + 1) % getNoOfSpans;
				});
				$(this).find("span").each(function(){
					var getPercentage = $(this).attr("attr_percent");
					if(l > 1)
					{
						$(this).css("height",getPercentage+'%');
					}else{
						$(this).css("height","100%");
					}
				});
			});
			
		});
	}
	
	$(document).on("click",".comparisonSelect li",function(){
		if($(this).hasClass("active") == true)
		{
			$(this).removeClass("active");
		}else{
			$(".comparisonSelect li").removeClass("active");
			$(this).addClass("active");
		}
	});
	
	
	