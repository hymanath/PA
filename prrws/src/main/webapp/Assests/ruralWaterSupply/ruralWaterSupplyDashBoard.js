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
				console.log(response.data);
			});
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
				//$scope.myWelcome = response.data;
				console.log(response.data);
			});
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
				//$scope.myWelcome = response.data;
				console.log(response.data);
			});
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
				console.log(response.data);
			});
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
	});