	
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
	$(document).on("click",".userStructureClass",function(){
	
		var  userAccessLevelId =  $(this).attr("attr_userAccessLevelId")
		var  userAccessLevelValuesString = $(this).attr("attr_userAccessLevelValuesString");
		var  userAccessLevelValuesArray = [];
		if($.trim(userAccessLevelValuesString).length > 0){
			userAccessLevelValuesArray = userAccessLevelValuesString.split(",");
		}
		//MAKE SELECTED MEMBERS DATA AS GLOBAL
		globalUserAccessLevelId = userAccessLevelId;
		globalUserAccessLevelValues = userAccessLevelValuesArray;
		
		if($("#cumulativeId").is(':checked')){
			getCommitteesCumulativeBasicReportChart(userAccessLevelId,userAccessLevelValuesArray);
			getCommitteesCumulativeOverallReportCharts(userAccessLevelId,userAccessLevelValuesArray);
		}
		if($("#comparitiveId").is(':checked')){
			getCommitteesComparativeBascicReportChart(userAccessLevelId,userAccessLevelValuesArray);
			getCommitteesComparativeOverallReportChart(userAccessLevelId,userAccessLevelValuesArray);
		}	
		
	});
	$(document).on("click",".hideDropDownView",function(){
		$(".profileDropDown").removeClass("dropDownView");
	});
	function getCommitteesCumulativeBasicReportChart(userAccessLevelId,userAccessLevelValues){
		
		$("#committeesForComulative").html('<div ><center ><img  src="images/icons/loading.gif" id="commulativeEnlargeLoadingId"></center></div>');
	    var basicCommitteeId = 1;
        var startDateString = '01/01/2015';
	    var endDateString = '28/07/2016';
	   
 	   var jsObj ={ 
	                userAccessLevelId : userAccessLevelId,
 			        userAccessLevelValuesArray : userAccessLevelValues,
 			        state : globalState,
 			        basicCommitteeId:basicCommitteeId,
 			        startDateString : startDateString,
 			        endDateString :   endDateString
 			      };
	  
	   $.ajax({
			type : 'POST',
			url : 'getCommitteesCumulativeBasicReportChartAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(results){
			$("#committeesForComulative").html('');
			buildMainCommitteChart(results);
		});
	}
	function getCommitteesCumulativeOverallReportCharts(userAccessLevelId,userAccessLevelValues){
       
	   $("#levelWiseComulativeForCommittees").html('<div ><center ><img  src="images/icons/loading.gif" id="commulativeEnlargeLoadingId"></center></div>');
       var basicCommitteeIdsArray= [];
       basicCommitteeIdsArray.push(1);
       basicCommitteeIdsArray.push(2);
       basicCommitteeIdsArray.push(3);
	   
       var startDateString = '01/01/2015';
	   var endDateString = '28/07/2016';
	   
 	   var jsObj ={ 
	                userAccessLevelId : userAccessLevelId,
 			        userAccessLevelValuesArray : userAccessLevelValues,
 			        state : globalState,
 			        basicCommitteeIdsArray:basicCommitteeIdsArray,
 			        startDateString : startDateString,
 			        endDateString :   endDateString
 			      };
 	   
 	   $.ajax({
 			type : 'POST',
 			url : 'getCommitteesCumulativeOverallReportChartsAction.action',
 			dataType : 'json',
 			data : {task:JSON.stringify(jsObj)}
 		}).done(function(result){
			 $("#levelWiseComulativeForCommittees").html('');
 			buildCommitteesWiseLevelsBasedDetails(result);
 		});
 	}
	function getCommitteesComparativeBascicReportChart(userAccessLevelId,userAccessLevelValues){
		
	   $("#CamparitiveBasicBlock").html('<div ><center ><img  src="images/icons/loading.gif" id="commulativeEnlargeLoadingId"></center></div>');
	   var basicCommitteeIdsArray= [];
       basicCommitteeIdsArray.push(1);
       basicCommitteeIdsArray.push(2);
       basicCommitteeIdsArray.push(3);
	   
	   var firstMonthString = '03/2015';
	   var secondMonthString = '05/2015';
	   var jsObj ={ 
				userAccessLevelId : userAccessLevelId,
				userAccessLevelValuesArray : userAccessLevelValues,
				state : globalState,
				basicCommitteeIdsArray:basicCommitteeIdsArray,
				firstMonthString  : firstMonthString,
				secondMonthString :   secondMonthString
			  };
		 $.ajax({
 			type : 'POST',
 			url : 'getCommitteesComparativeBascicReportChartAction.action',//getBasicComparativeWiseCommitteesCountsAction
 			dataType : 'json',
 			data : {task:JSON.stringify(jsObj)}
 		}).done(function(result){
			$("#CamparitiveBasicBlock").html();
 			buildGraphComparativeForBasicCommitteeBlock(result);
 		});
	}
	
	function getCommitteesComparativeOverallReportChart(userAccessLevelId,userAccessLevelValues){
		
		  $("#levelWiseComparativeForCommittees").html('<div ><center ><img  src="images/icons/loading.gif" id="commulativeEnlargeLoadingId"></center></div>');
		   var basicCommitteeIdsArray= [];
	       basicCommitteeIdsArray.push(1);
	       basicCommitteeIdsArray.push(2);
	       basicCommitteeIdsArray.push(3);
		   
		   var firstMonthString = '03/2015';
		   var secondMonthString = '05/2015';
		   var jsObj ={ 
					userAccessLevelId : userAccessLevelId,
					userAccessLevelValuesArray : userAccessLevelValues,
					state : globalState,
					basicCommitteeIdsArray:basicCommitteeIdsArray,
					firstMonthString  : firstMonthString,
					secondMonthString :   secondMonthString
				  };
			 $.ajax({
	 			type : 'POST',
	 			url : 'getCommitteesComparativeOverallReportChartAction.action',
	 			dataType : 'json',
	 			data : {task:JSON.stringify(jsObj)}
	 		}).done(function(result){
				$("#levelWiseComparativeForCommittees").html('');
	 			buildlevelWiseComparativeCountsByBasicCommittees(result);
	 		});
	 }
	function buildMainCommitteChart(result){
		
		var CommCompletedArray ="";
		var CommStartedArray ="";
		var CommYetToStarted ="";
		var committeeName;
		if(result !=null ){
			CommCompletedArray=result.completedCount;
			CommStartedArray=result.startedCount;
			CommYetToStarted=result.yetToStartCount;
			committeeName = result.name;
		}
		$('#committeesForComulative').highcharts({
			colors:['#CBE7B9','#00C7F7','#D6EB59'],
			chart: {
			  plotBackgroundColor: null,
			  plotBorderWidth: null,
			  plotShadow: false,
			  type: 'pie',
			 
			},
			title: {
				 enabled: true,
				text: committeeName,
				verticalAlign: 'top',
				align: 'center',
				style: {
					fontSize: '12px',
					color: 'rgb(85, 85, 85)'
				}
						
			},
			tooltip: {
			  pointFormat: ' <b>{point.percentage:.1f}%</b>'
			},
			legend: {
				enabled: true,
				floating: true,
				verticalAlign: 'bottom',
				align:'left',
				y:20,
				x:5,
				width:700,
				useHtml: ' {point.x}'
			},
			plotOptions: {
			  series: {
				allowPointSelect: true,
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
	function buildCommitteesWiseLevelsBasedDetails(result){
		$("#levelWiseComulativeForCommittees").html('');
		if(result != null && result.length > 0){
			var str='';
			
			for(var i in result){
				$('#levelWiseComulativeForCommitteeslarge'+i+'').html('');
				  str+='<div class="col-md-4 col-xs-12 col-sm-4">';
				str+='<div id="levelWiseComulativeForCommitteeslarge'+i+'" class="chart" ></div>';
				str+='</div>';
			}
			
		}
	$("#levelWiseComulativeForCommittees").html(str);
	
	if(result != null && result.length > 0){
		
		for(var i in result){
			
			
				var pieChartDataArray=[];
				var barChartLocLevelArray=[];
				var barChartLocDetailsForComArray=[];
				var barChartLocDetailsForStarArray=[];
				var barChartLocDetailsForYetArray=[];
				var committeeNameForBarAndPie;
				
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
				committeeNameForBarAndPie = result[i].name
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
					completedCount = result[i].subList[j].completedCount;
					startedCount = result[i].subList[j].startedCount;
					yetToStartCount = result[i].subList[j].yetToStartCount;
					/* if(result[i].subList[j].completedCount == null || result[i].subList[j].completedCount == 0){
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
					} */
					
					
					
					barChartLocDetailsForComArray.push(completedCount);
					barChartLocDetailsForStarArray.push(startedCount);
					barChartLocDetailsForYetArray.push(yetToStartCount);
					
				}
			}
					
				 $('#levelWiseComulativeForCommitteeslarge'+i+'').highcharts({
					 colors:['#CBE7B9','#00C7F7','#D6EB59'],
						chart: {
							type: 'column'
						},
						title: {
							text: committeeNameForBarAndPie
						},
					
						xAxis: {
							categories: barChartLocLevelArray,
							gridLineWidth: 0,
							minorGridLineWidth: 0
						   // crosshair: true
						},
						yAxis: {
							gridLineWidth: 0,
							minorGridLineWidth: 0,
							min: 0,
							title: {
								text: ''
							},
							labels:{
								enabled: true
							},
						},
						tooltip: {
									headerFormat: '<b>{point.x}</b><br/>',
									pointFormat: '{series.name}: {point.y}'
								},
						plotOptions: {
							column: {
								pointPadding: 0.2,
								borderWidth: 0,
								groupPadding: 0.0,
								dataLabels: {
									enabled: true,
									format: '{point.y}'
									
								},
							}
						},
						series: [{
							name: 'Completed',
							data: barChartLocDetailsForComArray

						}, {
							name: 'Started',
							data: barChartLocDetailsForStarArray

						}, {
							name: 'Yet To Started',
							data: barChartLocDetailsForYetArray

						}/* ,{
						type: 'pie',
						name: 'Total consumption',
						data: pieChartDataArray,
						center: [200, 0],
						size: 60,
						showInLegend: false,
						dataLabels: {
							enabled: false
						}
					} */]
				});
				
		}
	}
		
}

function buildGraphComparativeForBasicCommitteeBlock(result){
	
	if(result != null && result.length > 0){
		
			var str='';
			
			for(var i in result){
				str+='<div class="col-md-6 col-xs-12 col-sm-6">';
				str+='<div id="committeesForCamparitiveBasic'+i+'" class="chart"></div>';
				str+='</div>';
			}
			
		}
	$("#CamparitiveBasicBlock").html(str);
		if(result != null && result.length > 0){
			
			var monthName;
			for(var i in result){
				 monthName = result[i].name;
				 
				if(result[i].subList != null && result[i].subList.length >0){
						var committeeNamesForBasicArray =[];
						var completedCountForBasicCommitteeArray =[];
						var completedCountForBasic;
						var committeeNamesForBasic;
						for(var j in result[i].subList){
							committeeNamesForBasic = result[i].subList[j].name;
							 if(result[i].subList[j].completedCount == null || result[i].subList[j].completedCount == 0){
								 completedCountForBasic  = '';
							 }else{
								completedCountForBasic  = result[i].subList[j].completedCount; 
							 }
							
						 
						 committeeNamesForBasicArray.push(committeeNamesForBasic);
						 completedCountForBasicCommitteeArray.push(completedCountForBasic);
						
					}
					
				}
			
				$('#committeesForCamparitiveBasic'+i+'').highcharts({
					colors:['#CBE7B9','#00C7F7','#D6EB59'],
					chart: {
						type: 'column'
					},
					title: {
						text: monthName
					},
					
					xAxis: {
						categories: committeeNamesForBasicArray,
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
						enabled: false,
						align: 'right',
						x: -30,
						verticalAlign: 'bottom',
						y: 25,
						floating: true,
						backgroundColor: (Highcharts.theme && Highcharts.theme.background2) || 'white',
						borderColor: '#CCC',
						borderWidth: 1,
						shadow: false
					},
					tooltip: {
						headerFormat: '<b>{point.x}</b><br/>',
						pointFormat: '{series.name}: {point.y}<br/>Total: {point.stackTotal}'
					},
					 plotOptions: {
						column: {
							pointPadding: 0.2,
							borderWidth: 2,
							groupPadding: 0.2
						}
						
					}, 
					series: [{
						name: 'Completed',
						dataLabels: {
							enabled: true
						},
						data: completedCountForBasicCommitteeArray
					}]
				});
			}
		}

	}

	function buildlevelWiseComparativeCountsByBasicCommittees(result){
		
		if(result != null && result.length > 0){
		
			var str='';
			
			for(var i in result){
				str+='<div class="col-md-4 col-xs-12 col-sm-4">';
				str+='<div id="levelWiseComparativeForCommitteesDetails'+i+'" class="chart"></div>';
				str+='</div>';
			}
			
		}
		$("#levelWiseComparativeForCommittees").html(str);
			
		if(result != null && result.length > 0){
			for(var i in result){
				var result1 = result[i];
				var committeeName = result1.name;
				var valuesArr = [];
				
				if(result1.subList != null && result1.subList.length > 0){
					for(var j in result1.subList){
						var valInArr=[];
						var mnthName=result1.subList[j].name;
						if(result1.subList[j].subList != null && result1.subList[j].subList.length > 0){
							for(var k in result1.subList[j].subList){
								var completedCount;
								if(result1.subList[j].subList[k].completedCount == null || result1.subList[j].subList[k].completedCount == 0){
									completedCount ='';
								}else{
									completedCount = result1.subList[j].subList[k].completedCount;
								}
								valInArr.push(completedCount);
							}
						}
						var obj={
							name: mnthName,
							data: valInArr
						};
						valuesArr.push(obj);
					}
				}
				
				var catName=[];
				if(result1.subList != null && result1.subList.length > 0){
					if(result1.subList[0].subList != null && result1.subList[0].subList.length > 0){
						for(var s in result1.subList[0].subList){
							catName.push(result1.subList[0].subList[s].name);
						}	
					}
				}
				
				$('#levelWiseComparativeForCommitteesDetails'+i).highcharts({
					colors:['#CBE7B9'],
					chart: {
						type: 'column'
					},
					title: {
							text: committeeName,
							verticalAlign: 'bottom',
							align: 'center',
							y:13,
							style: {
									fontSize: '12px',
									color: 'rgb(85, 85, 85)',
									fontWeight:'bold'
								}
						},
					xAxis: {
						categories: catName,
						gridLineWidth: 0,
						minorGridLineWidth: 0,
					},
					yAxis: {
						gridLineWidth: 0,
						minorGridLineWidth: 0,
						min: 0,
						title: {
							text:''
						},
						
						labels:
						{
						  enabled: true
						},
					},
					legend: {
						enabled: false,
						align: 'right',
						x: -30,
						verticalAlign: 'top',
						y: 25,
						floating: true,
						backgroundColor: (Highcharts.theme && Highcharts.theme.background2) || 'white',
						borderColor: '#CCC',
						borderWidth: 1,
						shadow: false
					},
					tooltip: {
						headerFormat: '<b>{point.x}</b><br/>',
						pointFormat: '{series.name}: {point.y}<br/>'
					},
					 plotOptions: {
						column: {
							pointPadding: 0.2,
							borderWidth: 2,
							groupPadding: 0.2,
							stacking: '',
							dataLabels: {
								enabled: true,
								 format: '{point.y}'
								 /* rotation: 90,
								 style: {
									fontSize: '10px',
									
								} */
							},
						 	
						}
					}, 
					series:  valuesArr,
					
					
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


	function getLoggedInUserStructure(){
		$("#userLevelDetailsDiv").html('<div ><center ><img  src="images/icons/loading.gif" id="commulativeEnlargeLoadingId"></center></div>');
		var jsObj ={userId:globalUserId}
		
		$.ajax({
			type : 'POST',
			url : 'getLoggedInUserStructureAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$("#userLevelDetailsDiv").html('');
			buildUserLoginLevelDetails(result);
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
	
	