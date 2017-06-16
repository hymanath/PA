 //Angular Start
	var myApp = angular.module('ruralWaterSupply',[]);
	
	myApp.controller('UserController', function($scope, $http) {
		onloadCalls();

		function onloadCalls(){
			getHabitationCoverageByStatusByLocationType();
			getLabTestDetails();
			getHabitationSupplyDetails();
			getSchemesDetails();
			getSchemeWiseWorkDetails();
			getAssetInfoBetweenDates();
			getAlertDetailsOfCategoryByStatusWise();
			getAlertFeedbackStatusDetails();
		}
		function highcharts(id,type,xAxis,yAxis,legend,data,plotOptions,tooltip,colors)
		{
			'use strict';
			$('#'+id).highcharts({
				 colors: colors,
				chart: type,
				title: {
					text: null
				},
				subtitle: {
					text: null
				},
				xAxis: xAxis,
				yAxis: yAxis,
				tooltip: tooltip,
				plotOptions: plotOptions,
				legend: legend,
				series: data
			});
		}
		
		
		function getHabitationCoverageByStatusByLocationType()
		{
			var json = {
				locationType:"state",
				year:"2017"
			}
			$http({
				url: 'getHabitationCoverageByStatusByLocationType',
				data: JSON.stringify(json),
				method: "POST",
				dataType: 'json', 

			}).then(function(response) {
				if(response.data != null && response.data.length > 0){
					buildChartForHabitationCoverage(response.data);
					buildChartForHabitationCoverageStatus(response.data);
				}
			});
		}
		
		function buildChartForHabitationCoverage(response){
			
			var dataArr = [];
			var pcCount = 0;
			for(var i in response){
				for(var j in response[i].statusList){
					if(response[i].statusList[j].status != "NC"){
						if(response[i].statusList[j].status == "PC1" || response[i].statusList[j].status == "PC2" || response[i].statusList[j].status == "PC3" || response[i].statusList[j].status == "PC4"){
							pcCount = pcCount+parseInt(response[i].statusList[j].count);
							if(response[i].statusList[j].status == "PC4"){
								var pcArr = [];
								pcArr.push("PC");
								pcArr.push(parseInt(pcCount));
								dataArr.push(pcArr);
							}
						}else{
							var tempArr = [];
							response[i].statusList[j].status == "NSS" ? tempArr.push("QA"):tempArr.push(response[i].statusList[j].status);
							tempArr.push(parseInt(response[i].statusList[j].count));
							dataArr.push(tempArr);
						}
					}
				}
			}
			
			var colors = ['#494949','#FC5049','#14BAAD']
			var id = 'totalValues';
			var type = {
				type: 'column',
				backgroundColor:'transparent'
			};
			var legend = {
				enabled: false
			};
			var yAxis = {
				title: {
					text: null
				},
			};
			var xAxis = {
				min: 0,
				gridLineWidth: 0,
				minorGridLineWidth: 0,
				categories: []
			};
			var plotOptions ={ column: {
					colorByPoint: true
				}};
			var tooltip = {
				pointFormat: '{point.y}'
			};

			var data = [{
				name: '',
				data: dataArr,

				dataLabels: {
					enabled: true,
					color: '#FFFFFF',
					align: 'right',
					format: '{point.y}',
				}
			}];
			highcharts(id,type,xAxis,yAxis,legend,data,plotOptions,tooltip,colors);
		}
		
		function buildChartForHabitationCoverageStatus(response){
			var dataArr = [];
			for(var i in response){
			  for(var j in response[i].statusList){
					if(response[i].statusList[j].status != "NC"){
						var tempArr = [];
						tempArr.push(response[i].statusList[j].status);
						tempArr.push(parseInt(response[i].statusList[j].count));
						dataArr.push(tempArr);
					}
				}
			}
			var colors = ['#14BAAD']
			var id = 'habitation';
			var type = {
				type: 'column',
				backgroundColor:'transparent'
			};
			var legend = {
				enabled: false
			};
			var yAxis = {
				title: {
					text: null
				},
			};
			var xAxis = {
				min: 0,
				gridLineWidth: 0,
				minorGridLineWidth: 0,
				categories: []
			};
			var plotOptions ={ column: {
					colorByPoint: true
				}};
			var tooltip = {
				pointFormat: '{point.y}'
			};

			var data = [{
				name: '',
				data: dataArr,

				dataLabels: {
					enabled: true,
					color: '#FFFFFF',
					align: 'right',
					format: '{point.y}',
				}
			}];
			highcharts(id,type,xAxis,yAxis,legend,data,plotOptions,tooltip,colors);
			
		}
		function getLabTestDetails()
		{
			var json = {
				year:"2017"
			}
			$http({
				url: 'getLabTestDetails',
				data: JSON.stringify(json),
				method: "POST",
				dataType: 'json', 
			}).then(function(response) {
				if(response.data != null){
					buildChartForLabTestDetails(response.data);
				}
			});
		}
		
		function buildChartForLabTestDetails(response){
			var dataArr = [];
			var arr1 = [],arr2 = [];
			arr1.push("physicalTestCount");
			arr1.push(parseInt(response.physicalTestCount));
			dataArr.push(arr1);
			arr2.push("bacterialTestCount");
			arr2.push(parseInt(response.bacterialTestCount));
			dataArr.push(arr2);
			var colors = ['#14BAAD','#FC5049']
			var id = 'overView';
			var type = {
				type: 'column',
				backgroundColor:'transparent'
			};
			var legend = {
				enabled: false
			};
			var yAxis = {
				title: {
					text: null
				},
			};
			var xAxis = {
				min: 0,
				gridLineWidth: 0,
				minorGridLineWidth: 0,
				categories: []
			};
			var plotOptions ={ column: {
					colorByPoint: true
				}};
			var tooltip = {
				pointFormat: '{point.y}'
			};

			var data = [{
				name: '',
				data: dataArr,

				dataLabels: {
					enabled: true,
					color: '#FFFFFF',
					align: 'right',
					format: '{point.y}',
				}
			}];
			highcharts(id,type,xAxis,yAxis,legend,data,plotOptions,tooltip,colors);
		}
		
		function getHabitationSupplyDetails(){
			var json = {
				year:"2017"
			}
			$http({
				url: 'getHabitationSupplyDetails',
				data: JSON.stringify(json),
				method: "POST",
				dataType: 'json', 

			}).then(function(response) {
				//$scope.myWelcome = response.data;
				if(response.data !=null){
					buildHabitationSupplyDetails(response.data);
				}
			});
		}
		
		function buildHabitationSupplyDetails(result){
			var dataArr = [];
				var subDataArr1=[],subDataArr2=[];
				
				subDataArr1.push("SAFE");
				subDataArr1.push(result.safeMLD);
				dataArr.push(subDataArr1);
				
				subDataArr2.push("UN-SAFE");
				subDataArr2.push(result.unsafeMLD);
				dataArr.push(subDataArr2);
				
				var id = 'levelOfSupply';
				var type = {
					type: 'column',
					backgroundColor:'transparent'
				};
				var legend = {
					enabled: false
				};
				var yAxis = {
					min: 0,
					gridLineWidth: 0,
					minorGridLineWidth: 0,
					title: {
						text: null
					},
				};
				var xAxis = {
					min: 0,
					gridLineWidth: 0,
					minorGridLineWidth: 0,
					categories: []
				};
				var plotOptions ={ column: {
						colorByPoint: true
					}};
				var tooltip = {
					pointFormat: '{point.y}'
				};

				var data = {
					name: '',
					data: dataArr,

					dataLabels: {
						enabled: true,
						color: '#FFFFFF',
						align: 'right',
						format: '{point.y}',
					}
				};
				var colors = ['#14BAAD','#FC5049']
				highcharts(id,type,xAxis,yAxis,legend,data,plotOptions,tooltip,colors);
		}
		
		function getSchemesDetails(){
			var json = {
				fromDateStr:"01-12-2016",
				toDateStr:"01-12-2017"
			}
			$http({
				url: 'getSchemesDetails',
				data: JSON.stringify(json),
				method: "POST",
				dataType: 'json', 

			}).then(function(response) {
				if(response.data !=null && response.data.length>0){
					buildSchemesDetails(response.data);
				}
			});
		}
		function buildSchemesDetails(result){
			var dataArr = [];

				for(var i in result)
				  {
					var tempArr = [];
					
					if(result[i].assetType == "PWS" || result[i].assetType == "CPWS")
					{
						if(result[i].assetType == "PWS"){
							result[i].assetType = "SINGAL VILLAGE";
						}else{
							result[i].assetType = "MULTI VILLAGE";
						}
						tempArr.push(result[i].assetType);
						tempArr.push(parseInt(result[i].count));
						dataArr.push(tempArr);
						
					}
					
				  }
				  
				var id = 'schemes';
				var type = {
					type: 'column',
					backgroundColor:'transparent'
				};
				var legend = {
					enabled: false
				};
				var yAxis = {
					min: 0,
					gridLineWidth: 0,
					minorGridLineWidth: 0,
					title: {
						text: null
					},
				};
				var xAxis = {
					min: 0,
					gridLineWidth: 0,
					minorGridLineWidth: 0,
					categories: []
				};
				var plotOptions ={ column: {
						colorByPoint: true
					}};
				var tooltip = {
					pointFormat: '{point.y}'
				};

				var data = [{
					name: '',
					data: dataArr,

					dataLabels: {
						enabled: true,
						color: '#FFFFFF',
						align: 'right',
						format: '{point.y}',
					}
				}];
				var colors = ['#14BAAD','#FC5049']
				highcharts(id,type,xAxis,yAxis,legend,data,plotOptions,tooltip,colors);
		}
		function getSchemeWiseWorkDetails(){
			var json = {
					formDateStr:"01-01-2016",
					toDateStr:"01-04-2017"
			}
			$http({
				url: 'getSchemeWiseWorkDetails',
				data: JSON.stringify(json),
				method: "POST",
				dataType: 'json', 

			}).then(function(response) {
				if(response.data !=null && response.data.length>0){
					buildSchemeWiseWorkDetails(response.data);
				}
			});
		}
		function buildSchemeWiseWorkDetails(result){
			var dataArr = [];
			var assetTypeArr = [];
			var workOngoingArr = [];
			var workNotGroundedArr = [];
			var workCompletedArr = [];
			var workComissionedArr = [];
				for(var i in result)
				  {					 
					if(result[i].assetType == "PWS" || result[i].assetType == "CPWS")
					{
						assetTypeArr.push(result[i].assetType);						
						workOngoingArr.push({"y":result[i].workOngoingCount,color:'#14BBAE'});
						workNotGroundedArr.push({"y":result[i].workNotGroundedCount,color:'#FC5E57'});
						workCompletedArr.push({"y":result[i].workCompletedCount,color:'#FFBF14'});
						workComissionedArr.push({"y":result[i].workComissionedCount,color:'#465556'});						
					}
					
				  }
				  
				var id = 'habitationWorks';
				var type = {
					type: 'column',
					backgroundColor:'transparent'
				};
				var legend = {
					//enabled: true
				};
				var yAxis = {
					min: 0,
					gridLineWidth: 0,
					minorGridLineWidth: 0,
					title: {
						text: null
					},
				};
				var xAxis = {
					min: 0,
					gridLineWidth: 0,
					minorGridLineWidth: 0,
					categories: assetTypeArr
				};
				var plotOptions ={ 
						column: {
						//colorByPoint: true
					}};
				var tooltip = {
					pointFormat: '{point.y}'
				};
				var colors = []
				var data =  [{
								name: 'Ongoing',
								data: workOngoingArr
							}, {
								name: 'Not Grounded',
								data: workNotGroundedArr
							}, {
								name: 'Completed',
								data: workCompletedArr,
							}, {
								name: 'Commissioned',
								data: workComissionedArr
							}]
				highcharts(id,type,xAxis,yAxis,legend,data,plotOptions,tooltip,colors);
		}
		function getAssetInfoBetweenDates(){ 
			var json = {
				formDateStr:"01-12-2016",
				toDateStr:"01-12-2017"
			}
			$http({
				url: 'getAssetInfoBetweenDates',
				data: JSON.stringify(json),
				method: "POST",
				dataType: 'json', 

			}).then(function(response) {
				//$scope.myWelcome = response.data;
				
				var dataArr = [];
				for(var i in response.data)
				{
				  //console.log(response.data);
					var tempArr = [];
					tempArr.push(response.data[i].assetType);
					console.log(response.data[i].assetType);
					tempArr.push(parseInt(response.data[i].count));
					dataArr.push(tempArr);
				  
				}
				var colors = ['#14BBAE'];
				var id = 'assets';
				var type = {
					type: 'column',
					backgroundColor:'transparent'
				};
				var legend = {
					enabled: false
				};
				var yAxis = {
					min: 0,
					gridLineWidth: 0,
					minorGridLineWidth: 0,
					title: {
						text: null
					},
				};
				var xAxis = {
					min: 0,
					gridLineWidth: 0,
					minorGridLineWidth: 0,
					categories: []
				};
				var plotOptions ={ column: {
						colorByPoint: false
					}};
				var tooltip = {
					pointFormat: '{point.y}'
				};

				var data = [{
					name: '',
					data: dataArr,

					dataLabels: {
						enabled: true,
						color: '#FFFFFF',
						align: 'right',
						format: '{point.y}',
					}
				}];
				highcharts(id,type,xAxis,yAxis,legend,data,plotOptions,tooltip,colors);
			});
		}
		
		function getAlertDetailsOfCategoryByStatusWise(){
			var json = {
				fromDate:dd-MM-yyyy,
				toDate:dd-MM-yyyy,
				deptId:Long,
				year:String
			}
			$http({
				url: 'getAlertDetailsOfCategoryByStatusWise',
				data: JSON.stringify(json),
				method: "POST",
				dataType: 'json', 

			}).then(function(response) {
				
			});
		}
		
		function getAlertFeedbackStatusDetails(){
			var json = {
				fromDate:dd-MM-yyyy,
				toDate:dd-MM-yyyy,
				deptId:Long,
				year:String
			}
			$http({
				url: 'getAlertFeedbackStatusDetails',
				data: JSON.stringify(json),
				method: "POST",
				dataType: 'json', 

			}).then(function(response) {
				
			});
		}
	});