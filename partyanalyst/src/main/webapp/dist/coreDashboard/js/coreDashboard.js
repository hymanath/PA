	
	function getUserBasicDetails(){
		
		var jsObj ={userId:globalUserId}
		$.ajax({
			type : 'GET',
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
	
	function getMainCommitteeCountDetails(){
		
		var committeeId = 1;
		var state ="AP"
	   var jsObj ={committeeId:committeeId,state:state}
	   $.ajax({
			type : 'GET',
			url : 'getMainCommitteeCountDetailsAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(results){
			buildMainCommitteChart(results);
		});
	}
	function buildMainCommitteChart(results){
		
		var CommCompletedArray ="";
		var CommStartedArray ="";
		var CommYetToStarted ="";
		if(results !=null ){
			for(var i in results){
				CommCompletedArray=results[i].mainCommCompletedCount;
				CommStartedArray=results[i].mainCommStartedCount;
				CommYetToStarted=results[i].mainCommNotYetStarted;
				
			}
			
		}
		
		$('#committees').highcharts({
			colors:['#CBE7B9','#00C7F7','#D6EB59'],
			chart: {
			  plotBackgroundColor: null,
			  plotBorderWidth: null,
			  plotShadow: false,
			  type: 'pie',
			 
			},
			title: {
						text: null
					},
			tooltip: {
			  pointFormat: ' <b>{point.percentage:.1f}%</b>'
			},
			legend: {
				enabled: true,
				floating: true,
				verticalAlign: 'bottom',
				align:'left',
				y:8,
				x:-0.5,
				//width:100,
				useHtml: ' {point.x}'
			},
			plotOptions: {
			  series: {
				allowPointSelect: false,
				cursor: 'pointer',
				 dataLabels: {
				
					distance: -43,
					formatter: function () {
						if(this.y!=0)  return Math.round(this.y);

					}
				},
				showInLegend: true
			  }
			},
			series: [{
			  //name: '',
			  colorByPoint: true,
			  data: [{
				name: 'Completed',
				y: CommCompletedArray
			  }, {
				name: 'In Progress',
				y: CommStartedArray
			  }, {
				name: 'Yet To Start',
				y: CommYetToStarted
			  }]
			}]

		  });
	
	}
	
	function getCommitteesWiseLevelsBasedDetails(){
       
       var basicCommitteeIdsArray= [];
       basicCommitteeIdsArray.push(1);
       basicCommitteeIdsArray.push(2);
       basicCommitteeIdsArray.push(3);
	   
       var startDateString = '01/01/2015';
	   var endDateString = '28/07/2016';
	   var state = 'AP';
	   
 	   var jsObj ={ 
	                userAccessLevelId : globalUserAccessLevelId,
 			        userAccessLevelValuesArray : globalUserAccessLevelValues,
 			        state : state,
 			        basicCommitteeIdsArray:basicCommitteeIdsArray,
 			        startDateString : startDateString,
 			        endDateString :   endDateString
 			      };
 	   
 	   $.ajax({
 			type : 'POST',
 			url : 'getCommitteesWiseLevelsBasedDetailsAction.action',
 			dataType : 'json',
 			data : {task:JSON.stringify(jsObj)}
 		}).done(function(result){
 			buildCommitteesWiseLevelsBasedDetails(result);
 		});
 	}
	function getBasicComparativeWiseCommitteesCounts(){
		
	   var basicCommitteeIdsArray= [];
       basicCommitteeIdsArray.push(1);
       basicCommitteeIdsArray.push(2);
       basicCommitteeIdsArray.push(3);
		
	   var state = 'AP';
	   var firstMonthString = '04/2015';
	   var secondMonthString = '02/2016';
	   var jsObj ={ 
				userAccessLevelId : globalUserAccessLevelId,
				userAccessLevelValuesArray : globalUserAccessLevelValues,
				state : state,
				basicCommitteeIdsArray:basicCommitteeIdsArray,
				firstMonthString  : firstMonthString,
				secondMonthString :   secondMonthString
			  };
		 $.ajax({
 			type : 'POST',
 			url : 'getBasicComparativeWiseCommitteesCountsAction.action',
 			dataType : 'json',
 			data : {task:JSON.stringify(jsObj)}
 		}).done(function(result){
 			
 		});
	}
	
	function levelWiseComparativeCountsByBasicCommittees(){
		
		   var basicCommitteeIdsArray= [];
	       basicCommitteeIdsArray.push(1);
	       basicCommitteeIdsArray.push(2);
	       basicCommitteeIdsArray.push(3);
			
		   var state = 'AP';
		   var firstMonthString = '04/2015';
		   var secondMonthString = '05/2016';
		   var jsObj ={ 
					userAccessLevelId : globalUserAccessLevelId,
					userAccessLevelValuesArray : globalUserAccessLevelValues,
					state : state,
					basicCommitteeIdsArray:basicCommitteeIdsArray,
					firstMonthString  : firstMonthString,
					secondMonthString :   secondMonthString
				  };
			 $.ajax({
	 			type : 'POST',
	 			url : 'levelWiseComparativeCountsByBasicCommitteesAction.action',
	 			dataType : 'json',
	 			data : {task:JSON.stringify(jsObj)}
	 		}).done(function(result){
	 			
	 		});
	 }
	
	function buildCommitteesWiseLevelsBasedDetails(result){
		if(result != null && result.length > 0){
			var str='';
			
			for(var i in result){
				  str+='<div class="col-md-4 col-xs-12 col-sm-4">';
				str+='<div id="commiteelarge'+i+'" class="chart"></div>';
				str+='</div>';
			}
			
		}
	$("#graphsDivId").html(str);
	if(result != null && result.length > 0){
		for(var i in result){
				var pieChartDataArray=[];
				var barChartLocLevelArray=[];
				var barChartLocDetailsForComArray=[];
				var barChartLocDetailsForStarArray=[];
				var barChartLocDetailsForYetArray=[];
				
				 var obj1 = {
							name: 'Completed',
							y: result[i].completedCount
						};
				var obj2 = {
							name: 'Started',
							y: result[i].startedCount
						};
				var obj3 = {
							name: 'Yet To Started',
							y: result[i].yetToStartCount
						};						
				pieChartDataArray.push(obj1);
				pieChartDataArray.push(obj2);
				pieChartDataArray.push(obj3);
				
			if(result[i].subList !=null && result[i].subList.length >0){
				for(var j in result[i].subList){
					var locationLevel = result[i].subList[j].name;
					barChartLocLevelArray.push(locationLevel);
					var completedCount;
					var startedCount;
					var yetToStartCount;
					if(result[i].subList[j].completedCount == null || result[i].subList[j].completedCount == 0){
						 completedCount ="";
					}else{
						completedCount = result[i].subList[j].completedCount;
					}
					if(result[i].subList[j].startedCount == null || result[i].subList[j].startedCount == 0){
						 startedCount = '';
					}else{
						startedCount = result[i].subList[j].startedCount;
					}
					if(result[i].subList[j].yetToStartCount == null || result[i].subList[j].yetToStartCount ==  0){
						 yetToStartCount = '';
					}else{
						yetToStartCount = result[i].subList[j].yetToStartCount;
					}
					
					barChartLocDetailsForComArray.push(completedCount);
					barChartLocDetailsForStarArray.push(startedCount);
					barChartLocDetailsForYetArray.push(yetToStartCount);
					
				}
			}
				var getWidth = $('.committeesBlock').width()
				$('#commiteelarge'+i+'').css("width",getWidth+'px');	
				$('#commiteelarge'+i+'').highcharts({
					colors:['#CBE7B9','#00C7F7','#D6EB59'],
					chart: {
						type: 'column'
					},
					title: {
						text: null
					},
					xAxis: {
						categories: barChartLocLevelArray,
						gridLineWidth: 0,
						minorGridLineWidth: 0
					},
					yAxis: {
						gridLineWidth: 0,
						minorGridLineWidth: 0,
						min: 0,
						labels:
						{
						  enabled: false
						},
						title: {
							text: null
						},
						stackLabels: {
							enabled: true,
							
							
							style: {
								fontWeight: 'bold'
								//color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
							}
						}
					},
					legend: {
						align: 'left',
						x: 30,
						verticalAlign: 'bottom',
						y: 15,
						floating: false,
						//backgroundColor: (Highcharts.theme && Highcharts.theme.background2) || 'white',
						borderColor: '#CCC',
						borderWidth: 1,
						shadow: false,
						enabled:true,
					},
					tooltip: {
					headerFormat: '<b>{point.x}</b><br/>',
					pointFormat: '{series.name}: {point.y}<br/>Total: {point.stackTotal}'
				},
					plotOptions: {
						column: {
							stacking: 'normal',
							minPointLength: 4,
							dataLabels: {
								enabled: true,
								color: (Highcharts.theme && Highcharts.theme.dataLabelsColor) || 'white',
								style: {
									textShadow: '0 0 3px black'
								}
							}
						}
					},
					series: [{
							name: 'Completed',
							  data: barChartLocDetailsForComArray
							
						}, {
							name: 'Started',
							 data: barChartLocDetailsForStarArray
						
							
						}, {
							name: 'Yet TO Started',
							data: barChartLocDetailsForYetArray
							
						},{
						type: 'pie',
						name: 'Total consumption',
						data: pieChartDataArray,
						center: [200, 0],
						size: 60,
						showInLegend: false,
						dataLabels: {
							enabled: false
						}
					}]
				});
			
				
		}
	}
		
}

function blockHeights()
{
	var maxHeight = 0;
	$(".panelCustomBlock").each(function(){
	   if ($(this).height() > maxHeight) { maxHeight = $(this).height(); }
	});
	$(".panelCustomBlock").height(maxHeight);

}

function buildBasicgraphs(){
	
	$('#meetings').highcharts({
		colors:['#D6EB59','#FF5C54','#D6EB59'],
		chart: {
			plotBackgroundColor: null,
			plotBorderWidth: null,
			plotShadow: false,
			type: 'pie'
		},
		title: {
                text: null
            },
		tooltip: {
			pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
		},
		legend: {
			enabled: true,
			floating: true,
			verticalAlign: 'top',
			align:'left',
			y:30,
			x:-20,
			width:50,
			useHtml: ' {point.x}'
		},
		plotOptions: {
			pie: {
				allowPointSelect: true,
				cursor: 'pointer',
				dataLabels: {
					enabled: false
				},
				showInLegend: true
			}
		},
		
		series: [{
			name: 'Brands',
			colorByPoint: true,
			data: [{
				name: 'Conducted',
				y: 46.33
			}, {
				name: 'Not Conducted',
				y: 24.03,
			}]
		}]
	});
	$('#news').highcharts({
		colors:['#90E3FA','#FF8A83','#D6EB59'],
		chart: {
			plotBackgroundColor: null,
			plotBorderWidth: null,
			plotShadow: false,
			type: 'pie'
		},
		title: {
                text: null
            },
		tooltip: {
			pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
		},
		legend: {
			enabled: true,
			floating: true,
			verticalAlign: 'top',
			align:'left',
			y:30,
			x:-20,
			width:50,
			useHtml: ' {point.x}'
		},
		plotOptions: {
			pie: {
				allowPointSelect: true,
				cursor: 'pointer',
				dataLabels: {
					enabled: false
				},
				showInLegend: true
			}
		},
		
		series: [{
			name: 'Brands',
			colorByPoint: true,
			data: [{
				name: 'Positive',
				y: 46.33
			}, {
				name: 'Negative',
				y: 24.03,
			}, {
				name: 'Neutral',
				y: 24.03,
			}]
		}]
	});
	$('#events').highcharts({
		colors:['#CBE7B9','#00C7F7','#D6EB59'],
		chart: {
			plotBackgroundColor: null,
			plotBorderWidth: null,
			plotShadow: false,
			type: 'pie'
		},
		title: {
                text: null
            },
		tooltip: {
			pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
		},
		legend: {
			enabled: true,
			floating: true,
			verticalAlign: 'top',
			align:'left',
			y:30,
			x:-20,
			width:50,
			useHtml: ' {point.x}'
		},
		plotOptions: {
			pie: {
				allowPointSelect: true,
				cursor: 'pointer',
				dataLabels: {
					enabled: false
				},
				showInLegend: true
			}
		},
		series: [{
			name: 'Brands',
			colorByPoint: true,
			data: [{
				name: 'Conducted',
				y: 46.33
			}, {
				name: 'Non Conducted',
				y: 24.03,
			}]
		}]
	});
	$('#trainings').highcharts({
		colors:['#CBE7B9','#00C7F7','#D6EB59'],
		chart: {
			plotBackgroundColor: null,
			plotBorderWidth: null,
			plotShadow: false,
			type: 'pie'
		},
		title: {
                text: null
            },
		tooltip: {
			pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
		},
		legend: {
			enabled: true,
			floating: true,
			verticalAlign: 'top',
			align:'left',
			y:30,
			x:-20,
			width:50,
			useHtml: ' {point.x}'
		},
		plotOptions: {
			pie: {
				allowPointSelect: true,
				cursor: 'pointer',
				dataLabels: {
					enabled: false
				},
				showInLegend: true
			}
		},
		series: [{
			name: 'Brands',
			colorByPoint: true,
			data: [{
				name: 'Upcoming',
				y: 46.33
			}, {
				name: 'Running',
				y: 24.03,
			}, {
				name: 'Completed',
				y: 10.38
			}]
		}]
	});
	$('#debates').highcharts({
		colors:['#CBE7B9','#00C7F7','#D6EB59'],
		chart: {
			plotBackgroundColor: null,
			plotBorderWidth: null,
			plotShadow: false,
			type: 'pie'
		},
		title: {
                text: null
            },
		tooltip: {
			pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
		},
		legend: {
			enabled: true,
			floating: true,
			verticalAlign: 'top',
			align:'left',
			y:30,
			x:-20,
			width:50,
			useHtml: ' {point.x}'
		},
		plotOptions: {
			pie: {
				allowPointSelect: true,
				cursor: 'pointer',
				dataLabels: {
					enabled: false
				},
				showInLegend: true
			}
		},
		series: [{
			name: 'Brands',
			colorByPoint: true,
			data: [{
				name: 'TDP',
				y: 46.33
			}, {
				name: 'OTHERS',
				y: 24.03,
			}]
		}]
	});
}